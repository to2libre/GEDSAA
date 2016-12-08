/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Carlos
 */
public class TipoServicios {
    int id_tipo_servicio;
    String tipo_servicio;

    public TipoServicios() {
    }

    public TipoServicios(int id_tipo_servicio, String tipo_servicio) {
        this.id_tipo_servicio = id_tipo_servicio;
        this.tipo_servicio = tipo_servicio;
    }

    public int getId_tipo_srvicio() {
        return id_tipo_servicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setId_tipo_srvicio(int id_tipo_srvicio) {
        this.id_tipo_servicio = id_tipo_srvicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }
}
