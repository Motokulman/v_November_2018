package estimatecalculator;

import estimatecalculator.classes.Aperture;
import estimatecalculator.classes.database.MainDB;
import estimatecalculator.classes.database.ProjectDB;
import estimatecalculator.scheme.primitives.Point;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class EstimateCalculator extends Application {

    public static ProjectDB project_dB = new ProjectDB();
    public static MainDB main_dB = new MainDB();
    public static Settings settings = new Settings();
    public static BorderPane border = new BorderPane();
    public static ObservableList<Aperture> innerInsulateWallAperturesObservableList; // массив проемов

    public static ObservableList<Point> testPoint;

    public EstimateCalculator() {
        this.innerInsulateWallAperturesObservableList = FXCollections.observableArrayList();
        // System.out.println("EstimateCalculator");
        this.testPoint = FXCollections.observableArrayList();

    }

    public static void main(String[] arguments) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, SQLException {

        // Создаем экземпляр по работе с БД проекта
        try {
            project_dB.ProjectDBConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }      
        // Создаем экземпляр по работе с главной БД
        try {
            main_dB.MainDBConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Запускаем приложение
        Application.launch(EstimateCalculator.class, arguments);
        // Удаление временной базы данных при закрытии
        project_dB.deleteTempDB();
    }

    @Override
    public void start(final Stage stage) throws Exception {

        HBox hMenuBarBox = FXMLLoader.load(getClass().getResource("FXMLMenuBar.fxml"));
        border.setTop(hMenuBarBox);

        //VBox vLeftBox = FXMLLoader.load(getClass().getResource("FXMLNavPane.fxml"));
        border.setLeft(FXMLLoader.load(getClass().getResource("FXMLNavPane.fxml")));

        border.setCenter(FXMLLoader.load(getClass().getResource("propertypane/mainwall/FXMLPropertyPane.fxml")));
        // border.setCenter(FXMLLoader.load(getClass().getResource("propertypane/innerinsulatewall/InnerInsulateWallPropertyPane.fxml")));

        Scene scene = new Scene(border, 1400, 800);
        scene.getStylesheets().add(EstimateCalculator.class.getResource("base.css").toExternalForm());
        stage.setTitle("Estimate Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
