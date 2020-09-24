package com.vinci.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Vinci_Ma
 * @Oescription: 将数据转换成bootstrap-table识别的格式
 * @Date Created in 2020-08-21-18:12
 * @Modified By:
 */
public class ResultData<T> {
    //每次查询的数据集合
    private List<T> rows = new ArrayList<>();
    //总数量
    private int total;

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
