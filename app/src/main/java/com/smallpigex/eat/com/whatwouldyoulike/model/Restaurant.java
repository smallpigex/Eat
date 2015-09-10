package com.smallpigex.eat.com.whatwouldyoulike.model;

import java.io.Serializable;

/**
 * Created by smallpigex on 2015/7/26.
 */
public class Restaurant implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;

    public static final String REGION = "Region";
    public static final String NAME = "Name";
    public static final String Location = "Location";
    public static final String COMMENT = "Comment";
    public static final String PHOTO_PATH = "PhotoPath";

    private String region = "";
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
