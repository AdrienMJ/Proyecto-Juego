package clases;

import java.util.ArrayList;

import javax.swing.JButton;

public class Objetos  {
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




	public Objetos(String nombreObjeto, String descrip, int costeCreditos, JButton botonObjeto) {
		super();
		this.nombreObjeto = nombreObjeto;
		this.descrip = descrip;
		this.costeCreditos = costeCreditos;
		this.botonObjeto = botonObjeto;
		
	}

	ArrayList<Objetos> listaObjetos = new ArrayList<Objetos>();
    
    
    public Objetos() {
    	JButton botonSuperBoli = new JButton("HOLA");
    	Objetos superBoli = new Objetos("Super Bolígrafo","AAA",123, botonSuperBoli);
    	listaObjetos.add(superBoli);
    }
	

}
