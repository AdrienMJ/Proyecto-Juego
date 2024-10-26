package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Materiales extends JScrollPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Materiales() {
		//Botones de MULTIPLICADORES (Lápices, Cuadernos,...
		JList<JButton> lista = new JList<JButton>();
		
		
		
		//lapiz
		JButton lapiz = new JButton();
		lapiz.setText("Lápices");
		lapiz.setSize(200, 100);
		lapiz.setFont(lapiz.getFont().deriveFont(25.0f));
				
		lista.add(lapiz);
		
		//Cuaderno
		JButton cuaderno = new JButton();
		cuaderno.setText("Cuaderno");
		cuaderno.setSize(200, 100);
		cuaderno.setFont(cuaderno.getFont().deriveFont(20.0f));
				
		//panelMats.add(cuaderno);
		
		//Borragoma
				
		//Saca puntas
				
		//Mesa
				
		//Boligrafo
				
		//Libro de Matematicas (Se desbloquean los minijuegos)
				
		//Subrrayador
				
		//Tipex
				
		//Ordenador
				
		//Cerebro
				
		//IQ
				
		//ChatGPT
				
		//Ganas
				
		//Atención
				
		//Albert Einstein
				
		//Ordenador Cuántico	
		
		
		this.setPreferredSize(new Dimension(300,100));
	}
	
}


