/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

import static estimatecalculator.EstimateCalculator.dB;
import static estimatecalculator.Settings.bottom_name;
import static estimatecalculator.Settings.top_name;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Уровень - это как группа перекрытий. Можно заменить тип перекрытий на всем уровне. Но если на одном уровне есть разнородые перекрытия,
// нужно создать дополнительный уровень, зависящий от соседнего перекрытия. Иначе, например, если на втором этаже есть и плиты и деревянное перекрытие,
// и если сделать их одним уровнем, то, установив ориентир низа уровня плитного перекрытия 2-го этажа от перекрытия 1-го этажа,
// мы получим разного уровня пол на 2-м этаже
public class Level {

    private int id;
    private String name;
    public static int is_this_top;// учесть ли в расчетах верх данного уровня 
    public static int is_bottom_top;// учесть ли в расчетах верх нижележащего уровня 
    private double altitude; // высота уровня относительно нижележащего уровня
    private int bottom_level; // указание на нижележащий уровень, id
    private String bottom_level_name; // указание на нижележащий уровень, id
    private String is_this_top_name;
    private String is_bottom_top_name;

    public Level(int id, String name, int is_this_top, int is_bottom_top, double altitude, int bottom_level) throws SQLException {
        this.id = id;
        this.name = name;
        this.is_this_top = is_this_top;
        this.is_bottom_top = is_bottom_top;
        this.altitude = altitude;
        this.bottom_level = bottom_level;
        this.bottom_level_name = getBottomLevelNameByID(bottom_level);
        this.is_this_top_name = getTopOrBottomName(is_this_top);
        this.is_bottom_top_name = getTopOrBottomName(is_bottom_top);
    }

    // Добавление уровня
    public static void addLevel(String name, int is_this_top, int is_bottom_top, double altitude, int bottom_level) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO Levels(`name`, `is_this_top`,  `is_bottom_top`,  `altitude`, `bottom_level`) "
                + "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, name);
            statement.setObject(2, is_this_top);
            statement.setObject(3, is_bottom_top);
            statement.setObject(4, altitude);
            statement.setObject(5, bottom_level);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение уровней
    public static List<Level> getLevels() {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<Level> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, is_this_top, is_bottom_top, altitude, bottom_level FROM Levels");
            while (resultSet.next()) {
                products.add(new Level(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getInt("is_this_top"),
                        resultSet.getInt("is_bottom_top"),
                        resultSet.getDouble("altitude"),
                        resultSet.getInt("bottom_level")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Получение уровнz по его наименованию
    public static Level getLevelByName(String name) throws SQLException {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, is_this_top, is_bottom_top, altitude, bottom_level FROM Levels WHERE name LIKE  '" + name + "'");
            Level level = new Level(
                    resultSet.getInt("ID"),
                    resultSet.getString("name"),
                    resultSet.getInt("is_this_top"),
                    resultSet.getInt("is_bottom_top"),
                    resultSet.getDouble("altitude"),
                    resultSet.getInt("bottom_level"));
            return level;
        }
    }

    // Получение имени уровня по его id для получения имен нижележащих уровней
    public static String getBottomLevelNameByID(int id) throws SQLException {
        if (id != 0) {
            try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT ID, name, is_this_top, is_bottom_top, altitude, bottom_level FROM Levels WHERE ID = " + Integer.toString(id));
                String n = new String(
                        resultSet.getString("name"));
                return n;
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    // Получение на ЧПЯ верх это или низ
    public static String getTopOrBottomName(int i) {
        String n = top_name;
        if (i == 0) {
            n = bottom_name;
        }
        return n;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIs_this_top() {
        return is_this_top;
    }

    public int getIs_bottom_top() {
        return is_bottom_top;
    }

    public double getAltitude() {
        return altitude;
    }

    public int getBottom_level() {
        return bottom_level;
    }

    public String getBottom_level_name() {
        return bottom_level_name;
    }

    public String getIs_this_top_name() {
        return is_this_top_name;
    }

    public String getIs_bottom_top_name() {
        return is_bottom_top_name;
    }

}
