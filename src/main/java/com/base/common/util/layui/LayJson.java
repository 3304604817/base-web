package com.base.common.util.layui;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class LayJson<T> {
    private Integer code;
    private String msg;
    private long count;
    private List<T> data;

    public LayJson(PageInfo<T> pageInfo){
        this.code = 0;
        this.msg = "";
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }

    public LayJson(List<T> list){
        this.code = 0;
        this.msg = "";
        this.count = list.size();
        this.data = list;
    }
}
