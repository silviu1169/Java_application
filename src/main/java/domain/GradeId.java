package domain;

public class GradeId {
    private String idHomework;
    private String idStudent;

    public GradeId(String idStudent, String idHomework){
        this.idStudent = idStudent;
        this.idHomework = idHomework;
    }

    public String getIdHomework() {
        return idHomework;
    }

    public void setIdHomework(String idHomework) {
        this.idHomework = idHomework;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
}
