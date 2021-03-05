package validator;

import domain.Student;

public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student student) throws ValidationException {
        String string = "";

        if (student.getID() == "")
            string += "Incorect data! ID is invalid\n";

        if (student.getName() == "")
            string += "Incorect data! Nume is invalid\n";

        if (student.getFirstName() == "")
            string += "Incorect data! Prenume is invalid\n";

        if (student.getGroup() == "")
            string += "Incorect data! Grupa is invalid\n";

        if (student.getEmail() == "")
            string += "Incorect data! Email is invalid\n";

        if (student.getTeacherTrainingLab() == "")
            string += "Incorect data! CadruDidacticIndrumatorLab is invalid\n";

        if (string.length() > 0)
            throw new ValidationException(string);
    }
}
