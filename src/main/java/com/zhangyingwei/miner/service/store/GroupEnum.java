package com.zhangyingwei.miner.service.store;

/**
 * Created by zhangyw on 2017/8/16.
 */
public enum GroupEnum {
    RSS("rss"),
    RSSENTITY("rss.entity"),
    SITE("site"),
    SITEITEMPAGE("site.item.page"),
    SITEITEM("site.item");

    private String name;
    GroupEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
