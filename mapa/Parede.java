package mapa;

import javax.swing.ImageIcon;


public class Parede extends MapaForma{
	
	//ATRIBUTOS
		//ESTATICOS
	public static final ImageIcon IMAGE = new ImageIcon("src/imagens/cenario/parede.png");
	//COSNTRUTOR
	public Parede(int x, int y) {
		super(Parede.IMAGE, true,  x,  y);
		
	}

	@Override
	public String toString() {
		return "x";
	}

}
