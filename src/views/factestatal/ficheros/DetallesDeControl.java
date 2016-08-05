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
public class DetallesDeControl extends javax.swing.JInternalFrame {

    /**
     * Creates new form DetallesDeControl
     */
    public DetallesDeControl() {
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

        pieFacturaPanel = new javax.swing.JPanel();
        realizadoPorLabel = new javax.swing.JLabel();
        cargoLabel = new javax.swing.JLabel();
        realizadoPorTextField = new javax.swing.JTextField();
        cargoTextField = new javax.swing.JTextField();
        avisoVencimientoContradoLabel = new javax.swing.JLabel();
        mesesPromediarLecturaLabel = new javax.swing.JLabel();
        avisoVencimientoContratoFormattedTextField = new javax.swing.JFormattedTextField();
        mesesPromediarLecturaFormattedTextField = new javax.swing.JFormattedTextField();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setClosable(true);
        setTitle("Detalles de Control");
        setFrameIcon(new javax.swing.ImageIcon("C:\\Users\\comercial.gvca\\Documents\\NetBeansProjects\\FactEstatal\\images\\detalles_control.png")); // NOI18N

        pieFacturaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pie de la Factura"));

        realizadoPorLabel.setText("Ralizado por:");

        cargoLabel.setText("Cargo:");

        javax.swing.GroupLayout pieFacturaPanelLayout = new javax.swing.GroupLayout(pieFacturaPanel);
        pieFacturaPanel.setLayout(pieFacturaPanelLayout);
        pieFacturaPanelLayout.setHorizontalGroup(
            pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pieFacturaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(realizadoPorLabel)
                    .addComponent(cargoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(realizadoPorTextField)
                    .addComponent(cargoTextField))
                .addContainerGap())
        );
        pieFacturaPanelLayout.setVerticalGroup(
            pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pieFacturaPanelLayout.createSequentialGroup()
                .addGroup(pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realizadoPorLabel)
                    .addComponent(realizadoPorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pieFacturaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargoLabel)
                    .addComponent(cargoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        avisoVencimientoContradoLabel.setText("Aviso de Vencimiento Contrato:");

        mesesPromediarLecturaLabel.setText("Meses a Promediar Lectura:");

        avisoVencimientoContratoFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        mesesPromediarLecturaFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pieFacturaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(avisoVencimientoContradoLabel)
                            .addComponent(mesesPromediarLecturaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(avisoVencimientoContratoFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(mesesPromediarLecturaFormattedTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aceptarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieFacturaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(avisoVencimientoContradoLabel)
                    .addComponent(avisoVencimientoContratoFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesesPromediarLecturaLabel)
                    .addComponent(mesesPromediarLecturaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JLabel avisoVencimientoContradoLabel;
    private javax.swing.JFormattedTextField avisoVencimientoContratoFormattedTextField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel cargoLabel;
    private javax.swing.JTextField cargoTextField;
    private javax.swing.JFormattedTextField mesesPromediarLecturaFormattedTextField;
    private javax.swing.JLabel mesesPromediarLecturaLabel;
    private javax.swing.JPanel pieFacturaPanel;
    private javax.swing.JLabel realizadoPorLabel;
    private javax.swing.JTextField realizadoPorTextField;
    // End of variables declaration//GEN-END:variables
}