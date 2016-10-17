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
public class Rol {

    int id_rol;
    String rol;

    public Rol() {
    }

    public Rol(int id_rol, String rol) {
        this.id_rol = id_rol;
        this.rol = rol;
    }

    public int getId_rol() {
        return id_rol;
    }

    public String getRol() {
        return rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return id_rol + ", " + rol;
    }
}
