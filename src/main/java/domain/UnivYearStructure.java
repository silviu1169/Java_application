package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UnivYearStructure {
    private SemesterStructure1 semesterStructure1;
    private SemesterStructure2 semesterStructure2;

    public UnivYearStructure(SemesterStructure1 semesterStructure1, SemesterStructure2 semesterStructure2){
        this.semesterStructure1 = semesterStructure1;
        this.semesterStructure2 = semesterStructure2;
    }


    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public LocalDateTime getCurrentDateTime (){
        return LocalDateTime.now();
    }

    public int getCurrentWeek(){
        LocalDate localDate = LocalDate.now();
        if (this.semesterStructure1.validateDate(localDate))
            return this.semesterStructure1.getCurrentWeek();
        if (this.semesterStructure2.validateDate(localDate))
            return this.semesterStructure2.getCurrentWeek();
        return 0;
    }

    public int getCurrentWeekFromLocalDateTime(LocalDateTime localDateTime){
        LocalDate localDate = localDateTime.toLocalDate();
        if (this.semesterStructure1.validateDate(localDate))
            return this.semesterStructure1.getCurrentWeek();
        if (this.semesterStructure2.validateDate(localDate))
            return this.semesterStructure2.getCurrentWeek();
        return 0;
    }
}
