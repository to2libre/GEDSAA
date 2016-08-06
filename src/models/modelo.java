/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos
 */
public class modelo extends SQLite_conexion{    

    public modelo() {
    }
    
    public Usuario[] getUsuarios(){        
        Usuario[] u = null;
        String tabla = "usuario";
        LinkedList usuarios = new LinkedList();
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM " + tabla + "; ");
            while (this.resultSet.next()) {  
                usuarios.add(new Usuario(this.resultSet.getInt("id_usuario"),this.resultSet.getString("usuario"), this.resultSet.getString("passwd"), this.resultSet.getInt("id_rol"), "TEMP"));                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        u = (Usuario[]) usuarios.toArray();
        return u;
    }
}
