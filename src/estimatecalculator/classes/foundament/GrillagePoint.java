/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.foundament;

import static estimatecalculator.EstimateCalculator.dB;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author I
 */
public class GrillagePoint {

    private int id;
    private int id_x;
    private int id_y;
    private double wide; // Реальная ширина, в мм. Если равна 0, то значит вычислять автоматиески

    // Добавление новой точки ростверка
    public static void addGrillagePoint(int id_x, int id_y, double wide) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO GrillagePoints (`id_x`, `id_y`, `wide`) "
                + "VALUES(?, ?, ?)")) {
            statement.setObject(1, id_x);
            statement.setObject(2, id_y);
            statement.setObject(3, wide);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getId_x() {
        return id_x;
    }

    public int getId_y() {
        return id_y;
    }

    public double getWide() {
        return wide;
    }

}
