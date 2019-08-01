package models;

/**
 *
 * @author to2lobre
 */
public class MetroModelos {

    int id_metro_modelo;
    String modelo;
    int id_marca;
    String marca;

    public MetroModelos() {
    }

    public MetroModelos(int id_metro_modelo, String modelo, int id_marca, String marca) {
        this.id_metro_modelo = id_metro_modelo;
        this.modelo = modelo;
        this.id_marca = id_marca;
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public int getId_metro_modelo() {
        return id_metro_modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public void setId_metro_modelo(int id_metro_modelo) {
        this.id_metro_modelo = id_metro_modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
