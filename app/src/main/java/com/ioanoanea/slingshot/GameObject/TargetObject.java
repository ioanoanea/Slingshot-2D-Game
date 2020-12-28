package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.Animation.CrackingAnimation;
import com.ioanoanea.slingshot.Animation.Particle;
import com.ioanoanea.slingshot.R;

public class TargetObject extends Object {

    private final double positionX;
    private final double positionY;
    private final double left;
    private final double right;
    private final double top;
    private final double bottom;
    private boolean destroyed = false;
    private final CrackingAnimation crackingAnimation;
    private DestroyListener destroyListener;

    public TargetObject(Context context, double positionX, double positionY){
        super(context);
        this.positionX = positionX;
        this.positionY = positionY;
        this.left = positionX - 15;
        this.right = positionX + 15;
        this.top = positionY - 15;
        this.bottom = positionY + 15;
        this.crackingAnimation = new CrackingAnimation(context, this);
    }

    /**
     * Returns target object's position X
     * @return (double) position X
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Returns target object's position Y
     * @return (double) position Y
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Returns position of target object's left side
     * @return (double) left side position
     */
    public double getLeft() {
        return left;
    }

    /**
     * Returns position of target object's right side
     * @return (double) right side position
     */
    public double getRight() {
        return right;
    }

    /**
     * Returns position of target object's top side
     * @return (double) top side position
     */
    public double getTop() {
        return top;
    }

    /**
     * Returns position of target object's bottom side
     * @return (double) bottom side position
     */
    public double getBottom() {
        return bottom;
    }

    /**
     * Check if an object intersects target object
     * @param positionX (double) object's position X
     * @param positionY (double) object's position Y
     * @return true if object intersects this target object, false otherwise
     */
    public boolean intersects(double positionX, double positionY){
        return left <= positionX && positionX <= right && top <= positionY && positionY <= bottom;
    }

    /**
     * Check if this target object is destroyed
     * @return true if object is destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Destroy this target object
     */
    public void destroy(){
        destroyed = true;
        destroyListener.onDestroy();
    }

    /**
     * Set action on target object destroyed
     * @param listener destroyed listener
     */
    public void setOnDestroyed(DestroyListener listener){
        destroyListener = listener;
    }

    /**
     * Draw target object
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.magenta));
        if (!isDestroyed()){

            canvas.drawRoundRect(
                    (float) left * getDensity(),
                    (float) top * getDensity(),
                    (float) positionX * getDensity() - 5,
                    (float) positionY * getDensity() - 5,
                    5,
                    5,
                    paint
            );

            canvas.drawRoundRect(
                    (float) positionX * getDensity() + 5,
                    (float) top * getDensity(),
                    (float) right * getDensity(),
                    (float) positionY * getDensity() - 5,
                    5,
                    5,
                    paint
            );

            canvas.drawRoundRect(
                    (float) left * getDensity(),
                    (float) positionY * getDensity() + 5,
                    (float) positionX * getDensity() - 5,
                    (float) bottom * getDensity(),
                    5,
                    5,
                    paint
            );

            canvas.drawRoundRect(
                    (float) positionX * getDensity() + 5,
                    (float) positionY * getDensity() + 5,
                    (float) right * getDensity(),
                    (float) bottom * getDensity(),
                    5,
                    5,
                    paint
            );

        } else {
            crackingAnimation.draw(canvas);
            crackingAnimation.update();
        }
    }
}
