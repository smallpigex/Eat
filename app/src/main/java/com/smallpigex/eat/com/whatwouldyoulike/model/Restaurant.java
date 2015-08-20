package com.smallpigex.eat.com.whatwouldyoulike.model;

/**
 * Created by smallpigex on 2015/7/26.
 */
public class Restaurant {
    public static final String LOCATION = "location";
    public static final String NAME = "RestaurantName";
    public static final String ADDRESS = "RestaurantAddress";
    public static final String COMMENT = "RestaurantComment";
    public static final String PHOTO_PATH = "PhotoPath";

    private String location = "";
    private String restaurantName = "";
    private String restaurantAddress = "";
    private String restaurantComment = "";
    private String restaurantPhotoPath = "";

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
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
