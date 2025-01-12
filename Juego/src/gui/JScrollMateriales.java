package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import domain.Mejora;
import domain.Objeto; 

import gui.TodosLosObjetos;

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
//			for (Mejora mejora : ventana.listaMejoras) { 
//				crearPanelMejora(ventana, mejora);
//			}
			//lapiz
			Mejora lapiz = new Mejora(0,"Lapiz", 15, 15, 0.1, 0.1, 1.15, 0); //Se crea la mejora con el contructor
			ventana.listaMejoras.add(lapiz); // Se añade la mejora a una lista para su posterior uso			
			crearPanelMejora(ventana, lapiz);
			final double gananciaNormalLapiz = lapiz.getGanacia();
			
			
			//Cuaderno
			Mejora cuaderno = new Mejora(0, "Cuaderno", 100, 100, 1, 1, 1.132, 0);
			ventana.listaMejoras.add(cuaderno);			
			crearPanelMejora(ventana, cuaderno);
			final double gananciaNormalCuaderno = cuaderno.getGanacia();
			
			//Saca puntas
			Mejora sacaPuntas = new Mejora(0, "Saca-puntas", 1_100, 1_100, 8, 8, 1.146, 0);
			ventana.listaMejoras.add(sacaPuntas);
			crearPanelMejora(ventana, sacaPuntas);
			final double gananciaNormalSacaPuntas = sacaPuntas.getGanacia();
			
			
			//Mesa
			Mejora mesa = new Mejora(0, "Mesa", 12_000, 12_000, 47, 47, 1.158, 0);
			ventana.listaMejoras.add(mesa);
			crearPanelMejora(ventana, mesa);
			final double gananciaNormalMesa = mesa.getGanacia();
		
			//Borragoma
			Mejora borragoma = new Mejora(0, "Borragoma", 90_000, 90_000, 90, 90, 1.164, 0);
			ventana.listaMejoras.add(borragoma);
			crearPanelMejora(ventana, borragoma);
			final double gananciaNormalBorragoma = borragoma.getGanacia();
					
			//Libro de Matematicas (Se desbloquean los minijuegos)
			Mejora libroMate = new Mejora(0, "Libro de Matemáticas", 130_000, 130_000, 260, 260, 1.18, 0);
			ventana.listaMejoras.add(libroMate);
			crearPanelMejora(ventana, libroMate);
			final double gananciaNormalLibroMate = libroMate.getGanacia();
			
			//Profesor particular
			Mejora profesor = new Mejora(0, "Profesor Particular", 1_400_000, 1_400_000, 1_400, 1_400, 1.192, 0);
			ventana.listaMejoras.add(profesor);
			crearPanelMejora(ventana, profesor);
			final double gananciaNormalProfesor = profesor.getGanacia();
			
			
			
			
			for (Mejora m : ventana.listaMejoras) {
				if (m.getNombre().equals("Lapiz") && ventana.listaBotonesObjetos.contains(TodosLosObjetos.bSuperLapiz)) {
					if (lapiz.getNumero()%15 == 0) {
						libroMate.setGanacia(libroMate.getGanacia() + (lapiz.getNumero()%2) * 0.7);
					}
				}
				if (m.getNombre().equals("Borragoma") && ventana.listaBotonesObjetos.contains(TodosLosObjetos.bMegaBorragoma)) {
					if (borragoma.getNumero()%10 == 0) {
						borragoma.setGanacia(borragoma.getGanacia() + (borragoma.getNumero()%2)* 1.5);
					}
				}
				if (m.getNombre().equals("Mesa") && ventana.listaBotonesObjetos.contains(TodosLosObjetos.bMesasExtra)) {
					if (profesor.getNumero()%30 == 0) {
						profesor.setGanacia(profesor.getGanacia() + (profesor.getNumero()%2)* 3);
					}
				}
				if (m.getNombre().equals("Profesor Particular") && ventana.listaBotonesObjetos.contains(TodosLosObjetos.bProfesoresExtra)) {
					if (mesa.getNumero()%60 == 0) {
						mesa.setGanacia(mesa.getGanacia() + (mesa.getNumero()%2) * 25);
					}
				}
			}
			
			//Asegurarse de que cuando se elimine el boton de la tienda de cada uno, La ganancia vuelva a la normalidad
			for (Mejora m : ventana.listaMejoras) {
				
				
				if (m.getNombre().equals("Lapiz") && !ventana.listaBotonesObjetos.contains(TodosLosObjetos.bSuperLapiz)) {
					if (lapiz.getNumero()%15 == 0) {
						libroMate.setGanacia(gananciaNormalLapiz);
					}
				}
				if (m.getNombre().equals("Borragoma") && !ventana.listaBotonesObjetos.contains(TodosLosObjetos.bMegaBorragoma)) {
					if (borragoma.getNumero()%10 == 0) {
						borragoma.setGanacia(gananciaNormalBorragoma);
					}
				}
				if (m.getNombre().equals("Mesa") && !ventana.listaBotonesObjetos.contains(TodosLosObjetos.bMesasExtra)) {
					if (profesor.getNumero()%30 == 0) {
						profesor.setGanacia(gananciaNormalMesa);
					}
				}
				if (m.getNombre().equals("Profesor Particular") && !ventana.listaBotonesObjetos.contains(TodosLosObjetos.bProfesoresExtra)) {
					if (mesa.getNumero()%60 == 0) {
						mesa.setGanacia(gananciaNormalProfesor);
					}
				}
			}
			
				
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
