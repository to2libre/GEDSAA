/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factestatal;

import factestatal.ficheros.Users;
import factestatal.ficheros.About;
import factestatal.ficheros.*;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author comercial.gvca
 */
public class Principal extends javax.swing.JFrame {

    Users users;
    About about;
    DatosEmpresa datosempresa;
    TipoServicios tiposervicios;
    Servicios servicios;
    Titulares titulares;
    Clientes clientes;
    DetallesDeControl detallesDeControl;

    public Principal() {
        initComponents();
        
        this.setLocationRelativeTo(null);// Centrar la ventana
        
        this.users = new Users();
        this.about = new About();
        this.datosempresa = new DatosEmpresa();
        this.tiposervicios = new TipoServicios();
        this.servicios = new Servicios();
        this.titulares = new Titulares();
        this.clientes = new Clientes();
        this.detallesDeControl = new DetallesDeControl();
        
        // Cambiar por la direccion donde se tiene la imagen de fondo que se quiera en el software
        Image img = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/fondo.jpg"));
        this.desktopPane.setImagen(img);        
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/logo.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        contenedorToolBar = new javax.swing.JToolBar();
        importarExportarToolBar = new javax.swing.JToolBar();
        importarButton = new javax.swing.JButton();
        exportarButton = new javax.swing.JButton();
        accionesToolBar = new javax.swing.JToolBar();
        clientesButton = new javax.swing.JButton();
        serviciosXClientesButton = new javax.swing.JButton();
        facturarButton = new javax.swing.JButton();
        consultasAFacturasButton = new javax.swing.JButton();
        informesToolBar = new javax.swing.JToolBar();
        FacturasButton = new javax.swing.JButton();
        cierreToolBar = new javax.swing.JToolBar();
        ficheroABancoButton = new javax.swing.JButton();
        cierreMesButton = new javax.swing.JButton();
        cobrosToolBar = new javax.swing.JToolBar();
        captarCobroButton = new javax.swing.JButton();
        desktopPane = new myclass.JDescktopPaneImage();
        usuarioToolBar = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        ficherosMenu = new javax.swing.JMenu();
        datosEmpresaMenuItem = new javax.swing.JMenuItem();
        tipoServiciosMenuItem = new javax.swing.JMenuItem();
        serviciosMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        titularesMenuItem = new javax.swing.JMenuItem();
        clientesMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        detallesControlMenuItem = new javax.swing.JMenuItem();
        produccionMenu = new javax.swing.JMenu();
        relacionSMMenuItem = new javax.swing.JMenuItem();
        servicioInclAlcMenuItem = new javax.swing.JMenuItem();
        servicioXclienteMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        facturacionMenuItem = new javax.swing.JMenuItem();
        consultasAfacturasMenuItem = new javax.swing.JMenuItem();
        informesMenu = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        datosCierreMenu = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        cobrosMenu = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        herramientasMenu = new javax.swing.JMenu();
        usersMenuItem = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        prodMenu = new javax.swing.JMenu();
        ayudaMunu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GEDSAA");
        setIconImage(getIconImage());

        contenedorToolBar.setFloatable(false);
        contenedorToolBar.setRollover(true);

        importarExportarToolBar.setRollover(true);
        importarExportarToolBar.setName("Imporar - Exportar"); // NOI18N

        importarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/importar.png"))); // NOI18N
        importarButton.setToolTipText("Importar");
        importarButton.setFocusable(false);
        importarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importarExportarToolBar.add(importarButton);

        exportarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/exportar.png"))); // NOI18N
        exportarButton.setToolTipText("Exportar");
        exportarButton.setFocusable(false);
        exportarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importarExportarToolBar.add(exportarButton);

        contenedorToolBar.add(importarExportarToolBar);

        accionesToolBar.setRollover(true);
        accionesToolBar.setName("Acciones"); // NOI18N

        clientesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/clientes.png"))); // NOI18N
        clientesButton.setToolTipText("Clientes");
        clientesButton.setFocusable(false);
        clientesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clientesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        accionesToolBar.add(clientesButton);

        serviciosXClientesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/serviciosXcliantes.png"))); // NOI18N
        serviciosXClientesButton.setToolTipText("Servicios por Clientes");
        serviciosXClientesButton.setFocusable(false);
        serviciosXClientesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        serviciosXClientesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        accionesToolBar.add(serviciosXClientesButton);

        facturarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/facturar.png"))); // NOI18N
        facturarButton.setToolTipText("Facturar");
        facturarButton.setFocusable(false);
        facturarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        facturarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        accionesToolBar.add(facturarButton);

        consultasAFacturasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/consultar_facturas.png"))); // NOI18N
        consultasAFacturasButton.setToolTipText("Consultas a Facturas");
        consultasAFacturasButton.setFocusable(false);
        consultasAFacturasButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        consultasAFacturasButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        accionesToolBar.add(consultasAFacturasButton);

        contenedorToolBar.add(accionesToolBar);

        informesToolBar.setRollover(true);
        informesToolBar.setName("Informes"); // NOI18N

        FacturasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/facturas.png"))); // NOI18N
        FacturasButton.setToolTipText("Facturas");
        FacturasButton.setFocusable(false);
        FacturasButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FacturasButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        informesToolBar.add(FacturasButton);

        contenedorToolBar.add(informesToolBar);

        cierreToolBar.setRollover(true);
        cierreToolBar.setName("Cierre de mes"); // NOI18N

        ficheroABancoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/fichero_a_banco.png"))); // NOI18N
        ficheroABancoButton.setToolTipText("Fichero que va al Banco");
        ficheroABancoButton.setFocusable(false);
        ficheroABancoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ficheroABancoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cierreToolBar.add(ficheroABancoButton);

        cierreMesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/cierre_mes.png"))); // NOI18N
        cierreMesButton.setToolTipText("Cerrar Mes");
        cierreMesButton.setFocusable(false);
        cierreMesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cierreMesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cierreToolBar.add(cierreMesButton);

        contenedorToolBar.add(cierreToolBar);

        cobrosToolBar.setRollover(true);
        cobrosToolBar.setName("Cobros"); // NOI18N

        captarCobroButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/capturar_facturas_cobros.png"))); // NOI18N
        captarCobroButton.setToolTipText("Captar Factura x Cobro");
        captarCobroButton.setFocusable(false);
        captarCobroButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        captarCobroButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cobrosToolBar.add(captarCobroButton);

        contenedorToolBar.add(cobrosToolBar);

        usuarioToolBar.setFloatable(false);
        usuarioToolBar.setRollover(true);

        jLabel1.setText("Usuario Autenticado:");
        usuarioToolBar.add(jLabel1);

        jLabel2.setText(" User Name");
        usuarioToolBar.add(jLabel2);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Archivo");

        importMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        importMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/importar.png"))); // NOI18N
        importMenuItem.setText("Importar");
        fileMenu.add(importMenuItem);

        exportMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/exportar.png"))); // NOI18N
        exportMenuItem.setText("Exportar");
        fileMenu.add(exportMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Salir");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        ficherosMenu.setText("Ficheros");

        datosEmpresaMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        datosEmpresaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/datos_empresa.png"))); // NOI18N
        datosEmpresaMenuItem.setText("Datos de la Empresa");
        ficherosMenu.add(datosEmpresaMenuItem);

        tipoServiciosMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        tipoServiciosMenuItem.setText("Tipos de Servicios");
        ficherosMenu.add(tipoServiciosMenuItem);

        serviciosMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        serviciosMenuItem.setText("Servicios");
        ficherosMenu.add(serviciosMenuItem);
        ficherosMenu.add(jSeparator1);

        titularesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        titularesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/titulares.png"))); // NOI18N
        titularesMenuItem.setText("Titulares");
        ficherosMenu.add(titularesMenuItem);

        clientesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        clientesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/clientes.png"))); // NOI18N
        clientesMenuItem.setText("Clientes");
        ficherosMenu.add(clientesMenuItem);
        ficherosMenu.add(jSeparator2);

        detallesControlMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        detallesControlMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/detalles_control.png"))); // NOI18N
        detallesControlMenuItem.setText("Detalles de Control");
        ficherosMenu.add(detallesControlMenuItem);

        menuBar.add(ficherosMenu);

        produccionMenu.setText("Producción");

        relacionSMMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/relacion_servicio_sobreconsumo.png"))); // NOI18N
        relacionSMMenuItem.setText("Relación de Servicio Metrado - Sobreconsumo");
        produccionMenu.add(relacionSMMenuItem);

        servicioInclAlcMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/servicios_alcantarillado.png"))); // NOI18N
        servicioInclAlcMenuItem.setText("Servicios que incluyen Alcantatrillado");
        produccionMenu.add(servicioInclAlcMenuItem);

        servicioXclienteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/serviciosXcliantes.png"))); // NOI18N
        servicioXclienteMenuItem.setText("Servicios por clientes");
        produccionMenu.add(servicioXclienteMenuItem);
        produccionMenu.add(jSeparator3);

        facturacionMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/facturar.png"))); // NOI18N
        facturacionMenuItem.setText("Facturar");
        produccionMenu.add(facturacionMenuItem);

        consultasAfacturasMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/consultar_facturas.png"))); // NOI18N
        consultasAfacturasMenuItem.setText("Consultas a Facturas");
        produccionMenu.add(consultasAfacturasMenuItem);

        menuBar.add(produccionMenu);

        informesMenu.setText("Informes");

        jMenuItem8.setText("Listado de Facturas");
        informesMenu.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/facturas.png"))); // NOI18N
        jMenuItem9.setText("Facturas");
        informesMenu.add(jMenuItem9);

        jMenuItem11.setText("Facturas Rechazadas");
        informesMenu.add(jMenuItem11);
        informesMenu.add(jSeparator4);

        jMenuItem12.setText("Listado de Titulares y Clientes");
        informesMenu.add(jMenuItem12);

        jMenu6.setText("Mes y Acomulado de Facturas");

        jMenuItem13.setText("En CUP");
        jMenu6.add(jMenuItem13);

        jMenuItem14.setText("En CUC");
        jMenu6.add(jMenuItem14);

        informesMenu.add(jMenu6);

        jMenuItem15.setText("Total Facturado Presupuestado");
        informesMenu.add(jMenuItem15);

        jMenuItem16.setText("Listado de Grandes Consumidores");
        informesMenu.add(jMenuItem16);

        jMenuItem17.setText("Listado por Sector");
        informesMenu.add(jMenuItem17);

        jMenu7.setText("Informe Metros");

        jMenuItem18.setText("Informe de Metros en CUP");
        jMenu7.add(jMenuItem18);

        jMenuItem19.setText("Informe de Metros en CUC");
        jMenu7.add(jMenuItem19);

        informesMenu.add(jMenu7);
        informesMenu.add(jSeparator5);

        jMenuItem20.setText("Comprobantes de Operaciones");
        informesMenu.add(jMenuItem20);
        informesMenu.add(jSeparator6);

        jMenu9.setText("Clientes sin Facturar");

        jMenuItem21.setText("Clientes sin Facturar en CUP");
        jMenu9.add(jMenuItem21);

        jMenuItem22.setText("Clientes sin Facturar en CUC");
        jMenu9.add(jMenuItem22);

        informesMenu.add(jMenu9);
        informesMenu.add(jSeparator7);

        jMenu8.setText("Historico");

        jMenuItem23.setText("Historicos de las Facturas");
        jMenu8.add(jMenuItem23);

        informesMenu.add(jMenu8);

        menuBar.add(informesMenu);

        datosCierreMenu.setText("Datos de Cierre");

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/fichero_a_banco.png"))); // NOI18N
        jMenuItem24.setText("Crear fichero que va al Banco");
        datosCierreMenu.add(jMenuItem24);

        jMenuItem25.setText("Consultar el fichero que va al Banco");
        datosCierreMenu.add(jMenuItem25);

        jMenuItem26.setText("Copiar al dispositivo de salida para enviar al Banco");
        datosCierreMenu.add(jMenuItem26);
        datosCierreMenu.add(jSeparator8);

        jMenuItem27.setText("Copiar fichero que viene del Banco");
        datosCierreMenu.add(jMenuItem27);

        jMenuItem28.setText("Consultar fichero que viene del Banco");
        datosCierreMenu.add(jMenuItem28);
        datosCierreMenu.add(jSeparator9);

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/cierre_mes.png"))); // NOI18N
        jMenuItem29.setText("Cierre de Mes");
        datosCierreMenu.add(jMenuItem29);

        menuBar.add(datosCierreMenu);

        cobrosMenu.setText("Cobros");

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/capturar_facturas_cobros.png"))); // NOI18N
        jMenuItem30.setText("Captar Facturas x Cobro");
        cobrosMenu.add(jMenuItem30);

        jMenuItem31.setText("Consulta de Facturas x Cobro");
        cobrosMenu.add(jMenuItem31);
        cobrosMenu.add(jSeparator11);

        jMenuItem32.setText("Cobros a Facturas");
        cobrosMenu.add(jMenuItem32);

        jMenuItem33.setText("Consultas a Cobros de Facturas");
        cobrosMenu.add(jMenuItem33);
        cobrosMenu.add(jSeparator10);

        jMenuItem34.setText("Registro de Cobros");
        cobrosMenu.add(jMenuItem34);

        jMenuItem35.setText("Reporte de Edades");
        cobrosMenu.add(jMenuItem35);

        menuBar.add(cobrosMenu);

        herramientasMenu.setMnemonic('e');
        herramientasMenu.setText("Herramientas");

        usersMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/usuarios.png"))); // NOI18N
        usersMenuItem.setMnemonic('d');
        usersMenuItem.setText("Usuarios");
        herramientasMenu.add(usersMenuItem);

        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconos/password.png"))); // NOI18N
        jMenuItem36.setText("Cambiar Contraseña");
        herramientasMenu.add(jMenuItem36);

        menuBar.add(herramientasMenu);

        prodMenu.setText("Productividad");
        menuBar.add(prodMenu);

        ayudaMunu.setMnemonic('h');
        ayudaMunu.setText("Ayuda");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contenido");
        ayudaMunu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Sobre Nosotros");
        ayudaMunu.add(aboutMenuItem);

        menuBar.add(ayudaMunu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(usuarioToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(contenedorToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FacturasButton;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JToolBar accionesToolBar;
    private javax.swing.JMenu ayudaMunu;
    private javax.swing.JButton captarCobroButton;
    private javax.swing.JButton cierreMesButton;
    private javax.swing.JToolBar cierreToolBar;
    private javax.swing.JButton clientesButton;
    private javax.swing.JMenuItem clientesMenuItem;
    private javax.swing.JMenu cobrosMenu;
    private javax.swing.JToolBar cobrosToolBar;
    private javax.swing.JButton consultasAFacturasButton;
    private javax.swing.JMenuItem consultasAfacturasMenuItem;
    private javax.swing.JToolBar contenedorToolBar;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenu datosCierreMenu;
    private javax.swing.JMenuItem datosEmpresaMenuItem;
    private myclass.JDescktopPaneImage desktopPane;
    private javax.swing.JMenuItem detallesControlMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JButton exportarButton;
    private javax.swing.JMenuItem facturacionMenuItem;
    private javax.swing.JButton facturarButton;
    private javax.swing.JButton ficheroABancoButton;
    private javax.swing.JMenu ficherosMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu herramientasMenu;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JButton importarButton;
    private javax.swing.JToolBar importarExportarToolBar;
    private javax.swing.JMenu informesMenu;
    private javax.swing.JToolBar informesToolBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu prodMenu;
    private javax.swing.JMenu produccionMenu;
    private javax.swing.JMenuItem relacionSMMenuItem;
    private javax.swing.JMenuItem servicioInclAlcMenuItem;
    private javax.swing.JMenuItem servicioXclienteMenuItem;
    private javax.swing.JMenuItem serviciosMenuItem;
    private javax.swing.JButton serviciosXClientesButton;
    private javax.swing.JMenuItem tipoServiciosMenuItem;
    private javax.swing.JMenuItem titularesMenuItem;
    private javax.swing.JMenuItem usersMenuItem;
    private javax.swing.JToolBar usuarioToolBar;
    // End of variables declaration//GEN-END:variables

}