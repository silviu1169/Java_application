package gui;

import domain.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.HomeworkService;
import service.StudentService;
import validator.ValidationException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditHomeworkController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label descriptionLabel;

    @FXML
    private Label startWeekLabel;

    @FXML
    private Label deadlineWeekLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    private HomeworkService homeworkService;

    private Stage dialogStage;
    private String homeworkDescription;
    private String deadlineWeek;
    private StudentService studentService;
    private Teacher teacher;


    public void setService(Stage dialogStage, HomeworkService homeworkService, StudentService studentService, Teacher teacher,
                            String homeworkDescription, String deadlineWeek){
        this.homeworkService = homeworkService;
        this.studentService = studentService;
        this.homeworkDescription = homeworkDescription;
        this.dialogStage = dialogStage;
        this.deadlineWeek = deadlineWeek;
        this.teacher = teacher;
        setFields();
    }

    private void setFields() {
        descriptionLabel.setText(homeworkDescription);
        startWeekLabel.setText(String.valueOf(homeworkService.getCurrentWeek()));
        deadlineWeekLabel.setText(deadlineWeek);
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        try {

            homeworkService.save(homeworkDescription, deadlineWeek, this.teacher.getEmail());
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Homework added!","The homework has been added!");

            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION,"Emails sent!", "The emails has been sent successfully! \nThe students has been anounced that a new \nhomework has been added!");
        } catch (ValidationException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
        dialogStage.close();
    }

    @FXML
    void initialize() {

    }
}
