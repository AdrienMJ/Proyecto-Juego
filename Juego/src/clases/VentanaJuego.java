package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaJuego extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaJuego() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600,400);
        
        JPanel clickerPanel = new JPanel();
        clickerPanel.setBackground(Color.lightGray);
        
        JButton estudianteClick = new JButton();
        clickerPanel.add(estudianteClick);
        add(clickerPanel,BorderLayout.CENTER);
        
        //jListMejoras -> las mejoras para el estudiante
        DefaultListModel modeloJList = new DefaultListModel<Mejora>();
        JList jListMejoras = new JList<Mejora>(modeloJList);
		jListMejoras.setFixedCellWidth(200);
		jListMejoras.setFixedCellHeight(20);
		JScrollPane panelScroll = new JScrollPane(jListMejoras);
		add(panelScroll, BorderLayout.EAST);
		
	}
}
