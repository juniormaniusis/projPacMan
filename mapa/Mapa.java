package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

//TODO ESCOLHER O MAPA QUE VAI LER
public class Mapa {
	private static Mapa uniqueInstance;
	//SIGNIFICA QUE O MAPA É UMA MATRIZ DIMENSION X DIMENSION
	public static final int DIMENSION = 15;

	//TAMANHO QUE UM BLOCO OCUPARÁ NA TELA
	public static final int SIZE_OF_BLOCK = 32;

	private static int numOfPassagens;
	private static int numOfParedes;

	//O MAPA É UMA MATRIZ DE ELEMENTOS DE MAPA (PASSAGENS, PAREDES, ETC)
	private MapaForma[][] mapa = new MapaForma[Mapa.DIMENSION][Mapa.DIMENSION] ;

	//RETORNA A UNICA INSTANCIA DO MAPA
	public static Mapa getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Mapa();
		}
		return uniqueInstance;
	}


	//CONTRUTOR
	private Mapa() {
		this.mapa = Mapa.readFromFile(this.carregarArquivoMapa("src/MAPS/"));
	}

	//LE O MAPA A PARTIR DE UM ARQUIVO
	public static MapaForma[][] readFromFile(String fileName){
		MapaForma[][] map = new MapaForma[DIMENSION][DIMENSION];

		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			for(int i = 0; i < DIMENSION; i++) {
				String linha = br.readLine();
				for (int j = 0; j < DIMENSION; j++) {
					if (linha.charAt(j) == 'o') {
						map[j][i] = new Passagem(j, i);
						increaseNumOfPassagens();

					}
					if (linha.charAt(j) == 'x') {
						map[j][i] = new Parede(j, i);
						increaseNumOfParedes();
					}
				}
			}

		}catch(Exception e) {
			System.out.println("Erro ao ler o arquivo de mapa");
			System.out.println(e);
		}

		return map;
	}

	//IMPRIME O MAPA NO CONSOLE (PARA AFINS DE DEBUG)
	public void printMap() {
		for (int j = 0; j < DIMENSION; j++) {
			for (int i = 0; i < DIMENSION; i++) {
				System.out.print(getForma(i, j) + " ");
			}
			System.out.println();
		}
	}

	//RETORNA UM ELEMENTO DE MAPA NA POSICAO (X, Y)
	public MapaForma getForma(int x, int y) {
		return mapa[x][y];
	}

	private static void increaseNumOfParedes() {
		Mapa.numOfParedes = getNumOfParedes() + 1;
	}
	public static int getNumOfParedes() {
		return Mapa.numOfParedes;
	}


	private static void increaseNumOfPassagens() {
		Mapa.numOfPassagens = getNumOfPassagens() + 1;
	}


	public static int getNumOfPassagens() {
		return Mapa.numOfPassagens;
	}

	private String carregarArquivoMapa(String dir) {
		File diretorio = new File(dir);
		String[] arquivos = diretorio.list();
		String mensagem = "Digite o número correspondente ao mapa que deseja carregar: \n";
		for (int i = 0; i < arquivos.length; i++) {
			mensagem += "#"+i+" "+arquivos[i]+"\n";
		}
		int escolhaCorrespondente;
		do{
			escolhaCorrespondente = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
		}while(escolhaCorrespondente < 0 || escolhaCorrespondente > arquivos.length);
		return dir+arquivos[escolhaCorrespondente];
	}
}
