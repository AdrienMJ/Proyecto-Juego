package clases;


import javax.swing.JRadioButton;

public class Objeto  {
	private String nombreObjeto;
	private String descrip;
	private int costeCreditos;
	private JRadioButton botonObjeto;
	

    

	public String getNombreObjeto() {
        return nombreObjeto;
    }

    public String getDescrip() {
        return descrip;
    }

    public int getCosteCreditos() {
        return costeCreditos;
    }

   
    public JRadioButton getBotonObjeto() {
		return botonObjeto;
	}





	public Objeto(String nombreObjeto, String descrip, int costeCreditos, JRadioButton botonObjeto) {
		this.nombreObjeto = nombreObjeto;
		this.descrip = descrip;
		this.costeCreditos = costeCreditos;
		this.botonObjeto = botonObjeto;
		
	}
	
}
