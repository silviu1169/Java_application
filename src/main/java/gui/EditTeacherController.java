package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.TeacherService;
import validator.ValidationException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;


    @FXML
    private Label nameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label emailLabel;

    private Stage dialogStage;

    private TeacherService teacherService;

    private String teacherName;
    private String teacherFirstName;
    private String teacherEmail;


    public void setService(Stage dialogStage,
                                 TeacherService teacherService,
                                 String teacherName,
                                 String teacherFirstName,
                                 String teacherEmail) {
        this.dialogStage = dialogStage;
        this.teacherService = teacherService;
        this.teacherName = teacherName;
        this.teacherFirstName = teacherFirstName;
        this.teacherEmail = teacherEmail;
        this.setFields();
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
            dialogStage.close();
    }

    private void setFields(){
        nameLabel.setText(teacherName);
        firstNameLabel.setText(teacherFirstName);
        emailLabel.setText(teacherEmail);
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        try {
            teacherService.save(teacherName, teacherFirstName, teacherEmail);
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Teacher added", "The teacher has been added succesfully!");
        } catch (ValidationException e) {
            MessageAlert.showErrorMessage(null,e.getMessage());
        }
        dialogStage.close();
    }

    @FXML
    void initialize() {

    }
}
