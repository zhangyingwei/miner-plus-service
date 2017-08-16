package com.zhangyingwei.miner.service.store.handler;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.miner.service.date.action.ResourcesAction;
import com.zhangyingwei.miner.service.exception.MinerException;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ErrorHandler implements ITaskErrorHandler {

    private ResourcesAction resourcesAction;

    public ErrorHandler() {
        this.resourcesAction = new ResourcesAction();
    }

    public void error(Task task, String message) {
        try {
            this.resourcesAction.unvalid(task);
        } catch (MinerException e) {
            System.out.println(e.getMessage());
        }
    }
}
