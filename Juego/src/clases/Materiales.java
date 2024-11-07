package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//TODO Añadir poco a poco los materiales con los precios de los cokieclicker
public class Materiales extends JScrollPane{
	
	HashMap<String, Double> mapaCantidades;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Materiales() {
		
		//Paneles de MULTIPLICADORES (Lápices, Cuadernos,...
        //DefaultListModel<JPanel> modeloJList = new DefaultListModel<JPanel>();
		
		//Panel principal de los Multiplicadores
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
		panelContenedor.setSize(getPreferredSize());
		JPanel panelBotonesCompra = new JPanel();
		
		//Se añade un panel con botones. Estos serán los encargados de comprar de 1 en 1, de 10 en 10....
		panelBotonesCompra.setLayout(new BoxLayout(panelBotonesCompra,BoxLayout.X_AXIS));
		
		JButton botonx1 = new JButton("x1");
		JButton botonx10 = new JButton("x10");
		JButton botonx100 = new JButton("x100");
		
		Dimension dimensionBoton = new Dimension(60,20);
		botonx1.setPreferredSize(dimensionBoton);
		botonx10.setPreferredSize(dimensionBoton);
		botonx100.setPreferredSize(dimensionBoton);
		
		panelBotonesCompra.add(new JLabel("Comprar: "));
		panelBotonesCompra.add(botonx1);
		panelBotonesCompra.add(botonx10);
		panelBotonesCompra.add(botonx100);
			
		panelContenedor.add(panelBotonesCompra);

			
		//lapiz
		Mejora lapiz = new Mejora("Lapiz", 15, 0.1, 1.15);
		PanelMejora panelLapiz = new PanelMejora(lapiz);				
		panelContenedor.add(panelLapiz);
		
		//Cuaderno
		Mejora cuaderno = new Mejora("Cuaderno", 100, 1, 1.152);
		PanelMejora panelCuaderno = new PanelMejora(cuaderno);
		panelContenedor.add(panelCuaderno);

		//Borragoma
		Mejora borragoma = new Mejora("Borragoma",1_100, 8, 1.154);
		PanelMejora panelBorragoma = new PanelMejora(borragoma);
		panelContenedor.add(panelBorragoma);
		
		//Saca puntas
		Mejora sacaPuntas = new Mejora("Saca-puntas", 12_000, 47, 1.156);
		PanelMejora panelSacaPuntas = new PanelMejora(sacaPuntas);
		panelContenedor.add(panelSacaPuntas);
				
//		//Mesa
//		Mejora mesa = new Mejora("mesa",5_000_000,1_000);
//		PanelMejora panelMesa = new PanelMejora(mesa);
//		panelContenedor.add(panelMesa);
//				
//		//Boligrafo
//		Mejora boligrafo = new Mejora("boligrafo",5_000_000,1_000);
//		PanelMejora panelBoli = new PanelMejora(boligrafo);
//		panelContenedor.add(panelBoli);		
//		
//		//Libro de Matematicas (Se desbloquean los minijuegos)
//		Mejora libroMate = new Mejora("Libro de Mate",5_000_000,1_000);
//		PanelMejora panelLibroMate = new PanelMejora(libroMate);
//		panelContenedor.add(panelLibroMate);		
		
		//Profesor particular
				
		//Proyectos escolares
				
		//Ordenador
				
		//Cerebro
				
		//IQ
				
		//ChatGPT
				
		//Ganas
				
		//Atención
				
		//Albert Einstein
				
		//Ordenador Cuántico
		
		//Rick Sanchez
		
//		JList<JPanel> listaPanelesMejoras = new JList<JPanel>(modeloJList);
//        listaPanelesMejoras.setFixedCellWidth(200);
//        listaPanelesMejoras.setFixedCellHeight(20);
//		
//		this.setPreferredSize(new Dimension(300,100));
		
		panelContenedor.setVisible(true);
		this.setViewportView(panelContenedor);
		this.setVisible(true);
		
//		new HashMap<>();
//		mapaCantidades.put("Lapiz", 0d);
//		mapaCantidades.put("Cuaderno", 0d);
//		mapaCantidades.put("Borragoma", 0d);
//		mapaCantidades.put("Sacapuntas", 0d);
		

	}
	
	
//	public static void main(String[] args) {
//		JFrame ventana = new JFrame();
//		ventana.setSize(600, 400);
//		JScrollPane panelMejoras = new Materiales();
//		ventana.add(panelMejoras);
//		ventana.setVisible(true);
//	}
	
}


