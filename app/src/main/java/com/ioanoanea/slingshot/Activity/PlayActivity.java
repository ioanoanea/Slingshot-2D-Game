package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.gson.Gson;
import com.ioanoanea.slingshot.GameEngine.GameRender;
import com.ioanoanea.slingshot.Levels.Level;
import com.ioanoanea.slingshot.Levels.LevelList;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.LevelManager;
import com.ioanoanea.slingshot.R;

public class PlayActivity extends AppCompatActivity {


    private LinearLayout container;
    private TextView levelText;
    private TextView bulletsText;
    private int bulletsNumber;
    public final Activity activity = this;
    private LevelManager levelManager;
    private BulletManager bulletManager;
    private LevelList levelList;
    private Level level;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setViews();
        GameRender gameRender = new GameRender(this);
        container.addView(gameRender);

        // initializing levels data
        levelManager = new LevelManager(this);
        bulletManager = new BulletManager(this);
        levelList = new LevelList(this);
        level = levelList.getLevels().get(levelManager.getLevel() - 1);
        bulletsNumber = level.getBullets();

        // decrease bullet number on bullets text when a bullet is shot
        gameRender.setOnBulletShot(new GameRender.OnBulletShotListener() {
            @Override
            public void onShot() {
                if (bulletsNumber > 0){
                    bulletsText.setText(String.valueOf(bulletsNumber - 1));
                    bulletsNumber--;
                } else {
                    bulletsText.setText(String.valueOf(bulletManager.getBullets() - 1));
                }
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
                startActivity(new Intent(PlayActivity.this, WinActivity.class));
            }
        });

        // set level data (obstacles and target object)
        gameRender.setObstacles(level.getObstacles());
        gameRender.setTargetObjects(level.getTargetObjects());
        gameRender.setBullets(level.getBullets());

        bulletsText.setText(String.valueOf(bulletsNumber));
        levelText.setText("Level: " + levelManager.getLevel());

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

    @Override
    protected void onResume() {
        super.onResume();
        if (bulletsNumber == 0 && bulletManager.extraBulletsUnlocked()){
            bulletsText.setText(String.valueOf(bulletManager.getBullets()));
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