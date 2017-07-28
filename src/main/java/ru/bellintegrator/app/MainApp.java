package ru.bellintegrator.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ru.bellintegrator.app.controller.AdditionalController;
import ru.bellintegrator.app.controller.MainController;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.factory.impl.xml.XmlParserType;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.sax.SAXUtilForContact;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.validation.xml.Validator;
import ru.bellintegrator.app.validation.xml.impl.XMLValidator;

import javax.xml.XMLConstants;
import java.io.IOException;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

//        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
//        GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
//        GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
//        ContactService contactService = new ContactService(contactGenericDAO);
//        GroupService groupService = new GroupService(groupGenericDAO, contactService);

        DAOFactory.setParserType(XmlParserType.SAX);
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.XML);
        GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
        GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
        ContactService contactService = new ContactService(contactGenericDAO);
        GroupService groupService = new GroupService(groupGenericDAO, contactService);

        System.out.println("contactGenericDAO class is "+contactGenericDAO.getClass());
        System.out.println("groupGenericDAO class is "+groupGenericDAO.getClass());

        //test validation xml
        Validator validator = new XMLValidator("F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xml\\groups.xml"
                , "F:\\Data\\idea\\projects\\directory\\src\\main\\resources\\xsd\\groups.xsd"
                , XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            validator.validate();
            System.out.println("xml is valid");
        } catch (SAXException | IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        SAXUtilForContact saxUtilForContact = new SAXUtilForContact();
        System.out.println("getById:" + saxUtilForContact.getById(2));
        System.out.println("getByName:" + saxUtilForContact.getByName("Контакт1").toString());

        String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();

        loader.setController(new MainController(contactService, groupService));
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 800, 500);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Справочник контактов");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        showAdditionalWindow(contactService);

    }

    private void showAdditionalWindow(ContactService contactService) {

        String stageTitle = "";
        String fxmlPath = "/fxml/additional.fxml";
        FXMLLoader loader = null;
        AnchorPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        AdditionalController additionalController = new AdditionalController(contactService);

        try {
            loader = new FXMLLoader();
            loader.setController(additionalController);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            page = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            scene = new Scene(page, 760, 240);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.setTitle("Список контактов");

            contactService.addContactListChangeObserver(additionalController);

            dialogStage.showAndWait();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

}
