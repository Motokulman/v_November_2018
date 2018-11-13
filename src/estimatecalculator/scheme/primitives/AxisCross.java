/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.scheme.primitives;

import static estimatecalculator.Settings.axisUniversalPlateLineColor;
import static estimatecalculator.Settings.axisUniversalPlateLineDashes;
import static estimatecalculator.Settings.axisUniversalPlateLineWidth;
import static estimatecalculator.classes.ModelCanvas.graphicsContext;
import static estimatecalculator.classes.ModelCanvas.modelCanvasHeight;
import static estimatecalculator.classes.ModelCanvas.modelCanvasWidth;


public class AxisCross {
    
    

    public AxisCross() {
    }

    public void drawAxisCross(Point point) {
        graphicsContext.setStroke(axisUniversalPlateLineColor);
        graphicsContext.setLineDashes(axisUniversalPlateLineDashes);
        graphicsContext.setLineWidth(axisUniversalPlateLineWidth);
        graphicsContext.strokeLine(point.getX() + 0.5, 0.5, point.getX() + 0.5, modelCanvasHeight - 0.5);
        graphicsContext.strokeLine(0.5, point.getY() + 0.5, modelCanvasWidth - 0.5, point.getY() + 0.5);
    }
}
