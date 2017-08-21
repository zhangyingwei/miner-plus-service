package com.zhangyingwei.miner.service.date.action;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.date.service.ResourcesService;
import com.zhangyingwei.miner.service.exception.MinerServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ResourcesAction {
    private Logger logger = Logger.getLogger(ResourcesAction.class);
    private ResourcesService resourcesService;

    public ResourcesAction() {
        this.resourcesService = new ResourcesService();
    }

    public TaskQueue bulidTaskQueue() throws MinerServiceException {
        logger.info("准备入队...");
        List<Resources> res = this.resourcesService.listNolamResources();
        final TaskQueue queue = TaskQueue.of(1024);
        res.stream().map(item -> {
            Task task = new Task(item.getResources(),item.getGroup());
            task.setExtr(item);
            return task;
        }).forEach(item -> {
            try {
                queue.push(item);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        });
        logger.info("入队完毕 - " + res.size());
        return queue;
    }

    public void unvalid(Task task) throws MinerServiceException {
        this.resourcesService.markResourcesAsUnValid(task.getUrl());
    }


}
