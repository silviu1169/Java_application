package repository;

import domain.Entity;
import domain.Homework;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class HomeworkXMLRepository extends AbstractXMLRepository {
    public HomeworkXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    public Entity createEntityFromElement(Element element) {
        String id = element.getAttribute("id");
        String description = element.getElementsByTagName("description")
                .item(0)
                .getTextContent();

        int startWeek = Integer.parseInt(element.getElementsByTagName("startWeek")
                .item(0)
                .getTextContent()
        );

        int deadlineWeek = Integer.parseInt(element.getElementsByTagName("deadlineWeek")
                .item(0)
                .getTextContent()
        );
        Homework homework = new Homework(id, description, startWeek, deadlineWeek);
        return homework;
    }

    @Override
    public Element createElementFromEntity(Document document, Entity entity) {
        Homework homework = (Homework) entity;

        Element element = document.createElement("homework");
        element.setAttribute("id", homework.getId());

        Element description = document.createElement("description");
        description.setTextContent(homework.getDescription());
        element.appendChild(description);

        Element startWeek = document.createElement("startWeek");
        startWeek.setTextContent(String.valueOf(homework.getStartWeek()));
        element.appendChild(startWeek);

        Element deadlineWeek = document.createElement("deadlineWeek");
        deadlineWeek.setTextContent(String.valueOf(homework.getDeadlineWeek()));
        element.appendChild(deadlineWeek);

        return element;
    }
}
