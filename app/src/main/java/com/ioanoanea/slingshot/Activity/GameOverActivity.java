package com.ioanoanea.slingshot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.LevelManager;
import com.ioanoanea.slingshot.R;

public class GameOverActivity extends AppCompatActivity {

    private TextView extraBulletsText;
    private Button playAgainButton;
    private Button exitButton;
    private Button watchAdButton;
    private Button extraBulletsButton;
    private RewardedAd mRewardedAd;
    private BulletManager bulletManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        setView();
        // resizeWindow();
        hideSystemUI();

        // display extra bullets number
        bulletManager = new BulletManager(this);

        // initialize ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("GameOverActivity", "Ad failed to load: " + loadAdError);
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        Log.d("GameOverActivity", "Ad loaded");
                        mRewardedAd = rewardedAd;
                    }
                });

        if (mRewardedAd != null) {
            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    mRewardedAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                }
            });
        }


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
                onBackPressed();
            }
        });

        extraBulletsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulletManager.unlockExtraBullets();
                GameOverActivity.this.finish();
            }
        });

        watchAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show reward ad
                if (mRewardedAd != null) {
                    Activity activityContext = GameOverActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        boolean rewardClaimed = false;
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            Log.d("GameOverActivity", "The user earned the reward.");
                            // get reward data
                            int rewardAmount = rewardItem.getAmount();
                            String rewardType = rewardItem.getType();
                            // add rewarded bullets to bullet manager
                            BulletManager bulletManager = new BulletManager(GameOverActivity.this);
                            bulletManager.addBullets(1);
                            rewardClaimed = true;
                        }
                    });
                } else {
                    Log.d("TAG", "The rewarded ad wasn't ready yet.");
                }

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            hideSystemUI();
            extraBulletsText.setText(String.valueOf(bulletManager.getBullets()));

            // hide extra bullets button when there are no extra bullets
            if (bulletManager.getBullets() == 0){
                extraBulletsButton.setVisibility(View.INVISIBLE);
            } else {
                extraBulletsButton.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Set activity views
     */
    private void setView(){
        extraBulletsText = findViewById(R.id.text_extra_bullets_left);
        playAgainButton = findViewById(R.id.button_play_again);
        exitButton = findViewById(R.id.button_exit);
        watchAdButton = findViewById(R.id.button_watch_ad);
        extraBulletsButton = findViewById(R.id.button_extra_bullets);
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
        Intent intent = new Intent(GameOverActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        GameOverActivity.this.finish();
    }
}