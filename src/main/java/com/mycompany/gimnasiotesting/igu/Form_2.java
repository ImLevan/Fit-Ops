package com.mycompany.gimnasiotesting.igu;

import com.mycompany.gimnasiotesting.logica.Controladora;
import com.mycompany.gimnasiotesting.logica.Miembro;
import com.mycompany.gimnasiotesting.logica.Usuario;
import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Form_2 extends javax.swing.JPanel {
    
    Controladora control = null;
    Usuario usuario;
    
    public Form_2(Usuario usuario) {
        this.usuario = usuario;
        control = new Controladora();
        initComponents();
        spTable.getViewport().setBackground(new Color(102, 102, 102));
    }
    
    public void cargarDatos() {
        table.setRowCount(0);
        
        List<Miembro> listaMiembros = control.traerMiembros();
        
        if (listaMiembros != null) {
            for (Miembro miembro : listaMiembros) {
                String fechaInicioFormateada = "";
                String fechaFinFormateada = "";
                if(miembro.getFechaInicio() != null){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    fechaInicioFormateada = miembro.getFechaInicio().format(formatter);
                }
                if(miembro.getFechaFin() != null){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    fechaFinFormateada = miembro.getFechaFin().format(formatter);
                }
                Object[] objeto = {
                        miembro.getId(),
                        miembro.getMatricula(),
                        miembro.getNombre(),
                        miembro.getEstado(),
                        (miembro.getFechaInicio() != null) ? fechaInicioFormateada : "",
                        (miembro.getFechaFin() != null) ? fechaFinFormateada : "",
                        (miembro.getMiembro_membresia() != null) ? miembro.getMiembro_membresia().getNombre() : ""
                };
                
                table.addRow(objeto);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.gimnasiotesting.igu.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.mycompany.gimnasiotesting.igu.Table();
        jLabel2 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCamMembr = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));

        panelBorder1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Lista de miembros");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Matricula", "Nombre", "Estado", "Inicio membresía", "Fin membresía", "Membresía"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setGridColor(new java.awt.Color(102, 102, 102));
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MIEMBROS");

        btnEditar.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCamMembr.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnCamMembr.setText("Cambiar Membresía");
        btnCamMembr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamMembrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(btnCamMembr))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34))
                    .addComponent(btnCamMembr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(table.getRowCount() > 0){
            if(table.getSelectedRow() != -1){
                int num_miembro = Integer.parseInt (String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
                
                
                ModificarMiembro pantallaModif = new ModificarMiembro(num_miembro);
                pantallaModif.setForm2Reference(this);
                pantallaModif.setVisible(true);
                pantallaModif.setLocationRelativeTo(null);
                
            }
            else{
                mostrarMensaje("No se seleccionó ningun miembro", "Error", "Error al editar");
            }
        }
        else{
            mostrarMensaje("No hay nada para editar en la tabla", "Error", "Error al editar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(table.getRowCount() > 0){
            if(table.getSelectedRow() != -1){
                int num_miembro = Integer.parseInt (String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
                control.borrarMiembro(num_miembro);
                
                
                mostrarMensaje("Miembro eliminado con exito", "Info", "Borrado de miembro");
                cargarDatos();
            }
            else{
                mostrarMensaje("No se seleccionó ningun miembro", "Error", "Error al eliminar");
            }
        }
        else{
            mostrarMensaje("No hay nada para eliminar en la tabla", "Error", "Error al eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCamMembrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamMembrActionPerformed
        if(table.getRowCount() > 0){
            if(table.getSelectedRow() != -1){
                int num_miembro = Integer.parseInt (String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));              
                Miembro miembroElegido = control.traerMiembro(num_miembro);
                
                HacerPagoMembresia pantallaPago = new HacerPagoMembresia(miembroElegido, usuario);
                pantallaPago.setForm_2Reference(this);
                pantallaPago.setVisible(true);
                pantallaPago.setLocationRelativeTo(null);
            }
            else{
                mostrarMensaje("No se seleccionó ningun miembro", "Error", "Error al modificar membresía");
            }
        }
        else{
            mostrarMensaje("No hay nada para modificar membresia en la tabla", "Error", "Error al modificar membresía");
        }
    }//GEN-LAST:event_btnCamMembrActionPerformed

    public void mostrarMensaje (String mensaje, String tipo, String titulo){
        JOptionPane optionPane = new JOptionPane(mensaje);
        
        if(tipo.equals("Info")){
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        }else if(tipo.equals("Error")){
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCamMembr;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.mycompany.gimnasiotesting.igu.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.mycompany.gimnasiotesting.igu.Table table;
    // End of variables declaration//GEN-END:variables
}
