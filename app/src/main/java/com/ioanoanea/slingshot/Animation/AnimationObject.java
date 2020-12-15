package com.ioanoanea.slingshot.Animation;

import android.content.Context;

public class AnimationObject {

    public Context context;

    public AnimationObject(Context context){
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
