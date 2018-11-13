/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.bottom.creaters;

import static estimatecalculator.Settings.twoSupportSquarePlateTypesColor;
import static estimatecalculator.Settings.twoSupportSquarePlateTypesSchemeLateralSideThick;
import static estimatecalculator.Settings.twoSupportSquarePlateTypesSchemeSupportSideThick;
import static estimatecalculator.classes.ModelCanvas.defaultPlateType;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.level_id;
import static estimatecalculator.classes.ModelCanvas.numClick;
import static estimatecalculator.classes.ModelCanvas.stickedX;
import static estimatecalculator.classes.ModelCanvas.stickedY;
import static estimatecalculator.classes.Plates.TwoSupportSquarePlate.addTwoSupportSquarePlate;
import static estimatecalculator.classes.Plates.TwoSupportSquarePlate.drawTwoSupportSquarePlates;
import estimatecalculator.scheme.primitives.Axis;
import static estimatecalculator.scheme.primitives.Axis.addAxis;
import static estimatecalculator.scheme.primitives.Axis.drawAxises;
import static estimatecalculator.scheme.primitives.Axis.getAxisByID;
import static estimatecalculator.scheme.primitives.Axis.getAxises;
import java.sql.SQLException;

/**
 *
 * @author I
 */
public class TwoSupportSquarePlateCreater {

    private static int temp_x0_id = -1;
    private static int temp_y0_id = -1;
    private static int temp_x1_id = -1;
    private static int temp_y1_id = -1;
    private static int temp_x2_id = -1;
    private static int temp_y2_id = -1;
    private static int temp_x3_id = -1;
    private static int temp_y3_id = -1;


    // Функуция обработки кликов мыши, если рисуем линию
    public static void mouseClickedDrawindTwoSupportSquarePlate() throws SQLException {
        // Будем рисовать по 4-м точкам, т.к. теоретически, перекрытия могут быть под углом. Тогда идеальное совпадение осей не гарантировано
        switch (numClick) {
            case 0: {
                //    System.out.println("numClick = " + numClick);
                numClick++;
                //  System.out.println("numClick = " + numClick);
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Х
                for (Axis axis : getAxises("AxisesX")) {
                    if (axis.getPixels() == stickedX) {// если мы кликнули на имеющуюся ось
                        temp_x0_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Y
                for (Axis axis : getAxises("AxisesY")) {
                    if (axis.getPixels() == stickedY) {// если мы кликнули на имеющуюся ось
                        temp_y0_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // теперь обработаем варианты, если на ось не попали
                if (temp_x0_id == -1) { // если совпадений с осями не было, то temp_x0_id остался = -1
                    addAxis("AxisesX", stickedX, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_x0_id = getAxises("AxisesX").get(getAxises("AxisesX").size() - 1).getId(); // запоминаем ее id
                }
                if (temp_y0_id == -1) {
                    addAxis("AxisesY", stickedY, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_y0_id = getAxises("AxisesY").get(getAxises("AxisesY").size() - 1).getId(); // запоминаем ее id
                }
                //   graphicsContext.strokeOval(stickedX - prikleyka/2, stickedY - prikleyka/2, prikleyka, prikleyka);
                drawAxises();
                break;
            }
            case 1: {
                numClick++;
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Х
                for (Axis axis : getAxises("AxisesX")) {
                    if (axis.getPixels() == stickedX) {// если мы кликнули на имеющуюся ось
                        temp_x1_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Y
                for (Axis axis : getAxises("AxisesY")) {
                    if (axis.getPixels() == stickedY) {// если мы кликнули на имеющуюся ось
                        temp_y1_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // теперь обработаем варианты, если на ось не попали
                if (temp_x1_id == -1) { // если совпадений с осями не было, то temp_x1_id остался = -1
                    addAxis("AxisesX", stickedX, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_x1_id = getAxises("AxisesX").get(getAxises("AxisesX").size() - 1).getId(); // запоминаем ее id
                }
                if (temp_y1_id == -1) {
                    addAxis("AxisesY", stickedY, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_y1_id = getAxises("AxisesY").get(getAxises("AxisesY").size() - 1).getId(); // запоминаем ее id
                }
                drawAxises(); // Нарисуем оси, все что есть плюс что добавили
                // нарисуем первую опорную линию, еще не занесенную в базу
                graphicsContext.setStroke(twoSupportSquarePlateTypesColor);
                graphicsContext.setLineDashes(0);
                graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeSupportSideThick);
                graphicsContext.strokeLine(getAxisByID("AxisesX", temp_x0_id).getPixels() + 0.5, getAxisByID("AxisesY", temp_y0_id).getPixels() + 0.5, getAxisByID("AxisesX", temp_x1_id).getPixels() + 0.5, getAxisByID("AxisesY", temp_y1_id).getPixels() + 0.5);
                break;
            }
            case 2: {
                numClick++;
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Х
                for (Axis axis : getAxises("AxisesX")) {
                    if (axis.getPixels() == stickedX) {// если мы кликнули на имеющуюся ось
                        temp_x2_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Y
                for (Axis axis : getAxises("AxisesY")) {
                    if (axis.getPixels() == stickedY) {// если мы кликнули на имеющуюся ось
                        temp_y2_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // теперь обработаем варианты, если на ось не попали
                if (temp_x2_id == -1) { // если совпадений с осями не было, то temp_x1_id остался = -1
                    addAxis("AxisesX", stickedX, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_x2_id = getAxises("AxisesX").get(getAxises("AxisesX").size() - 1).getId(); // запоминаем ее id
                }
                if (temp_y2_id == -1) {
                    addAxis("AxisesY", stickedY, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_y2_id = getAxises("AxisesY").get(getAxises("AxisesY").size() - 1).getId(); // запоминаем ее id
                }
                drawAxises(); // Нарисуем оси, все что есть плюс что добавили
                // нарисуем первую боковую линию, еще не занесенную в базу
                graphicsContext.setStroke(twoSupportSquarePlateTypesColor);
                graphicsContext.setLineDashes(0);
                graphicsContext.setLineWidth(twoSupportSquarePlateTypesSchemeLateralSideThick);
                graphicsContext.strokeLine(getAxisByID("AxisesX", temp_x1_id).getPixels() + 0.5, getAxisByID("AxisesY", temp_y1_id).getPixels() + 0.5, getAxisByID("AxisesX", temp_x2_id).getPixels() + 0.5, getAxisByID("AxisesY", temp_y2_id).getPixels() + 0.5);
                break;
            }
            case 3: {
                numClick = 0;
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Х
                for (Axis axis : getAxises("AxisesX")) {
                    if (axis.getPixels() == stickedX) {// если мы кликнули на имеющуюся ось
                        temp_x3_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // Проверяем, не кликнули ли мы на уже имеющуюся ось Y
                for (Axis axis : getAxises("AxisesY")) {
                    if (axis.getPixels() == stickedY) {// если мы кликнули на имеющуюся ось
                        temp_y3_id = axis.getId(); // то запоминаем ее id
                    }
                }
                // теперь обработаем варианты, если на ось не попали
                if (temp_x3_id == -1) { // если совпадений с осями не было, то temp_x1_id остался = -1
                    addAxis("AxisesX", stickedX, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_x3_id = getAxises("AxisesX").get(getAxises("AxisesX").size() - 1).getId(); // запоминаем ее id
                }
                if (temp_y3_id == -1) {
                    addAxis("AxisesY", stickedY, 0); // добавляем новую ось, где натуральные размеры пока = 0
                    temp_y3_id = getAxises("AxisesY").get(getAxises("AxisesY").size() - 1).getId(); // запоминаем ее id
                }
                // теперь, когда все id имеем, добавление нового 2-х опорного прямоугольного перекрытия
                addTwoSupportSquarePlate(temp_x0_id, temp_y0_id, temp_x1_id, temp_y1_id, temp_x2_id, temp_y2_id, temp_x3_id, temp_y3_id, level_id, null, defaultPlateType);
                drawAxises(); // Нарисуем оси, все что есть плюс что добавили
                drawTwoSupportSquarePlates(); // Нарисуем ростверки все что есть плюс что добавили

                temp_x0_id = -1;
                temp_y0_id = -1;
                temp_x1_id = -1;
                temp_y1_id = -1;
                temp_x2_id = -1;
                temp_y2_id = -1;
                temp_x3_id = -1;
                temp_y3_id = -1;
                break;
            }
        }

    }

}
