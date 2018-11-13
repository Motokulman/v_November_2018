/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author I
 */
public class PlateEdge {

    private int id; // id этого ребра
    private int plate_id; // id перекрытия, которому принадлежит это ребро
    private double x0;
    private double y0;
    private double x1;
    private double y1;
    private double xr;// для радиусов, на будущее
    private double yr;
    private double Real_X0; // натуральные размеры, мм
    private double Real_Y0;
    private double Real_X1;
    private double Real_Y1;
    private double Real_Xr;// для радиусов, на будущее
    private double Real_Yr;
    private Boolean isSecant; // Является ли ребро секущим по отношению к нижележащим стенам
    private Boolean isSupport; // Является ли ребро опорным
    private Integer seamType_id; // тип шва из таблицы швов

    public PlateEdge(int id, int plate_id, double x0, double y0, double x1, double y1, double xr, double yr, double Real_X0, double Real_Y0, double Real_X1, double Real_Y1, double Real_Xr, double Real_Yr, Boolean isSecant, Boolean isSupport, Integer seamType_id) {
        this.id = id;
        this.plate_id = plate_id;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.xr = xr;
        this.yr = yr;
        this.Real_X0 = Real_X0;
        this.Real_Y0 = Real_Y0;
        this.Real_X1 = Real_X1;
        this.Real_Y1 = Real_Y1;
        this.Real_Xr = Real_Xr;
        this.Real_Yr = Real_Yr;
        this.isSecant = isSecant;
        this.isSupport = isSupport;
        this.seamType_id = seamType_id;
    }

    public int getId() {
        return id;
    }

    public int getPlate_id() {
        return plate_id;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getXr() {
        return xr;
    }

    public double getYr() {
        return yr;
    }

    public double getReal_X0() {
        return Real_X0;
    }

    public double getReal_Y0() {
        return Real_Y0;
    }

    public double getReal_X1() {
        return Real_X1;
    }

    public double getReal_Y1() {
        return Real_Y1;
    }

    public double getReal_Xr() {
        return Real_Xr;
    }

    public double getReal_Yr() {
        return Real_Yr;
    }

    public Boolean getIsSecant() {
        return isSecant;
    }

    public Boolean getIsSupport() {
        return isSupport;
    }

    public Integer getSeamType_id() {
        return seamType_id;
    }



}
