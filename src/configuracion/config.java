package configuracion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class config {

    SAXBuilder builder;
    File xmlFile;
    public String codigo_reup;
    public int id_ueb;

    public config() {
        //Se crea un SAXBuilder para poder parsear el archivo
        this.builder = new SAXBuilder();
        this.xmlFile = new File("config/configuracion.xml");
    }

    public void leerXml() {
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'root'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'configuracion'
            List list = rootNode.getChildren("configuracion");

            codigo_reup = ((Element) list.get(0)).getChildText("codigo_reup");
            id_ueb = Integer.parseInt(((Element) list.get(0)).getChildText("id_ueb"));
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    public void escribirXML(String cofigo_reup, int id_ueb) {

        try {
            //Se crea el documento a traves del archivo
            Document doc = (Document) builder.build(xmlFile);
                      
            Element codigo_reup1 = new Element("codigo_reup");
            codigo_reup1.setText(cofigo_reup);
            
            Element id_ueb1 = new Element("id_ueb");
            id_ueb1.setText(String.valueOf(id_ueb));
            
            LinkedList contenido = new LinkedList();
            contenido.add(codigo_reup1);
            contenido.add(id_ueb1);
            
            doc.getRootElement().getChild("configuracion").setContent(contenido);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            
            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(this.xmlFile));
            
            System.out.println("File Saved!");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCodigo_reup(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public void setId_ueb(int id_ueb) {
        this.id_ueb = id_ueb;
    }

    public int getId_ueb() {
        return id_ueb;
    }

    public String getCodigo_reup() {
        return codigo_reup;
    }

}
