package com.zhangyingwei.miner.service.filter;

/**
 * Created by zhangyw on 2017/8/21.
 */
public interface MinerFilter {
    <T>T filter(T t);
}
