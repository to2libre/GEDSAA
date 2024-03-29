/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views.factestatal.ficheros;

/**
 *
 * @author carlos
 */
public class MetrosMarca extends javax.swing.JInternalFrame {

    /**
     * Creates new form MetrosMarca
     */
    public MetrosMarca() {
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

        agregarModificarMarcaPanel = new javax.swing.JPanel();
        marcaLabel = new javax.swing.JLabel();
        marcaTextField = new javax.swing.JTextField();
        agragarActualizarButton = new javax.swing.JButton();
        visualizarPanel = new javax.swing.JPanel();
        marcasScrollPane = new javax.swing.JScrollPane();
        marcasList = new javax.swing.JList();
        modificarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();

        setClosable(true);
        setTitle("Metros - Marcas ...");

        agregarModificarMarcaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar/Actualizar"));

        marcaLabel.setText("Marca:");

        agragarActualizarButton.setText("Agregar/Actualizar");

        javax.swing.GroupLayout agregarModificarMarcaPanelLayout = new javax.swing.GroupLayout(agregarModificarMarcaPanel);
        agregarModificarMarcaPanel.setLayout(agregarModificarMarcaPanelLayout);
        agregarModificarMarcaPanelLayout.setHorizontalGroup(
            agregarModificarMarcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarModificarMarcaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarModificarMarcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarModificarMarcaPanelLayout.createSequentialGroup()
                        .addComponent(marcaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcaTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarModificarMarcaPanelLayout.createSequentialGroup()
                        .addGap(0, 123, Short.MAX_VALUE)
                        .addComponent(agragarActualizarButton)))
                .addContainerGap())
        );
        agregarModificarMarcaPanelLayout.setVerticalGroup(
            agregarModificarMarcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarModificarMarcaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarModificarMarcaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marcaLabel)
                    .addComponent(marcaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agragarActualizarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        marcasList.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualizar"));
        marcasList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        marcasScrollPane.setViewportView(marcasList);

        modificarButton.setText("Modificar");

        eliminarButton.setText("Eliminar");

        javax.swing.GroupLayout visualizarPanelLayout = new javax.swing.GroupLayout(visualizarPanel);
        visualizarPanel.setLayout(visualizarPanelLayout);
        visualizarPanelLayout.setHorizontalGroup(
            visualizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marcasScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visualizarPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(modificarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarButton)))
                .addContainerGap())
        );
        visualizarPanelLayout.setVerticalGroup(
            visualizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marcasScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarButton)
                    .addComponent(modificarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visualizarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarModificarMarcaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregarModificarMarcaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visualizarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton agragarActualizarButton;
    private javax.swing.JPanel agregarModificarMarcaPanel;
    public javax.swing.JButton eliminarButton;
    private javax.swing.JLabel marcaLabel;
    public javax.swing.JTextField marcaTextField;
    public javax.swing.JList marcasList;
    private javax.swing.JScrollPane marcasScrollPane;
    public javax.swing.JButton modificarButton;
    private javax.swing.JPanel visualizarPanel;
    // End of variables declaration//GEN-END:variables
}
