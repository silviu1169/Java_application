package events;

import domain.Homework;

public class HomeworkChangeEvent implements Event{
    private ChangeEventType type;
    private Homework Homework,oldHomework;

    public HomeworkChangeEvent(ChangeEventType type, Homework Homework) {
        this.type = type;
        this.Homework = Homework;
    }

    public HomeworkChangeEvent(ChangeEventType type, Homework Homework, Homework oldHomework) {
        this.type = type;
        this.Homework = Homework;
        this.oldHomework = oldHomework;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Homework getHomework() {
        return Homework;
    }

    public Homework getOldHomework() {
        return oldHomework;
    }
}
