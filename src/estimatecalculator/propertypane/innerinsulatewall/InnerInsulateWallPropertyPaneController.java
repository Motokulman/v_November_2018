/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.propertypane.innerinsulatewall;

import estimatecalculator.classes.Aperture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static estimatecalculator.EstimateCalculator.innerInsulateWallAperturesObservableList;
/**
 * FXML Controller class
 *
 * @author I
 */
public class InnerInsulateWallPropertyPaneController implements Initializable {
    
    @FXML private TableView<Aperture> innerInsulateWallTableView;
    @FXML private TextField apertureNameField;
    @FXML private TextField apertureAreaField;
    @FXML private TextField apertureNeedLintelledWidthField;
    
    @FXML
    protected void addAperture(ActionEvent event) {
        ObservableList<Aperture> innerInsulateWallAperturesLocal = innerInsulateWallTableView.getItems();
        innerInsulateWallAperturesLocal.add(new Aperture(apertureNameField.getText(), new Double(apertureAreaField.getText()), new Double(apertureNeedLintelledWidthField.getText())));
        
        apertureNameField.setText("");
        apertureAreaField.setText("");
        apertureNeedLintelledWidthField.setText("");   
        setData();
    }    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        innerInsulateWallTableView.setItems(innerInsulateWallAperturesObservableList);
    }    
    
    
        // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
//        innerInsulateWallAperturesObservableList.add(new Aperture("Alex", 3, 4));
    }
    // Попытаюсь сохранить введенные данные
    private void setData() {
        innerInsulateWallAperturesObservableList = innerInsulateWallTableView.getItems();
    }
    
}
