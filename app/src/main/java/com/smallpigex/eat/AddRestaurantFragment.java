package com.smallpigex.eat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smallpigex.eat.com.eating.util.Consts;
import com.smallpigex.eat.com.eating.util.ImageManagement;
import com.smallpigex.eat.com.whatwouldyoulike.model.Restaurant;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddRestaurantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddRestaurantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRestaurantFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LOCATION = "location";

    private static final int REQUEST_TAKE_CODE = 1;
    private Button takePhotoButton;
    private Button saveRestaurantButton;
    private TextView locationView;
    private ImageView imageView;
    private String mCurrentPhotoPath;

    // TODO: Rename and change types of parameters
    private String mLocation;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRestaurantFragment newInstance(String location) {
        AddRestaurantFragment fragment = new AddRestaurantFragment();
        Bundle args = new Bundle();
        args.putString(LOCATION, location);
        fragment.setArguments(args);
        return fragment;
    }

    public AddRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getString(LOCATION);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_restaurant, container, false);
        locationView = (TextView) view.findViewById(R.id.locationTextView);
        locationView.setText(mLocation);
        takePhotoButton = (Button) view.findViewById(R.id.takePhoto);
        takePhotoButton.setOnClickListener(this);
        saveRestaurantButton = (Button) view.findViewById(R.id.saveRestaurant);
        saveRestaurantButton.setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.restaurantPhotoView);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        if(buttonId == R.id.takePhoto) {
            takePhotoCommand();
        } else if(buttonId == R.id.saveRestaurant) {
            //save restaurant data;
            saveRestaurantInformationCommand();
        }

    }

    private void takePhotoCommand() {
        Intent takePictureIntent = new Intent();
        takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = MainActivity.model.createImageFile();
                mCurrentPhotoPath = photoFile.getAbsolutePath();
                Log.i(Consts.LOG_TAG, "The Path of Photo is " + mCurrentPhotoPath);
            } catch (IOException ex) {
                Toast.makeText(getActivity(), "The Camera is busy, please close camera.", Toast.LENGTH_LONG).show();
                Log.d(Consts.EXCEPTION_TAG, "IOException " + ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_CODE);
            }
        }
    }

    private void saveRestaurantInformationCommand() {
        TextView restaurantView = (EditText)getView().findViewById(R.id.restaurantName);
        TextView AddressView = (EditText)getView().findViewById(R.id.restaurantLocation);
        TextView commentView = (EditText)getView().findViewById(R.id.restaurantComment);

        String restaurantName = restaurantView.getText().toString();
        String restaurantAddress = AddressView.getText().toString();
        String restaurantComment = commentView.getText().toString();

        Restaurant restaurant = new Restaurant();
        restaurant.setLocation(mLocation);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setRestaurantLocation(restaurantAddress);
        restaurant.setRestaurantComment(restaurantComment);
        if(!mCurrentPhotoPath.isEmpty()) {
            restaurant.setRestaurantPhotoPath(mCurrentPhotoPath);
        }
        ((MainActivity) getActivity()).saveRestaurantInformation(restaurant);
    }

    public interface SaveRestaurantInformation {
        public void saveRestaurantInformation(Restaurant restaurant);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TAKE_CODE) {
            if(resultCode == getActivity().RESULT_OK) {
                ImageManagement im = new ImageManagement();
                Bitmap bitmap = im.decodeFile(mCurrentPhotoPath);
                imageView.setImageBitmap(bitmap);
            } else if(resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getActivity(), "User cancel that take a photo",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
