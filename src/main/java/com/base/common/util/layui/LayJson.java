package com.base.common.util.layui;

import com.github.pagehelper.PageInfo;
import java.util.List;

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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
