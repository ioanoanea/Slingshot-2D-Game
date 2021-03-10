package com.ioanoanea.slingshot.Levels;

import android.content.Context;

import com.ioanoanea.slingshot.GameObjects.Obstacle;
import com.ioanoanea.slingshot.GameObjects.TargetObject;

import java.util.ArrayList;

public class Level {

    private final Context context;
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<TargetObject> targetObjects;
    private int bullets;

    public Level(Context context){
        this.context = context;
        obstacles = new ArrayList<>();
        targetObjects = new ArrayList<>();
    }

    /**
     * Add obstacles
     * @param positionX (double) position x
     * @param positionY (double) position Y
     * @param length (double) length
     */
    public void addObstacle(double positionX, double positionY, double length){
        Obstacle obstacle = new Obstacle(context, positionX, positionY, length);
        obstacles.add(obstacle);
    }

    /**
     * Add target object
     * @param positionX (double) position x
     * @param positionY (double) position y
     */
    public void addTargetObject(double positionX, double positionY){
        TargetObject targetObject = new TargetObject(context, positionX, positionY);
        targetObjects.add(targetObject);
    }

    /**
     * Set bullets numbers
     * @param bullets (int) bullets number
     */
    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    /**
     * Returns obstacles list
     * @return (ArrayList) a list with all obstacles
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Returns all target objects
     * @return (ArrayList) a list with all target objects
     */
    public ArrayList<TargetObject> getTargetObjects() {
        return targetObjects;
    }

    /**
     * Returns bullets number
     * @return (int) bullets number
     */
    public int getBullets() {
        return bullets;
    }
}
