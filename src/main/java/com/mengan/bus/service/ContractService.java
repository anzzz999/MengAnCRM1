package com.mengan.bus.service;

import com.mengan.bus.domain.Contract;
import com.mengan.bus.vo.ContractVo;
import com.mengan.sys.domain.DataGridView;

public interface ContractService {
    DataGridView queryAllContract(ContractVo contractVo);

    void addContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(Integer id);

    Integer queryContractById(Integer id);
}
