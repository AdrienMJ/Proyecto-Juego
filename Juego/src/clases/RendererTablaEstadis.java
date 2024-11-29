package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererTablaEstadis extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		String valor = String.valueOf(value);
		JLabel miCelda = new JLabel(valor);
		
		miCelda.setOpaque(true);
		table.setRowHeight(40);
		
		
		//Fuente de toda la tabla
			miCelda.setFont(new Font("Arial", Font.BOLD, 22));
			miCelda.setHorizontalAlignment(CENTER);
		
		//Anchura de la primera columna (MATERIAL)
		if (table.getColumnName(0).equals("MATERIAL")) {
		    table.getColumnModel().getColumn(0).setPreferredWidth(200); // Establecer el ancho preferido
		}
		
		if (row%2 == 0) {
			miCelda.setBackground(new Color(255,196,242));
		}
		
		if (column == 2) {
			
			try {
			    //Convertir el texto a un número float:
			    float numeroDecimal = Float.parseFloat(miCelda.getText());

			    //Formato:
			    DecimalFormat formato = new DecimalFormat("#.#");
			    String numeroFormateado = formato.format(numeroDecimal);

			    miCelda.setText(numeroFormateado);
			
			} catch (NumberFormatException e) {
				
			    System.err.println("Error: el texto no es un número válido.");
			    miCelda.setText("0.00");
			}		}
		
		
		return miCelda;
		
	}

}
