package clases;

import java.util.ArrayList;

import javax.swing.JButton;

public class Objeto  {
	private String nombreObjeto;
	private String descrip;
	private int costeCreditos;
	private JButton botonObjeto;
	

    

	public String getNombreObjeto() {
        return nombreObjeto;
    }

    public String getDescrip() {
        return descrip;
    }

    public int getCosteCreditos() {
        return costeCreditos;
    }

   
    public JButton getBotonObjeto() {
		return botonObjeto;
	}





	public Objeto(String nombreObjeto, String descrip, int costeCreditos, JButton botonObjeto) {
		this.nombreObjeto = nombreObjeto;
		this.descrip = descrip;
		this.costeCreditos = costeCreditos;
		this.botonObjeto = botonObjeto;
		
	}
	
}
