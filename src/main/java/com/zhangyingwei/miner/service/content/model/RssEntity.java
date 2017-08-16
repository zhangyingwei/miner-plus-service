package com.zhangyingwei.miner.service.content.model;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssEntity {
    private RssHead head;
    private List<RssBody> bodies;

    public RssEntity(Document document) {
        this.head = new RssHead(this.getHeadEl(document));
        this.bodies = this.bulidBodys(document);
    }

    private List<RssBody> bulidBodys(Document document) {
        List<RssBody> bodies = new ArrayList<RssBody>();
        document.getRootElement().elements("item").stream().map(item -> new RssBody((Element) item)).forEach(item -> {
            bodies.add((RssBody) item);
        });
        return bodies;
    }

    private Element getHeadEl(Document document) {
        return document.getRootElement();
    }

    public RssHead getHead() {
        return head;
    }

    public List<RssBody> getBodies() {
        return bodies;
    }
}
