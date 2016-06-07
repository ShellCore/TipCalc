package com.edx.shell.android.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edx.shell.android.tipcalc.R;
import com.edx.shell.android.tipcalc.adapters.TipAdapter;
import com.edx.shell.android.tipcalc.listeners.OnItemClickListener;
import com.edx.shell.android.tipcalc.listeners.TipHistoryListFragmentListener;
import com.edx.shell.android.tipcalc.models.TipRecord;

import java.util.ArrayList;

public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {

    // Adaptadores
    private TipAdapter adapter;

    // Componentes
    private RecyclerView recyclerView;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rec_list);
        initAdapter();
        initRecyclerView();
        return v;
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Toast.makeText(getActivity(), tipRecord.getDateFormatted(), Toast.LENGTH_SHORT)
                .show();
    }
}
