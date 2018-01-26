package com.zhangyingwei.miner.service.store.istore.rss;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.selector.MinerSelector;
import com.zhangyingwei.miner.service.selector.model.Selector;
import com.zhangyingwei.miner.service.service.rss.RssContentAction;
import com.zhangyingwei.miner.service.store.ContentCache;
import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssItemStore implements IStore {
    Logger logger = Logger.getLogger(RssItemStore.class);
    private RssContentAction rssContentAction;

    public RssItemStore() {
        this.rssContentAction = new RssContentAction();
    }

    @Override
    public void store(TaskResponse taskResponse) throws Exception {
//        Content content = ContentCache.get(key);
//        content.setContent(result.toString());
//        logger.info("-----------------"+content);
//        this.rssContentAction.addNewContent(content);
    }
}
