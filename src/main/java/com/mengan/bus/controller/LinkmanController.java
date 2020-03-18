package com.mengan.bus.controller;

import com.mengan.bus.domain.Customer;
import com.mengan.bus.service.CustomerService;
import com.mengan.bus.service.LinkmanService;
import com.mengan.bus.vo.CustomerVo;
import com.mengan.bus.vo.LinkmanVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("linkman")
public class LinkmanController {

    @Autowired
    LinkmanService linkmanService;
    @Autowired
    CustomerService customerService;

    /**
     * 加载联系人列表返回DataGridView
     */
    @RequestMapping("loadAllLinkman")
    public DataGridView loadAllLinkman(LinkmanVo linkmanVo) {
        return this.linkmanService.queryAllLinkman(linkmanVo);
    }

    /**
     * 添加联系人
     */
    @RequestMapping("addLinkman")
    public ResultObj addLinkman(LinkmanVo linkmanVo){
        try{
            linkmanService.addLinkman(linkmanVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除联系人
     */
    @RequestMapping("deleteLinkman")
    public ResultObj deleteLinkman(LinkmanVo linkmanVo){
        try{
            linkmanService.deleteLinkman(linkmanVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改联系人信息
     * @return
     */
    @RequestMapping("updateLinkman")
    public ResultObj updateLinkman(LinkmanVo linkmanVo){
        try{
            linkmanService.updateLinkman(linkmanVo);

            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    @RequestMapping("queryCustomerForFrom")
    public List<Customer> queryCustomerForFrom(){
        List<Customer> customerList= customerService.queryAllCustomerForList();
        return customerList;
    }
}
