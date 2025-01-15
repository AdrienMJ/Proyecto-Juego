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
		ventanaLogin.borrarPartida.setEnabled(true);
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
					System.out.println(usuario.getCodPartida() +": "+ usuario.getNombre());
					JOptionPane.showMessageDialog(ventanaLogin, "<html> Bienvenido/a de nuevo, " + usuario.getNombre() + "<br> ¡Disfruta de la partida! <html/>", "Partida encontrada", JOptionPane.DEFAULT_OPTION);
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
					System.out.println(usuario);
					ventanaLogin.jugar.setEnabled(true);
					JOptionPane.showMessageDialog(ventanaLogin, "Usuario registrado  ¡Disfruta del juego!","Usuario registrado con exito" , JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ventanaLogin, "Este Usuario ya existe", "Usuario ya existente", JOptionPane.INFORMATION_MESSAGE);
				}
				gestorBD.desconectarBD();
			
			}
		});
		
		ventanaLogin.jugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ventanaLogin.dispose();
				
			}
		});
		
		ventanaLogin.borrarPartida.addActionListener(new ActionListener() {
			
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
					System.out.println(usuario.getCodPartida() +": "+ usuario.getNombre());
					
					int respuesta = JOptionPane.showConfirmDialog(ventanaLogin, "<html> Seguro/a que quiere borrar tu partida, " + usuario.getNombre() + " ? <br> No podrá recuperarla si la borra <html/>", "Borrar Partida", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						int respuesta2 = JOptionPane.showConfirmDialog(ventanaLogin, "<html> Recuerde que no podrá recuperar su partida, " + usuario.getNombre() + " <br> Es el último aviso <html/>", "Estás completamnete seguro/a ?", JOptionPane.YES_NO_OPTION);
						if (respuesta2 == JOptionPane.YES_OPTION) {

							int respuesta3 = JOptionPane.showConfirmDialog(ventanaLogin, "<html> Venga " + usuario.getNombre() + " piénsatelo una vez más porfa <br> ¿Tan malo es el juego? <html/>", "Va porfa no borres", JOptionPane.YES_NO_OPTION);
							if (respuesta3 == JOptionPane.YES_OPTION) {
								int respuesta4 = JOptionPane.showConfirmDialog(ventanaLogin, "<html> Bueno ya te dejo borrar tu partida " + usuario.getNombre() + " <br> Nos veremos pronto..... <br> Me acordaré de esta", "Esta es la última", JOptionPane.YES_NO_OPTION);
								if (respuesta4 == JOptionPane.YES_OPTION) {
									gestorBD.conectarBD();
									gestorBD.borrarPartida(usuario);
									gestorBD.desconectarBD();
									JOptionPane.showMessageDialog(ventanaLogin, "<html> Lo has conseguido<br> Felicidades <br> Espero que estés feliz <html/>","Partida Borrada" , JOptionPane.WARNING_MESSAGE);
									
								}
							}
						}
					}
					
				}
				
				System.out.println(nombreUsuario);
				System.out.println(contraseña);
			}
		});
		
	
		
	}
	
	
}
