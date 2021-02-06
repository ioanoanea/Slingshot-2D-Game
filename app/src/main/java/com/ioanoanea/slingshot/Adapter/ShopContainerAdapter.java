package com.ioanoanea.slingshot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ioanoanea.slingshot.BuildConfig;
import com.ioanoanea.slingshot.R;
import com.ioanoanea.slingshot.ViewHolder.BuyBulletsViewHolder;
import com.ioanoanea.slingshot.ViewHolder.BuyCoinsViewHolder;

public class ShopContainerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    public ShopContainerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bulletsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_bullets_card, parent,false);
        View coinsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_coins_card, parent,false);

        // initialize viewHolder
        if (viewType == 1) {
            return new BuyCoinsViewHolder(coinsView);
        }
        return new BuyBulletsViewHolder(bulletsView);
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

        if (holder.getItemViewType() == 1) {
            if (BuildConfig.DEBUG && !(holder instanceof BuyCoinsViewHolder)) {
                throw new AssertionError("Assertion failed");
            }
            final BuyCoinsViewHolder viewHolder = (BuyCoinsViewHolder) holder;

            // on item 10 coins click
            viewHolder.item10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You got 10 coins", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 25 coins click
            viewHolder.item25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You got 25 coins", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 50 coins click
            viewHolder.item50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You got 50 coins", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 100 coins click
            viewHolder.item100.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You got 100 coins", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 250 coins click
            viewHolder.item250.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You got 250 coins", Toast.LENGTH_SHORT).show();
                }
            });

            // on item 500 coins click
            viewHolder.item500.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You get 500 coins", Toast.LENGTH_SHORT).show();
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
        return 2;
    }
}
