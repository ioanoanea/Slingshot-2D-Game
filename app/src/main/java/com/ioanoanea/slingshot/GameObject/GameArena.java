package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.ioanoanea.slingshot.R;

public class GameArena extends Object {

    private  final double screenWidth;
    private final double screenHeight;

    public GameArena(Context context, double screenWidth, double screenHeight){
        super(context);
        this. screenWidth = screenWidth;
        this. screenHeight = screenHeight;
    }

    /**
     * Draw the game arena
     * @param canvas (Canvas) canvas value
     */
    public void draw(Canvas canvas){
        // Draw background color
        canvas.drawColor(context.getResources().getColor(R.color.dark_grey));

        drawTopSide(canvas);
        drawBottomSide(canvas);
        drawLeftSide(canvas);
        drawRightSide(canvas);
    }

    /**
     * Draw left side wall
     * @param canvas (Canvas) Canvas value
     */
    private void drawLeftSide(Canvas canvas){
        TextureDrawer textureDrawer = new TextureDrawer(context, 0, 20, 0, screenHeight / getDensity());
        textureDrawer.draw(canvas);
    }

    /**
     * Draw right side wall
     * @param canvas (Canvas) canvas value
     */
    private void drawRightSide(Canvas canvas){
        TextureDrawer textureDrawer = new TextureDrawer(context, screenWidth / getDensity() - 20, screenWidth / getDensity(), 0, screenHeight / getDensity());
        textureDrawer.draw(canvas);
    }

    /**
     * Draw top side wall
     * @param canvas (Canvas) Canvas value
     */
    private void drawTopSide(Canvas canvas){
        TextureDrawer textureDrawer = new TextureDrawer(context, 0, screenWidth / getDensity(), 0, 20);
        textureDrawer.draw(canvas);
    }

    /**
     * Draw bottom side wall
     * @param canvas (Canvas) canvas value
     */
    private void drawBottomSide(Canvas canvas){
        TextureDrawer textureDrawer = new TextureDrawer(context, 0, screenWidth / getDensity(), screenHeight / getDensity() - 20, screenHeight / getDensity());
        textureDrawer.draw(canvas);
    }

}
