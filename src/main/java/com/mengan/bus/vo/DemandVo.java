package com.mengan.bus.vo;

import com.mengan.bus.domain.Demand;

public class DemandVo extends Demand {
    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

    private Integer ids[];


    /**
     * 公司名称
     */
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
