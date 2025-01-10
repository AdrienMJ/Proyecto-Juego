package main;

import javax.swing.SwingUtilities;

import domain.Usuario;
import gui.VentanaJuego;

public class Main {
	public static void main(String[] args) {
		Usuario admin = new Usuario("admin", "admin", 1, 100, 5);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VentanaJuego(admin);
            }

        });
	}
}