package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;



public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    
    //Random:
    static Random randomizador = new Random();
    
    //PUNTOS DE CONOCIMIENTO
    public  double puntos = 0;  // Contador de puntos
    
    //LABEL DE PUNTOS
    public JLabel labelPuntos;
    
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos = 0;
    public double puntosBarra = 0;
    public int maxCreditos = 10; //Puntos iniciales de los creditos
    public JLabel labelCreditos;
    
    //REFERENCIA AL JLABEL DE MENSAJES:
    public JLabel labelMensajes;
    
    //LISTA QUE CONTENDRÁ LOS MENSAJES A MOSTRAR:
    public List<String> mensajes;
    
    //LISTA DE LAS MEJORAS
    public ArrayList<Mejora> listaMejoras;
    
    //HILOS
    public Thread hiloActualizarPuntos;
    public ThreadActualizadorPuntos actualizarPuntos;
    
    //PESTAÑA DE ESTADISTICAS:
    public JTabbedPane jTabbPrincipal;
    public JTabbedPane jTabbEstadis;
    
    
    public VentanaJuego() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //Lista de Mejoras
        listaMejoras = new ArrayList<Mejora>();
        
        //Hilo creado
        actualizarPuntos = new ThreadActualizadorPuntos(this);
        hiloActualizarPuntos = new Thread(actualizarPuntos);
        hiloActualizarPuntos.start();
        
        
        //Panel PRINCIPAL:
        JPanel panelPrincipal = new JPanel();
        //panelPrincipal.setLayout(new FlowLayout());
        
        //Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.Y_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
        clickerPanel.setBackground(Color.BLUE);
        
        
       
        
        // JLabel para mostrar los puntos
        labelPuntos = new JLabel("Conocimiento: " +  puntos);
        labelPuntos.setBackground(Color.GRAY);
        labelPuntos.setAlignmentX(CENTER_ALIGNMENT);  // Centrar el label en el panel
        labelPuntos.setFont(new Font("Arial", Font.BOLD, 16));
        clickerPanel.add(labelPuntos);
        
        // Botón Principal (Puntos de CONOCIMIENTO)
        JButton estudianteClick = new JButton();
        estudianteClick.setPreferredSize(new Dimension(200, 250));
        estudianteClick.setAlignmentX(CENTER_ALIGNMENT);
        estudianteClick.setIcon(new ImageIcon(getClass().getResource("/Imagenes/estuadinte2.png")));
        clickerPanel.add(estudianteClick);

        add(clickerPanel, BorderLayout.EAST);
        
        
        
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
      	barraCreditos = new JProgressBar(0, maxCreditos); //Los valores son de que valor MAX y min
      	barraCreditos.setStringPainted(true);//Queremos que se pinte el X% (SÍ/NO)
      	
      	labelCreditos = new JLabel();
      	
      	//Añadimos la barra y el Label al panel
      	panelBarra.add(labelCreditos , BorderLayout.NORTH);
      	panelBarra.add(barraCreditos, BorderLayout.NORTH);
      	panelBarra.setBackground(Color.LIGHT_GRAY);
      	clickerPanel.add(panelBarra, BorderLayout.CENTER);
        
      	//Acción del botón para incrementar los PUNTOS y los CREDITOS
      	labelCreditos.setText("Créditos: " + creditos);
      	estudianteClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Puntos:
                puntos++;
                puntosBarra ++;
                labelPuntos.setText("Conocimiento: " + puntos);
                
                //Creditos:
                barraCreditos.setValue((int)puntosBarra);// Actualiza la barra con el valor de puntosBarra
                
                if (puntosBarra >= barraCreditos.getMaximum()) {  // Asegurarse de que el valor esté dentro del rango
                	puntosBarra = 0;
                	maxCreditos *= 1.5;
                	barraCreditos.setValue((int)puntosBarra);
                	barraCreditos.setMaximum(maxCreditos);;
                	creditos++;
                	labelCreditos.setText("Créditos: " + creditos);
                	
        		}
                
                //ESTO DA ERROR!!!!!!!!!!! ("Index "el que sea" out of bounds for length 0")
                //Actualizacion mensaje:
                int seleccion;
                if (puntos % 10 == 0) {
                	seleccion = randomizador.nextInt(1, 20);        	
                	labelMensajes.setText(mensajes.get(seleccion));
                }
            }
        });
        
      
   
        //SCROLL de los Materiales
        JScrollMateriales jScrollMateriales = new JScrollMateriales(this);
		jScrollMateriales.setVisible(true);
        panelPrincipal.add(jScrollMateriales,BorderLayout.EAST);
         

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
        
        JMenuItem menuSumar1000Puntos = new JMenuItem("Sumar 1000000000 puntos (conocimiento)");
        menuSumar1000Puntos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 1000000000;
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
					
					//PARTE DE HILOS
					actualizarPuntos.detener();// Detén el hilo 
	                
					System.exit(0);
				}
			}
		});
        
        
        panelPrincipal.add(clickerPanel);
        panelPrincipal.add(panelBarra);
        panelPrincipal.add(jScrollMateriales);
        
        
        jTabbPrincipal = new JTabbedPane();
        jTabbPrincipal.addTab("Ventana Principal", panelPrincipal);
        add(jTabbPrincipal);
        
      //Pestaña estadisticas (Libro de Matemáticas):
        JPanel panelMinijuego = new JPanel(new BorderLayout());
        jTabbPrincipal.addTab("Mini Juego", panelMinijuego);
        add(jTabbPrincipal);
        
        //Boton de minijuego(Libro de Matemáticas)!!!!!!!!!!!!!!!!!!!
        JButton botonMiniJuego = new JButton("JUEGA AQUÍ");
        botonMiniJuego.setSize(new Dimension(50, 20));
        panelMinijuego.add(botonMiniJuego, BorderLayout.EAST);
        
        //Pestaña estadisticas (Tabla):
        JPanel panelEstadis = new JPanel(new BorderLayout());
        jTabbPrincipal.addTab("Estadísticas", panelEstadis);
        add(jTabbPrincipal);

        // Tabla de estadísticas
        JTable tablaEstadis = new JTable(new ModeloTablaEstadis(listaMejoras));
        tablaEstadis.setDefaultRenderer(Object.class, new RendererTablaEstadis()); 
        JScrollPane jScrollTablaEstadis = new JScrollPane(tablaEstadis);

        // Añadir componentes al panel
        panelEstadis.add(jScrollTablaEstadis, BorderLayout.CENTER);
        
        
        
        
        
    
    
    }
    
    	//Método encargado de leer el csv de mensajes
    	public void cargarMensajesCSV() {
			try (Scanner scanner = new Scanner(new File("src/ficheros/mensajes.csv"))) {
				int contador = 0;
				while (scanner.hasNextLine()) {
					String linea = scanner.nextLine();
					if (contador > 0) { //La primera línea del fichero no contiene un mensaje
						try {
							mensajes.add(linea);
						} catch (Exception e) {
							System.err.println("Error procesando los datos del fichero de mensajes.");
						}
						//"/Imagenes/estuadinte2.png"
					}
					contador++;
				}
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido abrir el fichero de mensajes.");
			}
	
    	}
    	
    	//Método para ir actualizando los puntos según la ListaMejoras
    	public void actualizarPuntos() {
    		for (Mejora mejora : listaMejoras) {
    				puntos = puntos	+ mejora.getGanacia();
    		}
    	}
    
    
    
}
    
