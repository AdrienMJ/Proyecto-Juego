package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class Creditos extends JFrame {
	
	//VARIABLE DE LA CLASE:
	private static final long serialVersionUID = 1L;
	public JProgressBar barra;
	

	public Creditos(/*VentanaJuego puntos*/) {
		//PRUEBA:
//		this.setTitle("Progreso de Créditos");
//		this.setSize(300, 100);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setLayout(new BorderLayout());
		
		
		//Panel de la barra
		JPanel panelBarra = new JPanel();
		panelBarra.setPreferredSize(null);
		
		
		//Se crea la barra de progreso
		barra = new JProgressBar(0, 100); //Los valores son de que valor MAX y min
		//Queremos que se pinte el X% (SÍ/NO)
		barra.setStringPainted(true);
		
		
		//Label de los Creditos
		JLabel labelCreditos = new JLabel();
		labelCreditos.setText("Creditos: ");
		labelCreditos.setSize(getPreferredSize());
		
		
		//Añadimos la barra y el Label al panel
		panelBarra.add(labelCreditos);
		panelBarra.add(barra);
		
//		this.add(panelBarra , BorderLayout.EAST);
		
//		this.setVisible(true);
		
		
	}
	
	public void actualizarProgreso(int puntos) {
		if (puntos >= 0 && puntos <= 100) {  // Asegurarse de que el valor esté dentro del rango
			barra.setValue(puntos); // Actualiza la barra con el valor de puntos
		}
		
	}
	
//	public static void main(String[] args) {
//		Creditos ventana = new Creditos();
//	}
}

