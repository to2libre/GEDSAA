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
public class ClienteServicio {
    int id_cliente;
    String nombre_cliente;
    int id_servicio;
    String descripcion;

    public ClienteServicio() {
    }

    public ClienteServicio(int id_cliente, String nombre_cliente, int id_servicio, String descripcion) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.id_servicio = id_servicio;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
}
