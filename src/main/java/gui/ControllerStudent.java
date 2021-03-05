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
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Observer;
import service.GradeService;
import service.HomeworkService;
import service.StudentService;
import service.TeacherService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerStudent  {
    @FXML
    private Label firstNameLabelFromStudentsTab;
    @FXML
    private Label emailLabelFromStudentsTab;
    @FXML
    private Label nameLabelFromStudentsTab;
    @FXML
    private Label idLabelFromStudentsTab;

    @FXML
    private Label groupLabelFromStudentsTab;

    @FXML
    private Label teacherTrainigLabLabelFromStudentsTab;
    @FXML
    private Label teacherIdLabelFromStudentsTab;
    @FXML
    private TextField studentNameTextFieldFromGradesTab;

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
    public CheckBox pageCheckBoxFromStudentsTab;

    @FXML
    public TextField pageTextFieldFromStudentsTab;


    @FXML
    public Button previousPageButtonFromHomeworksTab;

    @FXML
    public CheckBox pageCheckBoxFromHomeworksTab;

    @FXML
    public TextField pageTextFieldFromHomeworksTab;

    @FXML
    public Button nextPageButtonFromHomeworksTab;

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
    private TextField studentIdTextFieldFromGradesTab;

    @FXML
    private TextField homeworkIdTextFieldFromGradesTab;



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
    private ChoiceBox<Integer> valueChoiceBoxFromGradesTab;

    @FXML
    private TextField teacherIdTextFieldFromGradesTab;


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
    private Button clearFieldsButtonFromGradesTab;

    @FXML
    private CheckBox latePublicationOfGradesCheckBoxFromGradesTab;
//
//  The end of GradesTab's attributes ---------------------------------------------------------------


//################################################################################################################


    //
//  The begin of StudentsTab's attributes -----------------------------------------------------------


//
// The end of StudentsTab's attributes ---------------------------------------------------------


//################################################################################################################


//
// The begin of HomeworksTab's attributes ------------------------------------------------------

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
    public void clearFilterFieldsFromGradesTab(){
        studentIdFilterTextFieldFromGradesTab.setText("");
        homeworkIdFilterTextFieldFromGradesTab.setText("");
        valueFilterTextFieldFromGradesTab.setText("");
        teacherIdFilterTextFieldFromGradesTab.setText("");
        this.updateGradesModel();
    }

    @FXML
    public void clearFieldsFromGradesTab(){

        studentIdTextFieldFromGradesTab.setText("");

        teacherIdTextFieldFromGradesTab.setText("");
        valueTextFieldFromGradesTab.setText("");
        feedbackTextAreaFromGradesTab.setText("");
        studentNameTextFieldFromGradesTab.setText("");

    }

    @FXML
    public void updateGradesModel() {
        List<Grade> gradeList = new ArrayList<>();
//        gradeService.findAll().forEach(gradeList::add);
        this.gradeService.getMessagesOnPage(this.gradeService.getPage()).forEach(x->{
            if (x.getStudentId().equals(this.student.getID()))
                gradeList.add(x);
        });

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
            valueTextFieldFromGradesTab.setText(String.valueOf(grade.getValue()));
            studentIdTextFieldFromGradesTab.setText(student.getName() + " " + student.getFirstName());
            Homework homework = this.homeworkService.findOne(grade.getHomeworkId());
            homeworkIdTextFieldFromGradesTab.setText(homework.getDescription());
            Teacher teacher = this.teacherService.findOne(grade.getTeacherId());
            teacherIdTextFieldFromGradesTab.setText(teacher.getName() + " " + teacher.getFirstName() );
            feedbackTextAreaFromGradesTab.setText(grade.getFeedback());
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

//
// The end of GradesTab's methods ---------------------------------------------------------


//#########################################################################################


    //
// The begin of StudentsTab's methods ------------------------------------------------------

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


//
// The end of TeachersTab's methods ------------------------------------------------------



    @FXML
    void initialize() {
//        this.studentService.addObserver(this); --not here, the services didn't set yet


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



//        valueChoiceBoxModelFromGradesTab.addAll(1,2,3,4,5,6,7,8,9,10);
//        valueChoiceBoxFromGradesTab.setItems(valueChoiceBoxModelFromGradesTab);


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

    }


    public void setServices(StudentService studentService, TeacherService teacherService ,
                            HomeworkService homeworkService, GradeService gradeService) {
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
        this.student = this.studentService.findOne("7");
        this.idLabelFromStudentsTab.setText(student.getID());
        this.nameLabelFromStudentsTab.setText(student.getName());
        this.firstNameLabelFromStudentsTab.setText(student.getFirstName());
        this.emailLabelFromStudentsTab.setText(student.getEmail());
        this.groupLabelFromStudentsTab.setText(student.getGroup());
        this.teacherTrainigLabLabelFromStudentsTab.setText(student.getTeacherTrainingLab());

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
//                updateStudentsModel();
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
    @FXML
    public void handleTeacherIdFilterTextFieldFromGradesTab(KeyEvent keyEvent) {
        handleStudentIdFilterTextFieldFromGradesTab(keyEvent);
    }
}
