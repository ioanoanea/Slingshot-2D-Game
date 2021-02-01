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
        this.directionX = -1.5f + random.nextDouble() * 3;
        this.directionY = -1 + random.nextInt(2) + Math.sqrt(Math.pow(1.5, 2) - Math.pow(directionX, 2));
        paint = new Paint();
    }

    public Particle(Context context, double x, double y, int radius, int alpha){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        this.directionX = -1.5f + random.nextDouble() * 3;
        this.directionY = -1 + random.nextInt(2) + Math.sqrt(Math.pow(1.5, 2) - Math.pow(directionX, 2));
        paint = new Paint();
        this.alpha = alpha;
    }

    public Particle(Context context, double x, double y, int radius, double directionX, double directionY){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        this.directionX = directionX * random.nextDouble() * 1.5;
        this.directionY = directionY * Math.sqrt(Math.pow(1.5, 2) - Math.pow(this.directionX, 2));
        paint = new Paint();
    }

    public Particle(Context context, double x, double y, int radius, double directionX, double directionY, int alpha){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        this.directionX = directionX * random.nextDouble() * 1.5;
        this.directionY = directionY * Math.sqrt(Math.pow(1.5, 2) - Math.pow(this.directionX, 2));
        paint = new Paint();
        this.alpha = alpha;
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
        alpha = Math.max(alpha - 2, 0);
    }

    /**
     * Returns particle's alpha
     * @return (int) alpha value
     */
    public int getAlpha() {
        return alpha;
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
