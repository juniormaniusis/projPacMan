package personagem;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ghost extends Personagem {
    

    public static ImageIcon[] IMAGE = {
    	new ImageIcon("src/imagens/personagem/fantasmas/redGhost.png"),
    	new ImageIcon("src/imagens/personagem/fantasmas/pinkGhost.png"),
    	new ImageIcon("src/imagens/personagem/fantasmas/orangeGhost.png"),
    	new ImageIcon("src/imagens/personagem/fantasmas/blueGhost.png")
    };

	private static Random rand = new Random();


    public Ghost( int px, int py) {
    	//GERA UMA SKIN ALEATORIA PARA O FANTASMA
		super(new JLabel(sortIcon()), px, py);
    	updatePosition();
    	getJLabel().validate();
    	
	}



    public void move(int prefDirection) { //encontra um movimento possivel e aleatorio para o fantasma
        
    	boolean andou = false;
       
        while (!andou) {
            int dir = rand.nextInt(4)+1;

            if (dir == 1 && canWalk(Personagem.RIGHT)) {
            	super.move(Personagem.RIGHT);
                andou = true;
            }
            else if (dir == 2 && canWalk(Personagem.LEFT)) {
            	super.move(Personagem.LEFT);
                andou = true;
            }
            else if (dir == 3 && canWalk(Personagem.DOWN)) {
            	super.move(Personagem.DOWN);
                andou = true;
            }
            else if (dir == 4 && canWalk(Personagem.UP)) {
            	super.move(Personagem.UP);
                andou = true;
            }else if (canWalk(prefDirection)) {
            	super.move(prefDirection);
                andou = true;
            }
        }
    }

	private static ImageIcon sortIcon() {
		return IMAGE[rand.nextInt(4)];
	}
    

	@Override
	public void updateImage(int direction) {
		getJLabel().validate();
		getJLabel().repaint();
	}
    
}