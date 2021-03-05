package events;

import domain.Grade;

public class GradeChangeEvent implements Event{
    private ChangeEventType type;
    private Grade Grade,oldGrade;

    public GradeChangeEvent(ChangeEventType type, Grade Grade) {
        this.type = type;
        this.Grade = Grade;
    }

    public GradeChangeEvent(ChangeEventType type, Grade Grade, Grade oldGrade) {
        this.type = type;
        this.Grade = Grade;
        this.oldGrade = oldGrade;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Grade getGrade() {
        return Grade;
    }

    public Grade getOldGrade() {
        return oldGrade;
    }
}
