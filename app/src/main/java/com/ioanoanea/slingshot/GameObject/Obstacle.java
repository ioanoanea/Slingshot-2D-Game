package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class Obstacle {

    private Context context;
    private double positionX;
    private double positionY;
    private int length;

    public Obstacle(Context context, double positionX, double positionY, int length){
        this.context = context;
        this.positionX = positionX;
        this.positionY = positionY;
        this.length = length;
    }


    /**
     * Returns obstacle's position X
     * @return (double) position X
     */
    public double getPositionX() {
        return positionX;
    }


    /**
     * Returns obstacle's position Y
     * @return (double) position Y
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Returns obstacle's length
     * @return (int) length
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns display density
     * @return (float) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Draw the obstacle in the arena
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        canvas.drawRect(
                (float) getPositionX() * getDensity(),
                (float) getPositionY() * getDensity(),
                (float) getPositionX() * getDensity() + getLength() * getDensity(),
                (float) getPositionY() * getDensity() + 20 * getDensity(),
                paint
        );
    }


}
