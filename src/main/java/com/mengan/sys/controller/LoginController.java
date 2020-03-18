package com.mengan.sys.controller;

import com.mengan.sys.constant.SysConstant;
import com.mengan.sys.domain.User;
import com.mengan.sys.service.UserService;
import com.mengan.sys.utils.WebUtils;
import com.mengan.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 登录
     */
    @RequestMapping("login")
    public String login(UserVo userVo , Model model){
        User user = userService.login(userVo);
        if (user==null){
            model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
            return "system/main/login";
        }
        if(user.getAvailable()==0){
            model.addAttribute("error", SysConstant.USER_LOGIN_AVAILABLE_ERROR);
            return "system/main/login";
        }
        WebUtils.getHttpSession().setAttribute("user",user);
        return "system/main/index";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("toLoginOut")
    public String toLoginOut(){
        //清除session
        WebUtils.getHttpSession().invalidate();
        return "system/main/login";
    }
}
