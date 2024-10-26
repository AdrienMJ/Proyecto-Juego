package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;

    private int puntos = 0;  // Contador de puntos

    public VentanaJuego() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 400);

        // Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.Y_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
        clickerPanel.setBackground(Color.lightGray);
        
        // JLabel para mostrar los puntos
        JLabel labelPuntos = new JLabel("Creditos: 0");
        labelPuntos.setBackground(Color.white);
        labelPuntos.setAlignmentX(CENTER_ALIGNMENT);  // Centrar el label en el panel
        clickerPanel.add(labelPuntos);

        // Botón Principal (CREDITOS)
        JButton estudianteClick = new JButton("Estudiante");
        estudianteClick.setPreferredSize(new Dimension(150, 150));
        estudianteClick.setAlignmentX(CENTER_ALIGNMENT);
        clickerPanel.add(estudianteClick);

        // Acción del botón para incrementar los puntos
        estudianteClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos++;
                labelPuntos.setText("Creditos: " + puntos);
            }
        });

        add(clickerPanel, BorderLayout.CENTER);
        
        // jListMejoras -> las mejoras para el estudiante
//        DefaultListModel<Mejora> modeloJList = new DefaultListModel<Mejora>();
//        JList<Mejora> jListMejoras = new JList<>(modeloJList);
//        jListMejoras.setFixedCellWidth(200);
//        jListMejoras.setFixedCellHeight(20);
//        JScrollPane panelScroll = new JScrollPane(jListMejoras);
//        add(panelScroll, BorderLayout.EAST);  // Colocamos el JList en el lado derecho
        
        //SCROLL de los Materiales
        JScrollPane jScrollMats = new Materiales();
        jScrollMats.setPreferredSize(new Dimension(200,100));
        //jScrollMats.add(listaMats); //Hay que añadir la lista de materiales(Botones) al Scroll
		//this.add(jScrollMats, BorderLayout.EAST);
		
		
		
		
    }	
        
        
}