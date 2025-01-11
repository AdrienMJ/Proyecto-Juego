package domain;

public class Usuario {
	
	private String nombre;
	private String password;
	private int codPartida;
	private long ultimaConexion;
	private float puntos;
	private int creditos;
	
	public Usuario(String nombre, String password, int codPartida, float puntos, int creditos) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.codPartida = codPartida;
		this.ultimaConexion = System.currentTimeMillis();
		this.puntos = puntos;
		this.creditos = creditos;
	}
	public Usuario() {
		
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

	public long getUltimaConexion() {
		return ultimaConexion;
	}

	public void setUltimaConexion(long ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}

	public float getPuntos() {
		return puntos;
	}

	public void setPuntos(float puntos) {
		this.puntos = puntos;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public void setCodPartida(int codPartida) {
		this.codPartida = codPartida;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
}
