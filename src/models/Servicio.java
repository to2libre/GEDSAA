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
public class Servicio {

    int id_servicio;
    String descripcion;
    String unidad_medida;
    int id_tipo_servicio;
    String precio_cuc;
    String precio_cup;
    String tipo_servicio;

    public Servicio() {
    }

    public Servicio(int id_servicio, String descripcion, String unidad_medida, int id_tipo_servicio, String precio_cuc, String precio_cup, String tipo_servicio) {
        this.id_servicio = id_servicio;
        this.descripcion = descripcion;
        this.unidad_medida = unidad_medida;
        this.id_tipo_servicio = id_tipo_servicio;
        this.precio_cuc = precio_cuc;
        this.precio_cup = precio_cup;
        this.tipo_servicio = tipo_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public int getId_tipo_servicio() {
        return id_tipo_servicio;
    }

    public String getPrecio_cuc() {
        return precio_cuc;
    }

    public String getPrecio_cup() {
        return precio_cup;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setId_tipo_servicio(int id_tipo_servicio) {
        this.id_tipo_servicio = id_tipo_servicio;
    }

    public void setPrecio_cuc(String precio_cuc) {
        this.precio_cuc = precio_cuc;
    }

    public void setPrecio_cup(String precio_cup) {
        this.precio_cup = precio_cup;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

}
