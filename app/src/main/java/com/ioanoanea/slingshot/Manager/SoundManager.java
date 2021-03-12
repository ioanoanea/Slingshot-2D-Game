package com.ioanoanea.slingshot.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.ioanoanea.slingshot.R;

public class SoundManager {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    public static final int STRETCH = R.raw.stretch;
    public static final int SHOT = R.raw.shot;

    @SuppressLint("CommitPrefEdits")
    public SoundManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SOUND",0);
        editor = sharedPreferences.edit();
    }

    /**
     * Turn the sound off
     */
    public void turnSoundOff(){
        editor.putBoolean("SOUND", false);
        editor.apply();
    }

    /**
     * Turn sound on
     */
    public void turnSoundOn(){
        editor.putBoolean("SOUND", true);
        editor.apply();
    }

    /**
     * Check if sound is on
     */
    public boolean checkSoundOn(){
        return sharedPreferences.getBoolean("SOUND", true);
    }

    /**
     * Load sound
     * @param sound (int) sound
     */
    public void loadSound(int sound){
        MediaPlayer loadedSound = MediaPlayer.create(context, sound);
        loadedSound.start();
    }
}
