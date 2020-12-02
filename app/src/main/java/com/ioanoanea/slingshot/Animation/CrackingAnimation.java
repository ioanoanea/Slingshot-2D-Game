package com.ioanoanea.slingshot.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.ioanoanea.slingshot.GameObject.Bullet;
import com.ioanoanea.slingshot.GameObject.TargetObject;
import com.ioanoanea.slingshot.MathObject.LineEquation;
import com.ioanoanea.slingshot.R;

import java.util.ArrayList;

public class CrackingAnimation {

    private final Context context;
    private final double left;
    private final double right;
    private final double top;
    private final double bottom;
    Path path;

    public CrackingAnimation(Context context, TargetObject targetObject){
        this.context = context;
        this.left = targetObject.getLeft();
        this.right = targetObject.getRight();
        this.top = targetObject.getTop();
        this.bottom = targetObject.getBottom();
    }

    /**
     * Returns display density
     * @return (float) density
     */
    private float getDensity(){
        return context.getResources().getDisplayMetrics().density;
    }

    public void animate(){
        path = new Path();
    }

    private void animate(ArrayList<Path> paths){
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.cyan_blue));

        canvas.drawPath(path, paint);
    }
}
