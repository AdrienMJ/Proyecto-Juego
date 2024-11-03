package clases;

public class Conocimiento extends Materiales{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int conocimientoTotal;
	public double conocimientoPorSegundo;

	// Calcula créditos por segundo en base a las mejoras
    public void calcularConocimientoPorSegundo() {
    	
        conocimientoPorSegundo = (mapaCantidades.get("Lapiz") * 0.1) + (mapaCantidades.get("Cuaderno") * 1) + (mapaCantidades.get("Borragoma") * 8) + (mapaCantidades.get("Sacapuntas") * 47);
    }
    
    // Genera los créditos por segundo en base a las mejoras
    public void generarConocimiento(double segundos) {
    	
        conocimientoTotal += conocimientoPorSegundo * segundos;
    }
}
