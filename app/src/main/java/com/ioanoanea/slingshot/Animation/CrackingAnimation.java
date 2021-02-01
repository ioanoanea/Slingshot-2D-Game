package com.ioanoanea.slingshot.Animation;

import android.content.Context;
import android.graphics.Canvas;

import com.ioanoanea.slingshot.GameObject.TargetObject;

import java.util.ArrayList;
import java.util.Random;

public class CrackingAnimation extends AnimationObject {

    private final double left;
    private final double right;
    private final double top;
    private final double bottom;
    private Particle particleNV, particleNE, particleSW, particleSE;
    private final ArrayList<Particle> mParticles = new ArrayList<>();
    private final ArrayList<Particle> sParticles = new ArrayList<>();
    private OnAnimationFinishedListener onAnimationFinishedListener;

    public CrackingAnimation(Context context, TargetObject targetObject){
        super(context);
        this.left = targetObject.getLeft();
        this.right = targetObject.getRight();
        this.top = targetObject.getTop();
        this.bottom = targetObject.getBottom();
        setBigParticles();
        setMediumParticle();
        setSmallParticles();
    }

    /**
     * Update particles position
     */
    public void update(){
        // update big particles
        particleNV.update();
        particleNE.update();
        particleSE.update();
        particleSW.update();

        // update medium particles
        for (Particle particle: mParticles){
            particle.update();
        }

        // update small particles
        for (Particle particle: sParticles){
            particle.update();
        }

        if (checkAnimationFinished())
            onAnimationFinishedListener.onFinished();
    }

    /**
     * Set 4 big particles in each corner of the object
     */
    private void setBigParticles(){
        particleNV = new Particle(context, left + 5, top - 5, 5, -1, -1);
        particleNE = new Particle(context, right - 5, top - 5, 5, 1, -1);
        particleSE = new Particle(context, left + 5, bottom - 5, 5, -1, 1);
        particleSW = new Particle(context, right - 5, bottom - 5, 5, 1, 1);
    }

    /**
     * Set 6 medium particles in target object area
     */
    private void setMediumParticle(){
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            // set particle position
            double positionX = random.nextDouble() * (right  - left) + left;
            double positionY  = random.nextDouble() * (bottom - top) + top;

            // set particle direction
            double directionX;
            double directionY;

            if (right - positionX > positionX - left)
                directionX = -1;
            else directionX = 1;

            if (bottom - positionY > positionY - top)
                directionY = -1;
            else directionY = 1;

            // set particle
            Particle particle = new Particle(context, positionX, positionY, 3, directionX, directionY);
            mParticles.add(particle);
        }
    }

    /**
     * Set 8 small particles in target object area
     */
    private void setSmallParticles(){
        Random random = new Random();

        for (int i = 0; i < 8; i++){
            // set particle position
            double positionX = random.nextDouble() * (right  - left) + left;
            double positionY  = random.nextDouble() * (bottom - top) + top;

            // set particle
            Particle particle = new Particle(context, positionX, positionY, 2, 150);
            sParticles.add(particle);
        }
    }

    /**
     * Checks if animation is finished
     * Checks if particle's alpha is 0
     * @return true if is finished, false otherwise
     */
    private boolean checkAnimationFinished(){
        return particleNV.getAlpha() == 0;
    }

    /**
     * Draw particles
     * @param canvas (Canvas) Canvas value
     */
    public void draw(Canvas canvas){
        // Draw big particles
        particleNV.draw(canvas);
        particleNE.draw(canvas);
        particleSE.draw(canvas);
        particleSW.draw(canvas);

        // Draw medium particles
        for (Particle particle: mParticles){
            particle.draw(canvas);
        }

        // Draw small particles
        for (Particle particle: sParticles){
            particle.draw(canvas);
        }
    }

    /**
     * Set an action when animation is finished
     * @param listener Animation finished listener
     */
    public void setOnAnimationFinishedListener(OnAnimationFinishedListener listener){
        this.onAnimationFinishedListener = listener;
    }

    /**
     * Handle animation was finished
     */
    public interface OnAnimationFinishedListener {
        /**
         * Method called when animation was finished
         * Must override this method with code that will be executed when animation is finished
         */
        default void onFinished(){
        }
    }
    
}
