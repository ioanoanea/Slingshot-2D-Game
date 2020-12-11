package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.ioanoanea.slingshot.R;

import java.util.ArrayList;

public class Trace extends Object {

    private final ArrayList<Point> positions;
    private boolean mLengthReached = false;

    public Trace(Context context){
        super(context);
        positions = new ArrayList<>();
    }

    /**
     * Add position to the list of bullet positions
     * @param point (Point) position point
     */
    public void adPosition(Point point){
        positions.add(point);
        if (positions.size() > 40){
            mLengthReached = true;
        }
    }

    /**
     * Remove a position from bullet position's list
     * Note: is removed position from first list element
     */
    public void removePosition(){
        if (positions.size() > 0){
            positions.remove(0);
        }
    }

    /**
     * Check if max length of trace has been reached
     * Note: Max length is reached if positions list has 40 elements
     * @return true if max length was reached, false otherwise
     */
    public boolean maxLengthReached(){
        return mLengthReached;
    }

    /**
     * Draw the trace
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();

        float r = 18;
        int length = positions.size();
        double alpha = 5 * length;
        for (int i = length - 3; i >= 0; i-=5){
            alpha -= 6;
            int color = Color.argb((int) alpha, 128, 128, 128);
            paint.setColor(color);
            canvas.drawCircle(positions.get(i).x * getDensity(), positions.get(i).y * getDensity(), r, paint);
            r -= 2;
        }
    }

}
