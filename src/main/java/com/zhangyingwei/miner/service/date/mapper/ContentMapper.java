package com.zhangyingwei.miner.service.date.mapper;

import com.zhangyingwei.miner.service.date.model.Content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/8/20
 * @time: 下午9:50
 * @desc:
 */
public interface ContentMapper {
    @Select("select * from mp_content where state=#{state}")
    List<Content> listContentsByState(@Param("state") Integer state) throws Exception;

    @Select("select * from mp_content where getdate like #{getdate} and state=#{state}")
    List<Content> listContentByStateAndGetDate(@Param("state") Integer state, @Param("getdate") String getDate) throws Exception;

    @Select("select * from mp_content where title=#{title}")
    List<Content> listContentByTitle(@Param("title") String title) throws Exception;

    @Insert("insert into mp_content (author,sitename,site,url,title,content,pubdate,getdate,topic) values (#{cont.author},#{cont.sitename},#{cont.site},#{cont.url},#{cont.title},#{cont.content},#{cont.pubdate},#{cont.getdate},#{cont.topic})")
    void addContent(@Param("cont") Content content) throws Exception;
}
