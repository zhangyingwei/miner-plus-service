package com.zhangyingwei.miner.service.store.istore;

import com.zhangyingwei.cockroach.executer.TaskResponse;
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
public class RssContentStore implements IStore {

    Logger logger = Logger.getLogger(RssContentStore.class);

    private MinerSelector minerSelector;

    private RssContentAction rssContentAction;

    public RssContentStore(MinerSelector selector) {
        this.minerSelector = selector;
        this.rssContentAction = new RssContentAction();
    }

    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String key = taskResponse.getTask().getUrl();
        Selector selector = minerSelector.getSelector(key);
        Elements result = selector.select(taskResponse.getContent());
        Content content = ContentCache.get(key);
        content.setContent(result.toString());
        this.rssContentAction.addNewContent(content);
    }
}
