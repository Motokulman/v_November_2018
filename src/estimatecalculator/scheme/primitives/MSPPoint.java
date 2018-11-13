/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import static estimatecalculator.EstimateCalculator.dB;

/**
 *
 * @author I
 */
public class MSPPoint {
    private int msp_id;
    private double x;
    private double y;


    public MSPPoint( int msp_id, double x, double y) {
     //   System.out.println("this.msp_id = " + msp_id);
        this.msp_id = msp_id;
        this.x = x;
        this.y = y;
    }


    public int getMSP_id() {
        return msp_id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    
        
}
