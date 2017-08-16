package com.zhangyingwei.miner.service.date.action;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.date.service.ResourcesService;
import com.zhangyingwei.miner.service.exception.MinerException;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ResourcesAction {
    private ResourcesService resourcesService;

    public ResourcesAction() {
        this.resourcesService = new ResourcesService();
    }

    public TaskQueue bulidTaskQueue() throws MinerException {
        System.out.println("INFO: 准备入队...");
        List<Resources> res = this.resourcesService.listNolamResources();
        final TaskQueue queue = TaskQueue.of(1024);
        res.stream().map(item -> {
            Task task = new Task(item.getResources(),item.getGroup());
            return task;
        }).forEach(item -> {
            try {
                queue.push(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("INFO: 入队完毕 - " + res.size());
        return queue;
    }

    public void unvalid(Task task) throws MinerException {
        this.resourcesService.markResourcesAsUnValid(task.getUrl());
    }


}
