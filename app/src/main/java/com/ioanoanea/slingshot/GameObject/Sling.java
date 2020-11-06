package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class Sling {

    private Context context;
    private double positionX;
    private double positionY;
    private double cordPositionX;
    private double cordPositionY;

    public Sling(Context context, double positionX, double positionY, double cordPositionX, double cordPositionY){
        this.context = context;
        this.positionX = positionX;
        this.positionY = positionY;
        this.cordPositionX = cordPositionX;
        this.cordPositionY = cordPositionY;
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
        return positionX - 35;
    }


    /**
     * Returns right rectangle's position X
     * @return (double) position X
     */
    private double getRightRectanglePositionX(){
        return positionX + 35;
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
     * Set cord position
     * @param cordPositionX (double) cord position X
     * @param cordPositionY (double) cord position y
     */
    public void setCordPosition(double cordPositionX, double cordPositionY){
        this.cordPositionX = cordPositionX;
        this.cordPositionY = cordPositionY;
    }


    /**
     * Reset sling's cord to initial position
     */
    public void reset(){
        this.cordPositionX = this.positionX;
        this.cordPositionY = this.positionY;
    }


    /**
     * Draw the sling
     * @param canvas (canvas) canvas value
     */
    public void draw(Canvas canvas){

        drawCord(canvas);

        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.cyan_blue));

        // Draw sling's left side rectangle
        canvas.drawRect(
                (float) (getLeftRectanglePositionX() - 7) * getDensity(),
                (float) (positionY - 7) * getDensity(),
                (float) (getLeftRectanglePositionX() + 7) *getDensity(),
                (float) (positionY + 7) * getDensity(),
                paint
        );

        // Draw sling's right side rectangle
        canvas.drawRect(
                (float) (getRightRectanglePositionX() - 7) * getDensity(),
                (float) (positionY - 7) * getDensity(),
                (float) (getRightRectanglePositionX() + 7) * getDensity(),
                (float) (positionY + 7) * getDensity(),
                paint
        );
    }


    /**
     * Draw sling's cord
     * @param canvas (canvas) canvas value
     */
    public void drawCord(Canvas canvas){
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(context.getResources().getColor(R.color.light_grey));

        // Draw left side cord
        canvas.drawLine(
                (float) getLeftRectanglePositionX() * getDensity(),
                (float) this.positionY * getDensity(),
                (float) cordPositionX * getDensity(),
                (float) cordPositionY * getDensity(),
                paint
        );

        // Draw right side cord
        canvas.drawLine(
                (float) cordPositionX * getDensity(),
                (float) cordPositionY * getDensity(),
                (float) getRightRectanglePositionX() * getDensity(),
                (float) this.positionY * getDensity(),
                paint
        );
    }

}
