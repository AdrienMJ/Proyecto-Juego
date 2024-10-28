package clases;

public class Mejora {
	private String nombre;
	private float precio;
	private float ganacias;
	private int numero;
	
	/**Crea un objeto Mejora, que es el propio material como: lapiz,libro,cuaderno....
	 * Se puede saber y cambiar su nombre, precio y ganancia(cuanto multiplica)
	 * 
	 * @param nombre Nombre del material escolar
	 * @param precio Precio del material escolar
	 * @param ganacias Cuanto multiplica a las ganacias totales
	 */
	public Mejora(String nombre, float precio, float ganacias) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.ganacias = ganacias;
		this.numero = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getGanacias() {
		return ganacias;
	}

	public void setGanacias(float ganacias) {
		this.ganacias = ganacias;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Actualiza la cantidad de "Edificios" que tiene una mejora
	 * @param cantidad numero que se suma a la cantidad actual
	 */
	public void actualizarCantidad(int cantidad) {
		this.setNumero(this.getNumero() + cantidad);
		
	}
}
