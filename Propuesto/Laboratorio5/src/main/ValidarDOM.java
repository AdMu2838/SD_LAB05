import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;

public class ValidarDOM {
    public static void main(String[] args) {
        try {
            File archivo = new File("src/main/resources/boe.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new org.xml.sax.helpers.DefaultHandler() {
                public void error(SAXException e) throws SAXException {
                    System.out.println("❌ Error: " + e.getMessage());
                    throw e;
                }

                public void fatalError(SAXException e) throws SAXException {
                    System.out.println("❌ Error fatal: " + e.getMessage());
                    throw e;
                }

                public void warning(SAXException e) {
                    System.out.println("⚠️ Advertencia: " + e.getMessage());
                }
            });

            Document doc = builder.parse(archivo);
            System.out.println("✅ XML válido con DOM.");
        } catch (Exception e) {
            System.out.println("❌ Error de validación: " + e.getMessage());
        }
    }
}
