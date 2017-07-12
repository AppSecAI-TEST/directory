package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class GroupEditorController {

    private static final Logger log = LoggerFactory.getLogger(GroupEditorController.class);

    //<editor-fold desc="����">

    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    Group group;

    //</editor-fold>

    //<editor-fold desc="������ ��������� � ���������">

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {

        this.group = group;

        groupNameTextField.setText(group.getName());
        groupNotesTextArea.setText(group.getNotes());

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //</editor-fold>

    //edit
//    int editableGroupId = group.getId();
//
//    Iterator<Group> groupIterator = groupObservableSet.iterator();
//
//    while (groupIterator.hasNext()) {
//        Group editableGroup = groupIterator.next();
//
//        //�������� ���� �� UI sgsdfg
//        if (editableGroup.getId() == editableGroupId) {
//            editableGroup.setName("");
//            editableGroup.setNotes("");
//        }
//    }


}
