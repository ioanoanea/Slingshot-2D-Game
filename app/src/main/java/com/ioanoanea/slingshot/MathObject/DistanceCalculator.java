package com.ioanoanea.slingshot.MathObject;

import android.graphics.Point;

public class DistanceCalculator {


    public double getDistanceX(Point pointA, Point pointB, double speed){
        double speedX;

        if (pointA.x - pointB.x > 0){
            speedX = -speed;
        } else speedX = speed;

        double distance = Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
        return (Math.abs(pointA.x - pointB.x) / (distance / speedX));
    }
    
    public double getDistanceY(Point pointA, Point pointB, double speed){
        double speedY;

        if (pointA.y - pointB.y > 0){
            speedY = - speed;
        } else speedY = speed;

        double distance = Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
        return (Math.abs(pointA.y - pointB.y) / (distance / speedY));
    }

}
