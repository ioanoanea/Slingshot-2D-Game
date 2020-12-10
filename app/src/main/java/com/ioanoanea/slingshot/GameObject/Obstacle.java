package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class Obstacle extends Object {

    private final double left;
    private final double right;
    private final double top;
    private final double bottom;

    public Obstacle(Context context, double positionX, double positionY, double length){
        super(context);
        this.left = positionX - length / 2;
        this.right = positionX + length / 2;
        this.top = positionY - 10;
        this.bottom = positionY + 10;
    }

    public Obstacle(Context context, double left, double right, double top, double bottom){
        super(context);
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * Returns position of obstacle's left side
     * @return (double) left side position
     */
    public double getLeft() {
        return left;
    }

    /**
     * Returns position of obstacle's right side
     * @return (double) right side position
     */
    public double getRight() {
        return right;
    }

    /**
     * Returns position of obstacle's top side
     * @return (double) top side postition
     */
    public double getTop() {
        return top;
    }

    /**
     * Returns position of obstacle's bottom side
     * @return (double) bottom side postition
     */
    public double getBottom() {
        return bottom;
    }

    /**
     * Returns obstacle's length
     * @return (int) length
     */
    public int getLength() {
        return (int) (right - left);
    }

    /**
     * Returns obstacle's height
     * @return (int) height
     */
    public int getHeight() {
        return (int) (bottom - top);
    }

    /**
     * Check if an object intersects obstacle's North area
     * North area position:
     * Width: from obstacle's left side to obstacle's right side
     * Height: from obstacle's bottom side to top of the screen
     * @param positionX (double) object position X
     * @param positionY (double) object position Y
     * @return true if object is in North area, false otherwise
     */
    public boolean intersectNorthArea(double positionX, double positionY){
        return left <= positionX && positionX <= right && positionY <= bottom;
    }

    /**
     * Check if an object intersects obstacle's South area
     * South area position:
     * Width: from obstacle's left side to obstacle's right side
     * Height: from obstacle's top side bottom of the screen
     * @param positionX (double) object position X
     * @param positionY (double) object position Y
     * @return true if object is in South area, false otherwise
     */
    public boolean intersectsSouthArea(double positionX, double positionY){
        return left <= positionX && positionX <= right && positionY >= top;
    }

    /**
     * Check if an object intersects obstacle's West area
     * West area position:
     * Width: from left of the screen to obstacle's right side
     * Height: from obstacle's top side to obstacle's bottom side
     * @param positionX (double) object position X
     * @param positionY (double) object position Y
     * @return true if object is in West area, false otherwise
     */
    public boolean intersectsWestArea(double positionX, double positionY){
        return top <= positionY && positionY <= bottom && positionX <= right;
    }

    /**
     * Check if an objects intersects obstacle's East area
     * East area position:
     * Width: from obstacle's left side to right of the screen
     * Height: from obstacle's top side to obstacle's bottom side
     * @param positionX (double) object position X
     * @param positionY (double) object position Y
     * @return true if object is in East area, false otherwise
     */
    public boolean intersectsEastArea(double positionX, double positionY){
        return top <= positionY && positionY <= bottom && positionX >= left;
    }

    /**
     * Draw the obstacle in the arena
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        canvas.drawRect(
                (float) left * getDensity(),
                (float) top * getDensity(),
                (float) right * getDensity(),
                (float) bottom * getDensity(),
                paint
        );
    }


}
