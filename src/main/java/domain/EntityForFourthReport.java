package domain;

public class EntityForFourthReport extends Entity<String> {
    private String studentFullName;
    private boolean showedHomeworksInTime;


    public EntityForFourthReport(String studentId, String studentFullName) {
        super(studentId);
        this.studentFullName = studentFullName;
        this.showedHomeworksInTime = true;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public boolean getShowedHomeworksInTime() {
        return showedHomeworksInTime;
    }

    public void setShowedHomeworksInTime(boolean showedHomeworksInTime) {
        this.showedHomeworksInTime = showedHomeworksInTime;
    }

}
