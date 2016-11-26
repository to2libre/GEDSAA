/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEDSAA;

import controllers.controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import models.modelo;
import views.factestatal.Autenticar;
import views.factestatal.Principal;

/**
 *
 * @author carlos860920
 */
public class GEDSAA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //se crean los objetos MODELO Y VISTA
        modelo modelo = new modelo();
        Autenticar vistaA = new Autenticar();
        Principal vista = new Principal();
        //Para colocar un skin propio de java
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            new controlador(vista,vistaA, modelo);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
    }
}
