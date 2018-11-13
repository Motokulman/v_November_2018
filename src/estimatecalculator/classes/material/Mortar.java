/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material;

import java.util.List;

/**
 *
 * @author I
 */
public class Mortar {
    private List<AmorphousMaterial> composition; // Состав раствора. Всегда здесь есть вода
    private double lambda; // Теплопроводность
    private double density; // плотность готового раствора  кг/м³
    private double coatingThickness; // стандартная толщина нанесения

    public Mortar(List<AmorphousMaterial> composition, double lambda, double density, double coatingThickness) {
        this.composition = composition;
        this.lambda = lambda;
        this.density = density;
        this.coatingThickness = coatingThickness;
    }

    public List<AmorphousMaterial> getComposition() {
        return composition;
    }

    public double getLambda() {
        return lambda;
    }

    public double getDensity() {
        return density;
    }

    public double getCoatingThickness() {
        return coatingThickness;
    }


    
}
