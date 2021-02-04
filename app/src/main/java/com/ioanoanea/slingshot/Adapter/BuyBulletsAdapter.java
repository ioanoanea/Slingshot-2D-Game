package com.ioanoanea.slingshot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ioanoanea.slingshot.R;
import com.ioanoanea.slingshot.ViewHolder.BuyBulletsViewHolder;

public class BuyBulletsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    public BuyBulletsAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_bullets_card, parent,false);

        // initialize viewHolder
        switch (viewType){
            default:
                return new BuyBulletsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            final BuyBulletsViewHolder viewHolder = (BuyBulletsViewHolder) holder;

            // on item 5 bullets click
            viewHolder.item5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You bought 5 bullets", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 25 bullets click
            viewHolder.item25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You bought 25 bullets", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 50 bullets click
            viewHolder.item50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You bought 50 bullets", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
