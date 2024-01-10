package com.mycompany.gimnasiotesting.igu;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableGeneral extends JTable{
    
    public TableGeneral(){
        setShowHorizontalLines(false);
        setShowVerticalLines(false);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1){
                TableHeader header = new TableHeader(o + "");
                header.setForeground(Color.WHITE);
                header.setBackground(new Color(102, 102, 102));
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(new Color(102, 102, 102));
                setBorder(noFocusBorder);
                if(selected){
                    com.setForeground(new Color(15, 89, 140));
                }else{
                    com.setForeground(Color.WHITE);
                }
                return com;
            }
        });
    }
    
    
    public void addRow(Object[] row){
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    
    public void setRowCount(int rowCount) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(rowCount);
    }
}
