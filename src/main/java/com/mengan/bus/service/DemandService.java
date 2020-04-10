package com.mengan.bus.service;

import com.mengan.bus.domain.Demand;
import com.mengan.bus.vo.DemandVo;
import com.mengan.sys.domain.DataGridView;


/**
 * 需求处理管理的服务接口
 */
public interface DemandService {
    /**
     * 查询所有需求处理
     * @param demandVo
     * @return
     */
    public DataGridView queryAllDemand(DemandVo demandVo);
    /**
     * 添加需求处理
     */
    public void addDemand(Demand demand);
    /**
     * 修改需求处理
     */
    public void updateDemand(Demand demand);
    /**
     * 根据id删除需求处理
     * @param id
     */
    public void deleteDemand(Integer id);

    /**
     * 批量删除需求处理
     */
    public void deleteBatchDemand(Integer[] ids);

    /**
     * 查询一个需求处理
     * @param id
     * @return
     */
    public Demand queryDemandById(Integer id);

}
