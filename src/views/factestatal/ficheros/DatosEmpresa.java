/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factestatal.ficheros;

/**
 *
 * @author comercial.gvca
 */
public class DatosEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form DatosEmpresa
     */
    public DatosEmpresa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logotipoFileChooser = new javax.swing.JFileChooser();
        contenedorTabbedPane = new javax.swing.JTabbedPane();
        datosTrabajoPanel = new javax.swing.JPanel();
        codigoReupLabel = new javax.swing.JLabel();
        tutularesCuentaPanel = new javax.swing.JPanel();
        titularCuentaCUPLabel = new javax.swing.JLabel();
        titularCuentaCUCLabel = new javax.swing.JLabel();
        titularCuentaCUPTextField = new javax.swing.JTextField();
        titularCuentaCUCTextField = new javax.swing.JTextField();
        cuentaBancariaPanel = new javax.swing.JPanel();
        cuentaBancariaCUPLabel = new javax.swing.JLabel();
        cuentaBancariaCUCLabel = new javax.swing.JLabel();
        cuentaBancariaCUPTextField = new javax.swing.JTextField();
        cuentaBancariaCUCTextField = new javax.swing.JTextField();
        logotipoEmpresaPanel = new javax.swing.JPanel();
        logotipoEmpresaPanelImage = new myclass.JPanelImage();
        buscarLogotipoButton = new javax.swing.JButton();
        codigoReupFormattedTextField = new javax.swing.JFormattedTextField();
        nombreEmpresaLabel = new javax.swing.JLabel();
        nombreEmpresaTextField = new javax.swing.JTextField();
        datosGeneralesPanel = new javax.swing.JPanel();
        organismoLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        faxLabel = new javax.swing.JLabel();
        correoElectronicoLabel = new javax.swing.JLabel();
        direccionLabel1 = new javax.swing.JLabel();
        organismoComboBox = new javax.swing.JComboBox();
        telefonoFormattedTextField = new javax.swing.JFormattedTextField();
        faxFormattedTextField = new javax.swing.JFormattedTextField();
        correoElectronicoTextField = new javax.swing.JTextField();
        direccionScrollPane = new javax.swing.JScrollPane();
        direccionTextArea = new javax.swing.JTextArea();
        agregarModificarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Datos de la Empresa");
        setToolTipText("Datos necesarios para la configuración del sistema");
        setFrameIcon(null);

        codigoReupLabel.setText("Código Reup:");

        tutularesCuentaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulares de Cuenta"));

        titularCuentaCUPLabel.setText("Titular de la cuenta CUP:");

        titularCuentaCUCLabel.setText("Titular de la custa CUC:");

        titularCuentaCUPTextField.setToolTipText("Entre el titular de la cuenta CUP");

        titularCuentaCUCTextField.setToolTipText("Entre el titular de la cuenta CUC");

        javax.swing.GroupLayout tutularesCuentaPanelLayout = new javax.swing.GroupLayout(tutularesCuentaPanel);
        tutularesCuentaPanel.setLayout(tutularesCuentaPanelLayout);
        tutularesCuentaPanelLayout.setHorizontalGroup(
            tutularesCuentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tutularesCuentaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tutularesCuentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tutularesCuentaPanelLayout.createSequentialGroup()
                        .addComponent(titularCuentaCUPLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titularCuentaCUPTextField))
                    .addGroup(tutularesCuentaPanelLayout.createSequentialGroup()
                        .addComponent(titularCuentaCUCLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titularCuentaCUCTextField)))
                .addContainerGap())
        );
        tutularesCuentaPanelLayout.setVerticalGroup(
            tutularesCuentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tutularesCuentaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tutularesCuentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titularCuentaCUPLabel)
                    .addComponent(titularCuentaCUPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tutularesCuentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titularCuentaCUCLabel)
                    .addComponent(titularCuentaCUCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cuentaBancariaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuentas bancarias"));

        cuentaBancariaCUPLabel.setText("Cuenta bancaria CUP:");

        cuentaBancariaCUCLabel.setText("Cuenta bancaria CUC:");

        cuentaBancariaCUPTextField.setToolTipText("Entre la cuenta bancaria en CUP");

        cuentaBancariaCUCTextField.setToolTipText("Entre la cuenta bancaria en CUC");

        javax.swing.GroupLayout cuentaBancariaPanelLayout = new javax.swing.GroupLayout(cuentaBancariaPanel);
        cuentaBancariaPanel.setLayout(cuentaBancariaPanelLayout);
        cuentaBancariaPanelLayout.setHorizontalGroup(
            cuentaBancariaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cuentaBancariaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cuentaBancariaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cuentaBancariaPanelLayout.createSequentialGroup()
                        .addComponent(cuentaBancariaCUPLabel)
                        .addGap(18, 18, 18)
                        .addComponent(cuentaBancariaCUPTextField))
                    .addGroup(cuentaBancariaPanelLayout.createSequentialGroup()
                        .addComponent(cuentaBancariaCUCLabel)
                        .addGap(18, 18, 18)
                        .addComponent(cuentaBancariaCUCTextField)))
                .addContainerGap())
        );
        cuentaBancariaPanelLayout.setVerticalGroup(
            cuentaBancariaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cuentaBancariaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cuentaBancariaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuentaBancariaCUPLabel)
                    .addComponent(cuentaBancariaCUPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cuentaBancariaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuentaBancariaCUCLabel)
                    .addComponent(cuentaBancariaCUCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        logotipoEmpresaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Logotipo de la Empresa"));

        logotipoEmpresaPanelImage.setBackground(new java.awt.Color(153, 204, 255));
        logotipoEmpresaPanelImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logotipoEmpresaPanelImage.setForeground(new java.awt.Color(204, 204, 204));
        logotipoEmpresaPanelImage.setToolTipText("Logotipo de la Empresa");

        javax.swing.GroupLayout logotipoEmpresaPanelImageLayout = new javax.swing.GroupLayout(logotipoEmpresaPanelImage);
        logotipoEmpresaPanelImage.setLayout(logotipoEmpresaPanelImageLayout);
        logotipoEmpresaPanelImageLayout.setHorizontalGroup(
            logotipoEmpresaPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );
        logotipoEmpresaPanelImageLayout.setVerticalGroup(
            logotipoEmpresaPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        buscarLogotipoButton.setText("Buscar Logotipo");

        javax.swing.GroupLayout logotipoEmpresaPanelLayout = new javax.swing.GroupLayout(logotipoEmpresaPanel);
        logotipoEmpresaPanel.setLayout(logotipoEmpresaPanelLayout);
        logotipoEmpresaPanelLayout.setHorizontalGroup(
            logotipoEmpresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logotipoEmpresaPanelLayout.createSequentialGroup()
                .addComponent(logotipoEmpresaPanelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarLogotipoButton)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        logotipoEmpresaPanelLayout.setVerticalGroup(
            logotipoEmpresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logotipoEmpresaPanelLayout.createSequentialGroup()
                .addComponent(logotipoEmpresaPanelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logotipoEmpresaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscarLogotipoButton)
                .addContainerGap())
        );

        try {
            codigoReupFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.#.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        codigoReupFormattedTextField.setToolTipText("Entre el código reup de la empresa");

        nombreEmpresaLabel.setText("Nombre de la Empresa:");

        javax.swing.GroupLayout datosTrabajoPanelLayout = new javax.swing.GroupLayout(datosTrabajoPanel);
        datosTrabajoPanel.setLayout(datosTrabajoPanelLayout);
        datosTrabajoPanelLayout.setHorizontalGroup(
            datosTrabajoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tutularesCuentaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cuentaBancariaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logotipoEmpresaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(datosTrabajoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosTrabajoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(datosTrabajoPanelLayout.createSequentialGroup()
                        .addComponent(codigoReupLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoReupFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datosTrabajoPanelLayout.createSequentialGroup()
                        .addComponent(nombreEmpresaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreEmpresaTextField)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        datosTrabajoPanelLayout.setVerticalGroup(
            datosTrabajoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosTrabajoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosTrabajoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreEmpresaLabel)
                    .addComponent(nombreEmpresaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosTrabajoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoReupLabel)
                    .addComponent(codigoReupFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tutularesCuentaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuentaBancariaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logotipoEmpresaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedorTabbedPane.addTab("Datos de Trabajo", datosTrabajoPanel);

        organismoLabel.setText("Organismo:");

        telefonoLabel.setText("Teléfono:");

        faxLabel.setText("Fax:");

        correoElectronicoLabel.setText("Correo electronico:");

        direccionLabel1.setText("Dirección:");

        organismoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organismoComboBox.setToolTipText("Organismo al que pertenece la empresa");

        try {
            telefonoFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+53 ## ######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefonoFormattedTextField.setToolTipText("Teléfono de la empresa");

        try {
            faxFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+53 ## ######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        faxFormattedTextField.setToolTipText("Fax de la Empresa");

        correoElectronicoTextField.setToolTipText("Entre Correo Electronico de la Empresa");

        direccionTextArea.setColumns(20);
        direccionTextArea.setRows(5);
        direccionTextArea.setToolTipText("Entre la dirección donde se encuentra la empresa");
        direccionScrollPane.setViewportView(direccionTextArea);

        javax.swing.GroupLayout datosGeneralesPanelLayout = new javax.swing.GroupLayout(datosGeneralesPanel);
        datosGeneralesPanel.setLayout(datosGeneralesPanelLayout);
        datosGeneralesPanelLayout.setHorizontalGroup(
            datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosGeneralesPanelLayout.createSequentialGroup()
                        .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonoLabel)
                            .addComponent(organismoLabel))
                        .addGap(41, 41, 41)
                        .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonoFormattedTextField)
                            .addComponent(organismoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(datosGeneralesPanelLayout.createSequentialGroup()
                        .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(correoElectronicoLabel)
                            .addComponent(faxLabel)
                            .addComponent(direccionLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(faxFormattedTextField)
                            .addComponent(direccionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(correoElectronicoTextField))))
                .addContainerGap())
        );
        datosGeneralesPanelLayout.setVerticalGroup(
            datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(organismoLabel)
                    .addComponent(organismoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faxLabel)
                    .addComponent(faxFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correoElectronicoLabel)
                    .addComponent(correoElectronicoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(direccionLabel1)
                    .addComponent(direccionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        contenedorTabbedPane.addTab("Datos Generales", datosGeneralesPanel);

        agregarModificarButton.setText("Agregar/ Modificar");

        cancelarButton.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorTabbedPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(agregarModificarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(contenedorTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarButton)
                    .addComponent(agregarModificarButton, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton agregarModificarButton;
    public javax.swing.JButton buscarLogotipoButton;
    public javax.swing.JButton cancelarButton;
    public javax.swing.JFormattedTextField codigoReupFormattedTextField;
    private javax.swing.JLabel codigoReupLabel;
    public javax.swing.JTabbedPane contenedorTabbedPane;
    private javax.swing.JLabel correoElectronicoLabel;
    public javax.swing.JTextField correoElectronicoTextField;
    private javax.swing.JLabel cuentaBancariaCUCLabel;
    public javax.swing.JTextField cuentaBancariaCUCTextField;
    private javax.swing.JLabel cuentaBancariaCUPLabel;
    public javax.swing.JTextField cuentaBancariaCUPTextField;
    private javax.swing.JPanel cuentaBancariaPanel;
    public javax.swing.JPanel datosGeneralesPanel;
    private javax.swing.JPanel datosTrabajoPanel;
    private javax.swing.JLabel direccionLabel1;
    public javax.swing.JScrollPane direccionScrollPane;
    public javax.swing.JTextArea direccionTextArea;
    public javax.swing.JFormattedTextField faxFormattedTextField;
    private javax.swing.JLabel faxLabel;
    private javax.swing.JPanel logotipoEmpresaPanel;
    public myclass.JPanelImage logotipoEmpresaPanelImage;
    public javax.swing.JFileChooser logotipoFileChooser;
    private javax.swing.JLabel nombreEmpresaLabel;
    public javax.swing.JTextField nombreEmpresaTextField;
    public javax.swing.JComboBox organismoComboBox;
    private javax.swing.JLabel organismoLabel;
    public javax.swing.JFormattedTextField telefonoFormattedTextField;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JLabel titularCuentaCUCLabel;
    public javax.swing.JTextField titularCuentaCUCTextField;
    private javax.swing.JLabel titularCuentaCUPLabel;
    public javax.swing.JTextField titularCuentaCUPTextField;
    private javax.swing.JPanel tutularesCuentaPanel;
    // End of variables declaration//GEN-END:variables
}
