package com.zhangyingwei.miner.service.store.istore;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.rss.model.RssBody;
import com.zhangyingwei.miner.service.rss.model.RssEntity;
import com.zhangyingwei.miner.service.store.RssCache;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssStore implements IStore{
    @Override
    public void store(TaskResponse taskResponse) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new ByteArrayInputStream(taskResponse.getContent().getBytes()));
            RssEntity entity = new RssEntity(doc);
            List<RssBody> bodies = entity.getBodies();
            TaskQueue queue = TaskQueue.of();
            bodies.stream().filter(body -> body.getLink()!=null && body.getLink().trim().length() > 0).sorted((b1,b2) -> b1.getPubdate().compareTo(b2.getPubdate())).limit(20).forEach(body -> {
                Task task = new Task(body.getLink());
                task.setGroup("rssentity");
                try {
                    queue.push(task);
                    RssCache.put(body);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
