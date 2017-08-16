package com.zhangyingwei.miner.service.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.selector.MinerSelector;
import com.zhangyingwei.miner.service.store.istore.RssEntityStore;
import com.zhangyingwei.miner.service.store.istore.RssStore;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class MinerStore implements IStore {
    private MinerSelector selector = new MinerSelector("selector.xml");
    private IStore rssStore = new RssStore();
    private IStore rssEntityStore = new RssEntityStore(selector);

    public void store(TaskResponse taskResponse) throws Exception {
        String group = taskResponse.getTask().getGroup();
        if(GroupEnum.RSS.getName().equals(group)){
            this.rssStore.store(taskResponse);
        } else if (GroupEnum.RSSENTITY.getName().equals(group)) {
            this.rssEntityStore.store(taskResponse);
        }
    }
}
