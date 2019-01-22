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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import myclass.utilfecha;

/**
 * @author carlos860920
 *
 */
public class modelo extends SQLite_conexion {

    public Usuario usuario;
    public ArrayList<Usuario> arreglo;
    public ArrayList<TipoServicios> arregloTipoServ;
    public ArrayList<Servicio> arregloServicio;
    public ArrayList<Titulares> arregloTitulares;
    public ArrayList<Organismo> arregloOrganismos;
    public ArrayList<Cliente> arregloCliente;
    public ArrayList<ClienteServicio> arregloClienteServicio;
    public ArrayList<ServicioSobreConsumo> arregloServicioSobreC;

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
     * @return
     */
    public Usuario Autenticar(String usuario, String password) {
        String pssSHA512 = getStringMessageDigest(password, "SHA-512");
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM v_usuario_all WHERE v_usuario_all.usuario = '" + usuario + "' AND v_usuario_all.password = '" + pssSHA512 + "'; ");
            if (resultSet.next()) {
                try {
                    this.usuario = new Usuario(resultSet.getInt("id_usuario"), resultSet.getString("usuario"), resultSet.getString("password"), resultSet.getInt("id_rol"), resultSet.getString("rol"));
                    Date hoy = new Date();
                    String Fis = utilfecha.convierteDateAString(hoy, "yyyy-MM-dd kk:mm:ss");
                    insertar("t_session", "fecha, id_usuario", "'" + Fis + "'," + this.getUsuario().getIdUsuario());
                } catch (SQLException ex) {
                    Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getUsuario();
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
     * @return
     */
    public ComboBoxModel rolCombobox(String tabla) {
        ComboBoxModel cbm;

        resultSet = seleccionarResultSet(tabla);
        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("rol"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo1.toArray());

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
     * @return
     */
    public ComboBoxModel organismoCombobox(String tabla) {
        ComboBoxModel cbm;
        resultSet = seleccionarResultSet(tabla);
        arregloOrganismos = new ArrayList<>();

        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("nombre_organismo"));
                arregloOrganismos.add(new Organismo(resultSet.getInt("id_organismo"), resultSet.getString("nombre_organismo"), resultSet.getString("siglas"), resultSet.getString("abreviatura")));
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

    /**
     * Método para eliminar un tipo de servicio
     *
     * @param selectString
     * @return boolean
     */
    public boolean eliminarTipoServicio(String selectString) {
        return eliminarRegistro("t_tipo_servicio", "id_tipo_servicio = " + buscarTipoServicio(selectString));
    }

    /**
     * Método para mostrar los Servicios
     *
     * @return TableModel modelo con los datos de la tabla
     */
    public TableModel mostrarServicios() {
        resultSet = seleccionarResultSet("v_servicio_all");

        arregloServicio = new ArrayList<>();

        try {
            while (resultSet.next()) {
                arregloServicio.add(new Servicio(resultSet.getInt("id_servicio"), resultSet.getString("descripcion"), resultSet.getString("unidad_medida"), resultSet.getInt("id_tipo_servicio"), resultSet.getString("precio_cuc"), resultSet.getString("precio_cup"), resultSet.getString("tipo_servicio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableModel tm;
        if (arregloServicio.size() > 0) {
            String nombreColumna[] = {"Descripción", "Unidad de Medida", "Tipo de Servicio", "Precio en CUC", "Precio en CUP"};
            tm = new DefaultTableModel(nombreColumna, arregloServicio.size());
            int conuntRow = 0;
            for (int i = 0; i < arregloServicio.size(); i++) {
                tm.setValueAt(arregloServicio.get(i).getDescripcion(), conuntRow, 0);
                tm.setValueAt(arregloServicio.get(i).getUnidad_medida(), conuntRow, 1);
                tm.setValueAt(arregloServicio.get(i).getTipo_servicio(), conuntRow, 2);
                tm.setValueAt(arregloServicio.get(i).getPrecio_cuc(), conuntRow, 3);
                tm.setValueAt(arregloServicio.get(i).getPrecio_cup(), conuntRow, 4);
                conuntRow++;
            }
        } else {
            String nombreColumna[] = {"Descripción"};
            tm = new DefaultTableModel(nombreColumna, 1);
            tm.setValueAt("No existen Servicios para mostrar", 0, 0);
        }
        return tm;
    }

    /**
     * Método que debuelve el model de un <b>jCombobox</b> para tipo de Servicio
     *
     * @param tabla String con el nombre de la tabla que tiene los datos
     * necesatios
     * @return
     */
    public ComboBoxModel tipoServicioCombobox(String tabla) {
        ComboBoxModel cbm;

        resultSet = seleccionarResultSet(tabla);
        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("tipo_servicio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo1.toArray());

        getTipoServicio(); // Esto es para refrescar los tipos de servicio (guardarlos en array)
        return cbm;
    }

    public ComboBoxModel servicioCombobox(String tabla) {
        ComboBoxModel cbm;

        resultSet = seleccionarResultSet(tabla);
        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo1.toArray());

        mostrarServicios();
        //getTipoServicio(); // Esto es para refrescar los tipos de servicio (guardarlos en array)
        return cbm;
    }

    /**
     * Método para la creación de Servicios
     *
     * @param descripcion
     * @param unidad_medida
     * @param idTipoServicio
     * @param precio_cuc
     * @param precio_cup
     * @return boolean, verdadero si se agrega el servicio, falso si no pudo
     * agregarce.
     */
    public boolean agregarServicio(String descripcion, String unidad_medida, int idTipoServicio, String precio_cuc, String precio_cup) {
        return insertar("t_servicio", "descripcion, unidad_medida, id_tipo_servicio, precio_cuc, precio_cup", "'" + descripcion + "','" + unidad_medida + "'," + idTipoServicio + ",'" + precio_cuc + "','" + precio_cup + "'");
    }

    /**
     * Método para actualizar usuario
     *
     * @param id_servicio
     * @param descripcion
     * @param unidad_medida
     * @param idTipoServicio
     * @param precio_cuc
     * @param precio_cup
     * @return boolean
     */
    public boolean actualizarServicio(int id_servicio, String descripcion, String unidad_medida, int idTipoServicio, String precio_cuc, String precio_cup) {
        return this.actualizar("t_servicio", "descripcion = '" + descripcion + "',unidad_medida = '" + unidad_medida + "', id_tipo_servicio = " + idTipoServicio + ",precio_cuc = '" + precio_cuc + "', precio_cup = '" + precio_cup + "'", "id_servicio = " + id_servicio);
    }

    /**
     * Método para eliminar Servicio
     *
     * @param id_servicio
     * @return boolean
     */
    public boolean eliminarServicio(int id_servicio) {
        String donde = "id_servicio = " + id_servicio;
        return this.eliminarRegistro("t_servicio", donde);
    }

    /**
     * Método para la agregar Titular
     *
     * @param titular
     * @param descripcion
     * @param cuenta_bancaria
     * @param codigo_reup
     * @param direccion
     * @param telefono
     * @param fax
     * @param id_organismo
     * @param no_contrato
     * @param fecha_inicio_contrato
     * @param vigencia_contrato
     * @param email
     * @param tipo_moneda
     *
     * @return boolean, verdadero si se agrega el servicio, falso si no pudo
     * agregarce.
     */
    public boolean agregarTitular(String titular, String descripcion, String cuenta_bancaria, String codigo_reup, String direccion, String telefono, String fax, String email, int no_contrato, String fecha_inicio_contrato, int vigencia_contrato, int id_organismo, String tipo_moneda) {
        Date Fid = utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy");
        String Fis = utilfecha.convierteDateAString(Fid, "yyyy-MM-dd");
        return insertar("t_titular", "titular, descripcion, cuenta_bancaria, codigo_reup, direccion, telefono, fax, email, no_contrato, fecha_inicio_contrato, vigencia_contrato, id_organismo, tipo_moneda", "'" + titular + "','" + descripcion + "'," + cuenta_bancaria + ",'" + codigo_reup + "','" + direccion + "','" + telefono + "','" + fax + "','" + email + "'," + no_contrato + ",'" + Fis + "'," + vigencia_contrato + "," + id_organismo + ",'" + tipo_moneda + "'");
    }

    public boolean actualizarTitular(int id_titular, String titular, String descripcion, String cuenta_bancaria, String codigo_reup, String direccion, String telefono, String fax, String email, int no_contrato, String fecha_inicio_contrato, int vigencia_contrato, int id_organismo, String tipo_moneda) {
        Date Fid = utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy");
        String Fis = utilfecha.convierteDateAString(Fid, "yyyy-MM-dd");
        return this.actualizar("t_titular", "titular='" + titular + "', descripcion='" + descripcion + "', cuenta_bancaria=" + cuenta_bancaria + ", codigo_reup='" + codigo_reup + "', direccion='" + direccion + "', telefono='" + telefono + "', fax='" + fax + "', email='" + email + "', no_contrato=" + no_contrato + ", fecha_inicio_contrato= '" + Fis + "', vigencia_contrato=" + vigencia_contrato + ", id_organismo=" + id_organismo + ", tipo_moneda='" + tipo_moneda + "'", "id_titular = " + id_titular);
    }

    /**
     * Método para eliminar Servicio
     *
     * @param id_titular
     * @return boolean
     */
    public boolean eliminarTitular(int id_titular) {
        String donde = "id_titular = " + id_titular;
        return this.eliminarRegistro("t_titular", donde);
    }

    /**
     * Método para mostrar los Titular
     *
     * @return TableModel modelo con los datos de la tabla
     */
    public TableModel mostrarTitulares() {
        resultSet = seleccionarResultSet("v_titulares_all");

        arregloTitulares = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Date fechaIN = utilfecha.convierteStringADate(resultSet.getString("fecha_inicio_contrato"), "yyyy-MM-dd");
                String FeIN = utilfecha.convierteDateAString(fechaIN, "d/MM/yyyy");
                arregloTitulares.add(new Titulares(resultSet.getInt("id_titular"), resultSet.getString("titular"), resultSet.getString("descripcion"), resultSet.getString("cuenta_bancaria"), resultSet.getString("codigo_reup"), resultSet.getString("direccion"), resultSet.getString("telefono"), resultSet.getString("fax"), resultSet.getString("email"), resultSet.getInt("no_contrato"), FeIN, resultSet.getInt("vigencia_contrato"), resultSet.getInt("id_organismo"), resultSet.getString("tipo_moneda"), resultSet.getString("nombre_organismo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableModel tm;
        if (arregloTitulares.size() > 0) {
            String nombreColumna[] = {"No Contrato", "Código Reup", "Titular", "Cuenta", "CUC/CUP", "Fecha Ini Contrato", "Fecha Fin Contrato"};
            tm = new DefaultTableModel(nombreColumna, arregloTitulares.size());
            int conuntRow = 0;
            for (int i = 0; i < arregloTitulares.size(); i++) {
                tm.setValueAt(arregloTitulares.get(i).getNo_contrato(), conuntRow, 0);
                tm.setValueAt(arregloTitulares.get(i).getCodigo_reup(), conuntRow, 1);
                tm.setValueAt(arregloTitulares.get(i).getTitular(), conuntRow, 2);
                tm.setValueAt(arregloTitulares.get(i).getCuenta_bancaria(), conuntRow, 3);
                tm.setValueAt(arregloTitulares.get(i).getTipo_moneda(), conuntRow, 4);
                tm.setValueAt(arregloTitulares.get(i).getFecha_inicio_contrato(), conuntRow, 5);

                Date fecha = utilfecha.convierteStringADate(arregloTitulares.get(i).getFecha_inicio_contrato(), "d/MM/yyyy");

                int vigenciaContrato = arregloTitulares.get(i).getVigencia_contrato();
                Date fecha_fin_contrato = utilfecha.sumaAnnos(fecha, vigenciaContrato);

                tm.setValueAt(utilfecha.convierteDateAString(fecha_fin_contrato), conuntRow, 6);
                conuntRow++;
            }
        } else {
            String nombreColumna[] = {"Titular"};
            tm = new DefaultTableModel(nombreColumna, 1);
            tm.setValueAt("No existen Titulares para mostrar", 0, 0);
        }
        return tm;
    }

    /**
     * Método para la agregar Cliente
     *
     * @param nombre_cliente
     * @param direccion
     * @param id_titular
     * @param telefono
     * @param email
     * @param alcantarillado
     * @param presupuestado
     * @param subsidio
     *
     * @return boolean, verdadero si se agrega el cliente, falso si no pudo
     * agregarce.
     */
    public boolean agregarCliente(String nombre_cliente, String direccion, int id_titular, String telefono, String email, boolean alcantarillado, boolean presupuestado, boolean subsidio) {
        return insertar("t_cliente", "nombre_cliente, direccion, id_titular, telefono, email, alcantarillado, presupuestado, subsidio", "'" + nombre_cliente + "','" + direccion + "'," + id_titular + ",'" + telefono + "','" + email + "','" + alcantarillado + "','" + presupuestado + "','" + subsidio + "'");
    }

    /**
     * Método para actualizar Cliente
     *
     * @param id_cliente
     * @param nombre_cliente
     * @param direccion
     * @param id_titular
     * @param telefono
     * @param email
     * @param alcantarillado
     * @param presupuestado
     * @param subsidio
     *
     * @return
     */
    public boolean actualizarCliente(int id_cliente, String nombre_cliente, String direccion, int id_titular, String telefono, String email, boolean alcantarillado, boolean presupuestado, boolean subsidio) {
        return this.actualizar("t_cliente", "nombre_cliente='" + nombre_cliente + "', direccion='" + direccion + "', id_titular=" + id_titular + ", telefono='" + telefono + "', email='" + email + "', alcantarillado='" + alcantarillado + "', presupuestado='" + presupuestado + "', subsidio='" + subsidio + "'", "id_cliente = " + id_cliente);
    }

    /**
     * Método para eliminar Cliente
     *
     * @param id_cliente
     * @return boolean
     */
    public boolean eliminarCliente(int id_cliente) {
        String donde = "id_cliente = " + id_cliente;
        return this.eliminarRegistro("t_cliente", donde);
    }

    /**
     * Método para mostrar los Titular
     *
     * @return TableModel modelo con los datos de la tabla
     */
    public TableModel mostrarCliente() {
        resultSet = seleccionarResultSet("v_cliente_all");

        arregloCliente = new ArrayList<>();

        try {
            while (resultSet.next()) {
                arregloCliente.add(new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nombre_cliente"), resultSet.getString("direccion"), resultSet.getInt("id_titular"), resultSet.getString("telefono"), resultSet.getString("email"), Boolean.valueOf(resultSet.getString("alcantarillado")), Boolean.valueOf(resultSet.getString("presupuestado")), Boolean.valueOf(resultSet.getString("subsidio")), resultSet.getString("titular")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableModel tm;
        if (arregloCliente.size() > 0) {
            String nombreColumna[] = {"Titular", "Cliente", "Dirección", "Teléfono", "Correo"};
            tm = new DefaultTableModel(nombreColumna, arregloCliente.size());
            int conuntRow = 0;
            for (int i = 0; i < arregloCliente.size(); i++) {
                tm.setValueAt(arregloCliente.get(i).getNombre_titular(), conuntRow, 0);
                tm.setValueAt(arregloCliente.get(i).getNombre_cliente(), conuntRow, 1);
                tm.setValueAt(arregloCliente.get(i).getDireccion(), conuntRow, 2);
                tm.setValueAt(arregloCliente.get(i).getTelefono(), conuntRow, 3);
                tm.setValueAt(arregloCliente.get(i).getEmail(), conuntRow, 4);
                conuntRow++;
            }
        } else {
            String nombreColumna[] = {"Cliente"};
            tm = new DefaultTableModel(nombreColumna, 1);
            tm.setValueAt("No existen Clientes para mostrar", 0, 0);
        }
        return tm;
    }

    public ListModel mostrarClienteServicios(int id_cliente) {
        String donde = "id_cliente = " + id_cliente;
        resultSet = seleccionarResultSet("v_cliente_servicio_all", donde);
        arregloClienteServicio = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>(); //creo un arreglo de String para guardar los servicios (para poder incluirlo en el jList)

        try {
            while (resultSet.next()) {
                arregloClienteServicio.add(new ClienteServicio(resultSet.getInt("id_cliente"), resultSet.getString("nombre_cliente"), resultSet.getInt("id_servicio"), resultSet.getString("descripcion")));
                a.add(resultSet.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JList jl = new JList(a.toArray());//Creo el JList con los valores del arreglo que tiene los servicio del cliente
        //Retorno ListModel con los tipos de servicios
        this.mostrarServicios();
        return jl.getModel();
    }

    public boolean asignarClienteServicio(int id_cliente, int id_servicio) {
        return this.insertar("t_cliente_servicio", "id_cliente, id_servicio", +id_cliente + "," + id_servicio);
    }

    public boolean eliminarClienteServicio(int id_cliente, int id_servicio) {
        String donde = "id_cliente = " + id_cliente + " AND id_servicio = " + id_servicio;
        return this.eliminarRegistro("t_cliente_servicio", donde);
    }

    /**
     * Método para crear los datos del Combo titulares
     *
     * @param tabla
     * @return
     */
    public ComboBoxModel titularesCombobox(String tabla) {
        ComboBoxModel cbm;
        resultSet = seleccionarResultSet(tabla);
        arregloTitulares = new ArrayList<>();

        ArrayList arreglo1 = new ArrayList();
        arreglo1.add("Seleccionar");

        try {
            while (resultSet.next()) {
                arreglo1.add(resultSet.getString("titular") + "  |  " + resultSet.getString("descripcion"));
                Date fechaIN = utilfecha.convierteStringADate(resultSet.getString("fecha_inicio_contrato"), "yyyy-MM-dd");
                String FeIN = utilfecha.convierteDateAString(fechaIN, "d/MM/yyyy");
                arregloTitulares.add(new Titulares(resultSet.getInt("id_titular"), resultSet.getString("titular"), resultSet.getString("descripcion"), resultSet.getString("cuenta_bancaria"), resultSet.getString("codigo_reup"), resultSet.getString("direccion"), resultSet.getString("telefono"), resultSet.getString("fax"), resultSet.getString("email"), resultSet.getInt("no_contrato"), FeIN, resultSet.getInt("vigencia_contrato"), resultSet.getInt("id_organismo"), resultSet.getString("tipo_moneda"), resultSet.getString("nombre_organismo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbm = new DefaultComboBoxModel(arreglo1.toArray());

        return cbm;
    }

    public boolean agregarServMetradoSobreCons(int id_servicio, int id_sobreconsumo) {
        return this.insertar("t_servicio_sobrecons", "id_servicio, id_sobreconsumo", +id_servicio + "," + id_sobreconsumo);
    }
    
    public boolean gregarServMetradoSobreCons(int id_servicio, int id_sobreconsumo){
        String donde = "id_servicio = " + id_servicio + " AND id_sobreconsumo = " + id_sobreconsumo;
        return this.eliminarRegistro("t_servicio_sobrecons", donde);
    }

    public TableModel mostrarServMetradoSobreCons() {
        resultSet = seleccionarResultSet("v_servicio_sobrecons_all");
        arregloServicioSobreC = new ArrayList<>();
        
        try {
            while (resultSet.next()) {
                int id_servicio = resultSet.getInt("id_servicio");
                String servicio = resultSet.getString("servicio");
                double precio_cuc_s = resultSet.getDouble("precio_cuc_s");
                double precio_cup_s = resultSet.getDouble("precio_cup_s");
                int id_sobreconsumo = resultSet.getInt("id_sobreconsumo");
                String sobreconsumo = resultSet.getString("sobreconsumo");
                double precio_cuc_sc = resultSet.getDouble("precio_cuc_sc");
                double precio_cup_sc = resultSet.getDouble("precio_cup_sc");
                arregloServicioSobreC.add(new ServicioSobreConsumo(id_servicio, servicio, precio_cuc_s, precio_cup_s, id_sobreconsumo, sobreconsumo, precio_cuc_sc, precio_cup_sc));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableModel tm;
        if (arregloServicioSobreC.size() > 0) {
            String nombreColumna[] = {"Servicio", "Precio cuc", "Precio cup", "Sobre-Consumo", "Precio cuc", "Precio cup"};
            tm = new DefaultTableModel(nombreColumna, arregloServicioSobreC.size());            
            int conuntRow = 0;
            for (int i = 0; i < arregloServicioSobreC.size(); i++) {
                tm.setValueAt(arregloServicioSobreC.get(i).getServicio(), conuntRow, 0);                
                tm.setValueAt(arregloServicioSobreC.get(i).getPrecio_cuc_s(), conuntRow, 1);
                tm.setValueAt(arregloServicioSobreC.get(i).getPrecio_cup_s(), conuntRow, 2);
                tm.setValueAt(arregloServicioSobreC.get(i).getSobreconsumo(), conuntRow, 3);
                tm.setValueAt(arregloServicioSobreC.get(i).getPrecio_cuc_sc(), conuntRow, 4);
                tm.setValueAt(arregloServicioSobreC.get(i).getPrecio_cup_sc(), conuntRow, 5);
                conuntRow++;
            }
        } else {
            String nombreColumna[] = {"Servicios Metrados | Sobre - Consumo Asociado"};
            tm = new DefaultTableModel(nombreColumna, 1);
            tm.setValueAt("No existen relaciones para mostrar", 0, 0);
        }
        return tm;
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

    public void llenarArregloOrganismo() {
        resultSet = seleccionarResultSet("t_organismo");
        arregloOrganismos = new ArrayList();

        try {
            while (resultSet.next()) {
                arregloOrganismos.add(new Organismo(resultSet.getInt("id_organismo"), resultSet.getString("nombre_organismo"), resultSet.getString("siglas"), resultSet.getString("abreviaturas")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
