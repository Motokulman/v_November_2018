/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.elements;

/**
 *
 * @author I
 */
public enum PlateBySoilEdgeTypeEnum {
    // типы примыканий плит
    DEFORMATION_SEAM, // простой деформационный щов
    INSULATED_SEAM, // утепленный деформационный щов
    OUTER_SEAM, // внешняя сторона плиты, ничего нет
    SUPPORTED_SEAM; // этой стороной плита лежит на стене или колоннах
}
