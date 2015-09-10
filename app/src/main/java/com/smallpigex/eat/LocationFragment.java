package com.smallpigex.eat;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.smallpigex.eat.com.whatwouldyoulike.model.Location;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class LocationFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String IS_SLOTMACHINE_STATE = "state";

    // TODO: Rename and change types of parameters
    private boolean isSlotMachineState;
    private List<Location> locations;
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static LocationFragment newInstance(boolean isSlotMachineState) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_SLOTMACHINE_STATE, isSlotMachineState);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            isSlotMachineState = getArguments().getBoolean(IS_SLOTMACHINE_STATE);
        }
        locations = MainActivity.model.getAllLocation();
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<Location>(getActivity(),
                R.layout.fragment_location_list, android.R.id.text1, locations);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        if(mListView.getCount() == 0){
            TextView noRestaurantView = (TextView) view.findViewById(R.id.noLocation);
            noRestaurantView.setText("Please add some location.");
        }
        // Set OnItemClickListener so we can be notified on item clicks
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.addLocation);
        mListView.setOnItemClickListener(this);
        if(!isSlotMachineState) {
            button.setOnClickListener(this);
        } else {
            button.setVisibility(View.INVISIBLE);
        }

        return view;
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

    @Override
    public void onResume() {
        locations = MainActivity.model.getAllLocation();
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<Location>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, locations);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Log.i("Click location ", locations.get(position).getLocationName());
           mListener.onFragmentInteraction(locations.get(position).getLocationName(), isSlotMachineState);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    private void showDialog() {
        DialogFragment newFragment = MyAlertDialogFragment.newInstance(
                R.string.create_new_location);
        newFragment.show(getFragmentManager(), "CreateNewLocation");
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
        public void onFragmentInteraction(String id, boolean state);
    }

}
