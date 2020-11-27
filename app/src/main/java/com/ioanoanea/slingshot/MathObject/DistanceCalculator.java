package com.ioanoanea.slingshot.MathObject;

import android.graphics.Point;

public class DistanceCalculator {


    /**
     * Return distance between two points A and B
     * @param pointA (Point) point A
     * @param pointB (Point) point B
     * @return distance between point a and point B
     */
    public double getDistance(Point pointA, Point pointB){
        return Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
    }

    /**
     * Calculate distance between point A position x and point A next position X
     * Note: next position X is calculated on the direction from A - B
     * @param pointA (Point) point A
     * @param pointB (Point) point B
     * @param speed (double) Pixels at one move
     * @return (double) distance to next position X
     */
    public double getDistanceToNextPositionX(Point pointA, Point pointB, double speed){
        // Determine speed X direction
        double speedX;
        if (pointA.x - pointB.x > 0){
            speedX = -speed;
        } else speedX = speed;

        // get distance between point A and point B
        double distance = Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));

        return (Math.abs(pointA.x - pointB.x) / (distance / speedX));
    }


    /**
     * Calculate distance between point A position Y and point A next position Y
     * Note: next position X is calculated on the direction from A - B
     * @param pointA (Point) point A
     * @param pointB (Point) point B
     * @param speed (double) Pixels at one move
     * @return (double) distance to next position Y
     */
    public double getDistanceToNextPositionY(Point pointA, Point pointB, double speed){
        // Determine speed Y
        double speedY;
        if (pointA.y - pointB.y > 0){
            speedY = - speed;
        } else speedY = speed;

        // Get distance between point A and point B
        double distance = Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));

        return (Math.abs(pointA.y - pointB.y) / (distance / speedY));
    }

}
