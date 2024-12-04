package clases;

import javax.swing.JRadioButton;

public class TodosLosObjetos {
	
	public TodosLosObjetos(VentanaJuego vent){
		
		//SUPER BOLI:
		JRadioButton bSuperBoli = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Super Boligrafo","<html>Por cada 15 Boligrafos,<br>los libros de matematicas <br>hacen 0.3 puntos extra. <html>",2, bSuperBoli ));

		//MEGA BORRAGOMA:
		JRadioButton bMegaBorragoma = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Mega Borragoma","<html> Por cada 10 Gomas, los exámenes corregidos otorgan 0.5 puntos extra. <html>",4, bMegaBorragoma ));

		//PROFERES EXTRA:
		JRadioButton bProfesoresExtra = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Profesores Extra","<html> Por cada 30 Profesores Particulares, consigues 2 puntos de conocimiento <html>",10, bProfesoresExtra ));
	}
}
