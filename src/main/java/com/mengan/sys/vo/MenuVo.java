package com.mengan.sys.vo;

import com.mengan.sys.domain.Menu;

public class MenuVo extends Menu {

    /**
     * 分页参数 第几页 每页多少行
     */
    private Integer page;
    private Integer limit;

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
