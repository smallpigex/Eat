package com.smallpigex.eat.com.whatwouldyoulike.model;
import  java.util.List;
/**
 * Created by smallpigex on 2015/7/18.
 */
public class Location {
    private String locationName;
    private List<Restaurant> restaurants;


    @Override
    public String toString() {
        return locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
