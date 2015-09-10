package com.smallpigex.eat.com.eating.util;

import com.smallpigex.eat.com.whatwouldyoulike.model.Restaurant;
import java.util.Map;

/**
 * Created by smallpigex on 2015/9/10.
 */
public class Convertor {
    public static Restaurant convertMapToRestaurantObject(Map<String, Object> object) {
        String name = object.get(Restaurant.NAME).toString();
        String region = object.get(Restaurant.REGION).toString();
        String location = object.get(Restaurant.Location).toString();
        String comment = object.get(Restaurant.COMMENT).toString();
        String path = object.get(Restaurant.PHOTO_PATH).toString();
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(name);
        restaurant.setRegion(region);
        restaurant.setRestaurantLocation(location);
        restaurant.setRestaurantComment(comment);
        restaurant.setRestaurantPhotoPath(path);
        return restaurant;
    }
}
