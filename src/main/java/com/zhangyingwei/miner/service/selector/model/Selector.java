package com.zhangyingwei.miner.service.selector.model;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class Selector {
    private String key;
    private List<String> andRules;
    private List<String> orRules;
    private List<String> notRules;

    public Selector(Element selector) {
        this.key = selector.attributeValue(QName.get("key"));
        this.andRules = new ArrayList<String>();
        this.orRules = new ArrayList<String>();
        this.notRules = new ArrayList<String>();
        this.getAllRules(selector);
    }

    private void getAllRules(Element selector) {
        selector.elements(QName.get("rule")).forEach(rule -> {
            Element el = (Element) rule;
            String type = el.attributeValue(QName.get("type"));
            if (type.equals("and")){
                this.andRules.add(el.getText());
            } else if (type.equals("or")) {
                this.orRules.add(el.getText());
            } else if (type.equals("not")) {
                this.notRules.add(el.getText());
            }
        });
    }

    public String getKey() {
        return key;
    }

    public Elements select(String content){
        org.jsoup.nodes.Document document = Jsoup.parse(content);
        Elements els = document.select(this.gerenateCssQuery());
        els.select(this.gerenateDeleteCssQuery()).remove();
        return els;
    }

    private String gerenateDeleteCssQuery() {
        return String.join(",", this.notRules);
    }

    private String gerenateCssQuery() {
        String andQuery = String.join(" ", this.andRules);
        String orQuery = String.join(",", this.orRules);
        return andQuery + "," + orQuery;
    }
}
