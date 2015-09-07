package com.smallpigex.eat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAlertDialogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAlertDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAlertDialogFragment extends DialogFragment {
    public static String TITLE = "dialogTitle";
    private String value = "";
    public static MyAlertDialogFragment newInstance(int title) {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();

        Bundle args = new Bundle();
        args.putInt(TITLE, title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(TITLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_my_alert_dialog, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setPositiveButton(R.string.alert_dialog_ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (getDialog() != null) {
                            EditText editText = (EditText) getDialog().findViewById(R.id.editText);
                            value = editText.getText().toString();
                        }
                        ((MainActivity) getActivity()).doPositiveClick(value);
                    }
                }
        );
        alertDialogBuilder.setNegativeButton(R.string.alert_dialog_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ((MainActivity) getActivity()).doNegativeClick();
                    }
                }
        );
        return alertDialogBuilder.create();
    }
}
