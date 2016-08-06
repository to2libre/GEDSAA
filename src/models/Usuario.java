/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author carlos860920
 */
public class Usuario {
    int idUsuario;
    String usuario;
    String password;
    int idRol;
    String Rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String password, int idRol, String Rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.idRol = idRol;
        this.Rol = Rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return Rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
