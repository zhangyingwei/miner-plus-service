package com.zhangyingwei.miner.service.server;

import com.zhangyingwei.cockroach.CockroachContext;
import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.miner.service.store.MinerStore;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerServer {

    public void start() throws InstantiationException, IllegalAccessException {
        CockroachConfig config = new CockroachConfig()
                .setAppName("Miner 服务端")
                .setStore(MinerStore.class)
                .setThread(5);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = new TaskQueue();
        context.start(queue);
    }
}
