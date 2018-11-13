/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.insulate;

/**
 *
 * @author I
 */
public class ClimaticZone {
    private int id;
    private String name;
    private double floor_r;
    private double wall_r;
    private double roof_r;

    public ClimaticZone(int id, String name, double floor_r, double wall_r, double roof_r) {
        this.id = id;
        this.name = name;
        this.floor_r = floor_r;
        this.wall_r = wall_r;
        this.roof_r = roof_r;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getFloor_r() {
        return floor_r;
    }

    public double getWall_r() {
        return wall_r;
    }

    public double getRoof_r() {
        return roof_r;
    }
    
    
}
