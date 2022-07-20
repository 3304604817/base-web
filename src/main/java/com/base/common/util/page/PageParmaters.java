package com.base.common.util.page;

import java.util.Objects;

public class PageParmaters {
    // 默认当前页数第一页（pageHelp是从1开始的）
    private int page = 1;
    // 默认分页大小为10
    private int limit = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
