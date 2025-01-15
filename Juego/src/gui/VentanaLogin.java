package gui;

import java.awt.BorderLayout;
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
	public JButton inicioSesion;
	public JButton registrarse;
	public JButton jugar;
	public JButton borrarPartida;
	
	public VentanaLogin() {
		
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login");
		setLocationRelativeTo(null);
		
		//Componentes
		JLabel labelBienvenida = new JLabel("<html>   ¡Hola, Estudiante! <br>   ¡Introduce tu usuario y contraseña, por favor! <br>   O regístrate si aún no lo has hecho </html>");
		labelBienvenida.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		labelUsuario = new JLabel("  Usuario:");
		labelUsuario.setFont(new Font("Arial" , Font.BOLD ,  18));
		labelContraseña = new JLabel("  Contraseña:");
		labelContraseña.setFont(new Font("Arial" , Font.BOLD ,  18));
		usuario = new JTextField();
		contraseña = new JPasswordField();
		inicioSesion = new JButton("Iniciar Sesion");
		registrarse = new JButton("Registrarse");
		jugar = new JButton("Jugar");
		borrarPartida = new JButton("Borrar Partida");
			
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(new GridLayout(4,2,5,5));
		panelCampos.add(labelUsuario);
		panelCampos.add(usuario);
		panelCampos.add(labelContraseña);
		panelCampos.add(contraseña);
		panelCampos.add(inicioSesion);
		panelCampos.add(registrarse);
		panelCampos.add(jugar);
		panelCampos.add(borrarPartida);
		
		setVisible(true);
		
		this.add(labelBienvenida, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
				
		
	}
	
	
	
	public JTextField getUsuario() {
		return usuario;
	}



	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}



	public JPasswordField getContraseña() {
		return contraseña;
	}



	public void setContraseña(JPasswordField contraseña) {
		this.contraseña = contraseña;
	}



	public static void main(String[] args) {
		new IniciadorPartida();
	}

}
