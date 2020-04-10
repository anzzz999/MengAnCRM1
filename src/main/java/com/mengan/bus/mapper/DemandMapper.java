package com.mengan.bus.mapper;

import com.mengan.bus.domain.Demand;
import com.mengan.bus.vo.DemandVo;

import java.util.List;

public interface DemandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demand record);

    int insertSelective(Demand record);

    Demand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demand record);

    int updateByPrimaryKey(Demand record);

    /**
     * 查询需求处理
     * @param demandVo
     * @return
     */
    List<Demand> queryAllDemand(DemandVo demandVo);
}