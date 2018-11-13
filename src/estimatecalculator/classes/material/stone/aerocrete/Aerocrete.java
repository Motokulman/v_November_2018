/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material.stone.aerocrete;

/**
 *
 * @author I
 */
public class Aerocrete {

    public String name = "Газобетон";
    private double longSide = 625;
    private double mediumSide;
    private double smallSide;
    private double sizeUnit = longSide;
    private int defaultMortarID;
    private double densityMark;

    public Aerocrete(double mediumSide, double smallSide, int defaultMortarID, double densityMark) {
        this.mediumSide = mediumSide;
        this.smallSide = smallSide;
        this.defaultMortarID = defaultMortarID;
        this.densityMark = densityMark;
    }
    
    
}
