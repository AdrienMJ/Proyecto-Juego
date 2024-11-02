package main;

import javax.swing.SwingUtilities;

import clases.VentanaJuego;
import tema3.tema3A.EjemploJButton;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VentanaJuego();
            }

        });
	}
}
