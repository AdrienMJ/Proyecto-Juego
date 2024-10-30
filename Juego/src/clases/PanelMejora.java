package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMejora extends JPanel {
	
	/**Clase que añade paneles para comprar y visualizar las mejoras(Los Potenciadores)
	 * Hay que poner la un objeto de la Clase Mejora para que construya el panel
	 * 
	 * @param mejora objeto(ppotenciador) del que se quiera crear un panel
	 */
	public PanelMejora(Mejora mejora) {
		
		//Componentes de cada Panel de mejora
		setLayout(new BorderLayout());
		JLabel tipoMejora = new JLabel(mejora.getNombre());
		
		//Hay que hacer una pequeña conversion de int a String
		String cantidadMejoraString = String.valueOf(mejora.getNumero());
		JLabel cantidadMejora = new JLabel("Cantidad: " + cantidadMejoraString);
		String precioMejoraString = String.valueOf(mejora.getPrecio());
		//JLabel precioMejora = new JLabel("Precio: " + precioMejoraString);
		
		/*
		 * Boton de cada objeto
		 * con su respectivo precio
		 */
		JButton botonCompra = new JButton();
		botonCompra.setText("Precio: "+ precioMejoraString);
		
//		JButton botonx1 = new JButton("x1");
//		JButton botonx10 = new JButton("x10");
//		JButton botonx100 = new JButton("x100");
//		
//		//Los Botones tendran un panel propio para que se visualicen bien los datos
//		JPanel panelBotones = new JPanel();
		
//		Dimension dimensionBoton = new Dimension(60,30);
//		botonx1.setPreferredSize(dimensionBoton);
//		botonx10.setPreferredSize(dimensionBoton);
//		botonx100.setPreferredSize(dimensionBoton);
		
//		panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));
//		panelBotones.add(botonx1);
//		panelBotones.add(botonx10);
//		panelBotones.add(botonx100);
				
		add(tipoMejora,BorderLayout.NORTH);
		add(botonCompra, BorderLayout.CENTER);
//		add(panelBotones,BorderLayout.CENTER);
		add(cantidadMejora,BorderLayout.SOUTH);
		
		this.setMaximumSize(new Dimension(1000,60));
		
	}
	
	
//	public static void main(String[] args) {
//		Mejora m = new Mejora("Lapiz",2,1);
//		PanelMejora panelLapiz = new PanelMejora(m);
//		JFrame ventana = new JFrame();
//		ventana.setSize(300,100);
//		ventana.add(panelLapiz);
//		ventana.setVisible(true);
//	}
}
