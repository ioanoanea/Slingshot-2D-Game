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
     * @param canvas (Canvas) canvas value
     * @param width (int) screen width
     * @param height (int) screen height
     */
    public void draw(Canvas canvas, int width, int height){
        // Draw background color
        canvas.drawColor(context.getResources().getColor(R.color.dark_grey));

        // Draw arena's walls
        // Draw a heavy stroked rectangle
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(38 * getDensity());
        canvas.drawRect(0, 0, width, height, paint);
    }

}
