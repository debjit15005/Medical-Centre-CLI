package medc;

public class Notice {
	private int docID;
	private String name;
	private String consultationType;
	private String daysAvailable;
	private String time;
	
	Notice(Doctor d){
		this.docID = d.getID();
		this.name = d.getName();
		this.consultationType = d.getConsultationType();
		this.daysAvailable = d.getDaysAvailable();
		this.time = d.getTime();
	}
	
	public void showNotice() {
		System.out.println(docID + '\n');
		System.out.println(name + '\n');
		System.out.println(consultationType + '\n');
		System.out.println(daysAvailable + '\n');
		System.out.println(time + '\n');
	}
	
	
}
