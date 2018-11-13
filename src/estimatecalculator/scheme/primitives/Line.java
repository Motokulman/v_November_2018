/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import static estimatecalculator.classes.ModelCanvas.color;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author I
 */
public class Line {

    double x0;
    double y0;
    double x1;
    double y1;
    public GraphicsContext gC;
    //  List<Integer> elementsArray = new ArrayList<>();

    public Line(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public Line(Point point_1, Point point_2, GraphicsContext gC) {
        this.x0 = point_1.getX();
        this.y0 = point_1.getY();
        this.x1 = point_2.getX();
        this.y1 = point_2.getY();
        this.gC = gC;
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

    public void drawLine() {
        gC.strokeLine(x0 + 0.5, y0 + 0.5, x1 - 0.5, y1 - 0.5);
    }
}
