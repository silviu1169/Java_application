package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.StudentService;
import validator.ValidationException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label groupLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label teacherTrainigLabLabel;

    private Stage dialogStage;
    private StudentService studentService;
    private String name;
    private String firstName;
    private String email;
    private String group;
    private String teacherTrainingLab;

    public void setService(Stage dialogStage, StudentService studentService,
                                 String name, String firstName, String email,
                                 String group, String teacherTrainingLab) {
        this.dialogStage = dialogStage;
        this.studentService = studentService;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.group = group;
        this.teacherTrainingLab = teacherTrainingLab;
        setFields();
    }

    private void setFields(){
        nameLabel.setText(name);
        firstNameLabel.setText(firstName);
        groupLabel.setText(group);
        emailLabel.setText(email);
        teacherTrainigLabLabel.setText(teacherTrainingLab);
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        try {
            this.studentService.save(name, firstName, group, email, teacherTrainingLab);
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Student added!", "The student has been added!");
        } catch (ValidationException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
        dialogStage.close();
    }

    @FXML
    void initialize() {

    }
}
