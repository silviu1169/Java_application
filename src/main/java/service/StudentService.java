package service;

import domain.Student;
import events.ChangeEventType;
import events.StudentChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PageableImplementation;
import repository.paging.PagingRepository;
import validator.StudentValidator;
import validator.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements Observable<StudentChangeEvent> {
    private PagingRepository<String, Student> repository;
    private StudentValidator validator;
    private int idCount;

    public StudentService(PagingRepository<String , Student> repository, StudentValidator validator) {
        this.repository = repository;
        this.validator = validator;
        this.idCount = setIdCount();
    }

    private int setIdCount(){
        int max = 0;
        for (Student student:
                this.findAll()){
            int idEntity = Integer.parseInt(student.getId());
            if (idEntity > max){
                max = idEntity;
            }
        }
        return max + 1;
    }


    public List<Student> allStudentsFromAGroup(String group){
        List<Student> studentList = new ArrayList<Student>();
        for (Student student:
                this.repository.findAll()) {
            studentList.add(student);
        }
        return studentList
                .stream()
                .filter(x->x.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public void save(String name, String firstName, String group,
                     String email, String cadruDidacticIndrumatorLab) throws ValidationException {
        Student student = new Student("1", name, firstName, group, email, cadruDidacticIndrumatorLab);
        this.validator.validate(student);
        student.setId(String.valueOf(this.idCount));
        this.idCount++;
        this.repository.save(student);
        notifyObservers(new StudentChangeEvent(ChangeEventType.UPDATE, student));
    }

    public Student findOne(String id) {
        return this.repository.findOne(id);
    }

    public Student delete(String id) {
        Student student = this.repository.delete(id);
        notifyObservers(new StudentChangeEvent(ChangeEventType.DELETE, student));
        return student;
    }

    public Iterable<Student> findAll() {
        return this.repository.findAll();
    }

    public void update(String id, String name, String firstName, String group,
                       String email, String cadruDidacticIndrumatorLab) {
        Student oldStudent = this.repository.findOne(id);
        Student newStudent = oldStudent;
        newStudent.setName(name);
        newStudent.setFirstName(firstName);
        newStudent.setGroup(group);
        newStudent.setEmail(email);
        newStudent.setTeacherTrainingLab(cadruDidacticIndrumatorLab);
        try {
            this.validator.validate(newStudent);
            this.repository.update(oldStudent, newStudent);
            notifyObservers(new StudentChangeEvent(ChangeEventType.UPDATE, newStudent));
        }
        catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAllById(String studentId, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getId().contains(studentId))
                .collect(Collectors.toList());
    }

    public List<Student> findAllByName(String name, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Student> findAllByFirstName(String firstName, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    public List<Student> findAllByEmail(String email, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getEmail().contains(email))
                .collect(Collectors.toList());
    }

    public List<Student> findAllByGroup(String group, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getGroup().contains(group))
                .collect(Collectors.toList());
    }

    public List<Student> findAllByTeacherTrainingLab(String teacherTrainingLab, List<Student> studentList){
        return studentList.stream()
                .filter(x->x.getTeacherTrainingLab().contains(teacherTrainingLab))
                .collect(Collectors.toList());
    }

    public Student findOneByNameAndFirstName(String nameAndFirstName){
        for (Student student :
            this.repository.findAll()) {
            if (student.getName().concat(" " + student.getFirstName()).contains(nameAndFirstName))
                return student;
        }
        return null;
    }

    private List<Observer<StudentChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<StudentChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<StudentChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(StudentChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }

    private int page = 0;
    private int size = 1;

    private Pageable pageable;

    public int getPage(){return this.page;}

    public void setPageSize(int size) {
        this.size = size;
    }

    public List<Student> getNextMessages() {
        this.page++;
        return getMessagesOnPage(this.page);
    }

    public List<Student> getMessagesOnPage(int page) {
        this.page=page;
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Student> studentPage = repository.findAll(pageable);
        return studentPage.getContent().collect(Collectors.toList());
    }

    public List<Student> getMessagesFromPage(int page) {
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Student> studentPage = repository.findAll(pageable);
        return studentPage.getContent().collect(Collectors.toList());
    }


}
