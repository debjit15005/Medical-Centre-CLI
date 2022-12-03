import java.time.*;
import java.util.*;

public class Doctor {
	int docID;
	String name;
	String consultationType;
	DayOfWeek[] daysAvailable;
	LocalTime startTime;
	LocalTime endTime;
	List<TreeSet<LocalTime>> appt;//use appointment instead of start time of appointment; make custom comparator for sorting
	//static Map<Integer,Doctor> docs;
	
	Doctor(int docID, String name, String consultationType, DayOfWeek[] daysAvailable,LocalTime startTime,LocalTime endTime){
		this.name = name;
		this.docID = docID;
		this.consultationType = consultationType;
		this.daysAvailable = daysAvailable;
		this.startTime = startTime;
		this.endTime = endTime;
		
		appt = new ArrayList<TreeSet<LocalTime>>();
//		for(int i=0;i<7;i++) {
//			appt.get(i)=new TreeSet<LocalTime>();
//		}
		AppData.docs.put(docID, this);
	}
	
	public int getID() {
		return this.docID;
	}
	
	public TreeSet<LocalTime> getAppts(DayOfWeek day){
		return this.appt.get(day.getValue());
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
		return String.format(this.docID + " %1$%20s " + this.consultationType +" "+ this.daysAvailable + " " + this.startTime + " - " + this.endTime, this.name);
	}
}
