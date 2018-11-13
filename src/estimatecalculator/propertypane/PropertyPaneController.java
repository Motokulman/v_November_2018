/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.propertypane;

import estimatecalculator.classes.Aperture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author I
 */
public class PropertyPaneController implements Initializable {
    @FXML private TableView<Aperture> tableView;
    @FXML private TextField apertureNameField;
    @FXML private TextField apertureAreaField;
    @FXML private TextField apertureNeedLintelledWidthField;

    @FXML
    protected void addAperture(ActionEvent event) {
        ObservableList<Aperture> data = tableView.getItems();
        data.add(new Aperture(apertureNameField.getText(), new Double(apertureAreaField.getText()), new Double(apertureNeedLintelledWidthField.getText())));
        
        apertureNameField.setText("");
        apertureAreaField.setText("");
        apertureNeedLintelledWidthField.setText("");   
    }    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
