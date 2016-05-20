import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Calendar;

//Classe que utilitzem per tal de treballar amb les dates que necessitem
//Hem hagut de recorrer a la creació d'aquesta classe perquè utilitzant les del sistema no sabiem

public class Data {
	
	//Atributs
	
	private int dia;		//Tenim atribut per cada dada que ens serveix d'una data
	private int mes;
	private int any;
	private int hora;
	private int minuts;
	private int segons;
	private int milisegons;
	
	//Constructors
	
	public Data() {
		//Constructor sense paràmetres
		super();
		dia = 1;
		mes = 1;
		any = 0;
		hora = 0;
		minuts = 0;
		segons = 0;
		milisegons = 0;
	}
	
	public Data(Calendar a) {
		//Constructor amb un paràmetre de tipus Calendar
		dia = a.get(Calendar.DAY_OF_MONTH);
		mes = a.get(Calendar.MONTH) + 1;
		any = a.get(Calendar.YEAR);
		hora = a.get(Calendar.HOUR_OF_DAY);
		minuts = a.get(Calendar.MINUTE);
		segons = a.get(Calendar.SECOND);
		milisegons = a.get(Calendar.MILLISECOND);
	}
	
	//Mètodes
	
	public Data actualitza(Calendar a) {
		//Funció actualitza la data i es retorna a sí mateixa
		dia = a.get(Calendar.DAY_OF_MONTH);
		mes = a.get(Calendar.MONTH) + 1;
		any = a.get(Calendar.YEAR);
		hora = a.get(Calendar.HOUR_OF_DAY);
		minuts = a.get(Calendar.MINUTE);
		segons = a.get(Calendar.SECOND);
		milisegons = a.get(Calendar.MILLISECOND);
		return this;
	}
	
	public String transcorregut(Data a) {
		//Funció que retorna un String amb la diferència entre dues dates diferents.
		//Per tal que ho faci bé, la data antiga és la que ha de cridar la funció i la més recent 
		//la que rebem com a paràmetre
		Data b = new Data();
		
		b.milisegons = a.milisegons - this.milisegons;
		if (this.milisegons > a.milisegons) {
			b.segons--;
			b.milisegons += 1000;
		}
		else if (this.milisegons < a.milisegons&&a.segons - this.segons<0) b.segons++;
		
		b.segons += a.segons - this.segons;
		if (this.segons > a.segons) {
			b.minuts--;
			b.segons += 60;
		}
		else if ((this.segons < a.segons)&&a.minuts-this.minuts<0) b.minuts++;
		
		b.minuts += a.minuts - this.minuts;
		if (this.minuts > a.minuts){
			b.hora--;
			b.minuts += 60;
		}
		else if (this.minuts < a.minuts&&a.hora-this.hora<0) b.hora++;
		
		b.hora += a.hora - this.hora;
		
		return Integer.toString(b.hora) + ":" + Integer.toString(b.minuts) + ":" + 
			Integer.toString(b.segons) + ":" + Integer.toString(b.milisegons);
	}
	
	//Mètodes override
	
	@Override
	public String toString() {
		//Funció toString de la classe Data
		return new String (Integer.toString(dia) + "/" + Integer.toString(mes) + "/" +
			Integer.toString(any) + " - " + Integer.toString(hora) + ":" +
			Integer.toString(minuts) + ":" + Integer.toString(segons) + ":" +
			Integer.toString(milisegons) );
	}

	
	public static void main(String[] args) {
		ThreadMXBean th = ManagementFactory.getThreadMXBean();
		long inici = System.nanoTime();
		
		MemoryMXBean tm = ManagementFactory.getMemoryMXBean();
		System.out.println(tm.getHeapMemoryUsage().getUsed());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Temps real = " + (System.nanoTime()-inici)/1000000);
		System.out.println("Temps CPU = " + th.getCurrentThreadCpuTime()/1000000);
		
	}
	
}
