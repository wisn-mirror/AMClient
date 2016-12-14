package com.wisn.pmlib.activity.testjson.bean;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

/**
 * <b>Create Date:</b> 2016/11/22<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description:</b> <br>
 */

public class PromotionDetails extends Model{
    public long promotion_id;
    public String name;
    public String end_date;
    public String start_time;
    public String payment_way;
    public String promotion_type;
    public String start_date;
    public int promotion_price;
    public String end_time;
    public ArrayList<FreeBie> freebie;
    /** 商品促销弹窗图片链接*/
    public String promotional_image_links;
    public PromotionDetails(){

   }


    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.promotion_id);
        dest.writeString(this.name);
        dest.writeString(this.end_date);
        dest.writeString(this.start_time);
        dest.writeString(this.payment_way);
        dest.writeString(this.promotion_type);
        dest.writeString(this.start_date);
        dest.writeInt(this.promotion_price);
        dest.writeString(this.end_time);
        dest.writeTypedList(this.freebie);
        dest.writeString(this.promotional_image_links);
    }




    @Override
    public String toString() {
        return "PromotionDetails{" +
                "name='" + name + '\'' +
                ", end_date='" + end_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", payment_way='" + payment_way + '\'' +
                ", promotion_type='" + promotion_type + '\'' +
                ", start_date='" + start_date + '\'' +
                ", promotion_price=" + promotion_price +
                ", end_time='" + end_time + '\'' +
                ", promotional_image_links='" + promotional_image_links + '\'' +
                "} " + super.toString();
    }

    protected PromotionDetails(Parcel in) {
        super(in);
        this.promotion_id = in.readLong();
        this.name = in.readString();
        this.end_date = in.readString();
        this.start_time = in.readString();
        this.payment_way = in.readString();
        this.promotion_type = in.readString();
        this.start_date = in.readString();
        this.promotion_price = in.readInt();
        this.end_time = in.readString();
        this.freebie = in.createTypedArrayList(FreeBie.CREATOR);
        this.promotional_image_links = in.readString();
    }

    public static final Parcelable.Creator<PromotionDetails>
            CREATOR = new Parcelable.Creator<PromotionDetails>() {
        @Override
        public PromotionDetails createFromParcel(Parcel source) {return new PromotionDetails(source);}

        @Override
        public PromotionDetails[] newArray(int size) {return new PromotionDetails[size];}
    };
}
