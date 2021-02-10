package com.ioanoanea.slingshot.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    public TextView priceItem10;
    public TextView priceItem25;
    public TextView priceItem50;
    public TextView priceItem100;
    public TextView priceItem250;
    public TextView priceItem500;

    public BuyCoinsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.item10 = itemView.findViewById(R.id.image_background_item_10);
        this.item25 = itemView.findViewById(R.id.image_background_item_25);
        this.item50 = itemView.findViewById(R.id.image_background_item_50);
        this.item100 = itemView.findViewById(R.id.image_background_item_100);
        this.item250 = itemView.findViewById(R.id.image_background_item_250);
        this.item500 = itemView.findViewById(R.id.image_background_item_500);
        this.priceItem10 = itemView.findViewById(R.id.price_item_10);
        this.priceItem25 = itemView.findViewById(R.id.price_item_25);
        this.priceItem50 = itemView.findViewById(R.id.price_item_50);
        this.priceItem100 = itemView.findViewById(R.id.price_item_100);
        this.priceItem250 = itemView.findViewById(R.id.price_item_250);
        this.priceItem500 = itemView.findViewById(R.id.price_item_500);
    }
}
