
public class Marcatge {
	boolean[][] files;
	boolean[][] columnes;
	
	public Marcatge() {
		super();
	}
	public Marcatge(Sudoku s) {
		super();
		files = new boolean[s.getCas() * s.getCas()][s.getCas() * s.getCas()];
		columnes = new boolean[s.getCas() * s.getCas()][s.getCas() * s.getCas()];
		for(int i=0;i<s.getCas()*s.getCas();i++){
			for(int j=0;j<s.getCas()*s.getCas();j++){
				if(!s.getFixes()[i][j]&&s.getMatriu()[i][j]>0){
					files[i][s.getMatriu()[i][j]-1]=true;
					columnes[j][s.getMatriu()[i][j]-1]=true;
				}
			}
		}
	}
	
	public boolean[][] getFiles() {
		return files;
	}
	
	public void setFiles(boolean[][] files) {
		this.files = files;
	}
	
	public boolean[][] getColumnes() {
		return columnes;
	}

	public void setColumnes(boolean[][] columnes) {
		this.columnes = columnes;
	}
	public static Marcatge creaMarcatge(Sudoku s){
		Marcatge m = new Marcatge(s);
		return m;
	}
}
