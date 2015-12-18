import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Número de valors per recuadre: ");
		
		int cas = sc.nextInt();
		int[][] matriu = new int[cas*cas][cas*cas];
		boolean[][] fixes = new boolean[cas*cas][cas*cas];
		
		String a;
		String[] aux;
		
		BufferedReader br = new BufferedReader(new FileReader("Sudoku2.txt"));
		a = br.readLine();
		for(int i=0;a!=null;i++){
			aux = a.split(" ");
			for(int j=0;j<aux.length;j++){
				if(aux[j].charAt(0)=='-'){
					matriu[i][j] = -1;
					fixes[i][j] = false;
				}
				else{
					fixes[i][j] = true;
					matriu[i][j] = Integer.parseInt(aux[j]);
					
				}
			}
			a = br.readLine();
		}
		br.close();
		
		Sudoku sudoku = new Sudoku(matriu, fixes, cas);
		SudokuGUI gui = new SudokuGUI("Sudoku", 0, 0, sudoku.getFixes());
		gui.updateBoard(sudoku.getMatriu());
		sudoku.resolSudoku(0, 0, gui);
		gui.updateBoard(sudoku.getMatriu());
	}

}
