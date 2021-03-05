package domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SemesterStructure1 extends AbstractSemesterStructure {

    public SemesterStructure1(){
        this.startSemesterDate = LocalDate.of(2019, 9,30);
        this.startHolidayDate = LocalDate.of(2019, 12,21);
        this.endHolidayDate = LocalDate.of(2020, 1,5);
        this.endSemesterDate = LocalDate.of(2020, 1,17);
    }


    @Override
    public int getCurrentWeek() {
        int currentWeek = 0;
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        if (calendar.get(Calendar.WEEK_OF_YEAR) < 39)
            currentWeek = calendar.get(Calendar.WEEK_OF_YEAR) + 11;
        else
            currentWeek= calendar.get(Calendar.WEEK_OF_YEAR) - 39 ;
        return currentWeek;
    }
}