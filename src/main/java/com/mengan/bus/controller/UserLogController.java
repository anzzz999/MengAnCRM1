package com.mengan.bus.controller;

import com.mengan.bus.domain.UserLog;
import com.mengan.bus.service.UserLogService;
import com.mengan.bus.vo.UserLogVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("userlog")
public class UserLogController {

    @Autowired
    UserLogService userLogService;

    /**
     * 加载用户记录列表返回DataGridView
     */
    @RequestMapping("loadAllUserLog")
    public DataGridView loadAllUserLog(UserLogVo userLogVo) {
        User user=(User) WebUtils.getHttpSession().getAttribute("user");
        userLogVo.setUid(user.getUserid());
        return this.userLogService.queryAllUserLog(userLogVo);
    }

    /**
     * 添加用户记录
     */
    @RequestMapping("addUserLog")
    public ResultObj addUserLog(UserLog userLog) {
        try {
            userLog.setCreateTime(new Date());
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            userLog.setUid(user.getUserid());
            this.userLogService.addUserLog(userLog);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改用户记录
     */
    @RequestMapping("updateUserLog")
    public ResultObj updateUserLog(UserLog userLog) {
        try {
            this.userLogService.updateUserLog(userLog);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户记录
     */
    @RequestMapping("deleteUserLog")
    public ResultObj deleteUserLog(UserLog userLog) {
        try {
            this.userLogService.deleteUserLog(userLog.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户记录
     */
    @RequestMapping("deleteBatchUserLog")
    public ResultObj deleteBatchUserLog(UserLogVo userLogVo) {
        try {
            this.userLogService.deleteBatchUserLog(userLogVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询用户记录
     */
    @RequestMapping("loadUserLogById")
    public UserLog loadUserLogById(Integer id) {
        return this.userLogService.queryUserLogById(id);
    }
}
