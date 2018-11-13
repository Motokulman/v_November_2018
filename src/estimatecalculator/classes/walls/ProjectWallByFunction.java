/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.walls;

import static estimatecalculator.EstimateCalculator.project_dB;
import estimatecalculator.classes.elements.WallTypeByFunctionalEnum;
import estimatecalculator.classes.foundament.StretchGrillage;
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
public class ProjectWallByFunction {

    private int id;
    private String name;
    private WallTypeByFunctionalEnum functional_type;
    private Integer selected_wall_structure_id;// может и не быть выбрана в начале

    public ProjectWallByFunction(int id, String name, WallTypeByFunctionalEnum functional_type, Integer selected_wall_structure_id) {
        this.id = id;
        this.name = name;
        this.functional_type = functional_type;
        this.selected_wall_structure_id = selected_wall_structure_id;
    }

    // Добавление стен проекта по функциям
    public static void addProjectWallByFunction(String name, WallTypeByFunctionalEnum functional_type, Integer selected_wall_structure_id) {
        try (PreparedStatement statement = project_dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO ProjectWallsByFunction(`name`, `functional_type`, `selected_wall_structure_id`) "
                + "VALUES(?, ?, ?)")) {
            statement.setObject(1, name);
            statement.setObject(2, functional_type);
            statement.setObject(3, selected_wall_structure_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение стен проекта по функциям
    public static List<ProjectWallByFunction> getProjectWallsByFunction() {
        try (Statement statement = project_dB.getConnectionProjectDB().createStatement()) {
            List<ProjectWallByFunction> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, functional_type, selected_wall_structure_id FROM ProjectWallsByFunction");
            while (resultSet.next()) {
                products.add(new ProjectWallByFunction(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        Enum.valueOf(WallTypeByFunctionalEnum.class,
                                resultSet.getString("functional_type")),
                        resultSet.getInt("selected_wall_structure_id")));

            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Получение  стены проекта по ЧП имени
    public static ProjectWallByFunction getProjectWallsByFunctionByName(String name) throws SQLException {
        try (Statement statement = project_dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, functional_type, selected_wall_structure_id FROM ProjectWallsByFunction WHERE name = " + name);
            ProjectWallByFunction sg = new ProjectWallByFunction(
                    resultSet.getInt("ID"),
                    resultSet.getString("name"),
                    Enum.valueOf(WallTypeByFunctionalEnum.class,
                            resultSet.getString("functional_type")),
                    resultSet.getInt("selected_wall_structure_id"));
            return sg;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WallTypeByFunctionalEnum getFunctional_type() {
        return functional_type;
    }

    public Integer getSelected_wall_structure_id() {
        return selected_wall_structure_id;
    }

}
