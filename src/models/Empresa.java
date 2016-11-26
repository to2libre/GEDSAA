/*
 * Clase para la entidad empresa
 */
package models;

/**
 * @author carlos860920
 */
public class Empresa {

    int id_empresa;
    String nombre_empresa;
    String codigo_reup;
    String titular_cuenta_cup;
    String titular_cuenta_cuc;
    String cuenta_cup;
    String cuenta_cuc;
    String direccion_logo;
    int id_organismo;
    String telefono;
    String fax;
    String correo_electronico;
    String direccion;
    String organismo;

    public Empresa() {
    }

    public Empresa(int id_empresa, String nombre_empresa, String codigo_reup, String titular_cuenta_cup, String titular_cuenta_cuc, String cuenta_cup, String cuenta_cuc, String direccion_logo, int id_organismo, String telefono, String fax, String correo_electronico, String direccion, String organismo) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.codigo_reup = codigo_reup;
        this.titular_cuenta_cup = titular_cuenta_cup;
        this.titular_cuenta_cuc = titular_cuenta_cuc;
        this.cuenta_cup = cuenta_cup;
        this.cuenta_cuc = cuenta_cuc;
        this.direccion_logo = direccion_logo;
        this.id_organismo = id_organismo;
        this.telefono = telefono;
        this.fax = fax;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
        this.organismo = organismo;
    }

    public String getCodigo_reup() {
        return codigo_reup;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public String getCuenta_cuc() {
        return cuenta_cuc;
    }

    public String getCuenta_cup() {
        return cuenta_cup;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDireccion_logo() {
        return direccion_logo;
    }

    public String getFax() {
        return fax;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public int getId_organismo() {
        return id_organismo;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public String getOrganismo() {
        return organismo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTitular_cuenta_cuc() {
        return titular_cuenta_cuc;
    }

    public String getTitular_cuenta_cup() {
        return titular_cuenta_cup;
    }

    public void setCodigo_reup(String codigo_reup) {
        this.codigo_reup = codigo_reup;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public void setCuenta_cuc(String cuenta_cuc) {
        this.cuenta_cuc = cuenta_cuc;
    }

    public void setCuenta_cup(String cuenta_cup) {
        this.cuenta_cup = cuenta_cup;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDireccion_logo(String direccion_logo) {
        this.direccion_logo = direccion_logo;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setId_organismo(int id_organismo) {
        this.id_organismo = id_organismo;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTitular_cuenta_cuc(String titular_cuenta_cuc) {
        this.titular_cuenta_cuc = titular_cuenta_cuc;
    }

    public void setTitular_cuenta_cup(String titular_cuenta_cup) {
        this.titular_cuenta_cup = titular_cuenta_cup;
    }
}
