/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;
// если из газобетона, то так и создаем. Потом можно выбирать, какую стену из них подставить

public class wallStructure {

    private String name;
    private int stoneTypeID; // тип камня стены. Не меняется!
    private double thick; // толщина стены в единицах выбранного типа камня
  //  private boolean homeMaterial; // Материал стены - это материал дома? 1 - да, 0 - нет. Для проверки, чтобы проверять, что 

    public wallStructure(String name, int stoneTypeID, double thick) {
        this.name = name;
        this.stoneTypeID = stoneTypeID;
        this.thick = thick;
    }

    public String getName() {
        return name;
    }

    public int getStoneTypeID() {
        return stoneTypeID;
    }

    public double getThick() {
        return thick;
    }

}
