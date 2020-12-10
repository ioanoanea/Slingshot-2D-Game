package com.ioanoanea.slingshot.GameObject;

import android.content.Context;

public class Object {

    protected final Context context;

    public Object(Context context){
        this.context = context;
    }

    /**
     * Returns display density
     * @return (float) density
     */
    protected float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }
}
