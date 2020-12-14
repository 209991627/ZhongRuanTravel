package com.mxt.pojo;

public class Pages {
    //总记录
    private Integer count;
    //总页数
    private Integer pagesNumber;
    //数据
    private Object data;
    //当前页
    private Integer currentPage;
    //

    public Integer getCount() {
        return count;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(Integer pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
