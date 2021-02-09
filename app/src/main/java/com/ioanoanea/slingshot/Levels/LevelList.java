package com.ioanoanea.slingshot.Levels;

import android.content.Context;

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
    }

    /**
     * Returns level list
     * @return (ArrayList) list of levels
     */
    public ArrayList<Level> getLevels() {
        return levels;
    }
}
