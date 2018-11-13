/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import javafx.scene.paint.Color;

/**
 *
 * @author I
 */
public class TwoSupportPlate {

    private double x0;
    private double y0;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double level;
    private Color color;
    private double thick;
//    String name;

    public TwoSupportPlate(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double level, Color color, double thick) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.level = level;
        this.color = color;
        this.thick = thick;
//        this.name = name;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getLevel() {
        return level;
    }

    public Color getColor() {
        return color;
    }

    public double getThick() {
        return thick;
    }

}
