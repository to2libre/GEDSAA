package controllers;

import configuracion.config;
import factestatal.ficheros.About;
import factestatal.ficheros.Clientes;
import factestatal.ficheros.DatosEmpresa;
import factestatal.ficheros.DetallesDeControl;
import factestatal.ficheros.Servicios;
import factestatal.ficheros.TipoServicios;
import factestatal.ficheros.Titulares;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.DetallesControl;
import models.Empresa;
import models.Usuario;
import models.modelo;
import myclass.herramientas;
import myclass.utilfecha;
import views.factestatal.Autenticar;
import views.factestatal.Principal;
import views.factestatal.ficheros.AgregarActualizarTitular;
import views.factestatal.ficheros.ClientesServicios;
import views.factestatal.ficheros.Licencia;
import views.factestatal.ficheros.ServiMetradoSobreCons;
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
    private Licencia licencia;
    private DatosEmpresa datosEmpresa;
    private DetallesDeControl detallesControl;
    private TipoServicios tipoServicio;
    private Servicios servicio;
    private Titulares titulares;
    private AgregarActualizarTitular titularesAgregarActualizar;
    private Clientes clientes;
    private ClientesServicios cs;
    private ServiMetradoSobreCons serviMetradoSobreCons;

    //Variables de modelos
    public Usuario usuario;

    //Variables de ambito global para el trabajo con los formularios interiores
    public String msg;// Variable que guarda los mensages del sistema, sean informacion o error
    public int idUsuario; // id del usuario que se desea modificar en getionar usuario
    public Image imagen; // variable para el logo de la empresa  
    config conf;
    public String codigo_reup; //codigo reup de la empresa que esta seleccionada (configurado en el xml de configuracion)
    public int id_ueb; // Id de la UEB con la que se esta trabando, este id se carga de (Achivo de configuracion XML  de configuracion)
    public int idServicio; // Id del servicio a modificar o seleccionado
    public int idTitular; // Id del titular a modificar o seleccionado
    public int idCliente; // Id del cliente a modificar o seleccionado
    public boolean barraImportarExportar; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraAcciones; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraInformes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCierreMes; // Carga de (Achivo de configuracion XML  de configuracion)
    public boolean barraCobros; // Carga de (Achivo de configuracion XML  de configuracion)    
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
        this.form_autenticar();
        this.idUsuario = -1;
        this.idServicio = -1;
        this.idTitular = -1;

        // Leer el fichero de configuración
        this.conf = new config(); //Clase para la lectura y escritura del archivo de configuración
        this.conf.leerXml(); //Método para leer el archivo de configuración
        this.codigo_reup = conf.getCodigo_reup(); //Guardo el codigo reup en la variable ocn mismo nonbre
        this.id_ueb = conf.getId_ueb(); //Guardo el id_ueb en la variable con mismo nombre
        this.barraImportarExportar = conf.isBarraImportarExportar();
        this.barraAcciones = conf.isBarraAcciones();
        this.barraInformes = conf.isBarraInformes();
        this.barraCierreMes = conf.isBarraCierreMes();
        this.barraCobros = conf.isBarraCobros();

        //cargar barra de herramienta
        if (barraImportarExportar) {
            this.view.importarExportarToolBar.setVisible(true);
            this.view.barraImportarExportarMenuItem.setSelected(true);
        } else {
            this.view.importarExportarToolBar.setVisible(false);
            this.view.barraImportarExportarMenuItem.setSelected(false);
        }
        if (barraAcciones) {
            this.view.accionesToolBar.setVisible(true);
            this.view.barraAccionesMenuItem.setSelected(true);
        } else {
            this.view.accionesToolBar.setVisible(false);
            this.view.barraAccionesMenuItem.setSelected(false);
        }
        if (barraInformes) {
            this.view.informesToolBar.setVisible(true);
            this.view.barraInformesMenuItem.setSelected(true);
        } else {
            this.view.informesToolBar.setVisible(false);
            this.view.barraInformesMenuItem.setSelected(false);
        }
        if (barraCierreMes) {
            this.view.cierreToolBar.setVisible(true);
            this.view.barraCierreMesMenuItem.setSelected(true);
        } else {
            this.view.cierreToolBar.setVisible(false);
            this.view.barraCierreMesMenuItem.setSelected(false);
        }
        if (barraCobros) {
            this.view.cobrosToolBar.setVisible(true);
            this.view.barraCobrosMenuItem.setSelected(true);
        } else {
            this.view.cobrosToolBar.setVisible(false);
            this.view.barraCobrosMenuItem.setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Captura en String el comando accionado por el usuario
        String comando = e.getActionCommand();
        //Controlar acciones del usuario en las condiciones a continuación
        switch (comando) {
            case "Autenticar":
                if (this.validarUsuario(this.viewA.usuarioTextField.getText(), this.viewA.passwordTextField.getText())) {
                    this.usuario = this.model.getUsuario();
                    this.view.usuarioAutenticadoLabel.setText(" " + this.viewA.usuarioTextField.getText());
                    this.iniciar();
                } else {
                    JOptionPane.showMessageDialog(this.view, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Cancelar":
                this.cerrar_sistema(); // Si el usuario escogio cancelar se cierra el sistema.
                break;
            case "barraImportarExportar": //Mostrar u Ocultar barra de Importar exportar
                this.mostrarOcultarBarraImportarExportar();
                break;
            case "barraAcciones": //Mostrar u Ocultar barra de Acciones
                this.mostrarOcultarBarraAcciones();
                break;
            case "barraInformes": //Mostrar u Ocultar barra de Informes
                this.mostrarOcultarBarraInformes();
                break;
            case "barraCierreMes": //Mostrar u Ocultar barra de Cierre de mes
                this.mostrarOcultarBarraCierreMes();
                break;
            case "barraCobros": //Mostrar u Ocultar barra de Cobros
                this.mostrarOcultarBarraCobros();
                break;
            case "botonCliente":
                this.formClientes();
                break;
            case "about":
                this.formAcerca(); // Mostrar jInternalFrame Acerca
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
            case "servicioForm": //Mostrar jInternalFrame Servicios
                this.formServicio();
                break;
            case "agrearModificarServicio": // Acción de agregar o modificar un srvicio
                this.serviciosAcction("agregarModifiar");
                break;
            case "eliminarServicio": //Accion de eliminar un servicio
                this.serviciosAcction("eliminar");
                break;
            case "modificarServicio": //Accion de modificar un servicio
                this.serviciosAcction("modificar");
                break;
            case "titularesForm": //Accion para mostrar jinternal frame titulares
                this.formTitulares();
                break;
            case "agrearModificarTitulares": //Accion para agrear o modificar titulares
                this.titularesAcction("agregarModifiar");
                break;
            case "modificarTitulares": //Accion para modificar titulares
                this.titularesAcction("modificar");
                break;
            case "eliminarTitulares": //Accion para eliminar titulares                
                this.titularesAcction("eliminar");
                break;
            case "clientesForm": //Accion para mostrar jinternal frame clientes
                this.formClientes();
                break;
            case "nuevoCliente": //Accion para limpiar formulario para agregar un nuevo cliente
                this.clientesAcction("nuevoCliente");
                break;
            case "agregarActualizarCliente": //Accion para agregar o actualizar un cliente
                this.clientesAcction("agregarActualizarCliente");
                break;
            case "modificarCliente": //Accion para llenar campos del fomulario con el cliente seleccionado
                this.clientesAcction("modificarCliente");
                break;
            case "eliminarCliente": //Accion para eliminar clientes                
                this.clientesAcction("eliminarCliente");
                break;
            case "asignarClientesServicios":
                this.clientesAcction("serviciosDelCliente");
                break;
            case "asignarServiciosClientes": //Accion para asignar servicios a clientes
                this.clientesAcction("asignarSevicios");
                break;
            case "eliminarServiciosClientes": //Accion para eliminar servicios a clientes
                this.clientesAcction("eliminarSevicios");
                break;
            case "serviMetradoSobConsForm": //Accion para eliminar servicios a clientes
                this.formServiMetradoSobreCons();
                break;
            case "agregarMetradoSobreC": //Accion para eliminar servicios a clientes
                this.servMetradoSobreConsAcction("agregarMetSobCons");
                break;
            case "eliminarMetradoSobreC": //Accion para eliminar servicios a clientes
                this.servMetradoSobreConsAcction("eliminarMetSobCons");
                break;
            case "licenciaForm": //Accion para mostrar jinternal frame licencia
                this.formLicencia();
                break;
            case "generarSemillaLicencia": //Acciona para ...
                this.licAcction("generarSemilla");
                break;
            case "guardarSemillaLicencia": //Acciona para ...
                this.licAcction("guardarSemilla");
                break;
            case "cargarLic": //Acciona para ...
                this.licAcction("cargar");
                break;
            case "validarLic": //Acciona para ...
                this.licAcction("validar");
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
        this.view.exitMenuItem.setActionCommand("Salir del Sistema"); //Salir del sistema        
        this.view.barraImportarExportarMenuItem.setActionCommand("barraImportarExportar");//Menú -View- Mostrar u Ocultar barra 
        this.view.barraAccionesMenuItem.setActionCommand("barraAcciones");//Menú -View- Mostrar u Ocultar barra
        this.view.barraInformesMenuItem.setActionCommand("barraInformes");//Menú -View- Mostrar u Ocultar barra
        this.view.barraCierreMesMenuItem.setActionCommand("barraCierreMes");//Menú -View- Mostrar u Ocultar barra
        this.view.barraCobrosMenuItem.setActionCommand("barraCobros");//Menú -View- Mostrar u Ocultar barra
        this.view.clientesButton.setActionCommand("botonCliente"); //Clientes
        this.view.datosEmpresaMenuItem.setActionCommand("datosEmpresaForm"); //Datos de Empresa
        this.view.tipoServiciosMenuItem.setActionCommand("tipoServiciosForm"); //TipoServicios
        this.view.serviciosMenuItem.setActionCommand("servicioForm"); //Servicios
        this.view.titularesMenuItem.setActionCommand("titularesForm"); //Titulares
        this.view.clientesMenuItem.setActionCommand("clientesForm"); //Clientes
        this.view.relacionSMMenuItem.setActionCommand("serviMetradoSobConsForm"); //ServiMetradoSobreConsumo
        this.view.detallesControlMenuItem.setActionCommand("formDetallesControl"); //Formulario Detalles de Control
        this.view.usersMenuItem.setActionCommand("Usuario"); //Gestionar Usuario
        this.view.cambiarContrasennaMenuItem.setActionCommand("Cambiar password"); //Cambiar contraseña                
        this.view.aboutMenuItem.setActionCommand("about"); // Acerca de nosotros
        this.view.licenciaMenuItem.setActionCommand("licenciaForm"); // Formulario de para la licencia
        //Se pone a escuchar las acciones del usuario
        this.view.exitMenuItem.addActionListener(this); //Salir del sistema 
        this.view.barraImportarExportarMenuItem.addActionListener(this);//Menú -View- Mostrar u Ocultar barra 
        this.view.barraAccionesMenuItem.addActionListener(this);//Menú -View- Mostrar u Ocultar barra
        this.view.barraInformesMenuItem.addActionListener(this);//Menú -View- Mostrar u Ocultar barra
        this.view.barraCierreMesMenuItem.addActionListener(this);//Menú -View- Mostrar u Ocultar barra
        this.view.barraCobrosMenuItem.addActionListener(this);//Menú -View- Mostrar u Ocultar barra
        this.view.clientesButton.addActionListener(this); //Clientes
        this.view.datosEmpresaMenuItem.addActionListener(this); //Datos Empresa
        this.view.tipoServiciosMenuItem.addActionListener(this); //TipoServicios
        this.view.serviciosMenuItem.addActionListener(this); //Servicios
        this.view.titularesMenuItem.addActionListener(this); //Titulares
        this.view.clientesMenuItem.addActionListener(this); //Clientes
        this.view.relacionSMMenuItem.addActionListener(this); //ServiMetradoSobreConsumo
        this.view.detallesControlMenuItem.addActionListener(this); //DetallesControl
        this.view.usersMenuItem.addActionListener(this); //Gestionar Usuario
        this.view.cambiarContrasennaMenuItem.addActionListener(this); //Cambiar contraseñas        
        this.view.aboutMenuItem.addActionListener(this); // Acerca de nosotros
        this.view.licenciaMenuItem.addActionListener(this); // Formulario de para la licencia
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
     * Método para controlar el formulario <b>TipoServicio</b>
     */
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
     * Método para controlar el formulario <b>Servicio</b>
     */
    public void formServicio() {
        this.servicio = new Servicios();
        this.servicio.setTitle("Servicios...");

        if (!restaurarFormulario(this.servicio.getTitle())) {
            this.view.desktopPane.add(this.servicio);
            this.servicio.setLocation(centradoXY(this.servicio));
            this.servicio.setVisible(true);
            this.serviciosAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.servicio.descripcionTextField.setActionCommand("agrearModificarServicio");
            this.servicio.unidadMedidaTextField.setActionCommand("agrearModificarServicio");
            this.servicio.cucTextField.setActionCommand("agrearModificarServicio");
            this.servicio.cupTextField.setActionCommand("agrearModificarServicio");
            this.servicio.agregarModificarButton.setActionCommand("agrearModificarServicio");
            this.servicio.eliminarButton.setActionCommand("eliminarServicio");
            this.servicio.modificarButton.setActionCommand("modificarServicio");
            //Se pone a la escucha de las acciones del Usuario
            this.servicio.descripcionTextField.addActionListener(this);
            this.servicio.unidadMedidaTextField.addActionListener(this);
            this.servicio.cucTextField.addActionListener(this);
            this.servicio.cupTextField.addActionListener(this);
            this.servicio.agregarModificarButton.addActionListener(this);
            this.servicio.eliminarButton.addActionListener(this);
            this.servicio.modificarButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>Titulares</b>
     */
    public void formTitulares() {
        this.titulares = new Titulares();
        this.titulares.setTitle("Titulares...");

        if (!restaurarFormulario(this.titulares.getTitle())) {
            this.view.desktopPane.add(this.titulares);
            this.titulares.setLocation(centradoXY(this.titulares));
            this.titulares.setVisible(true);
            this.titularesAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.titulares.agregarModificarButton.setActionCommand("agrearModificarTitulares");
            this.titulares.eliminarButton.setActionCommand("eliminarTitulares");
            //Se pone a la escucha de las acciones del Usuario
            this.titulares.agregarModificarButton.addActionListener(this);
            this.titulares.eliminarButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>AgregarActualizarTitular</b>
     */
    public void formAgregarActualizarTitular() {
        this.titularesAgregarActualizar = new AgregarActualizarTitular();
        this.titularesAgregarActualizar.setTitle("Agregar o Actualizar Titular...");
        this.titularesAgregarActualizar.organismoComboBox.setModel(this.model.organismoCombobox("t_organismo"));

        if (!restaurarFormulario(this.titularesAgregarActualizar.getTitle())) {
            this.view.desktopPane.add(this.titularesAgregarActualizar);
            this.titularesAgregarActualizar.setLocation(centradoXY(this.titularesAgregarActualizar));
            this.titularesAgregarActualizar.setVisible(true);
            //this.titularesAcction("visualizar");            
            //Se agrega las acciones al formulario de Usuario        
            this.titularesAgregarActualizar.agregarModificarButton.setActionCommand("modificarTitulares");
            //Se pone a la escucha de las acciones del Usuario
            this.titularesAgregarActualizar.agregarModificarButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>Clientes</b>
     */
    public void formClientes() {
        this.clientes = new Clientes();
        this.clientes.setTitle("Clientes...");

        if (!restaurarFormulario(this.clientes.getTitle())) {
            this.view.desktopPane.add(this.clientes);
            this.clientes.setLocation(centradoXY(this.clientes));
            this.clientes.setVisible(true);
            this.clientesAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.clientes.nuevoButton.setActionCommand("nuevoCliente");
            this.clientes.agregarButton.setActionCommand("agregarActualizarCliente");
            this.clientes.modificarButton.setActionCommand("modificarCliente");
            this.clientes.eliminarButton.setActionCommand("eliminarCliente");
            this.clientes.asignarServiciosButton.setActionCommand("asignarClientesServicios");
            //Se pone a la escucha de las acciones del Usuario            
            this.clientes.nuevoButton.addActionListener(this);
            this.clientes.agregarButton.addActionListener(this);
            this.clientes.modificarButton.addActionListener(this);
            this.clientes.eliminarButton.addActionListener(this);
            this.clientes.asignarServiciosButton.addActionListener(this);
        }
    }

    /**
     * Método para controlar el formulario <b>formClientesServicios</b>
     *
     * @param fila_seleccionada
     */
    public void formClientesServicios(int fila_seleccionada) {
        this.cs = new ClientesServicios();
        this.cs.serviciosComboBox.setModel(this.model.servicioCombobox("v_servicio_all"));
        this.idCliente = this.model.arregloCliente.get(fila_seleccionada).getId_cliente();
        this.cs.serviciosList.setModel(this.model.mostrarClienteServicios(this.idCliente));
        this.cs.nombreClienteTextField.setText(this.model.arregloCliente.get(fila_seleccionada).getNombre_titular() + " | " + this.model.arregloCliente.get(fila_seleccionada).getNombre_cliente());

        //Se agrega las acciones al formulario de Usuario        
        this.cs.asignarServiciosButton.setActionCommand("asignarServiciosClientes");
        this.cs.eliminarServiciosButton.setActionCommand("eliminarServiciosClientes");
        //Se pone a la escucha de las acciones del Usuario            
        this.cs.asignarServiciosButton.addActionListener(this);
        this.cs.eliminarServiciosButton.addActionListener(this);

        JOptionPane.showInternalMessageDialog(this.clientes, this.cs, "Asignar Servicios al Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para controlar el formulario <b>ServiMetradoSobreCons</b>
     */
    public void formServiMetradoSobreCons() {
        this.serviMetradoSobreCons = new ServiMetradoSobreCons();
        this.serviMetradoSobreCons.setTitle("Relacion de Servicios Metrados - Sobre Consumo...");

        if (!restaurarFormulario(this.serviMetradoSobreCons.getTitle())) {
            this.view.desktopPane.add(this.serviMetradoSobreCons);
            this.serviMetradoSobreCons.setLocation(centradoXY(this.serviMetradoSobreCons));
            this.serviMetradoSobreCons.setVisible(true);
            this.servMetradoSobreConsAcction("visualizar");
            //Se agrega las acciones al formulario de Usuario        
            this.serviMetradoSobreCons.AgregarButton.setActionCommand("agregarMetradoSobreC");
            this.serviMetradoSobreCons.EliminarButton.setActionCommand("eliminarMetradoSobreC");
            //Se pone a la escucha de las acciones del Usuario            
            this.serviMetradoSobreCons.AgregarButton.addActionListener(this);
            this.serviMetradoSobreCons.EliminarButton.addActionListener(this);
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

    /**
     * Método para controlar el formulario <b>Licencia</b>
     */
    public void formLicencia() {
        licencia = new Licencia();
        this.licencia.setTitle("Licencia...");
        this.licencia.semillaTextArea.setEditable(false);
        this.licencia.semillaTextArea.setBackground(Color.lightGray);
        this.licencia.licenciaTextArea.setEditable(false);
        this.licencia.licenciaTextArea.setBackground(Color.lightGray);
        File archSem = new File("config/" + conf.getLength());
        File archLi = new File("config/" + conf.getIns());
        this.licencia.semillaTextArea.setText(this.leerTxt(archSem));
        if (!this.validarLic(archSem, archLi)) {
            this.cambiarEstado(false);
        } else {
            this.licencia.setClosable(true);
            this.licencia.cargarButton.setEnabled(false);
            this.licencia.validarButton.setEnabled(false);
        }
        this.licencia.nombresTextField.setText(conf.getNombres());
        this.licencia.apellidosTextField.setText(conf.getApellidos());
        this.licencia.emailFormattedTextField.setText(conf.getEmail());
        this.licencia.empresaTextField.setText(conf.getEmpresa());

        this.view.desktopPane.add(licencia);
        this.licencia.setLocation(centradoXY(licencia));
        this.licencia.setVisible(true);

        //Se agrega las acciones al formulario de Usuario        
        this.licencia.generarButton.setActionCommand("generarSemillaLicencia");
        this.licencia.guardarButton.setActionCommand("guardarSemillaLicencia");
        this.licencia.cargarButton.setActionCommand("cargarLic");
        this.licencia.validarButton.setActionCommand("validarLic");
        //Se pone a la escucha de las acciones del Usuario
        this.licencia.generarButton.addActionListener(this);
        this.licencia.guardarButton.addActionListener(this);
        this.licencia.cargarButton.addActionListener(this);
        this.licencia.validarButton.addActionListener(this);
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
     * Método para agregar servicio
     *
     * @param descripcion
     * @param unidad_medida
     * @param idTipoServicio
     * @param precio_cuc
     * @param precio_cup
     */
    public void agregarServicio(String descripcion, String unidad_medida, int idTipoServicio, String precio_cuc, String precio_cup) {
        this.msg = "";
        if (descripcion.isEmpty()) {
            this.msg += "Descripción: Campo Nulo \n";
        }
        if (unidad_medida.isEmpty()) {
            this.msg += "Unidad de Medida: Campo Nula \n";
        }
        if (idTipoServicio == 0) {
            this.msg += "Tipo de servicio: Seleccionar un rol \n";
        }
        if (precio_cuc.isEmpty() || precio_cup.isEmpty()) {
            this.msg += "Precio en cuc o cup: Campo Nula \n";
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int idTipServ = this.model.arregloTipoServ.get(idTipoServicio - 1).getId_tipo_srvicio();
            if (this.model.agregarServicio(descripcion, unidad_medida, idTipServ, precio_cuc, precio_cup)) {
                JOptionPane.showMessageDialog(this.servicio, "Se ha agregado el servicio correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.msg = "Error al intentar agregar el servicio";
                JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para la modificación de servicio
     *
     * @param descripcion
     * @param unidad_medida
     * @param idTipoServicio
     * @param precio_cuc
     * @param precio_cup
     */
    public void modificarServicio(String descripcion, String unidad_medida, int idTipoServicio, String precio_cuc, String precio_cup) {
        this.msg = "";
        if (descripcion.isEmpty()) {
            this.msg += "Descripción: Campo Nulo \n";
        }
        if (unidad_medida.isEmpty()) {
            this.msg += "Unidad de Medida: Campo Nula \n";
        }
        if (idTipoServicio == 0) {
            this.msg += "Tipo de Servicio: Seleccionar un Tipo de Servicio \n";
        }
        if (precio_cuc.isEmpty() || precio_cup.isEmpty()) {
            this.msg += "Precio en cuc o cup: Campo Nula \n";
        }
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int idTipServ = this.model.arregloTipoServ.get(idTipoServicio - 1).getId_tipo_srvicio();
            if (this.model.actualizarServicio(this.idServicio, descripcion, unidad_medida, idTipServ, precio_cuc, precio_cup)) {
                JOptionPane.showMessageDialog(this.servicio, "Se ha actualizado el Servicio correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.msg = "Error al intentar actualizar el servicio";
                JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para el trabajo con el formulario de <b>TipoServicios</b>
     *
     * @param accion String con la accion a realizar
     */
    private void serviciosAcction(String accion) {
        //String selectString = (String) this.tipoServicio.modificarEliminarList.getSelectedValue();
        int fila_seleccionada;
        switch (accion) {
            case "agregarModifiar":
                // Capturar datos del formulario
                String descripcion = this.servicio.descripcionTextField.getText();
                String unidad_medida = this.servicio.unidadMedidaTextField.getText();
                int idTipoServicio = this.servicio.tipoServicioComboBox.getSelectedIndex();
                String precio_cuc = this.servicio.cucTextField.getText();
                String precio_cup = this.servicio.cupTextField.getText();
                // idUsuario es igual a -1 para crearlo o es igual al id del usario para modificarlo o eliminarlo                
                if (idServicio == -1) {
                    this.agregarServicio(descripcion, unidad_medida, idTipoServicio, precio_cuc, precio_cup);
                } else {
                    this.modificarServicio(descripcion, unidad_medida, idTipoServicio, precio_cuc, precio_cup);
                }
                // Si se crea o se ctualiza el usuario entra
                if (this.msg.isEmpty()) {
                    // Limpiar todos los escaques del formulario
                    this.servicio.descripcionTextField.setText("");
                    this.servicio.unidadMedidaTextField.setText("");
                    this.servicio.cucTextField.setText("");
                    this.servicio.cupTextField.setText("");
                    this.servicio.tipoServicioComboBox.setSelectedIndex(0);
                    // Refrescar los datos a mostrar en el formulario de visualización
                    this.serviciosAcction("visualizar");
                }
                break;
            case "eliminar":
                fila_seleccionada = this.servicio.visualizarServiciosTable.getSelectedRow();
                idServicio = this.model.arregloServicio.get(fila_seleccionada).getId_servicio();
                if (idServicio > 0) {
                    int acc = JOptionPane.showConfirmDialog(this.servicio, "Estas seguro de eliminar a " + this.model.arregloServicio.get(fila_seleccionada).getDescripcion(), "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        if (this.model.eliminarServicio(idServicio)) {
                            JOptionPane.showMessageDialog(this.servicio, "Se ha eliminado el Servicio correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.servicio, "Error al intentar eliminar el Servicio", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.servicio, "Seleccione un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.serviciosAcction("visualizar");
                break;
            case "modificar":
                fila_seleccionada = this.servicio.visualizarServiciosTable.getSelectedRow();
                idServicio = this.model.arregloServicio.get(fila_seleccionada).getId_servicio();
                if (idServicio > 0) {
                    this.servicio.descripcionTextField.setText(this.model.arregloServicio.get(fila_seleccionada).getDescripcion());
                    this.servicio.unidadMedidaTextField.setText(this.model.arregloServicio.get(fila_seleccionada).getUnidad_medida());
                    // Para ubicar el servicio seleccionado
                    int fila_sel = 0;
                    for (int i = 0; i < this.model.arregloTipoServ.size(); i++) {
                        if (this.model.arregloTipoServ.get(i).getId_tipo_srvicio() == this.model.arregloServicio.get(fila_seleccionada).getId_tipo_servicio()) {
                            fila_sel = i + 1;
                            break;
                        }
                    }
                    this.servicio.tipoServicioComboBox.setSelectedIndex(fila_sel);
                    //
                    this.servicio.cucTextField.setText(this.model.arregloServicio.get(fila_seleccionada).getPrecio_cuc());
                    this.servicio.cupTextField.setText(this.model.arregloServicio.get(fila_seleccionada).getPrecio_cup());
                }
                break;
            default:
                idServicio = -1;
                this.servicio.visualizarServiciosTable.setModel(this.model.mostrarServicios());
                this.servicio.tipoServicioComboBox.setModel(this.model.tipoServicioCombobox("t_tipo_servicio"));
                break;
        }
    }

    /**
     * Método para agregar titulares
     *
     * @param titular
     * @param descripcion
     * @param cuenta_bancaria
     * @param codigo_reup
     * @param direccion
     * @param telefono
     * @param fax
     * @param email
     * @param no_contrato
     * @param fecha_inicio_contrato
     * @param fecha_fin_contrato
     * @param id_organismo
     * @param tipo_moneda
     */
    public void agregarTitulares(String titular, String descripcion, String cuenta_bancaria, String codigo_reup, String direccion, String telefono, String fax, String email, String no_contrato, String fecha_inicio_contrato, String fecha_fin_contrato, int id_organismo, String tipo_moneda) {
        int noContrato = 0;
        this.msg = "";
        if (titular.isEmpty()) {
            this.msg += "Titular: Campo Nulo \n";
        }
        if (cuenta_bancaria.isEmpty() || cuenta_bancaria.length() != 16) {
            this.msg += "Cuenta Bancaria: El campo no cumple con los requisitos, tiene " + cuenta_bancaria.length() + " caracteres de 16\n";
        }
        if (codigo_reup.isEmpty()) {
            this.msg += "Codigo Reup: Campo Nula \n";
        }
        if (direccion.isEmpty()) {
            this.msg += "Dirección: Campo Nula \n";
        }
        if (no_contrato.isEmpty()) {
            this.msg += "Numero de Contrato: Campo Nulo \n";
        }
        if (fecha_inicio_contrato.isEmpty()) {
            this.msg += "Fecha de Inicio: Campo Nula \n";
        }
        if (fecha_fin_contrato.isEmpty()) {
            this.msg += "Fecha de fin: Campo Nula \n";
        }
        if (id_organismo == 0) {
            this.msg += "Organismo: Se debe seleccionar un organismo \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Date fini = utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy");
            Date ffin = utilfecha.convierteStringADate(fecha_fin_contrato, "d/MM/yyyy");
            //int comparacionF = fini.compareTo(ffin);
            if (fini.after(ffin)) {
                this.msg += "Fecha de Fin: menor o igual a la Fecha de inicio \n";
            }
            if (!this.msg.isEmpty()) {
                JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                noContrato = Integer.parseInt(no_contrato);
                int vigenciaContrato = utilfecha.diferenciaFecha(utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy"), utilfecha.convierteStringADate(fecha_fin_contrato, "d/MM/yyyy"), "A");
                int id_org = this.model.arregloOrganismos.get(id_organismo - 1).getId_organismo();
                if (this.model.agregarTitular(titular, descripcion, cuenta_bancaria, codigo_reup, direccion, telefono, fax, email, noContrato, fecha_inicio_contrato, vigenciaContrato, id_org, tipo_moneda)) {
                    JOptionPane.showMessageDialog(this.servicio, "Se ha agregado el titular correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.msg = "Error al intentar agregar el titular \n";
                    this.msg += "Revice el codigo reup, numero de cuenta o contrato, no puede repetirse";
                    JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    /**
     * Método para la modificación de titulares
     *
     * @param titular
     * @param descripcion
     * @param cuenta_bancaria
     * @param codigo_reup
     * @param direccion
     * @param telefono
     * @param fax
     * @param email
     * @param no_contrato
     * @param fecha_inicio_contrato
     * @param fecha_fin_contrato
     * @param id_organismo
     * @param tipo_moneda
     */
    public void modificarTitulares(String titular, String descripcion, String cuenta_bancaria, String codigo_reup, String direccion, String telefono, String fax, String email, String no_contrato, String fecha_inicio_contrato, String fecha_fin_contrato, int id_organismo, String tipo_moneda) {
        int noContrato = 0;
        this.msg = "";
        if (titular.isEmpty()) {
            this.msg += "Titular: Campo Nulo \n";
        }
        if (cuenta_bancaria.isEmpty() || cuenta_bancaria.length() != 16) {
            this.msg += "Cuenta Bancaria: El campo no cumple con los requisitos, tiene " + cuenta_bancaria.length() + " caracteres de 16\n";
        }
        if (codigo_reup.isEmpty()) {
            this.msg += "Codigo Reup: Campo Nula \n";
        }
        if (direccion.isEmpty()) {
            this.msg += "Dirección: Campo Nula \n";
        }
        if (no_contrato.isEmpty()) {
            this.msg += "Numero de Contrato: Campo Nulo \n";
        }
        if (fecha_inicio_contrato.isEmpty()) {
            this.msg += "Fecha de Inicio: Campo Nula \n";
        }
        if (fecha_fin_contrato.isEmpty()) {
            this.msg += "Fecha de fin: Campo Nula \n";
        }
        if (id_organismo == 0) {
            this.msg += "Organismo: Se debe seleccionar un organismo \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Date fini = utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy");
            Date ffin = utilfecha.convierteStringADate(fecha_fin_contrato, "d/MM/yyyy");
            //int comparacionF = fini.compareTo(ffin);
            if (fini.after(ffin)) {
                this.msg += "Fecha de Fin: menor o igual a la Fecha de inicio \n";
            }
            if (!this.msg.isEmpty()) {
                JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                noContrato = Integer.parseInt(no_contrato);
                int vigenciaContrato = utilfecha.diferenciaFecha(utilfecha.convierteStringADate(fecha_inicio_contrato, "d/MM/yyyy"), utilfecha.convierteStringADate(fecha_fin_contrato, "d/MM/yyyy"), "A");
                int id_org = this.model.arregloOrganismos.get(id_organismo - 1).getId_organismo();
                if (this.model.actualizarTitular(idTitular, titular, descripcion, cuenta_bancaria, codigo_reup, direccion, telefono, fax, email, noContrato, fecha_inicio_contrato, vigenciaContrato, id_org, tipo_moneda)) {
                    JOptionPane.showMessageDialog(this.servicio, "Se ha actualizado el titular correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.msg = "Error al intentar agregar el titular";
                }
            }
        }
    }

    /**
     * Método para el trabajo con el formulario de <b>TipoServicios</b>
     *
     * @param accion String con la accion a realizar
     */
    private void titularesAcction(String accion) {
        int fila_seleccionada;
        fila_seleccionada = this.titulares.titularesTable.getSelectedRow();
        switch (accion) {
            case "agregarModifiar":
                this.formAgregarActualizarTitular();
                if (fila_seleccionada != -1) {
                    idTitular = this.model.arregloTitulares.get(fila_seleccionada).getId_titular();
                    this.rellenarCamposActualizarTitular(fila_seleccionada);
                }
                break;
            case "eliminar":
                if (fila_seleccionada != -1) {
                    idTitular = this.model.arregloTitulares.get(fila_seleccionada).getId_titular();
                    int acc = JOptionPane.showConfirmDialog(this.titulares, "Estas seguro de eliminar a " + this.model.arregloTitulares.get(fila_seleccionada).getDescripcion(), "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        if (this.model.eliminarTitular(idTitular)) {
                            JOptionPane.showMessageDialog(this.titulares, "Se ha eliminado el Titular correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.titulares, "Error al intentar eliminar el Titular", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.servicio, "Seleccione un Titular para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }

                idTitular = -1; // Si no se pone esto cuando buelba a intentar 
                this.titularesAcction("visualizar");
                break;

            case "modificar":
                // Capturar datos del formulario
                String titular = this.titularesAgregarActualizar.titularTextField.getText();
                String descripcion = this.titularesAgregarActualizar.descripcionTextField.getText();
                String cuentaBancaria = this.titularesAgregarActualizar.cuentaBancariaTextField.getText();
                String codigo_reupp = this.titularesAgregarActualizar.codigo_reupFormattedText.getText();
                String direccion = this.titularesAgregarActualizar.direccionTextField.getText();
                String telefono = this.titularesAgregarActualizar.telefonoFormattedText.getText();
                String fax = this.titularesAgregarActualizar.faxFormattedText.getText();
                String email = this.titularesAgregarActualizar.emailTextField.getText();
                String noContrato = this.titularesAgregarActualizar.noContratoFormattedText.getText();
                String fechaIniContrat = this.titularesAgregarActualizar.fechaInicioContratoFormattedText.getText();
                String fechaFinContrat = this.titularesAgregarActualizar.FechaFinalContratoFormattedText.getText();
                int organismoSeleccionado = this.titularesAgregarActualizar.organismoComboBox.getSelectedIndex();

                String tipoMoneda = "CUP";
                if (this.titularesAgregarActualizar.cucRadioButton.isSelected()) {
                    tipoMoneda = "CUC";
                }
                // Si el id del titual es -1 es que no hay titular seleccionado para modificar por lo que entra a agregar
                if (idTitular == -1) {
                    this.agregarTitulares(titular, descripcion, cuentaBancaria, codigo_reupp, direccion, telefono, fax, email, noContrato, fechaIniContrat, fechaFinContrat, organismoSeleccionado, tipoMoneda);
                } else {
                    this.modificarTitulares(titular, descripcion, cuentaBancaria, codigo_reupp, direccion, telefono, fax, email, noContrato, fechaIniContrat, fechaFinContrat, organismoSeleccionado, tipoMoneda);
                }
                // Si se crea o se ctualiza el titular entra
                if (this.msg.isEmpty()) {
                    if (idTitular == -1) {
                        // Limpiar todos los escaques del formulario
                        this.titularesAgregarActualizar.titularTextField.setText("");
                        this.titularesAgregarActualizar.descripcionTextField.setText("");
                        this.titularesAgregarActualizar.cuentaBancariaTextField.setText("");
                        this.titularesAgregarActualizar.codigo_reupFormattedText.setText("");
                        this.titularesAgregarActualizar.direccionTextField.setText("");
                        this.titularesAgregarActualizar.telefonoFormattedText.setText("");
                        this.titularesAgregarActualizar.faxFormattedText.setText("");
                        this.titularesAgregarActualizar.emailTextField.setText("");
                        this.titularesAgregarActualizar.noContratoFormattedText.setText("");
                        this.titularesAgregarActualizar.fechaInicioContratoFormattedText.setText("");
                        this.titularesAgregarActualizar.FechaFinalContratoFormattedText.setText("");
                        this.titularesAgregarActualizar.organismoComboBox.setSelectedIndex(0);
                        this.titularesAgregarActualizar.cucRadioButton.setSelected(true);
                        // Refrescar los datos a mostrar en el formulario de visualización
                    }
                    try {
                        this.cerrar(titularesAgregarActualizar);
                    } catch (PropertyVetoException ex) {
                        JOptionPane.showMessageDialog(this.servicio, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    this.titularesAcction("visualizar");
                }
            default:
                this.titulares.titularesTable.setModel(this.model.mostrarTitulares());
                idTitular = -1;
                break;
        }
    }

    /**
     * Método para rellenar los campos del formulario actualizar titular.
     *
     * @param fila_seleccionada
     */
    public void rellenarCamposActualizarTitular(int fila_seleccionada) {
        this.titularesAgregarActualizar.codigo_reupFormattedText.setText(this.model.arregloTitulares.get(fila_seleccionada).getCodigo_reup());
        this.titularesAgregarActualizar.titularTextField.setText(this.model.arregloTitulares.get(fila_seleccionada).getTitular());
        this.titularesAgregarActualizar.descripcionTextField.setText(this.model.arregloTitulares.get(fila_seleccionada).getDescripcion());
        this.titularesAgregarActualizar.cuentaBancariaTextField.setText(this.model.arregloTitulares.get(fila_seleccionada).getCuenta_bancaria());
        // Para selecionar la moneda
        if ("CUC".equals(this.model.arregloTitulares.get(fila_seleccionada).getTipo_moneda())) {
            this.titularesAgregarActualizar.cucRadioButton.setSelected(true);
        } else {
            this.titularesAgregarActualizar.cupRadioButton.setSelected(true);
        }
        //
        // Para ubicar el organismo que esta seleccionado y seleccionarlo
        int fila_sel = 0;
        for (int i = 0; i < this.model.arregloOrganismos.size(); i++) {
            if (this.model.arregloOrganismos.get(i).getId_organismo() == this.model.arregloTitulares.get(fila_seleccionada).getId_organismo()) {
                fila_sel = i + 1;
                i = this.model.arregloOrganismos.size() + 1;
            }
        }
        //
        this.titularesAgregarActualizar.organismoComboBox.setSelectedIndex(fila_sel);
        this.titularesAgregarActualizar.direccionTextField.setText(this.model.arregloTitulares.get(fila_seleccionada).getDireccion());
        this.titularesAgregarActualizar.telefonoFormattedText.setText(this.model.arregloTitulares.get(fila_seleccionada).getTelefono());
        this.titularesAgregarActualizar.faxFormattedText.setText(this.model.arregloTitulares.get(fila_seleccionada).getFax());
        this.titularesAgregarActualizar.emailTextField.setText(this.model.arregloTitulares.get(fila_seleccionada).getEmail());
        this.titularesAgregarActualizar.noContratoFormattedText.setText(String.valueOf(this.model.arregloTitulares.get(fila_seleccionada).getNo_contrato()));
        this.titularesAgregarActualizar.fechaInicioContratoFormattedText.setText(this.model.arregloTitulares.get(fila_seleccionada).getFecha_inicio_contrato());
        String fechaFinContrato = utilfecha.convierteDateAString(utilfecha.sumaAnnos(utilfecha.convierteStringADate(this.model.arregloTitulares.get(fila_seleccionada).getFecha_inicio_contrato(), "d/MM/yyyy"), this.model.arregloTitulares.get(fila_seleccionada).getVigencia_contrato()));
        this.titularesAgregarActualizar.FechaFinalContratoFormattedText.setText(fechaFinContrato);
    }

    /**
     * Método para el trabajo con el formulario de <b>TipoServicios</b>
     *
     * @param accion String con la accion a realizar
     */
    private void clientesAcction(String accion) {
        int fila_seleccionada;
        fila_seleccionada = this.clientes.clientesTable.getSelectedRow();

        switch (accion) {
            case "nuevoCliente":
                this.limpiarFormularioClientes();
                break;
            case "agregarActualizarCliente":
                // Capturar datos del formulario                
                String nombre_cliente = this.clientes.nombreClienteTextField.getText();
                String direccion = this.clientes.direccionTextField.getText();
                int titularSelected = this.clientes.titularComboBox.getSelectedIndex();
                int id_titular = 0;
                if (titularSelected > 0) {
                    for (int i = 0; i < this.model.arregloTitulares.size(); i++) {
                        if (this.model.arregloTitulares.get(i).getId_titular() == this.model.arregloTitulares.get(titularSelected - 1).getId_titular()) {
                            id_titular = this.model.arregloTitulares.get(i).getId_titular();
                            break;
                        }
                    }
                }
                String telefono = this.clientes.telefonoFormattedTextField.getText();
                String email = this.clientes.correoElectronicoFormattedTextField.getText();
                boolean alcantarillado = this.clientes.alcantarilladoCheckBox.isSelected();
                boolean presupuestado = this.clientes.presupuestadoCheckBox.isSelected();
                boolean subsidio = this.clientes.subsidioCheckBox.isSelected();
                // Si el id del cliente es -1 es que no hay cliente seleccionado para modificar por lo que entra a agregar
                if (idCliente == -1) {
                    this.agregarCliente(nombre_cliente, direccion, id_titular, telefono, email, alcantarillado, presupuestado, subsidio);
                } else {
                    this.actualizarCliente(idCliente, nombre_cliente, direccion, id_titular, telefono, email, alcantarillado, presupuestado, subsidio);
                }
                break;
            case "modificarCliente":
                if (fila_seleccionada != -1) {
                    idCliente = this.model.arregloCliente.get(fila_seleccionada).getId_cliente();
                    this.rellenarCamposActualizarCliente(fila_seleccionada);
                }
                break;
            case "eliminarCliente":
                if (fila_seleccionada != -1) {
                    idCliente = this.model.arregloCliente.get(fila_seleccionada).getId_cliente();
                    int acc = JOptionPane.showConfirmDialog(this.clientes, "Estas seguro de eliminar a " + this.model.arregloCliente.get(fila_seleccionada).getNombre_cliente(), "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        if (this.model.eliminarCliente(idCliente)) {
                            JOptionPane.showMessageDialog(this.clientes, "Se ha eliminado el Cliente correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.clientes, "Error al intentar eliminar el Cliente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.clientes, "Seleccione un Cliente para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.clientesAcction("visualizar");
                break;
            case "serviciosDelCliente":
                if (fila_seleccionada != -1) {
                    this.formClientesServicios(fila_seleccionada);
                } else {
                    JOptionPane.showMessageDialog(this.clientes, "Seleccione un Cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "asignarSevicios":
                int sSelected = this.cs.serviciosComboBox.getSelectedIndex();
                int id_serv = 0;
                if (sSelected > 0) {
                    for (int i = 0; i < this.model.arregloServicio.size(); i++) {
                        if (this.model.arregloServicio.get(i).getId_servicio() == this.model.arregloServicio.get(sSelected - 1).getId_servicio()) {
                            id_serv = this.model.arregloServicio.get(i).getId_servicio();
                            break;
                        }
                    }
                }
                if (id_serv > 0 && this.idCliente != -1) {
                    if (this.model.asignarClienteServicio(this.idCliente, id_serv)) {
                        JOptionPane.showMessageDialog(this.cs, "Servicio Asignado al Cliente Correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this.cs, "Error al intentar asignar el servicio", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this.clientes, "Seleccione un servicio a Asignar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.cs.serviciosComboBox.setSelectedIndex(0);
                this.cs.serviciosList.setModel(this.model.mostrarClienteServicios(this.idCliente));
                break;
            case "eliminarSevicios":
                int servSelected = this.cs.serviciosList.getSelectedIndex();
                System.out.println("Sevicio seleccionado: " + servSelected);
                int id_servi = 0;
                if (servSelected != -1) {
                    for (int i = 0; i < this.model.arregloClienteServicio.size(); i++) {
                        if (this.model.arregloClienteServicio.get(i).getId_servicio() == this.model.arregloClienteServicio.get(servSelected).getId_servicio()) {
                            id_servi = this.model.arregloClienteServicio.get(i).getId_servicio();
                            break;
                        }
                    }
                    int acc = JOptionPane.showConfirmDialog(this.cs, "¿Estas seguro de eliminar el servicio al cliente?", "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (acc == 0) {
                        System.out.println(" " + this.idCliente + " " + id_servi);
                        if (this.model.eliminarClienteServicio(this.idCliente, id_servi)) {
                            JOptionPane.showMessageDialog(this.cs, "Se ha eliminado el servicio para el cliente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this.cs, "Error al intentar eliminar el Servicio al Cliente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.cs, "No ha seleccionado servicios para eliminar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.cs.serviciosComboBox.setSelectedIndex(0);
                this.cs.serviciosList.setModel(this.model.mostrarClienteServicios(this.idCliente));
                break;
            default:
                this.limpiarFormularioClientes();
                idCliente = -1; // Si no se pone esto cuando buelba a intentar 
                this.clientes.titularComboBox.setModel(this.model.titularesCombobox("v_titulares_all"));
                this.clientes.clientesTable.setModel(this.model.mostrarCliente());
                break;
        }
    }

    public void actualizarCliente(int id_cliente, String nombre_cliente, String direccion, int id_titular, String telefono, String email, boolean alcantarillado, boolean presupuestado, boolean subsidio) {
        this.msg = "";
        if (nombre_cliente.isEmpty()) {
            this.msg += "Nombre del Cliente: Campo Nulo \n";
        }
        if (direccion.isEmpty()) {
            this.msg += "Direccion: Campo Nulo \n";
        }
        if (id_titular == 0) {
            this.msg += "Titular: Seleccione un <b>Titular</b> \n";
        }
        if (!telefono.isEmpty() && telefono.length() != 9) {
            this.msg += "Teléfono: No es número de teléfono valida \n";
        }
        if (!email.isEmpty() && !herramientas.validarEmailFuerte(email)) {
            this.msg += "Correo Electronico: No es una direccion de correo valida \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.model.actualizarCliente(id_cliente, nombre_cliente, direccion, id_titular, telefono, email, alcantarillado, presupuestado, subsidio)) {
                JOptionPane.showMessageDialog(this.servicio, "Se ha actualizado el cliente correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                this.clientesAcction("visualizar");
            } else {
                this.msg = "Error al intentar agregar el cliente";
            }

        }
    }

    public void agregarCliente(String nombre_cliente, String direccion, int id_titular, String telefono, String email, boolean alcantarillado, boolean presupuestado, boolean subsidio) {
        this.msg = "";
        if (nombre_cliente.isEmpty()) {
            this.msg += "Nombre del Cliente: Campo Nulo \n";
        }
        if (direccion.isEmpty()) {
            this.msg += "Direccion: Campo Nulo \n";
        }
        if (id_titular == 0) {
            this.msg += "Titular: Seleccione un <b>Titular</b> \n";
        }
        if (!telefono.isEmpty() && telefono.length() != 9) {
            this.msg += "Teléfono: No es número de teléfono valida \n";
        }
        if (!email.isEmpty() && !herramientas.validarEmailFuerte(email)) {
            this.msg += "Correo Electronico: No es una direccion de correo valida \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!this.msg.isEmpty()) {
                JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (this.model.agregarCliente(nombre_cliente, direccion, id_titular, telefono, email, alcantarillado, presupuestado, subsidio)) {
                    JOptionPane.showMessageDialog(this.servicio, "Se ha agregado el cliente correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                    this.clientesAcction("visualizar");
                } else {
                    this.msg = "Error al intentar agregar el cliente \n";
                    JOptionPane.showMessageDialog(this.servicio, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    public void limpiarFormularioClientes() {
        this.clientes.nombreClienteTextField.setText("");
        this.clientes.direccionTextField.setText("");
        this.clientes.titularComboBox.setSelectedIndex(0);
        this.clientes.telefonoFormattedTextField.setText("");
        this.clientes.correoElectronicoFormattedTextField.setText("");
        this.clientes.alcantarilladoCheckBox.setSelected(true);
        this.clientes.presupuestadoCheckBox.setSelected(false);
        this.clientes.subsidioCheckBox.setSelected(false);
    }

    public void rellenarCamposActualizarCliente(int fila_seleccionada) {
        this.clientes.nombreClienteTextField.setText(this.model.arregloCliente.get(fila_seleccionada).getNombre_cliente());
        this.clientes.direccionTextField.setText(this.model.arregloCliente.get(fila_seleccionada).getDireccion());
        // Para ubicar el titular que esta seleccionado
        int fila_sel = 0;
        for (int i = 0; i < this.model.arregloTitulares.size(); i++) {
            if (this.model.arregloTitulares.get(i).getId_titular() == this.model.arregloCliente.get(fila_seleccionada).getId_titular()) {
                fila_sel = i + 1;
                break;
            }
        }
        this.clientes.titularComboBox.setSelectedIndex(fila_sel);
        //
        this.clientes.telefonoFormattedTextField.setText(this.model.arregloCliente.get(fila_seleccionada).getTelefono());
        this.clientes.correoElectronicoFormattedTextField.setText(this.model.arregloCliente.get(fila_seleccionada).getEmail());
        // Para selecionar si posee Alcantarillado o no        
        if (this.model.arregloCliente.get(fila_seleccionada).isAlcantarillado()) {
            this.clientes.alcantarilladoCheckBox.setSelected(true);
        } else {
            this.clientes.alcantarilladoCheckBox.setSelected(false);
        }
        //
        // Para selecionar si es presupuestado o no
        if (this.model.arregloCliente.get(fila_seleccionada).isPresupuestado()) {
            this.clientes.presupuestadoCheckBox.setSelected(true);
        } else {
            this.clientes.presupuestadoCheckBox.setSelected(false);
        }
        //
        // Para selecionar si es subsidio o no
        if (this.model.arregloCliente.get(fila_seleccionada).isSubsidio()) {
            this.clientes.subsidioCheckBox.setSelected(true);
        } else {
            this.clientes.subsidioCheckBox.setSelected(false);
        }
        //
    }

    /**
     * Método para el trabajo con el formulario de <b>servMetradoSobreCons</b>
     *
     * Este Método se encarga de realizar las acciones que se soliciten en el
     * formulario servMetradoSobreCons
     *
     * @param accion String con la accion a realizar
     */
    private void servMetradoSobreConsAcction(String accion) {
        int fila_seleccionada;
        int servicioSel = this.serviMetradoSobreCons.servicioComboBox.getSelectedIndex();
        int sobreConsumoSel = this.serviMetradoSobreCons.sobreConsumoComboBox.getSelectedIndex();

        switch (accion) {
            case "agregarMetSobCons":
                this.agregarServicioSobreCons(servicioSel, sobreConsumoSel);
                break;
            case "eliminarMetSobCons":
                fila_seleccionada = this.serviMetradoSobreCons.ServMetSobConTable.getSelectedRow();
                if (fila_seleccionada > -1) {
                    this.eliminarServicioSobreCons(fila_seleccionada);
                } else {
                    JOptionPane.showMessageDialog(this.serviMetradoSobreCons, "Selecione una relacion para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                //this.serviMetradoSobreCons.servicioComboBox.setSelectedIndex(0);
                //this.serviMetradoSobreCons.sobreConsumoComboBox.setSelectedIndex(0);
                this.serviMetradoSobreCons.servicioComboBox.setModel(this.model.servicioCombobox("v_servicio_all"));
                this.serviMetradoSobreCons.sobreConsumoComboBox.setModel(this.model.servicioCombobox("v_servicio_all"));
                this.serviMetradoSobreCons.ServMetSobConTable.setModel(this.model.mostrarServMetradoSobreCons());
                break;
        }
    }

    public void eliminarServicioSobreCons(int fila_seleccionada) {
        int id_servicio = 0;
        int id_sobreconsumo = 0;
        for (int i = 0; i < this.model.arregloServicioSobreC.size(); i++) {
            if (this.model.arregloServicioSobreC.get(i).getId_servicio() == this.model.arregloServicioSobreC.get(fila_seleccionada).getId_servicio()) {
                id_servicio = this.model.arregloServicioSobreC.get(i).getId_servicio();
                id_sobreconsumo = this.model.arregloServicioSobreC.get(i).getId_sobreconsumo();
            }
        }
        if (this.model.gregarServMetradoSobreCons(id_servicio, id_sobreconsumo)) {
            JOptionPane.showMessageDialog(this.serviMetradoSobreCons, "Se ha eliminado la relacion entre Servicios Metrados y Sobre-Consumos", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
            this.servMetradoSobreConsAcction("visualizar");
        } else {
            this.msg = "Error al intentar eliminar la relación \n";
            JOptionPane.showMessageDialog(this.serviMetradoSobreCons, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregarServicioSobreCons(int servicioSel, int sobreConsumoSel) {
        this.msg = "";
        // Capturar datos del formulario                                
        int id_servicio = 0;
        int id_sobreconsumo = 0;
        if (servicioSel > 0) {
            for (int i = 0; i < this.model.arregloServicio.size(); i++) {
                if (this.model.arregloServicio.get(i).getId_servicio() == this.model.arregloServicio.get(servicioSel - 1).getId_servicio()) {
                    id_servicio = this.model.arregloServicio.get(i).getId_servicio();
                    break;
                }
            }
        } else {
            this.msg += "Seleccione un Servicio \n";
        }
        if (sobreConsumoSel > 0) {
            for (int i = 0; i < this.model.arregloServicio.size(); i++) {
                if (this.model.arregloServicio.get(i).getId_servicio() == this.model.arregloServicio.get(sobreConsumoSel - 1).getId_servicio()) {
                    id_sobreconsumo = this.model.arregloServicio.get(i).getId_servicio();
                    break;
                }
            }
        } else {
            this.msg += "Seleccione un Sobre-Consumo \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.serviMetradoSobreCons, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.model.agregarServMetradoSobreCons(id_servicio, id_sobreconsumo)) {
                JOptionPane.showMessageDialog(this.serviMetradoSobreCons, "Se ha agregado la relacion entre Servicios Metrados y Sobre-Consumos", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                this.servMetradoSobreConsAcction("visualizar");
            } else {
                this.msg = "Error al intentar agregar la relación \n";
                JOptionPane.showMessageDialog(this.serviMetradoSobreCons, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void validarSemilla(String nombres, String apellidos, String email, String empresa) {
        this.msg = "";
        if (nombres.isEmpty()) {
            this.msg += "Nombre: Campo Nulo \n";
        }
        if (apellidos.isEmpty()) {
            this.msg += "Apellidos: Campo Nulo \n";
        }
        if (email.isEmpty()) {
            this.msg += "email: Campo nulo \n";
        } else if (!herramientas.validarEmailFuerte(email)) {
            this.msg += "email: No es una direccion de correo valida \n";
        }
        if (empresa.isEmpty()) {
            this.msg += "empresa: Campo Nulo \n";
        }
        if (!this.msg.isEmpty()) {
            JOptionPane.showMessageDialog(this.servicio, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String textoSemilla = nombres + apellidos + email + empresa;
            byte[] name = textoSemilla.getBytes();
            String semilla = java.util.UUID.nameUUIDFromBytes(name).toString();
            this.licencia.semillaTextArea.setText(semilla);
        }
    }

    public void crearSemilla(File archivoSemilla, String Semilla) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(archivoSemilla);
            pw = new PrintWriter(fichero);
            pw.println(Semilla);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                    JOptionPane.showMessageDialog(this.licencia, "Archivo de licencia creado correctamente", "Archivo Creado...", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void guardarSemilla(String textSemilla) {
        try {
            // La variable length contiene el nombre del archivo semilla
            if (conf.getLength().isEmpty()) {
                conf.setLength(java.util.UUID.randomUUID().toString().substring(0, 6));
            }
            String direccion = "config/" + conf.getLength();
            File Semilla = new File(direccion);
            if (Semilla.exists()) {
                File f = Semilla;
                f.delete();
            }
            FileWriter fichero = null;
            PrintWriter pw = null;
            fichero = new FileWriter(Semilla);
            pw = new PrintWriter(fichero);
            pw.println(textSemilla);
            fichero.close();

        } catch (IOException ex) {
            Logger.getLogger(controlador.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarLic(String textLic) {
        try {
            // La variable Ins contiene el nombre del archivo licencia
            if (conf.getIns().isEmpty()) {
                conf.setIns(java.util.UUID.randomUUID().toString().substring(0, 6));
            }
            String direccion = "config/" + conf.getIns();
            File Lic = new File(direccion);
            if (Lic.exists()) {
                File f = Lic;
                f.delete();
            }
            FileWriter fichero = null;
            PrintWriter pw = null;
            fichero = new FileWriter(Lic);
            pw = new PrintWriter(fichero);
            pw.println(textLic);
            fichero.close();

        } catch (IOException ex) {
            Logger.getLogger(controlador.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String leerTxt(File archivoTxt) {
        String texto = "";
        if (archivoTxt != null) {
            String cadena;
            FileReader f = null;
            try {
                f = new FileReader(archivoTxt);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(controlador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            BufferedReader b = new BufferedReader(f);

            try {
                while ((cadena = b.readLine()) != null) {
                    texto += cadena;

                }
            } catch (IOException ex) {
                Logger.getLogger(controlador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto;
    }

    public boolean validarLic(File archivoSem, File archivoLic) {
        String textoSem = leerTxt(archivoSem);
        String txtLlavePersonal = "Numlock".trim();
        Date hoy = new Date();
        Date fechaVig;
        String Fis;
        String textoLic = leerTxt(archivoLic);
        byte[] name;
        String licg;
        boolean val = false;
        for (int i = 0; i <= 365; i++) {
            fechaVig = utilfecha.sumaDias(hoy, i);
            Fis = utilfecha.convierteDateAString(fechaVig, "yyyy-MM-dd");
            String txtLic = textoSem + txtLlavePersonal + Fis.trim();
            name = txtLic.getBytes();
            licg = java.util.UUID.nameUUIDFromBytes(name).toString();
            if (licg.equals(textoLic)) {
                val = true;
                break;
            }
        }
        return val;
    }

    /**
     * Método para el trabajo con el formulario de <b>TipoServicios</b>
     *
     * @param accion String con la accion a realizar
     */
    private void licAcction(String accion) {
        File archivoLicencia = null;
        switch (accion) {
            case "generarSemilla":
                String nombres = this.licencia.nombresTextField.getText().trim();
                String apellidos = this.licencia.apellidosTextField.getText().trim();
                String email = this.licencia.emailFormattedTextField.getText().trim();
                String empresa = this.licencia.empresaTextField.getText().trim();
                this.validarSemilla(nombres, apellidos, email, empresa);
                break;
            case "guardarSemilla":
                if (this.licencia.semillaTextArea.getText().equals("")) {
                    JOptionPane.showMessageDialog(this.licencia, "Es necesario generar la semilla", "Alerta...", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JFileChooser sem = new JFileChooser();
                    FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.sem", "sem");
                    sem.setFileFilter(filtro);
                    sem.setAcceptAllFileFilterUsed(false);
                    sem.setDialogTitle("Guardar Licencia");
                    int accionl = sem.showSaveDialog(this.licencia);
                    if (accionl == JFileChooser.APPROVE_OPTION) {
                        archivoLicencia = sem.getSelectedFile();
                        String direccion = "";
                        String[] nombre = archivoLicencia.getName().trim().split("\\.");
                        if (nombre[nombre.length - 1].equals("sem")) {
                            direccion = archivoLicencia.getPath();
                        } else {
                            direccion = archivoLicencia.getPath() + ".sem";
                        }
                        File Semilla = new File(direccion);
                        if (!Semilla.exists()) {
                            this.crearSemilla(Semilla, this.licencia.semillaTextArea.getText());
                        } else {
                            int acc = JOptionPane.showConfirmDialog(this.licencia, "Ya existe un archivo con ese nombre, deceas remplazarlo", "¡Cuidado...!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (acc == 0) {
                                File f = archivoLicencia;
                                f.delete();
                                crearSemilla(Semilla, this.licencia.semillaTextArea.getText());
                            }
                        }
                    }
                }
                break;
            case "cargar":
                JFileChooser lic = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.lic", "lic");
                lic.setFileFilter(filtro);
                lic.setAcceptAllFileFilterUsed(false);
                lic.showOpenDialog(this.licencia);
                archivoLicencia = lic.getSelectedFile();
                String textoLice = "";
                if (archivoLicencia != null) {
                    String cadena;
                    FileReader f = null;
                    try {
                        f = new FileReader(archivoLicencia);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(controlador.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                    BufferedReader b = new BufferedReader(f);

                    try {
                        while ((cadena = b.readLine()) != null) {
                            textoLice += cadena;
                            this.licencia.licenciaTextArea.setText(textoLice);

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(controlador.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "validar":
                File archSem = new File("config/" + conf.getLength());
                if (this.validarLic(archSem, archivoLicencia)) {
                    try {
                        this.guardarSemilla(this.licencia.semillaTextArea.getText());
                        this.guardarLic(this.licencia.licenciaTextArea.getText());
                        this.cambiarEstado(true);
                        this.cerrar(this.licencia);

                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(controlador.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(this.licencia, "Licencia invalida", "Error...", JOptionPane.ERROR_MESSAGE);
                }
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
                Logger.getLogger(controlador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
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
                Logger.getLogger(controlador.class
                        .getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(controlador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para Mostrar u Ocultar la Barra de Importar Exportar
     */
    public void mostrarOcultarBarraImportarExportar() {
        if (barraImportarExportar) {
            this.view.importarExportarToolBar.setVisible(false);
            this.conf.setBarraImportarExportar(false);
            this.barraImportarExportar = conf.isBarraImportarExportar();
        } else {
            this.view.importarExportarToolBar.setVisible(true);
            this.conf.setBarraImportarExportar(true);
            this.barraImportarExportar = conf.isBarraImportarExportar();
        }
    }

    /**
     * Método para Mostrar u Ocultar la Barra de Acciones
     */
    public void mostrarOcultarBarraAcciones() {
        if (this.view.accionesToolBar.isVisible()) {
            this.view.accionesToolBar.setVisible(false);
            this.conf.setBarraAcciones(false);
            this.barraAcciones = conf.barraAcciones;
        } else {
            this.view.accionesToolBar.setVisible(true);
            this.conf.setBarraAcciones(true);
            this.barraAcciones = conf.barraAcciones;
        }
    }

    /**
     * Método para Mostrar u Ocultar la Barra de Informes
     */
    public void mostrarOcultarBarraInformes() {
        if (this.view.informesToolBar.isVisible()) {
            this.view.informesToolBar.setVisible(false);
            this.conf.setBarraInformes(false);
            this.barraInformes = conf.barraInformes;
        } else {
            this.view.informesToolBar.setVisible(true);
            this.conf.setBarraInformes(true);
            this.barraInformes = conf.barraInformes;
        }
    }

    /**
     * Método para Mostrar u Ocultar la Barra de Cierre de Mes
     */
    public void mostrarOcultarBarraCierreMes() {
        if (this.view.cierreToolBar.isVisible()) {
            this.view.cierreToolBar.setVisible(false);
            this.conf.setBarraCierreMes(false);
            this.barraCierreMes = conf.barraCierreMes;
        } else {
            this.view.cierreToolBar.setVisible(true);
            this.conf.setBarraCierreMes(true);
            this.barraCierreMes = conf.barraCierreMes;
        }
    }

    /**
     * Método para Mostrar u Ocultar la Barra de Cobros
     */
    public void mostrarOcultarBarraCobros() {
        if (this.view.cobrosToolBar.isVisible()) {
            this.view.cobrosToolBar.setVisible(false);
            this.conf.setBarraCobros(false);
            this.barraCierreMes = conf.barraCierreMes;
        } else {
            this.view.cobrosToolBar.setVisible(true);
            this.conf.setBarraCobros(true);
            this.barraCierreMes = conf.barraCierreMes;
        }
    }

    /**
     * Método para cambiar el estado de todos los componentes de la Vista
     * principal
     */
    private void cambiarEstado(boolean estado) {
        this.view.fileMenu.setEnabled(estado);
        this.view.verMenu.setEnabled(estado);
        this.view.ficherosMenu.setEnabled(estado);
        this.view.produccionMenu.setEnabled(estado);
        this.view.informesMenu.setEnabled(estado);
        this.view.datosCierreMenu.setEnabled(estado);
        this.view.cobrosMenu.setEnabled(estado);
        this.view.prodMenu.setEnabled(estado);
        this.view.contenedorToolBar.setVisible(estado);
    }
}
