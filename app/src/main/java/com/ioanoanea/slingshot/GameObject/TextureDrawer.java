package com.ioanoanea.slingshot.GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ioanoanea.slingshot.R;

public class TextureDrawer extends Object {

    private double left;
    private double right;
    private double top;
    private double bottom;
    public TextureDrawer(Context context, double left, double right, double top, double bottom){
        super(context);
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * Draw the texture in specified area
     * @param canvas (Canvas) Canvas value
     */
    public void draw(Canvas canvas){
        // set paint for first color
        Paint paint1 = new Paint();
        paint1.setColor(context.getResources().getColor(R.color.dark_blue));

        // set paint for second color
        Paint paint2 = new Paint();
        paint2.setColor(context.getResources().getColor(R.color.dark_blue_shadow));

        // set y to the of area
        double y = top;
        // set a count k
        int k = 0;

        // draw texture until bottom side
        while(y < bottom){
            // set x1 and x2
            double x1 = left + 10 * (k % 2);
            double x2 = left + 10 *((k + 1) % 2);

            // draw first color rectangles
            while(x1 < right){
                canvas.drawRect(
                        (float) x1 * getDensity(),
                        (float) y * getDensity(),
                        (float) (x1 + 10) * getDensity(),
                        (float) (y + 10) * getDensity(),
                        paint1
                );
                x1 += 20;
            }
            // draw second color rectangles
            /*
            while (x2 < right){
                canvas.drawRect(
                        (float) x2 * getDensity(),
                        (float) y * getDensity(),
                        (float) (x2 + 10) * getDensity(),
                        (float) (y + 10) * getDensity(),
                        paint2
                );
                x2 += 20;
            }*/

            y += 10;
            k++;
        }

    }

    /**
     * Draw the stroke for the textured area
     * @param canvas (Canvas) Canvas value
     */
    private void drawStroke(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.drawRect(
                (float) left * getDensity(),
                (float) top * getDensity(),
                (float) right * getDensity(),
                (float) bottom * getDensity(),
                paint
        );
    }
}
