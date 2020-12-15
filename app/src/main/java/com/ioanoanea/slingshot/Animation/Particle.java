package com.ioanoanea.slingshot.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

import java.util.Random;

public class Particle extends AnimationObject {

    double x;
    double y;
    int radius;
    double directionX;
    double directionY;
    Paint paint;
    private int alpha = 255;

    public Particle(Context context, double x, double y, int radius){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        this.directionX = -1f + random.nextDouble() * 2;
        this.directionY = -1 + random.nextInt(2) + Math.sqrt(1 - Math.pow(directionX, 2));
        paint = new Paint();
    }

    public Particle(Context context, double x, double y, int radius, double directionX, double directionY){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        this.directionX = directionX * random.nextDouble();
        this.directionY = directionY * Math.sqrt(1 - Math.pow(this.directionX, 2));
        paint = new Paint();
    }

    /**
     * Move the particle
     */
    public void move(){
        x += directionX / getDensity();
        y += directionY / getDensity();
    }

    /**
     * Update particle position
     */
    public void update(){
        move();
        paint.setColor(context.getResources().getColor(R.color.magenta));
        paint.setAlpha(alpha);
        alpha = Math.max(alpha - 1, 0);
    }

    /**
     * Draw the particle
     * @param canvas (Canvas) Canavs value
     */
    public void draw(Canvas canvas){
        canvas.drawRect(
                (float) (x - radius) * getDensity(),
                (float) (y - radius) * getDensity(),
                (float) (x + radius) * getDensity(),
                (float) (y + radius) * getDensity(),
                paint
        );
    }
}
