/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

import estimatecalculator.classes.Plates.EngineeringPlateTypeEnum;

/**
 *
 * @author I
 */
public enum PlateTypeEnum {
    NONE(""),
    UNIVERSAL(""), // Универсальное перекрытие. Рисуется как параллелепипед, имеющий две опорные стороны. Потом м.б.пустотная плита, МП по грунту, опалубке, деревянное
    HOLLOW_CORE_PLATE("Пустотная плита"), // пустотная плита
    MNL_EDGE_SUP_BY_SOIL_PLATE("Монолитная напряженная плита по грунту"), // монолитная плита с опиранием по краям, отливаемая по грунту
    MNL_EDGE_SUP_BY_FORMWORK_PLATE("Монолитная напряженная плита по опалубке"), // монолитная плита с опиранием по краям, отливаемая по опалубке
    MNL_PILES_SUP_PLATE("Монолитная напряженная плита по сваям"), // монолитная плита с опиранием на сваи
    MNL_SOIL_SUP_PLATE("Монолитная стяжка по грунту"), // монолитная плита с опиранием по грунту
    GRILLAGE_PART(""), // монолитная плита как часть ростверка. Приливы к роствеку под печь и т.д.
    WOOD_PLATE("Деревянное перекрытие");
    // THIS_PLATE(""); // Тип для замены поля в таких местах, как поле по умолчанию, если это не универсальное перекрытие и в группе "без группы"

    private String name; // ЧП имя. Можно потом вынести в настройки
    private static String N; // ЧП имя. Можно потом вынести в настройки

    PlateTypeEnum(String n) {
        name = n;
    }
        // Функция получения типa по его ЧПВ
    public static PlateTypeEnum getPlateTypeEnumByName(String n) {
        PlateTypeEnum w = null;
        for (PlateTypeEnum s : PlateTypeEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public String getPlateTypeEnumName() {
        return name;
    }
    public static String getN(String name) {
        N = name;
        return N;
    }
}
