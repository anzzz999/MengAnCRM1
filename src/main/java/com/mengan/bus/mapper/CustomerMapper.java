package com.mengan.bus.mapper;

import com.mengan.bus.domain.Customer;
import com.mengan.bus.vo.CustomerVo;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    List<CustomerVo> queryAllCustomerVo(CustomerVo customerVo);

    List<Customer> queryAllCustomerForList();
}