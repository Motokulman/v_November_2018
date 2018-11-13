/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.database;

import static estimatecalculator.classes.ModelCanvas.isSecant;
import static estimatecalculator.classes.ModelCanvas.isSupport;
import static estimatecalculator.classes.ModelCanvas.plate_id;
import static estimatecalculator.classes.ModelCanvas.seamType_id;
import estimatecalculator.classes.Plates.HollowCorePlate;
import estimatecalculator.classes.elements.PlateEdgeSeam;
import estimatecalculator.classes.elements.PlateForScheme;
import estimatecalculator.classes.elements.PlateTypeEnum;
import estimatecalculator.classes.elements.PlateTypeForScheme;
import estimatecalculator.classes.elements.UniversalPlatesGroup;
import estimatecalculator.classes.elements.WallOnScheme;
import estimatecalculator.scheme.primitives.Line;
import estimatecalculator.scheme.primitives.PlateEdge;
import estimatecalculator.scheme.primitives.Point;
import static estimatecalculator.classes.ModelCanvas.level_id;
import static estimatecalculator.classes.ModelCanvas.color;
import static estimatecalculator.classes.ModelCanvas.thick;
import static estimatecalculator.Settings.plateSchemeThick;
import static estimatecalculator.classes.elements.Level.addLevel;
import estimatecalculator.classes.elements.WallTypeByFunctionalEnum;
import static estimatecalculator.classes.walls.ProjectWallByFunction.addProjectWallByFunction;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.*;

// База данных для хранения проекта. На каждый проект своя такая база.
public class ProjectDB {

    // Константа, в которой хранится адрес подключения
    //   private static final String CON_STR = "jdbc:sqlite:C:\\Users\\I\\Documents\\NetBeansProjects\\Dbtest\\myDB.db";
    private static final String CON_STR = "temp.db";
    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    public Connection getConnectionProjectDB() {
        return connection;
    }

    public void ProjectDBConnect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + CON_STR);
            // Создали базу, теперь создаем таблицы
            Statement stmt = connection.createStatement();
            String sql = new String();
            
            
            // Таблица всех доступных структур стен 
            sql = "CREATE TABLE  if not exists WallsStructures "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " functional_type          TEXT      NOT NULL, "
                    + " wall_material            TEXT      NOT NULL, "// тип материала стены.WallsMaterialsEnum
                    + " size_unit                TEXT      NOT NULL, "// единица для определения толщины стены - SideSizeUnitsEnum
                    + " custom_or_preset         TEXT      NOT NULL, "// это пользователь создал или предустановка - WallsSetEnum
   //                 + " insulate_with_thick_id   INTEGER   NOT NULL, " здесь нужно вычислять R и исходя из выбранного утеплителя подбирать
                    + " thick                    REAL      NOT NULL )";// толщина стены в единицах выбранного типа камня
            stmt.executeUpdate(sql);

            // Таблица стен по назначению именно в этом проекте
            sql = "CREATE TABLE  if not exists ProjectWallsByFunction "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name                TEXT       NOT NULL, "
                    + " functional_type     TEXT       NOT NULL, "
                    + " selected_wall_structure_id   INTEGER   )"; // может и не быть выбрана в начале
            stmt.executeUpdate(sql);
            // Добавим предустановки
            addProjectWallByFunction(WallTypeByFunctionalEnum.FASADE_WALL.getWallTypeByFunctionalName(), WallTypeByFunctionalEnum.FASADE_WALL, null);
            addProjectWallByFunction(WallTypeByFunctionalEnum.WARM_WALL.getWallTypeByFunctionalName(), WallTypeByFunctionalEnum.WARM_WALL, null);
            addProjectWallByFunction(WallTypeByFunctionalEnum.LOADED_WALL.getWallTypeByFunctionalName(), WallTypeByFunctionalEnum.LOADED_WALL, null);
            addProjectWallByFunction("Тонкая перегородка", WallTypeByFunctionalEnum.PARTITION, null);
            addProjectWallByFunction("Толстая перегородка", WallTypeByFunctionalEnum.PARTITION, null);
            
            
            // Таблица стен в этом проекте
            sql = "CREATE TABLE  if not exists ProjectWallsByFunction "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " project_walls_by_function_id   INTEGER    NOT NULL, "
                    + " grillage_id                    INTEGER    NOT NULL )";
            stmt.executeUpdate(sql);
            
            // Таблица утеплителей. 
            sql = "CREATE TABLE if not exists Insulates "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " wall_structure_id   INTEGER    NOT NULL )";
            stmt.executeUpdate(sql);  
            
//            // Таблица материалов стен. 
//            sql = "CREATE TABLE if not exists WallMaterials "
//                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
//                    + " wall_structure_id   INTEGER    NOT NULL )";
//            stmt.executeUpdate(sql);

            // Таблица осей Х
            sql = "CREATE TABLE  if not exists AxisesX "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " pixels    REAL    NOT NULL, " // в пикселях канвы
                    + " size      REAL    NOT NULL )";// в миллиметрах от нулевой оси
            stmt.executeUpdate(sql);

            // Таблица осей Y
            sql = "CREATE TABLE  if not exists AxisesY "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " pixels    REAL    NOT NULL, " // в пикселях канвы
                    + " size      REAL    NOT NULL )";// в миллиметрах от нулевой оси
            stmt.executeUpdate(sql);

            // Таблица протяженных ростверков
            sql = "CREATE TABLE  if not exists StretchGrillages "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " id_x0           INTEGER    NOT NULL, "
                    + " id_y0           INTEGER    NOT NULL, "
                    + " id_x1           INTEGER    NOT NULL, "
                    + " id_y1           INTEGER    NOT NULL, "
                    + " id_xr           INTEGER            , "
                    + " id_yr           INTEGER            , "
                    + " wide            REAL       NOT NULL )"; //реальная ширина "wide". М.б. "авто" (=0) или задана точно
            stmt.executeUpdate(sql);

            // Таблица цоколей
            sql = "CREATE TABLE  if not exists BrickSocles "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " grillage_id     INTEGER    NOT NULL, "
                    + " level_id        INTEGER    NOT NULL )";
            stmt.executeUpdate(sql);

            //Таблица 2-х опорных прямоугольных перекрытий
            sql = "CREATE TABLE  if not exists TwoSupportSquarePlates "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," //id перекрытия
                    + " id_x0           INTEGER    NOT NULL, "
                    + " id_y0           INTEGER    NOT NULL, "
                    + " id_x1           INTEGER    NOT NULL, "
                    + " id_y1           INTEGER    NOT NULL, "
                    + " id_x2           INTEGER    NOT NULL, "
                    + " id_y2           INTEGER    NOT NULL, "
                    + " id_x3           INTEGER    NOT NULL, "
                    + " id_y3           INTEGER    NOT NULL, "
                    + " level_id        INTEGER    NOT NULL, " //какому из уровней принадлежит данное перекрытие
                    + " group_id        INTEGER            , " //какой из групп принадлежит данное перекрытие
                    + " selected_type   TEXT       NOT NULL )";       //тип перекрятия, выбранный из списка подмен
            stmt.executeUpdate(sql);

            // Таблица типов плитных элементов для рисования схемы
            // РЕАЛЬНЫЕ ПЛИТЫ, ГДЕ НЕТ УНИВЕРСАЛЬНОГО ПЕРЕКРЫТИЯ, И ЕСТЬ НАТУРАЛЬНЫЕ РАЗМЕРЫ ХРАНИТЬ В ДРУГОЙ ТАБЛИЦЕ!! (СОЗЖАТЬ ЕЕ НАДО)
            sql = "CREATE TABLE  if not exists PlateTypesForScheme "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " type           TEXT    NOT NULL, "
                    + " type_name      TEXT    NOT NULL, "
                    + " default_color  TEXT            , "
                    + " default_thick  REAL             )";

            stmt.executeUpdate(sql);
            // Сразу добавим в нее типы плитных элементов и их цвета на схеме
            // Color c = Color.DARKGREEN;
            //        addPlateTypesForScheme(new PlateTypeForScheme(PlateTypeEnum.NONE, "Не задано", null, null));

            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.UNIVERSAL, "Универсальное-ПК", BLUE, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.MNL_EDGE_SUP_BY_SOIL_PLATE, "МП грунт-края", RED, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.MNL_EDGE_SUP_BY_FORMWORK_PLATE, "МП опалубка-края", AQUA, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.MNL_SOIL_SUP_PLATE, "МП грунт-грунт", CHOCOLATE, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.MNL_PILES_SUP_PLATE, "МП грунт-сваи", DARKCYAN, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.WOOD_PLATE, "Деревянное", BROWN, plateSchemeThick));
            addPlateTypesForScheme(
                    new PlateTypeForScheme(PlateTypeEnum.GRILLAGE_PART, "Часть ростверка", BLACK, plateSchemeThick));

            // Таблица всех плитных элементов на схеме, здесь указывается тип UNIVERSAL вместо HOLLOW_CORE_PLATE
            sql = "CREATE TABLE  if not exists PlatesForScheme "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " type                  TEXT     NOT NULL, " // кроме HOLLOW_CORE_PLATE
                    + " default_type          TEXT             , " // кроме UNIVERSAL
                    + " name                  TEXT             , " // имя самого перекрытия, а не название типа, иначе все будут называться одинаково
                    + " comment               TEXT             , " // пояснение, комментарий. Вдруг понадобится, пусть будет
                    + " universal_group_id    INTEGER          , " // id универсальной группы, если перекрытие в группе
                    + " level_id              INTEGER  NOT NULL, " // привязка к уровню с заданным id
                    + " color                 TEXT     NOT NULL, "
                    + " thick                 REAL     NOT NULL, "
                    + " FOREIGN KEY(type) REFERENCES Levels(ID), "
                    + " FOREIGN KEY(type) REFERENCES PlateTypesForScheme(type))";

            stmt.executeUpdate(sql);

            // Таблица групп универсальных плит
            // уровень здесь рещил не указывать, т.к. он м.б. разным в одной группе
            sql = "CREATE TABLE  if not exists GroupsOfUniversalPlates "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name  TEXT    NOT NULL)";  // имя группы, заданное пользователем

            stmt.executeUpdate(sql);
            // Сразу добавим в нее пару групп 
            //           addUniversalPlatesGroup("Без группы", PlateTypeEnum.THIS_PLATE, 0); Все универсальные в группах. Их не много

            addUniversalPlatesGroup(
                    "Группа не задана");
            addUniversalPlatesGroup(
                    "Перекрытие 1-го этажа");
            addUniversalPlatesGroup(
                    "Перекрытие 2-го этажа");

            // Таблица соответствия универсальных плит группам
            sql = "CREATE TABLE  if not exists UniversalPlatesAccordanceGroup "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " universal_plate_id           INTEGER    NOT NULL,"
                    + " group_id           INTEGER    NOT NULL, "
                    + " FOREIGN KEY(universal_plate_id) REFERENCES PlatesForScheme(ID), "
                    + " FOREIGN KEY(group_id) REFERENCES GroupsOfUniversalPlates(ID))";

            stmt.executeUpdate(sql);

            // Таблица перекрытий из пустотных плит. Перекрытие, ограниченное параллелепипедом, состоящее из одной или более плит ПК, хранящихся в других таблицах
            sql = "CREATE TABLE  if not exists HollowCorePlates "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " plates_scheme_id  INTEGER    NOT NULL, " // какому перекрытию на схеме оно соответствует. Универсальному может соответствовать несколько разного типа перекрытий
                    + " height  REAL    NOT NULL," // Толщина перекрытия
                    + " FOREIGN KEY(plates_scheme_id) REFERENCES PlatesForScheme(ID))"; // установим ключ к таблице перекрытий на схеме

            stmt.executeUpdate(sql);

            // Таблица ребер плитных элементов
            sql = "CREATE TABLE  if not exists PlateEdges "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " plate_id     INTEGER    NOT NULL, " // id перекрытия, которому принадлежат эти точки
                    + " x0           REAL       NOT NULL, "
                    + " y0           REAL       NOT NULL, "
                    + " x1           REAL       NOT NULL, "
                    + " y1           REAL       NOT NULL, "
                    + " xr           REAL               , " // для радиусов, на будущее
                    + " yr           REAL               , "
                    + " Real_X0      REAL       NOT NULL, " // натуральные размеры, мм
                    + " Real_Y0      REAL       NOT NULL, "
                    + " Real_X1      REAL       NOT NULL, "
                    + " Real_Y1      REAL       NOT NULL, "
                    + " Real_Xr      REAL               , " // для радиусов, на будущее
                    + " Real_Yr      REAL               , "
                    + " isSecant     INTEGER    NOT NULL, " // Является ли ребро секущим по отношению к нижележащим стенам. Не м.б. нулл, т.к. всегда будет проверка на секучесть
                    + " isSupport    INTEGER            , " // Является ли ребро опорным
                    + " seamType_id  INTEGER            , " // тип шва из таблицы швов
                    + " FOREIGN KEY(plate_id) REFERENCES PlatesForScheme(ID))";

            stmt.executeUpdate(sql);

            // Таблица уровней
            sql = "CREATE TABLE  if not exists Levels"
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name           TEXT       NOT NULL, "
                    + " is_this_top    INTEGER    NOT NULL, " // учесть ли в расчетах верх данного уровня 
                    + " is_bottom_top  INTEGER    NOT NULL, " // учесть ли в расчетах верх нижележащего уровня 
                    + " altitude       REAL       NOT NULL, " // высота уровня относительно нижележащего уровня
                    + " bottom_level   INTEGER    NOT NULL )"; // указание на нижележащий уровень, id

            stmt.executeUpdate(sql);
            // Добавим сразу несколько уровней

            addLevel("Грунт", 0, 0, 0, 0);
            addLevel("Ростверк", 1, 1, 500, 1);
            //    addLevel("Базовый цоколь", 210, 2);
            //    addLevel("Дополнительный цоколь", 210, 2);
            //    addLevel("Высота потолка 1 этажа", 210, 2);

            // Таблица швов
            sql = "CREATE TABLE  if not exists PlateEdgesSeams"
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + " name  TEXT    NOT NULL )";

            stmt.executeUpdate(sql);
            // Добавим сразу несколько типов швов

            addPlateEdgesSeam(
                    new PlateEdgeSeam("Простой шов"));
            addPlateEdgesSeam(
                    new PlateEdgeSeam("Шов с рубероидом"));
            addPlateEdgesSeam(
                    new PlateEdgeSeam("Шов со вспененным п/э"));
            addPlateEdgesSeam(
                    new PlateEdgeSeam("Утепленный шов 50мм"));

            // Таблица атрибутов линий монолитных плит с опиранием по грунту
            sql = "CREATE TABLE  if not exists MNLPlateSoilSupPlateEdgesAttr"
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " line_id           INTEGER    NOT NULL, " // id линии, которой принадлежат эти аттрибуты
                    + " edge_type  TEXT    NOT NULL )"; // тип ребра PlateBySoilEdgeTypeEnum

            stmt.executeUpdate(sql);

            // Таблица атрибутов линий монолитных плит с опиранием на сваи
            sql = "CREATE TABLE  if not exists PlateByPilesPlateEdgesAttr"
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " line_id           INTEGER    NOT NULL, " // id линии, которой принадлежат эти аттрибуты
                    + " edge_type  TEXT    NOT NULL )"; // тип ребра PlateBySoilEdgeTypeEnum

            stmt.executeUpdate(sql);

            // Таблица временных точек 
            sql = "CREATE TABLE  if not exists TempPoints "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " x           REAL    NOT NULL, "
                    + " y           REAL    NOT NULL )";

            stmt.executeUpdate(sql);

            // Таблица временных линий для рисования
            sql = "CREATE TABLE  if not exists TempLines "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " x0           REAL    NOT NULL, "
                    + " y0           REAL    NOT NULL, "
                    + " x1           REAL    NOT NULL, "
                    + " y1           REAL    NOT NULL )";

            stmt.executeUpdate(sql);

            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println(
                "Opened database successfully");
    }

    // Добавление ребра 
    public void addPlateEdge(Point point_1, Point point_2) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO PlateEdges(`plate_id`, `x0`, `y0`, `x1`, `y1`, `xr`, `yr`, `Real_X0`, `Real_Y0`, `Real_X1`, `Real_Y1`, `Real_Xr`, `Real_Yr`,"
                + " `isSecant`, `isSupport`, `seamType_id`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, plate_id);
            statement.setObject(2, point_1.getX());
            statement.setObject(3, point_1.getY());
            statement.setObject(4, point_2.getX());
            statement.setObject(5, point_2.getY());
            statement.setObject(6, 0);
            statement.setObject(7, 0);
            statement.setObject(8, point_1.getX());
            statement.setObject(9, point_1.getY());
            statement.setObject(10, point_2.getX());
            statement.setObject(11, point_2.getY());
            statement.setObject(12, 0);
            statement.setObject(13, 0);
            statement.setObject(14, isSecant);
            statement.setObject(15, isSupport);
            statement.setObject(16, seamType_id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление перекрытия ПК 
    public void addHollowCorePlate(HollowCorePlate plate) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO HollowCorePlates(`plates_scheme_id`, `height`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, plate.getPlates_scheme_id());
            statement.setObject(2, plate.getHeight());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление типа шва
    public void addPlateEdgesSeam(PlateEdgeSeam seam) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO PlateEdgesSeams(`name`) "
                + "VALUES(?)")) {
            statement.setObject(1, seam.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение типа шва
    public List<PlateEdgeSeam> getPlateEdgesSeams() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlateEdgeSeam> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM PlateEdgesSeams");
            while (resultSet.next()) {
                products.add(new PlateEdgeSeam(
                        resultSet.getString("name")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Добавление записи о соответствии универсального перекрытия группе
    public void addUniversalPlateAccordanceGroup(int universal_plate_id, int group_id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO UniversalPlatesAccordanceGroup(`universal_plate_id`, `group_id`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, universal_plate_id);
            statement.setObject(2, group_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех перекрятий для схемы
    public List<PlateForScheme> getPlatesForScheme() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlateForScheme> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id, type, default_type, name, comment, universal_group_id, level_id, color, thick FROM PlatesForScheme");

            while (resultSet.next()) {
                products.add(new PlateForScheme(
                        resultSet.getInt("id"),
                        Enum.valueOf(PlateTypeEnum.class,
                                resultSet.getString("type")),
                        Enum.valueOf(PlateTypeEnum.class,
                                resultSet.getString("default_type")),
                        resultSet.getString("name"),
                        resultSet.getString("comment"),
                        resultSet.getInt("universal_group_id"),
                        resultSet.getInt("level_id"),
                        Color.web(resultSet.getString("color")),
                        resultSet.getDouble("thick")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Получение списка ребер
    public List<PlateEdge> getPlateEdges() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlateEdge> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id, plate_id, x0, y0, x1, y1, xr, yr, Real_X0, Real_Y0, Real_X1, Real_Y1, Real_Xr, Real_Yr,"
                    + " isSecant, isSupport, seamType_id FROM PlateEdges");
            while (resultSet.next()) {
                products.add(new PlateEdge(
                        resultSet.getInt("id"),
                        resultSet.getInt("plate_id"),
                        resultSet.getDouble("x0"),
                        resultSet.getDouble("y0"),
                        resultSet.getDouble("x1"),
                        resultSet.getDouble("y1"),
                        resultSet.getDouble("xr"),
                        resultSet.getDouble("yr"),
                        resultSet.getDouble("Real_X0"),
                        resultSet.getDouble("Real_Y0"),
                        resultSet.getDouble("Real_X1"),
                        resultSet.getDouble("Real_Y1"),
                        resultSet.getDouble("Real_Xr"),
                        resultSet.getDouble("Real_Yr"),
                        resultSet.getBoolean("isSecant"),
                        resultSet.getBoolean("isSupport"),
                        resultSet.getInt("seamType_id")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Добавление временной линии в БД
    public void addTempLine(Line line) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO TempLines(`x0`, `y0`, `x1`, `y1`) "
                + "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, line.getX0());
            statement.setObject(2, line.getY0());
            statement.setObject(3, line.getX1());
            statement.setObject(4, line.getY1());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление атрибутов ребра универсального перекрытия
    public void addUniversalPlateEdgeAttr(int id, boolean sup, boolean sec) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO TempLines(`x0`, `y0`, `x1`, `y1`) "
                + "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, id);
            statement.setObject(2, sup);
            statement.setObject(3, sec);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавление перекрытия в таблицу перекрытий схемы
    public void addPlateForScheme(PlateTypeEnum type, PlateTypeEnum default_type, String name, String comment, int universal_group_id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO PlatesForScheme(`type`, `default_type`, `name`, `comment`, `universal_group_id`, `level_id`, `color`, `thick`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, type);
            statement.setObject(2, default_type);
            statement.setObject(3, name);
            statement.setObject(4, comment);
            statement.setObject(5, universal_group_id);
            statement.setObject(6, level_id);
            statement.setObject(7, color);
            statement.setObject(8, thick);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление в таблицу новой группы универсальных перекрытий
    public void addUniversalPlatesGroup(String name) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO GroupsOfUniversalPlates(`name`) "
                + "VALUES(?)")) {
            statement.setObject(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение списка имеющихся групп перекрытий
    public List<UniversalPlatesGroup> getUniversalPlatesGroups() {
        try (Statement statement = this.connection.createStatement()) {
            List<UniversalPlatesGroup> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM GroupsOfUniversalPlates");

            while (resultSet.next()) {
                products.add(new UniversalPlatesGroup(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Добавление временной точки в БД
    public void addTempPoint(Point point) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO TempPoints(`x`, `y`) "
                + "VALUES(?, ?)")) {
            statement.setObject(1, point.getX());
            statement.setObject(2, point.getY());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Удаление временной точки в БД
    public void deleteTempPoint() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM TempPoints")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Удаление временной линии в БД

    public void deleteTempLine() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM TempLines")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Добавление стены для отрисовки схемы
    public void addSchemeWalls(WallOnScheme wallOnScheme) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO SchemeWalls(`x0`, `y0`, `x1`, `y1`, `type`, `color`, `thick`) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, wallOnScheme.getX0());
            statement.setObject(2, wallOnScheme.getY0());
            statement.setObject(3, wallOnScheme.getX1());
            statement.setObject(4, wallOnScheme.getY1());
            statement.setObject(5, wallOnScheme.getType());
            statement.setObject(6, wallOnScheme.getColor());
            statement.setObject(7, wallOnScheme.getThick());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // получаем стены для отрисовки схемы
    public List<WallOnScheme> getSchemeWalls() {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши соединения, полученные из БД
            List<WallOnScheme> products = new ArrayList<>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT x0, y0, x1, y1, type, color, thick FROM SchemeWalls");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new WallOnScheme( //(resultSet.getInt("id"),
                        resultSet.getDouble("x0"),
                        resultSet.getDouble("y0"),
                        resultSet.getDouble("x1"),
                        resultSet.getDouble("y1"),
                        resultSet.getString("type"),
                        Color.web(resultSet.getString("color")),
                        resultSet.getDouble("thick")));
            }
            // Возвращаем наш список
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление типов перекрытий
    public void addPlateTypesForScheme(PlateTypeForScheme pType) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO PlateTypesForScheme(`type`, `type_name`, `default_color`, `default_thick`) "
                + "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, pType.getType());
            statement.setObject(2, pType.getTypeName());
            statement.setObject(3, pType.getColor());
            statement.setObject(4, pType.getThick());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение типов перекрытий
    public List<PlateTypeForScheme> getPlateTypesForScheme() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlateTypeForScheme> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT type, type_name, default_color, default_thick FROM PlateTypesForScheme");

            while (resultSet.next()) {
                products.add(new PlateTypeForScheme( //(resultSet.getInt("id"),
                        Enum.valueOf(PlateTypeEnum.class,
                                resultSet.getString("type")),
                        resultSet.getString("type_name"),
                        Color.web(resultSet.getString("default_color")),
                        resultSet.getDouble("default_thick")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // получаем временные точки из базы
    public List<Point> getTempPoints() {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши точки, полученные из БД
            List<Point> products = new ArrayList<>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT x, y FROM TempPoints");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
//                products.add(new Point(
//                        resultSet.getInt("x"),
//                        resultSet.getInt("y")));
            }
            // Возвращаем наш список
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // получаем временные линии из базы
    public List<Line> getTempLines() {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши соединения, полученные из БД
            List<Line> products = new ArrayList<>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT x0, y0, x1, y1 FROM TempLines");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new Line(
                        resultSet.getInt("x0"),
                        resultSet.getInt("y0"),
                        resultSet.getInt("x1"),
                        resultSet.getInt("y1")));
            }
            // Возвращаем наш список
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

// Удаление временной базы данных
    public void deleteTempDB() throws SQLException {
        connection.close(); // Закрываем соединение с бд, иначе не удалится
        // Удаляем
        File file = new File(CON_STR);
        if (file.delete()) {
            System.out.println("Файл временной БД удален");
        } else {
            System.out.println("Файл временной БД удалить не получилось");
        }

    }

}
