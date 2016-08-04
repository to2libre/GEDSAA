/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factestatal;

import models.SQLite_conexion;

/**
 *
 * @author carlos860920
 */
public class FactEstatal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // LLamada al formulario principal
        Autenticar autenticar = new Autenticar();
        autenticar.setVisible(true);

        SQLite_conexion c = new SQLite_conexion();
        System.out.println(c.seleccionarString("usuario"));
    }
}
