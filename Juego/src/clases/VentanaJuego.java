package clases;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {
	
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(1600,900);
        
        JPanel clickerPanel = new JPanel();
        JPanel objetosPanel = new JPanel();
        JPanel mejorasPanel = new JPanel();
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        clickerPanel.setBackground(Color.blue);

        objetosPanel.setBackground(Color.GREEN);

        mejorasPanel.setBackground(Color.ORANGE);

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        panel.add(clickerPanel, gbc);

        gbc.gridx = 1; 
        gbc.gridy = 0; 
        panel.add(objetosPanel, gbc);

        // Agregar el tercer panel en (0, 1)
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        gbc.gridwidth = 2; // Este panel ocupa ambas columnas
        panel.add(mejorasPanel, gbc);
        
        add(panel);
	}
}
