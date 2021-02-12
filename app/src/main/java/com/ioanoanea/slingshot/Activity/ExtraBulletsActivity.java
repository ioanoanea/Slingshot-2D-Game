package com.ioanoanea.slingshot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.R;

public class ExtraBulletsActivity extends AppCompatActivity {

    private TextView extraBulletsText;
    private Button useExtraBulletsButton;
    private BulletManager bulletManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_bullets);

        setViews();
        bulletManager = new BulletManager(this);

        extraBulletsText.setText(Html.fromHtml("<p>"+ bulletManager.getBullets() + "&#x1F534; extra bullets</p>"));
        useExtraBulletsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExtraBulletsActivity.this, "Extra bullets used!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViews(){
        extraBulletsText = findViewById(R.id.text_bullets);
        useExtraBulletsButton = findViewById(R.id.extra_bullets_button);
    }
}