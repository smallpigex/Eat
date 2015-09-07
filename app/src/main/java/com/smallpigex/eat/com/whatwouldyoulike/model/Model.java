package com.smallpigex.eat.com.whatwouldyoulike.model;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smallpigex.eat.com.eating.util.Consts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by smallpigex on 2015/7/18.
 */
public class Model {
    private final String IMAGE_FORMAT = ".jpg";
    private final String IMAGE_FOLDER = "EatFor";
    private String imageFolderPath = "";
    private SharedPreferences preferences = null;
    private File storageDir = null;
    private SQLiteDatabase db;

    public Model(SharedPreferences preferences) {
        this.preferences = preferences;
        initAppInformation();
    }

    public Model() {

    }

    public void initAppInformation() {
        storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        imageFolderPath = storageDir.getAbsolutePath() + "/" + IMAGE_FOLDER;

        createPhotoFolder();
    }

    private void createPhotoFolder() {
        File imageFolder = new File(imageFolderPath);
        if(!imageFolder.exists()) {
            imageFolder.mkdir();
            Log.i(Consts.LOG_TAG, "It create a folder of image and path is " + imageFolderPath);
        } else {
            Log.i(Consts.LOG_TAG, "The folder of image is existing and path is " + imageFolderPath);
        }

    }

    public void saveLocation(String location) {
        if (location.isEmpty()) {
            return;
        }

        if (preferences.getString(location, "") == null || preferences.getString(location, "").isEmpty()) {
            saveData(location, "");
        }
    }

    public List<Location> getAllLocation() {
        List<Location> locations = new ArrayList<Location>();
        Map<String, ?> allLocation;
        allLocation = preferences.getAll();
        for (Map.Entry<String, ?> entry : allLocation.entrySet()) {
            Location location = new Location();
            location.setLocationName(entry.getKey().toString());
            Log.i("Location ", entry.getKey().toString());
            locations.add(location);
        }
        return locations;
    }

    public List<Map<String, Object>> getRestaurantByLocation(String location) {
        List<Restaurant> restaurants = null;
        String restaurantJson = preferences.getString(location, "");
        restaurants = convertJsonToRestaurantList(restaurantJson);
        List<Map<String, Object>> restaurantInfoList = convertRestaurantInfoToAdapterFormat(restaurants);
        return restaurantInfoList;
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File imageStorageDir = new File(imageFolderPath);
        Log.i("myApp", "The image path is " + imageFolderPath);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                IMAGE_FORMAT,         /* suffix */
                imageStorageDir      /* directory */
        );

        return image;
    }

    public void saveRestaurant(Restaurant restaurant) {
        List<Restaurant> restaurants = null;
        String restaurantsJson = "";
        restaurantsJson = preferences.getString(restaurant.getLocation(), "");
        restaurants = convertJsonToRestaurantList(restaurantsJson);
        if (restaurants == null) {
            restaurants = new ArrayList<Restaurant>();
        }

        restaurants.add(restaurant);
        restaurantsJson = convertObjectToJson(restaurants);
        saveData(restaurant.getLocation(), restaurantsJson);
    }

    private List<Map<String, Object>> convertRestaurantInfoToAdapterFormat(List<Restaurant> restaurants) {
        if(restaurants == null) {
            return null;
        }

        List<Map<String, Object>> adapter = new ArrayList<Map<String, Object>>();
        for (Restaurant restaurant : restaurants) {
            if(!restaurant.getRestaurantPhotoPath().isEmpty()) {
                Map<String, Object> tmpRestaurantInfo = new TreeMap<String, Object>();
                tmpRestaurantInfo.put(Restaurant.NAME, restaurant.getRestaurantName());
                tmpRestaurantInfo.put(Restaurant.PHOTO_PATH, restaurant.getRestaurantPhotoPath());
                adapter.add(tmpRestaurantInfo);
            }
        }

        return adapter;
    }

    private List<Restaurant> convertJsonToRestaurantList(String json) {
        List<Restaurant> restaurants = null;
        Gson gson = new Gson();
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<Restaurant>>() {
            }.getType();
            restaurants = gson.fromJson(json, type);
        }
        return restaurants;
    }

    private String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private void saveData(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveRestaurantToDB(Restaurant restaurant) {
        try {
            FileInputStream fileInputStream = new FileInputStream(restaurant.getRestaurantPhotoPath());
            byte[] image = new byte[fileInputStream.available()];

        } catch (FileNotFoundException e) {
            Log.d(Consts.EXCEPTION_TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(Consts.EXCEPTION_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    //隨機挑選指定區域的餐廳
    public Restaurant putSlotMachineButton(String region) {
        List<Restaurant> restaurants = null;
        String restaurantJson = preferences.getString(region, "");
        restaurants = convertJsonToRestaurantList(restaurantJson);
        SlotMachine sm = new SlotMachine();

        int number = sm.getRandomNumber(restaurants.size());

        Restaurant restaurantInfo = restaurants.get(number);
        Log.d(Consts.LOG_DEBUG_TAG, "The restaurant object is " + restaurantInfo);
        Log.d(Consts.LOG_DEBUG_TAG, "The number is " + number);
        return restaurantInfo;
    }
}
