import java.time.*;
import java.util.*;

public class Doctor {
	int docID;
	String name;
	String consultationType;
	DayOfWeek[] daysAvailable;
	Duration available;
	SortedSet<LocalTime> appt;//use appointment instead of start time of appointment; make custom comparator for sorting
	//static Map<Integer,Doctor> docs;
	
	Doctor(int docID, String name, String consultationType, DayOfWeek[] daysAvailable,LocalTime startTime,LocalTime endTime){
		this.name = name;
		this.docID = docID;
		this.consultationType = consultationType;
		this.daysAvailable = daysAvailable;
		this.available = Duration.between(startTime, endTime);
		
		appt = new TreeSet<LocalTime>();
		AppData.docs.put(docID, this);
	}
	
	public int getID() {
		return this.docID;
	}
	
	public SortedSet<LocalTime> getAppts(){
		return this.appt;
	}
}
