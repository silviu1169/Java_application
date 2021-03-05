package gui;

import domain.*;
import events.GradeChangeEvent;
import events.HomeworkChangeEvent;
import events.StudentChangeEvent;
import events.TeacherChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Observer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.GradeService;
import service.HomeworkService;
import service.StudentService;
import service.TeacherService;
import validator.ValidationException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerTeacher  {
    @FXML
    private Label firstNameLabelFromTeachersTab;
    @FXML
    private Label emailLabelFromTeachersTab;
    @FXML
    private Label nameLabelFromTeachersTab;
    @FXML
    private Label teacherIdLabelFromTeachersTab;
    @FXML
    private TextField studentNameTextFieldFromGradesTab;

    private Teacher teacher;
    private Student student;

    @FXML
    public Button previousPageButtonFromGradesTab;

    @FXML
    public CheckBox pageCheckBoxFromGradesTab;

    @FXML
    public TextField pageTextFieldFromGradesTab;

    @FXML
    public Button nextPageButtonFromGradesTab;

    @FXML
    public Button previousPageButtonFromStudentsTab;

    @FXML
    public CheckBox pageCheckBoxFromStudentsTab;

    @FXML
    public TextField pageTextFieldFromStudentsTab;

    @FXML
    public Button nextPageButtonFromStudentsTab;

    @FXML
    public Button previousPageButtonFromHomeworksTab;

    @FXML
    public CheckBox pageCheckBoxFromHomeworksTab;

    @FXML
    public TextField pageTextFieldFromHomeworksTab;

    @FXML
    public Button nextPageButtonFromHomeworksTab;

    @FXML
    public Button previousPageButtonFromTeachersTab;

    @FXML
    public CheckBox pageCheckBoxFromTeachersTab;

    @FXML
    public TextField pageTextFieldFromTeachersTab;

    @FXML
    public Button nextPageButtonFromTeachersTab;


//*******************************************************************************************
//***************************- THE BEGIN OF ATTRIBUTES -*************************************
//*******************************************************************************************


    private ObservableList<Student> studentsModel = FXCollections.observableArrayList();

    private ObservableList<Teacher> teachersModel = FXCollections.observableArrayList();

    private ObservableList<Grade> gradesModel = FXCollections.observableArrayList();

    private ObservableList<Homework> homeworksModel = FXCollections.observableArrayList();

    private ObservableList<String> groupChoiceBoxModelFromStudentsTab= FXCollections.observableArrayList();

    private ObservableList<Integer> valueChoiceBoxModelFromGradesTab= FXCollections.observableArrayList();

    private ObservableList<String> latePublicationOfGradesComboBoxModelFromGradesTab = FXCollections.observableArrayList();

    private ObservableList<String> reportsChoiceBoxModelFromGradesTab= FXCollections.observableArrayList();

    private ObservableList<Integer> startWeekChoiceBoxModelFromHomeworksTab= FXCollections.observableArrayList();

    private ObservableList<Integer> deadlineWeekChoiceBoxModelFromHomeworksTab= FXCollections.observableArrayList();

    private ObservableList<String> homeworkIdComboBoxModelFromGradesTab= FXCollections.observableArrayList();

    private ObservableList<String> studentIdComboBoxModelFromGradesTab= FXCollections.observableArrayList();

    private StudentService studentService;

    private TeacherService teacherService;

    private HomeworkService homeworkService;

    private GradeService gradeService;

    private UnivYearStructure univYearStructure;



    //
//  The begin of GradesTab's attributes ---------------------------------------------------------------
    @FXML
    private TextArea feedbackTextAreaFromGradesTab;

    @FXML
    private ComboBox latePublicationOfGradesComboBoxFromGradesTab;

    @FXML
    private ComboBox studentIdComboBoxFromGradesTab;

    @FXML
    private TextField valueTextFieldFromGradesTab;

    @FXML
    private Label latePublicationOfGradesLabelFromGradesTab;

    @FXML
    private ComboBox homeworkIdComboBoxFromGradesTab;

    @FXML
    private TextField feedbackTextFieldFromGradesTab;

    @FXML
    private ChoiceBox<Integer> latePublicationOfGradesChoiceBoxFromGradesTab;

    @FXML
    private TableView<Grade> tableFromGradesTab;

    @FXML
    private TableColumn<Grade, String> tableFromGradesTabColumnStudentName;

    @FXML
    private TableColumn<Grade, String> tableFromGradesTabColumnHomeworkId;

    @FXML
    private TableColumn<Grade, String> tableFromGradesTabColumnValue;

    @FXML
    private TableColumn<Grade, String> tableFromGradesTabColumnTeacherId;

    @FXML
    private TextField studentIdTextFieldFromGradesTab;

    @FXML
    private TextField homeworkIdTextFieldFromGradesTab;

    @FXML
    private Button addButtonFromGradesTab;

    @FXML
    private Button deleteButtonFromGradesTab;

    @FXML
    private Button updateButtonFromGradesTab;

    @FXML
    private ChoiceBox<Integer> valueChoiceBoxFromGradesTab;

    @FXML
    private TextField teacherIdTextFieldFromGradesTab;

    @FXML
    private CheckBox motivationCheckBoxFromGradesTab;

    @FXML
    private TextField studentIdFilterTextFieldFromGradesTab;

    @FXML
    private TextField homeworkIdFilterTextFieldFromGradesTab;

    @FXML
    private TextField valueFilterTextFieldFromGradesTab;

    @FXML
    private TextField teacherIdFilterTextFieldFromGradesTab;

    @FXML
    private Button clearFilterFieldsButtonFromGradesTab;

    @FXML
    private Button saveButtonFromGradesTab;

    @FXML
    private Button viewButtonFromGradesTab;

    @FXML
    private ChoiceBox<String> reportChoiceBoxFromGradesTab;

    @FXML
    private Button clearFieldsButtonFromGradesTab;

    @FXML
    private CheckBox latePublicationOfGradesCheckBoxFromGradesTab;
//
//  The end of GradesTab's attributes ---------------------------------------------------------------


//################################################################################################################


    //
//  The begin of StudentsTab's attributes -----------------------------------------------------------

    @FXML
    private Button clearFilterFieldsFromButtonStudentsTab;

    @FXML
    private Button clearFieldsButtonFromStudetsTab;

    @FXML
    private TableView<Student> tableFromStudentsTab;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnId;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnName;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnFirstName;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnGroup;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnEmail;

    @FXML
    private TableColumn<Student, String> tableFromStudentsTabColumnTeacherTrainingLab;

    @FXML
    private TextField nameTextFieldFromStudentsTab;

    @FXML
    private TextField firstNameTextFieldFromStudentsTab;

    @FXML
    private ChoiceBox<String> groupChoiceBoxFromStudentsTab;

    @FXML
    private TextField emailTextFieldFromStudentsTab;

    @FXML
    private TextField teacherIdTextFieldFromStudentsTab;

    @FXML
    private TextField idFilterTextFieldFromStudentsTab;

    @FXML
    private TextField nameFilterTextFieldFromStudentsTab;

    @FXML
    private TextField firstNameFilterTextFieldFromStudentsTab;

    @FXML
    private TextField groupFilterTextFieldFromStudentsTab;

    @FXML
    private TextField emailFilterTextFieldFromStudentsTab;

    @FXML
    private TextField teacherIdFilterTextFieldFromStudentsTab;
//
// The end of StudentsTab's attributes ---------------------------------------------------------


//################################################################################################################


//
// The begin of HomeworksTab's attributes ------------------------------------------------------

    @FXML
    private Button saveButtonFromHomeworksTab;

    @FXML
    private Button viewButtonFromHomeworksTab;

    @FXML
    private ChoiceBox<String> reportChoiceBoxFromHomeworksTab;

    @FXML
    private Button clearFilterFieldsButtonFromHomeworksTab;

    @FXML
    private Button clearFieldsButtonFromHomeworksTab;

    @FXML
    private TableView<Homework> tableFromHomeworksTab;

    @FXML
    private TableColumn<Homework, String> tableFromHomeworksTabColumnId;

    @FXML
    private TableColumn<Homework, String> tableFromHomeworksTabColumnDescription;

    @FXML
    private TableColumn<Homework, String> tableFromHomeworksTabColumnStartWeek;

    @FXML
    private TableColumn<Homework, String> tableFromHomeworksTabColumnDeadlineWeek;

    @FXML
    private TextField descriptionTextFieldFromHomeworksTab;

    @FXML
    private Button addButtonFromHomeworksTab;

    @FXML
    private Button deleteButtonFromHomeworksTab;

    @FXML
    private Button updateButtonFromHomeworksTab;

    @FXML
    private ChoiceBox<Integer> startWeekChoiceBoxFromHomeworksTab;

    @FXML
    private ChoiceBox<Integer> deadlineWeekChoiceBoxFromHomeworksTab;

    @FXML
    private TextField idFilterTextFieldFromHomeworksTab;

    @FXML
    private TextField descriptionFilterTextFieldFromHomeworksTab;

    @FXML
    private TextField startWeekFilterTextFieldFromHomeworksTab;

    @FXML
    private TextField deadlineWeekFilterTextFieldFromHomeworksTab;
//
// The end of HomeworksTab's attributes ------------------------------------------------------


//################################################################################################################


    //
// The begin of TeachersTab's attributes ------------------------------------------------------

// The end of TeachersTab's attributes ------------------------------------------------------


//#########################################################################################


    //
// The begin of MenuBar's attributes -------------------------------------------------------
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private RadioMenuItem theme1;

    @FXML
    private RadioMenuItem theme2;

    @FXML
    private MenuItem aboutMenuItem;

    private Stage stage;
//
// The end of MenuBar's attributes -------------------------------------------------------




//*******************************************************************************************
//***************************- THE BEGIN OF METHODS -****************************************
//*******************************************************************************************


    //
// The begin of GradesTab's methods ---------------------------------------------------------
    @FXML
    public void handleStudentIdFilterTextFieldFromGradesTab(KeyEvent keyEvent) {
        keyEvent.consume();
        List<Grade> initList = new ArrayList<Grade>();

        for (Grade grade :
                this.gradeService.findAll()) {
            initList.add(grade);
        }

        int value = 0;
        if( ! valueFilterTextFieldFromGradesTab.getText().equals("")) {
            value = Integer.parseInt(valueFilterTextFieldFromGradesTab.getText());
            List<Grade> gradeList = this.gradeService.findAllByHomeworkId(
                    homeworkIdFilterTextFieldFromGradesTab.getText(),
                    this.gradeService.findALlByStudentId(
                            studentIdFilterTextFieldFromGradesTab.getText(),
                            this.gradeService.findAllByTeacherId(
                                    teacherIdFilterTextFieldFromGradesTab.getText(),
                                    this.gradeService.findAllByValue(
                                            value,
                                            initList
                                    )
                            )
                    )
            );
            gradesModel.setAll(gradeList);
            tableFromGradesTab.setItems(gradesModel);
        }
        else {
            List<Grade> gradeList = this.gradeService.findAllByHomeworkId(
                    homeworkIdFilterTextFieldFromGradesTab.getText(),
                    this.gradeService.findALlByStudentId(
                            studentIdFilterTextFieldFromGradesTab.getText(),
                            this.gradeService.findAllByTeacherId(
                                    teacherIdFilterTextFieldFromGradesTab.getText(),
                                    initList
                            )
                    )
            );
            gradesModel.setAll(gradeList);
            tableFromGradesTab.setItems(gradesModel);
        }

    }

    @FXML
    public void handleValueFilterTextFieldFromGradesTab(KeyEvent keyEvent) {
        handleStudentIdFilterTextFieldFromGradesTab(keyEvent);
    }

    @FXML
    public void handleHomeworkIdFilterTextFieldFromGradesTab(KeyEvent keyEvent) {
        handleStudentIdFilterTextFieldFromGradesTab(keyEvent);
    }


    @FXML
    public void handleMotivationCheckBoxFromGradesTab(ActionEvent actionEvent) {
        if (motivationCheckBoxFromGradesTab.isSelected())
            this.valueTextFieldFromGradesTab.setText(String.valueOf(Integer.parseInt(this.valueTextFieldFromGradesTab.getText()) + 1));
        else
            this.valueTextFieldFromGradesTab.setText(String.valueOf(Integer.parseInt(this.valueTextFieldFromGradesTab.getText()) - 1));
    }

    @FXML
    public void handleUpdateButtonFromGradesTab(ActionEvent actionEvent) {
        if(tableFromGradesTab.getSelectionModel().getSelectedItem() != null){
            Grade grade = tableFromGradesTab.getSelectionModel().getSelectedItem();
            int value = 1;
            try{
                value = Integer.parseInt(valueTextFieldFromGradesTab.getText());
            }catch (Exception e){
                MessageAlert.showErrorMessage(null, "Invalid value!");
            }

            String automateFeedback = "";
            if (this.homeworkService.findOne(grade.getHomeworkId()).getDeadlineWeek() < this.univYearStructure.getCurrentWeek())
                automateFeedback += "The grade has been reduced with "
                        +
                        String.valueOf(10- Integer.parseInt(valueTextFieldFromGradesTab.getText()))
                        +
                        "points due to delays. ";

            grade.setFeedback(automateFeedback.concat(feedbackTextAreaFromGradesTab.getText()));

            this.gradeService.update(
                    grade.getStudentId(),
                    grade.getHomeworkId(),
                    String.valueOf(value),
                    grade.getTeacherId(),
                    automateFeedback.concat(feedbackTextAreaFromGradesTab.getText())
            );
        }
        else{
            MessageAlert.showErrorMessage(null, "You didn't select any grade!\n Select a grade and then try again!");
        }
        // updateGradesModel();
    }

    @FXML
    public void handleDeleteButtonFromGradesTab(ActionEvent actionEvent) {
        if (tableFromGradesTab.getSelectionModel().getSelectedItem() != null) {
            this.gradeService.delete(tableFromGradesTab.getSelectionModel().getSelectedItem().getStudentId() + " " + tableFromGradesTab.getSelectionModel().getSelectedItem().getHomeworkId());
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Grade deleted", "The grade has been deleted!");
        }
        else{
            MessageAlert.showErrorMessage(null, "You didn't select any grade!\n Select the grade you want to delete\n and then try again!");
        }
        // updateGradesModel();
    }

    @FXML
    public void handleAddButtonFromGradesTab(ActionEvent actionEvent) {
        try {

            Student student = this.studentService.findOneByNameAndFirstName((String) studentIdComboBoxFromGradesTab.getValue());

            Homework homework = this.homeworkService.findOneByDescription((String) homeworkIdComboBoxFromGradesTab.getValue());

            Teacher teacher = this.teacherService.findOneByNameAndFirstName(teacherIdTextFieldFromGradesTab.getText());

            if (student == null)
                throw new ValidationException("The student doesn't exist");
            if (homework == null)
                throw new ValidationException("The homework doesn't exist");

            Grade grade = new Grade(student.getId(), homework.getId(), Integer.parseInt(valueTextFieldFromGradesTab.getText()),
                    this.univYearStructure.getCurrentDateTime(), teacher.getId());
            String automateFeedback = "";
            if (homework.getDeadlineWeek() < this.univYearStructure.getCurrentWeek())
                automateFeedback += "The grade has been reduced with "
                        +
                        String.valueOf(10- Integer.parseInt(valueTextFieldFromGradesTab.getText()))
                        +
                        "points due to delays. ";

            grade.setFeedback(automateFeedback.concat(feedbackTextAreaFromGradesTab.getText()));
            grade.setHomeworkDescription(homework.getDescription());
            grade.setStudentName(student.getName().concat(" " + student.getFirstName()));
            grade.setTeacherName(teacher.getName().concat(" " + teacher.getFirstName()));

            showEditGradeDialog(
                    student.getId(),
                    homework.getId(),
                    valueTextFieldFromGradesTab.getText(),
                    teacher.getId(),
                    automateFeedback.concat(feedbackTextAreaFromGradesTab.getText()),
                    homework.getDescription(),
                    student.getName().concat(" " + student.getFirstName()),
                    teacher.getName().concat(" " + teacher.getFirstName())
            );
            this.clearFieldsFromGradesTab();
            //MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Grade added","The grade has been added!");
            //updateGradesModel();
        } catch (ValidationException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }

    }

    @FXML
    public void clearFilterFieldsFromGradesTab(){
        studentIdFilterTextFieldFromGradesTab.setText("");
        homeworkIdFilterTextFieldFromGradesTab.setText("");
        valueFilterTextFieldFromGradesTab.setText("");
        teacherIdFilterTextFieldFromGradesTab.setText("");
        this.updateGradesModel();
    }

    @FXML
    public void clearFieldsFromGradesTab(){
        int size = studentIdComboBoxModelFromGradesTab.size();
        if (size == 0)
            size--;
//        studentIdComboBoxModelFromGradesTab.remove(0,size);
        studentIdComboBoxFromGradesTab.getSelectionModel().select(0);
        initHomeworkIdComboBoxFromGradesTab();
        motivationCheckBoxFromGradesTab.setSelected(false);
        latePublicationOfGradesCheckBoxFromGradesTab.setSelected(false);
        teacherIdTextFieldFromGradesTab.setText("");
        valueTextFieldFromGradesTab.setText("");
        feedbackTextAreaFromGradesTab.setText("");
        studentNameTextFieldFromGradesTab.setText("");
        initStudentIdComboBoxFromGradesTab();
    }

    @FXML
    public void updateGradesModel() {
        List<Grade> gradeList = new ArrayList<>();
//        gradeService.findAll().forEach(gradeList::add);
        this.gradeService.getMessagesOnPage(this.gradeService.getPage()).forEach(gradeList::add);
        gradesModel.setAll(gradeList);
        tableFromGradesTab.setItems(gradesModel);
    }

    private void initHomeworkIdComboBoxFromGradesTab() {
        String homeworkDescription = "";
        String descriptionForCurrentHomework = "";
        for (Homework homework :
                this.homeworkService.findAll()) {
            homeworkDescription += homework.getDescription() + ",";
            if (homework.getDeadlineWeek() == this.univYearStructure.getCurrentWeek())
                descriptionForCurrentHomework = homework.getDescription();
        }
        homeworkIdComboBoxModelFromGradesTab.remove(0,homeworkIdComboBoxModelFromGradesTab.size());
        homeworkIdComboBoxModelFromGradesTab.addAll(homeworkDescription.split(","));
        homeworkIdComboBoxFromGradesTab.setItems(homeworkIdComboBoxModelFromGradesTab);
        homeworkIdComboBoxFromGradesTab.setValue(descriptionForCurrentHomework);
        valueTextFieldFromGradesTab.setText("10");

    }

    @FXML
    public void handleTableViewSelectionFromGradesTab(MouseEvent mouseEvent) {
        mouseEvent.consume();
        Grade grade = tableFromGradesTab.getSelectionModel().getSelectedItem();
        if ( grade != null){
            clearFieldsFromGradesTab();
            Student student = this.studentService.findOne(grade.getStudentId());

//            studentIdComboBoxFromGradesTab.getEditor().textProperty().addListener((obs, oldText, newText) -> {
//                studentIdComboBoxFromGradesTab.setValue(newText);
//            });

            //studentIdComboBoxFromGradesTab.setEditable(false);
            int size = studentIdComboBoxModelFromGradesTab.size();
            if (size >0 )
                size--;
            studentIdComboBoxModelFromGradesTab.remove(0, size);
            studentIdComboBoxFromGradesTab.getEditor().setText((student.getName() + " " + student.getFirstName()));
            studentIdComboBoxFromGradesTab.hide();
            //studentIdComboBoxFromGradesTab.setEditable(true);
            Homework homework = this.homeworkService.findOne(grade.getHomeworkId());
            homeworkIdComboBoxFromGradesTab.setValue(homework.getDescription());
            Teacher teacher = this.teacherService.findOne(grade.getTeacherId());
            teacherIdTextFieldFromGradesTab.setText(teacher.getName() + " " + teacher.getFirstName() );
            feedbackTextAreaFromGradesTab.setText(grade.getFeedback());
        }
    }

    @FXML
    public void handleLatePublicationOfGradesCheckBoxFromGradesTab(ActionEvent actionEvent) {
        actionEvent.consume();
        if(!latePublicationOfGradesCheckBoxFromGradesTab.isSelected()){
            latePublicationOfGradesComboBoxFromGradesTab.setDisable(true);
            latePublicationOfGradesLabelFromGradesTab.setDisable(true);


        }
        else {
            latePublicationOfGradesComboBoxFromGradesTab.setDisable(false);
            latePublicationOfGradesLabelFromGradesTab.setDisable(false);

        }

    }

    private void handleFirstReport() {
        showReportsDialog(
                this.gradeService.handleFirstReport()
        );
    }

    private void handleSecondReport() {
        showReportsDialog(
                this.gradeService.handleSecondReport()
        );
    }

    private void handleThirdReport() {
        showReportsDialog(
                this.gradeService.handleThirdReport()
        );
    }

    private void handleFourthReport() {
        showReportsDialog(
                this.gradeService.handleFourthReport()
        );
    }

    @FXML
    public void handleViewButtonFromGradesTab(ActionEvent actionEvent) {
        String report = reportChoiceBoxFromGradesTab.getValue();
        if (report == null)
            MessageAlert.showErrorMessage(null, "Choose a report first! ");
        else{
            if(report.contains("1")) {
                handleViewFirstSecondThirdReport(report);
            }
//            handleFirstReport();
            if(report.contains("2")) {
                handleViewFirstSecondThirdReport(report);
            }
//            handleSecondReport();
            if (report.contains("3"))
                handleViewFirstSecondThirdReport(report);
//            handleThirdReport();
            if(report.contains("4")) {
                handleViewFirstSecondThirdReport(report);
            }
//            handleFourthReport();
        }
    }


    @FXML
    public void handleHomeworkIdComboBoxFromGradesTab(ActionEvent actionEvent) {
        String homeworkDescription = (String) homeworkIdComboBoxFromGradesTab.getValue();
        Homework homework = this.homeworkService.findOneByDescription(homeworkDescription);
        if (homework != null)
            valueTextFieldFromGradesTab.setText(
                    String.valueOf(
                            this.homeworkService.maxGrade(homework.getId(),
                                    this.univYearStructure.getCurrentWeek())
                    )
            );
    }

    @FXML
    public void handleStudentNameTextFieldFromGradesTab(KeyEvent keyEvent){
        String studentName = studentNameTextFieldFromGradesTab.getText();
        ObservableList<String> newList= FXCollections.observableArrayList();
        for (Student student :
                this.studentService.findAll()) {

            if (student.getName().concat(" " + student.getFirstName()).contains(studentName))
                newList.add(student.getName() + " " + student.getFirstName());

        }




        studentIdComboBoxModelFromGradesTab.remove(0,studentIdComboBoxModelFromGradesTab.size());

        studentIdComboBoxModelFromGradesTab.setAll(newList);
//        studentIdComboBoxModelFromGradesTab.add("23");
//        studentIdComboBoxFromGradesTab.setItems(studentIdComboBoxModelFromGradesTab);
        studentIdComboBoxFromGradesTab.show();

    }

    @FXML
    public void handleStudentIdComboBoxFromGradesTab(ActionEvent keyEvent) {
//        System.out.println("here");
        String studentName = studentIdComboBoxFromGradesTab.getSelectionModel().getSelectedItem().toString();
        ObservableList<String> newList= FXCollections.observableArrayList();
        String studentsName = "";
        for (Student student :
                this.studentService.findAll()) {

            if (student.getName().concat(" " + student.getFirstName()).contains(studentName))
                newList.add(student.getName() + " " + student.getFirstName());

        }




        studentIdComboBoxModelFromGradesTab.remove(0,studentIdComboBoxModelFromGradesTab.size());

        studentIdComboBoxModelFromGradesTab.setAll(newList);
//        studentIdComboBoxModelFromGradesTab.add("23");
//        studentIdComboBoxFromGradesTab.setItems(studentIdComboBoxModelFromGradesTab);
        studentIdComboBoxFromGradesTab.show();
//        studentIdComboBoxFromGradesTab.getSelectionModel().select(0);
        //        System.out.println("done");
    }

    @FXML
    public void handleLatePublicationOfGradesComboBoxFromGradesTab(ActionEvent actionEvent) {
        String homeworkDescription = (String) homeworkIdComboBoxFromGradesTab.getValue();
        Homework homework = this.homeworkService.findOneByDescription(homeworkDescription);
        if (homework != null)
            valueTextFieldFromGradesTab.setText(
                    String.valueOf(
                            this.homeworkService.maxGrade(homework.getId(),
                                    Integer.parseInt((String) this.latePublicationOfGradesComboBoxFromGradesTab.getValue())
                            )
                    )
            );
    }

    public void showEditGradeDialog(String studentId, String homeworkId,String value,
                                    String teacherId, String feedback, String homeworkDescription,
                                    String studentName, String teacherName) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/editGrade.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Preview Grade");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditGradeController editGradeController = loader.getController();
            editGradeController.setService(
                    this.gradeService, dialogStage,studentId, homeworkId,
                    value, teacherId,feedback,homeworkDescription,
                    studentName, teacherName
            );

            dialogStage.show();

        } catch (Exception e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    public void showReportsDialog(String information) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/reportsGui.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Report");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            ReportController reportController = loader.getController();
            reportController.setInformation(information);

            dialogStage.show();

        } catch (Exception e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }
    public void showReportsDialog(String information, String title) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/reportsGui.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            ReportController reportController = loader.getController();
            reportController.setInformation(information);

            dialogStage.show();

        } catch (Exception e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    private void initGradeModel() {
        List<Grade> gradeList = new ArrayList<>();
        this.gradeService.findAll().forEach(gradeList::add);
        gradesModel.setAll(gradeList);
    }

    @FXML
    public void handleSaveButtonFromGradesTab(ActionEvent actionEvent) {
        try {
            String reportNo = reportChoiceBoxFromGradesTab.getValue();

            if (reportNo == null)
                MessageAlert.showErrorMessage(null, "You didn't selected any report!\n Select a report and try again later");
            else {
                String information = "";
                String reportName = "";
                if (reportNo.contains("1")) {
                    information = this.gradeService.handleFirstReport();
                    reportName = "report1-grade-lab-for-each-stud";
                }
                if (reportNo.contains("2")){
                    information = this.gradeService.handleSecondReport();
                    reportName = "report2-hardest-homework";
                }
                if (reportNo.contains("3")) {
                    information = this.gradeService.handleThirdReport();
                    reportName = "report3-students-who-can-join-the-exam";
                }
                if (reportNo.contains("4")){
                    information = this.gradeService.handleFourthReport();
                    reportName = "report4-the-students-who-have-showed-the-homework-in-time";
                }


                // create a Directory chooser
                DirectoryChooser directoryChooser = new DirectoryChooser();

                // get the file selected
                File selectedDirectory = directoryChooser.showDialog(this.stage);

                //get the path with --> selectedDirectory.getAbsolutePath()
                if (selectedDirectory != null) {
                    String path = selectedDirectory.getAbsolutePath() + "/"+ reportName + ".pdf";
//
//            //create the pdf file with the string information in it
                    //PDPage pdfPage = new PDPage();
                    //pdfDocument.addPage(pdfPage);

                    PDDocument pdfDocument = new PDDocument();
                    try{
                        PDPage page = new PDPage();
                        //write the information to the page
                        pdfDocument.addPage(page);
                        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

                        PDFont pdfFont = PDType1Font.HELVETICA;
                        float fontSize = 25;
                        float leading = 1.5f * fontSize;

                        PDRectangle mediabox = page.getMediaBox();
                        float margin = 72;
                        float width = mediabox.getWidth() - 2*margin;
                        float startX = mediabox.getLowerLeftX() + margin;
                        float startY = mediabox.getUpperRightY() - margin;

                        List<String> lines = new ArrayList<String>();

                        for (String text : information.split("\n")){
                            int lastSpace = -1;
                            while (text.length() > 0){
                                int spaceIndex = text.indexOf(' ', lastSpace + 1);
                                if (spaceIndex < 0)
                                    spaceIndex = text.length();
                                String subString = text.substring(0, spaceIndex);
                                float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                                if (size > width)
                                {
                                    if (lastSpace < 0)
                                        lastSpace = spaceIndex;
                                    subString = text.substring(0, lastSpace);
                                    lines.add(subString);
                                    text = text.substring(lastSpace).trim();

                                    lastSpace = -1;
                                }
                                else if (spaceIndex == text.length())
                                {
                                    lines.add(text);

                                    text = "";
                                }
                                else
                                {
                                    lastSpace = spaceIndex;
                                }
                            }
                        }


                        contentStream.beginText();
                        contentStream.setFont(pdfFont, fontSize);
                        contentStream.newLineAtOffset(startX, startY);
                        for (String line: lines)
                        {
                            contentStream.showText(line);
                            contentStream.newLineAtOffset(0, -leading);
                        }
                        contentStream.endText();
                        contentStream.close();



                        pdfDocument.save(path);
                    }
                    catch (IOException e) {
                        MessageAlert.showErrorMessage(null, e.getMessage());
                    }finally {
                        pdfDocument.close();
                    }


                }
            }
        } catch (IOException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }

    }


    private void handleViewFirstSecondThirdReport( String reportName){
        try{
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/viewReport.fxml"));

            AnchorPane root = null;
            root = (AnchorPane) loader.load();


            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(reportName);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            ViewReportController viewReportController = loader.getController();

            if (reportName.contains("1"))
                viewReportController.setDataForFirstReport(this.gradeService.mapForFirstAndThirdReport());
            if (reportName.contains("2"))
                viewReportController.setDataForSecondReport(this.gradeService.mapForSecondReport());
            if (reportName.contains("3"))
                viewReportController.setDataForThirdReport(this.gradeService.mapForFirstAndThirdReport());
            if (reportName.contains("4"))
                viewReportController.setDataForFourthReport(this.gradeService.mapForFourthReport());

            dialogStage.show();

        }catch (Exception e){

            MessageAlert.showErrorMessage(null, e.getMessage());
        }




    }
//
// The end of GradesTab's methods ---------------------------------------------------------


//#########################################################################################


    //
// The begin of StudentsTab's methods ------------------------------------------------------
    private void initStudentModel() {
        List<Student> studentList = new ArrayList<>();
        this.studentService.findAll().forEach(studentList::add);
        studentsModel.setAll(studentList);
    }


    @FXML
    public void handleNameFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        keyEvent.consume();
        List<Student> initList = new ArrayList<Student>();

        for(Student student:
                this.studentService.findAll()) {
            initList.add(student);
        }


        List<Student> studentList = this.studentService.findAllById(
                idFilterTextFieldFromStudentsTab.getText(),
                this.studentService.findAllByName(
                        nameFilterTextFieldFromStudentsTab.getText(),
                        this.studentService.findAllByFirstName(
                                firstNameFilterTextFieldFromStudentsTab.getText(),
                                this.studentService.findAllByGroup(
                                        groupFilterTextFieldFromStudentsTab.getText(),
                                        this.studentService.findAllByEmail(
                                                emailFilterTextFieldFromStudentsTab.getText(),
                                                this.studentService.findAllByTeacherTrainingLab(
                                                        teacherIdFilterTextFieldFromStudentsTab.getText(),
                                                        initList
                                                )
                                        )
                                )
                        )
                )

        );
        studentsModel.setAll(studentList);
        tableFromStudentsTab.setItems(studentsModel);
    }

    @FXML
    public void handleFirstNameFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        handleNameFilterTextFieldFromStudentsTab(keyEvent);
    }



    @FXML
    public void handleTeacherIdFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        handleNameFilterTextFieldFromStudentsTab(keyEvent);
    }

    @FXML
    public void handleEmailFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        handleNameFilterTextFieldFromStudentsTab(keyEvent);
    }

    @FXML
    public void handleGroupFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        handleNameFilterTextFieldFromStudentsTab(keyEvent);
    }

    @FXML
    public void handleIdFilterTextFieldFromStudentsTab(KeyEvent keyEvent) {
        handleNameFilterTextFieldFromStudentsTab(keyEvent);
    }


    @FXML
    public void clearFilterFieldsFromStudentsTab(){
        idFilterTextFieldFromStudentsTab.setText("");
        nameFilterTextFieldFromStudentsTab.setText("");
        firstNameFilterTextFieldFromStudentsTab.setText("");
        groupFilterTextFieldFromStudentsTab.setText("");
        emailFilterTextFieldFromStudentsTab.setText("");
        teacherIdTextFieldFromStudentsTab.setText("");
        this.updateStudentsModel();
    }

    @FXML
    public void clearFieldsFromStudentsTab(){
        nameTextFieldFromStudentsTab.setText("");
        firstNameTextFieldFromStudentsTab.setText("");
        emailTextFieldFromStudentsTab.setText("");
        groupChoiceBoxFromStudentsTab.getSelectionModel().clearSelection();
        teacherIdTextFieldFromStudentsTab.setText("");
    }

    @FXML
    public void updateStudentsModel() {
        List<Student> studentsList = new ArrayList<>();
//        studentService.findAll().forEach(studentsList::add);
        studentService.getMessagesOnPage(this.studentService.getPage()).forEach(studentsList::add);
        studentsModel.setAll(studentsList);
        tableFromStudentsTab.setItems(studentsModel);
    }

    @FXML
    public void handleTableViewSelectionFromStudentsTab(MouseEvent mouseEvent) {
        mouseEvent.consume();
        Student student = tableFromStudentsTab.getSelectionModel().getSelectedItem();
        if ( student != null){
            clearFieldsFromStudentsTab();
            nameTextFieldFromStudentsTab.setText(student.getName());
            firstNameTextFieldFromStudentsTab.setText(student.getFirstName());
            emailTextFieldFromStudentsTab.setText(student.getEmail());
            teacherIdTextFieldFromStudentsTab.setText(student.getTeacherTrainingLab());
            groupChoiceBoxFromStudentsTab.setValue(student.getGroup());
        }
    }

//
// The end of StudentsTab's methods ------------------------------------------------------


//########################################################################################


    //
// The begin of HomeworksTab's methods ------------------------------------------------------
    @FXML
    public void handleDeadlineWeekFilterTextFieldFromHomeworksTab(KeyEvent keyEvent) {
        handleIdFilterTextFieldFromHomeworksTab(keyEvent);
    }

    @FXML
    public void handleStartWeekFilterTextFieldFromHomeworksTab(KeyEvent keyEvent) {
        handleIdFilterTextFieldFromHomeworksTab(keyEvent);
    }

    @FXML
    public void handleDescriptionFilterTextFieldFromHomeworksTab(KeyEvent keyEvent) {
        handleIdFilterTextFieldFromHomeworksTab(keyEvent);
    }

    @FXML
    public void handleIdFilterTextFieldFromHomeworksTab(KeyEvent keyEvent) {
        keyEvent.consume();
        List<Homework> initList = new ArrayList<Homework>();
        List<Homework> homeworkList = null;
        for (Homework homework:
                this.homeworkService.findAll()
        ) {
            initList.add(homework);
        }
        if (startWeekFilterTextFieldFromHomeworksTab.getText().equals("")){
            if (deadlineWeekFilterTextFieldFromHomeworksTab.getText().equals("")){
                homeworkList = this.homeworkService.findAllById(
                        idFilterTextFieldFromHomeworksTab.getText(),
                        this.homeworkService.findAllByDescription(
                                descriptionFilterTextFieldFromHomeworksTab.getText(),
                                initList
                        )
                );
                homeworksModel.setAll(homeworkList);
                tableFromHomeworksTab.setItems(homeworksModel);
            }
            else{
                int deadlineWeek = Integer.parseInt(deadlineWeekFilterTextFieldFromHomeworksTab.getText());
                homeworkList = this.homeworkService.findAllByDeadlineWeek(
                        deadlineWeek,
                        this.homeworkService.findAllById(
                                idFilterTextFieldFromHomeworksTab.getText(),
                                this.homeworkService.findAllByDescription(
                                        descriptionFilterTextFieldFromHomeworksTab.getText(),
                                        initList
                                )
                        )
                );

                homeworksModel.setAll(homeworkList);
                tableFromHomeworksTab.setItems(homeworksModel);

            }
        }

        if (deadlineWeekFilterTextFieldFromHomeworksTab.equals("")){
            if(startWeekFilterTextFieldFromHomeworksTab.getText().equals("")){
                homeworkList = this.homeworkService.findAllById(
                        idFilterTextFieldFromHomeworksTab.getText(),
                        this.homeworkService.findAllByDescription(
                                descriptionFilterTextFieldFromHomeworksTab.getText(),
                                initList
                        )
                );
                homeworksModel.setAll(homeworkList);
                tableFromHomeworksTab.setItems(homeworksModel);
            }
            else{
                int startWeek = Integer.parseInt(startWeekFilterTextFieldFromHomeworksTab.getText());
                homeworkList = this.homeworkService.findAllByStartWeek(
                        startWeek,
                        this.homeworkService.findAllById(
                                idFilterTextFieldFromHomeworksTab.getText(),
                                this.homeworkService.findAllByDescription(
                                        descriptionFilterTextFieldFromHomeworksTab.getText(),
                                        initList
                                ))
                );
                homeworksModel.setAll(homeworkList);
                tableFromHomeworksTab.setItems(homeworksModel);
            }
        }
        if ((! startWeekFilterTextFieldFromHomeworksTab.getText().equals(""))
                && (! deadlineWeekFilterTextFieldFromHomeworksTab.getText().equals("")) ){
            int deadlineWeek = Integer.parseInt(startWeekFilterTextFieldFromHomeworksTab.getText());
            int startWeek = Integer.parseInt(startWeekFilterTextFieldFromHomeworksTab.getText());
            homeworkList = this.homeworkService.findAllByDeadlineWeek(
                    deadlineWeek,
                    this.homeworkService.findAllByStartWeek(
                            startWeek,
                            this.homeworkService.findAllById(
                                    idFilterTextFieldFromHomeworksTab.getText(),
                                    this.homeworkService.findAllByDescription(
                                            descriptionFilterTextFieldFromHomeworksTab.getText(),
                                            initList
                                    ))
                    )
            );

        }
        homeworksModel.setAll(homeworkList);
        tableFromHomeworksTab.setItems(homeworksModel);


    }

    private void showEditHomeworkDialog( String description, String deadlineWeek){
        try{
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/editHomework.fxml"));

            AnchorPane root = null;
            root = (AnchorPane) loader.load();


            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Preview Homework");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditHomeworkController editHomeworkController = loader.getController();
            editHomeworkController.setService(
                    dialogStage, this.homeworkService,
                    this.studentService,
                    teacher,
                    descriptionTextFieldFromHomeworksTab.getText(),
                    String.valueOf(deadlineWeekChoiceBoxFromHomeworksTab.getValue())
            );

            dialogStage.show();
            this.clearFieldsFromHomeworksTab();
        }catch (Exception e){
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    @FXML
    public void handleAddButtonFromHomeworksTab(ActionEvent actionEvent) {
        showEditHomeworkDialog(descriptionTextFieldFromHomeworksTab.getText(), String.valueOf(deadlineWeekChoiceBoxFromHomeworksTab.getValue()));
        clearFieldsFromHomeworksTab();
        //updateHomeworksModel();
    }

    @FXML
    public void handleDeleteButtonFromHomeworksTab(ActionEvent actionEvent) {
        if(tableFromHomeworksTab.getSelectionModel().getSelectedItem() != null){
            this.homeworkService.delete(tableFromHomeworksTab.getSelectionModel().getSelectedItem().getId());
            MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Homework deleted!","You deleted the homework ");
        }
        else{
            MessageAlert.showErrorMessage(null, "You didn't selected any homework!\n Select a homework and \n try again!");
        }
        //updateHomeworksModel();
    }

    @FXML
    public void handleUpdateButtonFromHomeworksTab(ActionEvent actionEvent) {
        if(tableFromHomeworksTab.getSelectionModel().getSelectedItem() != null){
            Homework homework = this.homeworkService.findOne(tableFromHomeworksTab.getSelectionModel().getSelectedItem().getId());
            try {
                this.homeworkService.update(
                        tableFromHomeworksTab.getSelectionModel().getSelectedItem().getId(),
                        descriptionTextFieldFromHomeworksTab.getText(),
                        String.valueOf(deadlineWeekChoiceBoxFromHomeworksTab.getValue()),
                        this.teacher.getEmail()
                );
                MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION, "Homework updated!", "The homework has been updated!");

                MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION,"Emails sent!", "The emails has been sent successfully! \nThe students has been anounced that the \ndeadline at this homework has been\n modified!");
            } catch (ValidationException e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }

        }
        else{
            MessageAlert.showErrorMessage(null, "You didn't select any homework!\n Select a homework you want to update\n and try again!");
        }
        //updateHomeworksModel();
    }


    @FXML
    public void clearFilterFieldsFromHomeworksTab(){
        idFilterTextFieldFromHomeworksTab.setText("");
        descriptionFilterTextFieldFromHomeworksTab.setText("");
        startWeekFilterTextFieldFromHomeworksTab.setText("");
        deadlineWeekFilterTextFieldFromHomeworksTab.setText("");
        this.updateHomeworksModel();
    }


    @FXML
    public void clearFieldsFromHomeworksTab(){
        descriptionTextFieldFromHomeworksTab.setText("");
        deadlineWeekChoiceBoxFromHomeworksTab.getSelectionModel().clearSelection();
        deadlineWeekChoiceBoxFromHomeworksTab.setValue(this.univYearStructure.getCurrentWeek());
        startWeekChoiceBoxFromHomeworksTab.getSelectionModel().clearSelection();
        startWeekChoiceBoxFromHomeworksTab.setValue(this.univYearStructure.getCurrentWeek());
    }

    @FXML
    public void updateHomeworksModel() {
        List<Homework> homeworksList = new ArrayList<>();
//        homeworkService.findAll().forEach(homeworksList::add);
        this.homeworkService.getMessagesOnPage(this.homeworkService.getPage()).forEach(homeworksList::add);
        homeworksModel.setAll(homeworksList);
        tableFromHomeworksTab.setItems(homeworksModel);
    }


    @FXML
    public void handleTableViewSelectionFromHomeworksTab(MouseEvent mouseEvent) {
        mouseEvent.consume();
        Homework homework = this.tableFromHomeworksTab.getSelectionModel().getSelectedItem();
        if ( homework != null){
            clearFieldsFromHomeworksTab();
            descriptionTextFieldFromHomeworksTab.setText(homework.getDescription());
            startWeekChoiceBoxFromHomeworksTab.setValue(homework.getStartWeek());
            deadlineWeekChoiceBoxFromHomeworksTab.setValue(homework.getDeadlineWeek());
        }
    }

    private void initHomeworkModel() {
        List<Homework> homeworkList = new ArrayList<>();
        this.homeworkService.findAll().forEach(homeworkList::add);
        homeworksModel.setAll(homeworkList);
    }

//
// The end of HomeworksTab's methods ------------------------------------------------------


//#########################################################################################


//
// The begin of TeachersTab's methods ------------------------------------------------------

    private void initTeacherModel() {
        List<Teacher> teacherList= new ArrayList<>();
        this.teacherService.findAll().forEach(teacherList::add);
        teachersModel.setAll(teacherList);
    }



//
// The end of TeachersTab's methods ------------------------------------------------------



    @FXML
    void initialize() {
//        this.studentService.addObserver(this); --not here, the services didn't set yet


        tableFromStudentsTabColumnId.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        tableFromStudentsTabColumnName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tableFromStudentsTabColumnFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        tableFromStudentsTabColumnGroup.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));
        tableFromStudentsTabColumnEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        tableFromStudentsTabColumnTeacherTrainingLab.setCellValueFactory(new PropertyValueFactory<Student, String>("teacherTrainingLab"));

        tableFromHomeworksTabColumnId.setCellValueFactory(new PropertyValueFactory<Homework, String>("id"));
        tableFromHomeworksTabColumnStartWeek.setCellValueFactory(new PropertyValueFactory<Homework, String>("startWeek"));
        tableFromHomeworksTabColumnDeadlineWeek.setCellValueFactory(new PropertyValueFactory<Homework, String>("deadlineWeek"));
        tableFromHomeworksTabColumnDescription.setCellValueFactory(new PropertyValueFactory<Homework, String>("description"));

        tableFromGradesTabColumnHomeworkId.setCellValueFactory(new PropertyValueFactory<Grade, String>("homeworkDescription"));
        tableFromGradesTabColumnStudentName.setCellValueFactory(new PropertyValueFactory<Grade, String>("studentName"));
        tableFromGradesTabColumnTeacherId.setCellValueFactory(new PropertyValueFactory<Grade, String>("teacherName"));
        tableFromGradesTabColumnValue.setCellValueFactory(new PropertyValueFactory<Grade, String>("value"));


        tableFromGradesTab.setItems(this.gradesModel);
        tableFromHomeworksTab.setItems(this.homeworksModel);


        /*
         *       the services didn't set yet so it will crash if I update models here
         *
         * */
        //updateModels();


        groupChoiceBoxModelFromStudentsTab.addAll("213", "223", "233", "214", "224", "234", "215", "225", "235");
        groupChoiceBoxFromStudentsTab.setItems(groupChoiceBoxModelFromStudentsTab);


//        valueChoiceBoxModelFromGradesTab.addAll(1,2,3,4,5,6,7,8,9,10);
//        valueChoiceBoxFromGradesTab.setItems(valueChoiceBoxModelFromGradesTab);

        latePublicationOfGradesComboBoxModelFromGradesTab.addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14");
        latePublicationOfGradesComboBoxFromGradesTab.setItems(latePublicationOfGradesComboBoxModelFromGradesTab);

        latePublicationOfGradesComboBoxFromGradesTab.setDisable(true);
        latePublicationOfGradesLabelFromGradesTab.setDisable(true);

        //to set the value 10 in the text field for value
        //        valueChoiceBoxFromGradesTab.setValue(10);


        startWeekChoiceBoxModelFromHomeworksTab.addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        startWeekChoiceBoxFromHomeworksTab.setItems(startWeekChoiceBoxModelFromHomeworksTab);
        deadlineWeekChoiceBoxModelFromHomeworksTab.addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        deadlineWeekChoiceBoxFromHomeworksTab.setItems(deadlineWeekChoiceBoxModelFromHomeworksTab);

        reportsChoiceBoxModelFromGradesTab.addAll(
                "1.Grade laboratory for each student",
                "2.Hardest homework",
                "3.Students who can join the exam",
                "4.The students who have show the homework in time"
        );

        reportChoiceBoxFromGradesTab.setItems(reportsChoiceBoxModelFromGradesTab);

//        studentIdComboBoxFromGradesTab.setEditable(true);
        studentIdComboBoxFromGradesTab.setItems(studentIdComboBoxModelFromGradesTab);

        pageTextFieldFromGradesTab.setText("0");
        pageTextFieldFromStudentsTab.setText("0");
        pageTextFieldFromTeachersTab.setText("0");
        pageTextFieldFromHomeworksTab.setText("0");

        pageTextFieldFromGradesTab.setEditable(false);
        pageTextFieldFromStudentsTab.setEditable(false);
        pageTextFieldFromTeachersTab.setEditable(false);
        pageTextFieldFromHomeworksTab.setEditable(false);


    }

    public void updateModels(){
        updateGradesModel();
        updateHomeworksModel();
        updateStudentsModel();

    }


    public void setServices(StudentService studentService, TeacherService teacherService ,
                            HomeworkService homeworkService, GradeService gradeService){
        this.studentService = studentService;
        this.studentService.setPageSize(2);
        this.teacherService = teacherService;
        this.teacherService.setPageSize(2);
        this.gradeService = gradeService;
        this.gradeService.setPageSize(2);
        this.homeworkService = homeworkService;
        this.homeworkService.setPageSize(2);
        this.gradeService.addObserver(gradeChangeEventObserver());
        this.studentService.addObserver(studentChangeEventObserver());
        this.homeworkService.addObserver(homeworkChangeEventObserver());
        this.teacherService.addObserver(teacherChangeEventObserver());
        initHomeworkIdComboBoxFromGradesTab();
        this.teacher = this.teacherService.findOne("1");
        initStudentIdComboBoxFromGradesTab();
        teacherIdLabelFromTeachersTab.setText(this.teacher.getId());
        nameLabelFromTeachersTab.setText(this.teacher.getName());
        firstNameLabelFromTeachersTab.setText(this.teacher.getFirstName());
        emailLabelFromTeachersTab.setText(this.teacher.getEmail());

    }

    private void initStudentIdComboBoxFromGradesTab() {

        ObservableList<String> newList= FXCollections.observableArrayList();
        for (Student student :
                this.studentService.findAll()) {
            newList.add(student.getName() + " " + student.getFirstName());
        }




        studentIdComboBoxModelFromGradesTab.remove(0,studentIdComboBoxModelFromGradesTab.size());
        studentIdComboBoxModelFromGradesTab.setAll(newList);

    }


    public void setUnivYear(UnivYearStructure univYearStructure) {
        this.univYearStructure = univYearStructure;
        startWeekChoiceBoxFromHomeworksTab.setValue(this.univYearStructure.getCurrentWeek());
        deadlineWeekChoiceBoxFromHomeworksTab.setValue(this.univYearStructure.getCurrentWeek());
    }

    @FXML
    void handleAboutMenuItem(ActionEvent event) {
        showReportsDialog("Version: 1.0.0\nAutor: Silviu Hadaruga\n", "About");
    }

    @FXML
    void handleCloseMenuItem(ActionEvent event) {
        this.stage.close();
    }

    public Observer<GradeChangeEvent> gradeChangeEventObserver(){
        return new Observer<GradeChangeEvent>() {
            @Override
            public void update(GradeChangeEvent gradeChangeEvent) {
//                initGradeModel();
                updateGradesModel();
            }
        };
    }

    public Observer<TeacherChangeEvent> teacherChangeEventObserver(){
        return new Observer<TeacherChangeEvent>() {
            @Override
            public void update(TeacherChangeEvent teacherChangeEvent) {
//                initTeacherModel();
            }
        };
    }

    public Observer<StudentChangeEvent> studentChangeEventObserver(){
        return new Observer<StudentChangeEvent>() {
            @Override
            public void update(StudentChangeEvent studentChangeEvent) {
//                initStudentModel();
                updateStudentsModel();
            }
        };
    }

    public Observer<HomeworkChangeEvent> homeworkChangeEventObserver (){
        return new Observer<HomeworkChangeEvent>() {
            @Override
            public void update(HomeworkChangeEvent homeworkChangeEvent) {
//                initHomeworkModel();
                updateHomeworksModel();
            }
        };
    }


    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    private boolean noOfPagesMaxGradesTab(){
        int noOfPage = Integer.parseInt(pageTextFieldFromGradesTab.getText());
        noOfPage++;
        return this.gradeService.getMessagesFromPage(noOfPage).size() == 0;
    }

    public void simplifiedHandlePageTextFieldFromGradesTab(){
        if(!pageTextFieldFromGradesTab.getText().equals("")){
            int noOfPage = Integer.parseInt(pageTextFieldFromGradesTab.getText());
            this.gradesModel.setAll(this.gradeService.getMessagesOnPage(noOfPage));
        }
    }

    @FXML
    public void handleNextPageButtonFromGradesTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromGradesTab.getText());
        nr++;
        if(!noOfPagesMaxGradesTab()) {
            pageTextFieldFromGradesTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromGradesTab();
        }
    }

    @FXML
    public void handlePageCheckBoxFromGradesTab(ActionEvent keyEvent) {
        if(pageCheckBoxFromGradesTab.isSelected())
            pageTextFieldFromGradesTab.setEditable(true);
        else{
            pageTextFieldFromGradesTab.setEditable(false);
            pageTextFieldFromGradesTab.setText(String.valueOf(this.gradeService.getPage()));
        }
    }

    @FXML
    public void handlePageTextFieldFromGradesTab(KeyEvent keyEvent) {
        simplifiedHandlePageTextFieldFromGradesTab();

    }

    @FXML
    public void handlePreviousPageButtonFromGradesTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromGradesTab.getText());
        if(nr  > 0){
            nr--;
            pageTextFieldFromGradesTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromGradesTab();
        }
    }


    private boolean noOfPagesMaxStudentsTab(){
        int noOfPage = Integer.parseInt(pageTextFieldFromStudentsTab.getText());
        noOfPage++;
        return this.studentService.getMessagesFromPage(noOfPage).size() == 0;
    }

    private void simplifiedHandlePageTextFieldFromStudentsTab(){
        if(!pageTextFieldFromStudentsTab.getText().equals("")){
            int noOfPage = Integer.parseInt(pageTextFieldFromStudentsTab.getText());
            this.studentsModel.setAll(this.studentService.getMessagesOnPage(noOfPage));
        }

    }

    @FXML
    public void handleNextPageButtonFromStudentsTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromStudentsTab.getText());
        nr++;
        if(!noOfPagesMaxStudentsTab()) {
            pageTextFieldFromStudentsTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromStudentsTab();
        }
    }

    @FXML
    public void handlePageTextFieldFromStudentsTab(KeyEvent keyEvent) {
        simplifiedHandlePageTextFieldFromStudentsTab();
    }

    @FXML
    public void handlePageCheckBoxFromStudentsTab(ActionEvent actionEvent) {
        if(pageCheckBoxFromStudentsTab.isSelected())
            pageTextFieldFromStudentsTab.setEditable(true);
        else
            pageTextFieldFromStudentsTab.setEditable(false);
    }

    @FXML
    public void handlePreviousPageButtonFromStudentsTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromStudentsTab.getText());
        if(nr  > 0){
            nr--;
            pageTextFieldFromStudentsTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromStudentsTab();
        }
    }

    private boolean noOfPagesMaxHomeworksTab(){
        int noOfPage = Integer.parseInt(pageTextFieldFromHomeworksTab.getText());
        noOfPage++;
        return this.homeworkService.getMessagesFromPage(noOfPage).size() == 0;
    }

    public void simplifiedHandlePageTextFieldFromHomeworksTab(){
        if(!pageTextFieldFromHomeworksTab.getText().equals("")){
            int noOfPage = Integer.parseInt(pageTextFieldFromHomeworksTab.getText());
            this.homeworksModel.setAll(this.homeworkService.getMessagesOnPage(noOfPage));
        }
    }

    @FXML
    public void handleNextPageButtonFromHomeworksTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromHomeworksTab.getText());
        nr++;
        if(!noOfPagesMaxHomeworksTab()) {
            pageTextFieldFromHomeworksTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromHomeworksTab();
        }
    }

    @FXML
    public void handlePageTextFieldFromHomeworksTab(KeyEvent keyEvent) {
        simplifiedHandlePageTextFieldFromHomeworksTab();
    }

    @FXML
    public void handlePageCheckBoxFromHomeworksTab(ActionEvent actionEvent) {
        if (pageCheckBoxFromHomeworksTab.isSelected())
            pageTextFieldFromHomeworksTab.setEditable(true);
        else
            pageTextFieldFromHomeworksTab.setEditable(false);
    }

    @FXML
    public void handlePreviousPageButtonFromHomeworksTab(ActionEvent actionEvent) {
        int nr = Integer.parseInt(pageTextFieldFromHomeworksTab.getText());
        if(nr  > 0){
            nr--;
            pageTextFieldFromHomeworksTab.setText(
                    String.valueOf(nr)
            );
            simplifiedHandlePageTextFieldFromHomeworksTab();
        }
    }

    private boolean noOfPagesMaxTeachersTab(){
        int noOfPage = Integer.parseInt(pageTextFieldFromTeachersTab.getText());
        noOfPage++;
        return this.teacherService.getMessagesFromPage(noOfPage).size() == 0;
    }

    public void simplifiedHandlePageTextFieldFromTeachersTab(){
        if(!pageTextFieldFromTeachersTab.getText().equals("")){
            int noOfPage = Integer.parseInt(pageTextFieldFromTeachersTab.getText());
            this.teachersModel.setAll(this.teacherService.getMessagesOnPage(noOfPage));
        }
    }
    @FXML
    public void handleTeacherIdFilterTextFieldFromGradesTab(KeyEvent keyEvent) {
        handleStudentIdFilterTextFieldFromGradesTab(keyEvent);
    }
}
