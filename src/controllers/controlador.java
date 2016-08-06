package controllers;

import views.factestatal.Autenticar;
import views.factestatal.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.modelo;

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

    /**
     * Método Constructor de la clase controldora
     */
    public controlador(Principal vista, Autenticar vistaA, modelo modelo) {
        this.view = vista;
        this.viewA = vistaA;
        this.model = modelo;
        form_autenticar();
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
        //Se pone a la escucha de las acciones del Usuario
        viewA.aceptarButton.addActionListener(this);
        viewA.cancelarButton.addActionListener(this);
    }

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
        
        return this.msg.isEmpty();
    }

    /**
     * Método para inicializar el formulario principal del sistema
     */
    private void iniciar() {
        this.viewA.setVisible(false);
        this.view.setVisible(true);
        view.setTitle("GEDSAA");
        view.setLocationRelativeTo(null);//centrado en pantalla        
        //Se añade las acciones a los controles del formulario padre        
        this.view.exitMenuItem.setActionCommand("Salir del Sistema");
        //Se pone a escuchar las acciones del usuario
        view.exitMenuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Captura en String el comando accionado por el usuario
        String comando = e.getActionCommand();

        //Controlar acciones del usuario en las condiciones a continuación
        if (comando.equals("Autenticar")) {
            if (this.validarUsuario(this.viewA.usuarioTextField.getText(), this.viewA.passwordTextField.getText())) {
                this.iniciar();
            } else {
                JOptionPane.showMessageDialog(this.view, this.msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (comando.equals("Cancelar")) {
            this.cerrar_sistema(); // Si el usuario escogio cancelar se cierra el sistema.
        } else if (comando.equals("Salir del Sistema")) {
            this.cerrar_sistema(); // Si el usuario escogio cancelar se cierra el sistema.
        } else if (comando.equals("Cerrar")) {
            //deshabilita/habilita controles según sea necesario
            /*
             this.view.cmdIdentificacion.setEnabled(true);
             this.view.cmdExit.setEnabled(false);
             this.view.cmdProgramar.setEnabled(false);
             this.view.cmdImprimir.setEnabled(false);
             */
            //Cierra formularios hijos abiertos
            cerrar_todo();
        }
    }

    private void cerrar_sistema() {
        System.exit(0);
    }

    // CIERRA TODOS LOS JInternalFrame QUE ESTEN ABIERTOS
    private void cerrar_todo() {
        /*
         JInternalFrame[] activos = this.view.jCDesktopPane1.getAllFrames();
         //boolean cerrado=true;
         int i = 0;
         while (i < activos.length) {
         cerrar(activos[i]);
         i++;
         }
         */
    }
}
