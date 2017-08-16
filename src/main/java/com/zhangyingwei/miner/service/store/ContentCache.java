package com.zhangyingwei.miner.service.store;

import com.zhangyingwei.miner.service.content.model.RssBody;
import com.zhangyingwei.miner.service.date.model.Content;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ContentCache {
    private static Map<String, Content> rssCache = new HashMap<String, Content>();

    public static void put(String key, Content value){
        synchronized (rssCache){
            rssCache.put(key, value);
        }
    }

    public static Content get(String link){
        synchronized (rssCache){
            return rssCache.get(link);
        }
    }
}
