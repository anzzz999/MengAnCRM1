package com.mengan.bus.controller;

import com.mengan.bus.service.ContractService;
import com.mengan.bus.service.CustomerService;
import com.mengan.bus.service.LinkmanService;
import com.mengan.bus.vo.CustomerVo;
import com.mengan.sys.domain.DataGridView;

import com.mengan.sys.domain.User;
import com.mengan.sys.utils.ResultObj;

import com.mengan.sys.utils.WebUtils;
import com.mengan.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    LinkmanService linkmanService;
    @Autowired
    ContractService contractService;
    /**
     * 加载客户列表返回DataGridView
     */
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加客户
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
        try{
            customerVo.setCreatetime(new Date());
            User user=(User)WebUtils.getHttpSession().getAttribute("user");
            customerVo.setUid(user.getUserid());
            customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除客户
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo){
        try{
            //判断是否存在联系人
           Integer number = linkmanService.queryLinkmanByCid(customerVo.getId());
           //存在联系人
           if (number!=0){
               return ResultObj.HAVING_LINKMAN;
           }
            //判断是否存在相关合同
            Integer number2 = contractService.queryContractById(customerVo.getId());
            //存在联系人
            if (number2!=0){
                return ResultObj.HAVING_CONTRACT;
            }
            customerService.deleteCustomer(customerVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

/*    *//**
     * 批量删除客户
     *//*
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(CustomerVo customerVo){
        try{
            customerService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }*/

    /**
     * 修改客户信息
     * @return
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo){
        try{

            customerService.updateCustomer(customerVo);

            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
