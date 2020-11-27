package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

import java.util.ArrayList;

public class Bullet {

    private final Context context;
    private final double screenWidth;
    private final double screenHeight;
    private double positionX = -100;
    private double positionY = -100;
    private double distanceToNextPositionX = 0;
    private double distanceToNextPositionY = 0;
    private double decreaseSpeed;
    private ArrayList<Obstacle> obstacles;

    public Bullet(Context context, double screenWidth, double screenHeight, ArrayList<Obstacle> obstacles){
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.obstacles = obstacles;
    }


    /**
     * Returns display density
     * @return (float) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Check if bullet is set (bullet is set if his position is inside the screen)
     * @return true if bullet is set, false otherwise
     */
    public boolean isSet(){
        return positionX > 0 && positionY > 0;
    }


    /**
     * Set bullet position
     * @param positionX (double) position x
     * @param positionY (double) position Y
     */
    public void setPosition(double positionX, double positionY){

        this.positionX = positionX;
        this.positionY = positionY;

        // if position next X is left to game arena left side, set position X to game arena left side and change direction
        if (positionX * getDensity() < 19 * getDensity() + 29){
            this.positionX = 19 + 29 / getDensity();
            setDistanceToNextPositionX(-distanceToNextPositionX);
            //setDistanceToNextPositionY(-distanceToNextPositionY);
        }
        // if position next Y is upper than game arena up side, set position Y to game arena up side and change direction
        if (positionY * getDensity() < 19 * getDensity() + 29){
            this.positionY = 19 + 29 / getDensity();
            setDistanceToNextPositionY(-distanceToNextPositionY);
        }
        // if position next X is right to game arena right side, set position X to game arena right side change direction
        if(positionX * getDensity() > screenWidth - (19 * getDensity() + 29)){
            this.positionX = screenWidth / getDensity() - (19 + 29 / getDensity());
            setDistanceToNextPositionX(-distanceToNextPositionX);
        }
        // if position next Y is lower than game arena bottom side, set position Y to game arena bottom side change direction
        if (positionY * getDensity() > screenHeight - (19 * getDensity() + 29)) {
            this.positionY = screenHeight / getDensity() - (19 + 29 / getDensity());
            setDistanceToNextPositionY(-distanceToNextPositionY);
        }

        // check if ball intersects any obstacle
        for (Obstacle obstacle : obstacles){
            // if intersects an obstacle set ball position outside of the obstacle and change direction
            if(intersectsObstacle(positionX, positionY, obstacle)){
                if (distanceToNextPositionY < 0){
                    this.positionY = obstacle.getPositionY() + 20 + 29 / getDensity();
                } else {
                    this.positionY = obstacle.getPositionY() - 29 / getDensity();
                }
                setDistanceToNextPositionY(-distanceToNextPositionY);
            }
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
     * Set decreasing speed
     * @param decreaseSpeed (double) decrease speed
     */
    public void setDecreaseSpeed(double decreaseSpeed) {
        this.decreaseSpeed = decreaseSpeed;
    }

    /**
     * Verify if point of position (x, y) intersects obstacle
     * @param x (double) position X
     * @param y (double) position Y
     * @param obstacle (Obstacle) obstacle
     * @return true if point intersect the obstacle
     */
    private boolean intersectsObstacle(double x, double y, Obstacle obstacle){
        return obstacle.getPositionX() < x && x < obstacle.getPositionX() + obstacle.getLength() &&
                obstacle.getPositionY() < y && y < obstacle.getPositionY() + 20;
    }

    /**
     * Move the bullet
     */
    public void move(){
            setPosition(
                    getPositionX() + distanceToNextPositionX,
                    getPositionY() + distanceToNextPositionY
            );

            // Decrease speed at every frame
            setDistanceToNextPositionX(distanceToNextPositionX * decreaseSpeed);
            setDistanceToNextPositionY(distanceToNextPositionY * decreaseSpeed);
            setDecreaseSpeed(decreaseSpeed - 0.0001);

            // Set distance to next position to 0 if it is too smaller (this will skip many useless calculations)
            // if distance to next position X is smaller than 0.001 set distance to next position X to 0
            if (Math.abs(distanceToNextPositionX) < 0.001){
                distanceToNextPositionX = 0;
            }
            // if distance to next position Y is smaller than 0.001 set distance to next position Y to 0
            if (Math.abs(distanceToNextPositionY) < 0.001){
                distanceToNextPositionY = 0;
            }

            // TODO: Calculate speed depending on sling stretching
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

    public void drawSpeed(Canvas canvas){
        String currentSpeed = Double.toString(Math.abs(distanceToNextPositionX));
        Paint paint = new Paint();
        int color = context.getResources().getColor(R.color.teal);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Speed: " + currentSpeed, 100, 150, paint);
    }

}
