/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.Plates;

/**
 *
 * @author I
 */
public class HollowCorePlate {

    private int plates_scheme_id; // какому перекрытию на схеме оно соответствует.
    private double height; // Толщина перекрытия

    public HollowCorePlate(int plates_scheme_id, double height) {
        this.plates_scheme_id = plates_scheme_id;
        this.height = height;
    }

    public int getPlates_scheme_id() {
        return plates_scheme_id;
    }

    public double getHeight() {
        return height;
    }

}
