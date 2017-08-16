package com.zhangyingwei.miner.service.date.model;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class Content {
    private Integer id;
    private String author;
    private String sitename;
    private String site;
    private String title;
    private String url;
    private String content;
    private String pubdate;
    private String getdate;
    private String topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getGetdate() {
        return getdate;
    }

    public void setGetdate(String getdate) {
        this.getdate = getdate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", sitename='" + sitename + '\'' +
                ", site='" + site + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", getdate='" + getdate + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}