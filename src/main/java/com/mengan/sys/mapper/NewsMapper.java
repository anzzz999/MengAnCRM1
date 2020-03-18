package com.mengan.sys.mapper;

import com.mengan.sys.domain.News;
import com.mengan.sys.vo.NewsVo;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    /**
     * 查询全部news
     */
    List<News> queryAllNews(NewsVo newsVo);
}