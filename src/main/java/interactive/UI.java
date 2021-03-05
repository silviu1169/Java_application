package interactive;

import domain.*;
import service.GradeService;
import service.HomeworkService;
import service.StudentService;
import service.TeacherService;
import validator.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {

    private StudentService studentService;
    private HomeworkService homeworkService;
    private GradeService gradeService;
    private TeacherService teacherService;
    private UnivYearStructure univYearStructure;

    public UI(StudentService studentService, HomeworkService homeworkService,GradeService gradeService ,TeacherService teacherService, UnivYearStructure univYearStructure){
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.gradeService = gradeService;
        this.teacherService = teacherService;
        this.univYearStructure = univYearStructure;
    }

    private String readString(String message){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        try {
            String input = reader.readLine();
            while (input.length() == 0){
                System.out.println("The input cannot be blank!");
                System.out.print(message);
                input = reader.readLine();
            }

            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void run(){
        while (true){
            System.out.println( "0 - to exit\n" +
                                "1 - to add a Student\n" +
                                "2 - to find a Student (by ID)\n" +
                                "3 - to update a Student \n" +
                                "4 - to get all Students\n" +
                                "5 - to add a Homework\n" +
                                "6 - to find a Homework(by ID)\n" +
                                "7 - to update a Homework\n" +
                                "8 - to get all Homeworks\n" +
                                "9 - to delete a Student(by ID)\n" +
                                "10 - to delete a Homework (by ID)\n" +
                                "11 - to add a Teacher\n" +
                                "12 - to find a Teacher (by ID)\n" +
                                "13 - to update a Teacher \n" +
                                "14 - to get all Teachers\n" +
                                "15 - to delete a Teacher\n" +
                                "16 - to add a Grade\n" +
                                "17 - to find a Grade (by ID)\n" +
                                "18 - to update a Grade \n" +
                                "19 - to get all Grades\n" +
                                "20 - to delete a Grade\n" +
                                "21 - to get all the Students from a group\n" +
                                "22 - to get all the Students that have a grade at a given Homework\n" +
                                "23 - to get all the Students that have a grade at a  given Homework at a given Teacher\n" +
                                "24 - to get all the Grades at a Homework from a given week\n"
            );
            int choice = 0;
            choice = Integer.parseInt(
                    readString("Choice:")
            );

            switch (choice){

                case 0: //exit
                    return;

                case 1:{ //add a Student
                    try {
                        this.studentService.save(
                                readString("Name:"),
                                readString("FirstName:"),
                                readString("Group:"),
                                readString("Email:"),
                                readString("TeacherTrainingLab:")
                        );
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: { //find a Student(by ID)
                    System.out.println(
                            this.studentService.findOne(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 3: {//update a Student
                    this.studentService.update(
                            readString("ID:"),
                            readString("Name:"),
                            readString("FirstName"),
                            readString("Group"),
                            readString("Email"),
                            readString("TeacherTrainingLab:")
                    );
                    break;
                }

                case 4:{ //get all Students
                    for(Student student:
                        this.studentService.findAll())
                                System.out.println(student);
                    break;
                }

                case 5:{ //add a Tema
//                    try {
////                        this.homeworkService.save(
////                                readString("Description:"),
////                                readString("DeadlineWeek:")
////                        );
//                    } catch (ValidationException e) {
//                        e.printStackTrace();
//                    }
                    break;
                }

                case 6:{ //find a Tema by ID
                    System.out.println(
                            this.homeworkService.findOne(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 7:{//update a Tema
//                    try {
//                        this.homeworkService.update(
//                                readString("ID:"),
//                                readString("Description:"),
//                                readString("DeadlineWeek:")
//                        );
//                    } catch (ValidationException e) {
//                        e.printStackTrace();
//                    }
                    break;
                }

                case 8:{ //get all Teme
                    for(Homework homework :
                        this.homeworkService.findAll())
                                System.out.println(homework);
                    break;
                }

                case 9:{ //delete a Student
                    System.out.println("It was deleted the Student\n" +
                            this.studentService.delete(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 10:{ //delete a Tema
                    System.out.println("It was deleted the Homework\n" +
                            this.homeworkService.delete(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 11:{//add a Teacher
                    try {
                        this.teacherService.save(

                                readString("Name:"),
                                readString("FirstName:"),
                                readString("Email:")
                        );
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 12:{//find a Teacher(by ID)
                    System.out.println(
                            this.teacherService.findOne(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 13:{//update a Teacher
                    this.teacherService.update(
                            readString("ID:"),
                            readString("Name:"),
                            readString("FirstName:"),
                            readString("Email:")
                    );
                    break;
                }

                case 14:{//get all Teachers
                    for (Teacher teacher :
                            this.teacherService.findAll()) {
                        System.out.println(teacher);
                    }
                    break;
                }

                case 15:{//delete a Teacher
                    System.out.println("It was deleted the Teacher\n" +
                        this.teacherService.delete(
                                readString("ID:")
                        )
                    );
                    break;
                }

                case 16: {//add a Grade

                    String idStudent = readString("ID_Student:");
                    String idHomework = readString("ID_Homework");

                    Homework homework  =this.homeworkService.findOne(idHomework);
                    Student student = this.studentService.findOne(idStudent);
                    Grade grade = this.gradeService.findOne(idStudent + " " + idHomework);

                    int currentWeek = this.univYearStructure.getCurrentWeek();

                    if (this.gradeService.isTeacherLate(homework)){
                        String hasStudentMotivation = readString("Has the Student a motivation? (yes/no)");
                        if (hasStudentMotivation.toLowerCase().equals("yes")){
                            currentWeek -= 1;
                        }
                        String lateTeacher = readString("You are late with the publication! ( The student showed the assignment in another week than the current week) yes/no :");
                        if (lateTeacher.toLowerCase().equals("yes")) {
                            String weeks = readString("In what week, the student show the assignment?");
                            currentWeek = Integer.parseInt(weeks);
                        }
                    }

                    int maxGrade = 10;
                    try {
                            maxGrade = this.homeworkService.maxGrade(idHomework, currentWeek);
                    }
                    catch(NullPointerException e ){
                        System.out.println("The homework doesn't exist! Add the homework first and try again after that!");
                        break;
                    }

                    String value = readString("Max grade you can assign is:" +
                            String.valueOf(maxGrade) +
                            ". Grade:"
                    );
                    String teacher = readString("Teacher:");
                    String feedback = readString("Feedback:");

//                    try {
//                        this.gradeService.save(
//                                idStudent,
//                                idHomework,
//                                value,
//                                teacher
//                        );
//                    } catch (ValidationException e) {
//                        e.printStackTrace();
//                    }

                    this.gradeService.saveToTxtFile(
                            student.getName()+student.getFirstName(),
                            idHomework,
                            value,
                            currentWeek,
                            homework.getDeadlineWeek(),
                            feedback
                    );
                    break;
                }

                case 17:{//find a Grade(by ID)
                    System.out.println(this.gradeService.findOne(
                            readString("ID:")
                    ));
                    break;
                }

                case 18:{//update a Grade
                    this.gradeService.update(
                            readString("ID_Student:"),
                            readString("ID_Homework"),
                            readString("Value:"),
                            readString("Teacher:"),
                            readString("feedback::")
                    );
                    break;
                }
                case 19:{//get all Grade
                    for (Grade grade :
                            this.gradeService.findAll()) {
                        System.out.println(grade);
                    }
                    break;
                }

                case 20:{//delete a Grade
                    System.out.println("It was deleted the Grade\n" +
                            this.gradeService.delete(
                                    readString("ID:")
                            )
                    );
                    break;
                }

                case 21:{//print all Students from a group
                    System.out.println(
                        this.studentService.allStudentsFromAGroup(
                                readString("Group:")
                        )
                    );
                    break;
                }

                case 22:{//to show all the Students that have a grade at a Homework
                    for (Grade grade:
                         this.gradeService.allTheStudentsWithGradeAtAHomework(
                                 readString("ID Homework:")
                         )) {
                        System.out.println(
                                this.studentService.findOne(
                                        grade.getId().split(" ")[0] // idGrade = idStudent + " " + idHomework
                                )
                        );
                    }
                    break;
                }

                case 23:{//to show all the Students that have a grade at a Homework at a Teacher
                    for (Grade grade :
                            this.gradeService.allTheStudentsWithAGradeAtAHomeworkAtATeacher(
                                    readString("ID Homework:"),
                                    readString("Teacher:")
                            )) {
                        System.out.println(
                                this.studentService.findOne(
                                        grade.getId().split(" ")[0]
                                )
                        );
                    }
                    break;
                }
                case 24:{//to get all the Grades at a Homework from a given week"
                    for (Grade grade :
                            this.gradeService.allTheGradesAtAHomeworkFromAGivenWeek(
                                    readString("ID Homework:"),
                                    readString("Week:")
                            )) {
                    }
                    break;
                }


                default:
                    throw new IllegalStateException("Wrong choice mate!");
            }
        }
    }
}
