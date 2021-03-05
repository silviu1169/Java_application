package validator;

import domain.Teacher;

public class TeacherValidator implements Validator<Teacher> {
    @Override
    public void validate(Teacher element) throws ValidationException {
        String string = "";
        if (element.getName().equals(""))
            string += "Invalid Data! Name is invalid!\n";
        if (element.getId().equals(""))
            string += "Invalid Data! ID is invalid!\n";
        if (element.getFirstName().equals(""))
            string += "Invalid Data! FirstName is invalid!\n";
        if (element.getEmail().equals( ""))
            string += "Invalid Data! FirstName is invalid!\n";
        if (string.length() > 0)
            throw new ValidationException(string);
    }
}
