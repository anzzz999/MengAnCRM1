package com.mengan.bus.controller;

import com.mengan.bus.domain.Contract;

import com.mengan.bus.service.ContractService;
import com.mengan.bus.vo.ContractVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.utils.AppFileUtils;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    ContractService contractService;


    /**
     * 加载用户记录列表返回DataGridView
     */
    @RequestMapping("loadAllContract")
    public DataGridView loadAllContract(ContractVo contractVo) {

        return this.contractService.queryAllContract(contractVo);
    }

    /**
     * 添加用户记录
     */
    @RequestMapping("addContract")
    public ResultObj addContract(Contract contract) {
        try {
            contract.setCreatetime(new Date());
            this.contractService.addContract(contract);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改用户记录
     */
    @RequestMapping("updateContract")
    public ResultObj updateContract(Contract contract) {
        try {
            this.contractService.updateContract(contract);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户记录
     */
    @RequestMapping("deleteContract")
    public ResultObj deleteContract(Contract contract) {
        try {
            //删除合同记录
            this.contractService.deleteContract(contract.getId());

            //删除对应的文件
            AppFileUtils.deleteFileUsePath(contract.getUrl());

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
