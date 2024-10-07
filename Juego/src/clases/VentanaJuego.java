package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaJuego() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600,400);
        
        setLayout(new BorderLayout());
        
        JPanel clickerPanel = new JPanel();
        JPanel objetosPanel = new JPanel();
        JPanel mejorasPanel = new JPanel();
        clickerPanel.setBackground(Color.blue);
        objetosPanel.setBackground(Color.GREEN);
        mejorasPanel.setBackground(Color.ORANGE);
        add(clickerPanel, BorderLayout.WEST);
        add(objetosPanel, BorderLayout.SOUTH);
        add(mejorasPanel, BorderLayout.EAST);
        

        
	}
}
