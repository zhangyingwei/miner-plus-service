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
public class ItemSelector {

    private Map<String, Rule> selecterMap;

    public ItemSelector(Element item) {
        this.selecterMap = new HashMap<String,Rule>();
        item.elements().stream().forEach(element -> {
            Element el = (Element) element;
            String key = el.getName();
            String value = el.getText();
            String id = el.attributeValue("id");
            selecterMap.put(key, new Rule(id,key,value));
        });
    }

    public Map<String, Object> select(Document document) {
        Rule urlRule = this.selecterMap.get("url");
        List<String> urls = document.select(urlRule.getSelector()).stream().map(element -> SelectUtils.getRulResult(urlRule,element)).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("urls", urls);
        return result;
    }
}
