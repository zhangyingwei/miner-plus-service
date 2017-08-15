package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.service.server.MinerServer;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerServiceApplication {
    public static void main(String[] args) {
        new MinerServer().start();
    }
}
