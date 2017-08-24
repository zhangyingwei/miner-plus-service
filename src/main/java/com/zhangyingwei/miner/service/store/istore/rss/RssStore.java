package com.zhangyingwei.miner.service.store.istore.rss;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.content.RssContentReader;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.store.ContentCache;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssStore implements IStore{
    private Logger logger = Logger.getLogger(RssStore.class);
    private RssContentReader reader = new RssContentReader();
    private TaskQueue queue = TaskQueue.of();

    @Override
    public void store(TaskResponse taskResponse) {
        try {
            Resources resources = (Resources) taskResponse.getTask().getExtr();
            List<Content> contents = this.reader.read(taskResponse.getContent());
            List<Task> tasks = contents.stream()
                    .map(content -> {
                        content.setTopic(resources.getType());
                        return content;
                    })
                    .filter(body -> (body.getUrl() != null && body.getUrl().trim().length() > 0))
                    .sorted((b1, b2) -> b1.getPubdate().compareTo(b2.getPubdate()))
                    .limit(20).map(body -> {
                        Task task = new Task(body.getUrl());
                        task.setGroup("rssentity");
                        ContentCache.put(body.getUrl(), body);
                        return task;
                    }).collect(Collectors.toList());
            this.queue.pushAll(tasks);
            logger.info("pull into queue - "+ tasks.size());
        }catch (Exception e){
            logger.error("RssStore - "+ e.getMessage());
        }
    }
}