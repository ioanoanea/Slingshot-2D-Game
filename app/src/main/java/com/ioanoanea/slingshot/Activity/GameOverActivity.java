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

import com.ioanoanea.slingshot.R;

public class GameOverActivity extends AppCompatActivity {

    private TextView textInfo;
    private Button playAgainButton;
    private Button exitButton;
    private Button watchAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        setView();
        resizeWindow();

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, PlayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                GameOverActivity.this.finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                GameOverActivity.this.finish();
            }
        });

        watchAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameOverActivity.this, "No ads available!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Set activity views
     */
    private void setView(){
        textInfo = findViewById(R.id.text_info);
        playAgainButton = findViewById(R.id.button_play_again);
        exitButton = findViewById(R.id.button_exit);
        watchAdButton = findViewById(R.id.button_watch_ad);
    }

    /**
     * set window width and height
     */
    @SuppressLint("ResourceAsColor")
    private void resizeWindow(){
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameOverActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        GameOverActivity.this.finish();
    }
}