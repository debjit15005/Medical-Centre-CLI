import java.time.DayOfWeek;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

public class Student implements Runnable{
	int studID;
	private String name;
	private String email;
	private String mobile;
	boolean running = true;
	Thread studThread;
	private boolean stopThread = false;
	
	
	Student (String name, int studID, String email, String mobile ){
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.studID = studID;
//		this.running = true;
		AppData.studs.put(studID, this);
	}
	
	public void start() {
		if(studThread==null) {
			this.studThread = new Thread(this,this.name);
			this.studThread.start();
		}
	}
	@Override
	public void run() {
		while(true) {
			if(stopThread) {System.out.println("Ending student"); break;}
			
			Scanner studentScanner = new Scanner(System.in);
				System.out.println("Welcome " + this.name);
				Main.studentMenu(studentScanner,this);	
		}
	}
	
	public void setStopThread(boolean stopThread) {
		this.stopThread = stopThread;
	}
	
	void takeAppointment (long timestamp, int docID, DayOfWeek day, LocalTime time,String strLine) throws Exception {
		
		Doctor doc = AppData.docs.get(docID);
		Set<Appointment> appts = doc.getAppts(day);
		if(appts.size()==0) {
			synchronized(doc.getAppts(day)) {
				Appointment appt = new Appointment(timestamp,this,doc,day,time,strLine);
				
			}
		}
		else {
		for(Appointment a: appts) {
			Duration d = Duration.between(a.time, time);
			if(d.toMinutes()>=10) {
				throw new Exception("Clash with another appointment");
			}
			else {
				synchronized(doc.getAppts(day)) {
					Appointment appt = new Appointment(timestamp,this,doc,day,time,strLine);
				}
			}
		}
		}
		
	}
	
	public void checkNoticeBoard() {
		for (Notice notice: AppData.notices) {
			notice.show();
		}
	}
	
	public void purchaseMeds(int medID, int quant, String p_mode) throws Exception {
		if(AppData.inventory.get(medID)==null) {
			throw new Exception("Medicine not available");
		}
		else if(AppData.inventory.get(medID).getQuantity()<quant) {
			throw new Exception("Quantity unservicable");
		}
		else {
			Medicine med = AppData.inventory.get(medID);
			int price = med.getPrice()*quant;
			med.setQuantity(med.getQuantity()-quant);
			
			System.out.println(med.getName() + " bought succesfully");
			switch(p_mode) {
			case "Cash":
				System.out.println("Paid in Cash");
				break;
			case "Later":
				if(AppData.dues.get(this)!=null)AppData.dues.replace(this, AppData.dues.get(this)+price) ;
				else {AppData.dues.put(this, price) ;}
				System.out.println("Purchase added to dues");
				break;
			}
			
			AppData.sale = AppData.sale + price;    
			AppData.writeToFile(price);
			
			
			
		}
		
	}

	public void checkDues() {
		if(AppData.dues.get(this)!=null)System.out.println("Your current dues: " +AppData.dues.get(this));
		else {System.out.println("Your current dues: 0" );}
	}
		
	public String getName() {
		return this.name;
	}

	
	public String toString() {
		return String.format(this.studID + " %20s " +" "+ this.email + " " + this.mobile + " - " , this.name);
	}
	
	public void writeToFile(String string) {
		try
        {
        FileWriter myWriter = new FileWriter("resources/studentDB.txt",true);
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
	
	
	
