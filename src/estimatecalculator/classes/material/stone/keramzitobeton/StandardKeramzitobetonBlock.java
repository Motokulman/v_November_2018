/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material.stone.keramzitobeton;

/**
 *
 * @author I
 */
public class StandardKeramzitobetonBlock {

    private String name = "Керамзитобетонный блок";
    private double longSide = 390;
    private double mediumSide = 190;
    private double smallSide = 188;
    private double sizeUnit = longSide;
    private int defaultMortarID;

    public StandardKeramzitobetonBlock(int defaultMortarID) {
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
