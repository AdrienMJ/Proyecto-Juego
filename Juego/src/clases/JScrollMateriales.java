package clases;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class JScrollMateriales extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JRadioButton botonx1;
	public JRadioButton botonx10;
	public JRadioButton botonx100;
	public  JPanel panelContenedor;

	public JScrollMateriales(VentanaJuego ventana) {
			
			
		
		
	      //Paneles de MULTIPLICADORES (Lápices, Cuadernos,...
		
			//Panel principal de los Multiplicadores
			panelContenedor = new JPanel();
			panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
			panelContenedor.setSize(new Dimension(500, 1000));
			
			JPanel panelBotonesCompra = new JPanel();
			//Se añade un panel con botones. Estos serán los encargados de comprar de 1 en 1, de 10 en 10....
			panelBotonesCompra.setLayout(new BoxLayout(panelBotonesCompra,BoxLayout.X_AXIS));
			
			botonx1 = new JRadioButton("x1");
			botonx10 = new JRadioButton("x10");
			botonx100 = new JRadioButton("x100");
			
			ButtonGroup opcionCompraButtonGroup = new ButtonGroup();
			opcionCompraButtonGroup.add(botonx1);
			opcionCompraButtonGroup.add(botonx10);
			opcionCompraButtonGroup.add(botonx100);
			botonx1.setSelected(true);
			
			
			Dimension dimensionBoton = new Dimension(60,20);
			botonx1.setPreferredSize(dimensionBoton);
			botonx10.setPreferredSize(dimensionBoton);
			botonx100.setPreferredSize(dimensionBoton);
			
			panelBotonesCompra.add(new JLabel("Comprar: "));
			panelBotonesCompra.add(botonx1);
			panelBotonesCompra.add(botonx10);
			panelBotonesCompra.add(botonx100);
				
			panelContenedor.add(panelBotonesCompra);
			
			
			
			//TODO - Hay que seguir construyendo las mejoras como se ha construido el lapiz
				
			//lapiz
			Mejora lapiz = new Mejora("Lapiz", 15, 0.1, 1.15); //Se crea la mejora con el contructor
			ventana.listaMejoras.add(lapiz); // Se añade la mejora a una lista para su posterior uso			
			crearPanelMejora(ventana, lapiz);
						
			//Cuaderno
			Mejora cuaderno = new Mejora("Cuaderno", 100, 1, 1.132);
			ventana.listaMejoras.add(cuaderno);			
			crearPanelMejora(ventana, cuaderno);
			
			//Saca puntas
			Mejora sacaPuntas = new Mejora("Saca-puntas", 1_100, 8, 1.146);
			ventana.listaMejoras.add(sacaPuntas);
			crearPanelMejora(ventana, sacaPuntas);
			
			//Mesa
			Mejora mesa = new Mejora("Mesa", 12_000, 47, 1.158);
			ventana.listaMejoras.add(mesa);
			crearPanelMejora(ventana, mesa);
		
			//Borragoma
			Mejora borragoma = new Mejora("Borragoma", 90_000, 90, 1.164);
			ventana.listaMejoras.add(borragoma);
			crearPanelMejora(ventana, borragoma);
		
			
			
			//Libro de Matematicas (Se desbloquean los minijuegos)
			Mejora libroMate = new Mejora("Libro de Matemáticas", 130_000, 260, 1.18);
			ventana.listaMejoras.add(libroMate);
			crearPanelMejora(ventana, libroMate);
			
			//Profesor particular
			Mejora profesor = new Mejora("Profesor Particular", 1_400_000,1_400,1.192);
			ventana.listaMejoras.add(profesor);
			crearPanelMejora(ventana, profesor);
			
			
			
			
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
			
//			PanelMejora panelLapiz = new PanelMejora(lapiz); // Se crea un panel personalizado para cada mejora
//			panelContenedor.add(panelLapiz); // Se añade el panel al panel que contendrá todas las mejoras
//			panelLapiz.botonCompra.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					if (botonx1.isSelected()) {
//						ventana.puntos = (int) lapiz.comprarMejora(ventana.puntos); // Actualiza los puntos
//						ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
//						panelLapiz.actualizarPanel(lapiz);
//						
//					} else if (botonx10.isSelected() && ventana.puntos >= lapiz.getPrecioInicial()* Math.pow(lapiz.getMultiplicador(), lapiz.getNumero()+10)) {
//						for (int i = 0; i<10; i++) {
//							ventana.puntos = (int) lapiz.comprarMejora(ventana.puntos);
//							ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
//							panelLapiz.actualizarPanel(lapiz);
//						}
//						
//					} else if(botonx100.isSelected() && ventana.puntos >= lapiz.getPrecioInicial()* Math.pow(lapiz.getMultiplicador(), lapiz.getNumero()+100))  {
//						for (int i = 0; i < 100; i++) {
//							ventana.puntos = (int) lapiz.comprarMejora(ventana.puntos);
//							ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
//							panelLapiz.actualizarPanel(lapiz);
//							
//						}
//					}
//
//				}
//			});
			
			
			
			panelContenedor.setVisible(true);
			setViewportView(panelContenedor);

	}
	
	
	
	public void crearPanelMejora(VentanaJuego ventana, Mejora mejora) {
	    // Crear el panel
	    PanelMejora panelMejora = new PanelMejora(mejora);
	    panelContenedor.add(panelMejora); // Añadir el panel al contenedor

	    // Configurar el ActionListener
	    panelMejora.botonCompra.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (botonx1.isSelected()) {
	                ventana.puntos = (int) mejora.comprarMejora(ventana.puntos); // Actualiza puntos
	                ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza label
	                panelMejora.actualizarPanel(mejora);

	            } else if (botonx10.isSelected()) { //En principio compra 10 o hasta los que pueda
	                for (int i = 0; i < 10; i++) {
	                    ventana.puntos = (int) mejora.comprarMejora(ventana.puntos);
	                    ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza label
	                    panelMejora.actualizarPanel(mejora);
	                }

	            } else if (botonx100.isSelected()) { //En principio compra 100 o hasta los que pueda
	                for (int i = 0; i < 100; i++) {
	                    ventana.puntos = (int) mejora.comprarMejora(ventana.puntos);
	                    ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza label
	                    panelMejora.actualizarPanel(mejora);
	                }
	            }
	        }
	    });
	}
	
	
	
 
}
