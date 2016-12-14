package com.wisn.pmlib.activity.testjson.bean;


/**
 * <b>Project:</b> project_vmc<br>
 * <b>Create Date:</b> 8/30/16<br>
 * <b>Author:</b> Peiweiwei<br>
 * <b>Description:</b> <br>
 */
public class ProductStock {
    //料道号
    public int roadId;
    //库存
    public int stock;

    public ProductStock(int roadId,int stock) {
        this.stock = stock;
        this.roadId = roadId;
    }
}
