package com.springboot.core.page;

/**
 * 分页参数类(Bootstrap table)
 * Created by admin on 2018/1/16.
 */
public class PageBT {
    //每页显示个数
    private int limit;
    //偏移量
    private int offset;
    //排序的方式
    private String order;

    public PageBT(){
        super();
    }

    public PageBT(int limit, int offset){
        super();
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageSize(){
        return this.limit;
    }

    public int getPageNumber(){
        return this.offset / this.limit + 1;
    }

    @Override
    public String toString() {
        return "PageBT{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", order='" + order + '\'' +
                '}';
    }
}
