package domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SemesterStructure2 extends AbstractSemesterStructure {

    public SemesterStructure2(){
        this.startSemesterDate = LocalDate.of(2020, 2,24);
        this.startHolidayDate = LocalDate.of(2020, 4,18);
        this.endHolidayDate = LocalDate.of(2020, 4,26);
        this.endSemesterDate = LocalDate.of(2020, 6,5);
    }

    @Override
    public int getCurrentWeek() {
        int currentWeek = 0;
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        LocalDate localDate = LocalDate.now();
        if (localDate.isBefore(this.startHolidayDate))
            currentWeek = calendar.get(Calendar.WEEK_OF_YEAR) -8;
        if (localDate.isAfter(this.endHolidayDate))
            currentWeek = calendar.get(Calendar.WEEK_OF_YEAR) -9;
        return currentWeek;
    }
}
