package gui;

import domain.Mejora;

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
					ganaciasPuntos = ganaciasPuntos + mejora.getGanacia()*mejora.getNumero();
					
				}
				float puntosRedondeado = (float) (ventana.puntos + ganaciasPuntos);
				ventana.puntos = Math.round(puntosRedondeado*100)/100f;
				
				
				javax.swing.SwingUtilities.invokeLater(() -> {
                    ventana.labelPuntos.setText("Conocimiento: " + ventana.redondearPuntos(puntosRedondeado));
                    System.out.println("hilo de puntos funcionando");
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
