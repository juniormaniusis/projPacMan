package personagem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import mapa.Passagem;

//TODO GET IMAGE (pegar a imagem adequadamente sem saber a posicao dela do vetor)

public class PacMan extends Personagem{

	//QTD DE BLOCOS POR PASSO
	public static final int SPEED = 1;

	//PONTOS COLETADOS
    private long score = 0;

    public static final ImageIcon[] IMAGE = {
	    		new ImageIcon("src/imagens/personagem/pacman/up.png"),
	    		new ImageIcon("src/imagens/personagem/pacman/down.png"),
	    		new ImageIcon("src/imagens/personagem/pacman/left.png"),
	    		new ImageIcon("src/imagens/personagem/pacman/right.png"),
	   		};

    public PacMan() {
    	super(new JLabel(IMAGE[0]), 13, 13);
    	updatePosition();
    	getJLabel().validate();
    }

    public long getScore() {
    	return this.score;
    }

	public void receivePoints() {
		this.score += 10;
	 }

	// ATUALIZA A IMAGEM CONFORME A DIREÇÃO
	@Override
	public  void updateImage(int direction) {
		switch (direction) {
		case UP:
			getJLabel().setIcon(IMAGE[0]);
			break;
		case DOWN:
			getJLabel().setIcon(IMAGE[1]);
			break;
		case RIGHT:
			getJLabel().setIcon(IMAGE[3]);
			break;
		case LEFT:
			getJLabel().setIcon(IMAGE[2]);
			break;
		default:
			break;

		}
		getJLabel().validate();
		getJLabel().repaint();

	}

	//ANDA PELO MAPA
	public void move(int direction) {

		if (!canWalk(direction)) {
			return;
		}

    	//VERIFICA SE O BLOCO JA FOI VISITADO
    	//E DÁ OS PONTOS
        if (getMapa().getForma(getPx(), getPy()) instanceof Passagem) {
        	Passagem p = (Passagem) getMapa().getForma(getPx(), getPy());
        	if(!p.wasVisited()) {
        		p.setVisited();
            	receivePoints();
        	}
        }
        super.move(direction);
	}

	//VERIFICA SE COLIDIU COM ALGUM FANTASMA
	public boolean collidedWithGhost(Ghost ghost) {
		if (getPx() == ghost.getPx() && getPy() == ghost.getPy()) {
			return true;
		}
		return false;
	}
	public boolean collidedWithGhost(Ghost[] ghosts) {
		for (Ghost ghost : ghosts) {
			if (collidedWithGhost(ghost)) {
				return true;
			}
		}
		return false;
	}

	//VERIFICA SE PEGOU A QUANTIDADE DE PONTOS PASSADA COMO PARAMETRO
	public boolean collectedAllPoints(long points) {
		if (getScore() == points) {
			return true;
		}
		return false;
	}
}
