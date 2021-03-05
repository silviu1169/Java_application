package events;

import domain.Teacher;

public class TeacherChangeEvent implements Event{
    private ChangeEventType type;
    private Teacher Teacher,oldTeacher;

    public TeacherChangeEvent(ChangeEventType type, Teacher Teacher) {
        this.type = type;
        this.Teacher = Teacher;
    }

    public TeacherChangeEvent(ChangeEventType type, Teacher Teacher, Teacher oldTeacher) {
        this.type = type;
        this.Teacher = Teacher;
        this.oldTeacher = oldTeacher;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Teacher getTeacher() {
        return Teacher;
    }

    public Teacher getOldTeacher() {
        return oldTeacher;
    }
}
