package domain;

public class Usuario {
	
	private String nombre;
	private String password;
	private int codPartida;
	private String ultimaConexion;
	
	public Usuario(String nombre, String password, int codPartida, String ultimaConexion) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.codPartida = codPartida;
		this.ultimaConexion = ultimaConexion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCodPartida() {
		return codPartida;
	}

	public String getUltimaConexion() {
		return ultimaConexion;
	}
	
	
	
	
}
