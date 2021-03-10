package com.ioanoanea.slingshot.MathObjects;


import android.content.res.Resources;

public class LineEquation {

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;

    public final double a;
    public final double b;
    public final double c;

    public LineEquation(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        a = y2 - y1;
        b = x1 - x2;
        c = x2 * y1 - x1 * y2;
    }


    /**
     * Returns display density
     * @return (int) density
     */
    private float getDensity(){
        return Resources.getSystem().getDisplayMetrics().density;
    }


    /**
     * Calculate position X for point with known y using line equation
     * @param positionY (double) point's position Y
     * @return (double) point's position X
     */
    public double getPositionX(double positionY){
        double a = x2 - x1;
        double b = y2 - y1;
        double c = a * y1;
        double d = b * x1;

        if(b == 0)
            return 0;
        else return (positionY * a - c + d) / b;
    }


    /**
     * Calculate position Y for point with known X using line equation
     * @param positionX (double) point's position X
     * @return (double) point's position Y
     */
    public double getPositionY(double positionX){
        double a = x2 - x1;
        double b = y2 - y1;
        double c = a * y1;
        double d = b * x1;

        if(a == 0)
            return 0;
        else return (positionX * b + c - d) / a;
    }
}
