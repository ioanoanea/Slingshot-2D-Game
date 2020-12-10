package com.ioanoanea.slingshot.GameEngine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 *
 */
public class GameLoop extends Thread {


    private final GameRender gameRender;
    private final SurfaceHolder surfaceHolder;

    private boolean isRunning = false;
    private double averageUPS = 0;
    private double averageFPS = 0;

    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3 / MAX_UPS;

    public GameLoop(GameRender gameRender, SurfaceHolder surfaceHolder){
        this.gameRender = gameRender;
        this.surfaceHolder = surfaceHolder;
    }


    /**
     * Returns average updates per second number
     * @return (double) average UPS number
     */
    public double getAverageUPS() {
        return averageUPS;
    }


    /**
     * Returns average frames per second number
     * @return (double) average FPS number
     */
    public double getAverageFPS() {
        return averageFPS;
    }

    /**
     * Starts game loop
     */
    public void startLoop() {
        isRunning = true;
        start();
    }


    @Override
    public void run() {
        super.run();

        // time and cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();

        // game loop
        while (isRunning){

            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();

                synchronized (surfaceHolder){
                    // update game rendering
                    gameRender.update();
                    gameRender.update();
                    updateCount++;
                    // draw
                    gameRender.draw(canvas);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (canvas != null){
                    try {
                        // Unlock canvas and increase frame count
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }


            // Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0 ){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            /*
            // Skip frames to keep up with target UPS
            while(sleepTime < 0 && updateCount < MAX_UPS - 1){
                // Update game rendering
                gameRender.update();
                updateCount++;
                // Recalculate elapsed time and sleep time
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }
             */


            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                // Calculate UPS and FPS
                averageUPS = updateCount / (1E-3 *  elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                // Reset update count, frame count and start time
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

}
