/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.foundament;

import static estimatecalculator.EstimateCalculator.dB;
import static estimatecalculator.Settings.grillageColor;
import static estimatecalculator.Settings.grillageHilightColor;
import static estimatecalculator.Settings.grillageSchemeHilightThick;
import static estimatecalculator.Settings.grillageSchemeThick;
import static estimatecalculator.Settings.prikleyka;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.stickedX;
import static estimatecalculator.classes.ModelCanvas.stickedY;
import static estimatecalculator.scheme.primitives.Axis.getAxisByID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author I
 */
public class StretchGrillage {

    private int id;
    private int id_x0;
    private int id_y0;
    private int id_x1;
    private int id_y1;
    private Integer id_xr;
    private Integer id_yr;
    private double wide;

    public StretchGrillage(int id, int id_x0, int id_y0, int id_x1, int id_y1, Integer id_xr, Integer id_yr, double wide) {
        this.id = id;
        this.id_x0 = id_x0;
        this.id_y0 = id_y0;
        this.id_x1 = id_x1;
        this.id_y1 = id_y1;
        this.id_xr = id_xr;
        this.id_yr = id_yr;
        this.wide = wide;
    }

    public StretchGrillage() {
    }

    // Добавление нового протяженного ростверка
    public static void addStretchGrillage(int id_x0, int id_y0, int id_x1, int id_y1, Integer id_xr, Integer id_yr, double wide) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO StretchGrillages (`id_x0`, `id_y0`, `id_x1`, `id_y1`, `id_xr`, `id_yr`, `wide`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, id_x0);
            statement.setObject(2, id_y0);
            statement.setObject(3, id_x1);
            statement.setObject(4, id_y1);
            statement.setObject(5, id_xr);
            statement.setObject(6, id_yr);
            statement.setObject(7, wide);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение протяженных ростверков
    public static List<StretchGrillage> getStretchGrillages() {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<StretchGrillage> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, id_x0, id_y0, id_x1, id_y1, id_xr, id_yr, wide FROM StretchGrillages");
            while (resultSet.next()) {
                products.add(new StretchGrillage(
                        resultSet.getInt("ID"),
                        resultSet.getInt("id_x0"),
                        resultSet.getInt("id_y0"),
                        resultSet.getInt("id_x1"),
                        resultSet.getInt("id_y1"),
                        resultSet.getInt("id_xr"),
                        resultSet.getInt("id_yr"),
                        resultSet.getDouble("wide")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Получение протяженных ростверков по id
    public static StretchGrillage getStretchGrillageByID(int id) throws SQLException {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, id_x0, id_y0, id_x1, id_y1, id_xr, id_yr, wide FROM StretchGrillages WHERE ID = " + Integer.toString(id));
            StretchGrillage sg = new StretchGrillage(
                    resultSet.getInt("ID"),
                    resultSet.getInt("id_x0"),
                    resultSet.getInt("id_y0"),
                    resultSet.getInt("id_x1"),
                    resultSet.getInt("id_y1"),
                    resultSet.getInt("id_xr"),
                    resultSet.getInt("id_yr"),
                    resultSet.getInt("wide"));

            return sg;
        }
    }

    // Получение протяженных ростверков по id осей
    public static StretchGrillage getStretchGrillageByAxisesID(int id_x0, int id_y0, int id_x1, int id_y1, Integer id_xr, Integer id_yr) throws SQLException {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, id_x0, id_y0, id_x1, id_y1, id_xr, id_yr, wide FROM StretchGrillages "
                    + "WHERE id_x0 = " + Integer.toString(id_x0)
                    + " AND id_y0 = " + Integer.toString(id_y0)
                    + " AND id_x1 = " + Integer.toString(id_x1)
                    + " AND id_y1 = " + Integer.toString(id_y1));
//                    + " AND id_xr = " + Integer.toString(id_xr)
//                    + " AND id_yr = " + Integer.toString(id_yr));
//System.out.println("resultSet.getFetchSize() = " + resultSet.getFetchSize());
//System.out.println("resultSet.getFetchSize() = " + resultSet.getStatement().toString());

            StretchGrillage sg = new StretchGrillage(
                    resultSet.getInt("ID"),
                    resultSet.getInt("id_x0"),
                    resultSet.getInt("id_y0"),
                    resultSet.getInt("id_x1"),
                    resultSet.getInt("id_y1"),
                    resultSet.getInt("id_xr"),
                    resultSet.getInt("id_yr"),
                    resultSet.getInt("wide"));
            return sg;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Рисование протяженных ростверков из базы
    public static void drawStretchGrillages() throws SQLException {
        double x0;
        double y0;
        double x1;
        double y1;
        graphicsContext.setStroke(grillageColor);
        graphicsContext.setLineDashes(0);
        graphicsContext.setLineWidth(grillageSchemeThick);

        for (StretchGrillage stretchGrillage : getStretchGrillages()) {
            x0 = getAxisByID("AxisesX", stretchGrillage.getId_x0()).getPixels();
            y0 = getAxisByID("AxisesY", stretchGrillage.getId_y0()).getPixels();
            x1 = getAxisByID("AxisesX", stretchGrillage.getId_x1()).getPixels();
            y1 = getAxisByID("AxisesY", stretchGrillage.getId_y1()).getPixels();
            graphicsContext.strokeLine(x0 + 0.5, y0 + 0.5, x1 + 0.5, y1 + 0.5);
        }
    }

// Рисование одного ростверка ярко (выделяем)
    public static void drawSingleStretchGrillage(StretchGrillage stretchGrillage) throws SQLException {
        double x0;
        double y0;
        double x1;
        double y1;
        graphicsContext.setStroke(grillageHilightColor);
        graphicsContext.setLineDashes(0);
        graphicsContext.setLineWidth(grillageSchemeHilightThick);

        x0 = getAxisByID("AxisesX", stretchGrillage.getId_x0()).getPixels();
        y0 = getAxisByID("AxisesY", stretchGrillage.getId_y0()).getPixels();
        x1 = getAxisByID("AxisesX", stretchGrillage.getId_x1()).getPixels();
        y1 = getAxisByID("AxisesY", stretchGrillage.getId_y1()).getPixels();
        graphicsContext.strokeLine(x0 + 0.5, y0 + 0.5, x1 + 0.5, y1 + 0.5);
    }

    // Подсветка ростверков под курсором
    public static void mouseMovedHilightStretchGrillage() throws SQLException {
        // При движении мыши находим ростверки под курсором
        for (StretchGrillage stretchGrillage : getStretchGrillages()) {
            double x0 = getAxisByID("AxisesX", stretchGrillage.getId_x0()).getPixels();
            double y0 = getAxisByID("AxisesY", stretchGrillage.getId_y0()).getPixels();
            double x1 = getAxisByID("AxisesX", stretchGrillage.getId_x1()).getPixels();
            double y1 = getAxisByID("AxisesY", stretchGrillage.getId_y1()).getPixels();
            double a = Math.hypot(Math.abs(y1 - y0), Math.abs(x1 - x0));
            double b = Math.hypot(Math.abs(y1 - stickedY), Math.abs(x1 - stickedX));
            double c = Math.hypot(Math.abs(y0 - stickedY), Math.abs(x0 - stickedX));
            if ((b + c - a) < Math.sqrt(prikleyka)) {
                drawSingleStretchGrillage(getStretchGrillageByAxisesID(stretchGrillage.getId_x0(), stretchGrillage.getId_y0(), stretchGrillage.getId_x1(), stretchGrillage.getId_y1(), null, null));
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getId_x0() {
        return id_x0;
    }

    public int getId_y0() {
        return id_y0;
    }

    public int getId_x1() {
        return id_x1;
    }

    public int getId_y1() {
        return id_y1;
    }

    public Integer getId_xr() {
        return id_xr;
    }

    public Integer getId_yr() {
        return id_yr;
    }

    public double getWide() {
        return wide;
    }

}
