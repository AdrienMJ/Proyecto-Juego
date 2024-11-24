package clases;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel labelUsuario;
	private JTextField usuario;
	private JLabel labelcontraseña;
	private JPasswordField contraseña;
	
	public VentanaLogin() {
		
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login");
		setVisible(true);
		setLocationRelativeTo(null);
		
		
	}
	
	public static void main(String[] args) {
		new VentanaLogin();
	}

}
