package domain;

public class Homework extends Entity <String > {
    private String description;
    private int startWeek;
    private int deadlineWeek;

    public Homework(String id, String description, int startWeek, int deadlineWeek){
        super(id);
        this.description = description;
        this.startWeek = startWeek;
        this.deadlineWeek = deadlineWeek;
    }

    public String getID() {
        return super.getId();
    }

    public void setID(String ID) {
        super.setId(ID);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "ID='" + super.getId() + '\'' +
                ", description='" + description + '\'' +
                ", startWeek=" + startWeek +
                ", deadlineWeek=" + deadlineWeek +
                '}';
    }
}
