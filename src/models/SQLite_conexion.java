/**
 * //Clase para el trabajo con Bases de datos SQLite//
 *
 * @author carlos860920
 * @version 2016.07.25
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite_conexion {

    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    private final String db;

    /**
     * Constructor de la clase SQlite_conexion
     */
    public SQLite_conexion() {
        this.db = "db/gedsaa.sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.db);            
        } catch (ClassNotFoundException | SQLException e) {            
            System.err.println(e);
        }
    }

    /**
     * Método para la insercion de contenido en la DB
     *
     * @param table String con el nombre de la tabla
     * @param fields String con el nombre de la columna
     * @param values String con el valor de la columna
     * @return Boolean en falso si no insertó o en verdadero si insertó
     */
    public boolean insertar(String table, String fields, String values) {
        boolean res = false;

        String q = " INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
        try {
            try (PreparedStatement pstm = this.connection.prepareStatement(q)) {
                pstm.execute();
            }
            res = true;
        } catch (SQLException e) {            
            if (e.getErrorCode() == 19){
                return false;
            }
            else
                System.err.println(e.getMessage());
        }
        return res;
    }

    /**
     * Método para la actualizar de contenido en la DB
     *
     * @param table String con el nombre de la tabla
     * @param fieldsValues String con el nombre de la fila y el valor, en el
     * formato nombre = carlos
     * @param donde criterio para la actualizacion
     * @return Boolean en falso si no insertó o en verdadero si insertó
     */
    public boolean actualizar(String table, String fieldsValues, String donde) {
        boolean res = false;
        String q = "UPDATE " + table + " SET " + fieldsValues + " WHERE " + donde + ";";
        try {
            try (PreparedStatement pstm = this.connection.prepareStatement(q)) {
                pstm.execute();
            }
            res = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return res;
    }

    /**
     * Método para la seleccion de contenido Este lo estoy arreglando ya que
     * estaba peronalizado para el trabajo con otra DB
     *
     * @param tabla String con el nombre de la tabla
     * @return String
     */
    public String seleccionarString(String tabla) {
        String res = "";
        LinkedList columName = new LinkedList();
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM " + tabla + "; ");
            // Conocer el nombre de las columnas
            while (this.resultSet.next()) {
                columName.add(this.resultSet.getCursorName());
                res += this.resultSet.getCursorName() + " | ";
            }
            res += "\n";
            while (this.resultSet.next()) {
                res = res + this.resultSet.getString("id_modelo") + " | " + this.resultSet.getString("nombre") + " \n ";
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return res;
    }

    /**
     * Método para la seleccion de contenido
     *
     * @param tabla String con la tabla
     * @return <b>ResultSet</b> con los valores de la table
     */
    public ResultSet seleccionarResultSet(String tabla) {
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM " + tabla + "; ");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        //desconectar();
        return this.resultSet;
    }

    /**
     * Método para la seleccion de contenido
     *
     * @param tabla String con la tabla
     * @param Consuta String con el <b>WHERE</b> de la consulta. Ejemplo "nombre
     * = 'Pancho' AND apellidos = 'Perez' "
     * @return <b>ResultSet</b> con los valores de la table
     */
    public ResultSet seleccionarResultSet(String tabla, String Consuta) {
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM " + tabla + " WHERE " + Consuta + ";");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return this.resultSet;
    }

    /**
     * Método para eliminar el registro de una tabla
     *
     * @param tabla String con la tabla
     * @param Donde String con el <b>WHERE</b> de la consulta. Ejemplo "nombre =
     * 'Pancho' AND apellidos = 'Perez' "
     * @return <b>Boolean</b>
     */
    public boolean eliminarRegistro(String tabla, String Donde) {
        int eliminado = 1;
        try {
            this.statement = this.connection.createStatement();
            eliminado = this.statement.executeUpdate("DELETE FROM " + tabla + " WHERE " + Donde);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return eliminado == 1;
    }

    /**
     * Método par cerrar la conexion con la <b>DB</b>
     */
    public void desconectar() {
        try {
            this.resultSet.close();
            this.statement.close();
            this.connection.close();
            System.out.println("Desconectado de la base de datos [ " + this.db + "]");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
