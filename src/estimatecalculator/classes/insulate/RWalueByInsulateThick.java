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
public class RWalueByInsulateThick {

    private int id;
    private int insulate_id;
    private double thick;
    private double r;

    public RWalueByInsulateThick(int id, int insulate_id, double thick, double r) {
        this.id = id;
        this.insulate_id = insulate_id;
        this.thick = thick;
        this.r = r;
    }

    // Добавление RWalueByInsulateThick
    public static void addRWalueByInsulateThick(int insulate_id, double thick, double r) {
        try (PreparedStatement statement = main_dB.getMainDBConnection().prepareStatement(
                "INSERT INTO InsulateTypes(`insulate_id`, `thick`, `r`) "
                + "VALUES(?, ?, ?)")) {
            statement.setObject(1, insulate_id);
            statement.setObject(2, thick);
            statement.setObject(3, r);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение RWalueByInsulateThick
    public static List<RWalueByInsulateThick> getRWaluesByInsulateThick() {
        try (Statement statement = main_dB.getMainDBConnection().createStatement()) {
            List<RWalueByInsulateThick> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, insulate_id, thick, r FROM InsulateTypes");
            while (resultSet.next()) {
                products.add(new RWalueByInsulateThick(
                        resultSet.getInt("ID"),
                        resultSet.getInt("insulate_id"),
                        resultSet.getDouble("thick"),
                        resultSet.getDouble("r")));
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

    public int getInsulate_id() {
        return insulate_id;
    }

    public double getThick() {
        return thick;
    }

    public double getR() {
        return r;
    }

}
