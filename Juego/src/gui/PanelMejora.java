package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domain.Mejora;



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
	 * @param mejora objeto(potenciador) del que se quiera crear un panel
	 */
	public PanelMejora(Mejora mejora) {
		
		//Componentes de cada Panel de mejora
		setLayout(new BorderLayout());
		tipoMejora = new JLabel(mejora.getNombre());
		
		//Boton de cada objeto con su respectivo precio
		botonCompra = new JButton();
		botonCompra.setText("Precio: "+ (int) mejora.getPrecio());
		cantidadMejora = new JLabel("Cantidad: " + mejora.getNumero());
		
				
		add(tipoMejora,BorderLayout.NORTH);
		add(botonCompra, BorderLayout.CENTER);
		add(cantidadMejora,BorderLayout.SOUTH);
		
		this.setMaximumSize(new Dimension(1000,100));
		this.setMinimumSize(new Dimension (100,40));
		
		
//		ImageIcon iconoLapiz = new ImageIcon(getClass().getResource("/Imagenes/lapiz.png"));
//		Image imagenEscalada = iconoLapiz.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//	    ImageIcon iconoLapizEscalado = new ImageIcon(imagenEscalada);
//	    
//	    botonCompra.setIcon(iconoLapizEscalado);
		
	}
	/**
	 * Actualiza el Jlabel
	 * @param mejora la mejora con la que se trabaje
	 */
	public void actualizarPanel(Mejora mejora) {
		cantidadMejora.setText("Cantidad: " + mejora.getNumero()); // Actualiza la cantidad actual de la mejora
		botonCompra.setText("Precio: "+ (int) mejora.getPrecio()); // Actuliza el precio actual de la mejora
		
	    
	    botonCompra.setIcon(elegirImagenYCrearMinijuego(mejora)); //se elige la imagen del botón y se avisa y crea el minijuego correspondiente
	}
	
	
	/**
	 * Elige la imagen de cada mejora
	 * @param mejora junto con la que irá la imagen
	 */
	public ImageIcon elegirImagenYCrearMinijuego(Mejora mejora) {
		
		ImageIcon icono = new ImageIcon("resources/images/lapiz.png"); //hacemos que el "defecto sea el lápiz, por ejemplo"
		Image imagenTamanyoAdecuado;
		
		if (mejora.getNumero() >= 1) { //la imagen solo saldrá a partir de la primera compra
			
			if (mejora.getNombre().equals("Lapiz")) {
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); //hacemos esto para que el png no sea demasiado grande
				icono = new ImageIcon(imagenTamanyoAdecuado);
			} else if (mejora.getNombre().equals("Cuaderno")) {
				icono = new ImageIcon("resources/images/cuaderno.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
			} else if (mejora.getNombre().equals("Borragoma")) {
				icono = new ImageIcon("resources/images/borragoma.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
			} else if (mejora.getNombre().equals("Saca-puntas")) {
				icono = new ImageIcon("resources/images/sacapuntas.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
			} else if (mejora.getNombre().equals("Mesa")) {
				icono = new ImageIcon("resources/images/mesa.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
			} else if (mejora.getNombre().equals("Libro de Matemáticas")) {
				icono = new ImageIcon("resources/images/libroMates.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
				
				VentanaAnuncioMiniJuego ventanaMinijuego = new VentanaAnuncioMiniJuego(); //se crea la ventana que avisa de un nuevo minijuego, pero solo se mostrará al comprar determinados artículos
				ventanaMinijuego.actualizarMensaje("¡Completa las ecuaciones del libro! Haz click en la pestaña \"Mini Juego\".");
				ventanaMinijuego.setVisible(true);
				
				VentanaJuego ventanaJuego = (VentanaJuego) SwingUtilities.getWindowAncestor(this); //Hacemos refencia a la propia ventana principal para llamar al método relacionado con el minijuego
				if (ventanaJuego != null) {
			        ventanaJuego.minijuegoLibroMatematica();
			    }

			} else if (mejora.getNombre().equals("Profesor Particular")) {
				icono = new ImageIcon("resources/images/profesorParticular.png");
				imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenTamanyoAdecuado);
			}
			return icono;
			}
		return null; //este caso se supone que nunca se debería dar, pero ha de estar cubierto
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
