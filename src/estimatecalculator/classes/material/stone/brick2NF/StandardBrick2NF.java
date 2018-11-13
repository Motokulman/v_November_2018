/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material.stone.brick2NF;

/**
 *
 * @author I
 */
public class StandardBrick2NF {

    public String name = "Камень 2.1 NF";
    private double longSide = 250;
    private double mediumSide = 140;
    private double smallSide = 120;
    private double sizeUnit = longSide;
    private int defaultMortarID;

    public StandardBrick2NF(int defaultMortarID) {
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
