package com.zhangyingwei.miner.service.utils;

import com.zhangyingwei.miner.service.selector.model.Rule;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class SelectUtils {
    public static String getRulResult(Rule rule, Element element) {
        if (rule.isText()) {
            return element.text();
        } else if (rule.isAttr()) {
            return element.attr(rule.getAttr());
        } else if (rule.isName("site")) {
            return rule.getSelector();
        } else if (rule.isName("author")) {
            return rule.getSelector();
        }
        return "";
    }

    public static String getRulResult(Rule rule, Elements elements) {
        if (rule.isText()) {
            return elements.text();
        } else if (rule.isAttr()) {
            return elements.attr(rule.getAttr());
        } else if (rule.isName("site")) {
            return rule.getSelector();
        } else if (rule.isName("author")) {
            return rule.getSelector();
        }
        return "";
    }
}
