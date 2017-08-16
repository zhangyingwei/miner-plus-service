package com.zhangyingwei.miner.service.date.service;

import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.exception.MinerException;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/15.
 */
public interface IResourcesService {
    List<Resources> listNolamResources() throws MinerException;

    void markResourcesAsUnValid(String resources) throws MinerException;
}
