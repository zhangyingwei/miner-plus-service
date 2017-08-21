package com.zhangyingwei.miner.service.service.rss;

import com.zhangyingwei.miner.service.date.mapper.ContentMapper;
import com.zhangyingwei.miner.service.date.model.Content;
import com.zhangyingwei.miner.service.exception.MinerServiceException;
import com.zhangyingwei.miner.service.utils.DateUtils;
import com.zhangyingwei.miner.service.utils.MybatisUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2017/8/20
 * @time: 下午9:46
 * @desc:
 */
public class RssContentService {

    private Logger logger = Logger.getLogger(RssContentService.class);

    private ContentMapper contentMapper;

    public RssContentService() {
        this.contentMapper = MybatisUtils.getMapper(ContentMapper.class);
    }

    /**
     * 新增内容
     * 1 判断是不是新内容
     * 如果是，添加 <br/>
     * 如果不是，直接pass
     * @param content
     * @throws MinerServiceException
     */
    public void addNewContent(Content content) throws MinerServiceException {
        try {
            if (isNewContent(content)) {
                logger.info("add new content - " + content.getTitle());
                this.contentMapper.addContent(content);
            } else {
                logger.info("old content - " + content.getTitle());
            }
        } catch (Exception e) {
            throw new MinerServiceException(e);
        }
    }

    /**
     * 判断是不是新的文章
     * 1 按照标题查询文章
     * 2 如果结果不为空，说明之前就有这篇文章
     * 3 判断发表时间是否相同
     * 4 如果不相同，说明文章有跟新，作为新文章处理
     * @param content
     * @return
     */
    private boolean isNewContent(Content content) throws Exception {
        List<Content> olds = this.contentMapper.listContentByTitle(content.getTitle());
        if(olds == null || olds.size() == 0){
            return true;
        }
        return !olds.stream()
                .map(old -> old.getPubdate())
                .map(date -> DateUtils.dateTimeToDate(date))
                .collect(Collectors.toList())
                .contains(DateUtils.dateTimeToDate(content.getPubdate()));
    }
}
