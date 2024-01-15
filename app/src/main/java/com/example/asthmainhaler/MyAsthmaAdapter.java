package com.example.asthmainhaler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAsthmaAdapter extends RecyclerView.Adapter<MyAsthmaAdapter.ViewHolder> {

    MyAsthmaData[] myAsthmaData;
    Context context;

    public MyAsthmaAdapter(MyAsthmaData[] myAsthmaData, TrackActivity activity) {
        this.myAsthmaData = myAsthmaData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.attack_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyAsthmaData myAsthmaDataList = myAsthmaData[position];
        holder.location.setText(myAsthmaDataList.getLocation());
        holder.date.setText(myAsthmaDataList.getDate());
        holder.trigger.setText(myAsthmaDataList.getTrigger());
    }

    @Override
    public int getItemCount() {
        return myAsthmaData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView location;
        TextView date;
        TextView trigger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
            trigger = itemView.findViewById(R.id.trigger);
        }
    }
}