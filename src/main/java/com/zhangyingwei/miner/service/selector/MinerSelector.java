package com.zhangyingwei.miner.service.selector;

import com.zhangyingwei.miner.service.selector.model.Selector;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class MinerSelector {
    private Logger logger = Logger.getLogger(MinerSelector.class);
    private String path;
    private Map<String, Selector> selectorMap;

    public MinerSelector(String path) {
        this.path = path;
        this.selectorMap = new HashMap<String, Selector>();
    }

    public MinerSelector read() throws DocumentException {
        SAXReader reader = new SAXReader();
//        this.path = this.getClass().getResource(this.path).getPath();
        Document doc = reader.read(new File(this.path));
        Element root = doc.getRootElement();
        root.elements(QName.get("selector")).forEach(sel -> {
            Selector selector = new Selector((Element) sel);
            selectorMap.put(selector.getKey(), selector);
        });
        return this;
    }

    public Selector getSelector(String key) {
        String realKey = URI.create(key).getHost();
        Selector value = this.selectorMap.get(realKey);
        if (value == null) {
            value = this.selectorMap.get("default");
            if (value == null) {
                logger.info("key - " + realKey + " is not found in selector map");
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return "MinerSelector{" +
                "path='" + path + '\'' +
                ", selectorMap=" + selectorMap +
                '}';
    }
}
