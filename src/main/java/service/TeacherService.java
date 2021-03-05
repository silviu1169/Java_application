package service;

import domain.Teacher;
import events.ChangeEventType;
import events.TeacherChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PageableImplementation;
import repository.paging.PagingRepository;
import validator.TeacherValidator;
import validator.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherService implements Observable<TeacherChangeEvent> {
    private PagingRepository<String, Teacher> repository;
    private TeacherValidator validator;
    private int idCount; //at every start it will set at 0, but if we have already entities with id 0 in repo it will crash
                         // I must set it at the high id present + 1

    private int setIdCount(){
        int max = 0;
        for (Teacher teacher:
            this.findAll()){
                int idEntity = Integer.parseInt(teacher.getId());
                if (idEntity > max){
                    max = idEntity;
            }
        }
        return max + 1;
    }

    public TeacherService(PagingRepository<String, Teacher> repository, TeacherValidator validator) {
        this.repository = repository;
        this.validator = validator;
        this.idCount = setIdCount();
    }

    public void save(String name, String firstName, String email) throws ValidationException{
        Teacher teacher = new Teacher("1", name,firstName,email);
        this.validator.validate(teacher);
        teacher.setId(String.valueOf(this.idCount));
        this.idCount++;
        this.repository.save(teacher);
        notifyObservers(new TeacherChangeEvent(ChangeEventType.ADD, teacher));
    }

    public void save(Teacher teacher) throws ValidationException {
        this.validator.validate(teacher);
        teacher.setId(String.valueOf(idCount));
        idCount++;
        this.repository.save(teacher);
    }

    public void update(String id, String name, String firstName, String email){
        Teacher oldTeacher = this.repository.findOne(id);
        if (oldTeacher != null) {
            Teacher newTeacher = oldTeacher;
            newTeacher.setName(name);
            newTeacher.setFirstName(firstName);
            newTeacher.setEmail(email);
            this.repository.update(oldTeacher, newTeacher);
            notifyObservers(new TeacherChangeEvent(ChangeEventType.UPDATE, newTeacher));
        }
    }

    public List<Teacher> findAllById(String id, List<Teacher> teacherList){
        return teacherList.stream()
                .filter(x->x.getId().contains(id))
                .collect(Collectors.toList());
    }

    public List<Teacher> findAllByName(String name, List<Teacher> teacherList){
        return teacherList.stream()
                .filter(x->x.getName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Teacher> findAllByFirstName(String firstName, List<Teacher> teacherList){
        return teacherList.stream()
                .filter(x->x.getFirstName().contains(firstName))
                .collect(Collectors.toList());
    }

    public List<Teacher> findAllByEmail(String email, List<Teacher> teacherList){
        return teacherList.stream()
                .filter(x->x.getEmail().contains(email))
                .collect(Collectors.toList());
    }

    public Teacher findOneByNameAndFirstName(String nameAndFirstName){
        for (Teacher teacher :
                this.repository.findAll()) {
            if (teacher.getName().concat(" " + teacher.getFirstName()).contains(nameAndFirstName))
                return teacher;
        }

        return null;
    }

    public Teacher findOne(String id){
        return this.repository.findOne(id);
    }

    public Teacher delete(String id) {
        Teacher teacher =this.repository.delete(id);
        notifyObservers(new TeacherChangeEvent(ChangeEventType.DELETE, teacher));
        return teacher;
    }

    public Iterable<Teacher> findAll(){
        return this.repository.findAll();
    }

    private List<Observer<TeacherChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<TeacherChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<TeacherChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(TeacherChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }

    private int page = 0;
    private int size = 1;

    private Pageable pageable;

    public void setPageSize(int size) {
        this.size = size;
    }

    public int getPage(){return this.page;}

    public List<Teacher> getNextMessages() {

        this.page++;
        return getMessagesOnPage(this.page);
    }

    public List<Teacher> getMessagesOnPage(int page) {
        this.page=page;
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Teacher> teacherPage = repository.findAll(pageable);
        return teacherPage.getContent().collect(Collectors.toList());
    }

    public List<Teacher> getMessagesFromPage(int page) {
        Pageable pageable = new PageableImplementation(page, this.size);
        Page<Teacher> teacherPage = repository.findAll(pageable);
        return teacherPage.getContent().collect(Collectors.toList());
    }


}
