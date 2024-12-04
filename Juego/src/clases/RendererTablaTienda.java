package clases;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class RendererTablaTienda extends DefaultTableCellRenderer {
	
	
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
		
		miCelda.setFont(table.getFont().deriveFont(Font.PLAIN));
		miCelda.setHorizontalAlignment(CENTER);
		
		//Fuente de toda la tabla
		if (column == 0) {
			miCelda.setFont(new Font("Arial", Font.BOLD, 20));
			
			
		} else if (column == 1) {
			miCelda.setFont(new Font("Arial", Font.PLAIN, 12));
			miCelda.setHorizontalAlignment(CENTER);
			
			} else if (column == 2) {
			miCelda.setFont(new Font("Arial", Font.PLAIN, 20));
			miCelda.setHorizontalAlignment(CENTER);
			
		} else if (column == 3) {
			
			JRadioButton button = (JRadioButton) value;
            button.setHorizontalAlignment(CENTER); 
            
            if (row % 2 == 0) {
            	button.setBackground(new Color(255,196,242));
			}
            
            if(isSelected) button.setBackground(Color.red);  //Cambiar el color si esta seleccionado
		  
            return button; //devuelve el boton para que no aparezca el string de la referencia del JRadioButton
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
		
		//Cambiar el color si esta seleccionado
				if (isSelected) {
					miCelda.setBackground(Color.red);
					
				}
		
		
		return miCelda;
		

	}
	
	
}
