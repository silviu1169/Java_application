package repository;

import domain.Entity;
import domain.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXMLRepository extends AbstractXMLRepository {
    public StudentXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    public Entity createEntityFromElement(Element element) {
        String id = element.getAttribute("id");
        //NodeList nods = studentElement.getChildNodes();
        String name = element.getElementsByTagName("name")
                .item(0)
                .getTextContent();

        String firstName = element.getElementsByTagName("firstName")
                .item(0)
                .getTextContent();
        String group = element.getElementsByTagName("group")
                .item(0)
                .getTextContent();
        String email = element.getElementsByTagName("email")
                .item(0)
                .getTextContent();
        String teacherTrainingLab = element.getElementsByTagName("teacherTrainingLab")
                .item(0)
                .getTextContent();

        Student student= new Student(id,name, firstName, group, email, teacherTrainingLab);
        return student;
    }

    @Override
    public Element createElementFromEntity(Document document, Entity entity) {
        Student student = (Student) entity;

        Element element = document.createElement("student");
        element.setAttribute("id", student.getId());

        Element name = document.createElement("name");
        name.setTextContent(student.getName());
        element.appendChild(name);

        Element firstName = document.createElement("firstName");
        firstName.setTextContent(student.getFirstName());
        element.appendChild(firstName);

        Element grupa = document.createElement("group");
        grupa.setTextContent(( student.getGroup()).toString());
        element.appendChild(grupa);

        Element email = document.createElement("email");
        email.setTextContent(student.getEmail());
        element.appendChild(email);

        Element teacherTrainingLab = document.createElement("teacherTrainingLab");
        teacherTrainingLab.setTextContent(student.getTeacherTrainingLab());
        element.appendChild(teacherTrainingLab);

        return element;
    }
}
