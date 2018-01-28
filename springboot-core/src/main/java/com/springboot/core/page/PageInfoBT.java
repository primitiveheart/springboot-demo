package com.springboot.core.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * 分页结果的封装
 * Created by admin on 2018/1/16.
 */
public class PageInfoBT<T> {
    //结果集
    private List<T> rows;

    //总数
    private int total;

    public PageInfoBT(Page<T> page){
        this.rows = page.getRecords();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
