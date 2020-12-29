package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ioanoanea.slingshot.R;

public class WinActivity extends AppCompatActivity {

    private Button nextLevelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        setViews();
        resizeWindow();

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WinActivity.this, "No levels available!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void setViews(){
        nextLevelButton = findViewById(R.id.button_next_level);
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