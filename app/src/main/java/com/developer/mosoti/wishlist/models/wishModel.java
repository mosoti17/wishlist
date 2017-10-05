package com.developer.mosoti.wishlist.models;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by mosoti on 10/4/17.
 */

public class wishModel {
    String name;
    String description;
    private String pushId;
    Object items;
   // ArrayList<Item> Items=new ArrayList<>();

    public wishModel(){}
    public wishModel(String name, String description,String pushId){
        this.name= name;
        this.description=description;
        this.pushId=pushId;
        this.items= "" ;

    }
    public String getname(){
        return name;
    }
    public String getdescription(){
        return description;
    }

    public String getPushId() {
        return pushId;
     }
     public Object setItems(){
        return items;
     }
     public Object getItems(){
         return items;
     }

}
