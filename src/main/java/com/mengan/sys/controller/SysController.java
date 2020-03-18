package com.mengan.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单转跳管理
 */
@Controller
@RequestMapping("sys")
public class SysController {
    /**
     * 跳转到菜单管理页面
     * @return
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }

    /**
     * 菜单管理左页面
     * @return
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }
    /**
     * 菜单管理右页面
     * @return
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }
    /**
     * 跳转角色管理页面
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转用户管理页面
     */
    @RequestMapping("toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }

    /**
     * 跳转文章管理页面
     */
    @RequestMapping("toNewsManager")
    public String toNewsManager() {
        return "system/news/newsManager";
    }

    /**
     * 跳转修改密码页面
     */
    @RequestMapping("toChangePwd")
    public String toChangePwd() {
        return "system/user/changePwd";
    }
    /**
     * 跳转个人信息页面
     */
    @RequestMapping("toUserInfo")
    public String toUserInfo() {
        return "system/user/userInfo";
    }
}
