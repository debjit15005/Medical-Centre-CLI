import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

public class Doctor {
	int docID;
	private String name;
	private String consultationType;
	private DayOfWeek[] daysAvailable;
	private LocalTime startTime;
	private LocalTime endTime;
	private List<HashSet<Appointment>> appt;//use appointment instead of start time of appointment; make custom comparator for sorting
	
	
	Doctor(int docID, String name, String consultationType, DayOfWeek[] daysAvailable,LocalTime startTime,LocalTime endTime){
		this.name = name;
		this.docID = docID;
		this.consultationType = consultationType;
		this.daysAvailable = daysAvailable;
		this.startTime = startTime;
		this.endTime = endTime;
		this.appt = new ArrayList<HashSet<Appointment>>(7);
		for(int i=0;i<7;i++) {
			appt.add(new HashSet<Appointment>());
			
		}
		AppData.docs.put(docID, this);
	}
	
	public int getID() {
		return this.docID;
	}
	
	public HashSet<Appointment> getAppts(DayOfWeek day){
		return this.appt.get(day.getValue()-1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getConsultationType() {
		return this.consultationType;
	}
	
	public DayOfWeek[] getDaysAvailable() {
		return this.daysAvailable;
	}
	
	public void setDaysAvailable(DayOfWeek[] days) {
		this.daysAvailable=days;
	}
	
	public void setConsultationType(String consultationType) {
		this.consultationType = consultationType;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		String[] days = new String[this.daysAvailable.length];
		for(int i=0;i<this.daysAvailable.length;i++) {
			days[i] = this.daysAvailable[i].getDisplayName(TextStyle.NARROW,Locale.US);
		}
		String dayss = "";
		for(int i=0;i<this.daysAvailable.length;i++) {
			dayss = dayss + days[i] + " ";
		}
		
				
		return String.format(this.docID + " %-10s " + "%-14s" +" "+ "%-10s" + " " + this.startTime + " - " + this.endTime, this.consultationType, this.name, dayss);
	}
}
