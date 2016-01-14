import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {		
		String linia;	//String on llegim les línies del fitxer
		String[] aux;	//Array d'strings on hi guardem els diferents elements de cada línia
		boolean esSamurai = false;	//Booleà per saber si es tracta d'un sudoku o d'un sudoku samurai
		
		//Obrim el fitxer, en llegim la primera línia i agafem els diferents elements que conté amb el mètode "split"
		BufferedReader br = new BufferedReader(new FileReader(args[0]) );
		linia = br.readLine();
		aux = linia.split(" ");
		
		//Creem dues matrius, una d'enters i una de booleans, amb la mida del nombre d'elements llegits a la primera línia
		int[][] matriu = new int[aux.length][aux.length];
		boolean[][] fixes = new boolean[aux.length][aux.length];
		
		for (int i = 0; linia != null; i++) {
			for (int j = 0; j < aux.length; j++) {			//Mentre no s'acabin les línies, anem llegint i fent el "split"
				if (aux[j].charAt(0) == '-') {			// i classificant segons el que llegim
					matriu[i][j] = -1;
					fixes[i][j] = true;
				}
				else if (aux[j].charAt(0) == '*') {
					fixes[i][j] = true;
					esSamurai = true;
				}
				else {
					fixes[i][j] = false;
					matriu[i][j] = Integer.parseInt(aux[j]);
					
				}
			}
			linia = br.readLine();
			if (linia != null) aux = linia.split(" ");
		}
		br.close();
		
		//Obtenim el mètode de sortida i el nom del fitxer dels arguments de l'execució
		int sortida = Integer.parseInt(args[1]);
		String fitxer = new String("");
		if (sortida == 3) fitxer = args[2];
		
		//EXTRA
		//Com que no teniem clar si INDEPENDENTMENT del mètode de sortida de la solució havíem de mostrar com 
		//el programa resolia el sudoku, ho hem preguntat i adéu problemes
		System.out.println ("Vols veure el procediment de resolució? (S/N)");

		Scanner sc = new Scanner(System.in);
		String resposta = sc.next();
		sc.close();
		
		//Obtenim la data d'inici de resolució del sudoku i la mostrem
		Data data1 = new Data (new GregorianCalendar() );
		System.out.println (data1.toString() );
		
		if (!esSamurai) {
			//Si no és samurai, sabem que el nombre d'elements per casella/el nombre de caselles ve donat
			//per l'arrel quadrada del nombre d'elements a una fila/columna
			//Entenem per casella el recuadre que engloba diversos elementes i que formen la totalitat del sudoku
			Sudoku sudoku = new Sudoku (matriu, fixes, (int) Math.sqrt(aux.length));
			
			Marcatge marca = new Marcatge(sudoku);

			if (resposta.charAt(0) == 'S' || resposta.charAt(0) == 's') {
				//Si hem de mostrar el procediment de resolució, hem d'inicalitzar la GUI
				SudokuGUI gui = new SudokuGUI ("Sudoku", 0, 0, sudoku.getFixes() );
				sudoku.resolSudoku(0, 0, sortida, fitxer, gui, marca);
				gui.updateBoard(sudoku.getMatriu() );
			}
			else sudoku.resolSudoku (0, 0, sortida, fitxer, marca);
		}
		else {
			//Obtenim el nombre d'elements per casella del samurai
			//El que fem es basa en anar augmentant "cas" fins que aquesta compleixi que és igual 
			//al nombre d'elements per línia del fitxer del samurai
			int cas;
			for (cas = 1; aux.length != cas * cas * 3 - 2 * cas; cas++);

			//En aquest sudoku hi guardem el que és tot el conjunt dels cinc sudokus
			Sudoku sudoku = new Sudoku(matriu, fixes, cas);
			//Obtenim el tipus samurai a partir del sudoku gegant
			Samurai samurai = sudoku.setSamurai(cas);
			
			Marcatge[] marca = new Marcatge[5];
			
			for(int i=0;i<5;i++) marca[i]= Marcatge.creaMarcatge(samurai.getSudoku(i));
						
			if (resposta.charAt(0) == 'S' || resposta.charAt(0) == 's') {
				//Mostrem la variable sudoku, que recordem és la visió global del samurai
				SudokuGUI gui = new SudokuGUI("Sudoku", 0, 0, sudoku.getFixes() );
				gui.updateBoard(sudoku.getMatriu() );
				
				//Cridem a la seva resolució gràfica
				samurai.resolSamurai(0, 0, gui, 0, sortida, fitxer, marca);
				gui.updateBoard(sudoku.getMatriu () );
			}
			else {
				//Cridem la seva resolució sense mostrar-la per pantalla
				samurai.resolSamurai(0, 0, 0, sortida, fitxer, marca);
			}
		}
		
		//Obtenim la data en el moment que hem acabat de comprovar tots els casos que podiem
		//i en mostrem la diferència amb el primer moment
		Data data2 = new Data(new GregorianCalendar() );
		System.out.println (data2.toString() );
		System.out.println ("Han pasat: " + data1.transcorregut(data2) );
		
		//Mostrem el nombre de solucions que ha tingut el sudoku escollit
		if (esSamurai) {
			if (Samurai.getSolucions() == 1)
				System.out.println ("El Sudoku Samurai ha tingut una única solució.");
			else 
				System.out.println ("El Sudoku Samurai ha tingut " + Samurai.getSolucions() + " solucions.");
		}
		else {
			if (Sudoku.getSolucions() == 1) 
				System.out.println ("El sudoku ha tingut una única solució.");
			else 
				System.out.println ("El Sudoku ha tingut " + Sudoku.getSolucions() + " solucions.");
		}
		
	}

}
