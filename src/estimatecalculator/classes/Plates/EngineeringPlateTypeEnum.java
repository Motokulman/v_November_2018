/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.Plates;

import static estimatecalculator.classes.bottom.BottomPane.defaultPlateTypeComboBox;
import estimatecalculator.classes.elements.PlateTypeEnum;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author I
 */
public enum EngineeringPlateTypeEnum {
    TWO_SUPPORT_RECTANGLE_PLATE("2-х опорное прямоугольное") {
        @Override
        public List<PlateTypeEnum> getAllowedPlateTypes() {
            List<PlateTypeEnum> t = FXCollections.observableArrayList();
            t.add(PlateTypeEnum.HOLLOW_CORE_PLATE);
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_SOIL_PLATE);
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_FORMWORK_PLATE);
            t.add(PlateTypeEnum.MNL_PILES_SUP_PLATE);
            t.add(PlateTypeEnum.MNL_SOIL_SUP_PLATE);
            t.add(PlateTypeEnum.WOOD_PLATE);
            return t;
        }
    },
    TWO_SUPPORT_POLYGON_PLATE("2-х опорное многоугольное") {
        @Override
        public List<PlateTypeEnum> getAllowedPlateTypes() {
            List<PlateTypeEnum> t = FXCollections.observableArrayList();
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_SOIL_PLATE);
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_FORMWORK_PLATE);
            t.add(PlateTypeEnum.MNL_PILES_SUP_PLATE);
            t.add(PlateTypeEnum.MNL_SOIL_SUP_PLATE);
            t.add(PlateTypeEnum.WOOD_PLATE);
            return t;
        }
    },
    MULTY_SUPPORT_PLATE("Многоопорное монолитное") {
        @Override
        public List<PlateTypeEnum> getAllowedPlateTypes() {
            List<PlateTypeEnum> t = FXCollections.observableArrayList();
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_SOIL_PLATE);
            t.add(PlateTypeEnum.MNL_EDGE_SUP_BY_FORMWORK_PLATE);
            t.add(PlateTypeEnum.MNL_PILES_SUP_PLATE);
            t.add(PlateTypeEnum.MNL_SOIL_SUP_PLATE);
            return t;
        }
    },
    NONE_SUPPORT_PLATE("Безопорное по грунту") {
        @Override
        public List<PlateTypeEnum> getAllowedPlateTypes() {
            List<PlateTypeEnum> t = FXCollections.observableArrayList();
            t.add(PlateTypeEnum.WOOD_PLATE);
            return t;
        }
    };

    String name; // ЧПВ имя. Можно потом вынести в настройки
    List<PlateTypeEnum> allowedPlateTypesList;

    public List<PlateTypeEnum> getAllowedPlateTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//
//    public static List<PlateTypeEnum> getTWO_SUPPORT_RECTANGLE_PLATE() {
//        return EngineeringPlateTypeEnum.TWO_SUPPORT_RECTANGLE_PLATE.getAllowedPlateTypes();
//    }
//
//    public static EngineeringPlateTypeEnum getMULTY_SUPPORT_PLATE() {
//        return MULTY_SUPPORT_PLATE;
//    }
//
//    public static EngineeringPlateTypeEnum getNONE_SUPPORT_PLATE() {
//        return NONE_SUPPORT_PLATE;
//    }

    public String getName() {
        return name;
    }

    public List<PlateTypeEnum> getAllowedPlateTypesList() {
        return allowedPlateTypesList;
    }

    EngineeringPlateTypeEnum(String n) {
        name = n;
    }

    public String getEngineeringPlateTypeEnumName() {
        return name;
    }

    // Функция получения всех типов ЧПВ
    public static List<String> getEngineeringPlateTypeEnumNamesAsList() {
        ObservableList<String> enumNames = FXCollections.observableArrayList();
        for (EngineeringPlateTypeEnum s : EngineeringPlateTypeEnum.values()) {
            enumNames.add(s.name);
        }
        return enumNames;
    }

    // Функция получения типa по его ЧПВ
    public static EngineeringPlateTypeEnum getEngineeringPlateTypeEnum(String n) {
        EngineeringPlateTypeEnum w = null;
        for (EngineeringPlateTypeEnum s : EngineeringPlateTypeEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public static List<String> getAllowedPlateTypesNames(EngineeringPlateTypeEnum p) {
        ObservableList<PlateTypeEnum> productsPlateTypes = FXCollections.observableArrayList();
        switch (p) {
            case TWO_SUPPORT_RECTANGLE_PLATE: {
                productsPlateTypes = (ObservableList<PlateTypeEnum>) EngineeringPlateTypeEnum.TWO_SUPPORT_RECTANGLE_PLATE.getAllowedPlateTypes();
                break;
            }
            case TWO_SUPPORT_POLYGON_PLATE: {
                productsPlateTypes = (ObservableList<PlateTypeEnum>) EngineeringPlateTypeEnum.TWO_SUPPORT_POLYGON_PLATE.getAllowedPlateTypes();
                break;
            }
            case MULTY_SUPPORT_PLATE: {
                productsPlateTypes = (ObservableList<PlateTypeEnum>) EngineeringPlateTypeEnum.MULTY_SUPPORT_PLATE.getAllowedPlateTypes();
                break;
            }
            case NONE_SUPPORT_PLATE: {
                productsPlateTypes = (ObservableList<PlateTypeEnum>) EngineeringPlateTypeEnum.NONE_SUPPORT_PLATE.getAllowedPlateTypes();
                break;
            }
        }
        List<String> a = new ArrayList();
        for (int i = 0; i < productsPlateTypes.size(); i++) {
            a.add(productsPlateTypes.get(i).getPlateTypeEnumName());
        }
        return a;
    }

}
