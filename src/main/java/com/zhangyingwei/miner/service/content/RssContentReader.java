package com.zhangyingwei.miner.service.content;

import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
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
        Element root = doc.getRootElement().element(QName.get("channel"));
        String author = "";
        String sitename = root.elementText(QName.get("title"));
        String site = root.elementText(QName.get("link"));
        List<Element> items = root.elements(QName.get("item"));
        return items.stream().map(el -> {
            Content content = new Content();
            content.setAuthor(author);
            content.setSitename(sitename);
            content.setSite(site);
            content.setUrl(el.elementText(QName.get("link")));
            content.setTitle(el.elementText(QName.get("title")));
            content.setPubdate(DateUtils.formateRssDate(el.elementText(QName.get("pubDate"))));
            content.setGetdate(DateUtils.getCurrentDateTime());
            return content;
        }).collect(Collectors.toList());
    }
}
