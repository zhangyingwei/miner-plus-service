package com.zhangyingwei.miner.service.store.istore;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.content.RssContentReader;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.store.ContentCache;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssStore implements IStore{
    private RssContentReader reader = new RssContentReader();
    @Override
    public void store(TaskResponse taskResponse) {
        try {
            List<Content> contents = this.reader.read(taskResponse.getContent());
            TaskQueue queue = TaskQueue.of();
            contents.stream().filter(body -> body.getUrl()!=null && body.getUrl().trim().length() > 0).sorted((b1,b2) -> b1.getPubdate().compareTo(b2.getPubdate())).limit(20).forEach(body -> {
                Task task = new Task(body.getUrl());
                task.setGroup("rssentity");
                try {
                    queue.push(task);
                    ContentCache.put(body.getSite(),body);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
