package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaAnuncioMiniJuego extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JLabel textoFijoVentanaMinijuego; //referencia al texto que se mostrará en la ventana de aviso de minijuego
    private JLabel textoCambianteVentanaMinijuego; //referencia al texto que se mostrará en la ventana de aviso de minijuego, cambiando dependiendo de cual sea
    
    public VentanaAnuncioMiniJuego() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("¡Minijuego disponible!");
        setSize(500, 280);
        setVisible(true);
        setLocationRelativeTo(null);

        JPanel panelVentanaMinijuego = new JPanel();
        panelVentanaMinijuego.setBackground(Color.CYAN);
        panelVentanaMinijuego.setLayout(new BorderLayout());

        textoFijoVentanaMinijuego = new JLabel();
        textoFijoVentanaMinijuego.setText("¡Has desbloqueado un minijuego por la compra de este artículo!");
        textoFijoVentanaMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoFijoVentanaMinijuego.setVerticalAlignment(SwingConstants.CENTER);
        panelVentanaMinijuego.add(textoFijoVentanaMinijuego, BorderLayout.NORTH);
        
        textoCambianteVentanaMinijuego = new JLabel(); //se crea el texto de la ventana de aviso de minijuego que cambiará dependiendo de cual se trata
        //textoVentanaMinijuego.setText("aa");
        textoCambianteVentanaMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoCambianteVentanaMinijuego.setVerticalAlignment(SwingConstants.CENTER);
        panelVentanaMinijuego.add(textoCambianteVentanaMinijuego, BorderLayout.CENTER);

        add(panelVentanaMinijuego);

    }

    public void actualizarMensaje(String mensaje) {
        textoCambianteVentanaMinijuego.setText(mensaje); //Actualiza el texto de la ventana con el mensaje recibido
    }
}
