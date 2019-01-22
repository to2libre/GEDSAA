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
public class ServicioSobreConsumo {

    int id_servicio;
    String servicio;
    double precio_cuc_s;
    double precio_cup_s;
    int id_sobreconsumo;
    String sobreconsumo;
    double precio_cuc_sc;
    double precio_cup_sc;

    public ServicioSobreConsumo() {
    }

    public ServicioSobreConsumo(int id_servicio, String servicio, double precio_cuc_s, double precio_cup_s, int id_sobreconsumo, String sobreconsumo, double precio_cuc_sc, double precio_cup_sc) {
        this.id_servicio = id_servicio;
        this.servicio = servicio;
        this.precio_cuc_s = precio_cuc_s;
        this.precio_cup_s = precio_cup_s;
        this.id_sobreconsumo = id_sobreconsumo;
        this.sobreconsumo = sobreconsumo;
        this.precio_cuc_sc = precio_cuc_sc;
        this.precio_cup_sc = precio_cup_sc;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public int getId_sobreconsumo() {
        return id_sobreconsumo;
    }

    public double getPrecio_cuc_s() {
        return precio_cuc_s;
    }

    public double getPrecio_cuc_sc() {
        return precio_cuc_sc;
    }

    public double getPrecio_cup_s() {
        return precio_cup_s;
    }

    public double getPrecio_cup_sc() {
        return precio_cup_sc;
    }

    public String getServicio() {
        return servicio;
    }

    public String getSobreconsumo() {
        return sobreconsumo;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setId_sobreconsumo(int id_sobreconsumo) {
        this.id_sobreconsumo = id_sobreconsumo;
    }

    public void setPrecio_cuc_s(double precio_cuc_s) {
        this.precio_cuc_s = precio_cuc_s;
    }

    public void setPrecio_cuc_sc(double precio_cuc_sc) {
        this.precio_cuc_sc = precio_cuc_sc;
    }

    public void setPrecio_cup_s(double precio_cup_s) {
        this.precio_cup_s = precio_cup_s;
    }

    public void setPrecio_cup_sc(double precio_cup_sc) {
        this.precio_cup_sc = precio_cup_sc;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setSobreconsumo(String sobreconsumo) {
        this.sobreconsumo = sobreconsumo;
    }

}
