package com.zhangyingwei.miner.service.store;

import com.zhangyingwei.miner.service.rss.model.RssBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssCache {
    private static Map<String, RssBody> rssCache = new HashMap<String, RssBody>();

    public static void put(RssBody body){
        rssCache.put(body.getLink(), body);
    }

    public static RssBody get(String link){
        return rssCache.get(link);
    }
}
