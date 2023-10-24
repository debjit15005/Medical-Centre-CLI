import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

public class Appointment {
	long timestamp;
	int studID;
	int docID;
	DayOfWeek day;
	LocalTime time;
	
	public Appointment(long timestamp, Student stud, Doctor doc, DayOfWeek day, LocalTime time,String strLine){
		this.timestamp = timestamp;
		this.studID=stud.studID;
		this.docID=doc.getID();
		this.day=day;
		this.time = time;
		
		doc.getAppts(day).add(this);
		this.writeToFile(strLine);
	}
	
	public String toString() {
		return String.format(this.docID +" "+ this.studID + " " + this.time);
	}
	
	public void writeToFile(String string) {
		try
        {
        FileWriter myWriter = new FileWriter("resources/appointmentDB.txt",true);
        if(string==null) return;
        myWriter.write(string+"\n");
        myWriter.close();
        //System.out.println("File created");
        }
        catch (IOException e)
        {
          System.out.println("An error occurred.");
        }
	}
	



}
