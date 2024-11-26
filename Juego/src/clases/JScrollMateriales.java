package clases;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JScrollMateriales extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JScrollMateriales(VentanaJuego ventana) {
		
		
		
	      //Paneles de MULTIPLICADORES (Lápices, Cuadernos,...
		
			//Panel principal de los Multiplicadores
			JPanel panelContenedor = new JPanel();
			panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
			panelContenedor.setSize(new Dimension(400, 1000));
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
			
			//TODO - Hay que seguir construyendo las mejoras como se ha construido el lapiz
				
			//lapiz
			Mejora lapiz = new Mejora("Lapiz", 15, 0.1, 1.15); //Se crea la mejora con el contructor
			ventana.listaMejoras.add(lapiz); // Se añade la mejora a una lista para su posterior uso
			PanelMejora panelLapiz = new PanelMejora(lapiz); // Se crea un panel personalizado para cada mejora
			panelContenedor.add(panelLapiz); // Se añade el panel al panel que contendrá todas las mejoras
			panelLapiz.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) lapiz.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					panelLapiz.actualizarPanel(lapiz);

				}
			});
			
			//Cuaderno
			Mejora cuaderno = new Mejora("Cuaderno", 100, 1, 1.152);
			ventana.listaMejoras.add(cuaderno);
			PanelMejora panelCuaderno = new PanelMejora(cuaderno);
			panelContenedor.add(panelCuaderno);
			panelCuaderno.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) cuaderno.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					panelCuaderno.actualizarPanel(cuaderno);

				}
			});

			//Borragoma
			Mejora borragoma = new Mejora("Borragoma",1_100, 8, 1.154);
			ventana.listaMejoras.add(borragoma);
			PanelMejora panelBorragoma = new PanelMejora(borragoma);
			panelContenedor.add(panelBorragoma);
			panelBorragoma.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) borragoma.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					panelBorragoma.actualizarPanel(borragoma);

				}
			});
			
			//Saca puntas
			Mejora sacaPuntas = new Mejora("Saca-puntas", 12_000, 47, 1.156);
			ventana.listaMejoras.add(sacaPuntas);
			PanelMejora panelSacaPuntas = new PanelMejora(sacaPuntas);
			panelContenedor.add(panelSacaPuntas);
			panelSacaPuntas.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) sacaPuntas.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					panelSacaPuntas.actualizarPanel(sacaPuntas);

				}
			});
					
			//Mesa
			Mejora mesa = new Mejora("Mesa", 130_000, 260, 1.158);
			ventana.listaMejoras.add(mesa);
			PanelMejora panelMesa = new PanelMejora(mesa);
			panelContenedor.add(panelMesa);
			panelMesa.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) mesa.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					panelMesa.actualizarPanel(mesa);

				}
			});
//					
//			//Boligrafo
//			Mejora boligrafo = new Mejora("boligrafo",5_000_000,1_000);
//			PanelMejora panelBoli = new PanelMejora(boligrafo);
//			panelContenedor.add(panelBoli);		
//			
//			//Libro de Matematicas (Se desbloquean los minijuegos)
			Mejora libroMate = new Mejora("Libro de Matemáticas", 130_000, 300, 1.208);
			ventana.listaMejoras.add(libroMate);
			PanelMejora PanelLibroMate = new PanelMejora(libroMate);
			panelContenedor.add(PanelLibroMate);
			PanelLibroMate.botonCompra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ventana.puntos = (int) libroMate.comprarMejora(ventana.puntos); // Actualiza los puntos
					ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos); // Actualiza el label de los puntos
					PanelLibroMate.actualizarPanel(libroMate);

				}
			});
			
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
			
			
			panelContenedor.setVisible(true);
			setViewportView(panelContenedor);

	}
	
	
	
	
	
	
 
}
