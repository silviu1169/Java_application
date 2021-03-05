package domain;

public class    Student extends Entity<String> {
    private String name;
    private String firstName;
    private String group;
    private String email;
    private String teacherTrainingLab;

    public Student (String id, String name, String firstName, String group, String email,
                    String teacherTrainingLab){
        super(id);
        this.name = name;
        this.firstName = firstName;
        this.group = group;
        this.email = email;
        this.teacherTrainingLab = teacherTrainingLab;
    }

    public String getID() {
        return super.getId();
    }

    public void setID(String ID) {
        super.setId(ID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacherTrainingLab() {
        return teacherTrainingLab;
    }

    public void setTeacherTrainingLab(String teacherTrainingLab) {
        this.teacherTrainingLab = teacherTrainingLab;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", group='" + group + '\'' +
                ", email='" + email + '\'' +
                ", teacherTrainigLab='" + teacherTrainingLab + '\'' +
                '}';
    }
}
