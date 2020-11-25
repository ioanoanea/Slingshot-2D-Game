package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

public class Bullet {

    private final Context context;
    private final double screenWidth;
    private final double screenHeight;
    private double positionX = -100;
    private double positionY = -100;
    private boolean locked = true;
    private double distanceToNextPositionX = 0;
    private double distanceToNextPositionY = 0;

    public Bullet(Context context, double screenWidth, double screenHeight){
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }


    /**
     * Returns display density
     * @return (float) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Set bullet position
     * @param positionX (double) position x
     * @param positionY (double) position Y
     */
    public void setPosition(double positionX, double positionY){

        // if next bullet position is not out of game arena, update bullet position
        if (positionX > 30 && positionY > 30 && positionX < screenWidth / getDensity() - 30 && positionY < screenHeight / getDensity() - 30){
            this.positionX = positionX;
            this.positionY = positionY;
        }
        // if position next X is left to game arena left side, set position X to game arena left side and lock bullet
        else if (positionX < 30){
            this.positionX = 30;
            lock();
        }
        // if position next Y is upper than game arena up side, set position Y to game arena up side and lock bullet
        else if (positionY < 30){
            this.positionY = 30;
            lock();
        }
        // if position next X is right to game arena right side, set position X to game arena right side and lock bullet
        else if(positionX > screenWidth / getDensity() - 30){
            this.positionX = screenWidth / getDensity() - 30;
            lock();
        }
        // if position next Y is lower than game arena bottom side, set position Y to game arena bottom side and lock bullet
        else {
            this.positionY = screenHeight / getDensity() - 30;
            lock();
        }
    }


    /**
     * Returns bullet position X
     * @return (double) position X
     */
    public double getPositionX() {
        return positionX;
    }


    /**
     * Returns bullet position Y
     * @return (double) position Y
     */
    public double getPositionY() {
        return positionY;
    }


    /**
     * Check if bullet is locked
     * @return (boolean) true if bullet is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }


    /**
     * Lock bullet
     */
    public void lock(){
        this.locked = true;
    }


    /**
     * Unlock bullet
     */
    public void unlock(){
        this.locked = false;
    }

    /**
     * Set distance from current position X to next position X
     * @param distanceToPositionX (double) distance to next postion X
     */
    public void setDistanceToNextPositionX(double distanceToPositionX) {
        this.distanceToNextPositionX = distanceToPositionX;
    }

    /**
     * Set distance from current position Y to next position Y
     * @param distanceToPositionY (double) distance to next position Y
     */
    public void setDistanceToNextPositionY(double distanceToPositionY) {
        this.distanceToNextPositionY = distanceToPositionY;
    }

    /**
     * Move the bullet
     */
    public void move(){
            setPosition(
                    getPositionX() + distanceToNextPositionX,
                    getPositionY() + distanceToNextPositionY
            );
    }

    /**
     * Draw bullet
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.cyan_blue));

        canvas.drawCircle(
                (float) getPositionX() * getDensity(),
                (float) getPositionY() * getDensity(),
                30,
                paint
        );

    }

}
