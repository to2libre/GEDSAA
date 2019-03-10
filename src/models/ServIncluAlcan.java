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
public class ServIncluAlcan {

    int id_servicio;
    String servicio;
    double precio_cuc;
    double precio_cup;

    public ServIncluAlcan() {
    }

    public ServIncluAlcan(int id_servicio, String servicio, double precio_cuc, double precio_cup) {        
        this.id_servicio = id_servicio;
        this.servicio = servicio;
        this.precio_cuc = precio_cuc;
        this.precio_cup = precio_cup;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getServicio() {
        return servicio;
    }
        
    public int getId_servicio() {
        return id_servicio;
    }

    public double getPrecio_cuc() {
        return precio_cuc;
    }

    public double getPrecio_cup() {
        return precio_cup;
    }

    public void setPrecio_cuc(double precio_cuc) {
        this.precio_cuc = precio_cuc;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setPrecio_cup(double precio_cup) {
        this.precio_cup = precio_cup;
    }

}
