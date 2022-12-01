package medc;

public class Doctor {
	private int docID;
	private String name;
	private String consultationType;
	private String daysAvailable;
	private String time;
	Doctor(int docId, String name, String consultationType, String daysAvailable,String time){
		this.name = name;
		this.docID = docId;
		this.consultationType = consultationType;
		this.daysAvailable = daysAvailable;
		this.time = time;
	}
	
	public int getID() {
		return this.docID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getConsultationType() {
		return this.consultationType;
	}
	
	public String getDaysAvailable() {
		return this.daysAvailable;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setDaysAvailabe(String DaysAvailable) {
		this.daysAvailable = DaysAvailable;
	}
	
	public void setConsultationType(String consultationType) {
		this.consultationType = consultationType;
	}
}


