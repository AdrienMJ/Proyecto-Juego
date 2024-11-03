package clases;
//TODO - Implementar en la clase mejoras dos métodos (uno para el precio de las mejoras y otro para las ganacias)
//Precio(n) = Precio inicial x Multiplicardor**n
//Produccion(n) = Produccion inicial x n x (1 + Bonificadores)
//Siendo los bonificadores otro apartado en las mejoras

public class Mejora {
	private String nombre;
	private double precio;
	private double precioInicial;
	private int numero;	
	private double ganacias;
	private double ganaciaInicial;
	private double multiplicador;
	
	
	
	/**Crea un objeto Mejora, que es el propio material como: lapiz,libro,cuaderno....
	 * Se puede saber y cambiar su nombre, precio y ganancia
	 * 
	 * @param nombre Nombre del material escolar
	 * @param precio Precio del material escolar
	 * @param ganacias Cuanto multiplica a las ganacias totales
	 */
	public Mejora(String nombre, double precioInicial, double ganaciaInicial, double multiplicador ) {
		super();
		this.nombre = nombre;
		this.precioInicial = precioInicial;
		this.precio = precioInicial;
		this.ganaciaInicial = ganaciaInicial;
		this.numero = 0;
		this.multiplicador = multiplicador;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public double getGanacias() {
		return ganacias;
	}



	public void setGanacias(double ganacias) {
		this.ganacias = ganacias;
	}



	public String getNombre() {
		return nombre;
	}



	public double getPrecioInicial() {
		return precioInicial;
	}



	public double getGanaciaInicial() {
		return ganaciaInicial;
	}



	public double getMultiplicador() {
		return multiplicador;
	}
	
	//Precio(n) = Precio inicial x Multiplicardor**n
	//Produccion(n) = Produccion inicial x n x (1 + Bonificadores)
	/**
	 * Actualiza el numero del item en 1 unidad, el precio del material y las ganacias de ese material
	 */
	public void comprarMejora() {
		numero = numero++;
		precio = precioInicial*multiplicador*numero;
		ganacias = ganaciaInicial*numero;
	}
	
	
}
