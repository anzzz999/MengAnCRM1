package com.mengan.sys.controller;

import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.News;
import com.mengan.sys.domain.User;
import com.mengan.sys.service.NewsService;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import com.mengan.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 加载公告列表返回DataGridView
     */
    @RequestMapping("loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo) {
        return this.newsService.queryAllNews(newsVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping("addNews")
    public ResultObj addNews(News news) {
        try {
            news.setCreatetime(new Date());
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            news.setOpername(user.getRealname());
            this.newsService.addNews(news);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改公告
     */
    @RequestMapping("updateNews")
    public ResultObj updateNews(News news) {
        try {
            this.newsService.updateNews(news);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(News news) {
        try {
            this.newsService.deleteNews(news.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            this.newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("loadNewsById")
    public News loadNewsById(Integer id) {
        return this.newsService.queryNewsById(id);
    }
}
