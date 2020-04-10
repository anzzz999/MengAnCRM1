package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.domain.Complaint;
import com.mengan.bus.mapper.ComplaintMapper;
import com.mengan.bus.service.ComplaintService;
import com.mengan.bus.vo.ComplaintVo;
import com.mengan.sys.domain.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;
    @Override
    public DataGridView queryAllComplaint(ComplaintVo complaintVo) {
        Page<Object> page= PageHelper.startPage(complaintVo.getPage(), complaintVo.getLimit());
        List<Complaint> data = this.complaintMapper.queryAllComplaint(complaintVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addComplaint(Complaint complaint) {
        this.complaintMapper.insertSelective(complaint);
    }

    @Override
    public void deleteComplaint(Integer id) {
        this.complaintMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchComplaint(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteComplaint(integer);
        }
    }

    @Override
    public void updateComplaint(Complaint complaint) {
        this.complaintMapper.updateByPrimaryKeySelective(complaint);
    }

    @Override
    public Complaint queryComplaintById(Integer id) {
        return this.complaintMapper.selectByPrimaryKey(id);
    }

}
