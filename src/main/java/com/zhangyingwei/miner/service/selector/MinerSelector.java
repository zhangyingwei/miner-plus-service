package com.zhangyingwei.miner.service.selector;

import com.zhangyingwei.miner.service.selector.model.Selector;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class MinerSelector {
    private String path;
    private Map<String, Selector> selectorMap;

    public MinerSelector(String path) {
        this.path = path;
        this.selectorMap = new HashMap<String, Selector>();
    }

    private void read() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(this.path));
        Element root = doc.getRootElement();
        root.elements(QName.get("selector")).forEach(sel -> {
            Selector selector = new Selector((Element) sel);
            selectorMap.put(selector.getKey(), selector);
        });
    }

    public Selector getSelector(String key) {
        return this.selectorMap.get(key);
    }
}
