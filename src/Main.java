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
		boolean esSamurai = false;
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
					esSamurai = true;
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
		
		if(!esSamurai){
			Sudoku sudoku = new Sudoku(matriu, fixes, (int) Math.sqrt(aux.length));
			SudokuGUI gui = new SudokuGUI("Sudoku", 0, 0, sudoku.getFixes());
			gui.updateBoard(sudoku.getMatriu());
			sudoku.resolSudoku(0, 0, gui);
		}else{
			//Obtenim el nombre de caselles pel samurai
			int cas;
			for(cas=1;aux.length!=cas*cas*3-2*cas;cas++);

			//En aquest sudoku hi guardem el que és tot el conjunt dels cinc sudokus
			Sudoku sudoku = new Sudoku(matriu, fixes, cas);
			
			//Mostrem la variable sudoku, que recordem és la visió global del samurai
			SudokuGUI gui = new SudokuGUI("Sudoku", 0, 0, sudoku.getFixes());
			gui.updateBoard(sudoku.getMatriu());
			
			Samurai samurai = sudoku.setSamurai(cas);
			samurai.resolSamurai(0, 0, gui, 0);
			System.out.println("Adeu!");
		}
	}
}
