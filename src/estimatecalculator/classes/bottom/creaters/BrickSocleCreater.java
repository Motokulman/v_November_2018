/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.bottom.creaters;

import static estimatecalculator.Settings.grillageColor;
import static estimatecalculator.Settings.grillageSchemeThick;
import static estimatecalculator.Settings.prikleyka;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.stickedX;
import static estimatecalculator.classes.ModelCanvas.stickedY;
import estimatecalculator.classes.foundament.StretchGrillage;
import static estimatecalculator.classes.foundament.StretchGrillage.drawSingleStretchGrillage;
import static estimatecalculator.classes.foundament.StretchGrillage.getStretchGrillageByAxisesID;
import static estimatecalculator.classes.foundament.StretchGrillage.getStretchGrillages;
import static estimatecalculator.classes.socle.BrickSocle.addBrickSocle;
import estimatecalculator.scheme.primitives.Axis;
import static estimatecalculator.scheme.primitives.Axis.getAxisByID;
import static estimatecalculator.scheme.primitives.Axis.getAxises;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author I
 */
public class BrickSocleCreater {

    private static int x0_id = -1;
    private static int y0_id = -1;
    private static int x1_id = -1;
    private static int y1_id = -1;
    private static List<Axis> list_x = getAxises("AxisesX");
    private static List<Axis> list_y = getAxises("AxisesY");



    public static void mouseClikedDrawindBrickSocle() throws SQLException {
        // находим ростверки под курсором
        for (StretchGrillage stretchGrillage : getStretchGrillages()) {
            double x0 = getAxisByID("AxisesX", stretchGrillage.getId_x0()).getPixels();
            double y0 = getAxisByID("AxisesY", stretchGrillage.getId_y0()).getPixels();
            double x1 = getAxisByID("AxisesX", stretchGrillage.getId_x1()).getPixels();
            double y1 = getAxisByID("AxisesY", stretchGrillage.getId_y1()).getPixels();
            double a = Math.hypot(Math.abs(y1 - y0), Math.abs(x1 - x0));
            double b = Math.hypot(Math.abs(y1 - stickedY), Math.abs(x1 - stickedX));
            double c = Math.hypot(Math.abs(y0 - stickedY), Math.abs(x0 - stickedX));
            if ((b + c - a) < Math.sqrt(prikleyka)) {
                addBrickSocle(stretchGrillage.getId(), 0);
            }
        }
    }
    
    // Рисование протяженных ростверков из базы
    public static void drawStretchGrillages() throws SQLException {
        double x0;
        double y0;
        double x1;
        double y1;
        graphicsContext.setStroke(grillageColor);
        graphicsContext.setLineDashes(0);
        graphicsContext.setLineWidth(grillageSchemeThick);

        for (StretchGrillage stretchGrillage : getStretchGrillages()) {
            x0 = getAxisByID("AxisesX", stretchGrillage.getId_x0()).getPixels();
            y0 = getAxisByID("AxisesY", stretchGrillage.getId_y0()).getPixels();
            x1 = getAxisByID("AxisesX", stretchGrillage.getId_x1()).getPixels();
            y1 = getAxisByID("AxisesY", stretchGrillage.getId_y1()).getPixels();
            graphicsContext.strokeLine(x0 + 0.5, y0 + 0.5, x1 + 0.5, y1 + 0.5);
        }
    }
}
//        for (int i = 0; i < list_x.size(); i++) {
//            if (stickedX > list_x.get(i).getPixels() + prikleyka) {
//                if (list_x.size() > i + 1) {
//                    if (stickedX < list_x.get(i + 1).getPixels() - prikleyka) {
//                        x0_id = list_x.get(i).getId();
//                        x1_id = list_x.get(i + 1).getId();
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < list_y.size(); i++) {
//            if (stickedY > list_y.get(i).getPixels() + prikleyka) {
//                if (list_y.size() > i + 1) {
//                    if (stickedY < list_y.get(i + 1).getPixels() - prikleyka) {
//                        y0_id = list_y.get(i).getId();
//                        y1_id = list_y.get(i + 1).getId();
//                    }
//                }
//            }
//        }
//        if ((x0_id != -1) && (y0_id != -1) && (x1_id != -1) && (y1_id != -1)) {
//            double a = (getAxisByID("AxisesX", x1_id).getPixels() - getAxisByID("AxisesX", x0_id).getPixels()) / (getAxisByID("AxisesY", y1_id).getPixels() - getAxisByID("AxisesY", y0_id).getPixels());
//            double b = (getAxisByID("AxisesY", y1_id).getPixels() - getAxisByID("AxisesY", y0_id).getPixels()) / (getAxisByID("AxisesX", x1_id).getPixels() - getAxisByID("AxisesX", x0_id).getPixels());
//            a = a * (stickedY - getAxisByID("AxisesY", y0_id).getPixels()) + getAxisByID("AxisesX", x0_id).getPixels();
//            b = b * (stickedX - getAxisByID("AxisesX", x0_id).getPixels()) + getAxisByID("AxisesY", y0_id).getPixels();
//            if ((stickedX > a - prikleyka) && (stickedX < a + prikleyka)
//                    && (stickedY > b - prikleyka) && (stickedY < b + prikleyka)) {
//                System.out.println("на прямой ");
//                System.out.println("на x0_id " + x0_id);
//                System.out.println("на y0_id " + y0_id);
//                System.out.println("на x1_id " + x1_id);
//                System.out.println("на y1_id " + y1_id);
//                if (getStretchGrillageByAxisesID(x0_id, y0_id, x1_id, y1_id, null, null) != null) {
//                    drawSingleStretchGrillage(getStretchGrillageByAxisesID(x0_id, y0_id, x1_id, y1_id, null, null));
//                    System.out.println("Hbcetv ");
//                }
//            }
//        }

