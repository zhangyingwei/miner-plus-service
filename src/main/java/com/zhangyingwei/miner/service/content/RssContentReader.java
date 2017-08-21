package com.zhangyingwei.miner.service.content;

import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

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
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new ByteArrayInputStream(content.getBytes()));
        return this.bulidContents(doc);
    }

    private List<Content> bulidContents(Document doc) {
        Element root = Optional
                .ofNullable(doc.getRootElement().element(QName.get("channel")))
                .orElse(doc.getRootElement());

        String author = root.element("author").elementText("name");
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
            content.setAuthor(Optional.ofNullable(author).orElse(content.getSitename()));
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
