import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;

public class Main {
	public static final int MATRIU4 = 0;
	public static final int MATRIU9 = 1;
	public static final int MATRIU16 = 2;
	public static final int MATRIUS = 3;
	public static final int PROVA = 4;
	public static final int MATRIUD = 5;
	public static final int MATRIUS2 = 6;
	public static final int MATRIUCENTRAL = 7;
	public static final int TEMP = 8;
	
	public static void main(String[] args) throws IOException{		
		String a;
		String[] aux;
		boolean esSamurai = false;	
		//BufferedReader br = new BufferedReader(new FileReader(args[0]));
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
		
		//int sortida=Integer.parseInt(args[1]);
		int sortida=1;
		//String fitxer = new String(args[2]);
		String fitxer = new String("temp.txt");
		
		Data data1 = new Data(new GregorianCalendar());
		System.out.println(data1.toString());
		
		if(!esSamurai){
			Sudoku sudoku = new Sudoku(matriu, fixes, (int) Math.sqrt(aux.length));
			sudoku.resolSudoku(0, 0, sortida, fitxer);
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
			samurai.resolSamurai(0, 0, gui, 0, sortida, fitxer);
			gui.updateBoard(sudoku.getMatriu());
		}
		
		Data data2 = new Data(new GregorianCalendar());
		System.out.println(data2.toString());
		System.out.println("Han pasat: " + data1.transcorregut(data2));
		if(esSamurai){
			if(Samurai.getSolucions()==1) System.out.println("El Sudoku Samurai ha tingut una única solució.");
			else System.out.println("El Sudoku Samurai ha tingut "+Samurai.getSolucions()+ " solucions.");
		}
		else{
			if(Sudoku.getSolucions()==1) System.out.println("El sudoku ha tingut una única solució.");
			else System.out.println("El Sudoku ha tingut "+Sudoku.getSolucions()+ " solucions.");
		}
		
	}
}
