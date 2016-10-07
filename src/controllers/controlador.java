package controllers;

import factestatal.ficheros.About;
import views.factestatal.ficheros.Users;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import models.Usuario;
import models.modelo;
import views.factestatal.Autenticar;
import views.factestatal.Principal;
import views.factestatal.ficheros.cambiarPassword;

/**
 * Clase Controladora del sistema, acá esta toda la parte controladora de nustro
 * sistema
 *
 * @author carlos860920
 */
public class controlador implements ActionListener {

    private Principal view;
    private Autenticar viewA;
    private modelo model;
    public String msg;
    cambiarPassword cP;
    public Usuario usuario;
    private Users users;
    private About about;

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
    //-----------------------------------------------------------------------------------
    /**
     * Método para inicializar el formulario principal del sistema
     */
    private void iniciar() {
        this.viewA.setVisible(false);
        this.view.setVisible(true);
        view.setTitle("GEDSAA");
        view.setLocationRelativeTo(null);//centrado en pantalla        
        //Se añade las acciones a los controles del formulario padre        
        this.view.usersMenuItem.setActionCommand("Usuario"); //Cambiar contraseña
        this.view.cambiarContrasennaMenuItem.setActionCommand("Cambiar password"); //Cambiar contraseña        
        this.view.exitMenuItem.setActionCommand("Salir del Sistema"); //Salir del sistema        
        this.view.aboutMenuItem.setActionCommand("about"); // Acerca de nosotros
        //Se pone a escuchar las acciones del usuario
        view.usersMenuItem.addActionListener(this);
        view.cambiarContrasennaMenuItem.addActionListener(this); //Cambiar contraseñas
        view.exitMenuItem.addActionListener(this); //Salir del sistema        
        view.aboutMenuItem.addActionListener(this); // Acerca de nosotros
    }

    /**
     * Método para controlar el formulario de Autenticación
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

    private void formCambiarPassword() {
        cP = new cambiarPassword();
        this.view.desktopPane.add(cP);
        cP.setLocation(centradoXY(cP));
        cP.setTitle("Cambiar contraseña...");
        cP.setVisible(true);
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
    
    private void formUsuario() {
        users = new Users();
        this.view.desktopPane.add(users);
        users.setLocation(centradoXY(users));
        users.setTitle("Gestion de Usuarios...");
        users.setVisible(true);
        usuarioAcction("Visualizar");
        //Se agrega las acciones al formulario de Autenticación
        
        //Se pone a la escucha de las acciones del Usuario        
    }
    
    public void formAcerca(){
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
     * @param idRol Id del rol del usuario a crear
     * @return boolean, verdadero si se creo el usuario con exito, falso si no
     * se creo.
     */
    public boolean crearUsuario(String usuario, String passwd, int idRol) {
        return this.model.crearUsuario(usuario, passwd, idRol);
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

    // CIERRA TODOS LOS JInternalFrame QUE ESTEN ABIERTOS
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
     * Método que dado un JInternalFrame calcula la posicion de centrado
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
     * Método para el trabajo con el formulario de Usuario
     * @param accion String con la accion a realizar
     */
    private void usuarioAcction(String accion) {
        switch(accion){
            case "crear":
                break;
            case "modificar":
                break;
            case "eliminar":
                break;
            default:
                users.usuariosTable.setModel(this.model.mostrarUsuarios());
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
            if (this.model.actualizarUsuario(this.usuario.getIdUsuario(), this.usuario.getUsuario(), passNew, this.usuario.getIdRol())) {
                try {
                    this.usuario = this.model.Autenticar(this.usuario.getUsuario(), passNew);
                    this.cerrar(this.cP);
                    JOptionPane.showMessageDialog(this.cP, "Se ha cambiado la contraseña correctamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
