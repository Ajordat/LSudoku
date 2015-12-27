import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Samurai {
	private Sudoku[] samurai;
	private static int cas;
	private static int solucions;
	
	public Samurai(int cas) {
		super();
		samurai = new Sudoku[5];
		for(int i=0;i<5;i++){
			samurai[i] = new Sudoku(cas);
		}
		Samurai.cas=cas;
		solucions=0;
	}

	public Sudoku[] getSamurai() {
		return samurai;
	}

	public void setSamurai(Sudoku[] samurai) {
		this.samurai = samurai;
	}
	
	public static int getSolucions(){
		return solucions;
	}
	
	public void setSolucions(int solucions){
		Samurai.solucions=solucions;
	}
	
	public Sudoku getSudoku(int pos){
		return samurai[pos];
	}
	
	public boolean bona(int i, int j, int mat){
		if(mat==0){
			int indy=0;
			int indx=0;
			while(indy+cas<=i) indy+=cas;
			while(indx+cas<=j) indx+=cas;
			if(indy==0&&indx==0){
				for(int a=0;a<cas*cas;a++){
					if(samurai[1].getMatriu()[a][cas*cas-cas+j]==samurai[0].getMatriu()[i][j]){
						if(a!=cas*cas-cas+i) return false;
					}
				}
				for(int b=0;b<cas*cas-cas;b++){
					if(samurai[1].getMatriu()[cas*cas-cas+i][b]==samurai[0].getMatriu()[i][j]){
						if(b!=cas*cas-cas+j) return false;
					}
				}
			}
			else if(indy==0&&indx==6){
				for(int a=0;a<cas*cas;a++){
					if(samurai[2].getMatriu()[a][-cas*cas+cas+j]==samurai[0].getMatriu()[i][j]){
						if(a!=cas*cas-cas+i) return false;
					}
				}
				for(int b=0;b<cas*cas-cas;b++){
					if(samurai[2].getMatriu()[cas*cas-cas+i][b]==samurai[0].getMatriu()[i][j]){
						if(b!=-cas*cas+cas+j) return false;
					}
				}
			}
			else if(indy==6&&indx==0){
				for(int a=0;a<cas*cas;a++){
					if(samurai[3].getMatriu()[a][cas*cas-cas+j]==samurai[0].getMatriu()[i][j]){
						if(a!=-cas*cas+cas+i) return false;
					}
				}
				for(int b=0;b<cas*cas-cas;b++){
					if(samurai[3].getMatriu()[-cas*cas+cas+i][b]==samurai[0].getMatriu()[i][j]){
						if(b!=cas*cas-cas+j) return false;
					}
				}
			}
			else if(indy==6&&indx==6){
				for(int a=0;a<cas*cas;a++){
					if(samurai[4].getMatriu()[a][-cas*cas+cas+j]==samurai[0].getMatriu()[i][j]){
						if(a!=-cas*cas+cas+i) return false;
					}
				}
				for(int b=0;b<cas*cas-cas;b++){
					if(samurai[4].getMatriu()[-cas*cas+cas+i][b]==samurai[0].getMatriu()[i][j]){
						if(b!=-cas*cas+cas+j) return false;
					}
				}
			}
			
		}
		return samurai[mat].bona(i, j);
	}
	
	public void printaSamurai(){
		Sudoku sudoku = this.setSudoku();
		int indx, indy=0;
		System.out.println(cas);
		for(int i=0;i<3*cas*cas-2*cas;i++){
			while(indy+cas<=i) indy+=cas;
			if(indy==i){
				indx=0;
				for(int j=0;j<3*cas*cas-2*cas;j++){
					while(indx+cas<=j) indx+=cas;
					if(j!=0) System.out.print(" ");
					if(j==indx) System.out.print("+  ");
					System.out.print("- ");
				}
				System.out.println(" +");
			}
			indx=0;
			for(int j=0;j<3*cas*cas-2*cas;j++){
				while(indx+cas<=j) indx+=cas;
				if(indx==j&&indx!=0) System.out.print(" | ");
				else if(indx==j) System.out.print("| ");
				if(sudoku.getMatriu()[i][j]==0) System.out.print(" * ");
				else if(sudoku.getMatriu()[i][j]>9) 
					System.out.print(sudoku.getMatriu()[i][j]+" ");
				else if(sudoku.getMatriu()[i][j]==-1) System.out.print(" - ");
				else System.out.print(" "+sudoku.getMatriu()[i][j]+" ");
			}
			System.out.println(" |");
		}
		indx=0;
		for(int j=0;j<3*cas*cas-2*cas;j++){
			while(indx+cas<=j) indx+=cas;
			if(j!=0) System.out.print(" ");
			if(j==indx) System.out.print("+  ");
			System.out.print("- ");
		}
		System.out.println(" +");
	}
	
	public void passaAFitxer(String fitxer){
	    try {
	    	Sudoku sudoku = this.setSudoku();
			PrintWriter printw = new PrintWriter(new FileWriter(fitxer));
			for(int i=0;i<3*cas*cas-2*cas;i++){
				for(int j=0;j<3*cas*cas-2*cas;j++){
					if(sudoku.getMatriu()[i][j]==0) printw.print("* ");
					else if(sudoku.getMatriu()[i][j]==-1) printw.print("- ");
					else printw.print(sudoku.getMatriu()[i][j]+" ");
				}
				printw.println();
			}
			printw.close();
		} catch (IOException e) {
			System.out.println("Ts, jefe! Això no tira!");
		}
	    
	}
	
	public Sudoku setSudoku(){
		Sudoku sudoku = new Sudoku(cas*(3*cas-2));
		
		for(int i=0;i<sudoku.getMatriu().length;i++){
			for(int j=0;j<sudoku.getMatriu().length;j++){
				sudoku.getMatriu()[i][j]=0;
				sudoku.getFixes()[i][j]=false;
			}
		}		
		//Set del sudoku superior dret
		for(int i = 0, y=0;i<cas*cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<3*cas*cas-2*cas;j++, x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(2).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(2).getFixes()[y][x];
			}
		}
		//Set del sudoku inferior esquerre
		for(int i = 2*cas*(cas-1), y=0;i<3*cas*cas-2*cas;i++, y++){
			for(int j = 0, x=0;j<cas*cas;j++, x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(3).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(3).getFixes()[y][x];
			}
		}
		//Set del sudoku inferior dret
		for(int i = 2*cas*(cas-1), y=0;i<3*cas*cas-2*cas;i++, y++){
			for(int j = 2*cas*(cas-1), x=0;j<3*cas*cas-2*cas;j++, x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(4).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(4).getFixes()[y][x];
			}
		}
		//Set del sudoku superior esquerre
		for(int i = 0;i<cas*cas;i++){
			for(int j = 0;j<cas*cas;j++){
				sudoku.getMatriu()[i][j] = this.getSudoku(1).getMatriu()[i][j];
				sudoku.getFixes()[i][j] = this.getSudoku(1).getFixes()[i][j];
			}
		}
		//Set del sudoku central
		for(int i=cas*cas-cas, y=0;i<2*cas*cas-cas;i++, y++){
			for(int j=cas*cas-cas, x=0;j<2*cas*cas-cas;j++,x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(0).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(0).getFixes()[y][x];
			}
		}
		return sudoku;
	}
	
	public void resolSamurai(int i, int j, SudokuGUI gui, int mat, int sortida, String fitxer){
		int num = 1;
		while(num<=cas*cas){
			if(samurai[mat].getFixes()[i][j]){
				samurai[mat].getMatriu()[i][j]=num;
				if(mat==0){
					int indy=0;
					int indx=0;
					while(indy+cas<=i) indy+=cas;
					while(indx+cas<=j) indx+=cas;
					if(indy==0&&indx==0){
						this.samurai[1].getMatriu()[6+i][6+j]=samurai[0].getMatriu()[i][j];
					}
					else if(indy==0&&indx==6){
						this.samurai[2].getMatriu()[6+i][j-6]=samurai[0].getMatriu()[i][j];
					}
					else if(indy==6&&indx==0){
						this.samurai[3].getMatriu()[-6+i][j+6]=samurai[0].getMatriu()[i][j];
					}
					else if(indy==6&&indx==6){
						this.samurai[4].getMatriu()[-6+i][j-6]=samurai[0].getMatriu()[i][j];
					}
				}
			}
			
			if(i==cas*cas-1 && j==cas*cas-1){
				if(this.bona(i, j, mat)){
					if(mat<4){
						gui.updateBoard(this.setSudoku().getMatriu());
						resolSamurai(0, 0, gui, mat+1, sortida, fitxer);
					}
					else{
						if(sortida==1){
							this.printaSamurai();
						}
						else if(sortida==2){
							gui.updateBoard(this.setSudoku().getMatriu());
							//Sudoku sudoku = this.setSudoku();
							//sudoku.setSamurai(cas).printaSamurai();
							SudokuGUI guis = new SudokuGUI("Solució samurai", 0, 0, this.setSudoku().getFixes());
							guis.updateBoard(this.setSudoku().getMatriu());
							/*
							System.out.println("Ha entrat al condicional.");
							Sudoku sudoku = this.setSudoku();
							System.out.println("Ha creat el sudoku gegant.");
							SudokuGUI guis = new SudokuGUI("Solució samurai", 0, 0, sudoku.getFixes());
							System.out.println("Ha creat el GUI amb els booleans.");
							guis.updateBoard(sudoku.getMatriu());
							System.out.println("Ha mostrat la solució per pantalla.");
							*/
						}
						else if(sortida==3){
							this.passaAFitxer(fitxer);
						}
						Samurai.solucions++;
					}
				}
			}
			else{
				if(this.bona(i,  j, mat)){
					if(j==cas*cas-1){
						
						gui.updateBoard(this.setSudoku().getMatriu());
						resolSamurai(i+1, 0, gui, mat, sortida, fitxer);
					}
					else{
						resolSamurai(i, j+1, gui, mat, sortida, fitxer);
					}
				}
			}
			if(samurai[mat].getFixes()[i][j]) num++;
			else num = cas*cas+1;
		}
		if(samurai[mat].getFixes()[i][j]){
			samurai[mat].getMatriu()[i][j]=-1;
			if(mat==0){
				int indy=0;
				int indx=0;
				while(indy+cas<=i) indy+=cas;
				while(indx+cas<=j) indx+=cas;
				if(indy==0&&indx==0){
					this.samurai[1].getMatriu()[6+i][6+j]=-1;
				}
				else if(indy==0&&indx==6){
					this.samurai[2].getMatriu()[6+i][j-6]=-1;
				}
				else if(indy==6&&indx==0){
					this.samurai[3].getMatriu()[-6+i][j+6]=-1;
				}
				else if(indy==6&&indx==6){
					this.samurai[4].getMatriu()[-6+i][j-6]=-1;
				}
			}
		}
		
	}
}
