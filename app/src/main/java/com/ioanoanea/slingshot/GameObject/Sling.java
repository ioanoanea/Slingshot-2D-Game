package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.ioanoanea.slingshot.MathObject.DistanceCalculator;
import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

public class Sling {

    private final Context context;
    private final double screenWidth;
    private final double screenHeight;
    private final double positionX;
    private final double positionY;
    private double cordPositionX;
    private double cordPositionY;
    private double distanceToNextCordPositionX = 0;
    private double distanceToNextCordPositionY = 0;
    private boolean locked = true;

    public Sling(Context context, double screenWidth, double screenHeight){
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.positionX = (screenWidth / getDensity()) / 2;
        this.positionY = (screenHeight / getDensity()) / 4 * 3;
        this.cordPositionX = (screenWidth / getDensity()) / 2;
        this.cordPositionY = (screenHeight / getDensity()) / 4 * 3;
    }

    /**
     * Returns display density
     * @return (int) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Returns left Rectangle's position X
     * @return (double) position X
     */
    private double getLeftRectanglePositionX(){
        return positionX - 30;
    }


    /**
     * Returns right rectangle's position X
     * @return (double) position X
     */
    private double getRightRectanglePositionX(){
        return positionX + 30;
    }


    /**
     * Returns sling's position X
     * @return (double) position X
     */
    public double getPositionX() {
        return positionX;
    }


    /**
     * Returns sling's position Y
     * @return (double) position Y
     */
    public double getPositionY() {
        return positionY;
    }


    /**
     * Returns cord's position X
     * @return (double) position X
     */
    public double getCordPositionX() {
        return cordPositionX;
    }


    /**
     * Returns cord's position Y
     * @return (double) position Y
     */
    public double getCordPositionY() {
        return cordPositionY;
    }


    /**
     * Check is sling is locked or not
     * @return (boolean) true if sling is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }


    /**
     * Check if sling's cord is stretched
     * @return true if it is stretched, false otherwise
     */
    public boolean isStretched(){
        return cordPositionY != positionY || Math.abs(positionX - cordPositionX) > Math.abs(distanceToNextCordPositionX);
    }


    /**
     * Lock the sling
     */
    public void lock(){
        this.locked = true;
    }


    /**
     * Unlock the sling
     */
    public void unlock(){
        this.locked = false;
    }

    /**
     * Set cord position
     * @param cordPositionX (double) cord position X
     * @param cordPositionY (double) cord position y
     */
    public void setCordPosition(double cordPositionX, double cordPositionY){
        this.cordPositionX = cordPositionX;

        // if cord position Y is outside of allowed range, move cord position Y inside of allowed range
        this.cordPositionY = Math.max(cordPositionY, getPositionY());
    }

    /**
     * Set distance to next position X
     * @param distanceToNextCordPositionX (double) distance to next position X
     */
    public void setDistanceToNextCordPositionX(double distanceToNextCordPositionX) {
        this.distanceToNextCordPositionX = distanceToNextCordPositionX;
    }

    /**
     * Set distance to next position Y
     * @param distanceToNextCordPositionY (double) distance to next position Y
     */
    public void setDistanceToNextCordPositionY(double distanceToNextCordPositionY) {
        this.distanceToNextCordPositionY = distanceToNextCordPositionY;
    }

    /**
     * Reset sling's cord to initial position
     */
    public void reset(){
        if(isStretched()){
            setCordPosition(
                    getCordPositionX() + distanceToNextCordPositionX,
                    getCordPositionY() + distanceToNextCordPositionY
            );
        }

    }


    /**
     * Verify if other object intersects sling
     * @param x (double) other object position X
     * @param y (double) other object position Y
     * @return true if object intersects sling, false otherwise
     */
    public boolean intersect(double x, double y){
        return Math.abs(x - getPositionX()) < 50 && Math.abs(y - getPositionY()) < 50;
    }


    /**
     * Draw the sling
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){

        // if sling is unlocked draw guide line
        if(!isLocked()) {
            drawGuideLine(canvas);
        }

        drawCenter(canvas);
        drawCord(canvas);

        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.cyan_blue));

        // Draw sling's left side rectangle
        canvas.drawRect(
                (float) (getLeftRectanglePositionX() - 7) * getDensity(),
                (float) (getPositionY() - 7) * getDensity(),
                (float) (getLeftRectanglePositionX() + 7) *getDensity(),
                (float) (getPositionY() + 7) * getDensity(),
                paint
        );

        // Draw sling's right side rectangle
        canvas.drawRect(
                (float) (getRightRectanglePositionX() - 7) * getDensity(),
                (float) (getPositionY() - 7) * getDensity(),
                (float) (getRightRectanglePositionX() + 7) * getDensity(),
                (float) (getPositionY() + 7) * getDensity(),
                paint
        );
    }


    /**
     * Draw sling's cord
     * @param canvas (Canvas) canvas value
     */
    public void drawCord(Canvas canvas){
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(context.getResources().getColor(R.color.light_grey));

        // Draw left side cord
        canvas.drawLine(
                (float) getLeftRectanglePositionX() * getDensity(),
                (float) getPositionY() * getDensity(),
                (float) getCordPositionX() * getDensity(),
                (float) getCordPositionY() * getDensity(),
                paint
        );

        // Draw right side cord
        canvas.drawLine(
                (float) getCordPositionX() * getDensity(),
                (float) getCordPositionY() * getDensity(),
                (float) getRightRectanglePositionX() * getDensity(),
                (float) getPositionY() * getDensity(),
                paint
        );

    }


    /**
     * Draw sling center as a smal circle
     * @param canvas (Canvas) canvas value
     */
    private void drawCenter(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_grey));
        canvas.drawCircle((float) getPositionX() * getDensity(), (float) getPositionY() * getDensity(), 5, paint);
    }


    /**
     * Draw indicator line
     * @param canvas (Canvas) canvas value
     */
    private void drawGuideLine(Canvas canvas){
        // determine guide line end point position
        double x = getPositionX() + 5 * distanceToNextCordPositionX;
        double y = getPositionY() + 5 * distanceToNextCordPositionY;

        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_grey));

        // draw line
        canvas.drawLine(
                (float) getCordPositionX() * getDensity(),
                (float) getCordPositionY() * getDensity(),
                (float) x * getDensity(),
                (float) y * getDensity(),
                paint
        );
    }

}
