package repository;

import domain.Entity;
import domain.Teacher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TeacherXMLRepository extends AbstractXMLRepository {
    public TeacherXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    public Entity createEntityFromElement(Element element) {
        String id = element.getAttribute("id");
        String name = element.getElementsByTagName("name")
                .item(0)
                .getTextContent();

        String firstName = element.getElementsByTagName("firstName")
                .item(0)
                .getTextContent();
        String email = element.getElementsByTagName("email")
                .item(0)
                .getTextContent();
        Teacher teacher = new Teacher(id, name, firstName, email);
        return teacher;
    }

    @Override
    public Element createElementFromEntity(Document document, Entity entity) {
        Teacher teacher = (Teacher)entity;

        Element element = document.createElement("teacher");
        element.setAttribute("id",teacher.getId());

        Element name = document.createElement("name");
        name.setTextContent(teacher.getName());
        element.appendChild(name);

        Element firstName = document.createElement("firstName");
        firstName.setTextContent(teacher.getFirstName());
        element.appendChild(firstName);

        Element email = document.createElement("email");
        email.setTextContent(teacher.getEmail());
        element.appendChild(email);

        return element;
    }
}
