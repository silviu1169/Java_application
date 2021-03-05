package domain;

public class EntityForThirdReport extends Entity<String> {
    private String studentName;
    private int sumOfGrades;
    private int noOfGrades;

    public EntityForThirdReport(String studentId, String studentName) {
        super(studentId);
        this.studentName = studentName;
        this.sumOfGrades = 0;
        this.noOfGrades = 0;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSumOfGrades() {
        return sumOfGrades;
    }

    public void setSumOfGrades(int sumOfGrades) {
        this.sumOfGrades = sumOfGrades;
    }

    public int getNoOfGrades() {
        return noOfGrades;
    }

    public void setNoOfGrades(int noOfGrades) {
        this.noOfGrades = noOfGrades;
    }
}
