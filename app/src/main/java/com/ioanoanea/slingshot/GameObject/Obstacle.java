package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class Obstacle {

    private Context context;
    private int positionX;
    private int positionY;
    private int length;

    public Obstacle(Context context, int positionX, int positionY, int length){
        this.context = context;
        this.positionX = positionX;
        this.positionY = positionY;
        this.length = length;
    }


    /**
     * Returns obstacle's position X
     * @return (int) position X
     */
    public int getPositionX() {
        return positionX;
    }


    /**
     * Returns obstacle's position Y
     * @return (int) position Y
     */
    public int getPositionY() {
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
     * @return (int) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Draw the obstacle in the arena
     * @param canvas (canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        canvas.drawRect(positionX * getDensity(), positionY * getDensity(),
                positionX * getDensity() + length * getDensity(),
                positionY * getDensity() + 20 * getDensity(), paint);
    }


}
