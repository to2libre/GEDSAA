/*
 * Clase para la entidad organismo
 */
package models;

/**
 *
 * @author carlos860920
 */
public class Organismo {

    int id_organismo;
    String nombre_organismo;
    String siglas;
    String abreviatura;

    public Organismo() {
    }

    public Organismo(int id_organismo, String nombre_organismo, String siglas, String abreviatura) {
        this.id_organismo = id_organismo;
        this.nombre_organismo = nombre_organismo;
        this.siglas = siglas;
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public int getId_organismo() {
        return id_organismo;
    }

    public String getNombre_organismo() {
        return nombre_organismo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setId_organismo(int id_organismo) {
        this.id_organismo = id_organismo;
    }

    public void setNombre_organismo(String nombre_organismo) {
        this.nombre_organismo = nombre_organismo;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
}
