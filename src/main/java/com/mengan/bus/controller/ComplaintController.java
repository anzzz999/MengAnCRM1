package com.mengan.bus.controller;

import com.mengan.bus.domain.Complaint;
import com.mengan.bus.service.ComplaintService;
import com.mengan.bus.vo.ComplaintVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    /**
     * 加载投诉处理列表返回DataGridView
     */
    @RequestMapping("loadAllComplaint")
    public DataGridView loadAllComplaint(ComplaintVo complaintVo) {
        return this.complaintService.queryAllComplaint(complaintVo);
    }

    /**
     * 添加投诉处理
     */
    @RequestMapping("addComplaint")
    public ResultObj addComplaint(Complaint complaint) {
        try {
            complaint.setCreatetime(new Date());
            //将endtime设置为null
            if ("".equals(complaint.getEndtime())){
                complaint.setEndtime(null);
            }
            this.complaintService.addComplaint(complaint);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改投诉处理
     */
    @RequestMapping("updateComplaint")
    public ResultObj updateComplaint(Complaint complaint) {
        try {
            //将endtime设置为null
            if ("".equals(complaint.getEndtime())){
                complaint.setEndtime(null);
            }
            this.complaintService.updateComplaint(complaint);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除投诉处理
     */
    @RequestMapping("deleteComplaint")
    public ResultObj deleteComplaint(Complaint complaint) {
        try {
            this.complaintService.deleteComplaint(complaint.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除投诉处理
     */
    @RequestMapping("deleteBatchComplaint")
    public ResultObj deleteBatchComplaint(ComplaintVo complaintVo) {
        try {
            this.complaintService.deleteBatchComplaint(complaintVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询投诉处理
     */
    @RequestMapping("loadComplaintById")
    public Complaint loadComplaintById(Integer id) {
        return this.complaintService.queryComplaintById(id);
    }
}
