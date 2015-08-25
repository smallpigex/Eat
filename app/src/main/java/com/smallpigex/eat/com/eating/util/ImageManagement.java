package com.smallpigex.eat.com.eating.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by smallpigex on 2015/8/22.
 */
public class ImageManagement {

    public Bitmap decodeFile(String filePath) {
        Bitmap bitmap = null;
        try {
            InputStream inputStreamFile = new FileInputStream(new File(filePath));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            bitmap = BitmapFactory.decodeStream(inputStreamFile, null, options);
        } catch (FileNotFoundException e) {
            Log.d(Consts.EXCEPTION_TAG, e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }
}
