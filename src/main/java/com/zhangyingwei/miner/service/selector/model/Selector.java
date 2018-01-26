package com.zhangyingwei.miner.service.selector.model;

import org.dom4j.Element;
import org.dom4j.QName;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class Selector {
    private String key;

    private ItemSelector itemSelector;
    private PageSelector pageSelector;
    private Element selector;

    public Selector(Element selector) {
        this.selector = selector;
        this.itemSelector = new ItemSelector(this.selector.element("item"));
        this.pageSelector = new PageSelector(this.selector.element("page"));
        this.key = selector.attributeValue(QName.get("key"));
    }

    public String getKey() {
        return key;
    }

    public Map<String, Object> selectItem(Document document){
        return this.itemSelector.select(document);
    }

    public Map<String, String> selectPage(Document document){
        return this.pageSelector.select(document);
    }
}
