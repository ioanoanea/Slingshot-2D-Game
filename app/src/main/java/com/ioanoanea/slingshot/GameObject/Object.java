package com.ioanoanea.slingshot.GameObject;

import android.content.Context;

import com.ioanoanea.slingshot.GameEngine.GameRender;

public class Object {

    protected final Context context;
    protected DestroyListener destroyListener;

    public Object(Context context){
        this.context = context;
        this.destroyListener = null;
    }

    /**
     * Returns display density
     * @return (float) density
     */
    protected float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }

    public interface DestroyListener {
        default void onDestroyed(){
        }
    }
}
