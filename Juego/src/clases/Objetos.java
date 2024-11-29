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




	public ArrayList<Objetos> getListaObjetos() {
		return listaObjetos;
	}

	public void setListaObjetos(ArrayList<Objetos> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	public Objetos(String nombreObjeto, String descrip, int costeCreditos, JButton botonObjeto) {
		this.nombreObjeto = nombreObjeto;
		this.descrip = descrip;
		this.costeCreditos = costeCreditos;
		this.botonObjeto = botonObjeto;
		
	}

	ArrayList<Objetos> listaObjetos = new ArrayList<Objetos>();
    
    
    public Objetos() {
    	//Super Boli:
    	JButton botonSuperBoli = new JButton("HOLA");
    	Objetos superBoli = new Objetos("Super Bolígrafo","AAA",123, botonSuperBoli);
    	listaObjetos.add(superBoli);
    }
	

}
