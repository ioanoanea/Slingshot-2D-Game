package com.ioanoanea.slingshot.GameObjects;

import android.content.Context;

public class Object {

    protected final Context context;
    protected DestroyListener destroyListener;
    public static final double CENTER = -0.5;
    public static final double LEFT = 0;
    public static final double RIGHT = 400;
    public static final double TOP = 0;
    public static final double BOTTOM = 5000;

    public Object(Context context){
        this.context = context;
        this.destroyListener = null;
    }

    /**
     * Returns display density
     * @return (float) density
     */
    protected float getDensity(){
        return (float) context.getResources().getDisplayMetrics().widthPixels / 400;
    }

    public interface DestroyListener {
        default void onDestroyed(){
        }
    }
}
