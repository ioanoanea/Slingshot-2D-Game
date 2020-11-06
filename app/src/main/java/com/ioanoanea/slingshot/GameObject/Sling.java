package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class Sling {

    private Context context;
    private int positionX;
    private int positionY;

    public Sling(Context context, int positionX, int positionY){
        this.context = context;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Returns display density
     * @return (int) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * Draw the sling
     * @param canvas (canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.cyan_blue));

        // Draw sling's left side rectangle
        canvas.drawRect((positionX - 40) * getDensity(), positionY * getDensity(),
                (positionX - 25) *getDensity(), (positionY - 15) * getDensity(),
                paint);

        // Draw sling's right side rectangle
        canvas.drawRect((positionX + 25) * getDensity(), positionY * getDensity(),
                (positionX + 40) *getDensity(), (positionY - 15) * getDensity(),
                paint);
    }

}
