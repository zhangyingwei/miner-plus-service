package com.zhangyingwei.miner.service.selector.model;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class Rule {
    private String id;
    private String name;
    private String selector;

    public Rule(String id, String name, String selector) {
        this.id = id;
        this.name = name;
        this.selector = selector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public boolean isAttr() {
        return this.getId().startsWith("attr.");
    }

    public boolean isText() {
        return "text".equals(this.getId());
    }

    public String getAttr() {
        return this.getId().split("\\.")[1].trim();
    }

    public boolean isName(String name) {
        return name.equals(this.name);
    }
}
