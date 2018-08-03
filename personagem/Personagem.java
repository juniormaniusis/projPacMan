package personagem;

import javax.swing.JLabel;

import mapa.Mapa;

public abstract class Personagem {

	public static final int UP = 38;
	public static final int LEFT = 37;
	public static final int DOWN = 40;
	public static final int RIGHT = 39;

	private int speed = 1;

	private Mapa mapa;

	private JLabel lblPersonagem;

	private int px;
	private int py;

	//CONSTRUTOR
	public Personagem(JLabel lblPersonagem, int px, int py) {
		this.lblPersonagem = lblPersonagem;
		this.px = px;
		this.py = py;
		this.mapa = Mapa.getInstance();
		lblPersonagem.setBounds(px, py, Mapa.SIZE_OF_BLOCK, Mapa.SIZE_OF_BLOCK);
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return this.speed;
	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public JLabel getJLabel() {
		return this.lblPersonagem;
	}



	//VERIFICA SE O PACMAN PODE SE MOVIMENTAR EM DETERMINADA DIREÇÃO
    public boolean canWalk(int direction) {
    	switch (direction) {
		case UP:
			return !(mapa.getForma(getPx(), getPy() - 1).isSolid());
		case DOWN:
			return !(mapa.getForma(getPx(), getPy() + 1).isSolid());
		case RIGHT:
			return !(mapa.getForma(getPx() + 1, getPy()).isSolid());
		case LEFT:
			return !(mapa.getForma(getPx() - 1, getPy()).isSolid());
		default:
			break;
		}
    	return true;
    }


	public void move(int direction) {

		// MOVE O PACMAN EM DETERMINADA DIREÇÃO
		if (direction == DOWN) {
			this.py = getPy() + getSpeed();
		} else if (direction == UP) {
			this.py = getPy() - getSpeed();
		} else if (direction == LEFT) {
			this.px = getPx() - getSpeed();
		} else if (direction == RIGHT) {
			this.px = getPx() + getSpeed();
		}
		// ATUALIZA A IMAGEM E A POSIÇÃO NA TELA
		updatePosition();
		updateImage(direction);

	}

  //ATUALIZA A POSIÇÃO CONFOME OS VALORES ATUAIS DE px e py
	public void updatePosition() {
    	getJLabel().setBounds(this.px * Mapa.SIZE_OF_BLOCK, this.py * Mapa.SIZE_OF_BLOCK,
    			Mapa.SIZE_OF_BLOCK, Mapa.SIZE_OF_BLOCK);
    	getJLabel().setVisible(true);
    	getJLabel().repaint();
    }

	//METODOS ABSTRATO
	public abstract void updateImage(int direction);
}
