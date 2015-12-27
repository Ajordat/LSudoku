import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
	private int dia;
	private int mes;
	private int any;
	private int hora;
	private int minuts;
	private int segons;
	private int milisegons;
	public Data() {
		super();
		dia=1;
		mes=1;
		any=0;
		hora=0;
		minuts=0;
		segons=0;
		milisegons=0;
	}
	public Data(Calendar a){
		dia=a.get(Calendar.DAY_OF_MONTH);
		mes=a.get(Calendar.MONTH)+1;
		any=a.get(Calendar.YEAR);
		hora=a.get(Calendar.HOUR_OF_DAY);
		minuts=a.get(Calendar.MINUTE);
		segons=a.get(Calendar.SECOND);
		milisegons=a.get(Calendar.MILLISECOND);
	}
	public Data actualitza(Calendar a){
		dia=a.get(Calendar.DAY_OF_MONTH);
		mes=a.get(Calendar.MONTH)+1;
		any=a.get(Calendar.YEAR);
		hora=a.get(Calendar.HOUR_OF_DAY);
		minuts=a.get(Calendar.MINUTE);
		segons=a.get(Calendar.SECOND);
		milisegons=a.get(Calendar.MILLISECOND);
		return this;
	}
	public String toString(){
		return new String(Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+
			Integer.toString(any)+" - "+Integer.toString(hora)+":"+
			Integer.toString(minuts)+":"+Integer.toString(segons)+":"
			+Integer.toString(milisegons));
	}
	public String transcorregut(Data a){
		Data b=new Data();
		b.milisegons=a.milisegons-this.milisegons;
		if(this.milisegons>a.milisegons) b.milisegons+=1000;
		if(this.milisegons<a.milisegons) b.segons++;
		
		b.segons=a.segons-this.segons;
		if(this.segons>a.segons) b.segons+=60;
		if(this.segons<a.segons) b.minuts++;
		
		b.minuts=a.minuts-this.minuts;
		if(this.minuts>a.minuts) b.minuts+=60;
		if(this.minuts>a.minuts) b.hora++;
		
		b.hora=a.hora-this.hora;
		
		return Integer.toString(b.hora)+":"+Integer.toString(b.minuts)+":"+Integer.toString(b.segons)+":"
		+Integer.toString(b.milisegons);
	}
}
