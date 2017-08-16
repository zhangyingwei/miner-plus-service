package com.zhangyingwei.miner.service.content;

import com.zhangyingwei.miner.service.date.model.Content;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public interface ContentReader {
    List<Content> read(String content) throws Exception;
}
