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
	public int obtenerCodigoPartida(String nombreUsuario) {
		int codigo = 0;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT codPartida FROM Usuario"
				+ "WHERE nombre = ?")) {
			stmt.setString(1, nombreUsuario);
			
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
	 * Metodo que carga todos los Usuarios con su nombre(clave) y contraseña(valor) en un mapa 
	 * @return Devuelve un mapa con todos los Usuarios
	 */
	public Map<String, String> cargarUsuarios() {
		Map<String, String> mapaUsuarios = new HashMap<String, String>();
		//TODO seguir programando metodo 
		
		
		return mapaUsuarios;
	}
	
	/**
	 * Metodo que devuelve una lista de mejoras a partir de el nombre de un usuario. 
	 * Primero sacar el codigo de partida del usuario y depués (a partir de ese codigo)
	 * rellenar la lisat con todas las mejoras y sus atributos
	 * @param nombreUsuario, nombre del usuario del que se quiere cargar la partida
	 * @return devuelve una lista de Mejoras
	 */
	public List<Mejora> cargarPartida(String nombreUsuario) {
		List<Mejora> listaMejoras = new ArrayList<Mejora>();
		int codPartida = obtenerCodigoPartida(nombreUsuario);
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
	 * Metodo que guarda la partida del usuario en la BD a partir de su nombre
	 * Hacer con UPDATE Mejora 
	 * set precio = ? ...
	 * where codPartida = ? and nombre = mejora.getNombre();
	 * @param nombreUsuario
	 */
	public void guardarPartida(String nombreUsuario, ArrayList<Mejora> listaMejoras) {
		int codPartida = obtenerCodigoPartida(nombreUsuario);
		if (codPartida != 0) {
			try (PreparedStatement guardarMejoras = conn.prepareStatement("UPDATE Mejora set"
					+ "numero = ? ," //1
					+ "precio = ? ," //2
					+ "ganancia = ? ," //3
					+ "multiplicador = ? ," //4
					+ "bonificador = ? ," //5
					+ "WHERE codPartida = ? and nombre = ?")) { // 6 y 7
				
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
	
	
}
