package com.ioanoanea.slingshot.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.ioanoanea.slingshot.GameEngine.GameRender;
import com.ioanoanea.slingshot.Levels.LevelList;

public class LevelManager {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Context context;

    @SuppressLint("CommitPrefEdits")
    public LevelManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LEVEL_INFO",0);
        editor = sharedPreferences.edit();
    }

    /**
     * Set current level
     * saved in sharedPreferences
     * @param level (int) level to be set
     */
    public void setLevel(int level){
        editor.putInt("LEVEL", level);
        editor.apply();
    }

    /**
     * Returns current level
     * @return (int) current level
     */
    public int getLevel(){
        return sharedPreferences.getInt("LEVEL",1);
    }

    /**
     * Increases current level by 1
     */
    public void nextLevel(){
        setLevel(getLevel() + 1);
    }
}
