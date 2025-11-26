/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios;

import Controller.UsuarioController;
import entity.Area;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmGestionUsuario extends javax.swing.JInternalFrame {

    private UsuarioController control = new UsuarioController();

    public frmGestionUsuario() {
        initComponents();
        configurarFormulario();
    }

    private void configurarFormulario() {
        DefaultComboBoxModel modeloCombo = (DefaultComboBoxModel) cboArea.getModel();
        control.llenarComboArea(modeloCombo);
        cargartabla();
    }

    private void cargartabla() {
        DefaultTableModel modelTabla = (DefaultTableModel) tblUsuarios.getModel();
        control.llenarTabal(modelTabla);
    }

  private void limpiar() {
        txtUsuario.setText("");
        txtPass.setText("");
        cboRol.setSelectedIndex(0);
        if(cboRol.getItemCount()>0){cboRol.setSelectedIndex(0);}
        if (cboArea.getItemCount() > 0) cboArea.setSelectedIndex(0);
        
        tblUsuarios.clearSelection();
        tblUsuarios.getSelectionModel().clearSelection();
        
        tblUsuarios.clearSelection(); 
        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(false);  
        btnEliminar.setEnabled(false); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        cboRol = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cboArea = new javax.swing.JComboBox<>();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Área Asignada:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 190, 40));
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 190, 40));

        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Agente", "Tecnico" }));
        cboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRolActionPerformed(evt);
            }
        });
        jPanel1.add(cboRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 190, 50));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Rol", "Área"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 590, 270));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Rol:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jPanel1.add(cboArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 190, 50));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRolActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtUsuario.getText();
        String pass = new String(txtPass.getPassword());
        String rol = cboRol.getSelectedItem().toString();

        if (cboArea.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe selccioanr un área");
            return;
        }
        
        entity.Area areaSelecionada=(entity.Area) cboArea.getSelectedItem();
        
        if(nombre.isEmpty() || pass.isEmpty()){
        JOptionPane.showMessageDialog(this, "Complete los campos");
        return;
        }
      
        boolean exito=control.registrarUsuario(nombre, pass, rol, areaSelecionada);
        
        if(exito){
        JOptionPane.showMessageDialog(this, "Usuario registrado y asignado a "+areaSelecionada.getNombreArea());
        cargartabla();
        limpiar();
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked

        int fila = tblUsuarios.getSelectedRow();
        if (fila >= 0) {
           
            txtUsuario.setText(tblUsuarios.getValueAt(fila, 1).toString());
            txtPass.setText(""); 
            cboRol.setSelectedItem(tblUsuarios.getValueAt(fila, 2).toString());
            String nombreAreaTabla = tblUsuarios.getValueAt(fila, 3).toString();
            for (int i = 0; i < cboArea.getItemCount(); i++) {
                Area a = (Area) 
                        cboArea.getSelectedItem();
                if (a.getNombreArea().equals(nombreAreaTabla)) {
                    cboArea.setSelectedIndex(i);
                    break;
                }
            }
            btnGuardar.setEnabled(false);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }

        int idUsuario = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());

        String nombre = txtUsuario.getText();
        String pass = new String(txtPass.getPassword());
        String rol = cboRol.getSelectedItem().toString();
        entity.Area area = (entity.Area) cboArea.getSelectedItem();

        if (nombre.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos (incluyendo contraseña nueva)");
            return;
        }

        boolean exito = control.actualizarUsuario(idUsuario, nombre, pass, rol, area);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
            cargartabla();
            limpiar(); 
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            
            int idUsuario = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
            
            boolean exito = control.eliminarUsuario(idUsuario);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado");
                cargartabla();
                limpiar();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       limpiar();
       txtUsuario.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cboArea;
    public javax.swing.JComboBox<String> cboRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblUsuarios;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
