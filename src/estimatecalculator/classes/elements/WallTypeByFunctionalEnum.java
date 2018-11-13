/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

import estimatecalculator.classes.Plates.EngineeringPlateTypeEnum;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author I
 */
public enum WallTypeByFunctionalEnum {
    FASADE_WALL("Фасадная стена", "Стена с облицовкой"),
    WARM_WALL("Теплая стена", "Теплая несущая стена, например, между домом и гаражом, отличается тем, что может иметь другую облицовку"),// 
    LOADED_WALL("Несущая стена", "Несущая стена внутри дома, без облицовки"),
    PARTITION("Перегородка", "Внутридомовая перегородка, ограничивается высотой этажа"),
    GARAGE_WALL("Стена гаража", "Наружная стена смежного помещения, с меньщими требованиями по утеплению, но с облицовкой");

    private String name; // ЧП имя. Можно потом вынести в настройки
    private String comment; // ЧП имя. Можно потом вынести в настройки

    WallTypeByFunctionalEnum(String n, String c) {
        name = n;
        comment = c;
    }
    // Функция получения типa по его ЧПВ

    public static WallTypeByFunctionalEnum getWallTypeByFunctionalByName(String n) {
        WallTypeByFunctionalEnum w = null;
        for (WallTypeByFunctionalEnum s : WallTypeByFunctionalEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }
    // Функция получения всех типов в ЧПВ

    public static List<String> getWallTypeByFunctionalNamesAsList() {
        ObservableList<String> enumNames = FXCollections.observableArrayList();
        for (WallTypeByFunctionalEnum s : WallTypeByFunctionalEnum.values()) {
            enumNames.add(s.name);
        }
        return enumNames;
    }

    public String getWallTypeByFunctionalName() {
        return name;
    }
}
