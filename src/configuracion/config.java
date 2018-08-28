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
    public boolean barraImportarExportar; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraAcciones; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraInformes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCierreMes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCobros; // Carga de (Achivo de configuracion XML  de configuracion)
    List list;

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
            list = rootNode.getChildren("configuracion");

            codigo_reup = ((Element) list.get(0)).getChildText("codigo_reup");
            id_ueb = Integer.parseInt(((Element) list.get(0)).getChildText("id_ueb"));            
            barraImportarExportar = Boolean.valueOf(((Element) list.get(0)).getChildText("barraImportarExportar"));
            barraAcciones = Boolean.valueOf(((Element) list.get(0)).getChildText("barraAcciones"));
            barraInformes = Boolean.valueOf(((Element) list.get(0)).getChildText("barraInformes"));
            barraCierreMes = Boolean.valueOf(((Element) list.get(0)).getChildText("barraCierreMes"));
            barraCobros = Boolean.valueOf((((Element) list.get(0)).getChildText("barraCobros")));            
        } catch (IOException | JDOMException io) {
            System.err.println(io.getMessage());  
            System.err.println(io.getLocalizedMessage());
        }
    }
    
    public void escribirXML(){
        try {
            //Se crea el documento a traves del archivo
            Document doc = (Document) builder.build(xmlFile);
                      
            //Llenando valores del archivo de configuración
            Element codigo_reup1 = new Element("codigo_reup");
            codigo_reup1.setText(this.codigo_reup);           
            Element id_ueb1 = new Element("id_ueb");
            id_ueb1.setText(String.valueOf(this.id_ueb));
            Element barraImportarExportar1 = new Element("barraImportarExportar");
            barraImportarExportar1.setText(String.valueOf(this.barraImportarExportar));
            Element barraAcciones1 = new Element("barraAcciones");
            barraAcciones1.setText(String.valueOf(this.barraAcciones));
            Element barraInformes1 = new Element("barraInformes");
            barraInformes1.setText(String.valueOf(this.barraInformes));
            Element barraCierreMes1 = new Element("barraCierreMes");
            barraCierreMes1.setText(String.valueOf(this.barraCierreMes));
            Element barraCobros1 = new Element("barraCobros");
            barraCobros1.setText(String.valueOf(this.barraCobros));
            
            LinkedList contenido = new LinkedList();
            contenido.add(codigo_reup1);
            contenido.add(id_ueb1);
            contenido.add(barraImportarExportar1);
            contenido.add(barraAcciones1);
            contenido.add(barraInformes1);
            contenido.add(barraCierreMes1);
            contenido.add(barraCobros1);
            
            doc.getRootElement().getChild("configuracion").setContent(contenido);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            
            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(this.xmlFile));
        } catch (JDOMException | IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getLocalizedMessage());
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribirXML(String cofigo_reup, int id_ueb) {
        this.codigo_reup = cofigo_reup;
        this.id_ueb = id_ueb;
        escribirXML();
    }

    public void setCodigo_reup(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public void setId_ueb(int id_ueb) {
        this.id_ueb = id_ueb;
    }
    
    public void setBarraAcciones(boolean barraAcciones) {
        this.barraAcciones = barraAcciones;
        escribirXML();
    }

    public void setBarraCierreMes(boolean barraCierreMes) {
        this.barraCierreMes = barraCierreMes;        
        escribirXML();
    }

    public void setBarraCobros(boolean barraCobros) {
        this.barraCobros = barraCobros;        
        escribirXML();
    }

    public void setBarraImportarExportar(boolean barraImportarExportar) {
        this.barraImportarExportar = barraImportarExportar;
        escribirXML();
    }

    public void setBarraInformes(boolean barraInformes) {
        this.barraInformes = barraInformes;
        this.escribirXML();
    }

    public int getId_ueb() {
        return id_ueb;
    }

    public String getCodigo_reup() {
        return codigo_reup;
    }

    public boolean isBarraAcciones() {
        return barraAcciones;
    }

    public boolean isBarraCierreMes() {
        return barraCierreMes;
    }

    public boolean isBarraCobros() {
        return barraCobros;
    }

    public boolean isBarraImportarExportar() {
        return barraImportarExportar;
    }

    public boolean isBarraInformes() {
        return barraInformes;
    }
}
