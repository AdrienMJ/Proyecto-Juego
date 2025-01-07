package domain;
//TODO - Implementar en la clase mejoras dos métodos (uno para el precio de las mejoras y otro para las ganacias)
//Precio(n) = Precio inicial x Multiplicardor**n
//Produccion(n) = Produccion inicial x n x (1 + Bonificadores)
//Siendo los bonificadores otro apartado en las mejoras

public class Mejora  { // Esta clase hace todo el proceso de compra de un material
	
	private String nombre;
	private double precio;
	private double precioInicial;
	private int numero;	
	private double ganancia;
	private double gananciaInicial;
	private double multiplicador;
	private double bonificador;
	
	
	
	/**Crea un objeto Mejora, que es el propio material como: lapiz,libro,cuaderno....
	 * Se puede saber y cambiar su nombre, precio y ganancia(cuanto multiplica)
	 * 
	 * @param nombre Nombre del material escolar
	 * @param precio Precio del material escolar
	 * @param ganancia cuanto gana en el "nivel 1"
	 * @param multiplicador Cuanto multiplica el precio anterior
	 */
	public Mejora(String nombre, double precioInicial, double gananciaInicial, double multiplicador ) {
		super();
		this.nombre = nombre;
		this.precioInicial = precioInicial;
		this.gananciaInicial = gananciaInicial;
		this.numero = 0; //cantidad de materiales
		this.multiplicador = multiplicador; // El incremento de precio
		this.bonificador = 0;
		this.precio = precioInicial;
		this.ganancia = 0;
		
	}

	// Comprar algo
	//Precio(n) = Precio inicial x Multiplicardor**n
	//Ganancia(n) = Ganancia inicial x n x (1 + Bonificadores)
	
	 /**
	  * compra 1 unidad de la mejora
	  * se actualiza el numero de mejoras, el precio y sus ganacias
	  * @param puntos puntos actuales del juego
	  * @return devuelve los puntos que quedan una vez comprada la mejora, si no se tienen los suficientes puntos devuelve los puntos actuales
	  * 
	  */
	 public double comprarMejora(double puntos) {
		if (puntos >= precio) { // Verifica si se puede comprar
			double puntosRestantes = puntos - precio ;
			numero++;           // Incrementa el numero que hay de esa mejora
			precio = Math.round(precioInicial*(Math.pow(multiplicador, numero))); //Actualiza el precio
			ganancia = gananciaInicial*numero*(1+bonificador);
			
			puntosRestantes = Math.round(puntosRestantes * 100.0) / 100.0;

	        return puntosRestantes;
			
		} else { //Si no se puede comprar devuelve los puntos actuales

			return Math.round(puntos * 100.0) / 100.0;
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



	public double getGanacia() {
		return ganancia;
	}



	public void setGanacia(double ganacia) {
		this.ganancia = ganacia;
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

	public double getBonificador() {
		return bonificador;
	}

	public void setBonificador(double bonificador) {
		this.bonificador = bonificador;
	}
	
	

	
	
	
}
