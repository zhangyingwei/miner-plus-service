package com.zhangyingwei.miner.service.date.service;

import com.zhangyingwei.miner.service.date.mapper.ResourcesMapper;
import com.zhangyingwei.miner.service.date.model.Resources;
import com.zhangyingwei.miner.service.exception.MinerException;
import com.zhangyingwei.miner.service.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class ResourcesService implements IResourcesService {

    private ResourcesMapper mapper = MybatisUtils.getMapper(ResourcesMapper.class);

    @Override
    public List<Resources> listNolamResources() throws MinerException {
        try {
            return mapper.listResourcesNomal();
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }

    @Override
    public void markResourcesAsUnValid(String resources) throws MinerException{
        try {
            this.mapper.UpdateFlagByResources(resources,Resources.FLAG_UNVALID);
        } catch (Exception e) {
            throw new MinerException(e);
        }
    }
}
