package com.mengan.sys.constant;

import java.sql.ResultSet;

/**
 * 常量接口
 */
public interface SysConstant {

	String USER_LOGIN_ERROR_MSG = "用户名或密码不正确";
    String USER_LOGIN_AVAILABLE_ERROR = "用户账号不可用，请联系管理员";

    /**
     * 可用状态
     */
    Integer AVAILABLE_TRUE = 1;
    Integer AVAILABLE_FALSE = 0;

    /**
     * 用户类型 超级用户  普通用户
     */
    Integer USER_TYPE_SUPER = 1;
    Integer USER_TYPE_NORMAL= 2;

    /**
     * 是否展开
     */
    Integer SPREAD_TRUE = 1;
    Integer SPREAD_FALSE = 0;


    /**
     * 操作状态
     */
    String ADD_SUCCESS="添加成功";
    String ADD_ERROR="添加失败";

    String UPDATE_SUCCESS="更新成功";
    String UPDATE_ERROR="更新失败";

    String DELETE_SUCCESS="删除成功";
    String DELETE_ERROR="删除失败";

    String RESET_SUCCESS="重置成功";
    String RESET_ERROR="重置失败";

    String DISPATCH_SUCCESS="分配成功";
    String DISPATCH_ERROR="分配失败";

    Integer CODE_SUCCESS=0; //操作成功
    Integer CODE_ERROR=-1;//失败

    //初始化密码
    String USER_DEFAnULT_PWD = "123456";

    //父菜单为1
    Integer PID_IS_ONE = 1;


    String OLDPWD_IS_ERROR = "密码错误，请重新输入！";

    //没有联系人
    Integer NO_LINKMAN =0;

    String HAVING_LINKMAN_MSG = "请先删除公司对应联系人！";
    String HAVING_CONTRACT = "请先删除公司对应合同！";
}
