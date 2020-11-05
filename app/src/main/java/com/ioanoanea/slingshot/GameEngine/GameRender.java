package com.ioanoanea.slingshot.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ioanoanea.slingshot.GameObject.GameArena;
import com.ioanoanea.slingshot.R;


public class GameRender extends SurfaceView implements SurfaceHolder.Callback {


    private GameLoop gameLoop;
    private GameArena gameArena;

    public GameRender(Context context){
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        gameArena = new GameArena(getContext());

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
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
        gameArena.draw(canvas, getWidth(), getHeight());
        //gameArena.drawObstacle(canvas, 0, 450, 120);
        drawUPS(canvas);
        drawFPS(canvas);
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
    }

}
