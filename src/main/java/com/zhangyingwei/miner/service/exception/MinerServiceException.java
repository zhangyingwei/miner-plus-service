package com.zhangyingwei.miner.service.exception;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerServiceException extends Exception {
    public MinerServiceException() {
    }

    public MinerServiceException(String message) {
        super(message);
    }

    public MinerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinerServiceException(Throwable cause) {
        super(cause);
    }

    public MinerServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
