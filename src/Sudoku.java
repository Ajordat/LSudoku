
public class Sudoku {
	private int[][] matriu;
	private boolean[][] fixes;
	private static int cas;
	
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
	
	private boolean bona(int i, int j){
		int a = 0, b = 0;
		int indx = 0, indy = 0;
		for(int x=0; x<cas*cas;x++) if(matriu[x][j] == matriu[i][j] && x!=i) return false;
		for(int x=0; x<cas*cas;x++) if(matriu[i][x] == matriu[i][j] && x!=j) return false;
		while(indy+cas<=i) indy+=cas;
		while(indx+cas<=j) indx+=cas;
		for(a=indy;a<indy+cas;a++){
			for(b=indx;b<indx+cas;b++){
				if(matriu[a][b]==matriu[i][j]&&i!=a&&j!=b) return false;
			}
		}
		return true;
	}
	public void resolSudoku(int i, int j, SudokuGUI gui){
		int x = 1;
		while(x<=cas*cas){
			
			if(!fixes[i][j]) matriu[i][j]=x;
			if(i==cas*cas-1 && j==cas*cas-1){ 
				if(this.bona(i, j)){ 
					//gui.updateBoard(matriu);
					SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, fixes);
					guis.updateBoard(matriu);
				}				
			}
			else{
				if(this.bona(i, j)){
					
					if(j==cas*cas-1){
						//gui.updateBoard(matriu);
						this.resolSudoku(i+1, 0, gui);
					}
					else{
						this.resolSudoku(i, j+1, gui);
					}
				}
			}
			if(!fixes[i][j]) x++;
			else x = cas*cas+1;
		}
		if(!fixes[i][j]) matriu[i][j]=-1;
	}
	public static void resolSamurai(int i, int j, SudokuGUI gui, Sudoku[] samurai, int mat){
		int x = 1;
		while(x<=cas*cas){
			
			if(!samurai[mat].fixes[i][j]) samurai[mat].matriu[i][j]=x;
			if(i==cas*cas-1 && j==cas*cas-1){
				if(samurai[mat].bona(i, j)){
					gui.updateBoard(samurai[mat].matriu);
					if(mat<5){
						resolSamurai(0, 0, gui, samurai, mat+1);
					}
					else{
						SudokuGUI guis = new SudokuGUI("Sudoku", 0, 0, samurai[mat].fixes);
						guis.updateBoard(samurai[mat].matriu);
					}
				}
			}
			else{
				if(samurai[mat].bona(i, j)){
					
					if(j==cas*cas-1){
						gui.updateBoard(samurai[mat].matriu);
						resolSamurai(i+1, 0, gui, samurai, mat);
					}
					else{
						resolSamurai(i, j+1, gui, samurai, mat);
					}
				}
			}
			if(!samurai[mat].fixes[i][j]) x++;
			else x = cas*cas+1;
		}
		if(!samurai[mat].fixes[i][j]) samurai[mat].matriu[i][j]=-1;
	}
}



