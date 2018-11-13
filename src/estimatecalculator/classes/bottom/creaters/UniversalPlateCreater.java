/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.bottom.creaters;

import static estimatecalculator.EstimateCalculator.project_dB;
import static estimatecalculator.classes.ModelCanvas.axisCross;
import static estimatecalculator.classes.ModelCanvas.plate_id;
import static estimatecalculator.classes.ModelCanvas.name;
import static estimatecalculator.classes.ModelCanvas.comment;
import static estimatecalculator.classes.ModelCanvas.isSupport;
import estimatecalculator.scheme.primitives.Point;
import static estimatecalculator.classes.ModelCanvas.stickedX;
import static estimatecalculator.classes.ModelCanvas.stickedY;
import static estimatecalculator.classes.ModelCanvas.univers_group_id;
import estimatecalculator.classes.Plates.HollowCorePlate;
import estimatecalculator.classes.elements.PlateTypeEnum;

/**
 *
 * @author I
 */
public class UniversalPlateCreater {

    private static int numClick = 0;
    private static Point point_1 = new Point();// Создаем первую точку
    private static Point point_2 = new Point();
    private static Point point_3 = new Point();
    // Функуия обработки кликов мыщи
    public static void mouseClickedDrawindUniversalPlate() {
        switch (numClick) {
            case 0: {
                numClick++;
                point_1.setPoint(stickedX, stickedY); // Заносим координаты в первую точку
                axisCross.drawAxisCross(point_1); //рисуем перекрестие
                point_1.drawPoint(); // нарисуем точку
                // Добавляем запись в таблицу всех перекрытий на схеме
                project_dB.addPlateForScheme(PlateTypeEnum.UNIVERSAL, PlateTypeEnum.HOLLOW_CORE_PLATE, name, comment, univers_group_id);
                // Получаем id только что добавленного универсального перекрытия. Нельзя просто взять размер массива, поскольку перекрытия могут удаляться
                plate_id = project_dB.getPlatesForScheme().get(project_dB.getPlatesForScheme().size() - 1).getId();
                // Добавляем перекрытие типа ПК, т.к. оно по умолчанию для универсальных
                project_dB.addHollowCorePlate(new HollowCorePlate(plate_id, 220));
                break;
            }
            case 1: {
                numClick++;
                point_2.setPoint(stickedX, stickedY);// Заносим координаты во вторую точку
                axisCross.drawAxisCross(point_2); //рисуем перекрестие
                point_2.drawPoint();
                // Добавляем ребро
                isSupport = true;
                project_dB.addPlateEdge(point_1, point_2);
                break;
            }
            case 2: {
                numClick = 0;
                point_3.setPoint(stickedX, stickedY);
                axisCross.drawAxisCross(point_3);
                point_3.drawPoint();
                // Добавляем ребро
                isSupport = false;
                project_dB.addPlateEdge(point_2, point_3);
                break;
                // Нет смысла ничего больше добавлять. Такое перекрытие задается тремя точками.
            }
        }
    }
}
