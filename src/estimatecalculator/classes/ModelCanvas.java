/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes;

import static estimatecalculator.EstimateCalculator.dB;
import static estimatecalculator.Settings.prikleyka;
import estimatecalculator.classes.Plates.EngineeringPlateTypeEnum;
import static estimatecalculator.classes.Plates.TwoSupportSquarePlate.drawTwoSupportSquarePlates;
import static estimatecalculator.classes.bottom.BottomPane.colorPicker;
import static estimatecalculator.classes.bottom.BottomPane.thickComboBox;
import static estimatecalculator.classes.bottom.BottomPane.wallTypeComboBox;
import static estimatecalculator.classes.bottom.creaters.GrillageCreater.mouseClickedDrawindGrillageLine;
import static estimatecalculator.classes.bottom.creaters.GrillageCreater.mouseMoveDrawindGrillageLine;
import static estimatecalculator.classes.bottom.creaters.TwoSupportSquarePlateCreater.mouseClickedDrawindTwoSupportSquarePlate;
import static estimatecalculator.classes.bottom.creaters.UniversalPlateCreater.mouseClickedDrawindUniversalPlate;
import estimatecalculator.classes.elements.PlateTypeEnum;
import estimatecalculator.classes.elements.WallOnScheme;
import static estimatecalculator.classes.foundament.StretchGrillage.drawStretchGrillages;
import static estimatecalculator.classes.foundament.StretchGrillage.mouseMovedHilightStretchGrillage;
import estimatecalculator.scheme.primitives.Axis;
import static estimatecalculator.scheme.primitives.Axis.drawAxises;
import static estimatecalculator.scheme.primitives.Axis.drawBrightAxisX;
import static estimatecalculator.scheme.primitives.Axis.drawBrightAxisY;
import static estimatecalculator.scheme.primitives.Axis.getAxises;
import estimatecalculator.scheme.primitives.AxisCross;
import static java.lang.Math.abs;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.GRAY;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author I
 */
public class ModelCanvas extends Canvas {

    public static double modelCanvasWidth = 600;
    public static double modelCanvasHeight = 600;
    public static double wide = 0;
    public static GraphicsContext graphicsContext;
    public static int numClick = 0; // Для подсчета какой клик по счету
    private double firstX = 0;
    private double firstY = 0;
    public static Color color = Color.BLUE;
    public static String wallType; // Выбранный тип стены
    public static String plateType;// Выбранный тип перекрытия
    public static double stickedX; // Для приклейки курсора
    public static double stickedY; // Для приклейки курсора
    public static int level_id; // id уровня (перекрытия)
    public static double level; // Сам уровень в мм (перекрытия)
    public static double thick; // Толщина линии для рисования на схеме
    public static double average; // Расстояние от заданного уровня
    private static boolean flagMSP = false; // флаг для определения, рисуем ли мы многоопорное переурытие или уже закончили его рисовать
    public static String whatDrawNow = "GrillageLine";//"NONE";// Это то, что выбрано для рисования нового элемента сейчас
    public static String whatRestoreDraw = "Wall";// Это то, что рисуем доставая из базы
    public static String name = "Без имени";// Имя элемента, активного сейчас. Изначально нужно при создании нового, потом не знаю
    public static String comment;// пояснение, комментарий. Вдруг понадобится, пусть будет
    public static Integer univers_group_id = 0;// Группа универсальных плит 
    public static Integer seamType_id = 0;// id типа шва
    public static Integer projectWallsByFunction_id = 0;// id типа стены проекта
    public static int plate_id; // Перекрытие, которое сейчас добавляем
    public static int edge_id; // Ребро, которое сейчас добавляем
    public static boolean isSupport = true; // флаг для определения, является ли данное ребро опорным
    public static boolean isSecant = true; // флаг для определения, является ли данное ребро секущим по отношению к нижележащим
    public static AxisCross axisCross = new AxisCross();
    public static double offsetX = 0; // смещение в пикселях при рисовании схемы
    public static double offsetY = 0;
    public static EngineeringPlateTypeEnum engineeringPlateTypeEnum;
    public static PlateTypeEnum defaultPlateType = null;
    
    public ModelCanvas() {
        this.setWidth(modelCanvasWidth);
        this.setHeight(modelCanvasHeight);
        graphicsContext = this.getGraphicsContext2D();
        clearModelCanvas();
        setOnMouseEvent();
        wallType = wallTypeComboBox.getSelectionModel().getSelectedItem().toString();
        graphicsContext.setLineCap(StrokeLineCap.BUTT);
    }

    private void setOnMouseEvent() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (whatDrawNow == "GrillageLine") {
                    try {
                        mouseClickedDrawindGrillageLine();
                    } catch (SQLException ex) {
                        Logger.getLogger(ModelCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (whatDrawNow == "TwoSupportSquarePlate") {
                    try {
                        mouseClickedDrawindTwoSupportSquarePlate();
                    } catch (SQLException ex) {
                        Logger.getLogger(ModelCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (whatDrawNow == PlateTypeEnum.UNIVERSAL.toString()) {
                    mouseClickedDrawindUniversalPlate();
                }
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    mouseMoved(e); // Ксли ничего пока не делаем
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (whatDrawNow == "GrillageLine") {
                    try {
                        mouseMoveDrawindGrillageLine();
                    } catch (SQLException ex) {
                        Logger.getLogger(ModelCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (whatDrawNow == "Socle") {
                    try {
                        mouseMovedHilightStretchGrillage();
                    } catch (SQLException ex) {
                        Logger.getLogger(ModelCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (whatDrawNow == "MultiSupportPlate") {
                    //               mouseMovedDrawingMultiSupportPlate(e);
                } else if (whatDrawNow == "Wall") {
                    mouseMovedDrawingWall(e);
                }
            }
        });
    }

//    // Обработка кликов при многоопорном перекрытии
//    private void mouseClickedDrawindMultiPlate() {
//        int msp_id = dB.getMSP().size();// Определяем id перекрытия, с которым работаем. id равен размеру базы многоопорных перекрытий       color = dB.getPlateColor(plateType);
//        graphicsContext.setLineDashes(7, 3);
//        drawPlateAxisCross(stickedX, stickedY); // Рисуем перекрестие
//        drawPoint(stickedX, stickedY, thick); // Рисуем точку
//        if (flagMSP == false) {
//            dB.addMSP(level, color, thick);// добавляем перекрытие в таблицу многоопорных перекрытий
//            flagMSP = true;
//        }
//
//        dB.addPointMSP(new MSPPoint(dB.getMSP().size(), stickedX, stickedY));// Заносим в базу многоопорных перекрытий нашу точку. В качестве id заносится размер базы многоопорных
//        // перекрытий, т.к. размер и есть последний id
//        if ((dB.getPointsOneMSP(msp_id).size() != 0) && (dB.getPointsOneMSP(msp_id).get(0).getX() == stickedX) && (dB.getPointsOneMSP(msp_id).get(0).getY() == stickedY)) {
//            flagMSP = false;
//        }
//    }
    //Функуция обработки движения мыши при рисовании многоопорного перекрытия
//    private void mouseMovedDrawingMultiSupportPlate(MouseEvent e) {
//        // System.out.println("flagMSP = " + flagMSP);
//        int msp_id = dB.getMSP().size();// Определяем id перекрытия, с которым работаем. id равен размеру базы многоопорных перекрытий
//        int current_MSP_NumPoints = dB.getPointsOneMSP(msp_id).size();
//        if (flagMSP == true) {
//            drawAll();
//            stickCursor(e.getX(), e.getY());
//            graphicsContext.setLineDashes(7, 3);
//            if (current_MSP_NumPoints != 0) {
//                System.out.println("dB.getPointsOneMSP(msp_id).size() = " + dB.getPointsOneMSP(msp_id).size());
//                drawLine(dB.getPointsOneMSP(msp_id).get(current_MSP_NumPoints - 1).getX(), dB.getPointsOneMSP(msp_id).get(current_MSP_NumPoints - 1).getY(), stickedX, stickedY);
//            }
//        }
//    }
//
//    // Функция рисования всех многоопорных перекрытий, которые уже есть в базе
//    private static void drawAllMSPs() {
//        for (int i = 1; i <= dB.getMSP().size(); i++) {
//            SetGCifRestore("MultiSupportPlate", "MULTI_SUPPORT", i - 1);
//            for (int j = 1; j < dB.getPointsOneMSP(i).size(); j++) {
//                graphicsContext.strokeLine(dB.getPointsOneMSP(i).get(j).getX(), dB.getPointsOneMSP(i).get(j).getY(), dB.getPointsOneMSP(i).get(j - 1).getX(), dB.getPointsOneMSP(i).get(j - 1).getY());
//            }
//        }
//    }
    // Функуия обработки кликов мыщи при рисовании 2-х опорного перекрытия
//    private void mouseClickedDrawind2Plate() {
//        switch (numClick) {
//            case 0: {
//                numClick++;
//                dB.addTempPoint(new Point(stickedX, stickedY));// Заносим во временную базу нашу первую точку
//                // Рисуем точку. Пока мы не знаем, куда будет второй клик, поэтому проверять не имеет смысла
//                drawPoint(stickedX, stickedY, thick);// Рисуем точку
//                drawPlateAxisCross(stickedX, stickedY); // Рисуем перекрестие. Можно бы и не рисовать, но вдруг надо явно задать, что перекрытие не начинается сразу от стены?
//                break;
//            }
//            case 1: {
//                numClick++;
//                dB.addTempPoint(new Point(stickedX, stickedY));// Заносим во временную базу нашу вторую точку
//                dB.addTempLine(new Line(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(), stickedX, stickedY));
//                drawPlateAxisCross(stickedX, stickedY); // Рисуем перекрестие перекрытия
//                drawLine(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(), stickedX, stickedY);// Рисуем первую линию
//                drawPlateAxisCross(stickedX, stickedY); // Рисуем перекрестие. Можно бы и не рисовать, но вдруг надо явно задать, что перекрытие не начинается сразу от стены?
//                break;
//            }
//            case 2: {
//                numClick++;
//                dB.addTempPoint(new Point(stickedX, stickedY));// Заносим во временную базу нашу третью точку
//                drawPoint(stickedX, stickedY, thick);
//                drawPlateAxisCross(stickedX, stickedY); // Рисуем перекрестие. Можно бы и не рисовать, но вдруг надо явно задать, что перекрытие не начинается сразу от стены?
//                break;
//            }
//            case 3: {
//                numClick = 0;
//                dB.addTwoSupportPlate(new TwoSupportPlate(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(),
//                        dB.getTempPoints().get(1).getX(), dB.getTempPoints().get(1).getY(),
//                        dB.getTempPoints().get(2).getX(), dB.getTempPoints().get(2).getY(),
//                        stickedX, stickedY, level, color, thick));
//                drawAll();
//                dB.deleteTempLine();// Удаляем временные линии
//                dB.deleteTempPoint();// Удаляем временные точки
//                break;
//            }
//        }
//    }
//
//    //Функуция обработки движения мыши при рисовании 2-х опорного перекрытия
//    private void mouseMovedDrawing2Plate(MouseEvent e) {
//
//        switch (numClick) {
//            case 1: {
//                drawAll();
//                stickCursor(e.getX(), e.getY());
//                drawPlateAxisCross(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY()); // Рисуем перекрестие в месте первого клика
//                drawPlateAxisCross(stickCrossAxes(stickLine(dB.getTempPoints().get(0).getX(), stickedX), "x"), stickCrossAxes(stickLine(dB.getTempPoints().get(0).getY(), stickedY), "y")); // Рисуем перекрестие 2
//                graphicsContext.setLineDashes(5, 3);
//                drawLine(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(), stickCrossAxes(stickedX, "x"), stickCrossAxes(stickedY, "y"));// Рисуем стену
//                break;
//            }
//            case 2: {
//                drawAll();
//                stickCursor(e.getX(), e.getY());
//                drawPlateAxisCross(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY()); // Рисуем перекрестие в месте первого клика
//                drawPlateAxisCross(dB.getTempPoints().get(1).getX(), dB.getTempPoints().get(1).getY()); // Рисуем перекрестие в месте второго клика
//                graphicsContext.setLineDashes(5, 3);
//                drawLine(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(), dB.getTempPoints().get(1).getX(), dB.getTempPoints().get(1).getY());// Рисуем линию первйю
//                break;
//            }
//            case 3: {
//                drawAll();
//                stickCursor(e.getX(), e.getY());
//                drawPlateAxisCross(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY()); // Рисуем перекрестие в месте первого клика
//                drawPlateAxisCross(dB.getTempPoints().get(1).getX(), dB.getTempPoints().get(1).getY()); // Рисуем перекрестие в месте второго клика
//                graphicsContext.setLineDashes(5, 3);
//                drawLine(dB.getTempPoints().get(0).getX(), dB.getTempPoints().get(0).getY(), dB.getTempPoints().get(1).getX(), dB.getTempPoints().get(1).getY());// Рисуем линию первйю
//                drawLine(dB.getTempPoints().get(2).getX(), dB.getTempPoints().get(2).getY(), stickCrossAxes(stickedX, "x"), stickCrossAxes(stickedY, "y"));// Рисуем стену
//                break;
//            }
//        }
//    }
    //Функуция обработки движения мыши при рисовании стены
    private void mouseMovedDrawingWall(MouseEvent e) {
        switch (numClick) {
            case 1: {
                drawAll();
                stickCursor(e.getX(), e.getY());
                drawAxisCross(firstX, firstY); // Рисуем перекрестие 1
                drawAxisCross(stickCrossAxes(stickLine(firstX, stickedX), "x"), stickCrossAxes(stickLine(firstY, stickedY), "y")); // Рисуем перекрестие 2
                graphicsContext.setLineDashes(0);
                drawLine(firstX, firstY, stickCrossAxes(stickedX, "x"), stickCrossAxes(stickedY, "y"));// Рисуем стену
                break;
            }
        }
    }

    // Отрисовка канвы когда нет действия
    private void mouseMoved(MouseEvent e) throws SQLException {
        drawAll();
        stickCursor(e.getX(), e.getY());
        drawAxises(); // Нарисуем оси, все что есть 
        drawStretchGrillages(); // Нарисуем ростверки все что есть 
        drawTwoSupportSquarePlates();
    }

    // Функция обработки кликов мыщи при рисовании стен
    private void mouseClickedDrawingWall(MouseEvent e) {
        switch (numClick) {
            case 0: {
                numClick++;
                // Рисуем точку. Пока мы не знаем, куда будет второй клик, поэтому проверять не имеет смысла
                //       drawPoint(stickedX, stickedY, thick);
                graphicsContext.fillOval(stickedX - thick / 2, stickedY - thick / 2, thick, thick);
                // Сохраняем эти координаты как временные, так как это лищь первая точка:
                firstX = stickedX;
                firstY = stickedY;
                drawAxisCross(firstX, firstY); // Рисуем перекрестие
                break;
            }
            case 1: {
                numClick = 0;
                dB.addSchemeWalls(new WallOnScheme(firstX, firstY, stickCrossAxes(stickLine(firstX, stickedX), "x"), stickCrossAxes(stickLine(firstY, stickedY), "y"), wallType, color, thick));
                drawAll();
                stickCursor(e.getX(), e.getY());
//                getWallsFromBase(); // Рисуем стены, которые уже есть в базе
                break;
            }
        }

    }

    // Функция очистки канвы и заполнение ее белым
    private static void clearModelCanvas() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, modelCanvasWidth, modelCanvasHeight);
    }

    // Функция рисования осей 
    private static void drawAxisCross(double x, double y) {
        graphicsContext.setLineDashes(4, 4, 10, 4);// - рисует ось симметрии
        graphicsContext.setStroke(GRAY);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(0.5, y + 0.5, modelCanvasWidth - 0.5, y + 0.5);
        graphicsContext.strokeLine(x + 0.5, 0.5, x + 0.5, modelCanvasHeight - 0.5);
    }

    // Функция рисования осей перекрытий 
    private static void drawPlateAxisCross(double x, double y) {
        graphicsContext.setLineDashes(6, 6);
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(0.5, y + 0.5, modelCanvasWidth - 0.5, y + 0.5);
        graphicsContext.strokeLine(x + 0.5, 0.5, x + 0.5, modelCanvasHeight - 0.5);
    }

// Функция рисования линий. Просто линий
    private static void drawLine(double firstX, double firstY, double secondX, double secondY) {
        graphicsContext.setLineWidth(thick);
        graphicsContext.setStroke(color);
        graphicsContext.strokeLine(firstX + 0.5, firstY + 0.5, stickLine(firstX, secondX) + 0.5, stickLine(firstY, secondY) + 0.5);
    }

    // Функция изменения координат для прилипания к осям вновь рисуемой линии
    private static double stickLine(double a, double b) {
        if (abs(a - b) < prikleyka) {
            b = a;
        }
        return b;
    }

    // Функция изменения координат для прилипания к пересечениям осей
    private static double stickCrossAxes(double a, String s) {
        if (s == "x") {
            for (int k = 0; k < dB.getSchemeWalls().size(); k++) {
                if (abs(dB.getSchemeWalls().get(k).getX0() - a) < prikleyka) {
                    a = dB.getSchemeWalls().get(k).getX0();
                } else if (abs(dB.getSchemeWalls().get(k).getX1() - a) < prikleyka) {
                    a = dB.getSchemeWalls().get(k).getX1();
                }
            }
        } else if (s == "y") {
            for (int k = 0; k < dB.getSchemeWalls().size(); k++) {
                if (abs(dB.getSchemeWalls().get(k).getY0() - a) < prikleyka) {
                    a = dB.getSchemeWalls().get(k).getY0();
                } else if (abs(dB.getSchemeWalls().get(k).getY1() - a) < prikleyka) {
                    a = dB.getSchemeWalls().get(k).getY1();
                }
            }
        }
        return a;
    }

    // Функция прилипания, когда уже есть нарисованное и мы ищем куда поставить следующий элемент
    private static void stickCursor(double x, double y) {
        //  boolean fx = false;
        //   boolean fy = false;
        stickedX = x;
        stickedY = y;
        if (getAxises("AxisesX").size() > 0) {
            for (Axis axis : getAxises("AxisesX")) {
                if (abs(axis.getPixels() - x) < prikleyka) {
                    stickedX = axis.getPixels();
                    drawBrightAxisX(stickedX);
                    //      fx = true;
                }
            }
        }
        if (getAxises("AxisesY").size() > 0) {
            for (Axis axis : getAxises("AxisesY")) {

                if (abs(axis.getPixels() - y) < prikleyka) {
                    stickedY = axis.getPixels();
                    drawBrightAxisY(stickedY);
                    //     fy = true;
                }
            }
        }
//        if ((fx == true) || (fy == true)) { // Если хоть что-то приклеилось
//            graphicsContext.setLineDashes(4, 4, 10, 4);// - рисует ось симметрии
//            graphicsContext.setStroke(ORANGE);
//            graphicsContext.setLineWidth(3);
//        }
//        if ((fx == true) && (fy == true)) { // если попали в зону с обеими осями, 
//            graphicsContext.strokeLine(0.5, stickedY + 0.5, modelCanvasWidth - 0.5, stickedY + 0.5);
//            graphicsContext.strokeLine(stickedX + 0.5, 0.5, stickedX + 0.5, modelCanvasHeight - 0.5);
//        } else if ((fx == true) && (fy == false)) {
//            graphicsContext.strokeLine(stickedX + 0.5, 0.5, stickedX + 0.5, modelCanvasHeight - 0.5);
//        } else if ((fx == false) && (fy == true)) {
//            graphicsContext.strokeLine(0.5, stickedY + 0.5, modelCanvasWidth - 0.5, stickedY + 0.5);
//        }
    }

// Функция отрисовки уже имеющихся в базе 2-х опорных перекрытий
//    private static void draw2PlatesFromBase() {
//        graphicsContext.setLineDashes(0);
//        for (int k = 0; k < dB.getTwoSupportPlates().size(); k++) {
//            SetGCifRestore("TwoSupportPlate", "TWO_SUPPORT", k);
//            graphicsContext.strokeLine(dB.getTwoSupportPlates().get(k).getX0(), dB.getTwoSupportPlates().get(k).getY0(), dB.getTwoSupportPlates().get(k).getX1(), dB.getTwoSupportPlates().get(k).getY1());
//            graphicsContext.strokeLine(dB.getTwoSupportPlates().get(k).getX2(), dB.getTwoSupportPlates().get(k).getY2(), dB.getTwoSupportPlates().get(k).getX3(), dB.getTwoSupportPlates().get(k).getY3());
//            // Рисование соединяющих линий
//            graphicsContext.setLineWidth(1);
//            graphicsContext.strokeLine(dB.getTwoSupportPlates().get(k).getX0() + 0.5, dB.getTwoSupportPlates().get(k).getY0() + 0.5, dB.getTwoSupportPlates().get(k).getX3() + 0.5, dB.getTwoSupportPlates().get(k).getY3() + 0.5);
//            graphicsContext.strokeLine(dB.getTwoSupportPlates().get(k).getX1() + 0.5, dB.getTwoSupportPlates().get(k).getY1() + 0.5, dB.getTwoSupportPlates().get(k).getX2() + 0.5, dB.getTwoSupportPlates().get(k).getY2() + 0.5);
//        }
//    }
// Функция отрисовки уже имеющихся в базе стен
    private static void getWallsFromBase() {
        // graphicsContext.setLineDashes(0);
        for (int k = 0; k < dB.getSchemeWalls().size(); k++) {
            wallType = dB.getSchemeWalls().get(k).getType();
            SetGCifRestore("Wall", wallType, k);
            graphicsContext.strokeLine(dB.getSchemeWalls().get(k).getX0(), dB.getSchemeWalls().get(k).getY0(), dB.getSchemeWalls().get(k).getX1(), dB.getSchemeWalls().get(k).getY1());// Рисуем стену
        }
    }

// Функция отрисовки осей на основе уже имеющихся в базе стен
    private static void getAxesFromWallsBase() {
        for (int k = 0; k < dB.getSchemeWalls().size(); k++) {
            graphicsContext.setLineDashes(4, 4, 10, 4);// - рисует ось симметрии
            graphicsContext.setStroke(GRAY);
            graphicsContext.setLineWidth(1);
            graphicsContext.strokeLine(0.5, dB.getSchemeWalls().get(k).getY0() + 0.5, modelCanvasWidth - 0.5, dB.getSchemeWalls().get(k).getY0() + 0.5);
            graphicsContext.strokeLine(dB.getSchemeWalls().get(k).getX0() + 0.5, 0.5, dB.getSchemeWalls().get(k).getX0() + 0.5, modelCanvasHeight - 0.5);
            graphicsContext.strokeLine(0.5, dB.getSchemeWalls().get(k).getY1() + 0.5, modelCanvasWidth - 0.5, dB.getSchemeWalls().get(k).getY1() + 0.5);
            graphicsContext.strokeLine(dB.getSchemeWalls().get(k).getX1() + 0.5, 0.5, dB.getSchemeWalls().get(k).getX1() + 0.5, modelCanvasHeight - 0.5);
        }
    }

    // Функция настройки цвета и толщин при выборе для рисования НОВОГО ЭЛЕМЕНТА: определение нужных и установка graphicsContext, colorPicker и thickComboBox
    public static void SetGCifNewElementChoosing(String what, String type) {
        switch (what) {
            case "Wall": {
                color = dB.getDefaultWallColor(type);
                thick = dB.getDefaultWallThick(type);
                graphicsContext.setLineDashes(0);
                break;
            }
            case "TwoSupportPlate": {
                //   color = dB.getDefaultPlateColor(type);
                //   thick = dB.getDefaultPlateThick(type);
                graphicsContext.setLineDashes(5, 2);
                break;
            }
            case "MultiSupportPlate": {
                //   color = dB.getDefaultPlateColor(type);
                //   thick = dB.getDefaultPlateThick(type);
                graphicsContext.setLineDashes(7, 3);
                break;
            }
        }
        colorPicker.setValue(color);
        thickComboBox.setValue(thick);
        graphicsContext.setLineWidth(thick);
        graphicsContext.setStroke(color);
        graphicsContext.setFill(color);
    }

    // Функция настройки цвета и толщин при отрисовке того, что уже есть в базе: определение нужных и установка graphicsContext, colorPicker и thickComboBox
    public static void SetGCifRestore(String what, String type, int num) {
        Color restoreColor = Color.YELLOW;
        double restoreThick = 30;
        switch (what) {
            case "Wall": {
                restoreColor = dB.getSchemeWalls().get(num).getColor();
                restoreThick = dB.getSchemeWalls().get(num).getThick();
                graphicsContext.setLineDashes(0);
                break;
            }
            case "TwoSupportPlate": {
                //            restoreColor = dB.getTwoSupportPlates().get(num).getColor();
                //            restoreThick = dB.getTwoSupportPlates().get(num).getThick();
                graphicsContext.setLineDashes(5, 2);
                break;
            }
            case "MultiSupportPlate": {
                //            restoreColor = dB.getMSP().get(num).getColor();
                //            restoreThick = dB.getMSP().get(num).getThick();
                graphicsContext.setLineDashes(7, 3);
                break;
            }
        }
        graphicsContext.setLineWidth(restoreThick);
        graphicsContext.setStroke(restoreColor);
        graphicsContext.setFill(restoreColor);
    }

    // Функция рисования всего. При рисовании вытаскивает из баз и рисует все что надо
    private static void drawAll() {
        clearModelCanvas(); // Очищаем канву
        getAxesFromWallsBase(); // Рисуем оси на основе стен, уже имеющихся в базе
        getWallsFromBase(); // Рисуем стены, которые уже есть в базе
        //      draw2PlatesFromBase();// Рисуем 2-х опорные перекрытия, которые уже есть в базе
        //      drawAllMSPs(); //Рисуем многоопорные перекрытия, которые уже есть в базе, в том числе текущее незаконченное
    }

}
