package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

public class Bullet {

    private Context context;
    private double positionX;
    private double positionY;
    private boolean locked = true;
    private double distanceToNextPositionX = 0;
    private double distanceToNextPositionY = 0;

    public Bullet(Context context, double positionX, double positionY){
        this.context = context;
        this.positionX = positionX;
        this.positionY = positionY;
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
        this.positionX = positionX;
        this.positionY = positionY;
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
