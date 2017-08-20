package com.zhangyingwei.miner.service.date.mapper;

import com.zhangyingwei.miner.service.date.model.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
@Mapper
public interface ResourcesMapper {

    @Select("select * from mp_resources where flag=1")
    List<Resources> listResourcesNomal() throws Exception;

    @Update("update mp_resources set flag=#{flag} where resources=#{res}")
    void UpdateFlagByResources(@Param("res") String resources,@Param("flag") Integer flag) throws Exception;
}
