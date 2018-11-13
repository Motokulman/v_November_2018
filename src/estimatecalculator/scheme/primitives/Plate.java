/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import estimatecalculator.classes.elements.PlateTypeEnum;

/**
 *
 * @author I
 */
// ПОД УДАЛЕНИЕ
public class Plate {

    private String name;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private int x4;
    private int y4;
    private int level;
    private PlateTypeEnum type;

    public String getName() {
        return name;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getX4() {
        return x4;
    }

    public int getY4() {
        return y4;
    }

    public int getLevel() {
        return level;
    }

    public PlateTypeEnum getType() {
        return type;
    }

}
