package com.mengan.bus.mapper;

import com.mengan.bus.domain.Contract;
import com.mengan.bus.vo.ContractVo;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    /**
     * 查询合同
     * @param contractVo
     * @return
     */
    List<ContractVo> queryAllContract(ContractVo contractVo);

    /**
     * 拆寻联系人是否有合同
     * @param id
     * @return
     */
    Integer queryContractById(Integer id);
}