package com.mengan.bus.vo;
import com.mengan.bus.domain.Linkman;


public class LinkmanVo extends Linkman {

    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

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
}
