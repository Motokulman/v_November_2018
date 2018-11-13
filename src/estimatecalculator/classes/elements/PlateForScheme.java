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
public class PlateForScheme {
    
    private int id; // id данного перекрытия
    private PlateTypeEnum type;
    private PlateTypeEnum default_type;
    private String name; // имя перекрытия, задается пользователем
    private String comment; // пояснение, комментарий. Вдруг понадобится, пусть будет
    private int universal_group_id; // id универсальной группы, если перекрытие в группе
    private int level_id; // id уровня, к которому привязано данное перекрытие
    private Color color;
    private double thick; // Толщина линии на схеме

    public PlateForScheme(int id, PlateTypeEnum type, PlateTypeEnum default_type, String name, String comment, int universal_group_id, int level_id, Color color, double thick) {
        this.id = id;
        this.type = type;
        this.default_type = default_type;
        this.name = name;
        this.comment = comment;
        this.universal_group_id = universal_group_id;
        this.level_id = level_id;
        this.color = color;
        this.thick = thick;
    }

    public int getId() {
        return id;
    }

    public PlateTypeEnum getType() {
        return type;
    }

    public PlateTypeEnum getDefault_type() {
        return default_type;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getUniversal_group_id() {
        return universal_group_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public Color getColor() {
        return color;
    }

    public double getThick() {
        return thick;
    }



}
