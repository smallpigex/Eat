package com.smallpigex.eat;

import android.app.Fragment;

/**
 * Created by smallpigex on 2015/7/15.
 */
public class FragmentFactory {
    public static Fragment newInstance(int position) {
        if(position == 0) {
            return new LocationFragment();
        }
        return null;
    }
}
