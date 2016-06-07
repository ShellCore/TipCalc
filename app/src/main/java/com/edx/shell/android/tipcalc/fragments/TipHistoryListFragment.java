package com.edx.shell.android.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edx.shell.android.tipcalc.R;

public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tip_history_list, container, false);
    }

    @Override
    public void action(String string) {
        Toast.makeText(getActivity().getApplicationContext(), string, Toast.LENGTH_LONG)
                .show();
    }
}
