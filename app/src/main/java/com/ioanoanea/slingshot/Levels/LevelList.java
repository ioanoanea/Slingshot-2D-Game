package com.ioanoanea.slingshot.Levels;

import android.content.Context;

import com.ioanoanea.slingshot.GameObjects.Object;
import com.ioanoanea.slingshot.GameObjects.Obstacle;
import com.ioanoanea.slingshot.Manager.LevelManager;

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
        level1.setBonusCoins(3);
        levels.add(level1);

        // level 2
        Level level2 = new Level(context);
        level2.addTargetObject(100, 200);
        level2.addTargetObject(350, 50);
        level2.setBullets(3);
        level2.setBonusCoins(3);
        levels.add(level2);

        // level 3
        Level level3 = new Level(context);
        level3.addTargetObject(360, 50);
        level3.addObstacle(340, 200, 140);
        level3.setBullets(3);
        level3.setBonusCoins(3);
        levels.add(level3);

        // level 4
        Level level4 = new Level(context);
        level4.addTargetObject(500, 100);
        level4.addTargetObject(50, 250);
        level4.addObstacle(80, 300, 120);
        level4.setBullets(3);
        level4.setBonusCoins(3);
        levels.add(level4);

        // level 5
        Level level5 = new Level(context);
        level5.addTargetObject(60, 700);
        level5.addTargetObject(300, 100);
        level5.addObstacle(300, 200, 120);
        level5.setBullets(3);
        level5.setBonusCoins(3);
        levels.add(level5);

        // level 6
        Level level6 = new Level(context);
        level6.addTargetObject(50, 200);
        level6.addTargetObject(-50, 200);
        level6.addObstacle(Object.LEFT, 250, 100);
        level6.addObstacle(Object.RIGHT, 250, 100);
        level6.setBullets(3);
        level6.setBonusCoins(4);
        levels.add(level6);

        // level 7
        Level level7 = new Level(context);
        level7.addObstacle(Obstacle.CENTER, 350, 120);
        level7.addObstacle(new Obstacle(context, Object.RIGHT, Object.RIGHT + 20, 90, 370));
        level7.addTargetObject(Object.CENTER, 300);
        level7.setBullets(3);
        level7.setBonusCoins(3);
        levels.add(level7);

        // level 8
        Level level8 = new Level(context);
        level8.addObstacle(Obstacle.LEFT, 350, 120);
        level8.addObstacle(Obstacle.LEFT, 450, 120);
        level8.addObstacle(new Obstacle(context, Obstacle.RIGHT, Obstacle.RIGHT + 20, 350, 600));
        level8.addTargetObject(20, 300);
        level8.addTargetObject(20, 420);
        level8.setBullets(4);
        level8.setBonusCoins(3);
        levels.add(level8);

        // level 9
        Level level9 = new Level(context);
        level9.addObstacle(Obstacle.LEFT, 100, 100);
        level9.addObstacle(new Obstacle(context, 0, 140, 220, 240));
        level9.addTargetObject(50, 150);
        level9.setBullets(2);
        level9.setBonusCoins(4);
        levels.add(level9);

        // level 10
        Level level10 = new Level(context);
        level10.addObstacle(new Obstacle(context, 0, 175, 300, 320));
        level10.addObstacle(new Obstacle(context, 225, 400, 300, 320));
        level10.addTargetObject(100, 270);
        level10.addTargetObject(300, 270);
        level10.setBullets(3);
        level10.setBonusCoins(4);
        levels.add(level10);

        // level 11
        Level level11 = new Level(context);
        level11.addObstacle(new Obstacle(context, 0, 50, 120, 140));
        level11.addObstacle(new Obstacle(context, 100, 400, 120, 140));
        level11.addTargetObject(40, 80);
        level11.addObstacle(new Obstacle(context, 0, 125, 260, 280));
        level11.addObstacle(new Obstacle(context, 205, 400, 260, 280));
        level11.setBullets(2);
        level11.setBonusCoins(4);
        levels.add(level11);

        // level 12
        Level level12 = new Level(context);
        level12.addObstacle(new Obstacle(context, 0, 50, 120, 140));
        level12.addObstacle(new Obstacle(context, 100, 400, 120, 140));
        level12.addTargetObject(40, 80);
        level12.addObstacle(new Obstacle(context, 0, 125, 260, 280));
        level12.addObstacle(new Obstacle(context, 205, 400, 260, 280));
        level12.addTargetObject(30, 230);
        level12.setBullets(4);
        level12.setBonusCoins(4);
        levels.add(level12);

        // level 13
        Level level13 = new Level(context);
        level13.addObstacle(new Obstacle(context, 0, 170, 100, 120));
        level13.addObstacle(new Obstacle(context, 0, 20, 100, 320));
        level13.addObstacle(new Obstacle(context, 0, 120, 320, 340));
        level13.addObstacle(new Obstacle(context, 80, 400, 210, 230));
        level13.addTargetObject(200, 170);
        level13.setBullets(4);
        level13.setBonusCoins(4);
        levels.add(level13);

        // level 14
        Level level14 = new Level(context);
        level14.addObstacle(new Obstacle(context, 0, 170, 100, 120));
        level14.addObstacle(new Obstacle(context, 0, 20, 100, 320));
        level14.addObstacle(new Obstacle(context, 0, 120, 320, 340));
        level14.addObstacle(new Obstacle(context, 80, 400, 210, 230));
        level14.addTargetObject(200, 170);
        level14.addTargetObject(50, 145);
        level14.setBonusCoins(5);
        level14.setBullets(4);
        levels.add(level14);

        // level 15
        Level level15 = new Level(context);
        level15.addObstacle(new Obstacle(context, 0, 50, 170, 190));
        level15.addObstacle(new Obstacle(context, 75, 120, 170, 190));
        level15.addObstacle(new Obstacle(context, 0, 20, 190, 300));
        level15.addObstacle(new Obstacle(context, 120, 140, 170, 300));
        level15.addObstacle(new Obstacle(context, 0, 140, 300, 320));
        level15.addTargetObject(70, 250);
        level15.setBullets(2);
        level15.setBonusCoins(4);
        levels.add(level15);

        // level 16
        Level level16 = new Level(context);
        level16.addObstacle(new Obstacle(context, 0, 50, 170, 190));
        level16.addObstacle(new Obstacle(context, 75, 120, 170, 190));
        level16.addObstacle(new Obstacle(context, 0, 20, 190, 300));
        level16.addObstacle(new Obstacle(context, 120, 140, 170, 300));
        level16.addObstacle(new Obstacle(context, 0, 140, 300, 320));
        level16.addTargetObject(70, 250);
        level16.addObstacle(new Obstacle(context, 280, 320, 210, 230));
        level16.addObstacle(new Obstacle(context, 345, 400, 210, 230));
        level16.addObstacle(new Obstacle(context, 260, 280, 210, 360));
        level16.addObstacle(new Obstacle(context, 380, 400, 230,340));
        level16.addObstacle(new Obstacle(context, 280, 400, 340, 360));
        level16.addTargetObject(320, 300);
        level16.setBullets(3);
        level16.setBonusCoins(5);
        levels.add(level16);

        // level 17
        Level level17 = new Level(context);
        level17.addObstacle(new Obstacle(context, 0, 50, 170, 190));
        level17.addObstacle(new Obstacle(context, 75, 120, 170, 190));
        level17.addObstacle(new Obstacle(context, 0, 20, 190, 400));
        level17.addObstacle(new Obstacle(context, 120, 140, 170, 400));
        level17.addObstacle(new Obstacle(context, 40, 120, 280, 300));
        level17.addObstacle(new Obstacle(context, 0, 140, 400, 420));
        level17.addTargetObject(70, 220);
        level17.addTargetObject(60, 360);
        level17.setBullets(3);
        level17.setBonusCoins(5);
        levels.add(level17);

        // level 18
        Level level18 = new Level(context);
        level18.addObstacle(new Obstacle(context, 0, 400, 100, 120));
        level18.addObstacle(new Obstacle(context, 380, 400, 120, 240));
        level18.addObstacle(new Obstacle(context, 0, 240, 240, 260));
        level18.addObstacle(new Obstacle(context, 300, 400, 240, 260));
        level18.addObstacle(new Obstacle(context, 0, 20, 260, 420));
        level18.addObstacle(new Obstacle(context, 100, 400, 380, 400));
        level18.addTargetObject(260, 350);
        level18.addTargetObject(100, 200);
        level18.setBullets(4);
        level18.setBonusCoins(5);
        levels.add(level18);

        // level 19
        Level level19 = new Level(context);
        level19.addObstacle(new Obstacle(context, 0, 90, 100, 120));
        level19.addObstacle(new Obstacle(context, 140, 400, 100, 120));
        level19.addObstacle(new Obstacle(context, 380, 400, 120, 240));
        level19.addObstacle(new Obstacle(context, 0, 240, 240, 260));
        level19.addObstacle(new Obstacle(context, 300, 400, 240, 260));
        level19.addObstacle(new Obstacle(context, 0, 20, 260, 420));
        level19.addObstacle(new Obstacle(context, 100, 400, 380, 400));
        level19.addTargetObject(260, 350);
        level19.addTargetObject(100, 200);
        level19.addTargetObject(115, 75);
        level19.setBullets(5);
        level19.setBonusCoins(5);
        levels.add(level19);

        // level 20
        Level level20 = new Level(context);
        level20.addObstacle(new Obstacle(context, 0, 80, 140, 160));
        level20.addObstacle(new Obstacle(context, 20, 110, 240, 260));
        level20.addObstacle(new Obstacle(context, 30, 160, 340, 360));
        level20.addObstacle(new Obstacle(context, 60, 200, 60, 80));
        level20.addObstacle(new Obstacle(context, 120, 260, 160, 180));
        level20.addObstacle(new Obstacle(context, 160, 300, 260, 280));
        level20.addTargetObject(40, 115);
        level20.addTargetObject(80, 215);
        level20.addTargetObject(120, 315);
        level20.setBullets(5);
        level20.setBonusCoins(5);
        levels.add(level20);
    }

    /**
     * Returns level list
     * @return (ArrayList) list of levels
     */
    public ArrayList<Level> getLevels() {
        return levels;
    }
}
