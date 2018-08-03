package mapa;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class MapaForma {
	//ATRIBUTOS

	private boolean solid;   //se o bloco tem colisão ou não
	private JLabel lblForma;

	//CONTRUTOR
	public MapaForma(ImageIcon image, boolean solid, int x, int y) {
		this.solid = solid;
		this.lblForma = new JLabel(image);
		lblForma.setBounds(x * Mapa.SIZE_OF_BLOCK, y * Mapa.SIZE_OF_BLOCK,
					Mapa.SIZE_OF_BLOCK , Mapa.SIZE_OF_BLOCK );
	}

	//GETTERS

	//RETORNA SE O ELEMENTO É SÓLIDO OU NÃO
	public boolean isSolid() {
		return this.solid;
	}

	//RETORNA A JLabel DO BLOCO CORRESPONDENTE
	public JLabel getLblForma() {
		return this.lblForma;
	}
	//ATUALIZA A IMAGEM
	public void setImagem(ImageIcon imagem) {
		this.lblForma.setIcon(imagem);
		this.lblForma.repaint();
		this.lblForma.validate();
	}
	public abstract String toString();


}
