package domain;

import java.time.LocalDateTime;

public class Grade extends Entity<String> {
    private  String feedback;
    //    Nota: ID, data, profesor, unde ID=ID_Student+ID_Tema (un student la o anumita tema are o
//            singura nota)
    private int value;
    private LocalDateTime  localDateTime;
    private String teacherId;
    private String studentId;
    private String homeworkId;
    private String studentName;
    private String homeworkDescription;
    private String teacherName;


    public Grade(String studentId, String homeworkId, int value, LocalDateTime localDateTime, String teacherId) {
        super(studentId + " " + homeworkId);
        this.value = value;
        this.localDateTime = localDateTime;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.studentName = "";
        this.feedback = "";
        this.homeworkDescription = "";
        this.teacherName = "";
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getStudentId(){
        return this.studentId;
    }

    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
	
    public String getHomeworkId(){
        return this.homeworkId;
    }

    public void setHomeworkId(String homeworkId){
        this.homeworkId = homeworkId;
    }
	

    public int getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Grade{" +
                " value=" + this.value +
                ", id='" + this.studentId + " " + this.homeworkId + '\'' +
                ", localDateTime=" + this.localDateTime +
                ", teacher='" + this.teacherId + '\'' +
                '}';

    }

    public String getHomeworkDescription() {
        return homeworkDescription;
    }

    public void setHomeworkDescription(String homeworkDescription) {
        this.homeworkDescription = homeworkDescription;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
