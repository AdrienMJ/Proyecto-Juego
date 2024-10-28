package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Materiales extends JScrollPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Materiales() {
		
		//Paneles de MULTIPLICADORES (Lápices, Cuadernos,...
        //DefaultListModel<JPanel> modeloJList = new DefaultListModel<JPanel>();
		
		//Panel principal
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
		JPanel panelBotonesCompra = new JPanel();
		panelBotonesCompra.setLayout(new BoxLayout(panelBotonesCompra,BoxLayout.X_AXIS));
		
		JButton botonx1 = new JButton("x1");
		JButton botonx10 = new JButton("x10");
		JButton botonx100 = new JButton("x100");
		
		Dimension dimensionBoton = new Dimension(60,30);
		botonx1.setPreferredSize(dimensionBoton);
		botonx10.setPreferredSize(dimensionBoton);
		botonx100.setPreferredSize(dimensionBoton);
		
		panelBotonesCompra.add(new JLabel("Comprar"));
		panelBotonesCompra.add(botonx1);
		panelBotonesCompra.add(botonx10);
		panelBotonesCompra.add(botonx100);
		
		
		panelContenedor.add(panelBotonesCompra);

			
		//lapiz
		Mejora lapiz = new Mejora("Lapiz",100,1);
		PanelMejora panelLapiz = new PanelMejora(lapiz);				
		panelContenedor.add(panelLapiz);
		
		//Cuaderno
		Mejora cuaderno = new Mejora("Cuaderno",1_000,10);
		PanelMejora panelCuaderno = new PanelMejora(cuaderno);
		panelContenedor.add(panelCuaderno);

		
		//Borragoma
		Mejora borragoma = new Mejora("Borragoma",5_000_000,1_000);
		PanelMejora panelBorragoma = new PanelMejora(borragoma);
		panelContenedor.add(panelBorragoma);
		
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
		
//		JList<JPanel> listaPanelesMejoras = new JList<JPanel>(modeloJList);
//        listaPanelesMejoras.setFixedCellWidth(200);
//        listaPanelesMejoras.setFixedCellHeight(20);
//		
//		this.setPreferredSize(new Dimension(300,100));
		
		panelContenedor.setVisible(true);
		this.setViewportView(panelContenedor);
		this.setVisible(true);
	}
	
	
//	public static void main(String[] args) {
//		JFrame ventana = new JFrame();
//		ventana.setSize(600, 400);
//		JScrollPane panelMejoras = new Materiales();
//		ventana.add(panelMejoras);
//		ventana.setVisible(true);
//	}
	
}


