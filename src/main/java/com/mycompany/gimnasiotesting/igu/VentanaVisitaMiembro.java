package com.mycompany.gimnasiotesting.igu;

import com.mycompany.gimnasiotesting.logica.Controladora;
import com.mycompany.gimnasiotesting.logica.Membresia;
import com.mycompany.gimnasiotesting.logica.Miembro;
import com.mycompany.gimnasiotesting.logica.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaVisitaMiembro extends javax.swing.JDialog {
    
    Controladora control = null;
    Usuario usuario;
    private Map<String, Miembro> cmbMiembMap;

    public VentanaVisitaMiembro(JPanel parent, Usuario usuario) {
        super(SwingUtilities.getWindowAncestor(parent), "Ingreso de Datos", ModalityType.APPLICATION_MODAL);
        this.usuario = usuario;
        this.cmbMiembMap = new HashMap<>();
        control = new Controladora();
        initComponents();
        cargarMiembrosEnComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cmbMiemb = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtDiasVisita = new javax.swing.JTextField();
        txtSexo = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INGRESE LOS DATOS PARA REGISTRAR LA VISITA");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccionar miembro");

        btnCerrar.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnGuardar.setText("Registrar visita");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cmbMiemb.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        cmbMiemb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMiembActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMiemb, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cmbMiemb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Datos del miembro:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        txtApellido.setEditable(false);
        txtApellido.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        txtMatricula.setEditable(false);
        txtMatricula.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        txtDiasVisita.setEditable(false);
        txtDiasVisita.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        txtSexo.setEditable(false);
        txtSexo.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        txtDni.setEditable(false);
        txtDni.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Matrícula");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Días de visita");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DNI");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Sexo");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Estado");

        txtEstado.setEditable(false);
        txtEstado.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtDiasVisita)
                        .addComponent(txtMatricula)
                        .addComponent(txtApellido)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiasVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String matriculaMiembro = (String) cmbMiemb.getSelectedItem();
        Miembro miembro = cmbMiembMap.get(matriculaMiembro);    
        int cantDiasVisitados = control.obtenerDiasVisitados(miembro);
        
        Membresia miembro_membresia = miembro.getMiembro_membresia();  
        
        if(!(miembro.getEstado().equals("ACTIVO"))){
            mostrarMensaje("El miembro no tiene una membresía asignada", "Error", "Error al registrar la visita");        
        }else if(cantDiasVisitados >= miembro_membresia.getDiasXSemana()){
            mostrarMensaje("El miembro ya cumplió con todas las visitas de la semana", "Error", "Error al registrar la visita");  
        }else{
            control.registrarVisita(usuario, miembro_membresia, miembro);
            mostrarMensaje("Visita registrada con éxito", "Info", "Éxito con la visita");
            this.dispose();
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cargarMiembrosEnComboBox() {
        // Obtener la lista de membresías desde la controladora
        List<Miembro> miembros = control.traerMiembros();

        // Limpiar el JComboBox
        cmbMiemb.removeAllItems();

        // Llenar el JComboBox con los nombres de las membresías
        for (Miembro miembro : miembros) {
            cmbMiemb.addItem(miembro.getMatricula());
            cmbMiembMap.put(miembro.getMatricula(), miembro);
        }
    }
    
    private void cmbMiembActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMiembActionPerformed
        actualizarMiembro();
    }//GEN-LAST:event_cmbMiembActionPerformed

    
    private void actualizarMiembro() {
        String matriculaMiembro = (String) cmbMiemb.getSelectedItem();
        if (matriculaMiembro != null && cmbMiembMap.containsKey(matriculaMiembro)) {
            Miembro miembro = cmbMiembMap.get(matriculaMiembro);
            txtNombre.setText(miembro.getNombre());
            txtApellido.setText(miembro.getApellido());
            txtMatricula.setText(miembro.getMatricula());
            txtDni.setText(miembro.getDni());
            txtSexo.setText(miembro.getSexo());
            txtEstado.setText(miembro.getEstado());
            
            if(miembro.getMiembro_membresia() != null){
                String cantDiasVisitados = String.valueOf(control.obtenerDiasVisitados(miembro));
                String diasHabilesMembresia = String.valueOf(miembro.getMiembro_membresia().getDiasXSemana());

                txtDiasVisita.setText(cantDiasVisitados + "/" + diasHabilesMembresia);
            }else{
                txtDiasVisita.setText("");
            }
        }      
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbMiemb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDiasVisita;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSexo;
    // End of variables declaration//GEN-END:variables

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
}
