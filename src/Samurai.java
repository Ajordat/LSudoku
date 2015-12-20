import java.util.Scanner;

public class Samurai {
	private Sudoku[] samurai;
	private static int cas;
	
	public Samurai(int cas) {
		super();
		samurai = new Sudoku[5];
		for(int i=0;i<5;i++){
			samurai[i] = new Sudoku(cas);
		}
		Samurai.cas=cas;
	}

	public Sudoku[] getSamurai() {
		return samurai;
	}

	public void setSamurai(Sudoku[] samurai) {
		this.samurai = samurai;
	}
	public Sudoku getSudoku(int pos){
		return samurai[pos];
	}
	public boolean bona(int i, int j, int mat){
		int indy=0, indx =0;
		if(mat==0){
			while(indy+cas<=i) indy+=cas;
			while(indx+cas<=j) indx+=cas;
			if(indy==0&&indx==0){
				getSudoku(1).getMatriu()[cas*cas-cas+i][cas*cas-cas+j]=getSudoku(0).getMatriu()[i][j];
				/*for(int a=0;a<cas*cas;a++){
					//System.out.println("i: "+i+"\na: "+a+"\n");
					if(this.getSudoku(1).getMatriu()[a][cas*cas-cas+j]==this.getSudoku(0).getMatriu()[i][j]){
						//Scanner sc = new Scanner(System.in);
						//String asdf = new String(sc.nextLine());
						if(a!=i) return false;
					}
				}
				for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(1).getMatriu()[cas*cas-cas+i][a]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=j) return false;
					}
				}*/
			}
			if(indy==0&&indx==cas*cas-cas){
				getSudoku(2).getMatriu()[cas*cas-cas+i][cas+j-cas*cas]=getSudoku(0).getMatriu()[i][j];
				/*for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(2).getMatriu()[a][cas+j-cas*cas]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=i) return false;
					}
				}
				for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(2).getMatriu()[cas*cas-cas+i][a]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=j) return false;
					}
				}*/
			}
			if(indy==cas*cas-cas&&indx==0){
				getSudoku(3).getMatriu()[cas+i-cas*cas][cas*cas-cas+j]=getSudoku(0).getMatriu()[i][j];
				/*for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(3).getMatriu()[a][cas*cas-cas+j]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=i) return false;
					}
				}
				for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(3).getMatriu()[-cas*cas+cas+i][a]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=j) return false;
					}
				}*/
			}
			if(indy==cas*cas-cas&&indx==cas*cas-cas){
				//getSudoku(4).getMatriu()[cas*cas-cas+i][cas+j-cas*cas]=getSudoku(0).getMatriu()[i][j];
				/*for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(4).getMatriu()[a][-cas*cas+cas+j]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=i) return false;
					}
				}
				for(int a=0;a<cas*cas;a++){
					if(this.getSudoku(4).getMatriu()[cas*cas-cas+i][a]==this.getSudoku(0).getMatriu()[i][j]){
						if(a!=j) return false;
					}
				}*/
			}
		}
		return samurai[mat].bona(i, j);
	}
	public Sudoku setSudoku(){
		Sudoku sudoku = new Sudoku(cas*(3*cas-2));
		
		for(int i=0;i<sudoku.getMatriu().length;i++){
			for(int j=0;j<sudoku.getMatriu().length;j++){
				sudoku.getMatriu()[i][j]=0;
				sudoku.getFixes()[i][j]=false;
			}
		}
		//Set del sudoku central
		for(int i=cas*cas-cas, y=0;i<2*cas*cas-cas;i++, y++){
			for(int j=cas*cas-cas, x=0;j<2*cas*cas-cas;j++,x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(0).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(0).getFixes()[y][x];
			}
		}
		//Set del sudoku superior esquerre
		for(int i = 0;i<cas*cas;i++){
			for(int j = 0;j<cas*cas;j++){
				sudoku.getMatriu()[i][j] = this.getSudoku(1).getMatriu()[i][j];
				sudoku.getFixes()[i][j] = this.getSudoku(1).getFixes()[i][j];
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
		//Set del sudoku central
		/*for(int i=cas*cas-cas, y=0;i<2*cas*cas-cas;i++, y++){
			for(int j=cas*cas-cas, x=0;j<2*cas*cas-cas;j++,x++){
				sudoku.getMatriu()[i][j] = this.getSudoku(0).getMatriu()[y][x];
				sudoku.getFixes()[i][j] = this.getSudoku(0).getFixes()[y][x];
			}
		}*/
		return sudoku;
	}
	public void resolSamurai(int i, int j, SudokuGUI gui, int mat){
		int x = 1;
		//System.out.println("Matriu: "+mat+"\ni: "+i+"\nj: "+j);
		while(x<=cas*cas){
			if(samurai[mat].getFixes()[i][j]) samurai[mat].getMatriu()[i][j]=x;
			
			if(i==cas*cas-1 && j==cas*cas-1){
				if(this.bona(i, j, mat)){
					gui.updateBoard(this.setSudoku().getMatriu());
					if(mat<4){
						System.out.println(mat);
						resolSamurai(0, 0, gui, mat+1);
					}
					else{
						SudokuGUI guip = new SudokuGUI("Sudoku", 0, 0, this.setSudoku().getFixes());
						guip.updateBoard(this.setSudoku().getMatriu());
						System.out.println("Tinc solució");
					}
				}
			}
			else{
				if(this.bona(i,  j, mat)){
					if(j==cas*cas-1){
						gui.updateBoard(this.setSudoku().getMatriu());
						resolSamurai(i+1, 0, gui, mat);
					}
					else{
						resolSamurai(i, j+1, gui, mat);
					}
				}
			}
			if(samurai[mat].getFixes()[i][j]) x++;
			else x = cas*cas+1;
		}
		if(samurai[mat].getFixes()[i][j]) samurai[mat].getMatriu()[i][j]=-1;
	}
}
