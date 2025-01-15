package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Mejora;
import domain.Usuario;

public class GestorBD {
		//Prueba commit pull
		//Driver de la BBDD
		private static final String DRIVER = "org.sqlite.JDBC";
		//Cadena de conexión a la BBDD
		private static final String CONNECTION_STRING = "jdbc:sqlite:resources/bd/EstudianteClicker.db";
		//Conexión a la BBDD
		private Connection conn;
	
		
	/**
	 * Metodo que por el cual se conecta a la BD
	 */
	public void conectarBD() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("Exito en la conexión");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println(("Error cargando el driver de la BD: " + e.getMessage()));
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("Error concetanto con la BD: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo por el cual se desconecta de la BD
	 */
	public void desconectarBD() {
		try {
			conn.close();
			System.out.println("Exito al cerrar la BD");
		} catch (SQLException e) {
			System.out.println("Error al cerrar la BD: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que a partir de un nombre de Usuario da su numero de partida
	 * @param nombreUsuario
	 * @return devuelve su codigo de partida. Si no ha encontrado el usuario devuelve 0
	 */
	public int obtenerCodigoPartida(String nombreUsuario, String contraseña) {
		int codigo = 0;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT codPartida FROM Usuario "
				+ "WHERE nombre = ? and password = ?")) {
			stmt.setString(1, nombreUsuario);
			stmt.setString(2, contraseña);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				codigo = rs.getInt("codPartida");
			}
			
		} catch (SQLException e) {
			System.out.println("Error al obtener el codigo de partida del usuario: " + nombreUsuario + e.getMessage());
			e.printStackTrace();
		}
		return codigo;
		
	}
	
	/**
	 * Metodo que carga todos los Usuarios con su codigo de partida(clave) y el usuario(valor) en un mapa 
	 * @return Devuelve un mapa con todos los Usuarios
	 */
	public Map<Integer, Usuario> cargarUsuarios() {
		Map<Integer, Usuario> mapaUsuarios = new HashMap<Integer, Usuario>();
		//TODO seguir programando metodo 
		
		
		return mapaUsuarios;
	}
	
	/**
	 * Metodo que actualiza los puntos y creditos de un usuario (se llamara al cerrar el juego)
	 * @param u - usuario
	 */
	public void actualizarUsuario(Usuario u) {
		//TODO 
		try (PreparedStatement actualizarUsuario = conn.prepareStatement("UPDATE Usuario SET puntos = ?, creditos = ? WHERE codPartida = ?")) {
			
			actualizarUsuario.setFloat(1, u.getPuntos());
			actualizarUsuario.setInt(2, u.getCreditos());
			actualizarUsuario.setInt(3, u.getCodPartida());
			
			actualizarUsuario.executeUpdate();
			
			System.out.println("Exito al actualizar usuario: " + u.getNombre());
			
		} catch (SQLException e) {
			System.out.println("Error al actulizar Usuario: " + u.getNombre());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que devuelve una lista de mejoras a partir de el nombre de un usuario. 
	 * Primero sacar el codigo de partida del usuario y depués (a partir de ese codigo)
	 * rellenar la lisat con todas las mejoras y sus atributos
	 * @param nombreUsuario, nombre del usuario del que se quiere cargar la partida
	 * @return devuelve una lista de Mejoras
	 */
	public List<Mejora> cargarPartida(String nombreUsuario, String contraseña) {
		List<Mejora> listaMejoras = new ArrayList<Mejora>();
		int codPartida = obtenerCodigoPartida(nombreUsuario, contraseña);
		//TODO seguir programando metodo
		try (PreparedStatement cargaPartida = conn.prepareStatement("SELECT * FROM Mejora WHERE codPartida = ?")) {
			cargaPartida.setInt(1, codPartida);
			
			ResultSet rs = cargaPartida.executeQuery();
			
			while(rs.next()) {
				int numero = rs.getInt("numero");
				String nombre = rs.getString("nombre");
				double precio = rs.getDouble("precio");
				double precioI = rs.getDouble("precioInicial");
				double ganancia = rs.getDouble("ganancia");
				double gananciaI = rs.getDouble("gananciaInicial");
				double mult = rs.getDouble("multiplicador");
				double boni = rs.getDouble("bonificador");
				Mejora m = new Mejora(numero, nombre, precio, precioI, ganancia, gananciaI, mult, boni);
				listaMejoras.add(m);
				System.out.println("Mejora: "  + m.getNombre() + "cargada en la lista");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al cargar partida: " + String.valueOf(codPartida));
			e.printStackTrace();
		}
		return listaMejoras;
	}
	
	/**
	 * Metodo que carga los datos de un usuario desde la BD
	 * @param nombreUsuario
	 * @param contraseña
	 * @return devuelve un Usuario o null en caso de que no se encuentre
	 */
	public Usuario cargarUsuario(String nombreUsuario, String contraseña) {
		try(PreparedStatement cargaUsuario = conn.prepareStatement("SELECT * FROM Usuario WHERE nombre = ? and password = ?")) {
			cargaUsuario.setString(1, nombreUsuario);
			cargaUsuario.setString(2, contraseña);
			
			ResultSet rs = cargaUsuario.executeQuery();
			 while (rs.next()) {
				 int codPartida = rs.getInt("codPartida");
				 float puntos = rs.getFloat("puntos");
				 int creditos = rs.getInt("creditos");
				 

				 Usuario u = new Usuario(nombreUsuario, contraseña, codPartida, puntos, creditos);
				 return u;
			 }
			
			
			
		} catch (SQLException e) {
			System.out.println("Error al cargar usuario desde la BD");
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Metodo que guarda la partida del usuario en la BD a partir de su nombre
	 * Hacer con UPDATE Mejora 
	 * set precio = ? ...
	 * where codPartida = ? and nombre = mejora.getNombre();
	 * @param nombreUsuario
	 */
	public void guardarPartida(String nombreUsuario, String contraseña, List<Mejora> listaMejoras) {
		int codPartida = obtenerCodigoPartida(nombreUsuario, contraseña);
		if (codPartida != 0) {
			try (PreparedStatement guardarMejoras = conn.prepareStatement("UPDATE Mejora set "
					+ "numero = ? ," //1
					+ "precio = ? ," //2
					+ "ganancia = ? ," //3
					+ "multiplicador = ? ," //4
					+ "bonificador = ? " //5
					+ "WHERE codPartida = ? and nombre = ?")) { // 6 y 7 (el nombre se refiere al nombre de la Mejora)
				
			for (Mejora mejora : listaMejoras) {
				guardarMejoras.setInt(1, mejora.getNumero());
				guardarMejoras.setDouble(2, mejora.getPrecio());
				guardarMejoras.setDouble(3, mejora.getGanacia());
				guardarMejoras.setDouble(4, mejora.getMultiplicador());
				guardarMejoras.setDouble(5, mejora.getBonificador());
				guardarMejoras.setInt(6, codPartida);
				guardarMejoras.setString(7, mejora.getNombre());
				
				guardarMejoras.executeUpdate();
			}
							
			} catch (SQLException e) {
				System.out.println("Error al guardar partida");
				e.printStackTrace();
				
			}
		
		} else {
			System.out.println("Usuario o partida no encontrados");
		}
	}
	
	/**
	 * Metodo que comprueba si el el nombre del usuario con una contraseña existe ya en la bd
	 * @param nombreUsuario
	 * @param contraseña
	 * @return devuelve true si existe y false si no existe
	 */
	public boolean comprobarSiUsuarioExiste(String nombreUsuario, String contraseña) {
	//Primero comprobar que no existe ya el usuario con la misma contraseña
			try (PreparedStatement comprobarSiExiste = conn.prepareStatement("SELECT COUNT(*) FROM Usuario WHERE nombre = ? and password = ?")) {
				comprobarSiExiste.setString(1, nombreUsuario);
				comprobarSiExiste.setString(2, contraseña);
				ResultSet rs = comprobarSiExiste.executeQuery();
				
				if (rs.next() && rs.getInt(1) > 0) {
	                System.out.println("El usuario existe en la base de datos.");
	                return true;
	            } else {
	                System.out.println("El usuario NO existe en la base de datos.");
	                return false;
	            }
				
			} catch (SQLException e) {
				System.out.println("Error al comprobar si el usuario existe en la BD");
				e.printStackTrace();
			}
			return false;
	}
	
	/**
	 * Metodo que inserta un nuevo usuario en la BD, utilizando su nombre y contraseña
	 * @param nombreUsuario
	 * @param contraseña
	 */
	public void insertarNuevoUsuario(String nombreUsuario, String contraseña) { //Falla algo al insertar las mejoras, solo se inserta el lapiz
		
		if (!comprobarSiUsuarioExiste(nombreUsuario, contraseña)) {
			//Creacion de Usuario
			try (PreparedStatement insertarUsuario = conn.prepareStatement("INSERT INTO Usuario "
					+ "(nombre, password, codPartida, ultimaConexion, puntos, creditos) VALUES (?, ?, ?, ?, ?, ?)")) {
				Statement codMaximo = conn.createStatement();

	        	ResultSet rs = codMaximo.executeQuery("SELECT MAX(codPartida) as codMaximo from Usuario");
	        	int codPartida = 0;
	        	while (rs.next()) {
	        		codPartida = rs.getInt("codMaximo") + 1;
	        	}
				insertarUsuario.setString(1, nombreUsuario);
				insertarUsuario.setString(2, contraseña);
				insertarUsuario.setInt(3, codPartida);
				insertarUsuario.setLong(4, System.currentTimeMillis());
				insertarUsuario.setFloat(5, 0);
				insertarUsuario.setInt(6, 0);
				
				insertarUsuario.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Error al insertar nuevo Usuario");
				e.printStackTrace();
			}
			//Creacion de todas sus Mejoras al empezar
			//int codPartida = obtenerCodigoPartida(nombreUsuario, contraseña);
			ArrayList<Mejora> listaMejoras = crearMejoraPorDefecto();
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} // Deshabilitar el autocommit

			try {
			    for (Mejora mejora : listaMejoras) {
			        try (PreparedStatement insertarMejoras = conn.prepareStatement("INSERT INTO Mejora"
			                + "(codPartida, nombre, precio, precioInicial, numero, ganancia, gananciaInicial, multiplicador, bonificador)"
			                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			        	Statement codMaximo = conn.createStatement();

			        	ResultSet rs = codMaximo.executeQuery("SELECT MAX(codPartida) as codMaximo from Usuario");
			        	int codPartida = 0;
			        	while (rs.next()) {
			        		codPartida = rs.getInt("codMaximo");
			        	}
			        			
			            insertarMejoras.setInt(1, codPartida );
			            insertarMejoras.setString(2, mejora.getNombre());
			            insertarMejoras.setDouble(3, mejora.getPrecio());
			            insertarMejoras.setDouble(4, mejora.getPrecioInicial());
			            insertarMejoras.setInt(5, mejora.getNumero());
			            insertarMejoras.setDouble(6, mejora.getGanacia());
			            insertarMejoras.setDouble(7, mejora.getGanaciaInicial());
			            insertarMejoras.setDouble(8, mejora.getMultiplicador());
			            insertarMejoras.setDouble(9, mejora.getBonificador());

			            insertarMejoras.executeUpdate();
			        }
			    }
			    conn.commit(); // Commit de todas las operaciones
			} catch (SQLException e) {
			    try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Deshacer todos los cambios en caso de error
			    System.out.println("Error al insertar Mejoras");
			    e.printStackTrace();
			} finally {
			    try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Restaurar el autocommit
			}
			
		} else {
			System.out.println("Este usuario ya existe en la BD");
		}
		
		
	}
	
	public void borrarPartida (Usuario usuario) {
		try (PreparedStatement borrarMejoras = conn.prepareStatement("DELETE FROM Mejora WHERE codPartida = ?")) {
			
			borrarMejoras.setInt(1, usuario.getCodPartida());
			
			borrarMejoras.executeUpdate();
			
			
			try (PreparedStatement borrarUsuario = conn.prepareStatement("DELETE FROM Usuario WHERE codPartida = ?")) {
				
				borrarUsuario.setInt(1, usuario.getCodPartida());
				
				borrarUsuario.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Error al borrar usuario: " + usuario.getNombre());
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			System.out.println("Error al borrar mejoras de: " + usuario.getNombre());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Mejora> crearMejoraPorDefecto() {
		ArrayList<Mejora> lMejoras = new ArrayList<Mejora>();
		lMejoras.add(new Mejora(0,"Lapiz", 15, 15, 0.1, 0.1, 1.15, 0));
		lMejoras.add(new Mejora(0, "Cuaderno", 100, 100, 1, 1, 1.132, 0));
		lMejoras.add(new Mejora(0, "Saca-puntas", 1100, 1100, 8, 8, 1.146, 0));
		lMejoras.add(new Mejora(0, "Mesa", 12000, 12000, 47, 47, 1.158, 0));
		lMejoras.add(new Mejora(0, "Borragoma", 90000, 90000, 90, 90, 1.164, 0));
		lMejoras.add(new Mejora(0, "Libro de Matemáticas", 130000, 130000, 260, 260, 1.18, 0));
		lMejoras.add(new Mejora(0, "Profesor Particular", 1400000, 1400000, 1400, 1400, 1.192, 0));
		
		
		
		return lMejoras;
	}
	
	
}
