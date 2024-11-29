package clases;

import javax.swing.JButton;

public class TodosLosObjetos {
	
	public TodosLosObjetos(VentanaJuego vent){
		
		//SUPER BOLI:
		JButton bSuperBoli = new JButton("COMPRAR");
		vent.listaObjetos.add(new Objeto("Super Boligrafo","Por cada 15 Boligrafos, \n los libros de matematicas hacen 0.3 puntos extra.",2, bSuperBoli ));
		
		//
	}
}
