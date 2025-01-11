package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaAnuncioMiniJuego extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaAnuncioMiniJuego() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("¡Minijuego disponible!");
        setSize(400, 280);
        setVisible(true);
        setLocationRelativeTo(null);
        
        JPanel panelMinijuego = new JPanel();
        panelMinijuego.setBackground(Color.YELLOW);
        panelMinijuego.setLayout(new BorderLayout());
        
        JLabel textoMinijuego = new JLabel();
        textoMinijuego.setText("aa");
        textoMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoMinijuego.setVerticalAlignment(SwingConstants.CENTER);
        panelMinijuego.add(textoMinijuego, BorderLayout.CENTER);
        
        add(panelMinijuego);
        
	}
	
	
	
}
