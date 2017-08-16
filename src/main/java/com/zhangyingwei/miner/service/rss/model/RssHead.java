package com.zhangyingwei.miner.service.rss.model;

import org.dom4j.Element;
import org.dom4j.QName;

/**
 * Created by zhangyw on 2017/8/16.
 * rss 头信息
 * 包含 站点主要信息
 */
public class RssHead {
    private String title;
    private String link;
    private String description;
    public RssHead(Element headElement) {
        this.title = headElement.elementText(QName.get("title"));
        this.link = headElement.elementText(QName.get("link"));
        this.description = headElement.elementText("description");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RssHead{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
