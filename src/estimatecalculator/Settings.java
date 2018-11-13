/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator;

import estimatecalculator.classes.elements.PlateTypeEnum;
import estimatecalculator.enums.CustomOrPreset;
import estimatecalculator.enums.WallsMaterialsEnum;
import javafx.scene.paint.Color;

/**
 *
 * @author I
 */
public class Settings {

    // Настройки графика
    public static int prikleyka = 10;
    
    // Настройки уровней
    public static final String top_name = "Верх";
    public static final String bottom_name = "Низ";

    // Настройки линий осей
    public static double[] axisDashes = {4, 4, 10, 4};
    public static double axisWidth = 1;
    public static Color axisColor = Color.GRAY;

    // Настройки ярких линий осей
    public static double axisBrightWidth = 3;
    public static Color axisBrightColor = Color.ORANGE;

    // Настройки перекрестия при рисовании универсальной плиты перекрытия
    public static double[] axisUniversalPlateLineDashes = {4, 4, 10, 4};
    public static double axisUniversalPlateLineWidth = 1;
    public static Color axisUniversalPlateLineColor = Color.GRAY;

    // Настройки щаблонов типов перекрытий
    public static double plateSchemeThick = 10;

    // Настройки ростверков
    // Рисование обычный ростверк
    public static double[] grillageWideSet = {0, 380, 400, 500, 510, 600};
    public static Color grillageColor = Color.BLACK;
    public static double grillageSchemeThick = 20;
    // Рисование Выделенный ростверк
    public static Color grillageHilightColor = Color.YELLOW;
    public static double grillageSchemeHilightThick = 10;
    // Настройки конструктива
    public static double grillageDefaultAverage = 700; // высота по умолчанию при вводе ростверков
    
    // Настройки цоколя
    public static double socleDefaultAverage = 210;

    // Настройки рисования 2-х опорных квадратных перекрытий
    public static Color twoSupportSquarePlateTypesColor = Color.BLUE;
    public static double twoSupportSquarePlateTypesSchemeSupportSideThick = 10;
    public static double twoSupportSquarePlateTypesSchemeLateralSideThick = 3;

    // Масиив вариантов 2-х опорных квадратных перекрытий
    public static PlateTypeEnum[] TwoSupportSquarePlateTypesVariants = {PlateTypeEnum.HOLLOW_CORE_PLATE, PlateTypeEnum.WOOD_PLATE};
    
    // Настройки для выноса в индивидуальные
    public static int climaticZoneID = 1;
    public static WallsMaterialsEnum defaultMaterial = WallsMaterialsEnum.STONE_2NF; // Матерал стен выбранный пользователем
    public static CustomOrPreset defaultWallsSet = CustomOrPreset.PRESET; // Набор материалов, выьранный пользовалетем
  //  public static int fasadeWallInsulateWithThickID = 0;
  //  public static int warmWallInsulateWithThickID = 0;
  //  public static int garageWallInsulateWithThickID = 0;

}
