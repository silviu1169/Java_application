package repository;

import domain.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public abstract class AbstractXMLRepository extends AbstractFileRepository{

    public AbstractXMLRepository(String fileName){
        super(fileName);

    }

    @Override
    public void loadFromFile() {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.fileName);
            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                if (node instanceof Element) {
                    Entity<String> entity = this.createEntityFromElement((Element) node);
                    super.save(entity);
                }
            }
        } catch (IOException e) {
            System.out.println("io-exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToFile() {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root = document.createElement("students");
            document.appendChild(root);
            super.findAll().forEach(s -> {
                Element e = this.createElementFromEntity(document, (Entity) s);
                root.appendChild(e);
            });

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();

            Source source=new DOMSource(document);

            transformer.transform(source,
                    new StreamResult(fileName));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract Entity createEntityFromElement(Element element);

    public abstract Element createElementFromEntity(Document document, Entity entity);

}
