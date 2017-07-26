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
import ru.bellintegrator.app.controller.AdditionalController;
import ru.bellintegrator.app.controller.MainController;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.parser.sax.SAXUtilForGroup;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

import java.io.IOException;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
        GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
        GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
        ContactService contactService = new ContactService(contactGenericDAO);
        GroupService groupService = new GroupService(groupGenericDAO, contactService);

//        DOMUtilForGroup domUtilForGroup = new DOMUtilForGroup();
//        System.out.println(domUtilForGroup.getAll().toString());
//        domUtilForGroup.create(new Group(0, "g1", "g1"));
//        domUtilForGroup.update(new Group(111, "g2", "g2"));
//        domUtilForGroup.delete(new Group(111, "", ""));

//        DOMUtilForContact domUtilForContact = new DOMUtilForContact();
//        System.out.println(domUtilForContact.getAll().toString());
//        domUtilForContact.create(new Contact(0, "c1", "c1", "c1"));
//        domUtilForContact.delete(new Contact(111, "", "", ""));
//        domUtilForContact.update(new Contact(111, "c2", "c2", "c2", "2", PhoneNumberType.HOME, "2", PhoneNumberType.WORKING, "2", "2"));
//        domUtilForContact.save(Arrays.asList(new Contact(1, "", "", ""), new Contact(1, "", "", ""), new Contact(1, "", "", "")));

        SAXUtilForGroup saxUtilForGroup = new SAXUtilForGroup();
        System.out.println(saxUtilForGroup.getAll().toString());

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
