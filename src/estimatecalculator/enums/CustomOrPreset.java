/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.enums;

import estimatecalculator.classes.elements.WallTypeByFunctionalEnum;

/**
 *
 * @author I
 */
public enum CustomOrPreset {
    PRESET("Предустановленный"),
    CUSTOM("Пользовательский");

    private String name; // ЧП имя. Можно потом вынести в настройки

    CustomOrPreset(String n) {
        name = n;
    }
    // Функция получения типa WallsSet по его ЧПВ
    public static CustomOrPreset getCustomOrPresetByName(String n) {
        CustomOrPreset w = null;
        for (CustomOrPreset s : CustomOrPreset.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public String getCustomOrPresetName() {
        return name;
    }
}
