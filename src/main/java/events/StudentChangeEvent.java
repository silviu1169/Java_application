package events;

import domain.Student;

public class StudentChangeEvent implements Event{
    private ChangeEventType type;
    private Student Student,oldStudent;

    public StudentChangeEvent(ChangeEventType type, Student Student) {
        this.type = type;
        this.Student = Student;
    }

    public StudentChangeEvent(ChangeEventType type, Student Student, Student oldStudent) {
        this.type = type;
        this.Student = Student;
        this.oldStudent = oldStudent;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Student getStudent() {
        return Student;
    }

    public Student getOldStudent() {
        return oldStudent;
    }
}
