package com.smallpigex.eat;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class RestaurantFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CURRENT_LOCATION = "Location";
    private static final String RESTAURANTS = "Restaurants";

    // TODO: Rename and change types of parameters
    private String selectLocation;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private BaseAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static RestaurantFragment newInstance(String location) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putString(CURRENT_LOCATION, location);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            selectLocation = getArguments().getString(CURRENT_LOCATION);
        }

        createNewAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        // Set the adapter
        if (mAdapter != null) {
            if(isNewData()) {
                createNewAdapter();
            }
            mListView = (AbsListView) view.findViewById(android.R.id.list);
            mListView.setAdapter(mAdapter);
            // Set OnItemClickListener so we can be notified on item clicks
            mListView.setOnItemClickListener(this);
        } else {
            TextView noRestaurantView = (TextView) view.findViewById(R.id.noRestaurantView);
            noRestaurantView.setText("There aren't  any restaurants.");
        }

        FloatingActionButton addRestaurantButton = (FloatingActionButton) view.findViewById(R.id.addRestaurant);
        addRestaurantButton.setOnClickListener(this);
        mListener = (OnFragmentInteractionListener) getActivity();
        return view;
    }

    public void createNewAdapter() {
        List<Map<String, Object>> restaurants = ((MainActivity) getActivity()).model.getRestaurantByLocation(selectLocation);
        if (restaurants != null) {
            mAdapter = new RestaurantAdapter(getActivity(), restaurants);
        }
    }

    public boolean isNewData() {
        int dataSize = ((MainActivity) getActivity()).model.getRestaurantByLocation(selectLocation).size();
        if (dataSize > mAdapter.getCount()) {
            return true;
        }
        return false;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Map<String, Object> objectMap = (Map<String, Object>) parent.getItemAtPosition(position);

            mListener.showRestaurantDetail(objectMap);
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
        //show add restaurant fragment?
        mListener.showAddRestaurantFragment(selectLocation);
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
        public void showRestaurantDetail(Map<String, Object> objectMap);

        public void showAddRestaurantFragment(String location);
    }

}
