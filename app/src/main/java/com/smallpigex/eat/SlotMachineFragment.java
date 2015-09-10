package com.smallpigex.eat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SlotMachineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SlotMachineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlotMachineFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESTAURANT_REGION = "param1";

    // TODO: Rename and change types of parameters
    private String region;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param region Parameter 1.
     * @return A new instance of fragment SlotMachineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SlotMachineFragment newInstance(String region) {
        SlotMachineFragment fragment = new SlotMachineFragment();
        Bundle args = new Bundle();
        args.putString(RESTAURANT_REGION, region);

        fragment.setArguments(args);
        return fragment;
    }

    public SlotMachineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            region = getArguments().getString(RESTAURANT_REGION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slot_machine, container, false);
        Button playButton = (Button) view.findViewById(R.id.playSlotMachine);
        playButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        onButtonPressed(Uri.parse(region));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.showRestaurantInfo(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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
        public void showRestaurantInfo(Uri uri);
    }

}
