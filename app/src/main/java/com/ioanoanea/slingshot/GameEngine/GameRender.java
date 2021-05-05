package com.ioanoanea.slingshot.GameEngine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.ioanoanea.slingshot.GameObjects.Bullet;
import com.ioanoanea.slingshot.GameObjects.GameArena;
import com.ioanoanea.slingshot.GameObjects.Object;
import com.ioanoanea.slingshot.GameObjects.Obstacle;
import com.ioanoanea.slingshot.GameObjects.Sling;
import com.ioanoanea.slingshot.GameObjects.TargetObject;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.SoundManager;
import com.ioanoanea.slingshot.MathObjects.DistanceCalculator;
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
    private BulletManager bulletManager;
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
                    // set bullet position if there are still extra bullets
                    if (new BulletManager(getContext()).extraBulletsUnlocked()){
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
                        // if there are extra bullets remained and unlocked, shot
                        if (bulletManager.extraBulletsUnlocked()){
                            bullet.unlock();
                            // load shot sound
                            soundManager.loadSound(SoundManager.SHOT);
                            // Set bullet speed
                            bullet.setSpeed(0.9995);
                            // notify a bullet have been shot
                            onBulletShotListener.onShot();
                            bullets.add(bullet);
                            bulletManager.removeBullets(1);
                            // lock extra bullets if extra if no extra bullets left
                            if (bulletManager.getBullets() == 0){
                                bulletManager.lockExtraBullets();
                                bullet.setOnDestroyed(new Object.DestroyListener() {
                                    @Override
                                    public void onDestroyed() {
                                        onLastBulletDestroyedListener.onDestroyed();
                                    }
                                });
                            }
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
        bulletManager = new BulletManager(getContext());

        // set obstacles inside the arena
        for (i = 0; i < obstacles.size(); i++){
            double left;
            double right;
            double top;
            double bottom;
            if (Math.abs((obstacles.get(i).getRight() + obstacles.get(i).getLeft()) / 2 - Object.CENTER) < 0.01){
                left = (double) getWidth() / 2 / getDensity() - (double) obstacles.get(i).getLength() / 2;
                right = (double) getWidth() / 2 / getDensity() + (double) obstacles.get(i).getLength() / 2;
            } else {
                left = obstacles.get(i).getLeft();
                right = obstacles.get(i).getRight();
            }
            if (Math.abs((obstacles.get(i).getBottom() + obstacles.get(i).getTop()) / 2 - Object.CENTER) < 0.01){
                top = (double) getHeight() / 2 / getDensity() - (double) obstacles.get(i).getHeight() / 2;
                bottom = (double) getHeight() / 2 / getDensity() + (double) obstacles.get(i).getHeight() / 2;
            } else {
                top = obstacles.get(i).getTop();
                bottom = obstacles.get(i).getBottom();
            }
            Obstacle obstacle = new Obstacle(getContext(), left, right, top, bottom, getWidth(), getHeight());
            obstacles.set(i, obstacle);
        }

        // set target objects inside arena
        for (i = 0; i < targetObjects.size(); i++){
            // if object was not destroyed
            if (!targetObjects.get(i).isDestroyed()){
                double positionX;
                double positionY;
                if (targetObjects.get(i).getPositionX() == Obstacle.CENTER){
                    positionX = (double) getWidth() / 2 / getDensity();
                } else {
                    positionX = targetObjects.get(i).getPositionX();
                }
                if (targetObjects.get(i).getPositionY() == Obstacle.CENTER){
                    positionY = (double)getHeight() / 2 / getDensity();
                } else {
                    positionY = targetObjects.get(i).getPositionY();
                }
                TargetObject targetObject = new TargetObject(getContext(), positionX, positionY, getWidth(), getHeight());
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
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        bulletManager.lockExtraBullets();
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
        return (float) getContext().getResources().getDisplayMetrics().widthPixels / 400;
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
