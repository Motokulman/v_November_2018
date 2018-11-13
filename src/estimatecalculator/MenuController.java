/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author I
 */
public class MenuController implements Initializable
{
  @FXML
  private MenuBar menuBar;
    private Window stage;
  
  // Создание нового файла
  @FXML
  private void handleCreateNewFile(final ActionEvent event) throws XMLStreamException, IOException, ParserConfigurationException, SAXException, XPathExpressionException
  {
    provideCreateNewFile();
  }
  // Сохранение файла
  @FXML
  private void handleSaveFileAs(final ActionEvent event) throws XMLStreamException, IOException, ParserConfigurationException, SAXException, XPathExpressionException
  {
    provideSaveFileAs();
  }
  
// Открытие файла
  @FXML
  private void handleOpenFile(final ActionEvent event) throws XMLStreamException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, ClassNotFoundException
  {
    provideOpenFile();
  }
  
  // Открытие файла
  private void provideOpenFile() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException {
//      
//    // Читаем файл настроек Config       
//    DocumentBuilderFactory factoryConfig = DocumentBuilderFactory.newInstance();
//    DocumentBuilder builderConfig = factoryConfig.newDocumentBuilder();
//    Document documentConfig = builderConfig.parse(new File("myConfig.xml"));
//    documentConfig.getDocumentElement().normalize();
//    // и получим из него путь последнего сохранения проекта
//    XPathFactory xPathFactory = XPathFactory.newInstance();
//    XPath xPath = xPathFactory.newXPath();
//    
//    FileChooser fileChooserCreate = new FileChooser();
//    fileChooserCreate.setTitle("Открыть файл");
//          
//    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Файл проекта .ser", "*.ser");//Расширение
//    fileChooserCreate.getExtensionFilters().add(extFilter);
//    fileChooserCreate.setInitialDirectory(new File(xPath.evaluate("//root/lastDir/text()", documentConfig.getDocumentElement())));
//    File file = fileChooserCreate.showOpenDialog(stage);
//      
//    if (file != null) {
//        try {
//            Scheme sC = new Scheme();
//            FileInputStream fis = new FileInputStream(file);
//            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
//                sC = (Scheme) ois.readObject();
//                ois.close();
//            }
////                pointsObservableList = FXCollections.observableArrayList(sC.getP());
////                couplesObservableList = FXCollections.observableArrayList(sC.getC());
////                axisXObservableList = FXCollections.observableArrayList(sC.getAxisX());
////                axisYObservableList = FXCollections.observableArrayList(sC.getAxisY());
//                border.setCenter(getVisualRedactor(600, 600, 14, 14));
//          }
//        catch (IOException ex) {
//             System.out.println(ex.getMessage());
//        }
//      }
  }
  // Сохранение файла
  private void provideSaveFileAs() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
//      
//    // Читаем файл настроек Config       
//    DocumentBuilderFactory factoryConfig = DocumentBuilderFactory.newInstance();
//    DocumentBuilder builderConfig = factoryConfig.newDocumentBuilder();
//    Document documentConfig = builderConfig.parse(new File("myConfig.xml"));
//    documentConfig.getDocumentElement().normalize();
//    // и получим из него путь последнего сохранения проекта
//    XPathFactory xPathFactory = XPathFactory.newInstance();
//    XPath xPath = xPathFactory.newXPath();
//    
//    FileChooser fileChooserCreate = new FileChooser();
//    fileChooserCreate.setTitle("Сохранить файл как...");
//          
//    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Файл проекта .ser", "*.ser");//Расширение
//    fileChooserCreate.getExtensionFilters().add(extFilter);
//    fileChooserCreate.setInitialDirectory(new File(xPath.evaluate("//root/lastDir/text()", documentConfig.getDocumentElement())));
//    File file = fileChooserCreate.showSaveDialog(stage);
//      
//    if (file != null) {
//        try {
////             ArrayList pointArrayList = new ArrayList(pointsObservableList);
////             ArrayList couplesArrayList = new ArrayList(couplesObservableList);
////             ArrayList axisXArrayList = new ArrayList(axisXObservableList);
////             ArrayList axisYArrayList = new ArrayList(axisYObservableList);
// //            Scheme sC = new Scheme(pointArrayList, couplesArrayList, axisXArrayList, axisYArrayList);
//             FileOutputStream fs = new FileOutputStream(file);
//            try (ObjectOutputStream os = new ObjectOutputStream(fs)) {
//                os.writeObject(sC);
//             System.out.println("Сохранили  " + sC.p.get(0));
//                os.close();        
//                
//            }
//          }
//        catch (IOException ex) {
//             System.out.println(ex.getMessage());
//        }
//      }
  }
  // Функции реализаций действий меню
  private void provideCreateNewFile() throws XMLStreamException, IOException, ParserConfigurationException, SAXException, XPathExpressionException
  {
    // Читаем файл настроек Config       
    DocumentBuilderFactory factoryConfig = DocumentBuilderFactory.newInstance();
    DocumentBuilder builderConfig = factoryConfig.newDocumentBuilder();
    Document documentConfig = builderConfig.parse(new File("myConfig.xml"));
    documentConfig.getDocumentElement().normalize();
    // и получим из него путь последнего сохранения проекта
    XPathFactory xPathFactory = XPathFactory.newInstance();
    XPath xPath = xPathFactory.newXPath();
    
    FileChooser fileChooserCreate = new FileChooser();
    fileChooserCreate.setTitle("Создать проект");
    
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Файл проекта XML", "*.xml");//Расширение
    fileChooserCreate.getExtensionFilters().add(extFilter);
    fileChooserCreate.setInitialDirectory(new File(xPath.evaluate("//root/lastDir/text()", documentConfig.getDocumentElement())));
    File file = fileChooserCreate.showSaveDialog(stage);
    // Создаем новый файл проекта
    if (file != null) {
        try {
            XMLOutputFactory factoryCreate = XMLOutputFactory.newFactory();
            XMLStreamWriter writerCreate = factoryCreate.createXMLStreamWriter(new FileOutputStream(file));
            writerCreate.writeStartDocument();
            writerCreate.writeStartElement("root");
            writerCreate.writeEndElement();
            writerCreate.writeEndDocument();
            // Если файл создался, то запишем путь его сохранения в конфиг для того, чтобы в след. раз открыть ту же папку
            NodeList languages  = documentConfig.getElementsByTagName("root");
            Element lang = (Element) languages.item(0);
            Node lastDir = lang.getElementsByTagName("lastDir").item(0).getFirstChild();
            lastDir.setNodeValue(file.getParent());
            // вызываем функцию записи
            writeDocument(documentConfig);
             
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
 }

  /**
   * Handle action related to "About" menu item.
   * 
   * @param event Event on "About" menu item.
   */
  @FXML
  private void handleAboutAction(final ActionEvent event)
  {
     provideAboutFunctionality();
  }

  /**
   * Handle action related to input (in this case specifically only responds to
   * keyboard event CTRL-A).
   * 
   * @param event Input event.
   */
  @FXML
  private void handleKeyInput(final InputEvent event)
  {
     if (event instanceof KeyEvent)
     {
        final KeyEvent keyEvent = (KeyEvent) event;
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A)
        {
           provideAboutFunctionality();
        }
     }
  }

  /**
   * Perform functionality associated with "About" menu selection or CTRL-A.
   */
  private void provideAboutFunctionality()
  {
     System.out.println("You clicked on About!");      
  }


 @Override
 public void initialize(java.net.URL arg0, ResourceBundle arg1) {
   menuBar.setFocusTraversable(true);
   
 } 
 
     // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("myConfig.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

}