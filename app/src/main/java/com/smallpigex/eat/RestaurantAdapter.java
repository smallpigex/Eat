package com.smallpigex.eat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.smallpigex.eat.com.eating.util.Consts;
import com.smallpigex.eat.com.whatwouldyoulike.model.Restaurant;

/**
 * Created by smallpigex on 2015/8/18.
 */
public class RestaurantAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater mInflater;

    public RestaurantAdapter(Context context, List<Map<String, Object>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_grid_view_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.restaurantImageView);
            holder.nameView = (TextView) convertView.findViewById(R.id.restaurantNameView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Log.d(Consts.LOG_TAG, "image path is " + data.get(position).get(Restaurant.PHOTO_PATH).toString());
        Bitmap bitmap = BitmapFactory.decodeFile(data.get(position).get(Restaurant.PHOTO_PATH).toString(), options);
        holder.imageView.setImageBitmap(bitmap);*/
        holder.imageView.setImageURI(Uri.parse(new File(data.get(position).get(Restaurant.PHOTO_PATH).toString()).toString()));
        Log.d(Consts.LOG_TAG, "The Restaurant is " + data.get(position).get(Restaurant.NAME).toString());
        holder.nameView.setText(data.get(position).get(Restaurant.NAME).toString());

        return convertView;
    }

    public final class ViewHolder {
        public ImageView imageView;
        public TextView nameView;
    }
}
