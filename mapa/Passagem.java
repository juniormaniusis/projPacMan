package mapa;

import javax.swing.ImageIcon;

public class Passagem extends MapaForma{
	//ATRIBUTOS
	public static final ImageIcon[] IMAGE = {
			new ImageIcon("src/imagens/cenario/passagem.png"),
			new ImageIcon("src/imagens/cenario/passagemVisitada.png")
		};
	private boolean visited = false;

	//CONTRUTOR
	public Passagem(int x, int y) {
		super(Passagem.IMAGE[1], false, x, y);
	}
	
	//RETORNA SE JA FOI VISITADO OU NÃO
	public boolean wasVisited() {
		return this.visited;
	}
	
	//DIZ QUE O BLOCO FOI VISITADO
	public void setVisited() {
		//POR PADRÃO NUNCA FORAM VISITADOS
		//ENTÃO ELE SÓ PODE SER VISITADO
		visited = true;
		setImagem(IMAGE[0]);
	}

	@Override
	public String toString() {
		if(wasVisited()) 
			return "W";
	 return "o";
	}
	
	
	
}
