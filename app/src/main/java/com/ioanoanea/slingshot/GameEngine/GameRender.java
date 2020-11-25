package com.ioanoanea.slingshot.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ioanoanea.slingshot.GameObject.Bullet;
import com.ioanoanea.slingshot.GameObject.GameArena;
import com.ioanoanea.slingshot.GameObject.Sling;
import com.ioanoanea.slingshot.MathObject.DistanceCalculator;
import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;


public class GameRender extends SurfaceView implements SurfaceHolder.Callback {


    private GameLoop gameLoop;
    private GameArena gameArena;
    private Sling sling;
    private Bullet bullet;

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
        gameArena = new GameArena(getContext());
        sling = new Sling(getContext(), getWidth(), getHeight());
        bullet = new Bullet(getContext(), -100, -100);

        gameLoop.startLoop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        // Handle touch events actions
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // If sling is not lock redraw sling's cord
                if (!sling.isLocked()){
                    sling.setCordPosition(event.getX() / getDensity(), event.getY() / getDensity());
                    bullet.setPosition(event.getX() / getDensity(), event.getY() / getDensity());

                    // Set sling next cord position X
                    sling.setDistanceToNextCordPositionX(
                            distanceCalculator.getDistanceToNextPositionX(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    10
                            )
                    );

                    // Set sling next cord position Y
                    sling.setDistanceToNextCordPositionY(
                            distanceCalculator.getDistanceToNextPositionY(
                                    new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                    new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                    10
                            )
                    );
                }
                // If touch event intersect the sling, unlock the sling
                if (sling.intersect(event.getX() / getDensity(), event.getY() / getDensity())) {
                    sling.unlock();
                    bullet = new Bullet(getContext(), event.getX() / getDensity(), event.getY() / getDensity());
                }
                return true;
            case MotionEvent.ACTION_UP:
                sling.lock();
                bullet.unlock();

                // Set sling next cord position X
                sling.setDistanceToNextCordPositionX(
                        distanceCalculator.getDistanceToNextPositionX(
                                new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                10
                        )
                );

                // Set sling next cord position Y
                sling.setDistanceToNextCordPositionY(
                        distanceCalculator.getDistanceToNextPositionY(
                                new Point((int) sling.getCordPositionX(), (int) sling.getCordPositionY()),
                                new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                10
                        )
                );

                // Set bullet distance to next position X
                bullet.setDistanceToNextPositionX(
                        distanceCalculator.getDistanceToNextPositionX(
                                new Point((int) bullet.getPositionX(), (int) bullet.getPositionY()),
                                new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                10
                        )
                );

                // Set bullet distance to next position Y
                bullet.setDistanceToNextPositionY(
                        distanceCalculator.getDistanceToNextPositionY(
                                new Point((int) bullet.getPositionX(), (int) bullet.getPositionY()),
                                new Point((int) sling.getPositionX(), (int) sling.getPositionY()),
                                10
                        )
                );
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // here game elements are drawing
        gameArena.draw(canvas, getWidth(), getHeight());
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
        if(sling.isLocked() && sling.isStretched()){
            sling.reset();
        }

        if(!bullet.isLocked()){
            bullet.move();
        }
    }

}
