package com.mengan.bus.vo;

import com.mengan.bus.domain.Contract;

public class ContractVo extends Contract {
    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

    private String cname;


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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
