package com.zhangyingwei.miner.service.store.istore.site;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.selector.model.Selector;
import com.zhangyingwei.miner.service.utils.DateUtils;

import java.util.Map;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class SiteItemStore implements IStore {
    private Selector selector;

    @Override
    public void store(TaskResponse response) throws Exception {
        Map<String, String> pageMap = this.selector.selectPage(response.getDocument());
        Resources resources = (Resources) response.getTask().getExtr();
        Content content = new Content();
        content.setAuthor(pageMap.get("author"));
        content.setSitename(pageMap.get("site"));
        content.setSite(resources.getResources());
        content.setTitle(pageMap.get("title"));
        content.setUrl(response.getTask().getUrl());
        content.setDescription(pageMap.get("description"));
        content.setContent(pageMap.get("content"));
        content.setPubdate(pageMap.get("pubdate"));
        content.setGetdate(DateUtils.getCurrentDateTime());
        content.setTopic(resources.getRtype());
        content.setState(Content.STATE_INIT);
        System.out.println(content);
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }
}
