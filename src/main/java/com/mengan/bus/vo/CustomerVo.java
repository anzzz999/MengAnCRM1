package com.mengan.bus.vo;

import com.mengan.bus.domain.Customer;

import java.util.Arrays;

public class CustomerVo extends Customer {
    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

    private String userRealname;

    //接收多个id
    private Integer[] ids;

    @Override
    public String toString() {
        return "CustomerVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", userRealname='" + userRealname + '\'' +
                ", ids=" + Arrays.toString(ids) +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
