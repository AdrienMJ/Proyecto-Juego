package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;


public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    
    //Random:
    static Random randomizador = new Random();
    
    //PUNTOS DE CONOCIMIENTO
    public static int puntos = 0;  // Contador de puntos
    public int puntosBarra = 0;
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos = 0;
    
    //REFERENCIA AL JLABEL DE MENSAJES:
    public JLabel labelMensajes;
    
    //LISTA QUE CONTENDRÁ LOS MENSAJES A MOSTRAR:
    private List<String> mensajes;
    
    
    public VentanaJuego() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        
        

        // Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.X_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
        clickerPanel.setBackground(Color.lightGray);
        
        // JLabel para mostrar los puntos
        JLabel labelPuntos = new JLabel("Conocimiento: 0");
        labelPuntos.setBackground(Color.white);
        labelPuntos.setAlignmentX(CENTER_ALIGNMENT);  // Centrar el label en el panel
        clickerPanel.add(labelPuntos);
        
        // Botón Principal (CRÉDITOS)
        JButton estudianteClick = new JButton("Estudiante");
        estudianteClick.setPreferredSize(new Dimension(150, 150));
        estudianteClick.setAlignmentX(CENTER_ALIGNMENT);
        clickerPanel.add(estudianteClick);

     
        add(clickerPanel, BorderLayout.CENTER);
        
        //Se llama al método para cargar los mensajes del csv
        cargarMensajesCSV();
        
        // Se añade el panel de mensajes
        Mensaje mensaje = new Mensaje();
        labelMensajes = new JLabel("¡Empieza a hacer click!");
        mensaje.add(labelMensajes);
        add(mensaje, BorderLayout.NORTH);
        
        //Se crea la lista que contendrá los mensajes
        mensajes = new ArrayList<String>();
        
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
            	//Puntos:
                puntos++;
                puntosBarra ++;
                labelPuntos.setText("Conocimiento: " + puntos);
                
                //Creditos:
                barraCreditos.setValue(puntosBarra);// Actualiza la barra con el valor de puntosBarra
                if (puntosBarra == barraCreditos.getMaximum()) {  // Asegurarse de que el valor esté dentro del rango
                	puntosBarra = 0;
                	barraCreditos.setValue(puntosBarra);
                	creditos++;
                	labelCreditos.setText("Créditos: " + creditos);
                	
        		}
                
                //Actualizacion mensaje:
                int seleccion;
                if (puntos % 10 == 0) {
                	seleccion = randomizador.nextInt(0, 21);        	
                	labelMensajes.setText(mensajes.get(seleccion));
                }
            }
        });
        
       
        
        
        
        
        
        /*
        
        //SCROLL de los Materiales
        JScrollPane jScrollMateriales = new Materiales();
        add(jScrollMateriales,BorderLayout.EAST);
        
        */
        
        
        
        
        
        
        
        
        //JMENU (ajustes)
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuPrincipal = new JMenu("Ajustes");
        menuPrincipal.setMnemonic(KeyEvent.VK_A);
        
        JMenu menuCheats = new JMenu("Cheats");
        menuCheats.setMnemonic(KeyEvent.VK_C);
        menuPrincipal.add(menuCheats);
        
        JMenuItem menuSumar100Puntos = new JMenuItem("Sumar 100 puntos (conocimiento)");
        menuSumar100Puntos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 100;
				creditos = creditos + 10;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar100Puntos);
        
        JMenuItem menuSumar1000Puntos = new JMenuItem("Sumar 1000 puntos (conocimiento)");
        menuSumar1000Puntos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 1000;
				creditos = creditos + 100;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar1000Puntos);
        
        menuCheats.addSeparator();
        
        JMenuItem menuSumar10Creditos = new JMenuItem("Sumar 100 créditos");
        menuSumar10Creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 1000;
				creditos = creditos + 100;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar10Creditos);
        
        JMenuItem menuSumar100Creditos = new JMenuItem("Sumar 100 créditos");
        menuSumar100Creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 10000; 
				creditos = creditos + 1000;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar100Creditos);
        
        menuCheats.addSeparator();
        
        JMenuItem menuReset = new JMenuItem("Reset valores");
        menuReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = 0;
				creditos = 0;
				labelPuntos.setText("Conocimiento: ");
				labelCreditos.setText("Créditos: ");
			}
		});
        menuCheats.add(menuReset);
        
      
        menuPrincipal.addSeparator();
        
        JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.setMnemonic(KeyEvent.VK_S);
		menuSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showConfirmDialog(VentanaJuego.this, "¿Quiere salir?", "Salir", JOptionPane.YES_NO_OPTION);
				if (seleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		});
        menuPrincipal.add(menuSalir);
        
        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);
        
        
        //JOptionPane salir ventana
        addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int seleccion = JOptionPane.showConfirmDialog(VentanaJuego.this, "¿Quiere salir?", "Salir", JOptionPane.YES_NO_OPTION);
				if (seleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
        

    }
    
    
    
    //Método encargado de leer el csv de mensajes
    public void cargarMensajesCSV() {
		try (Scanner scanner = new Scanner(new File("src/mensajes.csv"))) {
			int contador = 0;
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				if (contador > 0) { //La primera línea del fichero no contiene un mensaje
					try {
						mensajes.add(linea);
					} catch (Exception e) {
						System.out.println("Error procesando los datos del fichero de mensajes.");
					}
				}
				contador++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido abrir el fichero de mensajes.");
		} 
	}  
}
    
