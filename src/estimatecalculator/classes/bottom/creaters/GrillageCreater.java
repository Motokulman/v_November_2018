/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.bottom.creaters;

//Если стены стыкуются и имеют одну общую сторону, то изначально вводятся как имеющие одну ось, поскольку при ситуации когда в точке стыкуются несколько разных стен будет плохо.
//Потом создадим таблицу выравниваний.
//Если имеется уширение ростверка в одну сторону с конкретными размерами от края основного ростверка, задается как ростверк толщиной 1мм. Потом сделать заполнение
//Каждая точка, а не линия, принадлежит какой-либо оси
import static estimatecalculator.Settings.grillageColor;
import static estimatecalculator.Settings.grillageSchemeThick;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.numClick;
import static estimatecalculator.classes.ModelCanvas.stickedX;
import static estimatecalculator.classes.ModelCanvas.stickedY;
import static estimatecalculator.classes.ModelCanvas.wide;
import static estimatecalculator.classes.foundament.StretchGrillage.addStretchGrillage;
import static estimatecalculator.classes.foundament.StretchGrillage.drawStretchGrillages;
import static estimatecalculator.scheme.primitives.Axis.getAxises;
import estimatecalculator.scheme.primitives.Axis;
import static estimatecalculator.scheme.primitives.Axis.addAxis;
import static estimatecalculator.scheme.primitives.Axis.drawAxises;
import static estimatecalculator.scheme.primitives.Axis.getAxisByID;
import java.sql.SQLException;

public class GrillageCreater {

    private static int temp_x0_id = -1;
    private static int temp_y0_id = -1;
    private static int temp_x1_id = -1;
    private static int temp_y1_id = -1;

    // Функуция обработки кликов мыши, если рисуем точку
    public static void mouseClickedDrawindGrillagePoint() {
//        int temp_x_id = -1;
//        int temp_y_id = -1;
//        // Проверяем, не кликнули ли мы на уже имеющуюся ось Х
//        for (Axis axis : getAxises("AxisesX")) {
//            if (axis.getPixels() == stickedX) {// если мы кликнули на имеющуюся ось
//                temp_x_id = axis.getId(); // то запоминаем ее id
//            }
//        }
//        // Проверяем, не кликнули ли мы на уже имеющуюся ось Y
//        for (Axis axis : getAxises("AxisesY")) {
//            if (axis.getPixels() == stickedY) {// если мы кликнули на имеющуюся ось
//                temp_y_id = axis.getId(); // то запоминаем ее id
//            }
//        }
//        // теперь обработаем варианты, если на ось не попали
//        if (temp_x_id == -1) { // если совпадений с осями не было, то temp_x0_id остался = -1
//            addAxis("AxisesX", stickedX, 0); // добавляем новую ось, где натуральные размеры пока = 0
//            temp_x_id = getAxises("AxisesX").get(getAxises("AxisesX").size() - 1).getId(); // запоминаем ее id
//        }
//        if (temp_x_id == -1) {
//            addAxis("AxisesY", stickedY, 0); // добавляем новую ось, где натуральные размеры пока = 0
//            temp_y_id = getAxises("AxisesY").get(getAxises("AxisesY").size() - 1).getId(); // запоминаем ее id
//        }
//        // теперь, когда все id имеем, добавляем новое соединение 
//        addGrillagePoint(temp_x_id, temp_y_id, wide);

    }

    // Функуция обработки кликов мыши, если рисуем линию
    public static void mouseClickedDrawindGrillageLine() throws SQLException {

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
                numClick = 0;
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
                // теперь, когда все id имеем, добавление нового протяженного ростверка
                // Только для начала перепишем так, чтобы сначала щли оси с меньгими координатами
                if (getAxisByID("AxisesX", temp_x0_id).getPixels() > getAxisByID("AxisesX", temp_x1_id).getPixels()) {
                    int a = temp_x1_id;
                    temp_x1_id = temp_x0_id;
                    temp_x0_id = a;
                    a = temp_y1_id;
                    temp_y1_id = temp_y0_id;
                    temp_y0_id = a;
                }
//                if (getAxisByID("AxisesY", temp_y0_id).getPixels() < getAxisByID("AxisesY", temp_y1_id).getPixels()) {
//                    int a = temp_y1_id;
//                    temp_y1_id = temp_y0_id;
//                    temp_y0_id = a;
//                    a = temp_x1_id;
//                    temp_x1_id = temp_x0_id;
//                    temp_x0_id = a;
//
//                }
                addStretchGrillage(temp_x0_id, temp_y0_id, temp_x1_id, temp_y1_id, null, null, wide);

                drawAxises(); // Нарисуем оси, все что есть плюс что добавили
                drawStretchGrillages(); // Нарисуем ростверки все что есть плюс что добавили
                temp_x0_id = -1;
                temp_y0_id = -1;
                temp_x1_id = -1;
                temp_y1_id = -1;
                break;
            }
        }
    }

    // Функция рисования при движении мыши
    public static void mouseMoveDrawindGrillageLine() throws SQLException {
        if (numClick == 1) { // Рисуем только если один раз кликнули
            double x;
            double y;
            graphicsContext.setStroke(grillageColor);
            graphicsContext.setLineDashes(0);
            graphicsContext.setLineWidth(grillageSchemeThick);
            x = getAxisByID("AxisesX", temp_x0_id).getPixels();
            y = getAxisByID("AxisesY", temp_y0_id).getPixels();
            graphicsContext.strokeLine(x + 0.5, y + 0.5, stickedX, stickedY);
        }
    }

}
