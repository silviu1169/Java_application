package validator;

import domain.Homework;

public class HomeworkValidator implements Validator<Homework> {
    @Override
    public void validate(Homework homework) throws ValidationException {
        String string = "";

        if (homework.getID().equals(""))
            string += "Incorect data! ID is invalid!\n";

        if (homework.getStartWeek() < 1 || homework.getStartWeek() > 14)
            string += "Incorect data! StartWeek is invalid!\n";

        if (homework.getDeadlineWeek() < 1 || homework.getDeadlineWeek() > 14)
            string += "Incorect data! DeadLineWeek is invalid!\n";


        if (homework.getStartWeek()  > homework.getDeadlineWeek() )
            string += "Incorect data! StartWeek is greater than DeadLineWeek!\n";

        if(string.length() > 0 )
            throw new ValidationException(string);
    }
}
