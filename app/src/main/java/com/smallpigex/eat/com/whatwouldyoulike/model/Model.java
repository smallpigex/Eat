package com.smallpigex.eat.com.whatwouldyoulike.model;

import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
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

    public Model(SharedPreferences preferences) {
        this.preferences = preferences;
        initAppInformation();
    }

    public void initAppInformation() {
        storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        imageFolderPath = storageDir.getAbsolutePath() + "/" + IMAGE_FOLDER;
        createPhotoFolder();
    }

    private void createPhotoFolder() {
        for(File childFile : storageDir.listFiles()) {
            if(childFile.getName().equals(IMAGE_FOLDER)) {
                return;
            }
        }
        File imageFolder = new File(imageFolderPath);
        imageFolder.mkdir();
    }

    public void saveLocation(String location) {
        if(location.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(location, location);
        editor.commit();
    }

    public List<Location> getAllLocation() {
        List<Location> locations = new ArrayList<Location>();
        Map<String, ?> allLocation;
        allLocation = preferences.getAll();
        for(Map.Entry<String, ?> entry : allLocation.entrySet()) {
            Location location = new Location();
            location.setLocationName(entry.getValue().toString());
            Log.i("Location ", entry.getValue().toString());
            locations.add(location);
        }
        return locations;
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
}
