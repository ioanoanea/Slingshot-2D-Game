package com.ioanoanea.slingshot.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ioanoanea.slingshot.R;

public class BuyBulletsViewHolder extends RecyclerView.ViewHolder {

    public ImageView item5;
    public ImageView item25;
    public ImageView item50;

    public BuyBulletsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.item5 = itemView.findViewById(R.id.image_background_item_5);
        this.item25 = itemView.findViewById(R.id.image_background_item_25);
        this.item50 = itemView.findViewById(R.id.image_background_item_50);
    }
}
