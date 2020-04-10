package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.domain.Demand;
import com.mengan.bus.mapper.DemandMapper;
import com.mengan.bus.service.DemandService;
import com.mengan.bus.vo.DemandVo;
import com.mengan.sys.domain.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    DemandMapper demandMapper;
    @Override
    public DataGridView queryAllDemand(DemandVo demandVo) {
        Page<Object> page= PageHelper.startPage(demandVo.getPage(), demandVo.getLimit());
        List<Demand> data = this.demandMapper.queryAllDemand(demandVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addDemand(Demand demand) {
        this.demandMapper.insertSelective(demand);
    }

    @Override
    public void deleteDemand(Integer id) {
        this.demandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchDemand(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteDemand(integer);
        }
    }

    @Override
    public void updateDemand(Demand demand) {
        this.demandMapper.updateByPrimaryKeySelective(demand);
    }

    @Override
    public Demand queryDemandById(Integer id) {
        return this.demandMapper.selectByPrimaryKey(id);
    }

}
