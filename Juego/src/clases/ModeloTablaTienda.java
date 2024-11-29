package clases;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaTienda extends AbstractTableModel {

    private String[] nombreColumnas = { "NOMBRE DEL OBJETO", "DESCRIPCIÓN", "COSTE", "BOTÓN DE COMPRA" }; // Nombre de las columnas
    private ArrayList<Objetos> objetos;


	
	public ModeloTablaTienda(ArrayList<Objetos>  objetos) {
		this.objetos = objetos;
	}
	
	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return objetos.size();
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
		Objetos o = objetos.get(rowIndex); //personas es la lista de coches que hay.
		switch(columnIndex) {
		case 0: return o.getNombreObjeto();
		case 1: return o.getDescrip();
		case 2: return o.getCosteCreditos();
		case 3: return o.getBotonObjeto();
		default: return null;
		}
		
		
	}
	

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return columnIndex == 3;  
	}

	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return int.class;
		case 3: return JButton.class;
		default: return null;
		}
	}

}
