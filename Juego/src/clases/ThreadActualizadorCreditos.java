package clases;

import domain.Mejora;

public class ThreadActualizadorCreditos implements Runnable {
	
	
	private VentanaJuego ventana;
	private boolean actualizandoCreditos = true;
	

	

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
						
				double ganaciasPuntos = 0;
				
				for (Mejora mejora: ventana.listaMejoras) {
					ganaciasPuntos = ganaciasPuntos + mejora.getGanacia()/100;
				}
				float puntosRedondeado = (float) (ventana.puntosBarra + ganaciasPuntos);
				ventana.puntosBarra = Math.round(puntosRedondeado*100)/100f;
				ventana.barraCreditos.setValue((int) ventana.puntosBarra);
				
				if (ventana.puntosBarra >= ventana.barraCreditos.getMaximum()) {  // Asegurarse de que el valor esté dentro del rango
                	ventana.puntosBarra = 0;
                	ventana.maxCreditos *= 1.5;
                	ventana.barraCreditos.setValue((int)ventana.puntosBarra);
                	ventana.barraCreditos.setMaximum(ventana.maxCreditos);;
                	ventana.creditos++;
                	ventana.labelCreditos.setText("Créditos: " + ventana.creditos);
                	
        		}
				
				javax.swing.SwingUtilities.invokeLater(() -> {
					ventana.labelCreditosTienda.setText(String.format("Tienes para gastar: %d Créditos", ventana.creditos));
                    System.out.println("hilo  de creditos funcionando");
                });
				
				Thread.sleep(100);
				
			} catch (InterruptedException  e) {
				System.out.println("Se ha interrumpido el hilo");
				break;
			}
			
			
		}
		
	}

}
