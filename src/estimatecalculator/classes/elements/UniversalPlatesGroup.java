/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

/**
 *
 * @author I
 */
public class UniversalPlatesGroup {

    private int id; // нужно, т.к. после создания группа м.б. удалена, и порячдок в листе поменяется, поэтому полагаться на порядок в листе нельзя
    private String name;

    public UniversalPlatesGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
