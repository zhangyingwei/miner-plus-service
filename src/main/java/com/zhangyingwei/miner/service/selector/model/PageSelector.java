package com.zhangyingwei.miner.service.selector.model;

import com.zhangyingwei.miner.service.utils.SelectUtils;
import org.dom4j.Element;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class PageSelector {

    private Map<String, Rule> selecterMap;

    public PageSelector(Element item) {
        this.selecterMap = new HashMap<String,Rule>();
        item.elements().stream().forEach(element -> {
            Element el = (Element) element;
            String key = el.getName();
            String value = el.getText();
            String id = el.attributeValue("id");
            selecterMap.put(key, new Rule(id,key,value));
        });
    }

    public Map<String,String> select(Document document) {
        return this.selecterMap.entrySet().stream().map((Map.Entry<String, Rule> entity) -> {
            String key = entity.getKey();
            Rule rule = entity.getValue();
            String value = SelectUtils.getRulResult(rule, document.select(rule.getSelector()));
            return new Item(key,value);
        }).collect(Collectors.toMap(Item::getKey,Item::getValue));
    }

    private class Item {
        String key;
        String value;
        public Item(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
