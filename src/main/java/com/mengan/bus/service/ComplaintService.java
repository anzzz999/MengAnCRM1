package com.mengan.bus.service;

import com.mengan.bus.domain.Complaint;
import com.mengan.bus.vo.ComplaintVo;
import com.mengan.sys.domain.DataGridView;


/**
 * 投诉处理管理的服务接口
 */
public interface ComplaintService {
    /**
     * 查询所有投诉处理
     * @param complaintVo
     * @return
     */
    public DataGridView queryAllComplaint(ComplaintVo complaintVo);
    /**
     * 添加投诉处理
     */
    public void addComplaint(Complaint complaint);
    /**
     * 修改投诉处理
     */
    public void updateComplaint(Complaint complaint);
    /**
     * 根据id删除投诉处理
     * @param id
     */
    public void deleteComplaint(Integer id);

    /**
     * 批量删除投诉处理
     */
    public void deleteBatchComplaint(Integer[] ids);

    /**
     * 查询一个投诉处理
     * @param id
     * @return
     */
    public Complaint queryComplaintById(Integer id);

}
