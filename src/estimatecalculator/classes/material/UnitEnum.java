/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material;

/**
 *
 * @author I
 */
public enum UnitEnum {
    KILOGRAM("Килограмм"),
    TON("Тонна");

    private String name; // ЧП имя. Можно потом вынести в настройки
   // private static String N; // ЧП имя. Можно потом вынести в настройки

    UnitEnum(String n) {
        name = n;
    }
        // Функция получения типa по его ЧПВ
    public static UnitEnum getUnitEnumByName(String n) {
        UnitEnum w = null;
        for (UnitEnum s : UnitEnum.values()) {
            if (n.equals(s.name)) {
                w = s;
            }
        }
        return w;
    }

    public String getUnitEnumName() {
        return name;
    }
//    public static String getN(String name) {
//        N = name;
//        return N;
//    }
}
