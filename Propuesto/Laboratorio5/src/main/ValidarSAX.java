import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class ValidarSAX {
    public static void main(String[] args) {
        try {
            File archivo = new File("src/main/resources/boe.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();

            parser.parse(archivo, new DefaultHandler() {
                public void error(SAXParseException e) throws SAXException {
                    System.out.println("❌ Error: " + e.getMessage());
                    throw e;
                }

                public void fatalError(SAXParseException e) throws SAXException {
                    System.out.println("❌ Error fatal: " + e.getMessage());
                    throw e;
                }

                public void warning(SAXParseException e) {
                    System.out.println("⚠️ Advertencia: " + e.getMessage());
                }
            });

            System.out.println("✅ XML válido con SAX.");
        } catch (Exception e) {
            System.out.println("❌ Error de validación: " + e.getMessage());
        }
    }
}
