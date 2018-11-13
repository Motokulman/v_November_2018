/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material.stone.solidbrick;

/**
 *
 * @author I
 */
public class SolidBrick {

    public String name = "Кирпич полнотелый";
    private double longSide = 250;
    private double mediumSide = 120;
    private double smallSide = 65;
    private double sizeUnit = longSide;
    private int defaultMortarID;

    public SolidBrick(int defaultMortarID) {
        this.defaultMortarID = defaultMortarID;
    }

    public String getName() {
        return name;
    }

    public double getLongSide() {
        return longSide;
    }

    public double getMediumSide() {
        return mediumSide;
    }

    public double getSmallSide() {
        return smallSide;
    }

    public double getSizeUnit() {
        return sizeUnit;
    }

    public int getDefaultMortarID() {
        return defaultMortarID;
    }
    
    
}
