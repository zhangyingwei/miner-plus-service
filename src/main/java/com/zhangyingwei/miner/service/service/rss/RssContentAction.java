package com.zhangyingwei.miner.service.service.rss;

import com.zhangyingwei.miner.service.date.model.Content;
import org.apache.log4j.Logger;

/**
 * @author: zhangyw
 * @date: 2017/8/20
 * @time: 下午8:27
 * @desc:
 */
public class RssContentAction {
    private Logger logger = Logger.getLogger(RssContentAction.class);

    public void addNewContent(Content content){
        logger.info(content.getTitle());
    }

}
