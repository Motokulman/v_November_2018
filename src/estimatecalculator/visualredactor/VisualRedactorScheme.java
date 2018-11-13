/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.visualredactor;

import estimatecalculator.classes.ModelCanvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 *
 * @author I
 */
public class VisualRedactorScheme {

    public static Pane getModelScheme() {
        Pane pane = new Pane();
        Canvas modelFieldCanvas = new ModelCanvas();
        pane.getChildren().add(modelFieldCanvas);
        return (pane);
    }
    
}
