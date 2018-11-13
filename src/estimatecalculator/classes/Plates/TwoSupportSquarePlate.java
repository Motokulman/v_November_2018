// Прямоугольное перекрытие с изначально задаваемыми двумя опорными сторонами. В случае, если оно состоит из поит ПК, то может состоять из одной
// или более плит ПК. Также может состоять в группе перекрытий. То есть схема такая: плиты перекрытий - двухопорное перекрытие - группа перекрытий
package estimatecalculator.classes.Plates;

import static estimatecalculator.EstimateCalculator.dB;
import static estimatecalculator.Settings.twoSupportSquarePlateTypesColor;
import static estimatecalculator.Settings.twoSupportSquarePlateTypesSchemeLateralSideThick;
import static estimatecalculator.Settings.twoSupportSquarePlateTypesSchemeSupportSideThick;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import estimatecalculator.classes.elements.PlateTypeEnum;
import static estimatecalculator.scheme.primitives.Axis.getAxisByID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoSupportSquarePlate {

    private int id; //id перекрытия
    private int id_x0;
    private int id_y0;
    private int id_x1;
    private int id_y1;
    private int id_x2;
    private int id_y2;
    private int id_x3;
    private int id_y3;
    private int level_id; //какому из уровней принадлежит данное перекрытие
    private Integer group_id; //какой из групп принадлежит данное перекрытие
    private PlateTypeEnum selected_type; // тип перекрятия, выбранный из списка подмен

    public TwoSupportSquarePlate(int id, int id_x0, int id_y0, int id_x1, int id_y1, int id_x2, int id_y2, int id_x3, int id_y3, int level_id, Integer group_id, PlateTypeEnum selected_type) {
        this.id = id;
        this.id_x0 = id_x0;
        this.id_y0 = id_y0;
        this.id_x1 = id_x1;
        this.id_y1 = id_y1;
        this.id_x2 = id_x2;
        this.id_y2 = id_y2;
        this.id_x3 = id_x3;
        this.id_y3 = id_y3;
        this.level_id = level_id;
        this.group_id = group_id;
        this.selected_type = selected_type;
    }

    // Добавление нового 2-х опорного прямоугольного перекрытия
    public static void addTwoSupportSquarePlate(int id_x0, int id_y0, int id_x1, int id_y1, int id_x2, int id_y2, int id_x3, int id_y3, int level_id, Integer group_id, PlateTypeEnum selected_type) {
        try (PreparedStatement statement = dB.getConnectionProjectDB().prepareStatement(
                "INSERT INTO TwoSupportSquarePlates (`id_x0`, `id_y0`, `id_x1`, `id_y1`, `id_x2`, `id_y2`, `id_x3`, `id_y3`, `level_id`, `group_id`, `selected_type`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, id_x0);
            statement.setObject(2, id_y0);
            statement.setObject(3, id_x1);
            statement.setObject(4, id_y1);
            statement.setObject(5, id_x2);
            statement.setObject(6, id_y2);
            statement.setObject(7, id_x3);
            statement.setObject(8, id_y3);
            statement.setObject(9, level_id);
            statement.setObject(10, group_id);
            statement.setObject(11, selected_type);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение 2-х опорных прямоугольных перекрытий
    public static List<TwoSupportSquarePlate> getTwoSupportSquarePlates() {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            List<TwoSupportSquarePlate> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT ID, id_x0, id_y0, id_x1, id_y1, id_x2, id_y2, id_x3, id_y3, level_id, group_id, selected_type FROM TwoSupportSquarePlates");
            while (resultSet.next()) {
                products.add(new TwoSupportSquarePlate(
                        resultSet.getInt("ID"),
                        resultSet.getInt("id_x0"),
                        resultSet.getInt("id_y0"),
                        resultSet.getInt("id_x1"),
                        resultSet.getInt("id_y1"),
                        resultSet.getInt("id_x2"),
                        resultSet.getInt("id_y2"),
                        resultSet.getInt("id_x3"),
                        resultSet.getInt("id_y3"),
                        resultSet.getInt("level_id"),
                        resultSet.getInt("group_id"),
                        Enum.valueOf(PlateTypeEnum.class, resultSet.getString("selected_type"))));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Получение 2-х опорных прямоугольных перекрытий из базы по id
    public static TwoSupportSquarePlate getTwoSupportSquarePlateByID(int id) throws SQLException {
        try (Statement statement = dB.getConnectionProjectDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, id_x0, id_y0, id_x1, id_y1, id_x2, id_y2, id_x3, id_y3, level_id, group_id, selected_type FROM TwoSupportSquarePlates WHERE ID = " + Integer.toString(id));
            TwoSupportSquarePlate twoSupportSquarePlate = new TwoSupportSquarePlate(
                    resultSet.getInt("ID"),
                    resultSet.getInt("id_x0"),
                    resultSet.getInt("id_y0"),
                    resultSet.getInt("id_x1"),
                    resultSet.getInt("id_y1"),
                    resultSet.getInt("id_x2"),
                    resultSet.getInt("id_y2"),
                    resultSet.getInt("id_x3"),
                    resultSet.getInt("id_y3"),
                    resultSet.getInt("level_id"),
                    resultSet.getInt("group_id"),
                    Enum.valueOf(PlateTypeEnum.class, resultSet.getString("selected_type")));
            return twoSupportSquarePlate;
        }
    }

    // Рисование 2-х опорных прямоугольных перекрытий из базы
    public static void drawTwoSupportSquarePlates() throws SQLException {
        double x0;
        double y0;
        double x1;
        double y1;
        double x2;
        double y2;
        double x3;
        double y3;
        graphicsContext.setStroke(twoSupportSquarePlateTypesColor);
        graphicsContext.setLineDashes(0);

        for (TwoSupportSquarePlate twoSupportSquarePlate : getTwoSupportSquarePlates()) {
            x0 = getAxisByID("AxisesX", twoSupportSquarePlate.getId_x0()).getPixels();
            y0 = getAxisByID("AxisesY", twoSupportSquarePlate.getId_y0()).getPixels();
            x1 = getAxisByID("AxisesX", twoSupportSquarePlate.getId_x1()).getPixels();
            y1 = getAxisByID("AxisesY", twoSupportSquarePlate.getId_y1()).getPixels();
            x2 = getAxisByID("AxisesX", twoSupportSquarePlate.getId_x2()).getPixels();
            y2 = getAxisByID("AxisesY", twoSupportSquarePlate.getId_y2()).getPixels();
            x3 = getAxisByID("AxisesX", twoSupportSquarePlate.getId_x3()).getPixels();
            y3 = getAxisByID("AxisesY", twoSupportSquarePlate.getId_y3()).getPixels();
            graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeSupportSideThick);
            graphicsContext.strokeLine(x0 + 0.5, y0 + 0.5, x1 + 0.5, y1 + 0.5);
            graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeLateralSideThick);
            graphicsContext.strokeLine(x1 + 0.5, y1 + 0.5, x2 + 0.5, y2 + 0.5);
            graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeSupportSideThick);
            graphicsContext.strokeLine(x2 + 0.5, y2 + 0.5, x3 + 0.5, y3 + 0.5);
            graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeLateralSideThick);
            graphicsContext.strokeLine(x3 + 0.5, y3 + 0.5, x0 + 0.5, y0 + 0.5);
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

    public int getId_x2() {
        return id_x2;
    }

    public int getId_y2() {
        return id_y2;
    }

    public int getId_x3() {
        return id_x3;
    }

    public int getId_y3() {
        return id_y3;
    }

    public int getLevel_id() {
        return level_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public PlateTypeEnum getSelected_type() {
        return selected_type;
    }

}
