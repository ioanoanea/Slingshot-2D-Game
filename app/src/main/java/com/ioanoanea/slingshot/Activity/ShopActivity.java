package com.ioanoanea.slingshot.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.ioanoanea.slingshot.Adapter.ShopContainerAdapter;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.Manager.LevelManager;
import com.ioanoanea.slingshot.R;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView container;
    private TextView bulletsText;
    private TextView coinsText;
    private Button closeButton;
    private ShopContainerAdapter adapter;
    private LevelManager levelManager;
    private BulletManager bulletManager;
    private CoinManager coinManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        // set views
        setViews();

        // set managers
        levelManager = new LevelManager(this);
        bulletManager = new BulletManager(this);
        coinManager = new CoinManager(this);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // set container adapter
        adapter = new ShopContainerAdapter(this);

        adapter.setOnCoinsPurchasedListener(new ShopContainerAdapter.OnCoinsPurchasedListener() {
            @Override
            public void onPurchased() {
                coinsText.setText(String.valueOf(coinManager.getCoins()));
            }
        });

        container.setLayoutManager(new LinearLayoutManager(this));
        container.setAdapter(adapter);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            // set fullscreen mode
            hideSystemUI();

            // set info text
            bulletsText.setText(String.valueOf(bulletManager.getBullets()));
            coinsText.setText(String.valueOf(coinManager.getCoins()));
        }
    }

    /**
     * Hide system UI
     * Remove nav bar and notification bar
     * Set fullscreen
     */
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    /**
     * Set activity views
     */
    private void setViews(){
        container = findViewById(R.id.shop_container);
        bulletsText = findViewById(R.id.bullets_text);
        coinsText = findViewById(R.id.coins_text);
        closeButton = findViewById(R.id.button_close);
    }
}