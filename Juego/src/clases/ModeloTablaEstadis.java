package clases;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaEstadis extends AbstractTableModel  {
	private String[] nombreColumnas = {"Matrial", "Cantidad"}; //NOMBRE de las COLUMNAS
	private List<Mejora> mejoras;
	
	//el nombre del metodo == nombre de la clase
	public ModeloTablaEstadis(List<Mejora> mejoras) {
		this.mejoras = mejoras;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return mejoras.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nombreColumnas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return nombreColumnas[column];
	}
	
	//*CAMBIAR DEPENDIENDO DE LOS VALORES DEL CONSTRUCTOR DE LA VARIABLE*
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mejora m = mejoras.get(rowIndex); //personas es la lista de coches que hay.
		switch(columnIndex) {
		case 0: return m.getNombre();
		case 1: return m.getNumero();
		default: return null;
		}
		
		
	}
	

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return columnIndex == 1;  
	}

	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0: return String.class;
		case 1: return int.class;
		default: return null;
		}
	}

}
