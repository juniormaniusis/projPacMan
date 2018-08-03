package jogo;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mapa.Mapa;
import personagem.Ghost;
import personagem.PacMan;
import personagem.Personagem;

public class Jogo extends JFrame implements KeyListener{

	//tamanho do mapa * tamanho dos blocos = tamanho da tela
	public static final int SIZE_OF_SCREEN_X = (Mapa.DIMENSION ) * Mapa.SIZE_OF_BLOCK ;
	public static final int SIZE_OF_SCREEN_Y = (Mapa.DIMENSION + 1) * Mapa.SIZE_OF_BLOCK ;

	private Mapa mapa;
	private PacMan pacman;
	private Ghost[] ghosts;
	private Personagem[] personagens;

	private long scoreMax;

	//CONTRUTOR
	public Jogo() {
		//carrega o mapa
		this.mapa = Mapa.getInstance();
		//define o maximo de pontos que se pode obter
		this.scoreMax = Mapa.getNumOfPassagens() * 10;

		//pergunta pro usuario o numero de fantasmas
		int numberOfGhosts = defineNumberOfGhosts();


		personagens = new Personagem[numberOfGhosts + 1];

		pacman  = new PacMan();
		personagens[0] = pacman;


		ghosts = new Ghost[numberOfGhosts];

		for (int i = 1; i < personagens.length; i++) {
			personagens[i] = new Ghost(3, 3);
			ghosts[i-1] = (Ghost) personagens[i];
		}

	}

	public void start() {
		setTitle(" P A C - M A N ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension size = new Dimension(SIZE_OF_SCREEN_X, SIZE_OF_SCREEN_Y);
		setLocationRelativeTo(null);
		setMinimumSize(size);
		setSize(size);
		setResizable(false);


		for (int i = 0; i < personagens.length; i++) {
			add(personagens[i].getJLabel());
		}

		carregarMapa();

		addKeyListener(this);

		setVisible(true);

	}

	//ADICIONA TODAS AS JLABELS NO JFRAME
	private void carregarMapa() {
		for(int i = 0; i < Mapa.DIMENSION; i++) {
			for(int j = 0; j < Mapa.DIMENSION; j++) {
				add(mapa.getForma(i, j).getLblForma());
				mapa.getForma(i, j).getLblForma().repaint();
				mapa.getForma(i, j).getLblForma().validate();
			}
		}

	}

	//ATUALIZA O PLACAR NA BARRA DE TITULO
	private void refreshWindow() {
		setTitle("["+ personagens[0].getPx() + "," + personagens[0].getPy() + "] -  "
				+ "score: " + ((PacMan)personagens[0]).getScore() + " points");
	}

	//verifica se o jogo acabou
	private boolean endGame() {
		if (pacman.collectedAllPoints(scoreMax) || pacman.collidedWithGhost(ghosts)) {
			return true;
		}
		return false;
	}

	//SOLICITA AO USUARIO A QUANTIDADE DE FANTASMAS
	public static int defineNumberOfGhosts() {
		int numOfGhosts;
		do{
			numOfGhosts = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de fantasmas: "));
		}while(numOfGhosts < 0);
		return numOfGhosts;
	}
	public long getScoreMax() {
		return this.scoreMax;
	}
	//METODOS SOBRESCRITOS RELACIONADOS AO KEYLISTENER
	@Override
	public void keyPressed(KeyEvent key) {
		int direcao = key.getKeyCode();

		personagens[0].move(direcao);
		//verifica se há colisão antes dos fantasmas se movimentarem. Nesse caso, o pac meio q anda primeiro q eles
		if(!endGame()) {
			for (int i = 1; i < personagens.length; i++) {
				personagens[i].move(direcao);
			}
		}
		if(endGame()){
			JOptionPane.showMessageDialog(null, "O jogo acabou", "FIM DE JOGO", 1);
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "PONTUAÇÃO: "+pacman.getScore()+"/"+getScoreMax(), "FIM DE JOGO", 1);
			System.exit(0);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
