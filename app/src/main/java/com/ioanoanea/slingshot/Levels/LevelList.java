package com.ioanoanea.slingshot.Levels;

import android.content.Context;

import com.ioanoanea.slingshot.GameObjects.Object;
import com.ioanoanea.slingshot.GameObjects.Obstacle;

import java.util.ArrayList;

public class LevelList {

    private final Context context;
    private final ArrayList<Level> levels;

    public LevelList(Context context){
        this.context = context;
        levels = new ArrayList<>();
        setLevels();
    }

    /**
     * All game levels are initialized here
     */
    public void setLevels(){
        // level 1
        Level level1 = new Level(context);
        level1.addTargetObject(350, 150);
        level1.setBullets(3);
        levels.add(level1);

        // level 2
        Level level2 = new Level(context);
        level2.addTargetObject(100, 200);
        level2.addTargetObject(350, 50);
        level2.setBullets(3);
        levels.add(level2);

        // level 3
        Level level3 = new Level(context);
        level3.addTargetObject(360, 50);
        level3.addObstacle(340, 200, 140);
        level3.setBullets(3);
        levels.add(level3);

        // level 4
        Level level4 = new Level(context);
        level4.addTargetObject(500, 100);
        level4.addTargetObject(50, 250);
        level4.addObstacle(80, 300, 120);
        level4.setBullets(3);
        levels.add(level4);

        // level 5
        Level level5 = new Level(context);
        level5.addTargetObject(60, 700);
        level5.addTargetObject(300, 100);
        level5.addObstacle(300, 200, 120);
        level5.setBullets(3);
        levels.add(level5);

        // level 6
        Level level6 = new Level(context);
        level6.addTargetObject(50, 200);
        level6.addTargetObject(-50, 200);
        level6.addObstacle(Object.LEFT, 250, 100);
        level6.addObstacle(Object.RIGHT, 250, 100);
        level6.setBullets(3);
        level6.setBonusCoins(6);
        levels.add(level6);

        // level 7
        Level level7 = new Level(context);
        level7.addObstacle(Obstacle.CENTER, 350, 120);
        level7.addObstacle(new Obstacle(context, Object.RIGHT, Object.RIGHT + 20, 90, 370));
        level7.addTargetObject(Object.CENTER, 300);
        level7.setBullets(3);
        levels.add(level7);

        // level 8
        Level level8 = new Level(context);
        level8.addObstacle(Obstacle.LEFT, 350, 120);
        level8.addObstacle(Obstacle.LEFT, 450, 120);
        level8.addObstacle(new Obstacle(context, Obstacle.RIGHT, Obstacle.RIGHT + 20, 350, 600));
        level8.addTargetObject(20, 300);
        level8.addTargetObject(20, 420);
        level8.setBullets(4);
        level8.setBonusCoins(6);
        levels.add(level8);
    }

    /**
     * Returns level list
     * @return (ArrayList) list of levels
     */
    public ArrayList<Level> getLevels() {
        return levels;
    }
}
