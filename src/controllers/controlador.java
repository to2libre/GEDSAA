package controllers;

import configuracion.config;
import factestatal.ficheros.About;
import factestatal.ficheros.DatosEmpresa;
import factestatal.ficheros.DetallesDeControl;
import factestatal.ficheros.TipoServicios;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.DetallesControl;
import models.Empresa;
import models.Usuario;
import models.modelo;
import views.factestatal.Autenticar;
import views.factestatal.Principal;
import views.factestatal.ficheros.Users;
import views.factestatal.ficheros.cambiarPassword;

/**
 * Clase Controladora del sistema, acá esta toda la parte controladora de nustro
 * sistema
 *
 * @author carlos860920
 */
public class controlador implements ActionListener {

    //Si da error quitar el tipo final del atributo view, viewA y model
    private final Principal view;
    private final Autenticar viewA;
    private final modelo model;

    //Variables para lis jInternalFrame
    private cambiarPassword cP;
    private Users users;
    private About about;
    private DatosEmpresa datosEmpresa;
    private DetallesDeControl detallesControl;
    private TipoServicios tipoServicio;

    //Variables de modelos
    public Usuario usuario;

    //Variables de ambito global para el trabajo con los formularios interiores
    public String msg;// Variable que guarda los mensages del sistema, sean informacion o error
    public int idUsuario; // id del usuario que se desea modificar en getionar usuario
    public Image imagen; // variable para el logo de la empresa  
    public String codigo_reup; //codigo reup de la empresa que esta seleccionada (configurado en el xml de configuracion)
    public int id_ueb; // Id de la UEB con la que se esta trabando, este id se carga de (Achivo de configuracion XML  de configuracion)
    File ficheroSeleccionado;
    Empresa e;

    /**
     * Método Constructor de la clase controldora
     *
     * @param vista Vista principal
     * @param vistaA Vista de autenticación
     * @param modelo Modelo del sistema
     */
    public controlador(Principal vista, Autenticar vistaA, modelo modelo) {
        this.view = vista;
        this.viewA = vistaA;
        this.model = modelo;
        form_autenticar();
        idUsuario = -1;

        config conf = new config();//Clase para la lectura y escritura del archivo de configuración
        conf.leerXml();//Método para leer el archivo de configuración
        this.codigo_reup = conf.getCodigo_reup();//Guardo el codigo reup en la variable ocn mismo nonbre
        this.id_ueb = conf.getId_ueb();//Guardo el id_ueb en la variable con mismo nombre
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Captura en String el comando accionado por el usuario
        String comando = e.getActionCommand();
        //Controlar acciones del usuario en las condiciones a continuación
        switch (comando) {
            case "Autenticar":
                if (this.validarUsuario(this.viewA.usuarioTextField.getText(), this.viewA.passwordTextField.getText())) {
                    this.usuario = this.model.Autenticar(this.viewA.usuarioTextField.getText(), this.viewA.passwordTextField.getText());
                    this.view.usuarioAutenticadoLabel.setText(" " + this.viewA.usuarioTextField.getText());
                    this.iniciar();
                } else {
                    JOptionPane.showMessageDialog(this.view, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Cancelar":
                this.cerrar_sistema(); // Si el usuario escogio cancelar se cierra el sistema.
                break;
            case "about":
                this.formAcerca(); // mostrar jInternalFrame Acerca
                break;
            case "Salir del Sistema":
                this.cerrar_sistema(); // Si el usuario escogio cancelar se cierra el sistema.
                break;
            case "Cambiar password": // Mostrar el jInternalFrame de cambio de contraseña                
                this.formCambiarPassword();
                break;
            case "cambiarPasswordAccion": // Accion del boton de combio de contraseña
                this.cambiarPasswordAccion();
                break;
            case "Usuario": // Mostrar el jInternalFrame de usuario
                this.formUsuario();
                break;
            case "crearUsuario": // Accion del boton crear usuario en el formulario Users
                this.usuarioAcction("crear");
                break;
            case "cancelarUsuario": // Accion del boton cancelar en el formulario Usuario
                this.usuarioAcction("cancelar");
                break;
            case "modificarUsuario": // Accion del boton modificar usuario en el formulario Users
                this.usuarioAcction("modificar");
                break;
            case "eliminarUsuario": // Accion del boton eliminar usuario en el formulario Users
                this.usuarioAcction("eliminar");
                break;
            case "datosEmpresaForm":
                this.formDatosEmpresa(); // Mostrar el jInternalFrame de Datos de la Empresa
                break;
            case "buscarLogotipoButton":
                datosEmpresa.logotipoFileChooser.setDialogTitle("Seleccionar logo...");
                FileFilter ff = new FileNameExtensionFilter("Archivos de Imagenes en JPG", "jpg");
                datosEmpresa.logotipoFileChooser.setFileFilter(ff);
                datosEmpresa.logotipoFileChooser.setAcceptAllFileFilterUsed(false);
                datosEmpresa.logotipoFileChooser.showOpenDialog(datosEmpresa);
                this.ficheroSeleccionado = null;//Creo el objeto File para luego asignarle el fichero seleccionado
                ficheroSeleccionado = datosEmpresa.logotipoFileChooser.getSelectedFile();
                this.copiarLogo(ficheroSeleccionado, "logos_temp");
                break;
            case "crearEmpresa":// Accion para crear empresa
                this.datosEmpresaAcction("crear", null);
                break;
            case "modificarEmpresa": // Accion para modificar Empresa
                String direccionLogo = "logos";
                this.datosEmpresaAcction("modificar", direccionLogo);
                break;
            case "eliminarEmpresa": //Accion para eliminar empresa
                this.datosEmpresaAcction("eliminar", null);
                break;
            case "tipoServiciosForm": //Accion para mostrar jinternal frame TipoSrvicio
                this.formTipoServicio();
                break;
            case "agrearModificarTipoSerivio": //Accion para agrear o modificar tipo de servicio
                this.tipoServiosAcction("agregarModifiar");
                break;
            case "modificarTipoServicio": //Accion para modificar el tipo de Servicio
                this.tipoServicio.tipoServicioTextField.setText((String) this.tipoServicio.modificarEliminarList.getSelectedValue());
                break;
            case "eliminarTipoServicio": //Accion para eliminar tipo de Servicio                
                this.tipoServiosAcction("eliminar");
                break;
            case "formDetallesControl": // Accion para mostrar jInternarFrame Detalles de control
                this.formDetallesControl();
                break;
            case "aceptarDetallesControl": //Acciona para actualir los detalles de control
                this.detallesControlAcction("actualizar");
                break;
            case "Cancelar Accion":
                try {
                    cerrar(this.view.desktopPane.getSelectedFrame());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Cerrar":
                cerrar_todo();
                break;
        }
    }

    //-----------------------------------------------------------------------------------
    //Metodos para gestionar coportamiento de las vistas y capturar eventos de los formularios
    //Capturar eventos de los formularios (FORM)
    //-----------------------------------------------------------------------------------
    /**
     * Método para controlar el formulario <b>Principal</b>
     */
    private void iniciar() {
        this.viewA.setVisible(false);
        this.view.setVisible(true);
        this.view.setTitle("GEDSAA");
        this.view.setLocationRelativeTo(null);//centrado en pantalla        
        //Se añade las acciones a los controles del formulario padre        
        this.view.datosEmpresaMenuItem.setActionCommand("datosEmpresaForm"); //Datos de Empresa
        this.view.tipoServiciosMenuItem.setActionCommand("tipoServiciosForm"); //TipoServicios
        this.view.detallesControlMenuItem.setActionCommand("formDetallesControl"); //Formulario Detalles de Control
        this.view.usersMenuItem.setActionCommand("Usuario"); //Gestionar Usuario
        this.view.cambiarContrasennaMenuItem.setActionCommand("Cambiar password"); //Cambiar contraseña        
        this.view.exitMenuItem.setActionCommand("Salir del Sistema"); //Salir del sistema        
        this.view.aboutMenuItem.setActionCommand("about"); // Acerca de nosotros
        //Se pone a escuchar las acciones del usuario
        this.view.datosEmpresaMenuItem.addActionListener(this); //Datos Empresa
        this.view.tipoServiciosMenuItem.addActionListener(this); //TipoServicios
        this.view.detallesControlMenuItem.addActionListener(this); //DetallesControl
        this.view.usersMenuItem.addActionListener(this); //Gestionar Usuario
        this.view.cambiarContrasennaMenuItem.addActionListener(this); //Cambiar contraseñas
        this.view.exitMenuItem.addActionListener(this); //Salir del sistema        
        this.view.aboutMenuItem.addActionListener(this); // Acerca de nosotros
    }

    /**
     * Método para controlar el formulario <b>Autenticar</b>
     */
    private void form_autenticar() {
        this.viewA.setTitle("GEDSAA - Autenticar");//Poniendo título al formulario
        this.viewA.setLocationRelativeTo(null);//Centrado en pantalla
        this.viewA.setVisible(true);//Mostrar el formulario
        //Se agrega las acciones al formulario de Autenticación
        this.viewA.aceptarButton.setActionCommand("Autenticar");
        this.viewA.cancelarButton.setActionCommand("Cancelar");
        this.viewA.usuarioTextField.setActionCommand("Autenticar");
        this.viewA.passwordTextField.setActionCommand("Autenticar");
        //Se pone a la escucha de las acciones del Usuario
        viewA.aceptarButton.addActionListener(this);
        viewA.cancelarButton.addActionListener(this);
        viewA.usuarioTextField.addActionListener(this);
        viewA.passwordTextField.addActionListener(this);
    }

    /**
     * Método para controlar el formulario <b>cambiarPsasword</b>
     */
    private void formCambiarPassword() {
        this.cP = new cambiarPassword();
        this.cP.setTitle("Cambiar contraseña...");

        if (!restaurarFormulario(this.cP.getTitle())) {
            this.view.desktopPane.add(cP);
            this.cP.setLocation(centradoXY(cP));
            this.cP.setVisible(true);
            //Se agrega las acciones al formulario de Autenticación
            this.cP.contrasennaActualPasswordField.setActionCommand("cambiarPasswordAccion");
            this.cP.nuevaContrasennaPasswordField.setActionCommand("cambiarPasswordAccion");
            this.cP.repetirContrasennaNuevaPasswordField.setActionCommand("cambiarPasswordAccion");
            this.cP.cambiarContrasennaButton.setActionCommand("cambiarPasswordAccion");
            this.cP.cancelarButton.setActionCommand("Cancelar Accion");
            //Se pone a la escucha de las acciones del Usuario        
            this.cP.contrasennaActualPasswordField.addActionListener(this);
            this.cP.nuevaContrasennaPasswordField.addActionListener(this);
            this.cP.repetirContrasennaNuevaPasswordField.addActionListener(this);
            this.cP.cancelarButton.addActionListener(this);
            this.cP.cambiarContrasennaButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>Users</b>
     */
    private void formUsuario() {
        this.users = new Users();
        this.users.setTitle("Gestion de Usuarios...");

        if (!restaurarFormulario(this.users.getTitle())) {
            this.view.desktopPane.add(users);
            users.setLocation(centradoXY(users));
            users.setVisible(true);
            this.usuarioAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.users.CrearButton.setActionCommand("crearUsuario");
            this.users.usuarioTextField.setActionCommand("crearUsuario");
            this.users.PasswordPasswordField.setActionCommand("crearUsuario");
            this.users.rePasswordPasswordField.setActionCommand("crearUsuario");
            this.users.cancelarButton.setActionCommand("cancelarUsuario");
            this.users.modificarButton.setActionCommand("modificarUsuario");
            this.users.eliminarButton.setActionCommand("eliminarUsuario");
            //Se pone a la escucha de las acciones del Usuario   
            this.users.CrearButton.addActionListener(this);
            this.users.usuarioTextField.addActionListener(this);
            this.users.PasswordPasswordField.addActionListener(this);
            this.users.rePasswordPasswordField.addActionListener(this);
            this.users.cancelarButton.addActionListener(this);
            this.users.modificarButton.addActionListener(this);
            this.users.eliminarButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>DatosEmpresa</b>
     */
    private void formDatosEmpresa() {
        datosEmpresa = new DatosEmpresa();
        datosEmpresa.setTitle("Datos de la Empresa...");

        if (!restaurarFormulario(this.datosEmpresa.getTitle())) {
            this.view.desktopPane.add(datosEmpresa);
            datosEmpresa.setLocation(centradoXY(datosEmpresa));
            datosEmpresa.setVisible(true);
            datosEmpresaAcction("visualizar", null);
            //Se agrega las acciones al formulario de Usuario        
            this.datosEmpresa.agregarModificarButton.setActionCommand("modificarEmpresa");
            this.datosEmpresa.cancelarButton.setActionCommand("Cancelar Accion");
            this.datosEmpresa.buscarLogotipoButton.setActionCommand("buscarLogotipoButton");
            //Se pone a la escucha de las acciones del Usuario   
            this.datosEmpresa.agregarModificarButton.addActionListener(this);
            this.datosEmpresa.cancelarButton.addActionListener(this);
            this.datosEmpresa.buscarLogotipoButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>Detalles de Control</b>
     */
    public void formDetallesControl() {
        detallesControl = new DetallesDeControl();
        detallesControl.setTitle("Detalles de Control...");

        if (!restaurarFormulario(this.detallesControl.getTitle())) {
            this.view.desktopPane.add(detallesControl);
            detallesControl.setLocation(centradoXY(detallesControl));
            detallesControl.setVisible(true);
            detallesControlAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.detallesControl.aceptarButton.setActionCommand("aceptarDetallesControl");
            this.detallesControl.cancelarButton.setActionCommand("Cancelar Accion");
            this.detallesControl.realizadoPorTextField.setActionCommand("aceptarDetallesControl");
            this.detallesControl.cargoTextField.setActionCommand("aceptarDetallesControl");
            this.detallesControl.avisoVencimientoContratoFormattedTextField.setActionCommand("aceptarDetallesControl");
            this.detallesControl.mesesPromediarLecturaFormattedTextField.setActionCommand("aceptarDetallesControl");
            //Se pone a la escucha de las acciones del Usuario
            this.detallesControl.aceptarButton.addActionListener(this);
            this.detallesControl.cancelarButton.addActionListener(this);
            this.detallesControl.realizadoPorTextField.addActionListener(this);
            this.detallesControl.cargoTextField.addActionListener(this);
            this.detallesControl.avisoVencimientoContratoFormattedTextField.addActionListener(this);
            this.detallesControl.mesesPromediarLecturaFormattedTextField.addActionListener(this);
        }
    }

    /**
     * Método para restaurar la posicion inicial de un jInternalFrame dedo el
     * titulo del mismo
     *
     * @param tituloJInternalFrame
     * @return boolean
     */
    public boolean restaurarFormulario(String tituloJInternalFrame) {
        JInternalFrame[] activos = this.view.desktopPane.getAllFrames();
        int contador = 0, numactivo = -1;
        for (int i = 0; i < activos.length; i++) {
            if (activos[i].getTitle().equals(tituloJInternalFrame)) {
                contador++;
                numactivo = i;
            }
        }
        if (contador > 0) {
            try {
                activos[numactivo].setLocation(centradoXY(activos[numactivo]));// Pone el jInternalFrame en la posicion inicial
                activos[numactivo].setSelected(true);// Pone el jInternalFrame como selecionado
                activos[numactivo].pack();//Pone el jInternalFrame Restaurado si esta minimizado
            } catch (PropertyVetoException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para controlar el formulario <b>TipoServicio</b>
     */
    @SuppressWarnings("empty-statement")
    public void formTipoServicio() {
        this.tipoServicio = new TipoServicios();
        this.tipoServicio.setTitle("Tipos de Servicios...");

        if (!restaurarFormulario(this.tipoServicio.getTitle())) {
            this.view.desktopPane.add(this.tipoServicio);
            this.tipoServicio.setLocation(centradoXY(this.tipoServicio));
            this.tipoServicio.setVisible(true);
            this.tipoServiosAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.tipoServicio.tipoServicioTextField.setActionCommand("agrearModificarTipoSerivio");
            this.tipoServicio.agregarModificarButton.setActionCommand("agrearModificarTipoSerivio");
            this.tipoServicio.modificarButton.setActionCommand("modificarTipoServicio");
            this.tipoServicio.eliminarButton.setActionCommand("eliminarTipoServicio");
            //Se pone a la escucha de las acciones del Usuario
            this.tipoServicio.tipoServicioTextField.addActionListener(this);
            this.tipoServicio.agregarModificarButton.addActionListener(this);
            this.tipoServicio.modificarButton.addActionListener(this);
            this.tipoServicio.eliminarButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>About</b>
     */
    public void formAcerca() {
        about = new About();
        this.view.desktopPane.add(about);
        about.setLocation(centradoXY(about));
        about.setVisible(true);
    }

    //-----------------------------------------------------------------------------------
    //Metodos Para conrolar acciones de los formularios
    //-----------------------------------------------------------------------------------
    /**
     * Método para la validación de usuario
     *
     * @param usuario Nombre de usuario del sistema
     * @param passwd Contraseña del usuario
     * @return boolean, verdadero si pasa,falso si existe algun error, ademas
     * guarda en la variable <b>msg</b> el mensage a mostrar
     */
    public boolean validarUsuario(String usuario, String passwd) {
        this.msg = "";
        if (usuario.isEmpty()) {
            this.msg += "Usuario: Campo Nulo \n";
        }
        if (passwd.isEmpty()) {
            this.msg += "Contraseña: Campo Nula \n";
        }
        if (msg.isEmpty()) {
            if (this.model.Autenticar(usuario, passwd) == null) {
                this.msg += "Usuario o Contraseña incorrecta \n";
            }
        }
        return this.msg.isEmpty();
    }

    /**
     * Método para la creación de usuario
     *
     * @param usuario Nombre del usuario a crear
     * @param passwd Password del usuario a crear
     * @param rePassword Password nuevamente del usuario a crear
     * @param idRol Id del rol del usuario a crear
     */
    public void crearUsuario(String usuario, String passwd, String rePassword, int idRol) {
        this.msg = "";
        if (usuario.isEmpty()) {
            this.msg += "Usuario: Campo Nulo \n";
        }
        if (passwd.isEmpty()) {
            this.msg += "Contraseña: Campo Nula \n";
        }
        if (rePassword.isEmpty()) {
            this.msg += "Repetir Contraseña: Campo Nula \n";
        }
        if (idRol == 0) {
            this.msg += "Rol: Seleccionar un rol \n";
        }
        if (msg.isEmpty()) {
            if (!passwd.equals(rePassword)) {
                msg += "Las Contraseña no coinciden \n";
            }
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.users, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.model.crearUsuario(usuario, passwd, idRol)) {
                JOptionPane.showMessageDialog(this.users, "Se ha creado el usuario correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.msg = "El usuario ya está en el sistema";
                JOptionPane.showMessageDialog(this.users, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para la modificación de usuario
     *
     * @param usuario Nombre del usuario a crear
     * @param passwd Password del usuario a crear
     * @param rePassword Password nuevamente del usuario a crear
     * @param idRol Id del rol del usuario a crear
     */
    public void modificarUsuario(String usuario, String passwd, String rePassword, int idRol) {
        this.msg = "";
        if (usuario.isEmpty()) {
            this.msg += "Usuario: Campo Nulo \n";
        }
        if (passwd.isEmpty()) {
            this.msg += "Contraseña: Campo Nula \n";
        }
        if (rePassword.isEmpty()) {
            this.msg += "Repetir Contraseña: Campo Nula \n";
        }
        if (idRol == 0) {
            this.msg += "Rol: Seleccionar un rol \n";
        }
        if (msg.isEmpty()) {
            if (!passwd.equals(rePassword)) {
                msg += "Las Contraseña no coinciden \n";
            }
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.users, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.model.actualizarUsuario(idUsuario, usuario, passwd, idRol)) {
                JOptionPane.showMessageDialog(this.users, "Se ha actualizado el usuario correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.msg = "El usuario ya está en el sistema";
                JOptionPane.showMessageDialog(this.users, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para el trabajo con el formulario de Usuario
     *
     * @param accion String con la accion a realizar
     */
    private void usuarioAcction(String accion) {
        int row = -1;
        switch (accion) {
            case "crear":
                // Capturar datos del formulario
                String Usuario = this.users.usuarioTextField.getText();
                String Password = this.users.PasswordPasswordField.getText();
                String rePassword = this.users.rePasswordPasswordField.getText();
                int idRol = this.users.rolComboBox.getSelectedIndex();
                // idUsuario es igual a -1 para crearlo o es igual al id del usario para modificarlo o eliminarlo
                if (idUsuario == -1) {
                    this.crearUsuario(Usuario, Password, rePassword, idRol);
                } else {
                    this.modificarUsuario(Usuario, Password, rePassword, idRol);
                }
                // Si se crea o se ctualiza el usuario entra
                if (this.msg.isEmpty()) {
                    // Limpiar todos los escaques del formulario
                    this.users.usuarioTextField.setText("");
                    this.users.PasswordPasswordField.setText("");
                    this.users.rePasswordPasswordField.setText("");
                    this.users.rolComboBox.setSelectedIndex(0);
                    this.users.CrearButton.setText("Crear");
                    this.users.contenedorTabbedPane.setEnabledAt(0, true);
                    // Refrescar los datos a mostrar en el formulario de visualización
                    this.usuarioAcction("visualizar");
                }
                break;
            case "modificar":
                row = this.users.usuariosTable.getSelectedRow();
                if (row != -1) {
                    idUsuario = this.model.arreglo.get(row).getIdUsuario();
                    this.users.usuarioTextField.setText(this.model.arreglo.get(row).getUsuario());
                    this.users.rolComboBox.setSelectedIndex(this.model.arreglo.get(row).getIdRol());
                    this.users.contenedorTabbedPane.setSelectedIndex(1);
                    this.users.CrearButton.setText("Modificar");
                    this.users.contenedorTabbedPane.setEnabledAt(0, false);
                }
                break;
            case "eliminar":
                row = this.users.usuariosTable.getSelectedRow();
                if (row != -1) {
                    idUsuario = this.model.arreglo.get(row).getIdUsuario();
                    String us = this.model.arreglo.get(row).getUsuario();
                    int acc = JOptionPane.showConfirmDialog(this.users, "Estas seguro de eliminar a " + us, "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        if (this.model.eliminarUsuario(idUsuario)) {
                            JOptionPane.showMessageDialog(this.users, "Se ha eliminado el usuario correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.users, "Error al intentar eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.users, "Seleccione un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                idUsuario = -1;
                this.usuarioAcction("visualizar");
                break;
            case "cancelar":
                this.users.usuarioTextField.setText("");
                this.users.PasswordPasswordField.setText("");
                this.users.rePasswordPasswordField.setText("");
                this.users.rolComboBox.setSelectedIndex(0);
                this.users.contenedorTabbedPane.setEnabledAt(0, true);
                // Refrescar los datos a mostrar en el formulario de visualización
                this.usuarioAcction("visualizar");
                break;
            default:
                this.users.contenedorTabbedPane.setSelectedIndex(0);
                users.rolComboBox.setModel(this.model.rolCombobox("t_rol"));
                users.usuariosTable.setModel(this.model.mostrarUsuarios());
                break;
        }
    }

    /**
     * Método para cerrar el sistema
     */
    private void cerrar_sistema() {
        System.exit(0);
    }

    /**
     * Método para cerrar un JInternalFrame
     */
    private void cerrar(JInternalFrame jif) throws PropertyVetoException {
        jif.setClosed(true);
    }

    /**
     * Método para cerrar todos los </>jInternalFrame</b> que esten abiertos
     */
    private void cerrar_todo() {
        JInternalFrame[] activos = this.view.desktopPane.getAllFrames();
        int i = 0;
        while (i < activos.length) {
            try {
                cerrar(activos[i]);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    /**
     * Método que dado un <b>JInternalFrame</b> calcula la posicion de centrado
     * respecto a su contenedor, retorna las coordenadas en una variable de tipo
     * POINT
     */
    private Point centradoXY(JInternalFrame jif) {
        Point p = new Point(0, 0);
        //se obtiene dimension del contenedor
        Dimension pantalla = this.view.desktopPane.getSize();
        //se obtiene dimension del JInternalFrame
        Dimension ventana = jif.getSize();
        //se calcula posición para el centrado
        p.x = (pantalla.width - ventana.width) / 2;
        p.y = (pantalla.height - ventana.height) / 2;
        return p;
    }

    /**
     * Método para el trabajo con el formulario de <b>Datos Empresa</b>
     *
     * @param accion String con la accion a realizar
     */
    private void datosEmpresaAcction(String accion, String direccion_logo) {
        switch (accion) {
            case "crear":
                // Poner código de accion crear
                break;
            case "modificar":
                String nombre_empresa = this.datosEmpresa.nombreEmpresaTextField.getText();
                this.codigo_reup = this.datosEmpresa.codigoReupFormattedTextField.getText();
                codigo_reup = codigo_reup.replace(".", "");
                String titular_cuenta_cup = this.datosEmpresa.titularCuentaCUPTextField.getText();
                String titular_cuenta_cuc = this.datosEmpresa.titularCuentaCUCTextField.getText();
                String cuenta_cup = this.datosEmpresa.cuentaBancariaCUPTextField.getText();
                String cuenta_cuc = this.datosEmpresa.cuentaBancariaCUCTextField.getText();
                int id_organismo = this.datosEmpresa.organismoComboBox.getSelectedIndex();
                String telefono = this.datosEmpresa.telefonoFormattedTextField.getText();
                String fax = this.datosEmpresa.faxFormattedTextField.getText();
                String correo_electronico = this.datosEmpresa.correoElectronicoTextField.getText();
                String direccion = this.datosEmpresa.direccionTextArea.getText();
                this.modificarEmpresa(nombre_empresa, this.codigo_reup, titular_cuenta_cup, titular_cuenta_cuc, cuenta_cup, cuenta_cuc, direccion_logo, id_organismo, telefono, fax, correo_electronico, direccion);
                break;
            case "eliminar":
                // Poner código de accion eliminar
                break;
            default:
                this.e = this.model.getEmpresa(this.codigo_reup);
                this.datosEmpresa.nombreEmpresaTextField.setText(this.e.getNombre_empresa());
                this.datosEmpresa.codigoReupFormattedTextField.setText(this.e.getCodigo_reup());
                this.datosEmpresa.titularCuentaCUPTextField.setText(this.e.getTitular_cuenta_cup());
                this.datosEmpresa.titularCuentaCUCTextField.setText(this.e.getTitular_cuenta_cuc());
                this.datosEmpresa.cuentaBancariaCUPTextField.setText(this.e.getCuenta_cup());
                this.datosEmpresa.cuentaBancariaCUCTextField.setText(this.e.getCuenta_cuc());
                //Verificar si existe el logo para mostrarlo de lo contrario mostrar imagen por defecto                
                if (this.e.getDireccion_logo() != null) {
                    File f = new File(e.getDireccion_logo());
                    if (f.exists()) {
                        imagen = Toolkit.getDefaultToolkit().getImage(e.getDireccion_logo());
                    } else {
                        imagen = Toolkit.getDefaultToolkit().getImage("logos/default.png");
                    }
                } else {
                    imagen = Toolkit.getDefaultToolkit().getImage("logos/default.png");
                }
                this.datosEmpresa.logotipoEmpresaPanelImage.setImagen(imagen);
                this.datosEmpresa.organismoComboBox.setModel(this.model.organismoCombobox("t_organismo"));
                this.datosEmpresa.organismoComboBox.setSelectedIndex(this.e.getId_organismo());
                this.datosEmpresa.telefonoFormattedTextField.setText(this.e.getTelefono());
                this.datosEmpresa.faxFormattedTextField.setText(this.e.getFax());
                this.datosEmpresa.correoElectronicoTextField.setText(this.e.getCorreo_electronico());
                this.datosEmpresa.direccionTextArea.setText(this.e.getDireccion());
                break;
        }
    }

    /**
     * Método para copiar un fichero a una dirección dada
     *
     * @param ficheroSeleccionado
     * @param direccionDestino
     * <b>ficheroSeleccionado</b> es el fichero que se copiar en la
     * <b>direccionDestino</b>
     */
    public void copiarLogo(File ficheroSeleccionado, String direccionDestino) {
        if (ficheroSeleccionado != null) {
            File ficheroSalida = new File(direccionDestino + "/" + ficheroSeleccionado.getName());

            FileInputStream entrada = null;

            FileOutputStream salida = null;
            try {
                entrada = new FileInputStream(ficheroSeleccionado);
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
            try {
                salida = new FileOutputStream(ficheroSalida);
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
            byte[] datosArchivo = null;
            try {
                datosArchivo = new byte[entrada.available()];
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            try {
                entrada.read(datosArchivo);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            try {
                salida.write(datosArchivo);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            //cargo la imagen que copie en la carpeta logo_temp en el objeto logotipoEmpresaPanelImage para mostrar la imagen
            imagen = Toolkit.getDefaultToolkit().getImage(direccionDestino + "/" + ficheroSeleccionado.getName());
            this.datosEmpresa.logotipoEmpresaPanelImage.setImagen(imagen);
        }
    }

    public void modificarEmpresa(String nombre_empresa, String codigo_reup, String titular_cuenta_cup, String titular_cuenta_cuc, String cuenta_cup, String cuenta_cuc, String direccion_logo, int id_organismo, String telefono, String fax, String correo_electronico, String direccion) {
        this.msg = "";
        if (nombre_empresa.isEmpty()) {
            this.msg += "Nombre de la Empresa: Campo Nulo \n";
        }
        if (codigo_reup.isEmpty()) {
            this.msg += "Código Reup: Campo Nula \n";
        }
        if (titular_cuenta_cup.isEmpty()) {
            this.msg += "Titular de la Cuenta CUP: Campo Nula \n";
        }
        if (titular_cuenta_cuc.isEmpty()) {
            this.msg += "Titular de la Cuenta CUC: Campo Nula \n";
        }
        if (cuenta_cup.isEmpty()) {
            this.msg += "Cuenta bancaria CUP: Campo Nula \n";
        }
        if (cuenta_cuc.isEmpty()) {
            this.msg += "Cuenta bancaria CUC: Campo Nula \n";
        }
        if (direccion_logo.isEmpty()) {
            this.msg += "Logotipo de la Empresa: Campo Nula \n";
        }
        if (id_organismo == 0) {
            this.msg += "Organismo: Seleccionar un organismo \n";
        }
        if (telefono.isEmpty()) {
            this.msg += "Teléfono: Campo Nula \n";
        }
        if (fax.isEmpty()) {
            this.msg += "Fax: Campo Nula \n";
        }
        if (correo_electronico.isEmpty()) {
            this.msg += "Correo Eletrónico: Campo Nula \n";
        }
        if (direccion.isEmpty()) {
            this.msg += "Dirección: Campo Nula \n";
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.users, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            telefono = telefono.replace(" ", "");
            telefono = telefono.replace("+53", "");
            fax = fax.replace(" ", "");
            fax = fax.replace("+53", "");
            String dirLogos;
            if (ficheroSeleccionado == null) {
                dirLogos = this.e.getDireccion_logo();
            } else {
                dirLogos = direccion_logo + "/" + ficheroSeleccionado.getName();
                if (!dirLogos.equals(this.e.getDireccion_logo())) {
                    File f = new File(this.e.getDireccion_logo());
                    f.delete();
                }
            }
            if (this.model.actualizarEmpresa(nombre_empresa, codigo_reup, titular_cuenta_cup, titular_cuenta_cuc, cuenta_cup, cuenta_cuc, dirLogos, id_organismo, telefono, fax, correo_electronico, direccion)) {
                if (ficheroSeleccionado != null) {
                    copiarLogo(ficheroSeleccionado, direccion_logo);
                    File f = new File("logos_temp/" + ficheroSeleccionado.getName());
                    f.deleteOnExit();
                }
                JOptionPane.showMessageDialog(this.users, "Se ha actualizado la empresa correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                try {
                    this.cerrar(this.datosEmpresa);
                } catch (PropertyVetoException ex) {
                    System.err.println(ex.getMessage());
                }
            } else {
                this.msg = "Error al intentar actualizar la empresa";
                JOptionPane.showMessageDialog(this.users, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para el trabajo con el formulario de <b>Detalles de Control</b>
     *
     * @param accion String con la accion a realizar
     */
    private void detallesControlAcction(String accion) {
        switch (accion) {
            case "actualizar":
                String realiado_por = this.detallesControl.realizadoPorTextField.getText();
                String cargo = this.detallesControl.cargoTextField.getText();
                String aviso_vencimiento_contrato = this.detallesControl.avisoVencimientoContratoFormattedTextField.getText();
                String meses_promediar_lectura = this.detallesControl.mesesPromediarLecturaFormattedTextField.getText();
                this.msg = "";
                if (realiado_por.isEmpty()) {
                    this.msg += "Realizado por: Campo Nulo \n";
                }
                if (cargo.isEmpty()) {
                    this.msg += "Cargo: Campo Nula \n";
                }
                if (aviso_vencimiento_contrato.isEmpty()) {
                    this.msg += "Aviso de Vencimiento Contrato: Campo Nula \n";
                }
                if (meses_promediar_lectura.isEmpty()) {
                    this.msg += "Meses a Promediar Lectura: Campo Nula \n";
                }
                if (!msg.isEmpty()) {
                    JOptionPane.showMessageDialog(this.users, msg, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (this.model.agregarOActualizarDetallesControl(id_ueb, realiado_por, cargo, Integer.parseInt(aviso_vencimiento_contrato), Integer.parseInt(meses_promediar_lectura))) {
                        JOptionPane.showMessageDialog(this.users, "Se ha actualizado los detalles de control correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            this.cerrar(this.detallesControl);
                        } catch (PropertyVetoException ex) {
                            System.err.println(ex.getMessage());
                        }
                    } else {
                        this.msg = "Error al intentar actualizar los detalles de control";
                        JOptionPane.showMessageDialog(this.users, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            default:
                DetallesControl detallesDeControl = this.model.getDetallesControl(this.id_ueb);
                if (detallesDeControl != null) {
                    this.detallesControl.realizadoPorTextField.setText(detallesDeControl.getRealiado_por());
                    this.detallesControl.cargoTextField.setText(detallesDeControl.getCargo());
                    this.detallesControl.avisoVencimientoContratoFormattedTextField.setText(String.valueOf(detallesDeControl.getViso_vencimiento_contrato()));
                    this.detallesControl.mesesPromediarLecturaFormattedTextField.setText(String.valueOf(detallesDeControl.getMeses_promediar_lectura()));
                }
                break;
        }
    }

    /**
     * Método para el trabajo con el formulario de <b>TipoServicios</b>
     *
     * @param accion String con la accion a realizar
     */
    private void tipoServiosAcction(String accion) {
        String selectString = (String) this.tipoServicio.modificarEliminarList.getSelectedValue();
        switch (accion) {
            case "agregarModifiar":
                String tipo_servicio = this.tipoServicio.tipoServicioTextField.getText();
                this.msg = "";
                if (tipo_servicio.isEmpty()) {
                    this.msg += "Tipos de Servicios: Campo Nulo \n";
                }
                if (!msg.isEmpty()) {
                    JOptionPane.showMessageDialog(this.users, msg, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (this.model.agregarTipoServicio(tipo_servicio, selectString)) {
                        JOptionPane.showMessageDialog(this.users, "Se han actualizado los tipos de servicios correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        this.msg = "Error al intentar actualizar los tipos de servicios \n";
                        this.msg += "Puede que exista un Servicio con ese nombre o exista problema con la Base de Datos.";
                        JOptionPane.showMessageDialog(this.users, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                this.tipoServiosAcction("visualizar");
                break;
            case "eliminar":
                if (!selectString.isEmpty()) {
                    int acc = JOptionPane.showConfirmDialog(this.users, "Estas seguro de eliminar a " + selectString, "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        if (this.model.eliminarTipoServicio(selectString)) {
                            JOptionPane.showMessageDialog(this.users, "Se ha eliminado el Tipo d Servicio correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.users, "Error al intentar eliminar el Tipo de Servicio", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.users, "Seleccione un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.tipoServiosAcction("visualizar");
                break;
            default:
                this.tipoServicio.tipoServicioTextField.setText("");
                this.tipoServicio.modificarEliminarList.setModel(this.model.getTipoServicio());
                break;
        }
    }

    /**
     * Método para el trabajo con el formulario del cambio de contraseña
     */
    private void cambiarPasswordAccion() {
        String passAct;
        String passActEncr = "";
        String passNew;
        String passNewRep;
        passAct = this.cP.contrasennaActualPasswordField.getText();
        passNew = this.cP.nuevaContrasennaPasswordField.getText();
        passNewRep = this.cP.repetirContrasennaNuevaPasswordField.getText();
        msg = ""; //Guarda el cuerpo del mensaje a mostrar
        if (passAct.isEmpty()) {
            msg += "Contraseña Actual: Campo Nulo \n";
        } else {
            passActEncr = modelo.getStringMessageDigest(passAct, "SHA-512");
        }
        if (passNew.isEmpty()) {
            msg += "Nueva Contraseña: Campo Nulo \n";
        }
        if (passNewRep.isEmpty()) {
            msg += "Repetir nueva contraseña : Campo Nulo \n";
        }
        if (msg.isEmpty()) {
            if (!passNew.equals(passNewRep)) {
                msg += "La Contraseña nueva no coincide con la repetida \n";
            } else if (!usuario.getPassword().equals(passActEncr)) {
                msg += "Contraseña actual no valida \n";
            }
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.cP, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int acc = JOptionPane.showConfirmDialog(this.users, "¡Estas a punto de cambiar su contraseña!\n¿Estas seguro de quererlo?", "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (acc == 0) {
                if (this.model.actualizarUsuario(this.usuario.getIdUsuario(), this.usuario.getUsuario(), passNew, this.usuario.getIdRol())) {
                    this.usuario = this.model.Autenticar(this.usuario.getUsuario(), passNew);
                    JOptionPane.showMessageDialog(this.cP, "Se ha cambiado la contraseña correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            try {
                this.cerrar(this.cP);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
