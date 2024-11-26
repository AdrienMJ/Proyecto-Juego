package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel labelUsuario;
	private JTextField usuario;
	private JLabel labelContraseña;
	private JPasswordField contraseña;
	private JButton inicioSesion;
	private JButton registrarse;
	
	public VentanaLogin() {
		
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login");
		setLocationRelativeTo(null);
		
		//Componentes
		JLabel labelBienvenida = new JLabel("<html>Hola! Introduce tu usuario y contraseña porfavor! <br> O registrate si aún no lo has hecho!</html>");
		labelUsuario = new JLabel("  Usuario:");
		labelUsuario.setFont(new Font("Arial" , Font.BOLD ,  18));
		labelContraseña = new JLabel("  Contraseña:");
		labelContraseña.setFont(new Font("Arial" , Font.BOLD ,  18));
		usuario = new JTextField();
		contraseña = new JPasswordField();
		inicioSesion = new JButton("Iniciar Sesion");
		registrarse = new JButton("Registrarse");
			
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(new GridLayout(3,2,3,10));
		panelCampos.add(labelUsuario);
		panelCampos.add(usuario);
		panelCampos.add(labelContraseña);
		panelCampos.add(contraseña);
		panelCampos.add(inicioSesion);
		panelCampos.add(registrarse);
		
		setVisible(true);
		
		this.add(labelBienvenida, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		new VentanaLogin();
	}

}
