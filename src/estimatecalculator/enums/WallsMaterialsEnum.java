/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.enums;

/**
 *
 * @author I
 */

// поду жаление
public enum WallsMaterialsEnum {
    SOLID_BRICK("Полнотелый кирпич"),
    AEROCRETE("Газобетон"),
    STONE_2NF("Камень 2.1 NF");

    private String name; // ЧП имя. Можно потом вынести в настройки

    WallsMaterialsEnum(String n) {
        name = n;
    }

    // Функция получения типa WallsSet по его ЧПВ
    public static WallsMaterialsEnum getWallsMaterialsEnumByName(String n) {
        WallsMaterialsEnum w = null;
        for (WallsMaterialsEnum s : WallsMaterialsEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public String getWallsMaterialsEnumName() {
        return name;
    }
}
