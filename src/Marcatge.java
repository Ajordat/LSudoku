
public class Marcatge {
	boolean[][] files;
	boolean[][] columnes;
	boolean[][] casella;
	
	public Marcatge() {
		super();
	}
	public Marcatge(Sudoku s) {
		super();
		int indx, indy;
		int x, y;
		files = new boolean[s.getCas() * s.getCas()][s.getCas() * s.getCas()];
		columnes = new boolean[s.getCas() * s.getCas()][s.getCas() * s.getCas()];
		casella = new boolean[s.getCas() * s.getCas()][s.getCas() * s.getCas()];
		for(int i=0;i<s.getCas()*s.getCas();i++){
			for(int j=0;j<s.getCas()*s.getCas();j++){
				if(!s.getFixes()[i][j]&&s.getMatriu()[i][j]>0){
					files[i][s.getMatriu()[i][j]-1]=true;
					columnes[j][s.getMatriu()[i][j]-1]=true;
					indx=0; indy=0; x=0; y=0;
					while (indy + s.getCas() <= i){
						y++;
						indy += s.getCas();
					}
					while (indx + s.getCas() <= j){
						x++;
						indx += s.getCas();
					}
					casella[y*s.getCas()+x][s.getMatriu()[i][j]-1]=true;
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
	public boolean[][] getCasella() {
		return casella;
	}
	public void setCasella(boolean[][] casella) {
		this.casella = casella;
	}
	public static Marcatge creaMarcatge(Sudoku s){
		Marcatge m = new Marcatge(s);
		return m;
	}
}
