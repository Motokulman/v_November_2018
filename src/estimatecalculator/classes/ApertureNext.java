/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author I
 */
public class ApertureNext {
    private final SimpleStringProperty apertureName;
    private final SimpleDoubleProperty apertureArea;
    private final ArrayList<Double> apertureNodesX;
    private final ArrayList<Double> apertureNodesY;
   
    public ApertureNext(String aName, ArrayList<Double> aNodesX, ArrayList<Double> aNodesY) {
        this.apertureName = new SimpleStringProperty(aName);
        this.apertureNodesX = new ArrayList<>(aNodesX);
        this.apertureNodesY = new ArrayList<>(aNodesY);
        double aArea = 0;
        
        //Получение площади проема
        // Первая часть формулы Гаусса
        for (int x = 0; x < apertureNodesX.size() - 1; x++) {
            for (int y = 1; y < apertureNodesY.size(); y++) {
                aArea = aArea + apertureNodesX.get(x)*apertureNodesY.get(y);
            }
        }
        // Средний член формулы Гаусса
        aArea = aArea + apertureNodesX.get(apertureNodesX.size() - 1)*apertureNodesY.get(0);
        // Третья часть формулы Гаусса
        for (int x = 1; x < apertureNodesX.size(); x++) {
            for (int y = 0; y < apertureNodesY.size() - 1; y++) {
                aArea = aArea - apertureNodesX.get(x)*apertureNodesY.get(y);
            }
        }
        // Четвертый член формулы Гаусса
        aArea = aArea - apertureNodesX.get(0)*apertureNodesY.get(apertureNodesY.size() - 1);
        // Приведение к положительному числу и деление на 2
        aArea = Math.abs(aArea)/2;
        
        this.apertureArea = new SimpleDoubleProperty(aArea);
    }

    public SimpleStringProperty getApertureName() {
        return apertureName;
    }

    public SimpleDoubleProperty getApertureArea() {
        return apertureArea;
    }

    public ArrayList<Double> getApertureNodesX() {
        return apertureNodesX;
    }

    public ArrayList<Double> getApertureNodesY() {
        return apertureNodesY;
    }
    public SimpleStringProperty setApertureName() {
        return apertureName;
    }

    public ArrayList<Double> setApertureNodesX() {
        return apertureNodesX;
    }

    public ArrayList<Double> setApertureNodesY() {
        return apertureNodesY;
    }
    
}