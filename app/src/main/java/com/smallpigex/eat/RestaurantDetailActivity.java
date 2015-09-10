package com.smallpigex.eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.smallpigex.eat.R;
import com.smallpigex.eat.com.eating.util.Consts;
import com.smallpigex.eat.com.whatwouldyoulike.model.ImageManagement;
import com.smallpigex.eat.com.whatwouldyoulike.model.Restaurant;

public class RestaurantDetailActivity extends ActionBarActivity {
    private ImageView imageView;
    private TextView name;
    private TextView location;
    private TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        Intent intent = getIntent();
        Restaurant restaurantInfo = (Restaurant) intent.getSerializableExtra(Consts.RESTAURANT_INFORMATION);
        if(restaurantInfo != null) {
            imageView = (ImageView) findViewById(R.id.imageView);
            name = (TextView) findViewById(R.id.name);

            location = (TextView) findViewById(R.id.location);
            comment = (TextView) findViewById(R.id.comment);
            Log.d(Consts.LOG_DEBUG_TAG, restaurantInfo.getRestaurantPhotoPath());

            name.setText(restaurantInfo.getRestaurantName());
            Log.d(Consts.LOG_DEBUG_TAG, restaurantInfo.getRestaurantLocation());
            location.setText(restaurantInfo.getRestaurantLocation());
            comment.setText(restaurantInfo.getRestaurantComment());
            imageView.setImageBitmap(ImageManagement.decodeFile(restaurantInfo.getRestaurantPhotoPath(), 2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_testing_viewer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
