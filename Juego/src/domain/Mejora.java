package domain;

//TODO - Implementar en la clase mejoras dos métodos (uno para el precio de las mejoras y otro para las ganancias)
//Precio(n) = Precio inicial x Multiplicador**n
//Produccion(n) = Produccion inicial x n x (1 + Bonificadores)
//Siendo los bonificadores otro apartado en las mejoras

public class Mejora { // Esta clase hace todo el proceso de compra de un material

    private int numero;
    private String nombre;
    private double precio;
    private double precioInicial;
    private double ganancia;
    private double gananciaInicial;
    private double multiplicador;
    private double bonificador;

    /** Crea un objeto Mejora, que es el propio material como: lapiz,libro,cuaderno....
     * Se puede saber y cambiar su nombre, precio y ganancia(cuanto multiplica)
     *
     * @param nombre Nombre del material escolar
     * @param precio Precio del material escolar
     * @param ganancia cuanto gana en el "nivel 1"
     * @param multiplicador Cuanto multiplica el precio anterior
     */
    public Mejora(int numero, String nombre, double precio, double precioInicial, double ganancia,
                  double gananciaInicial, double multiplicador, double bonificador) {
        this.numero = numero;
        this.nombre = nombre;
        this.precio = precio;
        this.precioInicial = precioInicial;
        this.ganancia = ganancia;
        this.gananciaInicial = gananciaInicial;
        this.multiplicador = multiplicador;
        this.bonificador = bonificador;
    }

    // Calcular el precio basado en el número actual de mejoras
    private double calcularPrecio(int nivel) {
        if (nivel == 0) { // Caso base: nivel inicial
            return precioInicial;
        }
        return multiplicador * calcularPrecio(nivel - 1); // Recursión
    }

    // Calcular la ganancia acumulada basada en el número de mejoras
    private double calcularGanancia(int nivel) {
        if (nivel == 0) { // Caso base: sin mejoras
            return 0;
        }
        double gananciaNivel = gananciaInicial * nivel * (1 + bonificador);
        return gananciaNivel + calcularGanancia(nivel - 1); // Recursividad
    }

    /**
     * Compra 1 unidad de la mejora
     * Se actualiza el número de mejoras, el precio y sus ganancias
     * @param puntos puntos actuales del juego
     * @return devuelve los puntos que quedan una vez comprada la mejora, si no se tienen los suficientes puntos devuelve los puntos actuales
     */
    public double comprarMejora(double puntos) {
        if (puntos >= precio) { // Verifica si se puede comprar
            double puntosRestantes = puntos - precio;
            numero++; // Incrementa el número de mejoras
            precio = Math.round(calcularPrecio(numero)); // Actualiza el precio
            ganancia = gananciaInicial * (1 + bonificador);

            puntosRestantes = Math.round(puntosRestantes * 100.0) / 100.0;
            return puntosRestantes;

        } else { // Si no se puede comprar, devuelve los puntos actuales
            return Math.round(puntos * 100.0) / 100.0;
        }
    }

    // Obtener el precio
    public double getPrecioRecursivo() {
        return calcularPrecio(numero);
    }

    // Obtener la ganancia total 
    public double getGananciaTotalRecursiva() {
        return calcularGanancia(numero);
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

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public double getGananciaInicial() {
        return gananciaInicial;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public double getBonificador() {
        return bonificador;
    }

    public void setBonificador(double bonificador) {
        this.bonificador = bonificador;
    }
    
    
}
