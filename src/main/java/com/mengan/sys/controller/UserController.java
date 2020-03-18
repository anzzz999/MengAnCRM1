package com.mengan.sys.controller;

import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.service.UserService;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import com.mengan.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 加载用户列表返回DataGridView
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return this.userService.queryAllUser(userVo);
    }


    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
      try{
          userService.addUser(userVo);
          return ResultObj.ADD_SUCCESS;
      }catch (Exception e){
          e.printStackTrace();
          return ResultObj.ADD_ERROR;
      }
    }
    /**
     * 删除用户
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo){
        try{
            userService.deleteUser(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo){
        try{
            userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改用户信息
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public  ResultObj updateUser(UserVo userVo){
        try{
            userService.updateUser(userVo);

            //当用户修改自己信息的时候
            User user =(User)WebUtils.getHttpSession().getAttribute("user");
            if ( user.getUserid().equals(userVo.getUserid())){
                //将Session中用户信息更新
                WebUtils.getHttpSession().setAttribute("user",userVo);
            }

            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 修改用户密码
     * @param userVo
     * @return
     */
    @RequestMapping("updateUserPwd")
    public  ResultObj updateUserPwd(UserVo userVo){
        try{

            User user=userService.queryUserWithOldPwd(userVo);
            if (user==null){
                return ResultObj.OLDPWD_IS_ERROR;
            }
            userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 重置用户密码
     * @param userVo
     * @return
     */
    @RequestMapping("resetUserPwd")
    public  ResultObj resetUserPwd(UserVo userVo){
        try{
            userService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 初始化用户角色菜单,加载用户的角色
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo){
        return userService.queryUserRole(userVo.getUserid());
    }

    /**
     * 保存用户角色关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){

        try{
            userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
