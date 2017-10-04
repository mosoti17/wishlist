package com.developer.mosoti.wishlist.models;

import java.security.PublicKey;

/**
 * Created by mosoti on 10/4/17.
 */

public class wishModel {
    String name;
    String description;

    public wishModel(){}
    public wishModel(String name, String description){
        this.name= name;
        this.description=description;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

}
