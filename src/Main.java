import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static final int MATRIU4 = 0;
	public static final int MATRIU9 = 1;
	public static final int MATRIU16 = 2;
	public static final int MATRIUS = 3;
	public static void main(String[] args) throws IOException{		
		String a;
		String[] aux;
		BufferedReader br = new BufferedReader(new FileReader(args[MATRIUS]));
		a = br.readLine();
		aux = a.split(" ");
		int[][] matriu = new int[aux.length][aux.length];
		boolean[][] fixes = new boolean[aux.length][aux.length];
		for(int i=0;a!=null;i++){
			for(int j=0;j<aux.length;j++){
				if(aux[j].charAt(0)=='-'){
					matriu[i][j] = -1;
					fixes[i][j] = true;
				}
				else if(aux[j].charAt(0)=='*'){
					fixes[i][j] = true;
				}
				else{
					fixes[i][j] = false;
					matriu[i][j] = Integer.parseInt(aux[j]);
					
				}
			}
			a = br.readLine();
			if(a!=null) aux = a.split(" ");
		}
		br.close();
		System.out.println(aux.length);
		int cas;
		//Sudoku sudoku = new Sudoku(matriu, fixes, (int) Math.sqrt(aux.length));
		for(cas=1;aux.length!=cas*cas*3-2*cas;cas++);
		System.out.println(cas);
		Sudoku sudoku = new Sudoku(matriu, fixes, cas);
		SudokuGUI gui = new SudokuGUI("Sudoku", 0, 0, sudoku.getFixes());
		
		gui.updateBoard(sudoku.getMatriu());
		sudoku.resolSudoku(0, 0, gui);
		//Sudoku[] samurai = sudoku.aSamurai();
		//sudoku.resolSamurai(0, 0, gui, samurai, 0);
		gui.updateBoard(sudoku.getMatriu());
	}

}
