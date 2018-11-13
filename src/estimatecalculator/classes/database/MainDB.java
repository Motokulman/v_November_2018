/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author I
 */
public class MainDB {

    private static final String MAIN_DB_CONNECTION = "main.db";
    private Connection connection;

    public Connection getMainDBConnection() {
        return connection;
    }

    public void MainDBConnect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + MAIN_DB_CONNECTION);
            Statement stmt = connection.createStatement();
            String sql = new String();

            // Таблица предустановленных структур стен
            sql = "CREATE TABLE  if not exists WallsStructures "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " functional_type          TEXT       NOT NULL, "
                    + " name                     TEXT       NOT NULL, "
                    + " stoneTypeID              INTEGER    NOT NULL, "// тип камня стены.
                    + " size_unit                TEXT       NOT NULL, "// единица для определения толщины стены - SideSizeUnitsEnum
                    + " insulate_with_thick_id   INTEGER    NOT NULL, "// id утеплителя с толщиной. Здесь, т.к здесь конкретная толщина и конкретный материал стены
                    + " thick                    REAL       NOT NULL )";// толщина стены в единицах выбранного типа камня
            stmt.executeUpdate(sql);

            // Таблица толщин и термических сопротивлений утеплителей по типам, без конкретного производителя, размеров, упаковки и т.д.
            sql = "CREATE TABLE  if not exists InsulateWithThick "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " insulate_id  TEXT  NOT NULL, "
                    + " thick        REAL  NOT NULL, "
                    + " r            REAL  NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица утеплителей по типам, без конкретного производителя, размеров, упаковки и т.д.
            sql = "CREATE TABLE  if not exists InsulateTypes "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name    TEXT  NOT NULL, "
                    + " lambda  REAL  NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица материалов
            sql = "CREATE TABLE  if not exists MaterialPackages "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name           TEXT    NOT NULL, "
                    + " quantity       REAL    NOT NULL, "
                    + " longSide       REAL    NOT NULL, "
                    + " mediumSide     REAL    NOT NULL, "
                    + " smallSide      REAL    NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица упаковок
            sql = "CREATE TABLE  if not exists MaterialPackages "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name           TEXT    NOT NULL, "
                    + " quantity       REAL    NOT NULL, "
                    + " longSide       REAL    NOT NULL, "
                    + " mediumSide     REAL    NOT NULL, "
                    + " smallSide      REAL    NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица производителей
            sql = "CREATE TABLE  if not exists Manufacturers "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name           TEXT    NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица поставщиков
            sql = "CREATE TABLE  if not exists Vendors "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name           TEXT    NOT NULL, "
                    + " site           TEXT    NOT NULL, "
                    + " phone          TEXT    NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица офисов продаж поставщиков
            sql = "CREATE TABLE  if not exists VendorOffices "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " id_vendor     INTEGER  NOT NULL, "
                    + " adress        TEXT     NOT NULL, "
                    + " manager       TEXT     NOT NULL, "
                    + " phone         TEXT     NOT NULL, " // Общий телефон справочной
                    + " phone_code    TEXT     NOT NULL )";
            stmt.executeUpdate(sql);

            // Таблица менеджеров с контактами каждого офиса продаж поставщика
            sql = "CREATE TABLE  if not exists VendorOfficeContacts "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " id_vendor_office    INTEGER   NOT NULL, "
                    + " adress              TEXT      NOT NULL, "
                    + " manager             TEXT      NOT NULL, "
                    + " phone               TEXT      NOT NULL, "
                    + " phone_code          TEXT      NOT NULL, "
                    + " cell_phone          TEXT      NOT NULL, "
                    + " email               TEXT      NOT NULL, "
                    + " whatsapp            TEXT      NOT NULL )";
            stmt.executeUpdate(sql);
            
            // Таблица климатических зон
            sql = "CREATE TABLE  if not exists ClimaticZones "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                    + " name      TEXT    NOT NULL, "
                    + " wall_r    REAL    NOT NULL, "// единица для определения толщины стены - SideSizeUnitsEnum
                    + " roof_r    REAL    NOT NULL, "// толщина стены в единицах выбранного типа камня
                    + " floor_r   REAL    NOT NULL, "// тип камня стены.
                    + " glassy_r  REAL    NOT NULL )";// толщина стены в единицах выбранного типа камня
            stmt.executeUpdate(sql);
            // Закрываем
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Главная база банных успешно открыта");
    }
}
