package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.mapper.LinkmanMapper;
import com.mengan.bus.service.LinkmanService;
import com.mengan.bus.vo.CustomerVo;
import com.mengan.bus.vo.LinkmanVo;
import com.mengan.sys.domain.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkmanServiceImpl implements LinkmanService {

    @Autowired
    LinkmanMapper linkmanMapper;

    @Override
    public DataGridView queryAllLinkman(LinkmanVo linkmanVo) {
        //使用分页助手
        Page<Object> page = PageHelper.startPage(linkmanVo.getPage(), linkmanVo.getLimit());
        List<LinkmanVo> data = linkmanMapper.queryAllLinkmanVo(linkmanVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addLinkman(LinkmanVo linkmanVo) {
        linkmanMapper.insertSelective(linkmanVo);
    }

    @Override
    public void deleteLinkman(Integer id) {
        linkmanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateLinkman(LinkmanVo linkmanVo) {
        linkmanMapper.updateByPrimaryKeySelective(linkmanVo);
    }

    @Override
    public Integer queryLinkmanByCid(Integer cid) {
        return linkmanMapper.queryLinkmanByCid(cid);
    }
}
