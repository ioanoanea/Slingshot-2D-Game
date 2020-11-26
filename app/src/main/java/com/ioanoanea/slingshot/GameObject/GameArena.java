package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.ioanoanea.slingshot.R;

public class GameArena {

    private final Context context;
    private  final double screenWidth;
    private final double screenHeight;

    public GameArena(Context context, double screenWidth, double screenHeight){
        this.context = context;
        this. screenWidth = screenWidth;
        this. screenHeight = screenHeight;
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
     */
    public void draw(Canvas canvas){
        // Draw background color
        canvas.drawColor(context.getResources().getColor(R.color.dark_grey));

        // Draw arena's walls
        // Draw a heavy stroked rectangle
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(38 * getDensity());
        canvas.drawRect(0, 0, (float) screenWidth, (float) screenHeight, paint);
    }

}
