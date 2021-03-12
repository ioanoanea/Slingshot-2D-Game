package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ioanoanea.slingshot.Manager.SoundManager;
import com.ioanoanea.slingshot.R;

public class SettingsActivity extends AppCompatActivity {

    private Button soundOnButton;
    private Button soundOffButton;
    private Button shareButton;
    private Button closeButton;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setViews();
        soundManager = new SoundManager(this);

        if (soundManager.checkSoundOn()){
            soundOnButton.setVisibility(View.INVISIBLE);
            soundOffButton.setVisibility(View.VISIBLE);
        } else {
            soundOffButton.setVisibility(View.INVISIBLE);
            soundOnButton.setVisibility(View.VISIBLE);
        }

        soundOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundOnButton.setVisibility(View.INVISIBLE);
                soundOffButton.setVisibility(View.VISIBLE);
                soundManager.turnSoundOn();
            }
        });

        soundOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundOffButton.setVisibility(View.INVISIBLE);
                soundOnButton.setVisibility(View.VISIBLE);
                soundManager.turnSoundOff();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Shared!", Toast.LENGTH_SHORT).show();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

    /**
     * Set activity views
     */
    private void setViews(){
        soundOnButton = findViewById(R.id.button_sound_on);
        soundOffButton = findViewById(R.id.button_sound_off);
        shareButton = findViewById(R.id.button_share);
        closeButton = findViewById(R.id.button_close);
    }
}