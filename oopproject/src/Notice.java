import java.time.DayOfWeek;
import java.time.LocalTime;

public class Notice {
//	private int docID;
//	private String name;
//	private String consultationType;
//	private String daysAvailable;
//	private String time;
	Doctor doc;
	
	Notice(Doctor d){
//		this.docID = d.getID();
//		this.name = d.getName();
//		this.consultationType = d.getConsultationType();
//		this.daysAvailable = d.getDaysAvailable();
//		this.time = d.getTime();
		this.doc = d;
		
		AppData.notices.add(this);
	}
	
	
	
//	public void showNotice() {
//		System.out.println(docID + '\n');
//		System.out.println(name + '\n');
//		System.out.println(consultationType + '\n');
//		System.out.println(daysAvailable + '\n');
//		System.out.println(time + '\n');
//	}
	
	public String show() {
		return doc.toString();
	}
	
	
}