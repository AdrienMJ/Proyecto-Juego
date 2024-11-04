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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    
    //PUNTOS DE CONOCIMIENTO
    public int puntos = 0;  // Contador de puntos
    public int puntosBarra = 0;
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos = 0;
    
    public VentanaJuego() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 400);

        // Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.X_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
        clickerPanel.setBackground(Color.lightGray);
        
        // JLabel para mostrar los puntos
        JLabel labelPuntos = new JLabel("Conocimiento: 0");
        labelPuntos.setBackground(Color.white);
        labelPuntos.setAlignmentX(CENTER_ALIGNMENT);  // Centrar el label en el panel
        clickerPanel.add(labelPuntos);

        // Botón Principal (CREDITOS)
        JButton estudianteClick = new JButton("Estudiante");
        estudianteClick.setPreferredSize(new Dimension(150, 150));
        estudianteClick.setAlignmentX(CENTER_ALIGNMENT);
        clickerPanel.add(estudianteClick);

       

        add(clickerPanel, BorderLayout.CENTER);
        
        
        //CREDITOS:
        //Panel de la barra de progreso
      	JPanel panelBarra = new JPanel();
      	panelBarra.setPreferredSize(new Dimension(100,100));
      		
      	//Se crea la barra de progreso
      	barraCreditos = new JProgressBar(0, 10); //Los valores son de que valor MAX y min
      	barraCreditos.setStringPainted(true);//Queremos que se pinte el X% (SÍ/NO)
      	
      	JLabel labelCreditos = new JLabel();
      	
      	//Añadimos la barra y el Label al panel
      	panelBarra.add(labelCreditos);
      	panelBarra.add(barraCreditos);
      	clickerPanel.add(panelBarra, BorderLayout.CENTER);
        
      	//Acción del botón para incrementar los PUNTOS y los CREDITOS
        estudianteClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos++;
                puntosBarra ++;
                labelPuntos.setText("Conocimiento: " + puntos);
                
                //Creditos:
                barraCreditos.setValue(puntosBarra);// Actualiza la barra con el valor de puntosBarra
                if (puntosBarra == barraCreditos.getMaximum()) {  // Asegurarse de que el valor esté dentro del rango
                	puntosBarra = 0;
                	barraCreditos.setValue(puntosBarra);
                	creditos++;
                	labelCreditos.setText("Creditos: " + creditos);
                	
        		}
            }
        });
        
       
        //SCROLL de los Materiales
        JScrollPane jScrollMateriales = new Materiales();
        add(jScrollMateriales,BorderLayout.EAST);
				
    }
     
        
}