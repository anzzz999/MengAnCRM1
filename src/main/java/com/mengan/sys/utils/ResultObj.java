package com.mengan.sys.utils;

import com.mengan.sys.constant.SysConstant;

public class ResultObj {



    private Integer code=0;
    private String msg;

    /**
     * 存在合同,返回CODE_ERROR
     */
    public static final ResultObj HAVING_CONTRACT = new ResultObj(SysConstant.CODE_ERROR,SysConstant.HAVING_CONTRACT);
    /**
     * 存在联系人,返回error_msg
     */
    public static final ResultObj HAVING_LINKMAN = new ResultObj(SysConstant.CODE_ERROR,SysConstant.HAVING_LINKMAN_MSG);
    /**
     * 修改密码，旧密码错误,返回error_msg
     */
    public static final ResultObj OLDPWD_IS_ERROR =new ResultObj(SysConstant.CODE_ERROR,SysConstant.OLDPWD_IS_ERROR) ;
    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR=new ResultObj(SysConstant.CODE_ERROR, SysConstant.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstant.CODE_ERROR, SysConstant.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstant.CODE_ERROR, SysConstant.DELETE_ERROR);

    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR=new ResultObj(SysConstant.CODE_ERROR, SysConstant.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR=new ResultObj(SysConstant.CODE_ERROR, SysConstant.DISPATCH_ERROR);
    /**
     * 状态码0
     */
    public static final ResultObj STATUS_TRUE=new ResultObj(SysConstant.CODE_SUCCESS);
    /**
     * 状态码-1
     */
    public static final ResultObj STATUS_FALSE=new ResultObj(SysConstant.CODE_ERROR);

    public ResultObj(Integer code) {
        this.code = code;
    }

    private ResultObj(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }




}
