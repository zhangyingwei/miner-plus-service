package com.zhangyingwei.miner.service.store.istore.rss;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.content.RssContentReader;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.service.rss.RssContentAction;
import com.zhangyingwei.miner.service.store.ContentCache;
import com.zhangyingwei.miner.service.store.GroupEnum;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssStore implements IStore{
    private Logger logger = Logger.getLogger(RssStore.class);
    private RssContentReader reader = new RssContentReader();
    private IStore rssItemStore;
    private RssContentAction rssContentAction;

    public RssStore() {
        this.rssItemStore = new RssItemStore();
        this.rssContentAction = new RssContentAction();
    }

    @Override
    public void store(TaskResponse taskResponse) {
        try {
            Resources resources = (Resources) taskResponse.getTask().getExtr();
            List<Content> contents = this.reader.read(taskResponse.getContent());
            contents.stream()
                    .map(content -> {
                        content.setTopic(resources.getRtype());
                        return content;
                    })
                    .filter(body -> (body.getUrl() != null && body.getUrl().trim().length() > 0))
                    .sorted((b1, b2) -> b1.getPubdate().compareTo(b2.getPubdate()))
                    .limit(20).forEach(article -> {
                        rssContentAction.addNewContent(article);
            });
        }catch (Exception e){
            logger.error("RssStore - "+ e.getMessage());
        }
    }
}
