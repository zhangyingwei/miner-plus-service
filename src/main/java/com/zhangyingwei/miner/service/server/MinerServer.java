package com.zhangyingwei.miner.service.server;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.miner.service.date.action.ResourcesAction;
import com.zhangyingwei.miner.service.exception.MinerServiceException;
import com.zhangyingwei.miner.service.store.MinerStore;
import com.zhangyingwei.miner.service.store.handler.ErrorHandler;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/8/15.
 */
@EnableAutoConfiguration
@AppName("Miner 服务端")
@Store(MinerStore.class)
@ThreadConfig(num = 10, sleep = 5000)
@AutoClose(true)
@TaskErrorHandlerConfig(ErrorHandler.class)
public class MinerServer {

    private Logger logger = Logger.getLogger(MinerServer.class);
    private ResourcesAction resourcesAction;

    public MinerServer() {
        this.resourcesAction = new ResourcesAction();
    }

    public void start() throws MinerServiceException {
        CockroachQueue queue = this.resourcesAction.bulidTaskQueue();
        try {
            CockroachApplication.run(MinerServer.class,queue);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
