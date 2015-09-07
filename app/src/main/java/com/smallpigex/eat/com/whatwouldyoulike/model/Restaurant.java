package com.smallpigex.eat.com.whatwouldyoulike.model;

import java.io.Serializable;

/**
 * Created by smallpigex on 2015/7/26.
 */
public class Restaurant implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;

    public static final String LOCATION = "location";
    public static final String NAME = "RestaurantName";
    public static final String ADDRESS = "RestaurantAddress";
    public static final String COMMENT = "RestaurantComment";
    public static final String PHOTO_PATH = "PhotoPath";

    private String location = "";
    private String restaurantName = "";
    private String restaurantLocation = "";
    private String restaurantComment = "";
    private String restaurantPhotoPath = "";

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantComment() {
        return restaurantComment;
    }

    public void setRestaurantComment(String restaurantComment) {
        this.restaurantComment = restaurantComment;
    }

    public String getRestaurantPhotoPath() {
        return restaurantPhotoPath;
    }

    public void setRestaurantPhotoPath(String restaurantPhotoPath) {
        this.restaurantPhotoPath = restaurantPhotoPath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
