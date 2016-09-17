/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class modelo extends SQLite_conexion {

    public Usuario usuario;

    /**
     * Constructor de la clase <b>modelo</b>
     */
    public modelo() {
    }

    /**
     * Método para la autenticación del usuario.
     *
     * @param usuario Usario a verificar
     * @param password Password a verificar codificada en sha2
     * @return Verdadero si el usuario y la contraseña existe, falso si no
     * existe.
     */
    public Usuario Autenticar(String usuario, String password) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM v_usuario_all WHERE v_usuario_all.usuario = '" + usuario + "' AND v_usuario_all.password = '" + pssSHA512 + "'; ");
            if (resultSet.next()) {
                try {
                    this.usuario = new Usuario(resultSet.getInt("id_usuario"), resultSet.getString("usuario"), resultSet.getString("password"), resultSet.getInt("id_rol"), resultSet.getString("rol"));
                } catch (SQLException ex) {
                    Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this.getUsuario();
    }

    /**
     * Método para la creación de usuario
     *
     * @param usuario Nombre de usuario a crear
     * @param password Contraseña del usuario a crear
     * @param idRol Id del rol al que pertenece el usuario
     * @return boolean, verdadero si se creo el usuario, falso si no pudo
     * crearse.
     */
    public boolean crearUsuario(String usuario, String password, int idRol) {        
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        return insertar("t_usuario", "usuario, password, id_rol", "'" + usuario + "','" + pssSHA512 + "'," + idRol);
    }

    /**
     * @param idUsuario
     * @param usuario
     * @param password
     * @param idRol
     * @return
     */
    public boolean actualizarUsuario(int idUsuario, String usuario, String password, int idRol) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        return this.actualizar("t_usuario", "usuario = '" + usuario + "',password = '" + pssSHA512 + "', id_rol = " + idRol, "id_usuario = " + idUsuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * *
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     *
     * @param message texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     * SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public static String getStringMessageDigest(String message, String algorithm) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest" + ex.getMessage());
        }
        return toHexadecimal(digest);
    }

    /**
     * *
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }
}
