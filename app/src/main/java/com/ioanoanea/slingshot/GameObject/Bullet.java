package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

import java.util.ArrayList;

public class Bullet extends Object {

    private final double screenWidth;
    private final double screenHeight;
    private double positionX = -100;
    private double positionY = -100;
    private final double radius;
    private double distanceToNextPositionX = 0;
    private double distanceToNextPositionY = 0;
    private double speed;
    private boolean locked = true;
    private final ArrayList<Obstacle> obstacles;

    public Bullet(Context context, double screenWidth, double screenHeight, ArrayList<Obstacle> obstacles){
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.obstacles = obstacles;
        this.radius = 29;
    }

    /**
     * Unlock bullet
     */
    public void unlock(){
        this.locked = false;
    }

    /**
     * Check if bullet is set (bullet is set if his position is inside the screen)
     * @return true if bullet is set, false otherwise
     */
    public boolean isSet(){
        return !locked;
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
        if (positionX * getDensity() <  radius){
           this.positionX = radius / getDensity();
            setDistanceToNextPositionX(-distanceToNextPositionX);
            //setDistanceToNextPositionY(-distanceToNextPositionY);
        }
        // if position next Y is upper than game arena up side, set position Y to game arena up side and change direction
        if (positionY * getDensity() < radius){
            this.positionY = radius / getDensity();
            setDistanceToNextPositionY(-distanceToNextPositionY);
        }
        // if position next X is right to game arena right side, set position X to game arena right side change direction
        if(positionX * getDensity() > screenWidth - radius){
            this.positionX = screenWidth / getDensity() - radius / getDensity();
            setDistanceToNextPositionX(-distanceToNextPositionX);
        }
        // if next position Y is lower than game arena bottom side, set position Y to game arena bottom side and change direction
        if (positionY * getDensity() > screenHeight - radius) {
            this.positionY = screenHeight / getDensity() - radius / getDensity();
            setDistanceToNextPositionY(-distanceToNextPositionY);
        }



        // check if bullet intersects any obstacle
        for (Obstacle obstacle : obstacles){
            // if bullet intersects top side of the obstacle
            if (positionY - distanceToNextPositionY < obstacle.getTop() && obstacle.intersectsSouthArea(positionX, positionY)){
                this.positionY = obstacle.getTop() - radius / getDensity();
                setDistanceToNextPositionY(-distanceToNextPositionY);
            }
            // if bullet intersects bottom side of the obstacle
            if (positionY - distanceToNextPositionY > obstacle.getBottom() && obstacle.intersectNorthArea(positionX, positionY)){
                this.positionY = obstacle.getBottom() + radius / getDensity();
                setDistanceToNextPositionY(-distanceToNextPositionY);
            }
            // if bullet intersects left side of the obstacle
            if (positionX - distanceToNextPositionX < obstacle.getLeft() && obstacle.intersectsEastArea(positionX, positionY)){
                this.positionX = obstacle.getLeft() - radius / getDensity();
                setDistanceToNextPositionX(-distanceToNextPositionX);
            }
            // if bullet intersects right side of the obstacle
            if (positionX - distanceToNextPositionX > obstacle.getRight() && obstacle.intersectsWestArea(positionX, positionY)){
                this.positionX = obstacle.getRight() + radius / getDensity();
                setDistanceToNextPositionX(-distanceToNextPositionX);
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
     * @param speed (double) decrease speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isMoving(){
        return distanceToNextPositionX != 0 || distanceToNextPositionY != 0;
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
            setDistanceToNextPositionX(distanceToNextPositionX * speed);
            setDistanceToNextPositionY(distanceToNextPositionY * speed);
            setSpeed(speed - Math.pow(2, speed) / 50000);

            // Set distance to next position to 0 if it is too smaller (this will skip many useless calculations)
            // if distance to next position X is smaller than 0.001 set distance to next position X to 0
            if (Math.abs(distanceToNextPositionX) < 0.001){
                distanceToNextPositionX = 0;
            }
            // if distance to next position Y is smaller than 0.001 set distance to next position Y to 0
            if (Math.abs(distanceToNextPositionY) < 0.001){
                distanceToNextPositionY = 0;
            }
    }

    /**
     * Draw bullet
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.magenta));

        canvas.drawCircle(
                (float) getPositionX() * getDensity(),
                (float) getPositionY() * getDensity(),
                30,
                paint
        );

    }

}
