package com.zhangyingwei.miner.service.utils;

import com.zhangyingwei.miner.service.selector.model.Rule;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class SelectUtils {
    public static String getRulResult(Rule rule, Element elements) {
        String result = "";
        if (rule.is("text")) {
            result = elements.text();
        } else if (rule.isAttr()) {
            result = elements.attr(rule.getAttr());
        } else if (rule.isName("site")) {
            result = rule.getSelector();
        } else if (rule.isName("author")) {
            result = rule.getSelector();
        } else if (rule.is("html")) {
            result = elements.html();
        }
        if (rule.hasLengthLimit()) {
            int from = rule.getLengthFrom();
            int to = rule.getLengthTo();
            if (result.length() > to) {
                result = result.substring(from, to);
            }
        }
        return result;
    }

    public static String getRulResult(Rule rule, Elements elements) {
        String result = "";
        if (rule.is("text")) {
            result = elements.text();
        } else if (rule.isAttr()) {
            result = elements.attr(rule.getAttr());
        } else if (rule.isName("site")) {
            result = rule.getSelector();
        } else if (rule.isName("author")) {
            result = rule.getSelector();
        } else if (rule.is("html")) {
            result = elements.html();
        }
        if (rule.hasLengthLimit()) {
            int from = rule.getLengthFrom();
            int to = rule.getLengthTo();
            if (result.length() > to) {
                result = result.substring(from, to);
            }
        }
        return result;
    }
}
