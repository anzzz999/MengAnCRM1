package com.mengan.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 业务管理的控制器
 */
@Controller
@RequestMapping("bus")
public class BusController {
    /**
     * 跳转到客户管理
     *
     * @return
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager() {
        return "business/customer/customerManager";
    }

        /**
         * 跳转到联系人管理
         *
         * @return
         */
        @RequestMapping("toLinkmanManager")
        public String toLinkmanManager() {
            return "business/linkman/linkmanManager";
        }

    /**
     * 跳转到联系人管理
     *
     * @return
     */
    @RequestMapping("toUserLogManager")
    public String toUserLogManager() {
        return "business/userlog/userLogManager";
    }

    /**
     * 跳转到合同管理
     *
     * @return
     */
    @RequestMapping("toContractManager")
    public String toContractManager() {
        return "business/contract/contractManager";
    }
}
