package com.mengan.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.News;
import com.mengan.sys.mapper.NewsMapper;
import com.mengan.sys.service.NewsService;
import com.mengan.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;
    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page= PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
        List<News> data = this.newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addNews(News news) {
        this.newsMapper.insertSelective(news);
    }

    @Override
    public void deleteNews(Integer newsid) {
        this.newsMapper.deleteByPrimaryKey(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteNews(integer);
        }
    }

    @Override
    public void updateNews(News news) {
        this.newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public News queryNewsById(Integer id) {
        return this.newsMapper.selectByPrimaryKey(id);
    }

}
