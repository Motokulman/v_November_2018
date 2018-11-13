/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.socle;

import static estimatecalculator.EstimateCalculator.dB;
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
public class BrickSocle {
    int id;
    int grillage_id;
    int level_id;

    public BrickSocle(int id, int grillage_id, int level_id) {
        this.id = id;
        this.grillage_id = grillage_id;
        this.level_id = level_id;
    }
    
    
        // Добавление нового цоколя
    public static void addBrickSocle(int grillage_id, int level_id) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO BrickSocles (`grillage_id`, `level_id`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, grillage_id);
            statement.setObject(2, level_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение цоколей
    public static List<BrickSocle> getBrickSocles() {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<BrickSocle> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, grillage_id, level_id FROM BrickSocles");
            while (resultSet.next()) {
                products.add(new BrickSocle(
                        resultSet.getInt("ID"),
                        resultSet.getInt("grillage_id"),
                        resultSet.getInt("level_id")));
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

    public int getGrillage_id() {
        return grillage_id;
    }

    public int getLevel_id() {
        return level_id;
    }
    
    
    
}
