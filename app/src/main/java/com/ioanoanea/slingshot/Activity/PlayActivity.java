package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ioanoanea.slingshot.GameEngine.GameRender;

public class PlayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameRender(this));
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            // set fullscreen mode
            hideSystemUI();
        }
    }


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

}