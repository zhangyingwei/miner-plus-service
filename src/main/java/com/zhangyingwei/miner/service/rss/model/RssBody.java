package com.zhangyingwei.miner.service.rss.model;

import org.dom4j.Element;
import org.dom4j.QName;

/**
 * Created by zhangyw on 2017/8/16.
 * rss body 信息
 * 包含文章信息
 */
public class RssBody {
    private String title;
    private String link;
    private String pubdate;
    private String content;

    public RssBody(Element bodyElement) {
        this.title = bodyElement.elementText(QName.get("title"));
        this.link = bodyElement.elementText(QName.get("link"));
        this.pubdate = bodyElement.elementText(QName.get("pubDate"));
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

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RssBody{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
