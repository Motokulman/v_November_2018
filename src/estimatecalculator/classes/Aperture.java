/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author I
 */
public class Aperture {
    private final SimpleStringProperty apertureName = new SimpleStringProperty("");
    private final SimpleDoubleProperty apertureArea = new SimpleDoubleProperty();
    private final SimpleDoubleProperty apertureNeedLintelledWidth = new SimpleDoubleProperty();
   
    public Aperture(String aName, double aArea, double aNeedLintelledWidth) {
        setApertureName(aName);
        setApertureArea(aArea);
        setApertureNeedLintelledWidth(aNeedLintelledWidth);
    }

    public String getApertureName() {
        return apertureName.get();
    }

    public double getApertureArea() {
        return apertureArea.get();
    }

    public double getApertureNeedLintelledWidth() {
        return apertureNeedLintelledWidth.get();
    }

    public void setApertureName(String aName) {
        apertureName.set(aName);
    }

    public void setApertureArea(double aArea) {
        apertureArea.set(aArea);
    }

    public void setApertureNeedLintelledWidth(double aNeedLintelledWidth) {
        apertureNeedLintelledWidth.set(aNeedLintelledWidth);
    }

}