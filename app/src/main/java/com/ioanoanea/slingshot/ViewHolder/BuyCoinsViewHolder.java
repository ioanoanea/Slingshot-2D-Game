package com.ioanoanea.slingshot.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ioanoanea.slingshot.R;

public class BuyCoinsViewHolder extends RecyclerView.ViewHolder {

    public ImageView item10;
    public ImageView item25;
    public ImageView item50;
    public ImageView item100;
    public ImageView item250;
    public ImageView item500;

    public BuyCoinsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.item10 = itemView.findViewById(R.id.image_background_item_10);
        this.item25 = itemView.findViewById(R.id.image_background_item_25);
        this.item50 = itemView.findViewById(R.id.image_background_item_50);
        this.item100 = itemView.findViewById(R.id.image_background_item_100);
        this.item250 = itemView.findViewById(R.id.image_background_item_250);
        this.item500 = itemView.findViewById(R.id.image_background_item_500);
    }
}
