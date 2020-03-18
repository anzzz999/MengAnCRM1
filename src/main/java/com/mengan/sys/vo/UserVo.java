package com.mengan.sys.vo;

import com.mengan.sys.domain.User;

public class UserVo extends User {
    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

    //接收多个id  如角色id
    private Integer[] ids;

    private String oldPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
