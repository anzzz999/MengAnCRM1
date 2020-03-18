package com.mengan.bus.service;

import com.mengan.bus.vo.LinkmanVo;
import com.mengan.sys.domain.DataGridView;

public interface LinkmanService {

    DataGridView queryAllLinkman(LinkmanVo linkmanVo);

    void addLinkman(LinkmanVo linkmanVo);

    void deleteLinkman(Integer id);

    void updateLinkman(LinkmanVo linkmanVo);


    Integer queryLinkmanByCid(Integer cid);
}
