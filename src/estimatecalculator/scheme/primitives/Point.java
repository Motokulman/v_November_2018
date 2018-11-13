/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import static estimatecalculator.classes.ModelCanvas.color;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;

/**
 *
 * @author I
 */
public class Point {

    private static double x;
    private static double y;
    private static double thick = 7;

    public Point() {
    }

//    public Point(double i, double j) {
//        this.x = i;
//        this.y = j;
//    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static void setPoint(double x, double y) {
        Point.x = x;
        Point.y = y;
    }

    // Функция рисования точек. Просто точек
    public void drawPoint() {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - thick / 2 + 0.5, y - thick / 2 - 0.5, thick, thick);
    }
}
