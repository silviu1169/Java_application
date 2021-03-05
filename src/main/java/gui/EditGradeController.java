package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.GradeService;
import validator.ValidationException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditGradeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label homeworkLabel;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private GradeService gradeService;

    private Stage dialogStage;

    private String studentId;
    private String homeworkId;
    private String value;
    private String teacherId;
    private String feedback;
    private String homeworkDescription;
    private String studentName;
    private String teacherName;


    //----------------------Methods

    public void setService(GradeService gradeService,  Stage stage,String studentId, String homeworkId,String value,
                           String teacherId, String feedback, String homeworkDescription,
                           String studentName, String teacherName ) {
        this.gradeService = gradeService;
        this.dialogStage=stage;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.value = value;
        this.teacherId = teacherId;
        this.feedback = feedback;
        this.homeworkDescription = homeworkDescription;
        this.studentName = studentName;
        this.teacherName = teacherName;
        setFields();
    }

    private void setFields() {
        studentNameLabel.setText(studentName);
        homeworkLabel.setText(homeworkDescription);
        teacherNameLabel.setText(this.teacherName);
        valueLabel.setText(String.valueOf(this.value));
        feedbackLabel.setText(this.feedback);

    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        try {
            this.gradeService.save(studentId, homeworkId, value, teacherId, feedback, homeworkDescription, studentName, teacherName);
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION,"Grade added","The grade has been added!");
        } catch (ValidationException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
        dialogStage.close();
    }

    @FXML
    void initialize() {

    }
}
