package com.zhangyingwei.miner.service.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.selector.MinerSelector;
import com.zhangyingwei.miner.service.store.istore.rss.RssContentStore;
import com.zhangyingwei.miner.service.store.istore.rss.RssStore;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerStore implements IStore {
    private Logger logger = Logger.getLogger(MinerStore.class);
    private IStore rssStore;
    private IStore rssEntityStore;

    public MinerStore() {
        MinerSelector selector = new MinerSelector("/selector.xml");
        try {
            selector.read();
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        this.rssStore = new RssStore();
        this.rssEntityStore = new RssContentStore(selector);
    }

    public void store(TaskResponse taskResponse) throws Exception {
        String group = taskResponse.getTask().getGroup();
        logger.info("reveice - " + taskResponse.getTask().getUrl());
        if(GroupEnum.RSS.getName().equals(group)){
            this.rssStore.store(taskResponse);
        } else if (GroupEnum.RSSENTITY.getName().equals(group)) {
            this.rssEntityStore.store(taskResponse);
        }
    }
}
