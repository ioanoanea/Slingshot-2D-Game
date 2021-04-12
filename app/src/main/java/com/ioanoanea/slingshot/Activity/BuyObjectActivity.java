package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.R;

public class BuyObjectActivity extends AppCompatActivity {

    private TextView textBulletCount;
    private TextView textPrice;
    private Button buy;
    private CoinManager coinManager;
    private BulletManager bulletManager;


    @SuppressLint("SetTextI18n")
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

        textBulletCount.setText("x" + count);
        textPrice.setText("" + price);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0 && price != 0){
                    if (coinManager.getCoins() >= price){
                        coinManager.removeCoins(price);
                        bulletManager.addBullets(count);
                    } else {
                        Toast.makeText(BuyObjectActivity.this, "You don't have enough  coins!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuyObjectActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setViews(){
        textBulletCount = findViewById(R.id.text_count);
        textPrice = findViewById(R.id.text_price);
        buy = findViewById(R.id.button_buy);
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