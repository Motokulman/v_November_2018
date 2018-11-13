/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

//Ось имеет два размера: для схемы и натуральные
import static estimatecalculator.EstimateCalculator.dB;
import static estimatecalculator.Settings.axisBrightColor;
import static estimatecalculator.Settings.axisBrightWidth;
import static estimatecalculator.Settings.axisColor;
import static estimatecalculator.Settings.axisDashes;
import static estimatecalculator.Settings.axisWidth;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.modelCanvasHeight;
import static estimatecalculator.classes.ModelCanvas.modelCanvasWidth;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Первая ось имеет координаты 0
//Смещение при рисовании схемы - в отдельной таблице
//При появлении новой оси за пределами нулевой - все оси пересчитываются
public class Axis {

    private int id;
    private double pixels;
    private double size;

    public Axis(int id, double pixels, double size) {
        this.id = id;
        this.pixels = pixels;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public double getPixels() {
        return pixels;
    }

    public double getSize() {
        return size;
    }

    // Добавление оси X
    public void addAxisX() {
//        
//        
//        // если это первый клик
//        switch (getAxises("AxisesX").size()) {
//            case 0: {
//                offsetX = stickedX; // Присвоим скразц смещение, может и менять не придется
//                offsetY = stickedY;
//                addSimpleAxis("AxisesX", 0, 0);
//                addSimpleAxis("AxisesY", 0, 0);
//                break;
//            }
//            case 1: {
//                if (stickedX > offsetX) {// если справа
//                    addSimpleAxis("AxisesX", stickedX - offsetX, stickedX - offsetX);
//                } else { // если слева
//
//                }
//            }
//        }

    }

    public void ChangeAxis(String table, double increment) {
//        for (int i = 0; i < getAxises(table).size(); i++) {
//            try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                    "UPDATE " + table + " set distancePixels = ? , set distanceReal = ? "
//                    + "where ID = ?")) {
//                statement.setObject(1, getAxises(table).get(i).distancePixels + increment);
//                statement.setObject(2, getAxises(table).get(i).distanceReal + increment);
//                statement.setObject(3, getAxises(table).get(i).id);
//                statement.execute();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    // Получение осей
    public static List<Axis> getAxises(String table) {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<Axis> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, pixels, size FROM " + table);
            while (resultSet.next()) {
                products.add(new Axis(
                        resultSet.getInt("ID"),
                        resultSet.getDouble("pixels"),
                        resultSet.getDouble("size")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Рисование осей
    // Рисование обычной оси
    public static void drawAxises() {
        graphicsContext.setStroke(axisColor);
        graphicsContext.setLineDashes(axisDashes);
        graphicsContext.setLineWidth(axisWidth);

        getAxises("AxisesX").forEach((axis) -> {
            graphicsContext.strokeLine(axis.getPixels() + 0.5, 0.5, axis.getPixels() + 0.5, modelCanvasHeight - 0.5);
        });
        getAxises("AxisesY").forEach((axis) -> {
            graphicsContext.strokeLine(0.5, axis.getPixels() + 0.5, modelCanvasWidth - 0.5, axis.getPixels() + 0.5);
        });
    }

    // Рисование яркой оси X
    public static void drawBrightAxisX(double x) {
        graphicsContext.setStroke(axisBrightColor);
        graphicsContext.setLineDashes(axisDashes);
        graphicsContext.setLineWidth(axisBrightWidth);
        graphicsContext.strokeLine(x + 0.5, 0.5, x + 0.5, modelCanvasHeight - 0.5);
    }

    // Рисование яркой оси Y
    public static void drawBrightAxisY(double y) {
        graphicsContext.setStroke(axisBrightColor);
        graphicsContext.setLineDashes(axisDashes);
        graphicsContext.setLineWidth(axisBrightWidth);
        graphicsContext.strokeLine(0.5, y + 0.5, modelCanvasWidth - 0.5, y + 0.5);
    }

    // Получение осей по id
    public static Axis getAxisByID(String table, int id) throws SQLException {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, pixels, size FROM " + table + " WHERE ID = " + Integer.toString(id));
            Axis axis = new Axis(
                    resultSet.getInt("ID"),
                    resultSet.getDouble("pixels"),
                    resultSet.getDouble("size"));
            return axis;
        }
    }

    // Получение минимального зачения пикселей
    public double getMinPix(String table) {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<Double> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT pixels FROM " + table);
            while (resultSet.next()) {
                products.add(resultSet.getDouble("pixels"));
            }
            return Collections.min(products);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Получение максимального зачения пикселей
    public double getMaxPix(String table) {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<Double> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT pixels FROM " + table);
            while (resultSet.next()) {
                products.add(resultSet.getDouble("pixels"));
            }
            return Collections.max(products);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

// Получение максимального зачения оси в мм
    public double getMaxDistance(String table) {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<Double> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT distance FROM " + table);
            while (resultSet.next()) {
                products.add(resultSet.getDouble("distance"));
            }
            return Collections.max(products);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Добавление  оси
    public static void addAxis(String table, double pixels, double size) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO " + table + "(`pixels`, `size`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, pixels);
            statement.setObject(2, size);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

//
//    public void addAxisX(double current_pixels) {
//        double temp_distance;
//        double proportion;
//
//        // Если это первая ось
//        if (getAxisesX().size() == 0) {
//            // то просто добавляем ее
//            try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                    "INSERT INTO AxisesX(`pixel`, `distance`) "
//                    + "VALUES(?, ?)")) {
//                statement.setObject(1, current_pixels);
//                statement.setObject(2, 0);
//                statement.execute();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            // если это вторая ось,             
//            // теперь, если это второй клик, то есть у нас есть пока только одна ось и натуральных размеров не может быть по определению
//            // но уже может быть так, что вторая ось будет левее первой
//        } else if (getAxisesX().size() == 1) {
//            if (current_pixels > getMaxPix("AxisesX")) { // если правее, то просто добавляем
//                try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                        "INSERT INTO AxisesX(`pixel`, `distance`) "
//                        + "VALUES(?, ?)")) {
//                    statement.setObject(1, current_pixels);
//                    statement.setObject(2, current_pixels);
//                    statement.execute();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            } else { // если левее
//                // сначала обновляем имеющиеся значения натуральных размеров
//                for (int i = 0; i < getAxisesX().size(); i++) {
//                    try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                            "UPDATE AxisesX set distance = ? "
//                            + "where ID = ?")) {
//                        statement.setObject(1, getAxisesX().get(i).distance + current_pixels);
//                        statement.setObject(2, getAxisesX().get(i).id);
//
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                // потом добавляем
//                try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                        "INSERT INTO AxisesX(`pixel`, `distance`) "
//                        + "VALUES(?, ?)")) {
//                    statement.setObject(1, current_pixels);
//                    statement.setObject(2, current_pixels);
//                    statement.execute();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            // когда это 3-й клик и более
//        } else {
//            
//       //     proportion = getMaxPix("AxisesX") - getMinPix("AxisesX")
//            if (current_pixels > getMaxPix("AxisesX")) { //если правее
//                try (PreparedStatement statement = dB.getConnection().prepareStatement(
//                        "INSERT INTO AxisesX(`pixel`, `distance`) "
//                        + "VALUES(?, ?)")) {
//                    statement.setObject(1, current_pixels);
//                    statement.setObject(2, current_pixels);
//                    statement.execute();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
