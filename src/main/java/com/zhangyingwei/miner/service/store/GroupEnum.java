package com.zhangyingwei.miner.service.store;

/**
 * Created by zhangyw on 2017/8/16.
 */
public enum GroupEnum {
    RSS("rss"),
    RSSENTITY("rssentity"),
    SITE("site");

    private String name;
    GroupEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
