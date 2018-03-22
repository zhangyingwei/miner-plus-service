package com.zhangyingwei.miner.service.store.istore.site;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.selector.MinerSelector;
import com.zhangyingwei.miner.service.selector.model.Selector;
import com.zhangyingwei.miner.service.store.GroupEnum;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class SiteStore implements IStore {
    private SiteItemStore siteItemStore;
    private MinerSelector minerSelector;
    private Logger logger = LoggerFactory.getLogger(SiteStore.class);

    public SiteStore() {
        this.siteItemStore = new SiteItemStore();
        this.minerSelector = new MinerSelector("/root/selector.xml");
        try {
            this.minerSelector.read();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(TaskResponse response) throws Exception {
        Selector selector = minerSelector.getSelector(response.getTask().getUrl());
        if (response.isGroup(GroupEnum.SITEITEM.getName())) {
            this.siteItemStore.setSelector(selector);
            this.siteItemStore.store(response);
        } else {
            Map<String, Object> itemRes = selector.selectItem(response.getDocument());
            List<String> urls = (List<String>) itemRes.get("urls");
            urls.stream().filter(url -> StringUtils.isNotBlank(url)).map(url -> {
                if (!url.startsWith("http://")) {
                    try {
                        String prefix = response.getTask().getUrl().split("/")[0];
                        return prefix + "//" + new URL(response.getTask().getUrl()).getHost().concat(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                return url;
            }).forEach(url -> {
                Task task = new Task(url);
                Resources resources = (Resources) response.getTask().getExtr();
                task.setExtr(response.getTask().getExtr());
                task.setGroup(GroupEnum.SITEITEM.getName());
                try {
                    response.getQueue().push(task);
                } catch (Exception e) {
                    logger.info("入队错误: " + e.getLocalizedMessage());
                }
            });
        }
    }
}
