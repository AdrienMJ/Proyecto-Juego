//package clases;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JProgressBar;
//import javax.swing.SwingUtilities;
//
//public class Creditos extends JFrame {
//	
//	//VARIABLE DE LA CLASE:
//	private static final long serialVersionUID = 1L;
//	private JProgressBar barra;
//	
//
//	public Creditos(VentanaJuego puntos) {
//		
//		//Panel de la barra
//		JPanel panel = new JPanel();
//		add(panel);
//		add(panel , BorderLayout.EAST);
//		
//		//Se crea la barra de progreso
//		barra = new JProgressBar(0, 1000);
//		barra.setSize(getPreferredSize());
//		
//		//Queremos que se pinte el X% (SÍ/NO)
//		barra.setStringPainted(true);
//		
//		
//		//Label de los Creditos
//		JLabel labelCreditos = new JLabel();
//		labelCreditos.setText("Creditos: ");
//		labelCreditos.setSize(getPreferredSize());
//		
//		
//		//Añadimos la barra y el Label al panel
//		panel.add(labelCreditos);
//		panel.add(barra);
//		
//		
//	}
//	
//	public void actualizarProgreso(int puntos) {
//		barra.setValue(puntos); // Actualiza la barra con el valor de puntos
//	}
//
//	
//
//
//}
