import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sudoku {
	private int[][] matriu;
	private boolean[][] fixes;
	private int cas;
	private static int solucions;
	
	public Sudoku(int cas) {
		super();
		matriu = new int[cas*cas][cas*cas];
		fixes = new boolean[cas*cas][cas*cas];
		this.cas = cas;
		solucions=0;
	}

	public Sudoku(int[][] matriu, boolean[][] fixes, int cas) {
		super();
		this.matriu = matriu;
		this.fixes = fixes;
		this.cas = cas;
		solucions=0;
	}
	
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
		Sudoku.solucions=solucions;
	}
	
	public boolean bona(int i, int j){
		int indx = 0, indy = 0;
		for(int x=0; x<cas*cas;x++) if(matriu[x][j] == matriu[i][j] && x!=i) return false;
		for(int x=0; x<cas*cas;x++) if(matriu[i][x] == matriu[i][j] && x!=j) return false;
		while(indy+cas<=i) indy+=cas;
		while(indx+cas<=j) indx+=cas;
		for(int a=indy;a<indy+cas;a++){
			for(int b=indx;b<indx+cas;b++){
				if(matriu[a][b]==matriu[i][j]&&i!=a&&j!=b) return false;
			}
		}
		return true;
	}
	
	public void printaSudoku(){
		int indy=0, indx;
		for(int i=0;i<cas*cas;i++){
			while(indy+cas<=i) indy+=cas;
			if(indy!=i)/* System.out.print("| ")*/;
			else{
				indx=0;
				for(int j=0;j<cas*cas;j++){
					while(indx+cas<=j) indx+=cas;
					if(j!=0) System.out.print(" ");
					if(j==indx) System.out.print("+  ");
					System.out.print("- ");
				}
				System.out.println(" +");
			}
			indx=0;
			for(int j=0;j<cas*cas;j++){
				while(indx+cas<=j) indx+=cas;
				if(indx==j&&indx!=0) System.out.print(" | ");
				else if(indx==j) System.out.print("| ");
				if(this.matriu[i][j]>9) System.out.print(this.matriu[i][j]+" ");
				if(this.matriu[i][j]<10) System.out.print(" "+this.matriu[i][j]+" ");
			}
			System.out.println(" |");
		}
		indx=0;
		for(int j=0;j<cas*cas;j++){
			while(indx+cas<=j) indx+=cas;
			if(j!=0) System.out.print(" ");
			if(j==indx) System.out.print("+  ");
			System.out.print("- ");
		}
		System.out.println(" +");
	}
	
	public void passaAFitxer(String fitxer){
	    try {
			PrintWriter printw = new PrintWriter(new FileWriter(fitxer));
			for(int i=0;i<cas*cas;i++){
				for(int j=0;j<cas*cas;j++){
					printw.print(this.matriu[i][j]+" ");
				}
				printw.println();
			}
			printw.close();
		} catch (IOException e) {
			System.out.println("Ts, jefe! Això no tira!");
		}
	    
	}
	
	public void resolSudoku(int i, int j, int sortida, String fitxer){
		int x = 1;
		while(x<=cas*cas){
			if(fixes[i][j]) matriu[i][j]=x;
			if(i==cas*cas-1 && j==cas*cas-1){
				if(this.bona(i, j)){
					if(sortida==1){
						this.printaSudoku();
					}
					else if(sortida==2){
						SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, fixes);
						guis.updateBoard(matriu);
					}
					else if(sortida==3){
						this.passaAFitxer(fitxer);
					}
					Sudoku.solucions++;
				}				
			}
			else{
				if(this.bona(i, j)){
					if(j==cas*cas-1){
						this.resolSudoku(i+1, 0, sortida, fitxer);
					}
					else{
						this.resolSudoku(i, j+1, sortida, fitxer);
					}
				}
			}
			if(fixes[i][j]) x++;
			else x = cas*cas+1;
		}
		if(fixes[i][j]) matriu[i][j]=-1;
	}
	/*
	public void resolSudoku(int i, int j, SudokuGUI gui){
		int x = 1;
		while(x<=cas*cas){
			
			if(fixes[i][j]) matriu[i][j]=x;
			if(i==cas*cas-1 && j==cas*cas-1){ 
				if(this.bona(i, j)){ 
					gui.updateBoard(matriu);
					SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, fixes);
					guis.updateBoard(matriu);
				}				
			}
			else{
				if(this.bona(i, j)){
					if(j==cas*cas-1){
						gui.updateBoard(matriu);
						this.resolSudoku(i+1, 0, gui);
					}
					else{
						this.resolSudoku(i, j+1, gui);
					}
				}
			}
			if(fixes[i][j]) x++;
			else x = cas*cas+1;
		}
		if(fixes[i][j]) matriu[i][j]=-1;
	}
	*/
	public Samurai setSamurai(int cas){
		//Extracció del sudoku gran a cinc petits de la classe samurai
		Samurai samurai = new Samurai(cas);
		//Set del sudoku central
		for(int i=cas*cas-cas, y=0;i<2*cas*cas-cas;i++, y++){
			for(int j=cas*cas-cas, x=0;j<2*cas*cas-cas;j++,x++){
				samurai.getSudoku(0).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(0).getFixes()[y][x] = fixes[i][j];
			}
		}
		//Set del sudoku superior esquerre
		for(int i = 0;i<cas*cas;i++){
			for(int j = 0;j<cas*cas;j++){
				samurai.getSudoku(1).getMatriu()[i][j] = matriu[i][j];
				samurai.getSudoku(1).getFixes()[i][j] = fixes[i][j];
			}
		}
		for(int i = cas*cas-cas;i<cas*cas;i++){
			for(int j = cas*cas-cas;j<cas*cas;j++){
				samurai.getSudoku(1).getFixes()[i][j] = false;
			}
		}
		//Set del sudoku superior dret
		for(int i = 0, y=0;i<cas*cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<3*cas*cas-2*cas;j++, x++){
				samurai.getSudoku(2).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(2).getFixes()[y][x] = fixes[i][j];
			}
		}
		for(int i = cas*cas-cas, y=cas*cas-cas;i<cas*cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<2*cas*(cas-1)+cas;j++, x++){
				samurai.getSudoku(2).getFixes()[y][x] = false;
			}
		}
		//Set del sudoku inferior esquerre
		for(int i = 2*cas*(cas-1), y=0;i<3*cas*cas-2*cas;i++, y++){
			for(int j = 0, x=0;j<cas*cas;j++, x++){
				samurai.getSudoku(3).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(3).getFixes()[y][x] = fixes[i][j];
			}
		}
		for(int i = 2*cas*(cas-1), y=0;i<2*cas*(cas-1)+cas;i++, y++){
			for(int j = cas*cas-cas, x=cas*cas-cas;j<cas*cas;j++, x++){
				samurai.getSudoku(3).getFixes()[y][x] = false;
			}
		}
		//Set del sudoku inferior dret
		for(int i = 2*cas*(cas-1), y=0;i<3*cas*cas-2*cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<3*cas*cas-2*cas;j++, x++){
				samurai.getSudoku(4).getMatriu()[y][x] = matriu[i][j];
				samurai.getSudoku(4).getFixes()[y][x] = fixes[i][j];
			}
		}
		for(int i = 2*cas*(cas-1), y=0;i<2*cas*(cas-1)+cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<2*cas*(cas-1)+cas;j++, x++){
				samurai.getSudoku(4).getFixes()[y][x] = false;
			}
		}
		return samurai;
	}
	
}



