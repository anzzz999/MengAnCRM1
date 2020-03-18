package com.mengan.bus.service;

import com.mengan.bus.domain.Customer;
import com.mengan.bus.vo.CustomerVo;
import com.mengan.sys.domain.DataGridView;

import java.util.List;

public interface CustomerService {
    DataGridView queryAllCustomer(CustomerVo customerVo);

    void addCustomer(CustomerVo customerVo);

    void deleteCustomer(Integer id);

    void updateCustomer(CustomerVo customerVo);

    List<Customer> queryAllCustomerForList();
}
