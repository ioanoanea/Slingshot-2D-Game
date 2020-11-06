package com.ioanoanea.slingshot.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ioanoanea.slingshot.GameObject.GameArena;
import com.ioanoanea.slingshot.GameObject.Sling;
import com.ioanoanea.slingshot.R;


public class GameRender extends SurfaceView implements SurfaceHolder.Callback {


    private GameLoop gameLoop;
    private GameArena gameArena;
    private Sling sling;

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
        sling = new Sling(
                getContext(),
                (getWidth() / getDensity()) / 2,
                ((getHeight() / getDensity()) / 4 * 3),
                (getWidth() / getDensity()) / 2,
                (getHeight() / getDensity()) / 4 * 3
        );

        gameLoop.startLoop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle touch events actions
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // If sling is not lock redraw sling's cord
                if (!sling.isLocked())
                    sling.setCordPosition(event.getX() / getDensity(), event.getY() / getDensity());
                // If touch event intersect the sling, unlock the sling
                if (sling.intersect(event.getX() / getDensity(), event.getY() / getDensity()))
                    sling.unlock();
                return true;
            case MotionEvent.ACTION_UP:
                sling.lock();
                sling.reset();
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
        drawUPS(canvas);
        drawFPS(canvas);
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
     * @param canvas (canvas) canvas value
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
     * @param canvas (canvas) canvas value
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
        //sling.setCordPosition(sling.getCordPositionX() + 1, sling.getCordPositionY());
    }

}
