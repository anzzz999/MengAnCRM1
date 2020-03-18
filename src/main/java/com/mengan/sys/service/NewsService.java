package com.mengan.sys.service;

import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.News;
import com.mengan.sys.vo.NewsVo;
import org.springframework.stereotype.Service;

/**
 * 公告管理的服务接口
 */
public interface NewsService {
    /**
     * 查询所有公告
     * @param newsVo
     * @return
     */
    public DataGridView queryAllNews(NewsVo newsVo);
    /**
     * 添加公告
     */
    public void addNews(News news);
    /**
     * 修改公告
     */
    public void updateNews(News news);
    /**
     * 根据id删除公告
     * @param newsid
     */
    public void deleteNews(Integer newsid);

    /**
     * 批量删除公告
     */
    public void deleteBatchNews(Integer [] ids);

    /**
     * 查询一个公告
     * @param id
     * @return
     */
    public News queryNewsById(Integer id);

}
