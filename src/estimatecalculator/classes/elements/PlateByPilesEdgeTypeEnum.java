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
public enum PlateByPilesEdgeTypeEnum {
        // типы примыканий плит
    DEFORMATION_SEAM, // простой деформационный щов
    INSULATED_SEAM, // утепленный деформационный щов
    CONJOINT_SEAM, // щва нет, по этоу грани плита монолитится с ростверком
    OUTER_SEAM, // внешняя сторона плиты, ничего нет
}
