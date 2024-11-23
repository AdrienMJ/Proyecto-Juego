package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTabbedPane;



public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    
    //Random:
    static Random randomizador = new Random();
    
    //PUNTOS DE CONOCIMIENTO
    public  double puntos = 0;  // Contador de puntos
    
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos = 0;
    public int puntosBarra = 0;
    public int maxCreditos = 10; //Puntos iniciales de los creditos
    
    //REFERENCIA AL JLABEL DE MENSAJES:
    public JLabel labelMensajes;
    
    //LISTA QUE CONTENDRÁ LOS MENSAJES A MOSTRAR:
    public List<String> mensajes;
    
    //PESTAÑA DE ESTADISTICAS:
    public JTabbedPane jTabbPrincipal;
    public JTabbedPane jTabbEstadis;
    
    public VentanaJuego() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        
        
        
        //Panel PRINCIPAL:
        JPanel panelPrincipal = new JPanel();
        
        // Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.Y_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
       
        
        // JLabel para mostrar los puntos
        JLabel labelPuntos = new JLabel("Conocimiento: " +  puntos);
        labelPuntos.setBackground(Color.GRAY);
        labelPuntos.setAlignmentX(CENTER_ALIGNMENT);  // Centrar el label en el panel
        labelPuntos.setFont(new Font("Arial", Font.BOLD, 16));
        clickerPanel.add(labelPuntos);
        
        // Botón Principal (Puntos de CONOCIMIENTO)
        JButton estudianteClick = new JButton();
        estudianteClick.setPreferredSize(new Dimension(150, 250));
        estudianteClick.setAlignmentX(CENTER_ALIGNMENT);
        estudianteClick.setIcon(new ImageIcon(getClass().getResource("/Imagenes/estuadinte2.png")));
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
      	barraCreditos = new JProgressBar(0, maxCreditos); //Los valores son de que valor MAX y min
      	barraCreditos.setStringPainted(true);//Queremos que se pinte el X% (SÍ/NO)
      	
      	JLabel labelCreditos = new JLabel();
      	
      	//Añadimos la barra y el Label al panel
      	panelBarra.add(labelCreditos);
      	panelBarra.add(barraCreditos);
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
                barraCreditos.setValue(puntosBarra);// Actualiza la barra con el valor de puntosBarra
                
                if (puntosBarra == barraCreditos.getMaximum()) {  // Asegurarse de que el valor esté dentro del rango
                	puntosBarra = 0;
                	maxCreditos *= 1.5;
                	barraCreditos.setValue(puntosBarra);
                	barraCreditos.setMaximum(maxCreditos);;
                	creditos++;
                	labelCreditos.setText("Créditos: " + creditos);
                	
        		}
                
                //ESTO DA ERROR!!!!!!!!!!! ("Index "el que sea" out of bounds for length 0")
                //Actualizacion mensaje:
                int seleccion;
                if (puntos % 10 == 0) {
                	seleccion = randomizador.nextInt(0, 20);        	
                	labelMensajes.setText(mensajes.get(seleccion));
                }
            }
        });
        
   
        //SCROLL de los Materiales
        JScrollPane jScrollMateriales = new JScrollPane();
        
        
      //Paneles de MULTIPLICADORES (Lápices, Cuadernos,...
		
		//Panel principal de los Multiplicadores
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor,BoxLayout.Y_AXIS));
		panelContenedor.setSize(getPreferredSize());
		JPanel panelBotonesCompra = new JPanel();
		
		//Se añade un panel con botones. Estos serán los encargados de comprar de 1 en 1, de 10 en 10....
		panelBotonesCompra.setLayout(new BoxLayout(panelBotonesCompra,BoxLayout.X_AXIS));
		
		JButton botonx1 = new JButton("x1");
		JButton botonx10 = new JButton("x10");
		JButton botonx100 = new JButton("x100");
		
		Dimension dimensionBoton = new Dimension(60,20);
		botonx1.setPreferredSize(dimensionBoton);
		botonx10.setPreferredSize(dimensionBoton);
		botonx100.setPreferredSize(dimensionBoton);
		
		panelBotonesCompra.add(new JLabel("Comprar: "));
		panelBotonesCompra.add(botonx1);
		panelBotonesCompra.add(botonx10);
		panelBotonesCompra.add(botonx100);
			
		panelContenedor.add(panelBotonesCompra);
		
		//TODO - Hay que seguir construyendo las mejoras como se ha construido el lapiz
			
		//lapiz
		Mejora lapiz = new Mejora("Lapiz", 15, 0.1, 1.15);
		PanelMejora panelLapiz = new PanelMejora(lapiz);
		panelContenedor.add(panelLapiz);
		panelLapiz.botonCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				puntos = lapiz.comprarMejora(puntos); // Actualiza los puntos
				labelPuntos.setText("Conocimiento: " + puntos); // Actualiza el label de los puntos
				panelLapiz.actualizarPanel(lapiz);

			}
		});
		
		//Cuaderno
		Mejora cuaderno = new Mejora("Cuaderno", 100, 1, 1.152);
		PanelMejora panelCuaderno = new PanelMejora(cuaderno);
		panelContenedor.add(panelCuaderno);
		panelCuaderno.botonCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				puntos = cuaderno.comprarMejora(puntos); // Actualiza los puntos
				labelPuntos.setText("Conocimiento: " + puntos); // Actualiza el label de los puntos
				panelCuaderno.actualizarPanel(cuaderno);

			}
		});

		//Borragoma
		Mejora borragoma = new Mejora("Borragoma",1_100, 8, 1.154);
		PanelMejora panelBorragoma = new PanelMejora(borragoma);
		panelContenedor.add(panelBorragoma);
		panelBorragoma.botonCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				puntos = borragoma.comprarMejora(puntos); // Actualiza los puntos
				labelPuntos.setText("Conocimiento: " + puntos); // Actualiza el label de los puntos
				panelBorragoma.actualizarPanel(borragoma);

			}
		});
		
		//Saca puntas
		Mejora sacaPuntas = new Mejora("Saca-puntas", 12_000, 47, 1.156);
		PanelMejora panelSacaPuntas = new PanelMejora(sacaPuntas);
		panelContenedor.add(panelSacaPuntas);
		panelSacaPuntas.botonCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				puntos = sacaPuntas.comprarMejora(puntos); // Actualiza los puntos
				labelPuntos.setText("Conocimiento: " + puntos); // Actualiza el label de los puntos
				panelSacaPuntas.actualizarPanel(sacaPuntas);

			}
		});
				
		//Mesa
		Mejora mesa = new Mejora("Mesa", 130_000, 260, 1.158);
		PanelMejora panelMesa = new PanelMejora(mesa);
		panelContenedor.add(panelMesa);
		panelMesa.botonCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				puntos = mesa.comprarMejora(puntos); // Actualiza los puntos
				labelPuntos.setText("Conocimiento: " + puntos); // Actualiza el label de los puntos
				panelMesa.actualizarPanel(mesa);

			}
		});
//				
//		//Boligrafo
//		Mejora boligrafo = new Mejora("boligrafo",5_000_000,1_000);
//		PanelMejora panelBoli = new PanelMejora(boligrafo);
//		panelContenedor.add(panelBoli);		
//		
//		//Libro de Matematicas (Se desbloquean los minijuegos)
//		Mejora libroMate = new Mejora("Libro de Mate",5_000_000,1_000);
//		PanelMejora panelLibroMate = new PanelMejora(libroMate);
//		panelContenedor.add(panelLibroMate);		
		
		//Profesor particular
				
		//Proyectos escolares
				
		//Ordenador
				
		//Cerebro
				
		//IQ
				
		//ChatGPT
				
		//Ganas
				
		//Atención
				
		//Albert Einstein
				
		//Ordenador Cuántico
		
		//Rick Sanchez
		
		panelContenedor.setVisible(true);
		jScrollMateriales.setViewportView(panelContenedor);
		jScrollMateriales.setVisible(true);
    
        add(jScrollMateriales,BorderLayout.EAST);
             

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
        
        
        panelPrincipal.add(clickerPanel);
        panelPrincipal.add(panelBarra);
        panelPrincipal.add(jScrollMateriales);
        panelPrincipal.setBackground(Color.LIGHT_GRAY);
        
        jTabbPrincipal = new JTabbedPane();
        jTabbPrincipal.addTab("Ventana Principal", panelPrincipal);
        add(jTabbPrincipal);
        
        //Pestaña estadisticas (Tabla): 
        JPanel panelEstadis = new JPanel();
        
        jTabbPrincipal.addTab("Estadísticas", panelEstadis);
        add(jTabbEstadis);       
        
        
        
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
						System.err.println("Error procesando los datos del fichero de mensajes.");
					}
				}
				contador++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido abrir el fichero de mensajes.");
		}
		
		
		
		
		
	}
    
    
    
}
    
