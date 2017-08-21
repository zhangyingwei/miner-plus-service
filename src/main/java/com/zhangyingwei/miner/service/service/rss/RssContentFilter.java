package com.zhangyingwei.miner.service.service.rss;

import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.filter.MinerFilter;
import com.zhangyingwei.miner.service.utils.DateUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyw on 2017/8/21.
 */
public class RssContentFilter implements MinerFilter {
    private Logger logger = Logger.getLogger(RssContentFilter.class);

    @Override
    public <T> T filter(T t) {
        Content content = (Content) t;
        content.setContent(this.htmlContentFormate(content.getContent()));
        content.setPubdate(this.pubDateFormate(content.getPubdate()));
        return (T) content;
    }

    /**
     * 格式化 rss 时间为 datatime
     * @param pubdate
     * @return
     */
    private String pubDateFormate(String pubdate) {
        if(DateUtils.isDateTimeSring(pubdate)){
            return pubdate;
        }
        return DateUtils.formateRssDate(pubdate);
    }

    /**
     * 主要是为了去掉 html 内容中的类名
     * @param htmlContent
     * @return
     */
    private String htmlContentFormate(String htmlContent) {
        logger.info("do formate");
        htmlContent = this.filterEmoji(htmlContent);
        return htmlContent;
    }

    /**
     * 过滤 emoji 字符，因为 mysql 的 utf8 中不支持存储 emoji，所以只能过滤掉
     * @param source
     * @return
     */
    private String filterEmoji(String source) {
        if(source != null){
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find()){
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }
}
