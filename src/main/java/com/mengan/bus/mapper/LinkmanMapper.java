package com.mengan.bus.mapper;

import com.mengan.bus.domain.Linkman;
import com.mengan.bus.vo.LinkmanVo;

import java.util.List;

public interface LinkmanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Linkman record);

    int insertSelective(Linkman record);

    Linkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Linkman record);

    int updateByPrimaryKey(Linkman record);

    /**
     * 查询联系人信息
     * @param linkmanVo
     * @return
     */
    List<LinkmanVo> queryAllLinkmanVo(LinkmanVo linkmanVo);

    /**
     * 根据顾客公司id查询是否存在联系人
     * @param cid
     * @return
     */
    int queryLinkmanByCid(Integer cid);
}