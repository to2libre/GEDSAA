package models;

/**
 *
 * @author carlos860920
 */
public class Cliente {

    public int id_cliente;
    public String nombre_cliente;
    public String direccion;
    public int id_titular;
    public String telefono;
    public String email;
    public boolean alcantarillado;
    public boolean presupuestado;
    public boolean subsidio;
    public String nombre_titular;

    public Cliente(int id_cliente, String nombre_cliente, String direccion, int id_titular, String telefono, String email, boolean alcantarillado, boolean presupuestado, boolean subsidio, String nombre_titular) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.direccion = direccion;
        this.id_titular = id_titular;
        this.telefono = telefono;
        this.email = email;
        this.alcantarillado = alcantarillado;
        this.presupuestado = presupuestado;
        this.subsidio = subsidio;
        this.nombre_titular = nombre_titular;
    }

    public Cliente() {
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_titular() {
        return id_titular;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isAlcantarillado() {
        return alcantarillado;
    }

    public boolean isPresupuestado() {
        return presupuestado;
    }

    public boolean isSubsidio() {
        return subsidio;
    }

    public void setAlcantarillado(boolean alcantarillado) {
        this.alcantarillado = alcantarillado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_titular(int id_titular) {
        this.id_titular = id_titular;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setPresupuestado(boolean presupuestado) {
        this.presupuestado = presupuestado;
    }

    public void setSubsidio(boolean subsidio) {
        this.subsidio = subsidio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre_titular() {
        return nombre_titular;
    }

}
