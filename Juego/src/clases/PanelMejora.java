package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelMejora extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton botonCompra;
	public JLabel cantidadMejora;
	public JLabel tipoMejora;
	
	/**Clase que añade paneles para comprar y visualizar las mejoras(Los Potenciadores)
	 * Hay que poner la un objeto de la Clase Mejora para que construya el panel
	 * 
	 * @param mejora objeto(ppotenciador) del que se quiera crear un panel
	 */
	public PanelMejora(Mejora mejora) {
		
		//Componentes de cada Panel de mejora
		setLayout(new BorderLayout());
		tipoMejora = new JLabel(mejora.getNombre());
		
		//Boton de cada objeto con su respectivo precio
		botonCompra = new JButton();
		botonCompra.setText("Precio: "+ mejora.getPrecio());
		cantidadMejora = new JLabel("Cantidad: " + mejora.getNumero());
		
				
		add(tipoMejora,BorderLayout.NORTH);
		add(botonCompra, BorderLayout.CENTER);
		add(cantidadMejora,BorderLayout.SOUTH);
		
		this.setMaximumSize(new Dimension(1000,100));
		this.setMinimumSize(new Dimension (100,40));
		
		
		
		
	}
	
	
	
//	public static void main(String[] args) {
//		Mejora m = new Mejora("Lapiz",2,1,1.15);
//		PanelMejora panelLapiz = new PanelMejora(m);
//		JFrame ventana = new JFrame();
//		ventana.setSize(300,100);
//		ventana.add(panelLapiz);
//		ventana.setVisible(true);
//	}
}
