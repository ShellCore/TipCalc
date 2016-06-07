package com.edx.shell.android.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.tipcalc.R;
import com.edx.shell.android.tipcalc.listeners.OnItemClickListener;
import com.edx.shell.android.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private Context context;
    private List<TipRecord> recordList;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, List<TipRecord> recordList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.recordList = recordList;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.recordList = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = recordList.get(position);
        String strTip = String.format(context.getString(R.string.propina), element.getTip());
        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void add(TipRecord record) {
        recordList.add(record);
        notifyDataSetChanged();
    }

    public void clear() {
        recordList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.txt_content);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}
