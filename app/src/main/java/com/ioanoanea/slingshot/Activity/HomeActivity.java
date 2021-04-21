package com.ioanoanea.slingshot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ioanoanea.slingshot.Animation.ViewAnimator;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.Manager.LevelManager;
import com.ioanoanea.slingshot.Manager.SoundManager;
import com.ioanoanea.slingshot.R;

public class HomeActivity extends AppCompatActivity {

    private Button playButton;
    private Button shopButton;
    private Button settingsButton;
    private TextView levelText;
    private TextView bulletsText;
    private TextView coinsText;

    private LevelManager levelManager;
    private BulletManager bulletManager;
    private CoinManager coinManager;

    private com.ioanoanea.slingshot.Animation.ViewAnimator viewAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set information managers
        levelManager = new LevelManager(this);
        bulletManager = new BulletManager(this);
        coinManager = new CoinManager(this);
        viewAnimator = new ViewAnimator(this);

        // set views
        setViews();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start PlayActivity
                startActivity(new Intent(HomeActivity.this, PlayActivity.class));
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start ShopActivity
                startActivity(new Intent(HomeActivity.this, ShopActivity.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start SettingsActivity
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        // initialize ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Log.d("HomeActivity", "Ads loaded");
            }
        });
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            // set fullscreen mode
            hideSystemUI();

            // set views user interaction
            levelText.setText("Level: " + levelManager.getLevel());
            bulletsText.setText(String.valueOf(bulletManager.getBullets()));
            coinsText.setText(String.valueOf(coinManager.getCoins()));

            // animate views
            viewAnimator.animate(shopButton, ViewAnimator.BOUNCE_BALL);
            viewAnimator.animate(playButton, ViewAnimator.BOUNCE_BALL, ViewAnimator.DURATION);
            viewAnimator.animate(settingsButton, ViewAnimator.BOUNCE_BALL, 2 * ViewAnimator.DURATION);
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
        playButton = findViewById(R.id.play_button);
        shopButton = findViewById(R.id.shop_button);
        settingsButton = findViewById(R.id.settings_button);
        levelText = findViewById(R.id.level_text);
        coinsText = findViewById(R.id.coins_text);
        bulletsText = findViewById(R.id.bullets_text);
    }
}