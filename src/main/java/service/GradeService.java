package service;

import config.ApplicationContext;
import domain.*;
import events.ChangeEventType;
import events.GradeChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PageableImplementation;
import repository.paging.PagingRepository;
import validator.GradeValidator;
import validator.ValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GradeService implements Observable<GradeChangeEvent> {
    private PagingRepository<String, Grade> repository;
    private GradeValidator validator;
    private UnivYearStructure univYearStructure;
    private StudentService studentService;
    private HomeworkService homeworkService;
    private TeacherService teacherService;


    public GradeService(PagingRepository<String, Grade> repository, GradeValidator validator, UnivYearStructure univYearStructure,
                        StudentService studentService, HomeworkService homeworkService, TeacherService teacherService) {
        this.repository = repository;
        this.validator = validator;
        this.univYearStructure = univYearStructure;
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.teacherService = teacherService;
    }

    public boolean isTeacherLate(Homework homework){
        if (this.univYearStructure.getCurrentWeek() > homework.getDeadlineWeek() ){
            return true;
        }

        return false;
    }

    public List<Grade> allTheStudentsWithGradeAtAHomework(String idHomework) {
        List<Grade> gradeList = new ArrayList<Grade>();
        for (Grade grade:
            this.repository.findAll()) {
            gradeList.add(grade);
        }
        return gradeList
                .stream()
                .filter(x->x.getHomeworkId().equals(idHomework))
                .collect(Collectors.toList());
    }

    public List<Grade> allTheStudentsWithAGradeAtAHomeworkAtATeacher(String idHomework, String teacher){
        List<Grade> gradeList = new ArrayList<Grade>();
        for (Grade grade:
                this.repository.findAll()) {
            gradeList.add(grade);
        }

        return gradeList
                .stream()
                .filter(x->x.getHomeworkId().equals(idHomework) && x.getTeacherId().equals(teacher))
                .collect(Collectors.toList());
    }

    public List<Grade> allTheGradesAtAHomeworkFromAGivenWeek(String idHomework, String week){
        List<Grade> gradeList =(List<Grade>) this.repository.findAll();
        Predicate<Grade> gradePredicate = new Predicate<Grade>() {
            @Override
            public boolean test(Grade grade) {
                if (grade.getHomeworkId().equals(idHomework) && (String.valueOf(univYearStructure.getCurrentWeekFromLocalDateTime(grade.getLocalDateTime())).equals(week)))
                    return true;
                return false;
            }
        };
        return gradeList
                .stream()
                .filter(x-> gradePredicate.test(x))
                .collect(Collectors.toList());
    }

    public void save(String studentId, String homeworkId,String value,
                     String teacherId, String feedback, String homeworkDescription,
                     String studentName, String teacherName
    ) throws ValidationException {

        Grade grade = new Grade (
                studentId,
                homeworkId,
                Integer.parseInt(value),
                this.univYearStructure.getCurrentDateTime(),
                teacherId
        );
        grade.setFeedback(feedback);
        grade.setHomeworkDescription(homeworkDescription);
        grade.setStudentName(studentName);
        grade.setTeacherName(teacherName);
        this.validator.validate(grade);
        this.repository.save(grade);

        Student student = this.studentService.findOne(studentId);

        Homework homework = this.homeworkService.findOne(homeworkId);

        Teacher teacher = this.teacherService.findOne(teacherId);

        List<String> studentsEmail = new ArrayList<String>();
        studentsEmail.add(student.getEmail());

        this.saveToTxtFile(student.getName() + "_" + student.getFirstName(),
                homeworkId,
                String.valueOf(grade.getValue()),
                this.univYearStructure.getCurrentWeekFromLocalDateTime(LocalDateTime.now()),
                homework.getDeadlineWeek(),
                grade.getFeedback()
        );

        SendEmail sendEmail = new SendEmail(
                "The value for  " + homeworkDescription + " is " + value ,
                "Grade given for the  " + homeworkDescription,
                teacher.getEmail(),
                studentsEmail
        );
        sendEmail.send();
    }

    public void update(String studentId, String homeworkId,String value, String teacherId, String feedback){
        Grade oldGrade = this.repository.findOne(studentId + " " + homeworkId);
        if (oldGrade != null){
            Grade newGrade = oldGrade;
            newGrade.setValue(Integer.parseInt(value));
            newGrade.setTeacherId(teacherId);
            newGrade.setLocalDateTime(this.univYearStructure.getCurrentDateTime());
            newGrade.setFeedback(feedback);
            this.repository.update(oldGrade, newGrade);
            notifyObservers(new GradeChangeEvent(ChangeEventType.UPDATE, newGrade));
        }

    }

    public void deleteAllFromOneId(String studentId){
        for (Grade grade :
                this.findAll()) {
            Grade gradeDeleted = null;
            if (grade.getStudentId().equals(studentId)) {
                gradeDeleted = this.repository.delete(grade.getStudentId() + " " + grade.getHomeworkId());
                notifyObservers(new GradeChangeEvent(ChangeEventType.DELETE, gradeDeleted));
            }
        }
    }



    public Grade delete (String id){
        Grade grade = this.repository.delete(id);
        notifyObservers(new GradeChangeEvent(ChangeEventType.DELETE, grade));
        return grade;
    }

    public Grade findOne(String id){//id = studentId + " " + homeworkId
        return this.repository.findOne(id);
    }

    public List<Grade> findALlByStudentId(String studentId, List<Grade> gradeList){
        return gradeList.stream()
                        .filter(x->x.getStudentName().contains(studentId))
                        .collect(Collectors.toList());
    }

    public List<Grade> findAllByHomeworkId(String homeworkId, List<Grade> gradeList){
        return gradeList.stream()
                        .filter(x->x.getHomeworkDescription().contains(homeworkId))
                        .collect(Collectors.toList());
    }

    public List<Grade> findAllByTeacherId(String teacherId, List<Grade> gradeList){
        return gradeList.stream()
                .filter(x->x.getTeacherName().contains(teacherId))
                .collect(Collectors.toList());
    }

    public List<Grade> findAllByValue(int value, List<Grade> gradeList){
        return gradeList.stream()
                .filter(x->x.getValue() == value)
                .collect(Collectors.toList());
    }

    public Iterable<Grade> findAll(){
        return this.repository.findAll();
    }

    public void saveToTxtFile(String studentName, String idHomework, String value, int theWeeKWhenTheStudentShowTheAssignment, int deadlineWeek, String feedback) {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(
                ApplicationContext.getPROPERTIES().getProperty("database.catalog.gradesTXT") + studentName + ".txt"
        ))){
            String line = "";
            line = "Homework: " + idHomework +
                    "Grade: " + value +
                    "The week when the student show the homework: " + theWeeKWhenTheStudentShowTheAssignment +
                    "Deadline:" + deadlineWeek +
                    "Feedback: " + feedback;
            printWriter.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save(Grade grade){
        this.repository.save(grade);
        this.notifyObservers(new GradeChangeEvent(ChangeEventType.ADD, grade));
    }

    private List<Observer<GradeChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<GradeChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<GradeChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(GradeChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }

    public Map<Student, Double> mapForFirstAndThirdReport(){
        List<Grade> gradesList = new ArrayList<>();
        Map<Student, Double> averageGrades = new HashMap<>();

        this.findAll().forEach(gradesList::add);
        this.studentService.findAll().forEach(x->averageGrades.put(x,(double)0));

        gradesList.stream().forEach(x->{
            Student student = this.studentService.findOne(x.getStudentId());
            Homework homework = this.homeworkService.findOne(x.getHomeworkId());
            averageGrades.replace(student, averageGrades.get(student) + (double)(x.getValue()*(homework.getDeadlineWeek()-homework.getStartWeek())));
        });

        double weight = 0;

        for (Homework homework :
                this.homeworkService.findAll()) {
            weight += homework.getDeadlineWeek() - homework.getStartWeek();
        }

        double finalWeight = weight;
        this.studentService.findAll().forEach(x->averageGrades.replace(x, averageGrades.get(x)/ finalWeight));

        return averageGrades;

    }

    public Map<Homework, Double> mapForSecondReport(){
        List<Grade> gradesList = new ArrayList<>();
        Map<Homework, Double> averageHomeworkGrades = new HashMap<>();

        this.findAll().forEach(gradesList::add);
        this.homeworkService.findAll().forEach(homework -> {
            averageHomeworkGrades.put(homework, (double)0);
        });

        List<Student> studentList = new ArrayList<>();
        this.studentService.findAll().forEach(studentList::add);

        gradesList.stream().forEach(x->{
            Homework homework = this.homeworkService.findOne(x.getHomeworkId());
            averageHomeworkGrades.replace(homework, averageHomeworkGrades.get(homework) + (double)(x.getValue()));

        });

        for (Homework homework:
                this.homeworkService.findAll()) {
            averageHomeworkGrades.put(homework,averageHomeworkGrades.get(homework) / studentList.size());

        }
        return averageHomeworkGrades;
    }




    public Map<Student, Integer> mapForFourthReport(){
        List<Grade> gradesList = new ArrayList<>();
        List<Homework> homeworksList = new ArrayList<>();
        Map<Student, Integer> noOfHomeworksShowed = new HashMap<>();

        this.findAll().forEach(gradesList::add);
        this.homeworkService.findAll().forEach(homeworksList::add);
        this.studentService.findAll().forEach(x->noOfHomeworksShowed.put(x,0));

        for (Grade grade :
                gradesList) {
            if (
                    this.homeworkService.findOne(grade.getHomeworkId()).getDeadlineWeek()
                            >=
                            this.univYearStructure.getCurrentWeekFromLocalDateTime(grade.getLocalDateTime())
            )
                noOfHomeworksShowed.replace(
                        this.studentService.findOne(grade.getStudentId()),
                        noOfHomeworksShowed.get(this.studentService.findOne(grade.getStudentId())) + 1
                );
        }
        return noOfHomeworksShowed;

    }

    public String handleFirstReport(){
        //"1.Grade laboratory for each student",

        Map<Student, Double> averageGrades = this.mapForFirstAndThirdReport();

        String information = "";

        for (Student student :
                this.studentService.findAll()){
            information += student.getName().concat( " " + student.getFirstName()) + " has the average grade laboratory "
                    +
                    String.valueOf(averageGrades.get(student))
                    + "\n";
        }
        return information;
    }

    public String handleSecondReport(){
        //"2.Hardest homework, has the average grade the lowest",

        Map<Homework, Double> averageHomeworkGrades = this.mapForSecondReport();


        Homework homeworkWithLowestGrade  = null;
        double averageGrade = 10;

        for (Homework homework:
                this.homeworkService.findAll()) {

            if(averageHomeworkGrades.get(homework) < averageGrade) {
                averageGrade = averageHomeworkGrades.get(homework) ;
                homeworkWithLowestGrade = homework;
            }
        }
        String information = "The homework \"" + homeworkWithLowestGrade.getDescription() + "\" has the lowest grade - > " + String.valueOf(averageGrade);
        return information;
    }

    public String handleThirdReport(){
        //"3.Students who can join the exam", has the average grade > 5

        Map<Student, Double> averageGrades = this.mapForFirstAndThirdReport();

        String information = "";

        for (Student student :
                this.studentService.findAll()){
            if(averageGrades.get(student) >= 5)
                information += student.getName().concat( " " + student.getFirstName()) + " can join the exam\n";
            else
                information += student.getName().concat( " " + student.getFirstName()) + " can't join the exam\n";
        }
        return information;
    }


    public String handleFourthReport(){
        //"4.The students who have show the homework in time"
        List<Grade> gradesList = new ArrayList<>();
        List<Homework> homeworksList = new ArrayList<>();
        Map<Student, Integer> noOfHomeworksShowed =this.mapForFourthReport();

        this.findAll().forEach(gradesList::add);
        this.homeworkService.findAll().forEach(homeworksList::add);

        String information = "";
        for (Student student :
                this.studentService.findAll()) {
            if (noOfHomeworksShowed.get(student) == homeworksList.size()) {
                information += student.getName().concat("" + student.getFirstName()) + " showed all the homeworks in time\n";
            }
        }

        if(information.length() == 0)
            information += "There is no student with all the homeworks showed.\n";

        return information;
    }

    private int page = 0;
    private int size = 1;

    private Pageable pageable;

    public void setPageSize(int size) {
        this.size = size;
    }

    public int getPage(){return this.page;}

    public List<Grade> getNextMessages() {
        this.page++;
        return getMessagesOnPage(this.page);
    }

    public List<Grade> getMessagesOnPage(int page) {
        this.page=page;
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Grade> gradePage = repository.findAll(pageable);
        return gradePage.getContent().collect(Collectors.toList());
    }

    public List<Grade> getMessagesFromPage(int page) {
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Grade> gradePage = repository.findAll(pageable);
        return gradePage.getContent().collect(Collectors.toList());
    }
}
