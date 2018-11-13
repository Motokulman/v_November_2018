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
public class MultiSupportPlate {

    private int id;
    private double level;
    private Color color;
    private double thick;

    public MultiSupportPlate(int id, double level, Color color, double thick) {
        this.id = id;
        this.level = level;
        this.color = color;
        this.thick = thick;
    }

    public int getId() {
        return id;
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
