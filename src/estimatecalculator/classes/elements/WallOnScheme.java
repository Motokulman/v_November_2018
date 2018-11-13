/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

import javafx.scene.paint.Color;

/**
 *
 * @author I
 */
public class WallOnScheme {

    private double x0;
    private double y0;
    private double x1;
    private double y1;
    private String type;
    private Color color;
    private double thick;

    public WallOnScheme(double x0, double y0, double x1, double y1, String type, Color color, double thick) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.type = type;
        this.color = color;
        this.thick = thick;
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

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public double getThick() {
        return thick;
    }

}
