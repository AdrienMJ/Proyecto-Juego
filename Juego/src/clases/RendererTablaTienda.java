package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererTablaTienda extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		String valor = String.valueOf(value);
		JLabel miCelda = new JLabel(valor);
		
		miCelda.setOpaque(true);
		table.setRowHeight(40);
		
		
		//Fuente de toda la tabla
		if (column == 0) {
			miCelda.setFont(new Font("Arial", Font.BOLD, 22));
			miCelda.setHorizontalAlignment(CENTER);
		} else if (column == 1) {
			miCelda.setFont(new Font("Arial", Font.BOLD, 12));
			miCelda.setHorizontalAlignment(CENTER);
		} else if (column == 2) {
			miCelda.setFont(new Font("Arial", Font.BOLD, 22));
			miCelda.setHorizontalAlignment(CENTER);
		}
		
		
			//Anchura de la primera columna (MATERIAL)
		for (int i = 0; i < table.getColumnCount(); i++) {
		    String columnName = table.getColumnName(i);
		    if (columnName.equals("NOMBRE DEL OBJETO")) {
		        table.getColumnModel().getColumn(i).setPreferredWidth(250);
		    } else if (columnName.equals("DESCRIPCIÓN")) {
		        table.getColumnModel().getColumn(i).setPreferredWidth(300);
		    }
		}
		
		if (row%2 == 0) {
			miCelda.setBackground(new Color(255,196,242));
		}
		
		
		
		
		return miCelda;
		
	}

}
