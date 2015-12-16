
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
	private boolean bona(int i, int j){
		int a = 0, b = 0;
		for(int x=0; x<=cas*cas;x++) if(matriu[x][j] == matriu[i][j] && x!=i) return false;
		for(int x=0; x<=cas*cas;x++) if(matriu[i][x] == matriu[i][j] && x!=j) return false;
		
		return true;
	}
	public void resolSudoku(int i, int j){
		int x = 1;
		while(x<=cas*cas){
			if(!fixes[i][j]) matriu[i][j]=x;
			if(i==cas*cas-1 && j==cas*cas-1){ 
				if(this.bona(i, j)){ 
				//	printaSolucio(matriu);
				}				
			}
			else{
				if(this.bona(i, j)){
					if(j==cas*cas-1){
						this.resolSudoku(i+1, 0);
					}
					else{
						this.resolSudoku(i, j+1);
					}
				}
			}
			if(!fixes[i][j]) x++;
			else x = cas*cas+1;
		}
		if(!fixes[i][j]) matriu[i][j]=0;
	}
}



