package com.wisn.pmlib.activity.testjson.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * <b>Create Date:</b> 8/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Product extends Model implements Cloneable {
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {return new Product(source);}

        @Override
        public Product[] newArray(int size) {return new Product[size];}
    };
    /** 商品ID */
    public int id;
    /** 货道编号 */
    public String stack_no;
    /** 料道编号 */
//    public String channel_no;
    /** 商品价格, 以分为单位 */
    public int price;
    /** 商品图片链接 */
    public String image_url;
    /** 商品名称 */
    public String name;
    /** 商品库存 */
    public int stock;
    /** 商品序列号 */
    public String seq_no;
    /** 促销类别 */
//    public String sales_promotion;
    /** 商品类型 */
    public String product_type;
    /** 商品促销信息 */
    public PromotionDetails promotion_details;

    public String net_weight;



    /** 商品详情图片链接*/
    public String product_details_image_url;

    public Product() {}

    protected Product(Parcel in) {
        super(in);
        this.stack_no = in.readString();
//        this.channel_no = in.readString();
        this.id = in.readInt();
        this.price = in.readInt();
        this.image_url = in.readString();
        this.name = in.readString();
        this.stock = in.readInt();
        this.seq_no = in.readString();
//        this.sales_promotion=in.readString();
        this.product_type = in.readString();
        this.product_details_image_url = in.readString();
        this.net_weight = in.readString();
        this.promotion_details = in.readParcelable(PromotionDetails.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stack_no);
//        dest.writeString(this.channel_no);
        dest.writeInt(this.id);
        dest.writeInt(this.price);
        dest.writeString(this.image_url);
        dest.writeString(this.name);
        dest.writeInt(this.stock);
        dest.writeString(this.seq_no);
//        dest.writeString(this.sales_promotion);
        dest.writeString(this.product_type);
        dest.writeString(this.product_details_image_url);
        dest.writeString(this.net_weight);
        dest.writeParcelable(this.promotion_details,0);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", stack_no='" + stack_no + '\'' +
                ", price=" + price +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", seq_no='" + seq_no + '\'' +
                ", product_type='" + product_type + '\'' +
                ", promotion_details=" + promotion_details +
                ", net_weight='" + net_weight + '\'' +
                ", product_details_image_url='" + product_details_image_url + '\'' +
                "} " + super.toString();
    }

    /**
     * 获取货道ID
     *
     * @return
     */
    public int getStackNoInt() {
        return Integer.valueOf(stack_no);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
