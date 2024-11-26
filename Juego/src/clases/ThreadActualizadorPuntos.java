package clases;

public class ThreadActualizadorPuntos implements Runnable {

	private VentanaJuego ventana;
	private boolean hiloFuncionando = true; // para controlar estado del hilo
	
	
	
	public ThreadActualizadorPuntos(VentanaJuego ventana) {
		this.ventana = ventana;
	}

	public void detener() {
        hiloFuncionando = false;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (hiloFuncionando) {
			try  {
				double ganaciasPuntos = 0;
				
				for (Mejora mejora: ventana.listaMejoras) {
					ganaciasPuntos = ganaciasPuntos + mejora.getGanacia();
				}
				ventana.puntos = ventana.puntos + ganaciasPuntos;
				
				
				javax.swing.SwingUtilities.invokeLater(() -> {
                    ventana.labelPuntos.setText("Conocimiento: " + ventana.puntos);
                    System.out.println("hilo funcionando");
                });
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Se ha interrumpido el hilo");
				break;
			}
		}
			
	}



	public boolean isHiloFuncionando() {
		return hiloFuncionando;
	}



	public void setHiloFuncionando(boolean hiloFuncionando) {
		this.hiloFuncionando = hiloFuncionando;
	}
	
	
	
	

}