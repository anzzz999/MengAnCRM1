package com.mengan.bus.mapper;

import com.mengan.bus.domain.Complaint;
import com.mengan.bus.vo.ComplaintVo;

import java.util.List;

public interface ComplaintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);

    /**
     * 查询所有投诉
     * @param complaintVo
     * @return
     */
    List<Complaint> queryAllComplaint(ComplaintVo complaintVo);
}