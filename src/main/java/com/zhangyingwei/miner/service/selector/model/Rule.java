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
    private int lengthFrom = 0;
    private int lengthTo = 0;

    public Rule(String id, String name, String selector,String length) {
        this.id = id;
        this.name = name;
        this.selector = selector;
        if (length != null && length.contains("-")) {
            String[] tmp = length.split("-");
            this.lengthFrom = Integer.parseInt(tmp[0]);
            this.lengthTo = Integer.parseInt(tmp[1]);
        }
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

    public boolean is(String name) {
        return name.equals(this.getId());
    }

    public String getAttr() {
        return this.getId().split("\\.")[1].trim();
    }

    public boolean isName(String name) {
        return name.equals(this.name);
    }

    public int getLengthFrom() {
        return lengthFrom;
    }

    public int getLengthTo() {
        return lengthTo;
    }

    public boolean hasLengthLimit() {
        return this.lengthTo > 0;
    }
}
