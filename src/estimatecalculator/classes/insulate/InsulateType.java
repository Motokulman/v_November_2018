/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.insulate;

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
public class InsulateType {

    private int id;
    private String name;
    private double lambda; // лямбда, для вычисления R

    public InsulateType(int id, String name, double lambda) {
        this.id = id;
        this.name = name;
        this.lambda = lambda;
    }

    // Добавление Типа утеплителя
    public static void addInsulateType(String name, double lambda) {
        try (PreparedStatement statement = main_dB.getMainDBConnection().prepareStatement(
                "INSERT INTO InsulateTypes(`name`, `lambda`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, name);
            statement.setObject(2, lambda);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение Типов утеплителей
    public static List<InsulateType> getInsulateTypes() {
        try (Statement statement = main_dB.getMainDBConnection().createStatement()) {
            List<InsulateType> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, lambda FROM InsulateTypes");
            while (resultSet.next()) {
                products.add(new InsulateType(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getDouble("lambda")));
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

    public double getLambda() {
        return lambda;
    }

}
