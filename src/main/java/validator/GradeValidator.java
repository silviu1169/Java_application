package validator;

import domain.Grade;

public class GradeValidator implements Validator<Grade>{
    @Override
    public void validate(Grade grade) throws ValidationException {
        String string = "";
        if (grade.getId() == "")
            string += "Incorect data! ID is invalid!\n";

        if ( (grade.getValue() <1)  || (grade.getValue() >10))
            string += "Incorect data! Value is invalid!\n";

        if (grade.getTeacherId() == "")
            string += "Incorect data! Teacher is invalid!\n";

        if(string.length() > 0 )
            throw new ValidationException(string);
    }
}
