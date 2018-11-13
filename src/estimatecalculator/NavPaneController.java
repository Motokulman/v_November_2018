/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator;

import static estimatecalculator.EstimateCalculator.border;
import estimatecalculator.classes.bottom.BottomPane;
import static estimatecalculator.visualredactor.VisualRedactorScheme.getModelScheme;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author I
 */
public class NavPaneController implements Initializable {

    @FXML
    private TreeView<String> NavPaneTreeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Добавляем корневой узел. Сделать, чтобы он назывался как проект либо разобраться, как сделать чтоб его не было, а следующие узлы стали корневыми
        TreeItem<String> projectTreeItem = new TreeItem<>("Наш проект");
        projectTreeItem.setExpanded(true);
        NavPaneTreeView.setRoot(projectTreeItem);

        TreeItem<String> wallsTreeItem = new TreeItem<>("Стены");
        projectTreeItem.getChildren().add(wallsTreeItem);
        wallsTreeItem.setExpanded(true);

        TreeItem<String> fasadeWallTreeItem = new TreeItem<>("Фасадные стены");
        wallsTreeItem.getChildren().add(fasadeWallTreeItem);
        fasadeWallTreeItem.setExpanded(true);

        TreeItem<String> mainFasadeWallTreeItem = new TreeItem<>("Базовая стена");
        fasadeWallTreeItem.getChildren().add(mainFasadeWallTreeItem);

        TreeItem<String> secondFasadeWallTreeItem = new TreeItem<>("Стена гаража например");
        fasadeWallTreeItem.getChildren().add(secondFasadeWallTreeItem);

        TreeItem<String> innerInsulateWallTreeItem = new TreeItem<>("Внутренняя теплая стена Уровни");
        wallsTreeItem.getChildren().add(innerInsulateWallTreeItem);

        TreeItem<String> innerLoaderWallTreeItem = new TreeItem<>("Упаковка");
        wallsTreeItem.getChildren().add(innerLoaderWallTreeItem);

        TreeItem<String> partitionWallTreeItem1 = new TreeItem<>("Перегородка по материалу стен 1 (толстая)");
        wallsTreeItem.getChildren().add(partitionWallTreeItem1);

        TreeItem<String> partitionWallTreeItem2 = new TreeItem<>("Перегородка по материалу стен 2 (тонкая)");
        wallsTreeItem.getChildren().add(partitionWallTreeItem2);

        TreeItem<String> partitionWallTreeItem3 = new TreeItem<>("Перегородка из конкретного материала");
        wallsTreeItem.getChildren().add(partitionWallTreeItem3);

        TreeItem<String> partitionWallTreeItem4 = new TreeItem<>("Перегородка из гипсокартона");
        wallsTreeItem.getChildren().add(partitionWallTreeItem4);

        TreeItem<String> partitionWallTreeItem5 = new TreeItem<>("Перегородка из кирпича");
        wallsTreeItem.getChildren().add(partitionWallTreeItem5);

        TreeItem<String> visualRedactorTreeItem = new TreeItem<>("werwer");
        projectTreeItem.getChildren().add(visualRedactorTreeItem);
        //wallsTreeItem.setExpanded(true);

        NavPaneTreeView.setPrefHeight(1000.0);
    }

    // Выбираем в левой панели нужные итемс и меняем центр панели
    public void mouseTreeItemClick(MouseEvent mouseEvent) throws IOException, SQLException {
        switch (NavPaneTreeView.getSelectionModel().getSelectedIndex()) {
            case 2:
                border.setCenter(FXMLLoader.load(getClass().getResource("propertypane/mainwall/FXMLPropertyPane.fxml")));
                break;
            case 3:
                border.setCenter(FXMLLoader.load(getClass().getResource("propertypane/innerinsulatewall/InnerInsulateWallPropertyPane.fxml")));
                break;
            case 4:
                border.setCenter(FXMLLoader.load(getClass().getResource("propertypane/innerloadedwall/InnerLoadedWall.fxml")));
                break;
            case 5:
                border.setCenter(FXMLLoader.load(getClass().getResource("fxml/levels/levelEditorFXML.fxml")));
                break;
            case 6:
                border.setCenter(FXMLLoader.load(getClass().getResource("fxml/materialpackages/PackagesEditorFXML.fxml")));
                break;
            case 7:
                System.out.println("getVisualRedactor(600, 600, 14, 14)");
                break;
            case 8:
                BottomPane bP = new BottomPane();
                border.setRight(bP.getPane());
                border.setCenter(getModelScheme());
                System.out.println("getModelScheme");
                break;
                

        }

    }
}
