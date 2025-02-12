package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.text.DecimalFormat;

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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import db.GestorBD;
import domain.Mejora;
import domain.Objeto;
import domain.Usuario;



public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    //USUARIO
    public Usuario usuario;
    
    //Gestion de BD
    private GestorBD gestorBD = new GestorBD();
    
    //Random:
    static Random randomizador = new Random();
    
    //Message:
    JOptionPane mensaje = new JOptionPane();
    
    //FORMATO DE PUNTOS
    DecimalFormat formatoPuntos = new DecimalFormat("#.00");
    
    //PUNTOS DE CONOCIMIENTO
    public  float puntos;  // Contador de puntos
    
    //LABEL DE PUNTOS
    public JLabel labelPuntos;
    
    //CONOCIMIENTO POR SEGUNDO
    public JLabel labelConocimientoSegundo;
    public double conocimientoSegundo;
    
    //BARRA Y CREDITOS
    public JProgressBar barraCreditos; //Barra de progreso de creditos
    public int creditos;
    public double puntosBarra = 0;
    public int maxCreditos = 10; //Puntos iniciales de los creditos
    public JLabel labelCreditos;
    
    //REFERENCIA AL JLABEL DE MENSAJES:
    public JLabel labelMensajes;
    
    //LISTA QUE CONTENDRÁ LOS MENSAJES A MOSTRAR: (YA INSTANCIADA)
    public List<String> mensajes = new ArrayList<String>();
    
    //LISTA DE LAS MEJORAS
    public List<Mejora> listaMejoras;
    
    //REFERENCIA AL PANEL DE MEJORAS:
    public PanelMejora panelMejora;
    
    //HILOS
    public Thread hiloActualizarPuntos;
    public ThreadActualizadorPuntos actualizarPuntos;
    public Thread hiloActualizarCreditos;
    public ThreadActualizadorCreditos actualizarCreditos;
    
    //PESTAÑA DE ESTADISTICAS:
    public JTabbedPane jTabbPrincipal;
    public JTabbedPane jTabbEstadis;
    
    //LISTA DE LAS OPCIONES DEL MINIJUEGO DEL LIBRO DE MATEMÁTICAS:
	ArrayList<String> listaOpcionesEcuaciones = new ArrayList<>();

	//PANEL EN EL QUE SE ENCONTRARÁ EL MINIJUEGO
	//private JPanel panelMinijuego;
	private JPanel panelPrincipalMinijuego = new JPanel();
	private JPanel panelCentralMinijuego = new JPanel();
    
	//NUMERO DE LA RESPUESTA CORRECTA EN EL MINIJUEGO;
	private int numeroRespuestaCorrecta;
	
    //PESTAÑA ESTADISTICAS:
    
	
	//HILO MINIJUEGO CUADERNOS:
	private Thread hiloCuadernos;
    
	//VALORES MINIJUEGO CUADERNO:
	JLabel textoCuadernoSolicitado = new JLabel();
	JLabel imagenCuadernoActual = new JLabel();
	ArrayList<ImageIcon> listaLlenaCuadernos = new ArrayList<>();
	
    //PESTAÑA TIENDA:
    JLabel labelCreditosTienda = new JLabel(); //Label de la tienda
    public TodosLosObjetos todosLosObjetos;
    public ArrayList<Objeto> listaObjetos;
    JButton botonComprar;
    JButton botonDesechar;
    
    
    public ArrayList<JRadioButton> listaBotonesObjetos = new ArrayList<JRadioButton>(); //Lista de los botones de clase "TodosLosObjetos"
    
    public VentanaJuego(Usuario usuario) {
    	//Generacion de datos segun usuario
    	this.usuario = usuario;
    	puntos = usuario.getPuntos();
    	creditos = usuario.getCreditos();
    	
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("EstudianteClicker");
        setSize(600, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //Lista de Mejoras
        gestorBD.conectarBD();
        listaMejoras = gestorBD.cargarPartida(usuario.getNombre(), usuario.getPassword());
        gestorBD.desconectarBD();
        System.out.println(listaMejoras.size());
        
        
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
        estudianteClick.setIcon(new ImageIcon("resources/images/estuadinte2.png"));
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
        JScrollMateriales jScrollMateriales = new JScrollMateriales(this); ////////////////////////////////
		jScrollMateriales.setVisible(true);
        
         

        //JMENU (ajustes)
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuPrincipal = new JMenu("Ajustes");
        menuPrincipal.setMnemonic(KeyEvent.VK_A);
        
        JMenu menuCheats = new JMenu("Cheats");
        menuCheats.setMnemonic(KeyEvent.VK_C);
        
        if (usuario.getNombre().equals("admin") && usuario.getPassword().equals("admin")) { //Cheats solo visibles si eres admin
            menuPrincipal.add(menuCheats);
        }
        
        JMenuItem menuSumar100Puntos = new JMenuItem("Sumar 100 puntos (IQ)");
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
        
        JMenuItem menuSumar100000Puntos = new JMenuItem("Sumar 100000 puntos (IQ)");
        menuSumar100000Puntos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 100000;
				creditos = creditos + 10000;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar100000Puntos);
        
        menuCheats.addSeparator();
        
        JMenuItem menuSumar100Creditos = new JMenuItem("Sumar 100 créditos");
        menuSumar100Creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 1000;
				creditos = creditos + 100;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar100Creditos);
        
        JMenuItem menuSumar100000Creditos = new JMenuItem("Sumar 100000 créditos");
        menuSumar100Creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				puntos = puntos + 1000000; 
				creditos = creditos + 100000;
				labelPuntos.setText("Conocimiento: " + puntos);
				labelCreditos.setText("Créditos: " + creditos);
			}
		});
        menuCheats.add(menuSumar100000Creditos);
        
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
					
					actualizarPuntos.detener();// Detén el hilo
					actualizarCreditos.detener();
					
					//Guardar partida del usuario
					usuario.setPuntos(puntos);
					usuario.setCreditos(creditos);
					gestorBD.conectarBD();
					gestorBD.actualizarUsuario(usuario);
					gestorBD.guardarPartida(usuario.getNombre(), usuario.getPassword(), listaMejoras);
					gestorBD.desconectarBD();
					
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
					//Guardar usuario
					usuario.setPuntos(puntos);
					usuario.setCreditos(creditos);
					gestorBD.conectarBD();
					gestorBD.actualizarUsuario(usuario);
					gestorBD.guardarPartida(usuario.getNombre(), usuario.getPassword(), listaMejoras);
					gestorBD.desconectarBD();
	                
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
							listaBotonesObjetos.add(objeto.getBotonObjeto());
							
							
							labelCreditos.setText("Creditos: " + creditos);
							labelCreditosTienda.setText(String.format("Tienes para gastar: %d Créditos", creditos));
						} else {       
							JOptionPane.showMessageDialog(null, "No tienes suficientes créditos para comprar este objeto.");
						}
						
					
						if (!listaBotonesObjetos.contains(objeto.getBotonObjeto())) {
							objeto.getBotonObjeto().setSelected(false);
						} else if (listaBotonesObjetos.contains(objeto.getBotonObjeto()) &&  !objeto.getBotonObjeto().isSelected()) {
							objeto.getBotonObjeto().setSelected(true);
						}
						
						if (listaBotonesObjetos.size() > 3) {
							VentanaJuego.this.mensaje.showMessageDialog(null, "Solo se pueden comprar 3 objetos");
							
							if (listaObjetos.getLast() == objeto) {
								listaBotonesObjetos.remove(objeto);
								objeto.getBotonObjeto().setSelected(false);
								creditos += objeto.getCosteCreditos();
							}
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
					listaBotonesObjetos.remove(objeto.getBotonObjeto());
					
					if (!listaBotonesObjetos.contains(objeto.getBotonObjeto())) {
						objeto.getBotonObjeto().setSelected(false);
					} else if (listaBotonesObjetos.contains(objeto.getBotonObjeto()) &&  !objeto.getBotonObjeto().isSelected()) {
						objeto.getBotonObjeto().setSelected(true);
					}
					
					if (listaBotonesObjetos.size() == 0) {
						VentanaJuego.this.mensaje.showMessageDialog(null, "No se puede tener menos de 0 objetos");
					}
				}

					
					
				}
					
			}

			
		};
		
		
		botonDesechar.addActionListener(listenerDeschar);
		botonComprar.addActionListener(listenerComprar);
		
		//Explicacion del panel de la Tienda
		JPanel explicacion = new JPanel();
		explicacion.setLayout(new BorderLayout());
		JLabel funcionamientoTienda = new JLabel("<html>*Para comprar, debes clicar en el objeto que tu quieras, para después, darle al boton<br> de comprar. Para desechar la compra es el mismo proceso. Solo puedes tener comprados 3 objetos.<html>");
		funcionamientoTienda.setFont(new Font("Arial", Font.ITALIC, 14));
		explicacion.add(funcionamientoTienda, BorderLayout.SOUTH);
		
        panelTienda.add(panelBotonesTienda); //Panel de botones (COMPRAR/DESECHAR)
        panelTienda.add(explicacion); //Explicacion del funcionamiento de la compra
        
        
        
        
        
        //--------------------------
  
        //Pestaña MiniJuego (Libro de Matemáticas):
        //JPanel panelMinijuego = new JPanel();
        JLabel labelBaseMinijuego = new JLabel();
        labelBaseMinijuego.setText("No hay minijuegos disponibles en este momento. ¡Prueba a comprar artículos!");
        
        /*
        JButton temporal = new JButton("temporal");
        temporal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se borra lo que previamente había en el panel
                //panelPrincipalMinijuego.remove(labelBaseMinijuego);
                //panelPrincipalMinijuego.remove(temporal);

                minijuegoLibroMatematica();


                //panelPrincipalMinijuego.revalidate();
               // panelPrincipalMinijuego.repaint();
            }
        });

        
        panelPrincipalMinijuego.add(temporal);*/
        
        panelPrincipalMinijuego.add(labelBaseMinijuego);
        jTabbPrincipal.addTab("Mini Juego", panelPrincipalMinijuego);
        add(jTabbPrincipal);
        
       //----------------------------------------------------------------
        
        
       
        
        
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
			try (Scanner scanner = new Scanner(new File("resources/data/mensajes.csv"))) {
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

    		double redondeoPuntos = 0;
    		if (puntos < 1_000) {
    			String resultado = formatoPuntos.format(puntos);
    			return resultado + " de IQ";
    			
    		} else if (puntos< 1_000_000) {
    			redondeoPuntos =  puntos / 1_000.0f;
    			String resultado = formatoPuntos.format(redondeoPuntos);
    			return resultado + " mil de IQ";
    			
    		} else if (puntos < 1_000_000_000) {
    			redondeoPuntos =  puntos / 1_000_000.f;
    			String resultado = formatoPuntos.format(redondeoPuntos);
    			return resultado + " millones de IQ";
    		} else if (puntos < 1*Math.pow(10, 12)) {
    			redondeoPuntos =  puntos / 1_000_000_000.0f;
    			String resultado = formatoPuntos.format(redondeoPuntos);
    			return resultado + " mil millones de IQ";
    		} else return puntos + " de IQ";
    	}
    	


    	public void minijuegoLibroMatematica() {
    	    //Configuración de los elementos iniciales
    	    String enunciado = "";
    	    String opcion1 = "";
    	    String opcion2 = "";
    	    String opcion3 = "";

    	    JLabel labelEnunciado = new JLabel();
    	    JButton botonOpcion1 = new JButton();
    	    JButton botonOpcion2 = new JButton();
    	    JButton botonOpcion3 = new JButton();

    	    int eleccionConjuntoOpciones = randomizador.nextInt(0, 5); //0 inclusivo, 5 exclusivo; 5 posibilidades

    	    //Configuración de las opciones dependiendo de la elección
    	    if (eleccionConjuntoOpciones == 0) {
    	        enunciado = "¿En cuál de estas operaciones el valor de \"x\" tiene que ser 3?";
    	        opcion1 = "3x + 4 = 13"; //Respuesta correcta
    	        opcion2 = "x - 7 = -2";
    	        opcion3 = "2(x + 2) = 14";
    	        numeroRespuestaCorrecta = 1; //La respuesta se identifica como la primera
    	    } else if (eleccionConjuntoOpciones == 1) {
    	        enunciado = "¿En cuál de estas operaciones el valor de \"x\" tiene que ser 7?";
    	        opcion1 = "x - 5 = 10";
    	        opcion2 = "x + 4 = 12";
    	        opcion3 = "3x - 2 = 19"; //Respuesta correcta
    	        numeroRespuestaCorrecta = 3;
    	    } else if (eleccionConjuntoOpciones == 2) {
    	        enunciado = "¿En cuál de estas operaciones el valor de \"x\" tiene que ser -4?";
    	        opcion1 = "x + 5 = -1";
    	        opcion2 = "x - 3 = -7"; //Respuesta correcta
    	        opcion3 = "2x + 8 = 4";
    	        numeroRespuestaCorrecta = 2;
    	    } else if (eleccionConjuntoOpciones == 3) {
    	        enunciado = "¿En cuál de estas operaciones el valor de \"x\" tiene que ser 10?";
    	        opcion1 = "x / 2 = 5"; //Respuesta correcta
    	        opcion2 = "x - 8 = 1";
    	        opcion3 = "3x + 4 = 24";
    	        numeroRespuestaCorrecta = 1;
    	    } else if (eleccionConjuntoOpciones == 4) {
    	        enunciado = "¿En cuál de estas operaciones el valor de \"x\" tiene que ser 0?";
    	        opcion1 = "x - 1 = 1";
    	        opcion2 = "x + 5 = -5";
    	        opcion3 = "2x + 1 = 1"; //Respuesta correcta
    	        numeroRespuestaCorrecta = 3;
    	    }

    	    labelEnunciado.setText(enunciado);
    	    labelEnunciado.setFont(new Font("Arial", Font.PLAIN, 18));
    	    botonOpcion1.setText(opcion1);
    	    botonOpcion2.setText(opcion2);
    	    botonOpcion3.setText(opcion3);
    	    botonOpcion1.setFont(new Font("Arial", Font.BOLD, 20));
    	    botonOpcion2.setFont(new Font("Arial", Font.BOLD, 20));
    	    botonOpcion3.setFont(new Font("Arial", Font.BOLD, 20));
    	    
    	    
    	    
    	    botonOpcion1.addActionListener(e -> procesarRespuestaLibroMatematicas(1));
    	    botonOpcion2.addActionListener(e -> procesarRespuestaLibroMatematicas(2));
    	    botonOpcion3.addActionListener(e -> procesarRespuestaLibroMatematicas(3));

    	    //Reconfiguración del panel tras la acción
    	    panelPrincipalMinijuego.removeAll(); //Se quita todo lo que ya hubiese en el panel 
    	    panelPrincipalMinijuego.setLayout(new BorderLayout());
    	    panelPrincipalMinijuego.setBackground(Color.CYAN);

    	    panelCentralMinijuego = new JPanel(new GridLayout(3, 1, 10, 10));
    	    panelCentralMinijuego.setBackground(Color.CYAN);
    	    //Se añaden las respuestas posibles a la ecuación
    	    panelCentralMinijuego.add(botonOpcion1);
    	    panelCentralMinijuego.add(botonOpcion2);
    	    panelCentralMinijuego.add(botonOpcion3);

    	    panelPrincipalMinijuego.add(labelEnunciado, BorderLayout.NORTH);
    	    panelPrincipalMinijuego.add(panelCentralMinijuego, BorderLayout.CENTER);


    	  //  panelPrincipalMinijuego.revalidate();
    	  //  panelPrincipalMinijuego.repaint();
    	}

    	//Le indica al jugador si la respuesta es acertada o no.
    	private void procesarRespuestaLibroMatematicas(int respuestaUsuario) {
    	    if (respuestaUsuario == numeroRespuestaCorrecta) {
    	        JOptionPane.showMessageDialog(null, "¡Correcto! Recibes una bonificación del 20% en tu IQ", "Resultado", JOptionPane.INFORMATION_MESSAGE);
    	        
    	        this.puntos = (float) (this.puntos + this.puntos  * 0.2);
    	        
    	    } else {
    	        JOptionPane.showMessageDialog(null, "¡Incorrecto! No te desanimes...", "Resultado", JOptionPane.ERROR_MESSAGE);
    	    }

    	    
    	    restaurarEstadoInicial(); //Se devuelve el panel a su estado previo al minijuego.
    	}

    	
    	
		
    	public void minijuegoCuaderno() {
    		
    		JLabel labelInicial = new JLabel();
    		labelInicial.setText("Haz click en INICIAR para comenzar el minijuego. ¡Concéntrate!");
    		labelInicial.setHorizontalAlignment(SwingConstants.CENTER);
    		
    		listaLlenaCuadernos = (ArrayList<ImageIcon>) cargarImagenesCuadernos();
    		
    		int auxiliarEleccionCuaderno = randomizador.nextInt(0, 3);
    		
    		if (auxiliarEleccionCuaderno == 0) {
    			textoCuadernoSolicitado.setText("¡Detén el carrusel en el cuaderno azul!");
    		} else if (auxiliarEleccionCuaderno == 1 ) {
    			textoCuadernoSolicitado.setText("¡Detén el carrusel en el cuaderno rojo!");
    		} else {
    			textoCuadernoSolicitado.setText("¡Detén el carrusel en el cuaderno verde!");
    		}
    		
    		
    		imagenCuadernoActual.setIcon(listaLlenaCuadernos.get(0));; //empezamos por el primer cuaderno, por ejemplo
    		JPanel panelInferior = new JPanel(new BorderLayout());
    		
    		JButton botonDetener = new JButton("DETENER CARRUSEL");
    		botonDetener.setEnabled(false);
    		
    		JButton botonIniciar = new JButton("INICIAR");

    		botonIniciar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					botonIniciar.setEnabled(false);
					panelPrincipalMinijuego.remove(labelInicial);
					botonDetener.setEnabled(true);
					
					
					panelPrincipalMinijuego.add(imagenCuadernoActual, BorderLayout.CENTER);
					
					hiloCuadernos = new Thread() {
						public void run() {
							while (! Thread.currentThread().isInterrupted()) {
								int indiceCuadernoElegido = randomizador.nextInt(0, 3);
								imagenCuadernoActual.setIcon(listaLlenaCuadernos.get(indiceCuadernoElegido));
								panelPrincipalMinijuego.add(imagenCuadernoActual, BorderLayout.CENTER);
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
								}
								System.out.println("hilo de cuadernos funcionando");
							}
						}
						
					};
					
					hiloCuadernos.start();
				}
			});
    		
    		
    		botonDetener.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					hiloCuadernos.interrupt();
					
					procesarRespuestaCuaderno(auxiliarEleccionCuaderno);
					
				}
			});
    		
    	    panelPrincipalMinijuego.removeAll(); //Se quita todo lo que ya hubiese en el panel 
    	    panelPrincipalMinijuego.setLayout(new BorderLayout());
    	    panelPrincipalMinijuego.setBackground(Color.CYAN);
    		
    	    textoCuadernoSolicitado.setHorizontalAlignment(SwingConstants.CENTER); //para que el texto salga centrado
    		panelPrincipalMinijuego.add(textoCuadernoSolicitado, BorderLayout.NORTH);
    		panelPrincipalMinijuego.add(labelInicial, BorderLayout.CENTER);
    	    
    	    panelInferior.setBackground(Color.CYAN);
    	    panelInferior.add(botonIniciar, BorderLayout.WEST);
    	    panelInferior.add(botonDetener, BorderLayout.EAST);
    	    panelPrincipalMinijuego.add(panelInferior, BorderLayout.SOUTH);
    	    
    	    //panelPrincipalMinijuego.revalidate();
    	    //panelPrincipalMinijuego.repaint();
    	    
    	}

//		icono = new ImageIcon("resources/images/cuaderno.png");
//		imagenTamanyoAdecuado = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//		icono = new ImageIcon(imagenTamanyoAdecuado);
    	
    	public List<ImageIcon> cargarImagenesCuadernos() {
    		ArrayList<ImageIcon> listaCuadernos = new ArrayList<>();
    		ImageIcon cuadernoAzul = new ImageIcon("resources/images/cuadernoAzulMiniJuego.png");
    		ImageIcon cuadernoRojo = new ImageIcon("resources/images/cuadernoRojoMiniJuego.png");
    		ImageIcon cuadernoVerde = new ImageIcon("resources/images/cuadernoVerdeMiniJuego.png");

    		listaCuadernos.add(cuadernoAzul);
    		listaCuadernos.add(cuadernoRojo);
    		listaCuadernos.add(cuadernoVerde);
    		return listaCuadernos;
    	}
    	
    	private void procesarRespuestaCuaderno(int respuestaUsuario) {
    		boolean detenidoCorrectamente = false; //por defecto "creemos"que el carrusel no se ha detenido correctamente
			
			if (textoCuadernoSolicitado.getText().contains("azul") && imagenCuadernoActual.getIcon().equals(listaLlenaCuadernos.get(0))) { //comprobamos que ambos valores coinciden
		            detenidoCorrectamente = true;
		        } else if (textoCuadernoSolicitado.getText().contains("rojo") && imagenCuadernoActual.getIcon().equals(listaLlenaCuadernos.get(1))) {
		            detenidoCorrectamente = true;
		        } else if (textoCuadernoSolicitado.getText().contains("verde") && imagenCuadernoActual.getIcon().equals(listaLlenaCuadernos.get(2))) {
		            detenidoCorrectamente = true;
		        }
			if (detenidoCorrectamente) {
				JOptionPane.showMessageDialog(null, "¡Correcto! Recibes una bonificación del 10% en tu IQ", "Resultado", JOptionPane.INFORMATION_MESSAGE);
			
				this.puntos = (float) (this.puntos + this.puntos  * 0.1);
			
			} else {
				JOptionPane.showMessageDialog(null, "¡Incorrecto! No te desanimes...", "Resultado", JOptionPane.ERROR_MESSAGE);
			}
    	    
    	    restaurarEstadoInicial(); //Se devuelve el panel a su estado previo al minijuego.
    	}
    	
    	private void restaurarEstadoInicial() {
    	    panelPrincipalMinijuego.removeAll(); 
    	    JLabel labelBaseMinijuego = new JLabel("No hay minijuegos disponibles en este momento. ¡Prueba a comprar artículos!");
    	    labelBaseMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
    	    labelBaseMinijuego.setFont(new Font("Arial", Font.PLAIN, 16));
    	    panelPrincipalMinijuego.add(labelBaseMinijuego, BorderLayout.CENTER);

    	    //panelPrincipalMinijuego.revalidate();
    	    //panelPrincipalMinijuego.repaint();
    	}


    	
  
    
    
}
    
