package formularios;

import Controller.ReclamoController;
import builder.ReclamoBuilder;
import entity.Cliente;
import entity.Reclamo;
import factory.FabricaReclamos;
import factory.ReclamoFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class formRegistrarReclamo extends javax.swing.JFrame {

    
    private ReclamoController controller = new ReclamoController();
    private int idClienteEncontrado = -1;
    
    
    public formRegistrarReclamo() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblDatos = new javax.swing.JLabel();
        lblN = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblT = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        lblContrato = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblArea = new javax.swing.JLabel();
        cboArea = new javax.swing.JComboBox<>();
        lblCanal = new javax.swing.JLabel();
        cboCanal = new javax.swing.JComboBox<>();
        lblDesc = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDni.setText("DNI del Cliente: ");
        jPanel1.add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 30));

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 230, 30));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 120, 30));

        lblDatos.setText("Datos del Cliente:");
        jPanel1.add(lblDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 30));

        lblN.setText("Nombre:");
        jPanel1.add(lblN, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, 30));

        lblNombre.setText("-");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 300, 30));

        lblT.setText("Teléfono:");
        jPanel1.add(lblT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 30));

        lblTelefono.setText("-");
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 300, 30));

        lblC.setText("Contrato:");
        jPanel1.add(lblC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 100, 30));

        lblContrato.setText("-");
        jPanel1.add(lblContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 300, 30));

        lblTipo.setText("Tipo de Reclamo");
        jPanel1.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 30));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Técnico", "Facturación", "Servicio" }));
        cboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoActionPerformed(evt);
            }
        });
        jPanel1.add(cboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 200, 30));

        lblArea.setText("Área Asignada:");
        jPanel1.add(lblArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 150, 30));

        cboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Área Técnica", "Facturación", "Atención al Cliente" }));
        cboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAreaActionPerformed(evt);
            }
        });
        jPanel1.add(cboArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 200, 30));

        lblCanal.setText("Canal de Ingreso:");
        jPanel1.add(lblCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 150, 30));

        cboCanal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Call Center", "Web", "App" }));
        cboCanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCanalActionPerformed(evt);
            }
        });
        jPanel1.add(cboCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 200, 30));

        lblDesc.setText("Descripción:");
        jPanel1.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 150, 30));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        scroll.setViewportView(jScrollPane1);

        jPanel1.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 540, 100));

        btnRegistrar.setText("Registrar Reclamo");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void cboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoActionPerformed

    private void cboAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAreaActionPerformed

    private void cboCanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCanalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCanalActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void buscar(){        
        String dni = txtDNI.getText().trim();
    
        if(dni.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ingrese un DNI.");
            return;
        }

        try {
            Cliente c = controller.buscarCliente(dni);

            if (c != null) {
                idClienteEncontrado = c.getIdCliente();
                lblNombre.setText(c.getNombre() + " " + c.getApellidos());
                lblTelefono.setText(c.getTelefono());
                lblContrato.setText(c.getNumeroContrato());
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void registrar(){        
        if (idClienteEncontrado == -1) {            
            JOptionPane.showMessageDialog(this, "Debe buscar un cliente primero.");
            return;
        }
        //Aplicando el factory
        ReclamoFactory factory = FabricaReclamos.getFactory(cboTipo.getSelectedItem().toString());
        Reclamo base = factory.crearReclamo();
        //Aplicando el builder
        Reclamo reclamo = new ReclamoBuilder(base)                
                .descripcion(txtDescripcion.getText())
                .canal(cboCanal.getSelectedItem().toString())
                .cliente(idClienteEncontrado)
                .area(cboArea.getSelectedIndex() + 1)
                .usuario(1)   // usuario logueado
                .build();        
        try {            
            if (controller.registrarReclamo(reclamo)) {
                JOptionPane.showMessageDialog(this, "Reclamo registrado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el reclamo.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
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
            java.util.logging.Logger.getLogger(formRegistrarReclamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formRegistrarReclamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formRegistrarReclamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formRegistrarReclamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formRegistrarReclamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cboArea;
    private javax.swing.JComboBox<String> cboCanal;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblCanal;
    private javax.swing.JLabel lblContrato;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblN;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblT;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
