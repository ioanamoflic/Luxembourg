import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ReadXML {

    public static Vector<Nod> citireNoduri(String file) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(file));

            Vector<Nod> noduri = new Vector<>();
            NodeList nodeList = document.getElementsByTagName("node");

            int n = nodeList.getLength();
            for (int index = 0; index < n; index++) {
                Node node = nodeList.item(index);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    int id = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());

                    double y = (Double.parseDouble(node.getAttributes().getNamedItem("latitude").getNodeValue()))/100000;


                    double x = (Double.parseDouble(node.getAttributes().getNamedItem("longitude").getNodeValue()))/100000;

                    noduri.add(new Nod(x, y, id));
                }
            }

            return noduri;

        } catch (ParserConfigurationException | SAXException | IOException e) {

        }
        return null;
    }

    public static void citireLegaturi(String file, Vector<Nod> nodes) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(file));

            NodeList nodeList = document.getElementsByTagName("arc");

            int n = nodeList.getLength();

            for (int index = 0; index < n ; index++) {
                Node node = nodeList.item(index);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    int from = Integer.parseInt(node.getAttributes().getNamedItem("from").getNodeValue());
                    int to = Integer.parseInt(node.getAttributes().getNamedItem("to").getNodeValue());
                    int length = Integer.parseInt(node.getAttributes().getNamedItem("length").getNodeValue());

                    nodes.elementAt(from).addLegatura(nodes.elementAt(to), length);

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {

        }
    }
}

