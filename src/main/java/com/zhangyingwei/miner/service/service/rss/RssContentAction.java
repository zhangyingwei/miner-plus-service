package com.zhangyingwei.miner.service.service.rss;

import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.exception.MinerServiceException;
import org.apache.log4j.Logger;

/**
 * @author: zhangyw
 * @date: 2017/8/20
 * @time: 下午8:27
 * @desc:
 */
public class RssContentAction {
    private Logger logger = Logger.getLogger(RssContentAction.class);

    private RssContentService rssContentService;
    private RssContentFilter rssContentFilter;

    public RssContentAction() {
        this.rssContentService = new RssContentService();
        this.rssContentFilter = new RssContentFilter();
    }

    public void addNewContent(Content content) {
        try {
            logger.info("get new content - "+content.getTitle());
            content = this.rssContentFilter.filter(content);
            this.rssContentService.addNewContent(content);
        } catch (MinerServiceException e) {
            logger.error(e.getMessage());
        }
    }

}
