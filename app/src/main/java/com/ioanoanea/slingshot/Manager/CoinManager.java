package com.ioanoanea.slingshot.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class CoinManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    @SuppressLint("CommitPrefEdits")
    public CoinManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("COIN_INFO",0);
        editor = sharedPreferences.edit();
    }

    /**
     * Set current coins number
     * @param coins (int) number of coins to be set
     */
    public void setCoins(int coins){
        editor.putInt("COINS", coins);
        editor.apply();
    }


    /**
     * Returns current number of coins
     * @return (int) number of coins
     */
    public int getCoins(){
        return sharedPreferences.getInt("COINS",10);
    }


    /**
     * Increase current number of coins by a given value
     * @param coins (int) number of coins to be added
     */
    public void addCoins(int coins){
        setCoins(getCoins() + coins);
    }


    /**
     * Decreases current number of coins by a given value
     * @param coins (int) number of coins to be decreased by
     */
    public void removeCoins(int coins){
        setCoins(getCoins() - coins);
    }
}
