package configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    File xmlFileUser;
    public String codigo_reup;
    public int id_ueb;
    public int mes; // Especifica el mes del periodo de trabajo
    public int anno; // Especifica el año del periodo de trabajo
    public boolean barraImportarExportar; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraAcciones; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraInformes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCierreMes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCobros; // Carga de (Achivo de configuracion XML  de configuracion)
    public String nombres; // Carga de (Achivo de configuracion XML  de configuracion)
    public String apellidos; // Carga de (Achivo de configuracion XML  de configuracion)
    public String email; // Carga de (Achivo de configuracion XML  de configuracion)
    public String empresa; // Carga de (Achivo de configuracion XML  de configuracion)
    public String length; // Carga de (Achivo de configuracion XML  de configuracion)
    public String ins; // Carga de (Achivo de configuracion XML  de configuracion)
    List list;

    public config() {
        //Se crea un SAXBuilder para poder parsear el archivo
        this.builder = new SAXBuilder();
        this.xmlFile = new File("config/configGEDSAA.xml");
        String homeUsuario = System.getProperty("user.home");
        this.xmlFileUser = new File(homeUsuario + "/configGEDSAA.xml");
    }

    public void crearFicheroConfig() {
        try {
            OutputStream out;
            try (InputStream in = new FileInputStream(this.xmlFile) //archivo origen
                    ) {
                out = new FileOutputStream(this.xmlFileUser); //archivo destino
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } //archivo destino
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void leerXml() {
        try {
            //Se crea el documento a traves del archivo
            //Document document = (Document) builder.build(xmlFile);

            if (!xmlFileUser.exists()) {
                crearFicheroConfig();
            }

            Document document = (Document) builder.build(xmlFileUser);

            //Se obtiene la raiz 'root'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'configuracion'
            list = rootNode.getChildren("configuracion");

            codigo_reup = ((Element) list.get(0)).getChildText("codigo_reup");
            id_ueb = Integer.parseInt(((Element) list.get(0)).getChildText("id_ueb"));
            mes = Integer.parseInt(((Element) list.get(0)).getChildText("mes"));
            anno = Integer.parseInt(((Element) list.get(0)).getChildText("anno"));
            barraImportarExportar = Boolean.valueOf(((Element) list.get(0)).getChildText("barraImportarExportar"));
            barraAcciones = Boolean.valueOf(((Element) list.get(0)).getChildText("barraAcciones"));
            barraInformes = Boolean.valueOf(((Element) list.get(0)).getChildText("barraInformes"));
            barraCierreMes = Boolean.valueOf(((Element) list.get(0)).getChildText("barraCierreMes"));
            barraCobros = Boolean.valueOf((((Element) list.get(0)).getChildText("barraCobros")));
            nombres = ((Element) list.get(0)).getChildText("nombres");
            apellidos = ((Element) list.get(0)).getChildText("apellidos");
            email = ((Element) list.get(0)).getChildText("email");
            empresa = ((Element) list.get(0)).getChildText("empresa");
            length = ((Element) list.get(0)).getChildText("length");
            ins = ((Element) list.get(0)).getChildText("ins");
        } catch (IOException | JDOMException io) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    public void escribirXML() {
        try {
            //Se crea el documento a traves del archivo
            Document doc = (Document) builder.build(xmlFileUser);

            //Llenando valores del archivo de configuración
            Element codigo_reup1 = new Element("codigo_reup");
            codigo_reup1.setText(this.codigo_reup);
            Element id_ueb1 = new Element("id_ueb");
            id_ueb1.setText(String.valueOf(this.id_ueb));
            Element mes1 = new Element("mes");
            mes1.setText(String.valueOf(this.mes));
            Element anno1 = new Element("anno");
            anno1.setText(String.valueOf(this.anno));
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
            Element nombres1 = new Element("nombres");
            nombres1.setText(String.valueOf(this.nombres));
            Element apellidos1 = new Element("apellidos");
            apellidos1.setText(String.valueOf(this.apellidos));
            Element email1 = new Element("email");
            email1.setText(String.valueOf(this.email));
            Element empresa1 = new Element("empresa");
            empresa1.setText(String.valueOf(this.empresa));
            Element length1 = new Element("length");
            length1.setText(String.valueOf(this.length));
            Element ins1 = new Element("ins");
            ins1.setText(String.valueOf(this.ins));

            LinkedList contenido = new LinkedList();
            contenido.add(codigo_reup1);
            contenido.add(id_ueb1);
            contenido.add(mes1);
            contenido.add(anno1);
            contenido.add(barraImportarExportar1);
            contenido.add(barraAcciones1);
            contenido.add(barraInformes1);
            contenido.add(barraCierreMes1);
            contenido.add(barraCobros1);
            contenido.add(nombres1);
            contenido.add(apellidos1);
            contenido.add(email1);
            contenido.add(empresa1);
            contenido.add(length1);
            contenido.add(ins1);

            doc.getRootElement().getChild("configuracion").setContent(contenido);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(this.xmlFileUser));
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

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnno(int anno) {
        this.anno = anno;
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

    public void setNombres(String nombres) {
        this.nombres = nombres;
        this.escribirXML();
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
        this.escribirXML();
    }

    public void setEmail(String email) {
        this.email = email;
        this.escribirXML();
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
        this.escribirXML();
    }

    public void setLength(String length) {
        this.length = length;
        this.escribirXML();
    }

    public void setIns(String ins) {
        this.ins = ins;
        this.escribirXML();
    }

    public int getId_ueb() {
        return id_ueb;
    }

    public int getMes() {
        return mes;
    }

    public int getAnno() {
        return anno;
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

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getLength() {
        return length;
    }

    public String getIns() {
        return ins;
    }
}
