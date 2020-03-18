package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.domain.Contract;
import com.mengan.bus.domain.Contract;
import com.mengan.bus.mapper.ContractMapper;
import com.mengan.bus.service.ContractService;
import com.mengan.bus.vo.ContractVo;
import com.mengan.sys.domain.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractMapper contractMapper;
    
    @Override
    public DataGridView queryAllContract(ContractVo contractVo) {
        Page<Object> page= PageHelper.startPage(contractVo.getPage(), contractVo.getLimit());
        List<ContractVo> data = this.contractMapper.queryAllContract(contractVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addContract(Contract contract) {
        contractMapper.insertSelective(contract);
    }

    @Override
    public void updateContract(Contract contract) {
        contractMapper.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void deleteContract(Integer id) {
        contractMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer queryContractById(Integer id) {
        return  contractMapper.queryContractById(id);
    }
}
