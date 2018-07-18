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
public class Titulares {

    int id_titular;
    String titular;
    String descripcion;
    String cuenta_bancaria;
    String codigo_reup;
    String direccion;
    String telefono;
    String fax;
    String email;
    int no_contrato;
    String fecha_inicio_contrato;
    int vigencia_contrato;
    int id_organismo;
    String tipo_moneda;
    String nombre_organismo;

    public Titulares() {
    }

    public Titulares(int id_titular, String titular, String descripcion, String cuenta_bancaria, String codigo_reup, String direccion, String telefono, String fax, String email, int no_contrato, String fecha_inicio_contrato, int vigencia_contrato, int id_organismo, String tipo_moneda, String nombre_organismo) {
        this.id_titular = id_titular;
        this.titular = titular;
        this.descripcion = descripcion;
        this.cuenta_bancaria = cuenta_bancaria;
        this.codigo_reup = codigo_reup;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fax = fax;
        this.email = email;
        this.no_contrato = no_contrato;
        this.fecha_inicio_contrato = fecha_inicio_contrato;
        this.vigencia_contrato = vigencia_contrato;
        this.id_organismo = id_organismo;
        this.tipo_moneda = tipo_moneda;
        this.nombre_organismo = nombre_organismo;
    }

    public String getCodigo_reup() {
        return codigo_reup;
    }

    public String getCuenta_bancaria() {
        return cuenta_bancaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public String getFecha_inicio_contrato() {
        return fecha_inicio_contrato;
    }

    public int getId_organismo() {
        return id_organismo;
    }

    public int getId_titular() {
        return id_titular;
    }

    public int getNo_contrato() {
        return no_contrato;
    }

    public String getNombre_organismo() {
        return nombre_organismo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTitular() {
        return titular;
    }

    public int getVigencia_contrato() {
        return vigencia_contrato;
    }

    public String getTipo_moneda() {
        return tipo_moneda;
    }

    public void setCodigo_reup(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public void setCuenta_bancaria(String cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setFecha_inicio_contrato(String fecha_inicio_contrato) {
        this.fecha_inicio_contrato = fecha_inicio_contrato;
    }

    public void setId_organismo(int id_organismo) {
        this.id_organismo = id_organismo;
    }

    public void setId_titular(int id_titular) {
        this.id_titular = id_titular;
    }

    public void setNo_contrato(int no_contrato) {
        this.no_contrato = no_contrato;
    }

    public void setNombre_organismo(String nombre_organismo) {
        this.nombre_organismo = nombre_organismo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo_moneda(String tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setVigencia_contrato(int vigencia_contrato) {
        this.vigencia_contrato = vigencia_contrato;
    }

}
