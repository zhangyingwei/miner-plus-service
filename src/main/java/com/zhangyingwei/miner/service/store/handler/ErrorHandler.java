package com.zhangyingwei.miner.service.store.handler;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.miner.service.date.action.ResourcesAction;
import com.zhangyingwei.miner.service.exception.MinerServiceException;
import com.zhangyingwei.miner.service.store.GroupEnum;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ErrorHandler implements ITaskErrorHandler {

    private Logger logger = Logger.getLogger(ErrorHandler.class);
    private ResourcesAction resourcesAction;

    public ErrorHandler() {
        this.resourcesAction = new ResourcesAction();
    }

    public void error(Task task, String message) {
        try {
            logger.error("ERROR-TASK:"+task);
            if(GroupEnum.RSS.getName().equals(task.getGroup()) || GroupEnum.SITE.getName().equals(task.getGroup())){
                this.resourcesAction.unvalid(task);
                logger.info("invalid task - " + task);
            }
            logger.error(""+task.getUrl());
        } catch (MinerServiceException e) {
//            System.out.println(e.getMessage());
        }
    }
}
