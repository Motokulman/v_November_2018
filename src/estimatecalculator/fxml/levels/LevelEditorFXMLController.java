/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.fxml.levels;

import static estimatecalculator.Settings.bottom_name;
import static estimatecalculator.Settings.top_name;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import estimatecalculator.classes.elements.Level;
import static estimatecalculator.classes.elements.Level.addLevel;
import static estimatecalculator.classes.elements.Level.getLevelByName;
import static estimatecalculator.classes.elements.Level.getLevels;
import static estimatecalculator.classes.elements.Level.is_bottom_top;
import static estimatecalculator.classes.elements.Level.is_this_top;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;

/**
 * FXML Controller class
 *
 * @author I
 */
public class LevelEditorFXMLController implements Initializable {

    @FXML
    private TableView<Level> levelsTableView;
    @FXML
    private TextField levelNameField;
    @FXML
    private TextField levelAltitudeField;
    @FXML
    private ComboBox isThisTopComboBox;
    @FXML
    private ComboBox isBottomTopComboBox;
    @FXML
    private ComboBox levelFromComboBox;
    public static ObservableList<Level> levelsObservableList;
    public static ObservableList<String> levelsNamesObservableList;
    private SingleSelectionModel ssmIsThisTopComboBox;
    private SingleSelectionModel ssmIBottomTopComboBox;
    private SingleSelectionModel ssmlevelFromComboBox;

    @FXML
    protected void addLevelFromEditor(ActionEvent event) throws SQLException {

        // Добавляем уровень
        addLevel(levelNameField.getText(), is_this_top, is_bottom_top, Double.valueOf(levelAltitudeField.getText()), getLevelByName(levelFromComboBox.getSelectionModel().getSelectedItem().toString()).getId());
        levelNameField.setText("");
        levelAltitudeField.setText("");
        System.out.println("Сохраняем");
        System.out.println("is_this_top = " + is_this_top);
        System.out.println("is_bottom_top = " + is_bottom_top);
        initData();
        setComboBoxes();
        setLevelFromComboBox();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        // Наполняем комбобоксы "верх", "Низ"
        ObservableList<String> productsWallTypes = FXCollections.observableArrayList();
        productsWallTypes.add(top_name);
        productsWallTypes.add(bottom_name);
        isThisTopComboBox.setItems(productsWallTypes);
        isBottomTopComboBox.setItems(productsWallTypes);
        setComboBoxes();
        setLevelFromComboBox();
    }

//        // подготавливаем данные для таблицы
//    // вы можете получать их с базы данных
    private void initData() {
        // Загружаем в ObservableList данные из базы
        levelsObservableList = FXCollections.observableArrayList(getLevels());
        levelsNamesObservableList = FXCollections.observableArrayList();
        // Загружаем в TableView данные из ObservableList
        levelsTableView.setItems(levelsObservableList);
    }

    @FXML
    protected void setIsThisTop(ActionEvent event) {
        if (top_name.equals(isThisTopComboBox.getSelectionModel().getSelectedItem().toString())) {
            is_this_top = 1;
        } else {
            is_this_top = 0;
        }
    }

    @FXML
    protected void setIsBottomTop(ActionEvent event) {
        if (top_name.equals(isBottomTopComboBox.getSelectionModel().getSelectedItem().toString())) {
            is_bottom_top = 1;
        } else {
            is_bottom_top = 0;
        }
    }

    // Установка комбобоксов "Верх", "Низ"  в изначальное состояние
    private void setComboBoxes() {
        //System.out.println("Сбрасываем");
        ssmIsThisTopComboBox = isThisTopComboBox.getSelectionModel();
        ssmIsThisTopComboBox.select(0);
        isThisTopComboBox.setSelectionModel(ssmIsThisTopComboBox);
        is_this_top = 1;

        ssmIBottomTopComboBox = isBottomTopComboBox.getSelectionModel();
        ssmIBottomTopComboBox.select(0);
        isBottomTopComboBox.setSelectionModel(ssmIBottomTopComboBox);
        is_bottom_top = 1;
    }

    // Наполняем комбобокс "От уновня"
    private void setLevelFromComboBox() {
        for (int i = 0; i < levelsObservableList.size(); i++) {
        levelsNamesObservableList.add(levelsObservableList.get(i).getName());
        }
        levelFromComboBox.setItems(levelsNamesObservableList);
        ssmlevelFromComboBox = levelFromComboBox.getSelectionModel();
        ssmlevelFromComboBox.select(0);
        levelFromComboBox.setSelectionModel(ssmlevelFromComboBox);
    }
}
