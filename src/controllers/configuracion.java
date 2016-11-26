/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class configuracion {

    String codigo_reup;

    public configuracion() {
    }

    public static void main(String[] args) {
        configuracion c = new configuracion();
        c.escribirCodigo_reup(c.leerCodigo_reup());
    }

    public configuracion(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public String getCodigo_reup() {
        return codigo_reup;
    }

    public void setCodigo_reup(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public LinkedList leerCodigo_reup() {
        File f = new File("C:/Users/Carlos/Documents/NetBeansProjects/FactEstatal/src/configuracion/configuracion.xml");
        LinkedList archivo = new LinkedList();
        try {
            FileReader fr = new FileReader(f);
            while (fr.ready()) {
                char[] c = Character.toChars(fr.read());
                archivo.add(c[0]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        LinkedList arreglado = new LinkedList();
        String a = "";
        for (Object archivo1 : archivo) {
            if (archivo1.toString().equals(">")) {
                a += archivo1;
                arreglado.add(a);
                a = "";
            } else {
                a += archivo1;
            }
        }        
        return arreglado;
    }

    public void escribirCodigo_reup(LinkedList codigo) {
        File f = new File("C:/Users/Carlos/Documents/NetBeansProjects/FactEstatal/src/configuracion/configuracion.xml");
        try {
            FileWriter fw = new FileWriter(f);
            for (Object linea : codigo) {
                fw.write(linea.toString());
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
