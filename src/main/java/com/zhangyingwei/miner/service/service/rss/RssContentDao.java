package com.zhangyingwei.miner.service.service.rss;

import com.zhangyingwei.miner.service.date.mapper.ContentMapper;
import com.zhangyingwei.miner.service.utils.MybatisUtils;

/**
 * @author: zhangyw
 * @date: 2017/8/20
 * @time: 下午9:47
 * @desc:
 */
public class RssContentDao {
    private ContentMapper contentMapper;

    public RssContentDao() {
        this.contentMapper = MybatisUtils.getMapper(ContentMapper.class);
    }

    
}
