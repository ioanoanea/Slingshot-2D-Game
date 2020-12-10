package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
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
        // set paint
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.transparent_white));
        paint.setTextSize(50);

        // if trace length is not 0 draw the trace
        if (positions.size() > 0){
            // set a path
            Path path = new Path();

            path.moveTo(positions.get(0).x * getDensity(), positions.get(0).y * getDensity());
            path.lineTo(positions.get(0).x * getDensity(), positions.get(0).y * getDensity());

            // set trace width to 0 at the beginning
            int length = positions.size();

            float distanceX = (positions.get(length - 1).y - positions.get(length - 2).y) / 28f;
            float distanceY = (positions.get(length - 1).x - positions.get(length - 2).x) / 28f;

            float posX = 0;
            float posY = 0;

            // draw oan path side from first position to last position of list
            for (int i = 0; i < length; i++){
                posX += distanceX;
                posY += distanceY;
                path.lineTo(positions.get(i).x * getDensity() + posX, positions.get(i).y * getDensity() + posY);
            }

            // draw other side from last position to first position of list
            for (int i = length - 1; i >= 0; i--){
                posX -= distanceX;
                posY -= distanceY;
                path.lineTo(positions.get(i).x * getDensity() - posX, positions.get(i).y * getDensity() - posY);
            }

            // draw the path
            canvas.drawPath(path, paint);
        }
    }
}
