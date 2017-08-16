package com.zhangyingwei.miner.service.server;

import com.zhangyingwei.cockroach.CockroachContext;
import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.miner.service.date.action.ResourcesAction;
import com.zhangyingwei.miner.service.exception.MinerException;
import com.zhangyingwei.miner.service.store.MinerStore;
import com.zhangyingwei.miner.service.store.handler.ErrorHandler;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerServer {

    private ResourcesAction resourcesAction;

    public MinerServer() {
        this.resourcesAction = new ResourcesAction();
    }

    public void start() throws MinerException {
        CockroachConfig config = new CockroachConfig()
                .setAppName("Miner 服务端")
                .setStore(MinerStore.class)
                .setThread(5,5000)
                .setAutoClose(true)
                .setTaskErrorHandler(ErrorHandler.class);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = this.resourcesAction.bulidTaskQueue();
        try {
            context.start(queue);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
