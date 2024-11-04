package main;

import javax.swing.SwingUtilities;

import clases.VentanaJuego;

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
