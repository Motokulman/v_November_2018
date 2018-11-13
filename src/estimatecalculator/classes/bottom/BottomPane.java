/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.bottom;

import static estimatecalculator.Settings.grillageDefaultAverage;
import static estimatecalculator.Settings.grillageWideSet;
import static estimatecalculator.Settings.socleDefaultAverage;
import estimatecalculator.classes.ModelCanvas;
import static estimatecalculator.classes.ModelCanvas.SetGCifNewElementChoosing;
import static estimatecalculator.classes.ModelCanvas.isSecant;
import static estimatecalculator.classes.ModelCanvas.isSupport;
import static estimatecalculator.classes.ModelCanvas.level_id;
import static estimatecalculator.classes.ModelCanvas.wallType;
import static estimatecalculator.classes.ModelCanvas.whatDrawNow;
import static estimatecalculator.classes.ModelCanvas.comment;
import static estimatecalculator.classes.ModelCanvas.name;
import static estimatecalculator.classes.ModelCanvas.color;
import static estimatecalculator.classes.ModelCanvas.defaultPlateType;
import static estimatecalculator.classes.ModelCanvas.engineeringPlateTypeEnum;
import static estimatecalculator.classes.ModelCanvas.projectWallsByFunction_id;
import static estimatecalculator.classes.ModelCanvas.thick;
import static estimatecalculator.classes.ModelCanvas.wide;
import static estimatecalculator.classes.Plates.EngineeringPlateTypeEnum.getAllowedPlateTypesNames;
import static estimatecalculator.classes.Plates.EngineeringPlateTypeEnum.getEngineeringPlateTypeEnum;
import static estimatecalculator.classes.Plates.EngineeringPlateTypeEnum.getEngineeringPlateTypeEnumNamesAsList;
import static estimatecalculator.classes.elements.Level.getLevelByName;
import static estimatecalculator.classes.elements.Level.getLevels;
import static estimatecalculator.classes.elements.PlateTypeEnum.getPlateTypeEnumByName;
import static estimatecalculator.classes.walls.ProjectWallByFunction.getProjectWallsByFunction;
import static estimatecalculator.classes.walls.ProjectWallByFunction.getProjectWallsByFunctionByName;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author I
 */
public class BottomPane {

    Pane pane = new Pane();

    public static TextArea commentTextArea = new TextArea();
    public static Button pointButton = new Button("Точка");
    public static Button wallButton = new Button("Стена");
    public static Button PlateButton = new Button("Перекрытие");
    public static Button GrillageButton = new Button("Ростверк");
    public static Button socleButton = new Button("Цоколь");
    public static ComboBox projectWallByFunctionComboBox = new ComboBox();
    public static ComboBox defaultPlateTypeComboBox = new ComboBox();
    public static ComboBox levelComboBox = new ComboBox();
    public static ComboBox thickComboBox = new ComboBox();
    public static ComboBox grillageComboBox = new ComboBox();
    public static TextField nameTextField = new TextField();
    public static TextField grillageAverageTextField = new TextField(Double.toString(grillageDefaultAverage));
    public static TextField socleAverageTextField = new TextField(Double.toString(socleDefaultAverage));
    public static ComboBox SupportComboBox = new ComboBox();
    public static ComboBox SecantComboBox = new ComboBox();
    public static ComboBox engineeringPlateTypeComboBox = new ComboBox();
    public static ColorPicker colorPicker = new ColorPicker(ModelCanvas.color);
    public static boolean pointSelected = false;
    public static boolean lineSelected = true;

    public BottomPane() throws SQLException {

        PointButtonAction();
        PlateTypeComboBoxAction(); // Запуск слушателя комбобокса выбора типа перекрытия
        //  SetAllDisabled(); // при пуске установка панели параметров в исходное состояние

        wallButton.setLayoutX(50.0);
        wallButton.setLayoutY(0.0);
        WallButtonAction();

        projectWallByFunctionComboBox.setLayoutX(100.0);
        projectWallByFunctionComboBox.setLayoutY(0.0);
        SetProjectWallByFunctionComboBox();
        ProjectWallByFunctionComboBoxAction();

        // Кнопка выбора Рисуем перекрытие
        PlateButton.setLayoutY(30.0);
        PlateButtonAction();

        // Комбобокс инженерных типов перекрытий
        engineeringPlateTypeComboBox.setLayoutY(30.0);
        engineeringPlateTypeComboBox.setLayoutX(100.0);
        //   plateTypeComboBox.setEditable(false);
        SetEngineeringPlateTypeComboBox();
        EngineeringPlateTypeComboBoxAction();

        // Комбобокс перекрытий, устанавливаемых по умолчанию
        defaultPlateTypeComboBox.setLayoutY(60.0);
        //   plateTypeComboBox.setEditable(false);
        SetPlateTypeComboBox();
        DefaultPlateTypeComboBoxAction();

        // Комбобокс уровней
        levelComboBox.setLayoutY(90.0);
        levelComboBox.setEditable(true);
        SetLevelComboBox();
        LevelComboBoxAction();

        // Комбобокс толщин линий
        thickComboBox.setLayoutY(150.0);
        thickComboBox.setEditable(true);
        SetThickComboBox();
        ThickComboBoxAction();
        // thickComboBox.setValue("10");

        // Комбобокс цветов
        colorPicker.setLayoutY(180.0);
        ColorPickerAction();

        // Комбобокс является ли данная стена опорной
        SupportComboBox.setLayoutY(210.0);
        SetSupportComboBox();
        SupportComboBoxAction();

        // Комбобокс является ли данная стена секущей по отношению к нижележащим строронам
        SecantComboBox.setLayoutY(240.0);
        SetSecantComboBox();
        SecantComboBoxAction();

        // Текстовое поле для имени
        nameTextField.setLayoutY(270.0);
        NameTextFieldAction();

        // Кнопка выбора ростверка
        GrillageButton.setLayoutY(300.0);
        GrillageButtonAction();

        // Текстовое поле для высоты ростверка (нулевого цикла)
        grillageAverageTextField.setLayoutY(300.0);
        grillageAverageTextField.setLayoutX(100.0);
        NameTextFieldAction();

        // Комбобокс ширин ростверка
        grillageComboBox.setLayoutY(300.0);
        grillageComboBox.setLayoutX(300.0);
        SetGrillageComboBox();
        GrillageComboBoxAction();

        // Кнопка выбора цоколя
        socleButton.setLayoutY(330.0);
        SocleButtonAction();

        // Текстовое поле для высоты цоколя
        socleAverageTextField.setLayoutY(330.0);
        socleAverageTextField.setLayoutX(100.0);
        NameTextFieldAction();

        // TextArea для комментария
        commentTextArea.setLayoutY(360.0);
        CommentTextAreaAction();

        pane.getChildren().addAll(pointButton, wallButton, projectWallByFunctionComboBox, PlateButton, levelComboBox, defaultPlateTypeComboBox,
                SupportComboBox, SecantComboBox, thickComboBox, colorPicker, nameTextField, commentTextArea, GrillageButton, 
                socleButton, grillageComboBox, grillageAverageTextField, socleAverageTextField, engineeringPlateTypeComboBox);
    }

    // Обработка commentTextArea
    private void NameTextFieldAction() {
        nameTextField.textProperty().addListener((sdf) -> {
            name = nameTextField.getText();
            System.out.println("name = " + name);
        });
    }

    // Обработка nameTextArea
    private void CommentTextAreaAction() {
        commentTextArea.textProperty().addListener((sdf) -> {
            comment = commentTextArea.getText();
            System.out.println("comment = " + comment);
        });

    }

    // Обрабока кнопки Точка
    private void PointButtonAction() {
        pointButton.setOnAction((ActionEvent t) -> {
            whatDrawNow = "Point";
        });
    }

    // Обрабока кнопки Ростверк
    private void GrillageButtonAction() {
        pointButton.setOnAction((ActionEvent t) -> {
            whatDrawNow = "GrillageLine";
            // whatDrawNow = AdditionWhatDrawNowEnum.GRILLAGE.toString();
            wide = 0;
        });
    }

    // Обрабока кнопки Цоколь
    private void SocleButtonAction() {
        socleButton.setOnAction((ActionEvent t) -> {
            whatDrawNow = "Socle";
        });
    }

    // Обрабока кнопки выбора Стена
    private void WallButtonAction() {
        wallButton.setOnAction((ActionEvent t) -> {

            whatDrawNow = "Wall";
            wallType = projectWallByFunctionComboBox.getValue().toString();
            SetGCifNewElementChoosing(whatDrawNow, wallType);
        });
    }

    // Обработка кнопки выбора перекрытия
    private void PlateButtonAction() {
        PlateButton.setOnAction((ActionEvent t) -> {
            //   SetAllDisabled(); // установим все компоненты выбора параметров неактивными
            whatDrawNow = "TwoSupportSquarePlate";
//            plateTypeComboBox.setDisable(false);
//            universalPlateGroupsComboBox.setDisable(false);
//            whatDrawNow = dB.getPlateTypesForScheme().get(plateTypeComboBox.getSelectionModel().getSelectedIndex()).getType().toString();
//            color = dB.getPlateTypesForScheme().get(plateTypeComboBox.getSelectionModel().getSelectedIndex()).getColor();
//            thick = dB.getPlateTypesForScheme().get(plateTypeComboBox.getSelectionModel().getSelectedIndex()).getThick();

        });
    }

    // Функуция установки панели параметров в исходное состояние
    private void SetAllDisabled() {
        projectWallByFunctionComboBox.setDisable(true);
        defaultPlateTypeComboBox.setDisable(true);
        levelComboBox.setDisable(true);
        thickComboBox.setDisable(true);
        commentTextArea.clear();
        nameTextField.clear();
    }

    // Наполним комбобокс типов стен проекта значениями
    private void SetProjectWallByFunctionComboBox() {
        ObservableList<String> productsWallTypes = FXCollections.observableArrayList();
        for (int k = 0; k < getProjectWallsByFunction().size(); k++) {
            productsWallTypes.add(getProjectWallsByFunction().get(k).getName());
            projectWallByFunctionComboBox.setItems(productsWallTypes);
        }
        projectWallByFunctionComboBox.setValue(getProjectWallsByFunction().get(0).getName());
    }
    
    
// Наполним комбобокс типов стен значениями
//    private void SetEngineeringPlateTypeComboBox() {
//        ObservableList<String> productsPlateTypes = FXCollections.observableArrayList(getEngineeringPlateTypeEnumNamesAsList());
//        engineeringPlateTypeComboBox.setItems(productsPlateTypes);
//        SingleSelectionModel ssm = engineeringPlateTypeComboBox.getSelectionModel();
//        ssm.select(0);
//        engineeringPlateTypeEnum = getEngineeringPlateTypeEnum(productsPlateTypes.get(0));
//        engineeringPlateTypeComboBox.setSelectionModel(ssm);
//    }
    

    // Наполним комбобокс ширин ростверков значениями
    private void SetGrillageComboBox() {
        ObservableList<Double> productsWallTypes = FXCollections.observableArrayList();
        for (double wide : grillageWideSet) {
            productsWallTypes.add(wide);
            grillageComboBox.setItems(productsWallTypes);
        }
        grillageComboBox.setValue(0);
        wide = 0;
    }

    // Обработка событий комбобокс ширин ростверков 
    private void GrillageComboBoxAction() {
        grillageComboBox.setOnAction((Event t) -> {
            wide = Double.valueOf(grillageComboBox.getSelectionModel().getSelectedItem().toString());
        });
    }

    // Обработка событий комбобокс выбора инженерного типа перекрытия
    private void EngineeringPlateTypeComboBoxAction() {
        engineeringPlateTypeComboBox.setOnAction((Event t) -> {
            String s = engineeringPlateTypeComboBox.getSelectionModel().getSelectedItem().toString();
            engineeringPlateTypeEnum = getEngineeringPlateTypeEnum(s);
            SetPlateTypeComboBox();
        });
    }

    // Обработка событий комбобокс выбора типа перекрытия, устанавливаемого по умолчанию
    private void DefaultPlateTypeComboBoxAction() {
        defaultPlateTypeComboBox.setOnAction((Event t) -> {
            defaultPlateType = getPlateTypeEnumByName(defaultPlateTypeComboBox.getSelectionModel().getSelectedItem().toString());
        });
    }

    // Наполним комбобокс уровней
    private void SetLevelComboBox() throws SQLException {
        ObservableList<String> productsWallTypes = FXCollections.observableArrayList();
        for (int k = 0; k < getLevels().size(); k++) {
            productsWallTypes.add(getLevels().get(k).getName());
            levelComboBox.setItems(productsWallTypes);
        }
        SingleSelectionModel ssm = levelComboBox.getSelectionModel();
        ssm.select(1);
        levelComboBox.setSelectionModel(ssm);
        level_id = getLevelByName(levelComboBox.getSelectionModel().getSelectedItem().toString()).getId();
    }

    // Обработка событий копбибокса уровней
    private void LevelComboBoxAction() throws SQLException {
        levelComboBox.setOnAction((Event t) -> {
            try {
                level_id = getLevelByName(levelComboBox.getSelectionModel().getSelectedItem().toString()).getId();
            } catch (SQLException ex) {
                Logger.getLogger(BottomPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Наполним комбобокс SupportComboBox
    private void SetSupportComboBox() {
        ObservableList<String> productsWallTypes = FXCollections.observableArrayList();
        productsWallTypes.add("Опорная");
        productsWallTypes.add("Не опорная");
        SupportComboBox.setItems(productsWallTypes);
        if (isSupport == true) {
            SupportComboBox.setValue("Опорная");
        } else {
            SupportComboBox.setValue("Не опорная");
        }
    }

    // Наполним комбобокс SecantComboBox
    private void SetSecantComboBox() {
        ObservableList<String> productsWallTypes = FXCollections.observableArrayList();
        productsWallTypes.add("Секущая");
        productsWallTypes.add("Не секущая");
        SecantComboBox.setItems(productsWallTypes);
        if (isSecant == true) {
            SupportComboBox.setValue("Секущая");
        } else {
            SupportComboBox.setValue("Не секущая");
        }
    }

// Наполним комбобокс инженерных типов перекрытий значениями
    private void SetEngineeringPlateTypeComboBox() {
        ObservableList<String> productsPlateTypes = FXCollections.observableArrayList(getEngineeringPlateTypeEnumNamesAsList());
        engineeringPlateTypeComboBox.setItems(productsPlateTypes);
        SingleSelectionModel ssm = engineeringPlateTypeComboBox.getSelectionModel();
        ssm.select(0);
        engineeringPlateTypeEnum = getEngineeringPlateTypeEnum(productsPlateTypes.get(0));
        engineeringPlateTypeComboBox.setSelectionModel(ssm);
    }

// Наполним комбобокс типов перекрытий по умолчанию значениями, соответствующими выбранному инженерному типу перекрытия
    private void SetPlateTypeComboBox() {
        ObservableList<String> productsPlateTypes = FXCollections.observableArrayList();
        productsPlateTypes.addAll(getAllowedPlateTypesNames(engineeringPlateTypeEnum));
        defaultPlateTypeComboBox.setItems(productsPlateTypes);
        SingleSelectionModel ssm = defaultPlateTypeComboBox.getSelectionModel();
        ssm.select(0);
        defaultPlateTypeComboBox.setSelectionModel(ssm);
        defaultPlateType = getPlateTypeEnumByName(defaultPlateTypeComboBox.getSelectionModel().getSelectedItem().toString());
    }

    // Обработка событий комбобокса выбора типа перекрытия
    private void PlateTypeComboBoxAction() {
//        plateTypeComboBox.setOnAction((Event t) -> {
//            universalPlateGroupsComboBox.setDisable(true);
//            whatDrawNow = dB.getPlateTypesForScheme().get(plateTypeComboBox.getSelectionModel().getSelectedIndex()).getType().toString();
//            if (whatDrawNow == PlateTypeEnum.UNIVERSAL.toString()) {
//                universalPlateGroupsComboBox.setDisable(false);
//            }
//        });
    }

    // Обработка событий комбобокса выбора типа стен
    private void ProjectWallByFunctionComboBoxAction() {
        projectWallByFunctionComboBox.setOnAction((Event t) -> {
            try {
                projectWallsByFunction_id = getProjectWallsByFunctionByName(projectWallByFunctionComboBox.getValue().toString()).getId();
            } catch (SQLException ex) {
                Logger.getLogger(BottomPane.class.getName()).log(Level.SEVERE, null, ex);
            }
       //     whatDrawNow = "Wall";
    //        wallType = projectWallByFunctionComboBox.getValue().toString();
       //     SetGCifNewElementChoosing(whatDrawNow, wallType);
        });
    }

    // Наполним комбобокс толщин линий значениями
    private void SetThickComboBox() {
        ObservableList<String> productsThick = FXCollections.observableArrayList();
        for (int k = 1; k < 20; k++) {
            productsThick.add(Integer.toString(k));
            thickComboBox.setItems(productsThick);
        }
    }

    // Обработка событий комбибокса толщин линий
    private void ThickComboBoxAction() {
        thickComboBox.setOnAction((Event t) -> {
            thick = Double.parseDouble(thickComboBox.getEditor().getText());
        });
    }

// Обработка событий комбибокса SupportComboBox
    private void SupportComboBoxAction() {
        SupportComboBox.setOnAction((Event t) -> {
            if (SupportComboBox.getValue().toString() == "Опорная") {
                isSupport = true;
            } else {
                isSupport = false;
            }
        });
    }

// Обработка событий комбибокса SecantComboBox
    private void SecantComboBoxAction() {
        SecantComboBox.setOnAction((Event t) -> {
            if (SecantComboBox.getValue().toString() == "Секущая") {
                isSecant = true;
            } else {
                isSecant = false;
            }
        });
    }

    // Обработка событий колорпикера
    private void ColorPickerAction() {
        colorPicker.setOnAction((ActionEvent t) -> {
            color = colorPicker.getValue();
        });
    }

    public Pane getPane() {
        return pane;
    }

}
