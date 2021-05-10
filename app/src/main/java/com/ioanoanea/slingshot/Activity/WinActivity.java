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

import com.ioanoanea.slingshot.Levels.Level;
import com.ioanoanea.slingshot.Levels.LevelList;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.Manager.LevelManager;
import com.ioanoanea.slingshot.R;

public class WinActivity extends AppCompatActivity {

    private Button nextLevelButton;
    private LevelManager levelManager;
    private LevelList levelList;
    private TextView textBonusAmount;
    private CoinManager coinManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        setViews();
        resizeWindow();

        // initialize level manger
        levelManager = new LevelManager(this);
        levelList = new LevelList(this);
        coinManager = new CoinManager(this);

        textBonusAmount.setText("+" + levelList.getLevels().get(levelManager.getLevel() - 1).getBonusCoins());
        coinManager.addCoins(levelList.getLevels().get(levelManager.getLevel() - 1).getBonusCoins());

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (levelList.getLevels().size() == levelManager.getLevel()){
                    Toast.makeText(WinActivity.this, "This is the last level!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WinActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    levelManager.nextLevel();
                    Intent intent = new Intent(WinActivity.this, PlayActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                WinActivity.this.finish();
            }
        });
    }

    private void setViews(){
        nextLevelButton = findViewById(R.id.button_next_level);
        textBonusAmount = findViewById(R.id.text_bonus_amount);
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
        Intent intent = new Intent(WinActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        WinActivity.this.finish();
    }
}