package service;

import domain.Homework;
import domain.UnivYearStructure;
import events.ChangeEventType;
import events.HomeworkChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PageableImplementation;
import repository.paging.PagingRepository;
import validator.HomeworkValidator;
import validator.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.StrictMath.abs;

public class HomeworkService implements Observable<HomeworkChangeEvent> {
    private PagingRepository<String, Homework> repository;
    private StudentService studentService;
    private HomeworkValidator validator;
    private UnivYearStructure univYearStructure;
    private int idCount;

    public HomeworkService(StudentService studentService, PagingRepository<String, Homework> repository, HomeworkValidator validator, UnivYearStructure univYearStructure){
        this.repository = repository;
        this.validator = validator;
        this.univYearStructure = univYearStructure;
        this.idCount = setIdCount();
        this.studentService = studentService;
    }

    public int getCurrentWeek(){return this.univYearStructure.getCurrentWeek();}

    private int setIdCount(){
        int max = 0;
        for (Homework homework:
                this.findAll()){
            int idEntity = Integer.parseInt(homework.getId());
            if (idEntity > max){
                max = idEntity;
            }
        }
        return max + 1;
    }


    public Homework findOne(String id) {
        return this.repository.findOne(id);
    }


    public int maxGrade(String id, int theWeekWhenTheStudentShowTheAssignment){
        int currentWeek = theWeekWhenTheStudentShowTheAssignment;

        Homework homework = this.findOne(id);
        if (homework.getDeadlineWeek() >= currentWeek && currentWeek >= homework.getStartWeek())
            return 10;

        if (abs(currentWeek - homework.getDeadlineWeek()) > 2 )
            return 1;

        if (homework.getDeadlineWeek() < currentWeek) {
            return 10-(currentWeek - homework.getDeadlineWeek());
        }


        return 1;
    }

    public Homework delete(String id) {
        Homework homework = this.repository.delete(id);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.DELETE, homework));
        return homework;
    }

    public Iterable<Homework> findAll() {
        return this.repository.findAll();
    }

    public void save(String description, String deadlineWeek,String teacherEmail) throws ValidationException {//-----------startweek automate
        Homework homework = new Homework(String.valueOf(this.idCount), description,this.univYearStructure.getCurrentWeek(), Integer.parseInt(deadlineWeek)) ;
        this.idCount++;
        this.validator.validate(homework);
        this.repository.save(homework);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.ADD, homework));

        List<String> studentsEmail = new ArrayList<String>() {};
        this.studentService.findAll().forEach(x->studentsEmail.add(x.getEmail()));

        SendEmail sendEmail = new SendEmail(
                "New homework added: " + description + ". The deadline is:" + deadlineWeek ,
                "New homework added!",
                teacherEmail,
                studentsEmail
        );
        sendEmail.send();


    }

    public void update(String id, String description, String deadlineWeek,String teacherEmail) throws ValidationException {
        Homework oldHomework = this.repository.findOne(id);
        Homework newHomework = oldHomework;
        if (Integer.parseInt(deadlineWeek) < this.univYearStructure.getCurrentWeek()){
            throw new ValidationException("The deadline is smaller than the curent week!\n The current week is ".concat(String.valueOf(this.univYearStructure.getCurrentWeek())).concat(" \nand the deadline you entered is ".concat(deadlineWeek)));
        }
        newHomework.setDescription(description);
        newHomework.setDeadlineWeek(Integer.parseInt(deadlineWeek));

        //-------to verify if the new deadlineWeek
        //-------is greater than actualCurrentWeek


        this.validator.validate(newHomework);
        this.repository.update(oldHomework, newHomework);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.UPDATE, newHomework));

        List<String> studentsEmail = new ArrayList<String>() {};
        this.studentService.findAll().forEach(x->studentsEmail.add(x.getEmail()));

        SendEmail sendEmail = new SendEmail(
                "New deadline for  " + description + ". The deadline is:" + deadlineWeek ,
                "New deadline for " + description,
                teacherEmail,
                studentsEmail
        );
        sendEmail.send();

    }

    public List<Homework> findAllById(String id, List<Homework> homeworkList){
        return homeworkList.stream()
                .filter(x->x.getId().contains(id))
                .collect(Collectors.toList());
    }

    public List<Homework> findAllByDescription(String description, List<Homework> homeworkList){
        return homeworkList.stream()
                .filter(x->x.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    public List<Homework> findAllByStartWeek(int startWeek, List<Homework> homeworkList){
        return homeworkList.stream()
                .filter(x->x.getStartWeek()==startWeek)
                .collect(Collectors.toList());
    }

    public List<Homework> findAllByDeadlineWeek(int deadlineWeek, List<Homework> homeworkList){
        return homeworkList.stream()
                .filter(x->x.getDeadlineWeek() == deadlineWeek)
                .collect(Collectors.toList());
    }


    public Homework findOneByDescription(String homeworkDescription) {
        for (Homework homework :
                this.repository.findAll()) {
            if (homework.getDescription().equals(homeworkDescription))
                return homework;
        }
        return null;
    }

    private List<Observer<HomeworkChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<HomeworkChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<HomeworkChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(HomeworkChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }

    private int page = 0;
    private int size = 1;

    private Pageable pageable;

    public int getPage(){return this.page;}

    public void setPageSize(int size) {
        this.size = size;
    }

    public List<Homework> getNextMessages() {
        this.page++;
        return getMessagesOnPage(this.page);
    }

    public List<Homework> getMessagesOnPage(int page) {
        this.page=page;
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Homework> homeworkPage = repository.findAll(pageable);
        return homeworkPage.getContent().collect(Collectors.toList());
    }

    public List<Homework> getMessagesFromPage(int page) {
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Homework> homeworkPage = repository.findAll(pageable);
        return homeworkPage.getContent().collect(Collectors.toList());
    }
}
