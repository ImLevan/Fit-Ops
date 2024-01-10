package com.mycompany.gimnasiotesting.igu;

import com.mycompany.gimnasiotesting.logica.Controladora;
import com.mycompany.gimnasiotesting.logica.Miembro;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;

public class Form_Home extends javax.swing.JPanel {
    
    Controladora control = null;
   
    public Form_Home() {
        initComponents();
        control = new Controladora();       
        chequearMembresias();
        cargarDatos();
        spTable.getViewport().setBackground(new Color(102, 102, 102));

    }

    public void cargarDatos() {
        
        String recaudacionTotal = Float.toString(control.traerSumaDePagosMesActual());
        String recaudacionMembresias = Float.toString(control.traerSumaDeMembresiasMesActual());
        String recaudacionVisitasUnicas = Float.toString(control.traerSumaDeVisitasUnicasMesActual());
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/icons/stock.png")), "Recaudación Total", "$"+recaudacionTotal, ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icons/profit.png")), "Membresías", "$" + recaudacionMembresias, ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icons/flag.png")), "Visitas Únicas", "$" + recaudacionVisitasUnicas, ""));
        
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

        panel = new javax.swing.JLayeredPane();
        card1 = new com.mycompany.gimnasiotesting.igu.Card();
        card2 = new com.mycompany.gimnasiotesting.igu.Card();
        card3 = new com.mycompany.gimnasiotesting.igu.Card();
        panelBorder1 = new com.mycompany.gimnasiotesting.igu.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.mycompany.gimnasiotesting.igu.Table();

        setBackground(new java.awt.Color(102, 102, 102));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Lista de miembros");

        spTable.setOpaque(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Matrícula", "Nombre", "Estado", "Inicio membresía", "Fin membresía", "Membresía"
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
        table.setSelectionBackground(new java.awt.Color(102, 102, 102));
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.gimnasiotesting.igu.Card card1;
    private com.mycompany.gimnasiotesting.igu.Card card2;
    private com.mycompany.gimnasiotesting.igu.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.mycompany.gimnasiotesting.igu.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.mycompany.gimnasiotesting.igu.Table table;
    // End of variables declaration//GEN-END:variables

    private void chequearMembresias() {
        List<Miembro> listaMiembros = control.traerMiembros();
        LocalDateTime fechaHoy = LocalDateTime.now();
        
        if(listaMiembros != null){
            for(Miembro miembro : listaMiembros){
                if(miembro.getMiembro_membresia() != null){
                    if(miembro.getFechaFin().equals(fechaHoy)){
                        miembro.setEstado("VENCIDO");
                        control.modificarMiembro(miembro, miembro.getNombre(), miembro.getMatricula(), miembro.getApellido(), miembro.getDni(), miembro.getTelefono(), miembro.getSexo());
                    }
                }
            }
        }
    }
}
