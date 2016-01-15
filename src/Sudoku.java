import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Classe de Sudoku. Està formada per una matriu d'enter, una matriu de booleans, el nombre de caselles,
//i el nombre de solucions del sudoku.
//És la classe amb la que més treballarem per la resolució de l'enunciat.

public class Sudoku {
	
	//Atributs
	
	private int[][] matriu;			//Matriu on hi guardem el valor de cada element del sudoku
	private boolean[][] fixes;		//Matriu on hi guardem si podem modificar un element del sudoku
	private int cas;				//Enter on hi guardem el nombre de caselles del sudoku
									//Cal notar que el nombre de caselles per sudoku és el mateix que 
									//el nombre d'elements per casella, és a dir, entenem per casella
									//cada recuadre que engloba un quadrat del sudoku
	private static int solucions;	//Enter on hi guardem el nombre de solucions del sudoku
	
	//Constructors
	
	public Sudoku(int cas) {
		//Constructor amb el paràmetre de nombre de caselles del sudoku
		super();
		matriu = new int[cas * cas][cas * cas];		//Òbviament el sudoku té una mida del quadrat del
		fixes = new boolean[cas * cas][cas * cas];	//nombre d'elements per casella
		this.cas = cas;
		solucions = 0;
	}

	public Sudoku(int[][] matriu, boolean[][] fixes, int cas) {
		//Constructor que rep les dues matrius i el nombre d'elements per casella com a paràmetres
		super();
		this.matriu = matriu;
		this.fixes = fixes;
		this.cas = cas;
		solucions = 0;
	}
	
	//Setters i getters
	
	public int[][] getMatriu() {
		return matriu;
	}
	
	public void setMatriu(int[][] matriu) {
		this.matriu = matriu;
	}

	public boolean[][] getFixes() {
		return fixes;
	}

	public void setFixes(boolean[][] fixes) {
		this.fixes = fixes;
	}
	
	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}
	
	public static int getSolucions(){
		return solucions;
	}
	
	public void setSolucions(int solucions){
		Sudoku.solucions = solucions;
	}
	
	//Mètodes
	
	public void printaSudoku() {
		//Procediment que s'encarrega de pintar a la consola el sudoku que el crida
		int indy = 0, indx;
		for (int i = 0; i < cas * cas; i++) {
			while (indy + cas <= i) indy += cas;
			if (indy == i) {
				indx = 0;
				for (int j = 0; j < cas * cas; j++) {
					while (indx + cas <= j) indx += cas;
					if (j != 0) System.out.print (" ");
					if (j == indx) System.out.print ("+  ");
					System.out.print ("- ");
				}
				System.out.println (" +");
			}
			indx = 0;
			for (int j = 0; j < cas * cas; j++) {
				while (indx + cas <= j) indx += cas;
				if (indx == j && indx != 0) System.out.print (" | ");
				else if (indx == j) System.out.print ("| ");
				if (this.matriu[i][j] > 9) System.out.print (this.matriu[i][j] + " ");
				if (this.matriu[i][j] < 10) System.out.print (" " + this.matriu[i][j] + " ");
			}
			System.out.println (" |");
		}
		indx = 0;
		for (int j = 0; j < cas * cas; j++) {
			while (indx + cas <= j) indx += cas;
			if (j != 0) System.out.print (" ");
			if (j == indx) System.out.print ("+  ");
			System.out.print ("- ");
		}
		System.out.println (" +");
	}
	
	public void passaAFitxer(String fitxer) {
		//Procediment que s'encarrega d'escriure en un fitxer el sudoku que el cridi segons
		//l'estàndard que es defineix a l'enunciat
	    try {
			PrintWriter printw = new PrintWriter(new FileWriter(fitxer));
			for (int i = 0; i < cas * cas; i++) {
				for (int j = 0; j < cas * cas; j++) {
					if (this.matriu[i][j] == - 1) printw.print ("- ");
					else printw.print (this.matriu[i][j] + " ");
				}
				printw.println();
			}
			printw.close();
		} catch (IOException e) {
			System.out.println ("Ts, jefe! Això no tira!");
		}
	    
	}
	
	public void seguentGerma(int i, int j, int num, Marcatge marca){
		while(num<=cas*cas&&(marca.getFiles()[i][num-1]||marca.getColumnes()[j][num-1]||
				marca.getCasella()[(i/cas)*cas+(j/cas)][num-1])) num++;
	}
	
	public void marcatge(int i, int j, int x, Marcatge marca){
		marca.getFiles()[i][x-1]=true;
		marca.getColumnes()[j][x-1]=true;
		marca.getCasella()[(i/cas)*cas+(j/cas)][x-1]=true;
	}
	
	public void desmarcatge(int i, int j, int x, Marcatge marca){
		marca.getFiles()[i][x - 1] = false;
		marca.getColumnes()[j][x - 1] = false;
		marca.getCasella()[(i/cas)*cas+(j/cas)][x-1]=false;
	}
	
	public void resolSudoku(int i, int j, int sortida, String fitxer, SudokuGUI gui, Marcatge marca) {
		//Aquest procediment fa exactament el mateix que el procediment explicat anteriorment
		//amb la diferencia que aquest mostra l'estat de resolució del sudoku a mesura que avança
		int num = 1;
		while (num <= cas * cas) {
			if (fixes[i][j]){
				while(num<=cas*cas&&(marca.getFiles()[i][num-1]||marca.getColumnes()[j][num-1]
						||marca.getCasella()[(i/cas)*cas+(j/cas)][num-1])) num++;
				if(num==cas*cas+1){
					matriu[i][j] = - 1;
					return;
				}
				matriu[i][j] = num;
				marcatge(i, j, num, marca);
			}
			if (i == cas * cas - 1 && j == cas * cas - 1) {		//SOLUCIÓ
					gui.updateBoard(this.matriu);
					if (sortida == 1) {
						this.printaSudoku();
					}
					else if (sortida == 2) {
						SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, fixes);
						guis.updateBoard(matriu);
					}
					else if (sortida == 3) {
						this.passaAFitxer(fitxer);
					}
					Sudoku.solucions++;
			}
			else {		//NO SOLUCIÓ
				if (j == cas * cas - 1) {
					gui.updateBoard(this.matriu);	//Actualitzem l'interfície quan tenim una
													//línia resolta
					this.resolSudoku(i + 1, 0, sortida, fitxer, gui, marca);
				}
				else {
					this.resolSudoku(i, j + 1, sortida, fitxer, gui, marca);
				}
			}
			if (fixes[i][j]){
				desmarcatge(i, j, num, marca);
				num++;
			}
			else num = cas * cas + 1;
		}
		if (fixes[i][j]) matriu[i][j] = - 1;
	}
	
	public void resolSudoku(int i, int j, int sortida, String fitxer, Marcatge marca) {
		//Aquest procediment fa exactament el mateix que el procediment explicat anteriorment
		//amb la diferencia que aquest mostra l'estat de resolució del sudoku a mesura que avança
		int num = 1;
		while (num <= cas * cas) {
			if (fixes[i][j]){
				while(num<=cas*cas&&(marca.getFiles()[i][num-1]||marca.getColumnes()[j][num-1]
						||marca.getCasella()[(i/cas)*cas+(j/cas)][num-1])) num++;
				if(num==cas*cas+1){
					matriu[i][j] = - 1;
					return;
				}
				matriu[i][j] = num;
				marcatge(i, j, num, marca);
			}
			if (i == cas * cas - 1 && j == cas * cas - 1) {		//SOLUCIÓ
					if (sortida == 1) {
						this.printaSudoku();
					}
					else if (sortida == 2) {
						SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, fixes);
						guis.updateBoard(matriu);
					}
					else if (sortida == 3) {
						this.passaAFitxer(fitxer);
					}
					Sudoku.solucions++;
			}
			else {		//NO SOLUCIÓ
				if (j == cas * cas - 1) {
					this.resolSudoku(i + 1, 0, sortida, fitxer, marca);
				}
				else {
					this.resolSudoku(i, j + 1, sortida, fitxer, marca);
				}
			}
			if (fixes[i][j]){
				desmarcatge(i, j, num, marca);
				num++;
			}
			else num = cas * cas + 1;
		}
		if (fixes[i][j]) matriu[i][j] = - 1;
	}
	
	public Samurai setSamurai(int cas) {
		//Procediment que s'encarrega d'extreure d'un sudoku gran que conté un samurai a 
		//cinc sudokus petits que es guardaran a un objecte de la classe Samurai
		
		Samurai samurai = new Samurai(cas);
		samurai.getSudoku(0).setCas(cas);
		samurai.getSudoku(1).setCas(cas);
		samurai.getSudoku(2).setCas(cas);
		samurai.getSudoku(3).setCas(cas);
		samurai.getSudoku(4).setCas(cas);
		//Set del sudoku central
		for (int i = cas * cas - cas, y = 0; i < 2 * cas * cas - cas; i++, y++) {
			for (int j = cas * cas - cas, x = 0; j < 2 * cas * cas - cas; j++,x++) {
				samurai.getSudoku(0).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(0).getFixes()[y][x] = fixes[i][j];
			}
		}
		//Set del sudoku superior esquerre
		//Fem que la casella d'abaix a la dreta tingui els elements fixes
		for (int i = 0; i < cas * cas; i++) {
			for (int j = 0; j < cas * cas; j++) {
				samurai.getSudoku(1).getMatriu()[i][j] = matriu[i][j];
				samurai.getSudoku(1).getFixes()[i][j] = fixes[i][j];
			}
		}
		for (int i = cas * cas - cas; i < cas * cas; i++) {
			for (int j = cas * cas - cas; j < cas * cas; j++) {
				samurai.getSudoku(1).getFixes()[i][j] = false;
			}
		}
		//Set del sudoku superior dret
		//Fem que la casella d'abaix a l'esquerra tingui els elements fixes
		for (int i = 0, y = 0; i < cas * cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 3 * cas * cas - 2 * cas; j++, x++) {
				samurai.getSudoku(2).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(2).getFixes()[y][x] = fixes[i][j];
			}
		}
		for (int i = cas * cas - cas, y = cas * cas - cas; i < cas * cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 2 * cas * (cas - 1) + cas; j++, x++) {
				samurai.getSudoku(2).getFixes()[y][x] = false;
			}
		}
		
		//Set del sudoku inferior esquerre
		//Fem que la casella de dalt a la dreta tingui els elements fixes
		for (int i = 2 * cas * (cas - 1), y = 0; i < 3 * cas * cas - 2 * cas; i++, y++) {
			for (int j = 0, x = 0; j < cas * cas; j++, x++) {
				samurai.getSudoku(3).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(3).getFixes()[y][x] = fixes[i][j];
			}
		}
		for (int i = 2 * cas * (cas - 1), y = 0; i < 2 * cas * (cas - 1) + cas; i++, y++) {
			for (int j = cas * cas - cas, x = cas * cas - cas; j < cas * cas; j++, x++) {
				samurai.getSudoku(3).getFixes()[y][x] = false;
			}
		}
		
		//Set del sudoku inferior dret
		//Fem que la casella de dalt a l'esquerra tingui els elements fixes
		for (int i = 2 * cas * (cas - 1), y = 0; i < 3 * cas * cas - 2 * cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 3 * cas * cas - 2 * cas; j++, x++) {
				samurai.getSudoku(4).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(4).getFixes()[y][x] = fixes[i][j];
			}
		}
		for (int i = 2 * cas * (cas - 1), y = 0; i < 2 * cas * (cas - 1) + cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 2 * cas * (cas - 1) + cas; j++, x++) {
				samurai.getSudoku(4).getFixes()[y][x] = false;
			}
		}
		
		return samurai;
	}
	
	public static void printaFixes(boolean[][] a){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(a[i][j]) System.out.print("T ");
				else System.out.print("F ");
			}
			System.out.println();
		}
	}
	
}