package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

	// Zona de mensajes o consejos
public class Mensaje extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Mensaje() {
		
		//Panel principal de mensajes
		JPanel panelMensajes = new JPanel();
		panelMensajes.setLayout(new BorderLayout());
		//panelMensajes.setSize(getPreferredSize());
		
		/*
        //JLabel para mostrar los mensajes y consejos
        JLabel labelMensajes = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        labelMensajes.setBackground(Color.WHITE);
        */
        
        //Se añade el JLabel al panel y el panel al mensaje a mostrar en la ventana principal
        //panelMensajes.add(labelMensajes);
        add(panelMensajes, BorderLayout.CENTER);
       
        
	}

}


