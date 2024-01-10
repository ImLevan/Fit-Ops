package com.mycompany.gimnasiotesting.igu;

import com.mycompany.gimnasiotesting.logica.Usuario;
import java.awt.Color;
import javax.swing.JComponent;

public class Principal extends javax.swing.JFrame {
    
    private Usuario usuario;
    private Form_Home home;
    private Form_2 form2; 
    private CargarDatos cargaDatos;
    private Form_Usuarios formUsuarios;
    private Form_Membresia formMembresia;
    private Form_Pagos formPagos;
    private Form_Visitas formVisitas;
    private Form_HistorialVentas formHistorial;

    public Principal(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        home = new Form_Home();
        form2 = new Form_2(usuario);
        cargaDatos = new CargarDatos();
        formUsuarios = new Form_Usuarios();
        formMembresia = new Form_Membresia();
        formPagos = new Form_Pagos();
        formVisitas = new Form_Visitas(usuario);
        formHistorial = new Form_HistorialVentas();
        menu.initMoving(Principal.this);
        menu.setUserLbl(usuario.getUsuario());
        menu.addEventMenuSelected(new EventMenuSelected(){
           @Override
           public void selected(int index){
               switch (index) {
                   case 0:
                       home.cargarDatos();
                       setForm(home);
                       break;
                   case 1:
                       formUsuarios.cargarDatos();
                       setForm(formUsuarios);
                       break;
                   case 2:
                       setForm(cargaDatos);
                       break;
                   case 3:
                       form2.cargarDatos();
                       setForm(form2);
                       break;
                   case 4:
                       formMembresia.cargarDatos();
                       setForm(formMembresia);
                       break;
                   case 5:
                       formPagos.cargarDatos();
                       setForm(formPagos);
                       break;
                   case 6:
                       setForm(formVisitas);
                       break;
                   case 7:
                       formHistorial.cargarDatos();
                       setForm(formHistorial);  
                       break;
                   case 8:
                       System.exit(0);
                   default:
                       break;
               }
           }
        });
        setForm(new Form_Home());
    }
    
    private void setForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.gimnasiotesting.igu.PanelBorder();
        menu = new com.mycompany.gimnasiotesting.igu.Menu();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(102, 102, 102));
        panelBorder1.setOpaque(true);

        menu.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N

        mainPanel.setPreferredSize(new java.awt.Dimension(0, 0));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.mycompany.gimnasiotesting.igu.Menu menu;
    private com.mycompany.gimnasiotesting.igu.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}