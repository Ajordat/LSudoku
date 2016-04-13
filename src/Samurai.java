import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Classe que utilitzem per resoldre els sudokus samurai. Consta d'un array de cinc sudokus, 
//el nombre de caselles (que és el mateix que el nombre d'elements per casella) i el nombre de solucions

public class Samurai {
	
	//Atributs
	
	private Sudoku[] samurai;		//Array de sudokus. Sempre serà de cinc elements
	private static int cas;			//Nombre d'elements per casella
	private static int solucions;	//Nombre de solucions del sudoku samurai
	
	//Constructors
	
	public Samurai(int cas) {
		//Constructor que rep com a paràmetre el nombre de caselles que tindrà cada sudoku
		super();
		samurai = new Sudoku[5];
		for(int i=0;i<5;i++){
			samurai[i] = new Sudoku(cas);
		}
		Samurai.cas=cas;
		solucions=0;
	}

	//Setters i getters
	
	public Sudoku[] getSamurai() {
		return samurai;
	}

	public void setSamurai(Sudoku[] samurai) {
		this.samurai = samurai;
	}
	
	public static int getSolucions() {
		return solucions;
	}
	
	public void setSolucions(int solucions) {
		Samurai.solucions = solucions;
	}
	
	public Sudoku getSudoku(int pos) {
		return samurai[pos];
	}
	
	//Mètodes
	
	public void printaSamurai() {
		//Procediment que s'encarrega de dibuixar al terminal un samurai 
		Sudoku sudoku = this.setSudoku();
		int indx, indy = 0;
		int quad = cas * cas;
		for (int i = 0; i < 3 * quad - 2 * cas; i++) {
			indy=(i/cas)*cas;
			if (indy == i) {
				for (int j = 0; j < 3 * quad - 2 * cas; j++) {
					indx=(j/cas)*cas;
					if (j != 0) System.out.print (" ");
					if (j == indx) System.out.print ("+  ");
					if (j >= quad && j < 2 * quad - 2 * cas && (i < quad - cas || i > 2 * quad - cas) ) 
						System.out.print ("  ");
					else System.out.print ("- ");
				}
				System.out.println (" +");
			}
			for (int j = 0; j < 3 * quad - 2 * cas; j++) {
				indx=(j/cas)*cas;
				if (indx == j && indx != 0) {
					if (i >= quad && i < 2 * quad - 2 * cas && (j < quad - cas || j > 2 * quad - cas) ) 
						System.out.print ("   ");
					else System.out.print (" | ");
				}
				else if (indx == j) {
					if (i >= quad && i < 2 * quad - 2 * cas) System.out.print ("  ");
					else System.out.print ("| ");
				}
				if (sudoku.getMatriu()[i][j] == 0) System.out.print ("   ");
				else if (sudoku.getMatriu()[i][j] > 9) 
					System.out.print (sudoku.getMatriu()[i][j] + " ");
				else if (sudoku.getMatriu()[i][j] == - 1) System.out.print (" - ");
				else System.out.print (" " + sudoku.getMatriu()[i][j] + " ");
			}
			if (i >= quad && i < 2 * quad - 2 * cas) System.out.println();
			else System.out.println (" |");
		}
		for (int j = 0; j < 3 * quad - 2 * cas; j++) {
			indx=(j/cas)*cas;
			if (j != 0) System.out.print (" ");
			if (j == indx) System.out.print ("+  ");
			if (j >= quad && j < 2 * quad - 2 * cas) System.out.print ("  ");
			else System.out.print ("- ");
		}
		System.out.println (" +");
	}
	
	public void passaAFitxer(String fitxer) {
		//Procediment que s'encarrega d'escriure en un fitxer el samurai segons el format que 
		//s'especifica a l'enunciat
	    try {
	    	
	    	Sudoku sudoku = this.setSudoku();
			PrintWriter printw = new PrintWriter(new FileWriter(fitxer) );
			for (int i = 0; i < 3 * cas * cas - 2 * cas; i++) {
				for (int j = 0; j < 3 * cas * cas - 2 * cas; j++) {
					if (sudoku.getMatriu()[i][j] == 0) printw.print ("* ");
					else if (sudoku.getMatriu()[i][j] == - 1) printw.print ("- ");
					else printw.print (sudoku.getMatriu()[i][j] + " ");
				}
				printw.println();
			}
			printw.close();
		} catch (IOException e) {
			System.out.println ("Ts, jefe! Això no tira!");
		}
	    
	}
	
	public Sudoku setSudoku(){
		//Funció que retorna un sudoku "gegant" a partir del samurai. S'utilitza per poder fer ús de la GUI
		Sudoku sudoku = new Sudoku(cas * (3 * cas - 2) );
		
		for (int i = 0; i < sudoku.getMatriu().length; i++) {
			for (int j = 0; j < sudoku.getMatriu().length; j++) {
				sudoku.getMatriu()[i][j] = 0;
				sudoku.getFixes()[i][j] = false;
			}
		}		
		//Set del sudoku superior dret
		for(int i = 0, y = 0; i < cas * cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 3 * cas * cas - 2 * cas; j++, x++) {
				sudoku.getMatriu()[i][j] = this.getSudoku(2).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(2).getFixes()[y][x];
			}
		}
		//Set del sudoku inferior esquerre
		for (int i = 2 * cas * (cas - 1), y = 0; i < 3 * cas * cas - 2 * cas; i++, y++) {
			for (int j = 0, x = 0; j < cas * cas; j++, x++) {
				sudoku.getMatriu()[i][j] = this.getSudoku(3).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(3).getFixes()[y][x];
			}
		}
		//Set del sudoku inferior dret
		for (int i = 2 * cas * (cas - 1), y = 0; i < 3 * cas * cas - 2 * cas; i++, y++) {
			for (int j = 2 * cas * (cas - 1), x = 0; j < 3 * cas * cas - 2 * cas; j++, x++) {
				sudoku.getMatriu()[i][j] = this.getSudoku(4).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(4).getFixes()[y][x];
			}
		}
		//Set del sudoku superior esquerre
		for (int i = 0; i < cas * cas; i++) {
			for(int j = 0;j < cas * cas; j++){
				sudoku.getMatriu()[i][j] = this.getSudoku(1).getMatriu()[i][j];
				sudoku.getFixes()[i][j] = this.getSudoku(1).getFixes()[i][j];
			}
		}
		//Set del sudoku central
		for(int i = cas * cas - cas, y = 0; i < 2 * cas * cas - cas; i++, y++) {
			for (int j = cas * cas - cas, x = 0; j < 2 * cas * cas - cas; j++, x++) {
				sudoku.getMatriu()[i][j] = this.getSudoku(0).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(0).getFixes()[y][x];
			}
		}
		return sudoku;
	}
	
	public int seguentGerma(int i , int j, int num, int mat, Marcatge[] marca){
		int indy = 0;	//en l'altre el valor nou (cal a dir que les caselles compartides són fixes per tots els sudokus excepte
		int indx = 0;	//el central).
		int y, x;
		indy=(i/cas)*cas;
		indx=(j/cas)*cas;
		if(indy==0 && indx==0){
			y=cas * cas - cas + i;
			x=cas * cas - cas + j;
			while(num<=cas*cas&&(marca[0].getFiles()[i][num-1]||marca[0].getColumnes()[j][num-1]
				||marca[1].getFiles()[y][num-1]
				||marca[1].getColumnes()[x][num-1]||marca[1].getCasella()[(y/cas)*cas+(x/cas)][num-1])) 
				num++;
			if(num != cas*cas+1){
				marca[1].getFiles()[y][num-1]=true;
				marca[1].getColumnes()[x][num-1]=true;
				marca[1].getCasella()[(y/cas)*cas+(x/cas)][num-1]=true;
				samurai[1].getMatriu()[y][x] = num;
			}
		}
		else if (indy == 0 && indx == cas * cas - cas){
			y=cas * cas - cas + i;
			x=j - cas * cas + cas;
			while(num<=cas*cas&&(marca[0].getFiles()[i][num-1]||marca[0].getColumnes()[j][num-1]
				||marca[2].getFiles()[y][num-1]
				||marca[2].getColumnes()[x][num-1]||marca[2].getCasella()[(y/cas)*cas+(x/cas)][num-1]))
				num++;
			
			if(num != cas*cas+1){
				marca[2].getFiles()[y][num-1]=true;
				marca[2].getColumnes()[x][num-1]=true;
				marca[2].getCasella()[(y/cas)*cas+(x/cas)][num-1]=true;
				samurai[2].getMatriu()[y][x] = num;
			}
		}
		else if (indy == cas * cas - cas && indx == 0){
			y = cas - cas * cas + i;
			x = cas * cas - cas + j;
			while(num<=cas*cas&&(marca[0].getFiles()[i][num-1]||marca[0].getColumnes()[j][num-1]
				||marca[3].getFiles()[y][num-1]
				||marca[3].getColumnes()[x][num-1]||marca[3].getCasella()[(y/cas)*cas+(x/cas)][num-1]))
				num++;
			if(num != cas*cas+1){
				marca[3].getFiles()[y][num-1]=true;
				marca[3].getColumnes()[x][num-1]=true;
				marca[3].getCasella()[(y/cas)*cas+(x/cas)][num-1]=true;
				samurai[3].getMatriu()[y][x] = num;
			}
		}else if (indy == cas * cas - cas && indx == cas * cas - cas){
			y = cas - cas * cas + i;
			x = j - cas * cas + cas;
			while(num<=cas*cas&&(marca[0].getFiles()[i][num-1]||marca[0].getColumnes()[j][num-1]
				||marca[4].getFiles()[y][num-1]
				||marca[4].getColumnes()[x][num-1]||marca[4].getCasella()[(y/cas)*cas+(x/cas)][num-1]))
				num++;
			if(num != cas*cas+1){
				marca[4].getFiles()[y][num-1]=true;
				marca[4].getColumnes()[x][num-1]=true;
				marca[4].getCasella()[(y/cas)*cas+(x/cas)][num-1]=true;
				samurai[4].getMatriu()[y][x] = num;
			}
		}
		else{
			while(num<=cas*cas&&(marca[mat].getFiles()[i][num-1]||marca[mat].getColumnes()[j][num-1]
					||marca[mat].getCasella()[(i/cas)*cas+(j/cas)][num-1])) 
				num++;
		}
		return num;
	}
	
	public void marcatge(int i, int j, int num, int mat, Marcatge[] marca){

		marca[mat].getFiles()[i][num-1] = true;
		marca[mat].getColumnes()[j][num-1] = true;
		marca[mat].getCasella()[(i/cas)*cas+(j/cas)][num-1]=true;
	}
	
	public void desmarcatge(int i, int j, int num, int mat, Marcatge[] marca){

		marca[mat].getFiles()[i][num-1] = false;
		marca[mat].getColumnes()[j][num-1] = false;
		marca[mat].getCasella()[(i/cas)*cas+(j/cas)][num-1]=false;
	}
	
	public void resolSamurai(int i, int j, int mat, int sortida, String fitxer, Marcatge[] marca) {
		//Crida recursiva que resol un sudoku. El que fem és començar per la posició [0][0] del sudoku
		//central i ens anem desplaçant per la fila en la que ens trobem, un cop hem acabat una fila, 
		//anem al principi de la següent i així fins que arribem a l'últim element d'un dels sudokus
		//que és el [cas*cas-1][cas*cas-1] (del sudoku individual). Un cop hem acabat amb un sudoku
		//passem al següent en el següent ordre: central -> superior esquerre -> superior dret ->
		//inferior esquerre -> inferior dret
		int num = 1;
		while (num <= cas * cas) {	//Mentre no haguem provat totes les combinacions de l'element
			if (samurai[mat].getFixes()[i][j]) {	//Si la posició no és fixa, en modifiquem el valor
				
				if (mat != 0) while(num<=cas*cas&&(marca[mat].getFiles()[i][num-1]
					||marca[mat].getColumnes()[j][num-1]
					||marca[mat].getCasella()[(i/cas)*cas+(j/cas)][num-1])) 
					num++;
				else{				//Si a més a més de no ser fixa estem al sudoku central en una de les caselles que es sobreposa, hi fiquem
					num = seguentGerma(i, j, num, mat, marca);
				}
				if(num==cas*cas+1){
					samurai[mat].getMatriu()[i][j] = -1;
					return;
				}
				samurai[mat].getMatriu()[i][j] = num;
				marcatge(i, j, num, mat, marca);				
			}
			
			if (i == cas * cas - 1 && j == cas * cas - 1) {
				if (mat < 4) {
					resolSamurai (0, 0, mat + 1, sortida, fitxer, marca);
				}
				else {
					if (sortida == 1) {
						this.printaSamurai();
					}
					else if (sortida == 2) {
						SudokuGUI guis = new SudokuGUI("Solució samurai", 0, 0, this.passaAFixes());
						guis.updateBoard(this.passaAMatriu() );
					}
					else if (sortida == 3) {
						this.passaAFitxer(fitxer);
					}
					Samurai.solucions++;
				}
			}
			else {
				if (j == cas * cas - 1) {
					resolSamurai(i + 1, 0, mat, sortida, fitxer, marca);
				}
				else {
					resolSamurai(i, j + 1, mat, sortida, fitxer, marca);
				}
			}
			if (samurai[mat].getFixes()[i][j]){
				desmarcatge(i, j, num, mat, marca);
				if (mat == 0) {
					int indy=(i/cas)*cas;
					int indx=(j/cas)*cas;
					int aux = cas * cas -cas;
					if (indy == 0 && indx == 0){
						this.samurai[1].getMatriu()[aux + i][aux + j] = - 1;
						desmarcatge(aux + i, aux + j, num, 1, marca);
					}
					else if (indy == 0 && indx == aux) {
						this.samurai[2].getMatriu()[aux + i][j - aux] = - 1;
						desmarcatge(aux + i, j - aux, num, 2, marca);
					}
					else if (indy == aux && indx == 0) {
						this.samurai[3].getMatriu()[i - aux][j + aux] = - 1;
						desmarcatge(i - aux, aux + j, num, 3, marca);
					}
					else if (indy == aux && indx == aux) {
						this.samurai[4].getMatriu()[i - aux][j - aux] = - 1;
						desmarcatge(i - aux, j - aux, num, 4, marca);
					}
				}
				num++;
			}
			else num = cas * cas + 1;
		}
		if (samurai[mat].getFixes()[i][j]) samurai[mat].getMatriu()[i][j] = - 1;
	}
	
	public void resolSamurai(int i, int j, SudokuGUI gui, int mat, int sortida, String fitxer, Marcatge[] marca) {
		//Crida recursiva que resol un sudoku. El que fem és començar per la posició [0][0] del sudoku
		//central i ens anem desplaçant per la fila en la que ens trobem, un cop hem acabat una fila, 
		//anem al principi de la següent i així fins que arribem a l'últim element d'un dels sudokus
		//que és el [cas*cas-1][cas*cas-1] (del sudoku individual). Un cop hem acabat amb un sudoku
		//passem al següent en el següent ordre: central -> superior esquerre -> superior dret ->
		//inferior esquerre -> inferior dret
		int num = 1;
		while (num <= cas * cas) {	//Mentre no haguem provat totes les combinacions de l'element
			if (samurai[mat].getFixes()[i][j]) {	//Si la posició no és fixa, en modifiquem el valor
				if (mat != 0) while(num<=cas*cas&&(marca[mat].getFiles()[i][num-1]
					||marca[mat].getColumnes()[j][num-1]
					||marca[mat].getCasella()[(i/cas)*cas+(j/cas)][num-1])) 
					num++;
				else{				//Si a més a més de no ser fixa estem al sudoku central en una de les caselles que es sobreposa, hi fiquem
					num=seguentGerma(i, j, num, mat, marca);
				}
				if(num==cas*cas+1){
					samurai[mat].getMatriu()[i][j] = -1;
					return;
				}
				samurai[mat].getMatriu()[i][j] = num;
				marcatge(i, j, num, mat, marca);				
			}
			
			if (i == cas * cas - 1 && j == cas * cas - 1) {
				if (mat < 4) {
					gui.updateBoard(this.setSudoku().getMatriu() );
					resolSamurai (0, 0, gui, mat + 1, sortida, fitxer, marca);
				}
				else {
					if (sortida == 1) {
						this.printaSamurai();
					}
					else if (sortida == 2) {
						gui.updateBoard(this.setSudoku().getMatriu() );
						SudokuGUI guis = new SudokuGUI("Solució samurai", 0, 0, this.passaAFixes());
						guis.updateBoard(this.passaAMatriu() );
					}
					else if (sortida == 3) {
						this.passaAFitxer(fitxer);
					}
					Samurai.solucions++;
				}
			}
			else {
				if (j == cas * cas - 1) {
					gui.updateBoard(this.setSudoku().getMatriu() );
					resolSamurai(i + 1, 0, gui, mat, sortida, fitxer, marca);
				}
				else {
					resolSamurai(i, j + 1, gui, mat, sortida, fitxer, marca);
				}
			}
			if (samurai[mat].getFixes()[i][j]){
				desmarcatge(i, j, num, mat, marca);
				if (mat == 0) {
					int indy=(i/cas)*cas;
					int indx=(j/cas)*cas;
					int aux = cas * cas - cas;
					if (indy == 0 && indx == 0){
						this.samurai[1].getMatriu()[aux + i][aux + j] = - 1;
						desmarcatge(aux + i, aux + j, num, 1, marca);
					}
					else if (indy == 0 && indx == aux) {
						this.samurai[2].getMatriu()[aux + i][j - aux] = - 1;
						desmarcatge(aux + i, j - aux, num, 2, marca);
					}
					else if (indy == aux && indx == 0) {
						this.samurai[3].getMatriu()[i - aux][j + aux] = - 1;
						desmarcatge(i - aux, aux + j, num, 3, marca);
					}
					else if (indy == aux && indx == aux) {
						this.samurai[4].getMatriu()[i - aux][j - aux] = - 1;
						desmarcatge(i - aux, j - aux, num, 4, marca);
					}
				}
				num++;
			}
			else num = cas * cas + 1;
		}
		if (samurai[mat].getFixes()[i][j]) samurai[mat].getMatriu()[i][j] = - 1;
	}
	
	public boolean[][] passaAFixes() {
		boolean[][] fixes = new boolean[3 * cas * cas - 2 * cas][3 * cas * cas - 2 * cas];
		Sudoku sudoku = this.setSudoku();
		for (int i = 0; i < 3 * cas * cas - 2 * cas; i++) {
			for (int j = 0; j < 3 * cas * cas - 2 * cas; j++) {
				fixes[i][j] = sudoku.getFixes()[i][j];
			}
		}
		return fixes;
	}
	
	public int[][] passaAMatriu() {
		int[][] matriu = new int[3 * cas * cas - 2 * cas][3 * cas * cas - 2 * cas];
		Sudoku sudoku = this.setSudoku();
		for (int i = 0; i < 3 * cas * cas - 2 * cas; i++) {
			for (int j = 0; j < 3 * cas * cas - 2 * cas; j++) {
				matriu[i][j] = sudoku.getMatriu()[i][j];
			}
		}
		return matriu;
	}
	
}