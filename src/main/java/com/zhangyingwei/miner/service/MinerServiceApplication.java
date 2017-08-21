package com.zhangyingwei.miner.service;

import com.zhangyingwei.miner.service.exception.MinerServiceException;
import com.zhangyingwei.miner.service.server.MinerServer;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerServiceApplication {
    private static Logger logger = Logger.getLogger(MinerServiceApplication.class);
    public static void main(String[] args) {
        try {
            new MinerServer().start();
        } catch (MinerServiceException e) {
            logger.error(e.getMessage());
        }
    }
}
