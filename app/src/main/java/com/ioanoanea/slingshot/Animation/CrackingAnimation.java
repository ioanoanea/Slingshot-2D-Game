package com.ioanoanea.slingshot.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.ioanoanea.slingshot.GameObject.Object;
import com.ioanoanea.slingshot.GameObject.TargetObject;
import com.ioanoanea.slingshot.R;

import java.util.ArrayList;

public class CrackingAnimation extends AnimationObject {

    private final double left;
    private final double right;
    private final double top;
    private final double bottom;
    Particle particleNV, particleNE, particleSW, particleSE;

    public CrackingAnimation(Context context, TargetObject targetObject){
        super(context);
        this.left = targetObject.getLeft();
        this.right = targetObject.getRight();
        this.top = targetObject.getTop();
        this.bottom = targetObject.getBottom();
        setBiggestParticles();
    }

    /**
     * Update particles position
     */
    public void update(){
        // update biggest particle
        particleNV.update();
        particleNE.update();
        particleSE.update();
        particleSW.update();
    }

    /**
     * Set four bigger particles in each corner of the object
     */
    private void setBiggestParticles(){
        particleNV = new Particle(context, left + 5, top - 5, 5, -1, -1);
        particleNE = new Particle(context, right - 5, top - 5, 5, 1, -1);
        particleSE = new Particle(context, left + 5, bottom - 5, 5, -1, 1);
        particleSW = new Particle(context, right - 5, bottom - 5, 5, 1, 1);
    }

    /**
     * Draw particles
     * @param canvas (Canvas) Canvas value
     */
    public void draw(Canvas canvas){
        particleNV.draw(canvas);
        particleNE.draw(canvas);
        particleSE.draw(canvas);
        particleSW.draw(canvas);
    }
}
