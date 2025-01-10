package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Mejora;
import domain.Usuario;

public class GestorBD {
	
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
		try(PreparedStatement stmt = conn.prepareStatement("SELECT codPartida FROM Usuario"
				+ "WHERE nombre = ? and password = ?")) {
			stmt.setString(1, nombreUsuario);
			stmt.setString(2, contraseña);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				codigo = rs.getInt("codPartida");
			}
			
		} catch (SQLException e) {
			System.out.println("Error al obtener el codigo de partida del usuario: " + nombreUsuario);
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
				listaMejoras.add(new Mejora(numero, nombre, precio, precioI, ganancia, gananciaI, mult, boni));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al encontrar codigo de partida: " + String.valueOf(codPartida));
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
				 
				 return new Usuario(nombreUsuario, contraseña, codPartida, puntos, creditos);
			 }
			
			
			
		} catch (SQLException e) {
			System.out.println("Error al cargar usuario desde la BD");
			e.printStackTrace();
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
	public void guardarPartida(String nombreUsuario, String contraseña, ArrayList<Mejora> listaMejoras) {
		int codPartida = obtenerCodigoPartida(nombreUsuario, contraseña);
		if (codPartida != 0) {
			try (PreparedStatement guardarMejoras = conn.prepareStatement("UPDATE Mejora set"
					+ "numero = ? ," //1
					+ "precio = ? ," //2
					+ "ganancia = ? ," //3
					+ "multiplicador = ? ," //4
					+ "bonificador = ? ," //5
					+ "WHERE codPartida = ? and nombre = ?")) { // 6 y 7 (el nombre se refiere al nombre de la Mejora)
				
			for (Mejora mejora : listaMejoras) {
				guardarMejoras.setInt(1, mejora.getNumero());
				guardarMejoras.setDouble(2, mejora.getPrecio());
				guardarMejoras.setDouble(3, mejora.getBonificador());
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
	public void insertarNuevoUsuario(String nombreUsuario, String contraseña) {
		if (!comprobarSiUsuarioExiste(nombreUsuario, contraseña)) {
			
			try (PreparedStatement insertarUsuario = conn.prepareStatement("INSERT INTO Usuario "
					+ "(nombre, password, ultimaConexion, puntos, creditos) VALUES (?, ?, ?, ?, ?)")) {
				
				insertarUsuario.setString(1, nombreUsuario);
				insertarUsuario.setString(2, contraseña);
				insertarUsuario.setLong(3, System.currentTimeMillis());
				insertarUsuario.setFloat(4, 0);
				insertarUsuario.setInt(5, 0);
				
			} catch (SQLException e) {
				System.out.println("Error al insertar nuevo Usuario");
				e.printStackTrace();
			}
		} else {
			System.out.println("Este usuario ya existe en la BD");
		}
	}
	
	
}
