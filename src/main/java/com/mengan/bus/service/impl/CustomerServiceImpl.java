package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.domain.Customer;
import com.mengan.bus.mapper.CustomerMapper;
import com.mengan.bus.service.CustomerService;
import com.mengan.bus.vo.CustomerVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        //使用分页助手
        Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        List<CustomerVo> data = customerMapper.queryAllCustomerVo(customerVo);
/*        System.out.println("-------"+data);*/
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCustomer(CustomerVo customerVo) {
        customerMapper.insertSelective(customerVo);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateCustomer(CustomerVo customerVo) {
        //删除对应的联系人


        //删除对应的客户
        customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    @Override
    public List<Customer> queryAllCustomerForList() {
        return customerMapper.queryAllCustomerForList();
    }
}
