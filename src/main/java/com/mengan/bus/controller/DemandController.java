package com.mengan.bus.controller;

import com.mengan.bus.domain.Demand;
import com.mengan.bus.service.DemandService;
import com.mengan.bus.vo.DemandVo;
import com.mengan.sys.domain.DataGridView;

import com.mengan.sys.domain.User;

import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("demand")
public class DemandController {
    @Autowired
    private DemandService demandService;

    /**
     * 加载需求处理列表返回DataGridView
     */
    @RequestMapping("loadAllDemand")
    public DataGridView loadAllDemand(DemandVo demandVo) {
        return this.demandService.queryAllDemand(demandVo);
    }

    /**
     * 添加需求处理
     */
    @RequestMapping("addDemand")
    public ResultObj addDemand(Demand demand) {
        try {
            demand.setCreatetime(new Date());
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            demand.setOpername(user.getRealname());
            //将endtime设置为null
            if ("".equals(demand.getEndtime())){
                demand.setEndtime(null);
            }
            this.demandService.addDemand(demand);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改需求处理
     */
    @RequestMapping("updateDemand")
    public ResultObj updateDemand(Demand demand) {
        try {
            //将endtime设置为null
            if ("".equals(demand.getEndtime())){
                demand.setEndtime(null);
            }
            this.demandService.updateDemand(demand);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除需求处理
     */
    @RequestMapping("deleteDemand")
    public ResultObj deleteDemand(Demand demand) {
        try {
            this.demandService.deleteDemand(demand.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除需求处理
     */
    @RequestMapping("deleteBatchDemand")
    public ResultObj deleteBatchDemand(DemandVo demandVo) {
        try {
            this.demandService.deleteBatchDemand(demandVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询需求处理
     */
    @RequestMapping("loadDemandById")
    public Demand loadDemandById(Integer id) {
        return this.demandService.queryDemandById(id);
    }
}
