package com.zhangyingwei.miner.service.content;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class RssContentReader implements ContentReader {

    @Override
    public List<Content> read(String content) throws Exception{
//        SAXReader reader = new SAXReader();
//        Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
//        return this.bulidContents(doc);
        return this.bulidContentsByRome(content);
    }

    private List<Content> bulidContentsByRome(String content) throws FeedException {
        List<Content> contents = new ArrayList<Content>();
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputSource(new ByteArrayInputStream(content.getBytes())));
        return feed.getEntries().stream().map(syndEntry -> {
            Content cont = new Content();
            cont.setSitename(feed.getTitle());
            cont.setSite(feed.getLink());
            cont.setTitle(syndEntry.getTitle());
            cont.setUrl(syndEntry.getLink());
            cont.setDescription(syndEntry.getDescription().getValue());
            cont.setAuthor(feed.getAuthor());
            cont.setPubdate(DateUtils.formateDateTime(Optional.ofNullable(syndEntry.getPublishedDate()).orElse(new Date())));
            cont.setGetdate(DateUtils.getCurrentDateTime());
            return cont;
        }).collect(Collectors.toList());
    }

    private List<Content> bulidContents(Document doc) {
        Element root = Optional
                .ofNullable(doc.getRootElement().element(QName.get("channel")))
                .orElse(doc.getRootElement());

        String author = null;
        if(root.element("author") != null){
            if(root.element("author").element("name") != null){
                author = root.element("author").elementText("name");
            }else{
                author = root.elementText("author");
            }
        }

        final String authorValue = author;

        String sitename = root.elementText("title");

        String site = Optional
                .ofNullable(root.elementText("link").length() ==0 ? null:root.elementText("link"))
                .orElse(root.elementText("id"));

        List<Element> items = root.elements("item");
        if(items.size() == 0){
            items = root.elements("entry");
        }

        return items.stream().map(el -> {
            Content content = new Content();
            content.setSitename(sitename);
            content.setSite(site);
            content.setUrl(
                    Optional
                            .ofNullable(el.elementText("link").length() == 0 ? null : el.elementText("link"))
                            .orElse(el.element("link").attributeValue("href"))
            );
            content.setTitle(el.elementText("title"));
            content.setAuthor(Optional.ofNullable(authorValue).orElse(content.getSitename()));
            content.setPubdate(DateUtils.formateRssDate(
                    Optional
                            .ofNullable(el.elementText("pubDate"))
                            .orElse(Optional
                                    .ofNullable(el.elementText("updated"))
                                    .orElse(el.elementText("updated"))
                            )
            ));
            content.setGetdate(DateUtils.getCurrentDateTime());
            return content;
        }).collect(Collectors.toList());
    }
}
