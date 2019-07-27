package models;

/**
 *
 * @author carlos860920
 */
public class MetroMarca {
    int id_metro_marca;
    String marca;

    public MetroMarca() {
    }

    public MetroMarca(int id_metro_marca, String marca) {
        this.id_metro_marca = id_metro_marca;
        this.marca = marca;
    }

    public int getId_metro_marca() {
        return id_metro_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setId_metro_marca(int id_metro_marca) {
        this.id_metro_marca = id_metro_marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
