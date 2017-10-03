package com.developer.mosoti.wishlist;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by mosoti on 10/2/17.
 */
@Parcel
public class Item {
    private Integer itemid;
    private double price;
    private String name;
    private String image;

    private String stock;
    private String availability;
    private String url;
    private ArrayList<String>images=new ArrayList<>();
    private String description;
   // private String pushId;
   // String index;

    public Item(){}


    public Item(Integer itemid,double price, String name,String image,String stock,String availability,String url,ArrayList<String> images,String description){

        this.itemid=itemid;
        this.price=price;
        this.name=name;
        this.image=image;
        this.stock=stock;
        this.availability=availability;
        this.url=url;
        this.images=images;
        this.description=description;
       // this.index="not_assigned";

    }

    public Integer getItemId(){
        return itemid;
    }
    public double getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }
    public String getStock(){
        return stock;
    }
    public String getAvailability(){
        return availability;
    }
    public String getName(){
        return name;
    }
    public String getUrl(){
        return url;
    }
    public ArrayList<String> getImages() {
        return images;
    }
    public String getDescription(){
        return description;
    }
    //public String getPushId() {
    //    return pushId;
   // }

//    public void setPushId(String pushId) {
//        this.pushId = pushId;
//    }
//    public String getIndex() {
//        return index;
//    }
//
//    public void setIndex(String index) {
//        this.index = index;
//    }

}
