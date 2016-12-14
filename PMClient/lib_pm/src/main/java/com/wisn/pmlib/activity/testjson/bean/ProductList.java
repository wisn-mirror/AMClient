package com.wisn.pmlib.activity.testjson.bean;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

/**
 * <b>Create Date:</b> 8/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ProductList extends OdooList<Product> {


    public ArrayList<String> product_type_list;

    public ProductList() {

    }

    @Override
    public String toString() {
        return "ProductList{" +
                "product_type_list=" + product_type_list +
                "} " + super.toString();
    }

    protected ProductList(Parcel in) {
        super(in);
        this.records = in.createTypedArrayList(Product.CREATOR);
        this.product_type_list = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringList(this.product_type_list);
    }

    public static final Parcelable.Creator<ProductList> CREATOR = new Parcelable.Creator<ProductList>() {
        @Override
        public ProductList createFromParcel(Parcel source) {return new ProductList(source);}

        @Override
        public ProductList[] newArray(int size) {return new ProductList[size];}
    };
}
