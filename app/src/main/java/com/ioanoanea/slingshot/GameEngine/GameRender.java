package com.ioanoanea.slingshot.GameEngine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ioanoanea.slingshot.GameObject.Bullet;
import com.ioanoanea.slingshot.GameObject.GameArena;
import com.ioanoanea.slingshot.GameObject.Obstacle;
import com.ioanoanea.slingshot.GameObject.Sling;
import com.ioanoanea.slingshot.GameObject.TargetObject;
import com.ioanoanea.slingshot.MathObject.DistanceCalculator;
import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

import java.util.ArrayList;


public class GameRender extends SurfaceView implements SurfaceHolder.Callback {


    private GameLoop gameLoop;
    private GameArena gameArena;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<TargetObject> targetObjects;
    private Sling sling;
    private Bullet bullet;
    private double SPEED = 0;

    public GameRender(Context context){
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop = new GameLoop(this, surfaceHolder);
        gameLoop.startLoop();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        // Handle touch events actions
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // If sling is not lock redraw sling's cord
                if (!sling.isLocked()){
                    // Set sling cord position and bullet position
                    sling.setCordPosition(event.getX() / getDensity(), event.getY() / getDensity());
                    bullet.setPosition(sling.getCordPositionX(), sling.getCordPositionY());

                    // Determine speed based on sling stretching
                    SPEED = getSpeed(sling.getCordDistance());

                    // Set sling next cord position X
                    sling.setDistanceToNextCordPositionX(
                            distanceCalculator.getDistanceToNextPositionX(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );

                    // Set sling next cord position Y
                    sling.setDistanceToNextCordPositionY(
                            distanceCalculator.getDistanceToNextPositionY(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );
                }

                // If touch event intersect the sling, unlock the sling
                if (sling.intersect(event.getX() / getDensity(), event.getY() / getDensity())) {
                    sling.unlock();
                    bullet = new Bullet(getContext(), getWidth(), getHeight(), obstacles);
                    bullet.setPosition(sling.getCordPositionX(), sling.getCordPositionY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (sling.isStretched()){
                    // if sling is unlocked, unlock bullet and lock sling
                    if (!sling.isLocked()){
                        sling.lock();
                        // Set value of
                        bullet.setDecreaseSpeed(0.9995);
                        bullet.addTrace();
                    }

                    // Set sling next cord position X
                    sling.setDistanceToNextCordPositionX(
                            distanceCalculator.getDistanceToNextPositionX(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );

                    // Set sling next cord position Y
                    sling.setDistanceToNextCordPositionY(
                            distanceCalculator.getDistanceToNextPositionY(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );

                    // Set bullet distance to next position X
                    bullet.setDistanceToNextPositionX(
                            distanceCalculator.getDistanceToNextPositionX(
                                    new Point((int) bullet.getPositionX(), (int) bullet.getPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );

                    // Set bullet distance to next position Y
                    bullet.setDistanceToNextPositionY(
                            distanceCalculator.getDistanceToNextPositionY(
                                    new Point((int) bullet.getPositionX(), (int) bullet.getPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    SPEED
                            )
                    );
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // initialize game elements
        gameArena = new GameArena(getContext(), getWidth(), getHeight());
        setObstacles();
        setTargetObjects();
        sling = new Sling(getContext(), getWidth(), getHeight());
        bullet = new Bullet(getContext(), getWidth(), getHeight(), obstacles);
        sling = new Sling(getContext(), getWidth() ,getHeight());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // here game elements are drawing
        gameArena.draw(canvas);
        for(Obstacle obstacle: obstacles){
            obstacle.draw(canvas);
        }

        for(TargetObject targetObject: targetObjects){
            targetObject.draw(canvas);
        }
        sling.draw(canvas);
        bullet.draw(canvas);

        //drawUPS(canvas);
        //drawFPS(canvas);
    }

    /**
     * Returns display density
     * @return (float) density
     */
    private float getDensity(){
        return getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * Determine the speed based on sling cord distance
     * @param distance (double) distance
     * @return estimated speed
     */
    private double getSpeed(double distance){
        double maxSpeed = 15;
        double minSpeed = 0.15;
        return Math.min((Math.max(Math.pow(sling.getCordDistance() / 30, 1.8), minSpeed)), maxSpeed);
    }

    /**
     * Set obstacle list
     */
    public void setObstacles(){
        obstacles = new ArrayList<>();
        Obstacle obstacle = new Obstacle(getContext(), 280, 400, 120);
        obstacles.add(obstacle);
    }

    public void setTargetObjects(){
        targetObjects = new ArrayList<>();
        TargetObject targetObject = new TargetObject(getContext(), 300, 50);
        targetObjects.add(targetObject);
    }

    /**
     * draw a text with value of updates per second on screen
     * @param canvas (Canvas) canvas value
     */
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = getResources().getColor(R.color.teal);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS,100, 150, paint);
    }

    /**
     * draw a text with value frames per second on screen
     * @param canvas (Canvas) canvas value
     */
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = getResources().getColor(R.color.teal);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update(){
        // update
        if (sling.isLocked() && sling.isStretched()){
            sling.reset();
        }

        if (bullet.isSet()){
            bullet.move();
        }

        for (TargetObject targetObject: targetObjects){
            if (targetObject.intersects(bullet.getPositionX(), bullet.getPositionY())){
                targetObject.destroy();
            }
        }
    }

}
