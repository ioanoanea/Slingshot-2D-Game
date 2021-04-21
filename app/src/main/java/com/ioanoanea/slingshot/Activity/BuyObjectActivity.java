package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.R;

public class BuyObjectActivity extends AppCompatActivity {

    private TextView textPrice;
    private Button buy;
    private Button exit;
    private CoinManager coinManager;
    private BulletManager bulletManager;
    private ImageView bulletImageView;


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_object);
        resizeWindow();
        setViews();

        coinManager = new CoinManager(this);
        bulletManager = new BulletManager(this);

        Intent intent = getIntent();
        int price = intent.getIntExtra("PRICE", 0);
        int count = intent.getIntExtra("COUNT", 0);

        textPrice.setText("" + price);

        switch (count){
            case 5:
                bulletImageView.setImageResource(R.drawable.ic_bullets_5);
                break;
            case 25:
                bulletImageView.setImageResource(R.drawable.ic_bullets_25);
                break;
            case 50:
                bulletImageView.setImageResource(R.drawable.ic_bullets_50);
                break;
        }

        if (count != 0 && price != 0 && coinManager.getCoins() <= price){
            buy.setTextColor(getResources().getColor(R.color.red));
        }

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0 && price != 0){
                    if (coinManager.getCoins() >= price){
                        coinManager.removeCoins(price);
                        bulletManager.addBullets(count);
                        Toast.makeText(BuyObjectActivity.this, "Bought " + count + " bullets!", Toast.LENGTH_SHORT).show();
                        if (coinManager.getCoins() < price){
                            buy.setTextColor(getResources().getColor(R.color.red));
                        }
                    } else {
                        Toast.makeText(BuyObjectActivity.this, "You don't have enough  coins!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuyObjectActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyObjectActivity.this.finish();
            }
        });
    }

    private void setViews(){
        textPrice = findViewById(R.id.text_price);
        buy = findViewById(R.id.button_buy);
        exit = findViewById(R.id.button_exit);
        bulletImageView = findViewById(R.id.image_bullets);
    }

    /**
     * set window width and height
     */
    @SuppressLint("ResourceAsColor")
    private void resizeWindow(){
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
    }
}