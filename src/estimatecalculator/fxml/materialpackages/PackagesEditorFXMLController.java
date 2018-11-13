/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.fxml.materialpackages;

import static estimatecalculator.fxml.levels.LevelEditorFXMLController.levelsNamesObservableList;
import static estimatecalculator.fxml.levels.LevelEditorFXMLController.levelsObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import estimatecalculator.classes.elements.Level;
import estimatecalculator.classes.material.MaterialPackage;
import static estimatecalculator.classes.material.MaterialPackage.addMaterialPackage;
import static estimatecalculator.classes.material.MaterialPackage.getMaterialPackages;
import java.sql.SQLException;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author I
 */
public class PackagesEditorFXMLController implements Initializable {

    @FXML
    private TableView<MaterialPackage> materialPackagesTableView;
    @FXML
    private TextField materialPackageNameField;
    @FXML
    private TextField materialPackageQuantityField;
    @FXML
    private TextField materialPackageLongSideField;
    @FXML
    private TextField materialPackageMediumSideField;
    @FXML
    private TextField materialPackageSmallSideField;

    public static ObservableList<MaterialPackage> materialPackageObservableList;

    @FXML
    protected void addMaterialPackageFromEditor(ActionEvent event) throws SQLException {
        // Добавляем упаковку
        addMaterialPackage(materialPackageNameField.getText(), Double.valueOf(materialPackageQuantityField.getText()), Double.valueOf(materialPackageLongSideField.getText()), Double.valueOf(materialPackageMediumSideField.getText()), Double.valueOf(materialPackageSmallSideField.getText()));
        materialPackageNameField.setText("");
        materialPackageQuantityField.setText("");
        materialPackageLongSideField.setText("");
        materialPackageMediumSideField.setText("");
        materialPackageSmallSideField.setText("");
        System.out.println("Сохраняем упаковку");
        initData();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }

    // Загружаем в таблицу все что есть в базе    
    private void initData() {
        // Загружаем в ObservableList данные из базы
        materialPackageObservableList = FXCollections.observableArrayList(getMaterialPackages());
        // Загружаем в TableView данные из ObservableList
        if (materialPackageObservableList.size() > 0) {
            System.out.println("materialPackageObservableList.size() = " + materialPackageObservableList.size());
            materialPackagesTableView.setItems(materialPackageObservableList);
        }
    }

}
