package com.ioanoanea.slingshot.GameEngine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.TypedArrayUtils;

import com.ioanoanea.slingshot.GameObject.Bullet;
import com.ioanoanea.slingshot.GameObject.GameArena;
import com.ioanoanea.slingshot.GameObject.Object;
import com.ioanoanea.slingshot.GameObject.Obstacle;
import com.ioanoanea.slingshot.GameObject.Sling;
import com.ioanoanea.slingshot.GameObject.TargetObject;
import com.ioanoanea.slingshot.Manager.SoundManager;
import com.ioanoanea.slingshot.MathObject.DistanceCalculator;
import com.ioanoanea.slingshot.R;

import java.util.ArrayList;


public class GameRender extends SurfaceView implements SurfaceHolder.Callback {


    private GameLoop gameLoop;
    private GameArena gameArena;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<TargetObject> targetObjects;
    private Sling sling;
    private Bullet bullet;
    private ArrayList<Bullet> bullets;
    private int bulletIndex = 0;
    private double SPEED = 0;
    private int destroyedBullets = 0;
    private int destroyedTargetObjects = 0;
    private SoundManager soundManager;
    private OnBulletShotListener onBulletShotListener;
    private OnLastBulletDestroyedListener onLastBulletDestroyedListener;
    private OnLastTargetObjectDestroyedListener onLastTargetObjectDestroyedListener;

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
        //onBulletDestroyedListener = null;
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
                    // Set sling cord position
                    sling.setCordPosition(event.getX() / getDensity(), event.getY() / getDensity());
                    // set bullet position if there are still bullets remained
                    if (bullets.size() < bulletIndex){
                        bullet.setPosition(sling.getCordPositionX(), sling.getCordPositionY());
                    }

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
                    // load stretch sound
                    soundManager.loadSound(SoundManager.STRETCH);
                    // initialize a new bullet
                    bullet = new Bullet(getContext(), getWidth(), getHeight(), obstacles);
                    // increase destroyed bullets on bullet destroyed
                    bullet.setOnDestroyed(new Object.DestroyListener() {
                        @Override
                        public void onDestroyed() {
                            destroyedBullets++;
                            // set onDestroyed() method if last bullet is destroyed
                            if (destroyedBullets == bulletIndex) {
                                onLastBulletDestroyedListener.onDestroyed();
                            }
                        }
                    });
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (sling.isStretched()){
                    // if sling is unlocked, unlock bullet and lock sling
                    if (!sling.isLocked()){
                        // lock sling and unlock bullet
                        sling.lock();
                        // if there any bullet remained, shot
                        if (bullets.size() < bulletIndex){
                            bullet.unlock();
                            // load shot sound
                            soundManager.loadSound(SoundManager.SHOT);
                            // Set bullet speed
                            bullet.setSpeed(0.9995);
                            // notify a bullet have been shot
                            onBulletShotListener.onShot();
                            bullets.add(bullet);
                        }
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
        sling = new Sling(getContext(), getWidth(), getHeight());
        soundManager = new SoundManager(getContext());
        // set target objects inside the arena
        bullet = new Bullet(getContext(), getWidth(), getHeight(), obstacles);
        for (i = 0; i < targetObjects.size(); i++){
            TargetObject targetObject = new TargetObject(getContext(), targetObjects.get(i).getPositionX(),
                    targetObjects.get(i).getPositionY(), getWidth(), getHeight());
            targetObject.setOnDestroyed(new Object.DestroyListener() {
                @Override
                public void onDestroyed() {
                    destroyedTargetObjects++;
                    if (destroyedTargetObjects == targetObjects.size()){
                        onLastTargetObjectDestroyedListener.onDestroyed();
                        gameLoop.pause();
                    }
                }
            });
            targetObjects.set(i, targetObject);
        }
        // set obstacles inside the arena
        for (i = 0; i < obstacles.size(); i++){
            Obstacle obstacle = new Obstacle(getContext(), obstacles.get(i).getLeft(), obstacles.get(i).getRight(),
                    obstacles.get(i).getTop(), obstacles.get(i).getBottom(), getWidth(), getHeight());
            obstacles.set(i, obstacle);
        }
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
        for (Bullet bullet: bullets){
            bullet.draw(canvas);
        }

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
        double maxSpeed = 10;
        double minSpeed = 0.15;
        return Math.min((Math.max(Math.pow(sling.getCordDistance() / 50, 1.8), minSpeed)), maxSpeed);
    }

    /**
     * Set obstacle list
     */
    public void setObstacles(ArrayList<Obstacle> obstacles){
        this.obstacles = obstacles;
    }

    /**
     * Set target objects list
     */
    public void setTargetObjects(ArrayList<TargetObject> targetObjects){
        this.targetObjects = targetObjects;
    }

    /**
     * Set bullets
     * @param nrBullets (int) number of bullets
     */
    public void setBullets(int nrBullets) {
        bullets = new ArrayList<>();
        this.bulletIndex = nrBullets;
    }

    /**
     * Set an action when a bullet is shot
     * @param listener bullet shot listener
     */
    public void setOnBulletShot(OnBulletShotListener listener){
        this.onBulletShotListener = listener;
    }

    /**
     * Set an action when last bullet is destroyed
     * @param listener last bullet destroyed listener
     */
    public void setOnLastBulletDestroyed(OnLastBulletDestroyedListener listener){
        this.onLastBulletDestroyedListener = listener;
    }

    /**
     * Set an action when last target object is destroyed
     * @param listener last target object destroyed listener
     */
    public void setOnLastTargetObjectDestroyed(OnLastTargetObjectDestroyedListener listener){
        this.onLastTargetObjectDestroyedListener = listener;
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

    /**
     * Update game elements
     */
    public void update(){
        // update sling
        if (sling.isLocked() && sling.isStretched()){
            sling.reset();
        }

        // update bullets
        for (Bullet bullet: bullets){
            if (bullet.isSet()){
                bullet.move();
            }
        }

        // check if a bullet intersects a target object
        for (TargetObject targetObject: targetObjects){
            for (Bullet bullet: bullets) {
                if (targetObject.intersects(bullet.getPositionX(), bullet.getPositionY())) {
                    targetObject.destroy();
                }
            }
        }
    }

    /**
     * Handle bullet shot event
     */
    public interface OnBulletShotListener {
        /**
         * Method called when a bullet is shot
         * Must override this method with code that will be executed when a bullet is shot
         */
        default void onShot(){
        }
    }

    /**
     * Handle last bullet destroyed event
     */
    public interface OnLastBulletDestroyedListener {
        /**
         * Method called when last bullet is destroyed
         * Must override this method with code that will be executed when last bullet is destroyed
         */
        default void onDestroyed(){
        }
    }

    /**
     * Handle last target object destroyed event
     */
    public interface OnLastTargetObjectDestroyedListener {
        /**
         * Method called when last target object is destroyed
         * Must override this method with code that will be executed when last target object is destroyed
         */
        default void onDestroyed(){
        }
    }


}
