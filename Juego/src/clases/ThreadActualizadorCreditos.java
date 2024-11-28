package clases;

public class ThreadActualizadorCreditos implements Runnable {
	
	
	private VentanaJuego ventana;
	private boolean actualizandoCreditos;
	

	

	public ThreadActualizadorCreditos(VentanaJuego ventana) {
		this.ventana = ventana;
	}
	
	
	public void detener() {
		actualizandoCreditos = false;
	}


	@Override
	public void run() {
		
		while(actualizandoCreditos) {
			
			try {
				
			
                Thread.sleep(100);
			} catch (InterruptedException  e) {
				System.out.println("Se ha interrumpido el hilo");
				break;
			}
			
			
		}
		
	}

}
