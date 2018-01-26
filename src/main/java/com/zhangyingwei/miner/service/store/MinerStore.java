package com.zhangyingwei.miner.service.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.selector.MinerSelector;
import com.zhangyingwei.miner.service.store.istore.rss.RssItemStore;
import com.zhangyingwei.miner.service.store.istore.rss.RssStore;
import com.zhangyingwei.miner.service.store.istore.site.SiteStore;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerStore implements IStore {
    private Logger logger = Logger.getLogger(MinerStore.class);
    private IStore rssStore;
    private IStore siteStore;

    public MinerStore() {
        this.rssStore = new RssStore();
        this.siteStore = new SiteStore();
    }

    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        if(taskResponse.isGroupStartWith(GroupEnum.RSS.getName())){
            this.rssStore.store(taskResponse);
        } else if (taskResponse.isGroupStartWith(GroupEnum.SITE.getName())) {
            this.siteStore.store(taskResponse);
        }
    }
}