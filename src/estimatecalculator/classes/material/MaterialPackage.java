/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material;

import static estimatecalculator.EstimateCalculator.main_dB;
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
public class MaterialPackage {

    private int id;
    private String name;
    private double quantity; // кол-во единиц измерения в данной упаковке. Сама единица измерения не нужна
    private double longSide; // габарит упаковки по длинной стороне
    private double mediumSide; // габарит упаковки по средней стороне
    private double smallSide; // габарит упаковки по меньшей стороне

    public MaterialPackage(int id, String name, double quantity, double longSide, double mediumSide, double smallSide) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.longSide = longSide;
        this.mediumSide = mediumSide;
        this.smallSide = smallSide;
    }

// Добавление упаковки
    public static void addMaterialPackage(String name, double quantity, double longSide, double mediumSide, double smallSide) {
        try (PreparedStatement statement = main_dB.getMainDBConnection().prepareStatement(
                "INSERT INTO MaterialPackages(`name`, `quantity`, `longSide`, `mediumSide`, `smallSide`) "
                + "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, name);
            statement.setObject(2, quantity);
            statement.setObject(3, longSide);
            statement.setObject(4, mediumSide);
            statement.setObject(5, smallSide);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение упаковок
    public static List<MaterialPackage> getMaterialPackages() {
        try (Statement statement = main_dB.getMainDBConnection().createStatement()) {
            List<MaterialPackage> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, quantity, longSide, mediumSide, smallSide FROM MaterialPackages");
            while (resultSet.next()) {
                products.add(new MaterialPackage(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("longSide"),
                        resultSet.getDouble("mediumSide"),
                        resultSet.getInt("smallSide")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getLongSide() {
        return longSide;
    }

    public double getMediumSide() {
        return mediumSide;
    }

    public double getSmallSide() {
        return smallSide;
    }

}
