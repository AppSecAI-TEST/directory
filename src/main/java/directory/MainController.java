package directory;

import data.DataManager;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Contact;
import model.Group;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //<editor-fold desc="����">

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label messageLabel;
    private DataManager dataManager;

    //</editor-fold>

    public MainController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void createContact() {

        int id = dataManager.getContactObservableList().get(dataManager.getContactObservableList().size() - 1).getId() + 1;
        //�������� ���� �� UI sgsdfg
        dataManager.getContactObservableList().add(new Contact(id, "", "", "", "", PhoneNumberType.MOBILE, "", PhoneNumberType.MOBILE, "", ""));

    }

    @FXML
    private void editContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        //�������� ������ �� UI
        Contact contact = new Contact();

        int editableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact editableContact = contactObservableList.get(i);

            //�������� ���� �� UI sgsdfg
            if (editableContact.getId() == editableContactId) {
                editableContact.setDescription("");
                editableContact.setEmail("");
                editableContact.setFirstName("");
                editableContact.setFirstPhoneNumber("");
                editableContact.setFirstPhoneNumberType(PhoneNumberType.MOBILE);
                editableContact.setLastName("");
                editableContact.setMiddleName("");
                editableContact.setSecondPhoneNumber("");
                editableContact.setSecondPhoneNumberType(PhoneNumberType.MOBILE);
            }
        }

    }

    @FXML
    private void deleteContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        //�������� ������ �� UI
        Contact contact = new Contact();

        int deletableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact deletableContact = contactObservableList.get(i);

            if (deletableContact.getId() == deletableContactId)
                contactObservableList.remove(i);
        }

    }

    @FXML
    private void createGroup() {

        int id = ((Group) dataManager.getGroupObservableSet().toArray()[dataManager.getGroupObservableSet().toArray().length - 1]).getId() + 1;
        //�������� ���� �� UI sgsdfg
        dataManager.getGroupObservableSet().add(new Group(id, "", ""));

    }

    @FXML
    private void editGroup() {

        ObservableSet<Group> groupObservableSet = dataManager.getGroupObservableSet();

        //�������� ������ �� UI
        Group group = new Group();

        int editableGroupId = group.getId();

        Iterator<Group> groupIterator = groupObservableSet.iterator();

        while (groupIterator.hasNext()) {
            Group editableGroup = groupIterator.next();

            //�������� ���� �� UI sgsdfg
            if (editableGroup.getId() == editableGroupId) {
                editableGroup.setName("");
                editableGroup.setDescription("");
            }
        }

    }

    @FXML
    private void deleteGroup() {

        ObservableSet<Group> groupObservableSet = dataManager.getGroupObservableSet();

        //�������� ������ �� UI
        Group group = new Group();

        int editableGroupId = group.getId();

        Iterator<Group> groupIterator = groupObservableSet.iterator();

        while (groupIterator.hasNext()) {
            Group deletableGroup = groupIterator.next();

            //�������� ���� �� UI sgsdfg
            if (deletableGroup.getId() == editableGroupId)
                groupObservableSet.remove(deletableGroup);
        }

    }

}
