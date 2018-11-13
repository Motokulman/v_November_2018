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
public enum SideSizeUnitsEnum {
    LONG_SIDE("Длинная сторона"),
    MEDIUM_SIDE("Средняя сторона"),
    SMALL_SIDE("Меньшая сторона");
    
    private String name; // ЧП имя. Можно потом вынести в настройки

    SideSizeUnitsEnum(String n) {
        name = n;
    }

    // Функция получения типa WallsSet по его ЧПВ
    public static SideSizeUnitsEnum getSideSizeUnitsEnum(String n) {
        SideSizeUnitsEnum w = null;
        for (SideSizeUnitsEnum s : SideSizeUnitsEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public String getSideSizeUnitsEnumName() {
        return name;
    }
}
