package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ioanoanea.slingshot.GameEngine.GameRender;
import com.ioanoanea.slingshot.R;

public class PlayActivity extends AppCompatActivity {


    private LinearLayout container;
    private TextView levelText;
    private TextView bulletsText;
    private int bulletsNumber = 3;
    public final Activity activity = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setViews();

        GameRender gameRender = new GameRender(this);
        gameRender.setBullets(3);

        // decrease bullet number on bullets text when a bullet is shot
        gameRender.setOnBulletShot(new GameRender.OnBulletShotListener() {
            @Override
            public void onShot() {
                bulletsText.setText(String.valueOf(bulletsNumber - 1));
                bulletsNumber--;
            }
        });

        // close game when last bullet is destroyed
        gameRender.setOnLastBulletDestroyed(new GameRender.OnLastBulletDestroyedListener() {
            @Override
            public void onDestroyed() {
                startActivity(new Intent(PlayActivity.this, GameOverActivity.class));
            }
        });

        // close game when last target object is destroyed
        gameRender.setOnLastTargetObjectDestroyed(new GameRender.OnLastTargetObjectDestroyedListener() {
            @Override
            public void onDestroyed() {
                //startActivity(new Intent(PlayActivity.this, GameOverActivity.class));
            }
        });

        container.addView(gameRender);

        bulletsText.setText(String.valueOf(bulletsNumber));
        levelText.setText("Level: 1");

    }

    /**
     * Set activity views
     */
    private void setViews(){
        container = findViewById(R.id.container);
        levelText = findViewById(R.id.text_level);
        bulletsText = findViewById(R.id.text_bullets);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            // set fullscreen mode
            hideSystemUI();
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

    @Override
    public void onBackPressed() {
        PlayActivity.this.finish();
    }
}