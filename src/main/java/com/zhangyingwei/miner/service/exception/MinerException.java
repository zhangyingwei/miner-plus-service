package com.zhangyingwei.miner.service.exception;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerException extends Exception {
    public MinerException() {
    }

    public MinerException(String message) {
        super(message);
    }

    public MinerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinerException(Throwable cause) {
        super(cause);
    }

    public MinerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
