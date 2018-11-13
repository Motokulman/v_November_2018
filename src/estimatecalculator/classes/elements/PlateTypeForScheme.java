/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

import javafx.scene.paint.Color;

/**
 *
 * @author I
 */
public class PlateTypeForScheme {

    private PlateTypeEnum type;
    private String typeName;
    private Color color;
    private Double thick;

    public PlateTypeForScheme(PlateTypeEnum type, String typeName, Color color, Double thick) {
        this.type = type;
        this.typeName = typeName;
        this.color = color;
        this.thick = thick;
    }


    public PlateTypeEnum getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public Color getColor() {
        return color;
    }

    public Double getThick() {
        return thick;
    }
}
