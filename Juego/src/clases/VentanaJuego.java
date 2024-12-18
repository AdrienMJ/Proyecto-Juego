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
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


import javax.swing.BoxLayout;
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
    public  float puntos = 0;  // Contador de puntos
    
    //LABEL DE PUNTOS
    public JLabel labelPuntos;
    
    //CONOCIMIENTO POR SEGUNDO
    public JLabel labelConocimientoSegundo;
    public double conocimientoSegundo;
    
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos = 0;
    public double puntosBarra = 0;
    public int maxCreditos = 10; //Puntos iniciales de los creditos
    public JLabel labelCreditos;
    
    //REFERENCIA AL JLABEL DE MENSAJES:
    public JLabel labelMensajes;
    
    //LISTA QUE CONTENDRÁ LOS MENSAJES A MOSTRAR: (YA INSTANCIADA)
    public List<String> mensajes = new ArrayList<String>();
    
    //LISTA DE LAS MEJORAS
    public ArrayList<Mejora> listaMejoras;
    
    //HILOS
    public Thread hiloActualizarPuntos;
    public ThreadActualizadorPuntos actualizarPuntos;
    public Thread hiloActualizarCreditos;
    public ThreadActualizadorCreditos actualizarCreditos;
    
    //PESTAÑA DE ESTADISTICAS:
    public JTabbedPane jTabbPrincipal;
    public JTabbedPane jTabbEstadis;
    
    //PESTAÑA ESTADISTICAS:
    
    
    //PESTAÑA TIENDA:
    JLabel labelCreditosTienda = new JLabel(); //Label de la tienda
    public TodosLosObjetos todosLosObjetos;
    public ArrayList<Objeto> listaObjetos;
    JButton botonComprar;
    JButton botonDesechar;
    
    public VentanaJuego() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //Lista de Mejoras
        listaMejoras = new ArrayList<Mejora>();
        
        //Hilo  de puntos creado
        actualizarPuntos = new ThreadActualizadorPuntos(this);
        hiloActualizarPuntos = new Thread(actualizarPuntos);
        hiloActualizarPuntos.start();
        
        
        
        
        //Panel PRINCIPAL:
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        //panelPrincipal.setLayout(new FlowLayout());
        
        //Panel para el botón y el label de puntos
        JPanel clickerPanel = new JPanel();
        clickerPanel.setLayout(new BoxLayout(clickerPanel, BoxLayout.Y_AXIS));  // Aseguramos que el BoxLayout se aplique al clickerPanel
        clickerPanel.setBackground(Color.WHITE);
        
        
       
        
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

        add(clickerPanel, BorderLayout.WEST);
        
        
        
        //Se llama al método para cargar los mensajes del csv
        cargarMensajesCSV();
        
        // Se añade el panel de mensajes
        Mensaje mensaje = new Mensaje();
        labelMensajes = new JLabel("¡Empieza a hacer click!");
        mensaje.add(labelMensajes);
        add(mensaje, BorderLayout.NORTH);
        
        //CREDITOS:
        //Panel de la barra de progreso
      	JPanel panelBarra = new JPanel();
      	panelBarra.setPreferredSize(new Dimension(100,30));
      		
      	//Se crea la barra de progreso
      	barraCreditos = new JProgressBar(0, maxCreditos); //Los valores son de que valor MAX y min
      	barraCreditos.setStringPainted(true);//Queremos que se pinte el X% (SÍ/NO)
      	
      	labelCreditos = new JLabel();
      	
       //Hilo de creditos creado
        actualizarCreditos = new ThreadActualizadorCreditos(this);
        hiloActualizarCreditos = new Thread(actualizarCreditos);
        hiloActualizarCreditos.start();
      	
      	//Añadimos la barra y el Label al panel
      	panelBarra.add(labelCreditos , BorderLayout.NORTH);
      	panelBarra.add(barraCreditos, BorderLayout.NORTH);
      	panelBarra.setBackground(Color.LIGHT_GRAY);
      	clickerPanel.add(panelBarra, BorderLayout.CENTER);
        
      	
      	 
         
      	
      	
      	//Acción del botón para incrementar los PUNTOS y los CREDITOS, y para cambiar el mensaje motivacional
      	labelCreditos.setText("Créditos: " + creditos);
      	labelCreditosTienda.setText(String.format("Tienes para gastar: %d Créditos", creditos)); //Se hace un formato String para poder aplicar correctamente el formato
      	estudianteClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Puntos:
                puntos++;
                puntosBarra ++;
                labelPuntos.setText("Conocimiento: " + redondearPuntos(puntos));
                
                //Creditos:
                barraCreditos.setValue((int)puntosBarra);// Actualiza la barra con el valor de puntosBarra
                
                //Creditos de la pestaña tienda
                labelCreditosTienda.setText(String.format("Tienes para gastar: %d Créditos", creditos)); //Se hace un formato String para poder aplicar correctamente el formato
                

             
                //Actualizacion mensaje:
                int seleccion; //selección de línea del csv de mensajes.
                if (puntos % 10 == 0) {
                	seleccion = randomizador.nextInt(1, mensajes.size());        	
                	labelMensajes.setText(mensajes.get(seleccion));
                }
            }
        });
        
      
   
        //SCROLL de los Materiales
        JScrollMateriales jScrollMateriales = new JScrollMateriales(this);
		jScrollMateriales.setVisible(true);
        
         

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
					actualizarCreditos.detener();
	                
					System.exit(0);
				}
			}
		});
        
        
        panelPrincipal.add(clickerPanel,BorderLayout.CENTER);
        panelPrincipal.add(panelBarra, BorderLayout.NORTH);
        panelPrincipal.add(jScrollMateriales,BorderLayout.EAST);
        
        
        jTabbPrincipal = new JTabbedPane();
        jTabbPrincipal.addTab("Ventana Principal", panelPrincipal);
        add(jTabbPrincipal);
        
        //Pestaña Tienda (Tabla):
        JPanel panelTienda = new JPanel();
        jTabbPrincipal.addTab("Tienda", panelTienda);
        panelTienda.setLayout(new BoxLayout(panelTienda, BoxLayout.Y_AXIS));
        panelTienda.add(labelCreditosTienda);
        add(jTabbPrincipal);
        
        //Tabla de tienda:
        listaObjetos = new ArrayList<Objeto>();
        todosLosObjetos = new TodosLosObjetos(this);
        JTable tablaTienda = new JTable(new ModeloTablaTienda(listaObjetos));
        tablaTienda.setDefaultRenderer(Object.class, new RendererTablaTienda()); 
        JScrollPane jScrollTablaTienda = new JScrollPane(tablaTienda); //Panel de los objetos que se pueden comprar
        panelTienda.add(jScrollTablaTienda);
        
        //Botones de la tienda(COMPRAR/DESECHAR):
        botonComprar = new JButton("COMPRAR"); //Boton para comprar en la tienda
        botonDesechar = new JButton("DESECHAR"); //Boton para desechar la compra
        JPanel panelBotonesTienda = new JPanel();
        panelBotonesTienda.setLayout(new FlowLayout());
        panelBotonesTienda.add(botonComprar);
        panelBotonesTienda.add(botonDesechar);
        
        
        //Listeners de los botones de la Tienda:
        ActionListener listenerComprar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				for (Objeto objeto : listaObjetos) {
					if (objeto.getBotonObjeto().getBackground().equals(Color.RED)) {
						if (creditos >= objeto.getCosteCreditos()) {
							creditos -= objeto.getCosteCreditos();
								
							objeto.getBotonObjeto().setSelected(true);
								
							labelCreditos.setText("Creditos: " + creditos);
							labelCreditosTienda.setText(String.format("Tienes para gastar: %d Créditos", creditos));
						} else {       
							JOptionPane.showMessageDialog(null, "No tienes suficientes créditos para comprar este objeto.");
						}
					}
						
						 
					}
					

				}
				
			
		};
		
		ActionListener listenerDeschar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Objeto objeto : listaObjetos) {
					if (objeto.getBotonObjeto().getBackground().equals(Color.RED)) {
					objeto.getBotonObjeto().setSelected(false);
					}
				}
				
				
			}
		};
		
		
		botonDesechar.addActionListener(listenerDeschar);
		botonComprar.addActionListener(listenerComprar);
		
		//Explicacion del panel de la Tienda
		JPanel explicacion = new JPanel();
		explicacion.setLayout(new BorderLayout());
		JLabel funcionamientoTienda = new JLabel("<html>*Para comprar, debes clicar en el objeto que tu quieras, para después, darle al boton<br> de comprar. Para desechar la compra es el mismo proceso.<html>");
		funcionamientoTienda.setFont(new Font("Arial", Font.ITALIC, 14));
		explicacion.add(funcionamientoTienda, BorderLayout.SOUTH);
		
        panelTienda.add(panelBotonesTienda); //Panel de botones (COMPRAR/DESECHAR)
        panelTienda.add(explicacion); //Explicacion del funcionamiento de la compra
        
        
        
        //Pestaña MiniJuego (Libro de Matemáticas):
        JPanel panelMinijuego = new JPanel();
        jTabbPrincipal.addTab("Mini Juego", panelMinijuego);
        add(jTabbPrincipal);
        
       
        
        //Pestaña ESTADISTICAS (Tabla):
        labelCreditosTienda.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel panelEstadis = new JPanel(new BorderLayout());
        jTabbPrincipal.addTab("Estadísticas", panelEstadis);
        
        add(jTabbPrincipal);
        

        // Tabla de estadísticas
        JTable tablaEstadis = new JTable(new ModeloTablaEstadis(listaMejoras));
        tablaEstadis.setDefaultRenderer(Object.class, new RendererTablaEstadis()); 
        JScrollPane jScrollTablaEstadis = new JScrollPane(tablaEstadis);
        panelEstadis.add(jScrollTablaEstadis, BorderLayout.CENTER);
        JLabel labelPuntosSeg = new JLabel();
        
        
        //Visualizar los puntos/seg:
        double puntosSeg = 0;
        for (int i = 0; i < tablaEstadis.getRowCount(); i++) {
            Object valor = tablaEstadis.getValueAt(i, 2); // Columna índice 2

            // Verificar y convertir el valor a double
            if (valor instanceof Number) {
                puntosSeg += ((Number) valor).doubleValue();
            } else if (valor instanceof String) {
                try {
                    puntosSeg += Double.parseDouble((String) valor);
                } catch (NumberFormatException e) {
                    System.err.println("No se puede convertir a número: " + valor);
                }
            } else {
                System.err.println("Valor inesperado en fila " + i + ", columna 2: " + valor);
            }
        }
        
        labelPuntosSeg.setText("Se están produciendo: " + puntosSeg + " IQ/seg");
        panelEstadis.add(labelPuntosSeg, BorderLayout.SOUTH);
        
        
   
    
    
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
					}
					contador++;
				}
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido abrir el fichero de mensajes.");
			}
	
    	}
    	
    	/**Metodo para redondear los puntos (mil, millon, billon...)
    	 * 
    	 * @param puntos el numero de puntos del que se quiera redondear
    	 * @return devuelve un String con la cantidad formateada en mil, millon, mil millon, etc
    	 */
    	public String redondearPuntos(double puntos) {

    		float redondeoPuntos = 0;
    		if (puntos < 1_000) {
    			return puntos + " de IQ";
    			
    		} else if (puntos< 1_000_000) {
    			redondeoPuntos = (float) puntos / 1_000.0f;
    			return redondeoPuntos + " mil de IQ";
    			
    		} else if (puntos < 1_000_000_000) {
    			redondeoPuntos = (float) puntos / 1_000_000.f;
    			return redondeoPuntos + " millones de IQ";
    		} else if (puntos < 1*Math.pow(10, 12)) {
    			redondeoPuntos = (float) puntos / 1_000_000_000.0f;
    			return redondeoPuntos + " mil millones de IQ";
    		} else return puntos + " de IQ";
    	}
    	
    	
    
    
    
}
    
