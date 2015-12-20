
public class Sudoku {
	private int[][] matriu;
	private boolean[][] fixes;
	private int cas;
	
	public Sudoku(int cas) {
		super();
		matriu = new int[cas*cas][cas*cas];
		fixes = new boolean[cas*cas][cas*cas];
		this.cas = cas;
	}

	public Sudoku(int[][] matriu, boolean[][] fixes, int cas) {
		super();
		this.matriu = matriu;
		this.fixes = fixes;
		this.cas = cas;
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
	public Samurai setSamurai(int cas){
		Samurai samurai = new Samurai(cas);
		Sudoku sudoku = new Sudoku(cas);
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
		for(int i = cas*cas-cas, y=0;i<cas*cas;i++, y++){
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
			for(int j = cas*cas-cas, x=0;j<cas*cas;j++, x++){
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



