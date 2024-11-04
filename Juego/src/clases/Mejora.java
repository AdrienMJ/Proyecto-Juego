package clases;
//TODO - Implementar en la clase mejoras dos métodos (uno para el precio de las mejoras y otro para las ganacias)
//Precio(n) = Precio inicial x Multiplicardor**n
//Produccion(n) = Produccion inicial x n x (1 + Bonificadores)
//Siendo los bonificadores otro apartado en las mejoras

public class Mejora extends Conocimiento { // Esta clase hace todo el proceso de compra de un material
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private double precio;
	private double precioInicial;
	private int numero;	
	private double ganancias;
	private double gananciaInicial;
	private double multiplicador;
	
	
	
	/**Crea un objeto Mejora, que es el propio material como: lapiz,libro,cuaderno....
	 * Se puede saber y cambiar su nombre, precio y ganancia(cuanto multiplica)
	 * 
	 * @param nombre Nombre del material escolar
	 * @param precio Precio del material escolar
	 * @param ganacias Cuanto multiplica a las ganancias totales
	 */
	public Mejora(String nombre, double precioInicial, double gananciaInicial, int numero, double multiplicador ) {
		super();
		this.nombre = nombre;
		this.precioInicial = precioInicial;
		this.gananciaInicial = gananciaInicial;
		this.numero = numero; //cantidad de materiales
		this.multiplicador = multiplicador; // El incremento de precio
	}

	// Comprar algo
    public void comprar() {
        if (conocimientoTotal >= precio) {
            conocimientoTotal -= precio;
            numero++;
            precio *= multiplicador; // Aumenta el precio para la próxima compra
            calcularConocimientoPorSegundo(); // Actualiza los créditos por segundo
        } else {
            System.out.println("No tienes suficientes créditos para comprar este material.");
        }
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
		return ganancias;
	}



	public void setGanacias(double ganacias) {
		this.ganancias = ganacias;
	}



	public String getNombre() {
		return nombre;
	}



	public double getPrecioInicial() {
		return precioInicial;
	}



	public double getGanaciaInicial() {
		return gananciaInicial;
	}



	public double getMultiplicador() {
		//precio = precio * 1.2 // Coge el precio de el material en cuestión y lo multiplica en base al multiplicador que hay en el contructor de ese material
		return multiplicador;
	}
	

	
	
	
}
