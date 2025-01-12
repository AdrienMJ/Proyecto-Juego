package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import domain.Objeto;

public class TodosLosObjetos {
	
	//OBJETOS:
	static JRadioButton bSuperLapiz;
	static JRadioButton bMegaBorragoma;
	static JRadioButton bProfesoresExtra;
	static JRadioButton bMesasExtra;
	
	ArrayList<JRadioButton> listaBotones;
	
	JOptionPane jop;
	
	int contComprado = 0;
	
	
	public TodosLosObjetos(VentanaJuego vent){
		
		//SUPER BOLI:
		bSuperLapiz = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Super Lapiz","<html>Por cada 15 lapizes,<br>los libros de matematicas <br>hacen 0.7 puntos extra. <html>",24, bSuperLapiz ));

		//MEGA BORRAGOMA:
		bMegaBorragoma = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Mega Borragoma","<html> Por cada 10 Gomas, la borragomas otorgan 1.5 puntos extra. <html>",36, bMegaBorragoma ));

		//PROFERES EXTRA:
		bProfesoresExtra = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Profesores Extra","<html> Por cada 30 Profesores Particulares, consigues 3 puntos de conocimiento. <html>",50, bProfesoresExtra ));
	
		//MESAS EXTRA:
		bMesasExtra = new JRadioButton();
		vent.listaObjetos.add(new Objeto("Mesas Extra","<html> 60 mesas equivalen a 25 puntos/seg extra de conocimiento.<html>",55, bMesasExtra ));
	
	
		
		
		
		

		
	}
}
