package com.ioanoanea.slingshot.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class BulletManager {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Context context;

    @SuppressLint("CommitPrefEdits")
    public BulletManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("BULLET_INFO",0);
        editor = sharedPreferences.edit();
    }


    /**
     * Set number of bullets
     * @param bullets (int) number of bullets to be set
     */
    public void setBullets(int bullets){
        editor.putInt("BULLETS", bullets);
        editor.apply();
    }


    /**
     * Returns current number of bullets
     * @return (int) number of bullets
     */
    public int getBullets(){
        return sharedPreferences.getInt("BULLETS", 0);
    }


    /**
     * Increase current number by a given value
     * @param bullets (int) number of bullets to be added
     */
    public void addBullets(int bullets){
        setBullets(getBullets() + bullets);
    }


    /**
     * Decreases current number of bullets by a given value
     * @param bullets (int) number of bullets to be decreased by
     */
    public void removeBullets(int bullets){
        setBullets(getBullets() - bullets);
    }
}
