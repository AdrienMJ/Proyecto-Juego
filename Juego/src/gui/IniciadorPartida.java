package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import db.GestorBD;
import domain.Usuario;

public class IniciadorPartida {
	
	private VentanaLogin ventanaLogin;
	private String nombreUsuario;
	private String contraseña;
	private Usuario usuario;
	private GestorBD gestorBD = new GestorBD();
	
	
	public IniciadorPartida() {

		ventanaLogin = new VentanaLogin();
		ventanaLogin.jugar.setEnabled(false);
		ventanaLogin.inicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nombreUsuario = ventanaLogin.getUsuario().getText();
				char[] arrayContraseña = ventanaLogin.getContraseña().getPassword();
				contraseña = new String(arrayContraseña);
				
				gestorBD.conectarBD();
				usuario = gestorBD.cargarUsuario(nombreUsuario, contraseña);
				gestorBD.desconectarBD();
				if (usuario == null) {
					JOptionPane.showMessageDialog(ventanaLogin, "Usuario o contraseña incorrectas", "Usuario no encontrado", JOptionPane.WARNING_MESSAGE);
				} else {
					ventanaLogin.jugar.setEnabled(true);
				}
				
				System.out.println(nombreUsuario);
				System.out.println(contraseña);
				
			}
		});
		
		ventanaLogin.registrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nombreUsuario = ventanaLogin.getUsuario().getText();
				char[] arrayContraseña = ventanaLogin.getContraseña().getPassword();
				contraseña = new String(arrayContraseña);
				System.out.println(nombreUsuario);
				System.out.println(contraseña);
				
				gestorBD.conectarBD();
				if (!gestorBD.comprobarSiUsuarioExiste(nombreUsuario, contraseña)) {
					gestorBD.insertarNuevoUsuario(nombreUsuario, contraseña);
					usuario = gestorBD.cargarUsuario(nombreUsuario, contraseña);
					ventanaLogin.jugar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(ventanaLogin, "Este Usuario ya existe", "Usuario ya existente", JOptionPane.WARNING_MESSAGE);
				}
				gestorBD.desconectarBD();
			
			}
		});
		
		ventanaLogin.jugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaJuego ventanaJuego = new VentanaJuego(usuario);
				ventanaLogin.dispose();
				
			}
		});
		
	}
	
	
}
