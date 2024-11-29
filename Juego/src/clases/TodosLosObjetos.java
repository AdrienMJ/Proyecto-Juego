package clases;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class TodosLosObjetos {
	
	public TodosLosObjetos(VentanaJuego vent){
		
		//SUPER BOLI:
		JRadioButton bSuperBoli = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Super Boligrafo","<html>Por cada 15 Boligrafos,<br>los libros de matematicas <br>hacen 0.3 puntos extra. <html>",2, bSuperBoli ));
		
		//
	}
}
