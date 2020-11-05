package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.ioanoanea.slingshot.R;

public class GameArena {

    private Context context;

    public GameArena(Context context){
        this.context = context;
    }

    /**
     * Returns display density
     * @return (int) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Draw the game arena
     * @param canvas (canvas) canvas value
     * @param width (int) screen width
     * @param height (int) screen height
     */
    public void draw(Canvas canvas, int width, int height){
        // Draw background color
        canvas.drawColor(context.getResources().getColor(R.color.dark_grey));

        // Draw arena's walls
        // Draw a rectangle with heavy strokes
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(38 * getDensity());
        canvas.drawRect(0, 0, width, height, paint);
    }


    /**
     * Draw an obstacle in the arena
     * @param canvas (canvas) canvas value
     * @param positionX (int) position x of the obstacle
     * @param positionY (int) position y of the obstacle
     * @param length (int) length of the obstacle
     */
    public void drawObstacle(Canvas canvas, int positionX, int positionY, int length){
        // Draw an horizontal oriented rectangle at specified position
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        canvas.drawRect(positionX * getDensity(), positionY * getDensity(),
                positionX * getDensity() + length * getDensity(),
                positionY * getDensity() + 20 * getDensity(), paint);
    }
}
