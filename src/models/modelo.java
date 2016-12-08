/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author carlos860920
 *
 */
public class modelo extends SQLite_conexion {

    public Usuario usuario;
    public ArrayList<Usuario> arreglo;
    public ArrayList<TipoServicios> arregloTipoServ;

    /**
     * Constructor de la clase <b>modelo</b>
     */
    public modelo() {
    }

    /**
     * Método para la autenticación del usuario.
     *
     * @param usuario Usario a verificar
     * @param password Password a verificar codificada en sha2
     * @return Verdadero si el usuario y la contraseña existe, falso si no
     * existe.
     */
    public Usuario Autenticar(String usuario, String password) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM v_usuario_all WHERE v_usuario_all.usuario = '" + usuario + "' AND v_usuario_all.password = '" + pssSHA512 + "'; ");
            if (resultSet.next()) {
                try {
                    this.usuario = new Usuario(resultSet.getInt("id_usuario"), resultSet.getString("usuario"), resultSet.getString("password"), resultSet.getInt("id_rol"), resultSet.getString("rol"));
                } catch (SQLException ex) {
                    Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this.getUsuario();
    }

    /**
     * Método para mostrar los usuarios del sistema
     *
     * @return TableModel modelo con los datos de la tabla
     */
    public TableModel mostrarUsuarios() {
        resultSet = seleccionarResultSet("v_usuario_all");

        arreglo = new ArrayList<>();

        try {
            while (resultSet.next()) {
                arreglo.add(new Usuario(resultSet.getInt("id_usuario"), resultSet.getString("usuario"), resultSet.getString("password"), resultSet.getInt("id_rol"), resultSet.getString("rol")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        String nombreColumna[] = {"Usuario", "Rol"};
        TableModel tm = new DefaultTableModel(nombreColumna, arreglo.size());
        int conuntRow = 0;
        for (int i = 0; i < arreglo.size(); i++) {
            tm.setValueAt(arreglo.get(i).getUsuario(), conuntRow, 0);
            tm.setValueAt(arreglo.get(i).getRol(), conuntRow, 1);
            conuntRow++;
        }
        return tm;
    }

    /**
     * Método que debuelve el model de un <b>jCombobox</b> para lo Roles
     *
     * @param tabla String con el nombre de la tabla que tiene los datos
     * necesatios
     * @param campos Strign en el formato campo1, campo2, campon, ... con los
     * campos necesarios
     */
    public ComboBoxModel rolCombobox(String tabla) {
        ComboBoxModel cbm;

        resultSet = seleccionarResultSet(tabla);
        ArrayList arreglo = new ArrayList();
        arreglo.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo.add(resultSet.getString("rol"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo.toArray());

        return cbm;
    }

    /**
     * Método para la creación de usuario
     *
     * @param usuario Nombre de usuario a crear
     * @param password Contraseña del usuario a crear
     * @param idRol Id del rol al que pertenece el usuario
     * @return boolean, verdadero si se creo el usuario, falso si no pudo
     * crearse.
     */
    public boolean crearUsuario(String usuario, String password, int idRol) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        return insertar("t_usuario", "usuario, password, id_rol", "'" + usuario + "','" + pssSHA512 + "'," + idRol);
    }

    /**
     * Método para actualizar usuario
     *
     * @param idUsuario
     * @param usuario
     * @param password
     * @param idRol
     * @return boolean
     */
    public boolean actualizarUsuario(int idUsuario, String usuario, String password, int idRol) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        return this.actualizar("t_usuario", "usuario = '" + usuario + "',password = '" + pssSHA512 + "', id_rol = " + idRol, "id_usuario = " + idUsuario);
    }

    /**
     * Método para eliminar usuario
     *
     * @param id_usuario
     * @return boolean
     */
    public boolean eliminarUsuario(int id_usuario) {
        String donde = "id_usuario = " + id_usuario;
        if (id_usuario == 1) {
            return false;
        } else {
            return this.eliminarRegistro("t_usuario", donde);
        }
    }

    /**
     * Métoto para mostrar usuario
     *
     * @return usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Método para retornar una empresa dado el código reup
     *
     * @param codigo_reup
     * @return Empresa
     */
    public Empresa getEmpresa(String codigo_reup) {
        Empresa empresa = null;
        resultSet = this.seleccionarResultSet("v_empresa_all", "codigo_reup = " + codigo_reup);
        try {
            if (resultSet.next()) {
                empresa = new Empresa(resultSet.getInt("id_empresa"), resultSet.getString("nombre_empresa"), resultSet.getString("codigo_reup"), resultSet.getString("titular_cuenta_cup"), resultSet.getString("titular_cuenta_cuc"), resultSet.getString("cuenta_cup"), resultSet.getString("cuenta_cuc"), resultSet.getString("direccion_logo"), resultSet.getInt("id_organismo"), resultSet.getString("telefono"), resultSet.getString("fax"), resultSet.getString("correo_electronico"), resultSet.getString("direccion"), resultSet.getString("nombre_organismo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresa;
    }

    public boolean actualizarEmpresa(String nombre_empresa, String codigo_reup, String titular_cuenta_cup, String titular_cuenta_cuc, String cuenta_cup, String cuenta_cuc, String direccion_logo, int id_organismo, String telefono, String fax, String correo_electronico, String direccion) {
        return this.actualizar("t_empresa", "nombre_empresa='" + nombre_empresa + "',titular_cuenta_cup='" + titular_cuenta_cup + "',titular_cuenta_cuc='" + titular_cuenta_cuc + "',cuenta_cup='" + cuenta_cup + "',cuenta_cuc='" + cuenta_cuc + "',direccion_logo='" + direccion_logo + "',id_organismo=" + id_organismo + ",telefono='" + telefono + "',fax='" + fax + "',correo_electronico='" + correo_electronico + "',direccion='" + direccion + "'", "codigo_reup = " + codigo_reup);
    }

    /**
     * Método que debuelve el model de un <b>jCombobox</b> para los mostrar los
     * organismos
     *
     * @param tabla String con el nombre de la tabla que tiene los datos
     * necesatios
     * @param campos Strign en el formato campo1, campo2, campon, ... con los
     * campos necesarios
     */
    public ComboBoxModel organismoCombobox(String tabla) {
        ComboBoxModel cbm;

        resultSet = seleccionarResultSet(tabla);
        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("nombre_organismo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo1.toArray());

        return cbm;
    }

    /**
     * Método que retorna los detales de control de una UEB
     *
     * @param id_ueb
     * @return <b>DetallesControl</b>
     */
    public DetallesControl getDetallesControl(int id_ueb) {
        DetallesControl dc = null;
        resultSet = this.seleccionarResultSet("v_detalles_control_all", "id_ueb = " + id_ueb);
        try {
            if (resultSet.next()) {
                dc = new DetallesControl(resultSet.getInt("id_ueb"), resultSet.getString("realizado_por"), resultSet.getString("cargo"), resultSet.getInt("aviso_vencimiento_contrato"), resultSet.getInt("meses_promediar_lectura"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dc;
    }

    public boolean agregarOActualizarDetallesControl(int id_ueb, String realizado_por, String cargo, int aviso_vencimiento_contrato, int meses_promediar_lectura) {
        DetallesControl dc = this.getDetallesControl(id_ueb);
        if (dc == null) {
            return this.insertar("t_detalles_control", "id_ueb, realizado_por, cargo, aviso_vencimiento_contrato, meses_promediar_lectura", id_ueb + ",'" + realizado_por + "','" + cargo + "'," + aviso_vencimiento_contrato + "," + meses_promediar_lectura);
        } else {
            return this.actualizar("t_detalles_control", "realizado_por='" + realizado_por + "', cargo='" + cargo + "',aviso_vencimiento_contrato=" + aviso_vencimiento_contrato + ",meses_promediar_lectura =" + meses_promediar_lectura, "id_ueb = " + id_ueb);
        }
    }

    /**
     * Método que retorna los TipoSevicio
     *
     * @return <b>ListModel</b> con los tipos de servicios
     */
    public ListModel getTipoServicio() {
        resultSet = seleccionarResultSet("v_tipo_servicio_all"); //Selecciono los datos de tipos de servicios
        arregloTipoServ = new ArrayList<>(); //creo el objeto para guardar los tipos de servicio        
        ArrayList<String> a = new ArrayList<>(); //creo un arreglo de String para guardar los tipos de serivios (para poder incluirlo en el jList)
        // Llenando los arreglos
        try {
            while (resultSet.next()) {
                arregloTipoServ.add(new TipoServicios(resultSet.getInt("id_tipo_servicio"), resultSet.getString("tipo_servicio")));
                a.add(resultSet.getString("tipo_servicio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JList jl = new JList(a.toArray());//Creo el JList con los valores del arreglo que tiene los tipos de servicio
        //Retorno ListModel con los tipos de servicios
        return jl.getModel();
    }

    /**
     * Método para agregar un Tipo de Servicio
     *
     * @param tipo_servicio
     * @return boolean
     */
    public boolean agregarTipoServicio(String tipo_servicio, String selectString) {
        if (selectString == null) {
            if (buscarTipoServicio(tipo_servicio) != -1) {
                return false;
            } else {
                return insertar("t_tipo_servicio", "tipo_servicio", "'" + tipo_servicio + "'");
            }
        } else {
            if (buscarTipoServicio(tipo_servicio) != -1) {
                return false;
            } else {
                return actualizar("t_tipo_servicio", "tipo_servicio = '" + tipo_servicio + "'", "id_tipo_servicio = " + buscarTipoServicio(selectString));
            }
        }
    }

    /**
     * @param tipo_servicio
     * @return boolean Método que dado un tipo de servicio busca en todos los
     * tipos de servicios que hay en la Bse de datos y retorna <b>verdadero</b>
     * si existe uno igual, <b>falso</b> de forma contraria
     */
    public int buscarTipoServicio(String tipo_servicio) {
        for (int i = 0; i < arregloTipoServ.size(); i++) {
            if (arregloTipoServ.get(i).getTipo_servicio().toUpperCase().equals(tipo_servicio.toUpperCase())) {
                return arregloTipoServ.get(i).getId_tipo_srvicio();
            }
        }
        return -1;
    }

    public boolean eliminarTipoServicio(String selectString) {
        return eliminarRegistro("t_tipo_servicio", "id_tipo_servicio = " + buscarTipoServicio(selectString));
    }

    /**
     *
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     *
     * @param message texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     * SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public static String getStringMessageDigest(String message, String algorithm) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest" + ex.getMessage());
        }
        return toHexadecimal(digest);
    }

    /**
     * *
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }
}
