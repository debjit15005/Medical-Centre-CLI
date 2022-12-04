import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class Main extends Thread{
	public static void main(String[] args) {
		
		Main.scanStudent(null, "resources/studentDB.txt", 0);
		Main.scanNotice(null,"resources/noticeDB.txt",0);
		Main.scanAppointment(null, "resources/appointmentDB.txt", 0);
		Main.scanMedicine(null, "resources/MedicineDB.txt", 0);
		AppData.scanSales("resources/SALES.txt",0);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to MedC");
		
		
			modeMenu(sc);
			//Thread.currentThread().interrupt();
		
		
		
			
	}
	
	public static void modeMenu(Scanner sc) {
		
			String mode = "";
			System.out.println("Choose mode: \n A Admin Mode \n S Student Mode\n Q Quit");
			mode = sc.nextLine();
			switch(mode) {
			case "A":
				//state = State.ADMIN_MENU;
				Admin admin = new Admin();
				admin.start();
				//Thread.currentThread().interrupt();
				//adminMenu(sc);
				break;
			case "S":
				//state = State.STUDENT_MENU;
				studentReg(sc);
				break;
			case "Q":
				System.exit(0);
			default:
				System.out.println("Unknown input");
				modeMenu(sc);
				break;
				
			}		
	}
	
	public static void adminMenu(Scanner sc) {
//		System.out.println("in adminMenu");
		int in = 0;

		System.out.println("Menu: \n 1 View Student records\n 2 Remove Student\n 3 View appointments\n 4 Update Notice Board \n 5 Medicine Store\n 6 Net Sales\n 0 Quit");
		in = sc.nextInt();
		sc.nextLine();
		switch(in){
		case 1:
			for(Map.Entry<Integer,Student> stud : AppData.studs.entrySet()) {
				System.out.println(stud.getValue());
			}
			System.out.println();
			adminMenu(sc);
			break;
		case 3:
			System.out.println("Choose day");
			String day = sc.nextLine();
			DayOfWeek d = parseDay(day);
			System.out.println("Appointments on " + day);
			
			for(Map.Entry<Integer,Doctor> doc : AppData.docs.entrySet()) {
				Iterator<Appointment> i = doc.getValue().getAppts(d).iterator();
				while(i.hasNext()) {
					System.out.println(i.next().toString());
				}
//				System.out.println(doc.getValue().getAppts(d).);
			}
			System.out.println();
			adminMenu(sc);
			break;
		case 4:
			
			noticeBoardMenu(sc);
			
			break;
		case 5:
			
			//System.out.println(admin.adminThread.isAlive());
			medicineStoreMenu(sc);
			break;
		case 6:
			System.out.println("Net sales: "+AppData.sale);
			break;
		case 0:
//			System.out.println(admin.adminThread.isAlive());
//			admin.setStopThread(true);
//			try {
//				Thread t = currentThread();
//				t.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(admin.adminThread.isAlive());
//			//modeMenu(sc);
			System.exit(0);
		case 2:
			System.out.println("Enter id: ");
			int id = Integer.parseInt(sc.nextLine());
			if(AppData.studs.containsKey(id)) {
				AppData.studs.remove(id);
				System.out.println("Student Removed");
			}
			else {
				System.out.println("Student not found");
			}
			adminMenu(sc);
			
		}
		
	}
	
	private static void getTodayAppts(Scanner sc) {
		String day = sc.nextLine();
		DayOfWeek d = parseDay(day);
		for(Map.Entry<Integer, Doctor> doc : AppData.docs.entrySet()) {
			doc.getValue().getAppts(d);
		}
		
	}

	public static void noticeBoardMenu(Scanner sc) {
		int in = 0;
		System.out.println("Notice Board Options");
		System.out.println(" 1 View Notice Board\n 2 Add Notice\n 0 Go Back");
		in = sc.nextInt();
		sc.nextLine();
		switch(in) {
		case 1:{
			for(int i=0; i<AppData.notices.size(); i++) {
				
				Notice notice = AppData.notices.get(i);
				
				System.out.println(notice.show());
				
			}
			System.out.println();
			noticeBoardMenu(sc);
			break;
		}
		case 2:{
			scanNotice(sc,null,1);
			noticeBoardMenu(sc);
			break;
		}
		case 0:
			adminMenu(sc);
			break;
		}	
	}
	
	public static void medicineStoreMenu(Scanner sc) {
		System.out.println("Medicine Store");
		System.out.println(" 1 View Medicines\n 2 Add Medicines\n 0 Go Back");
		int in;
		in = sc.nextInt();
		sc.nextLine();
		
		switch(in) {
		case 1:{
			for(Map.Entry<Integer, Medicine> med : AppData.inventory.entrySet()) {
				System.out.println(med.getValue().toString());
			}
			System.out.println();
			medicineStoreMenu(sc);
			break;
		}
		case 2:{
			scanMedicine(sc,null,1);
			
			medicineStoreMenu(sc);
			break;
		}
		case 3:{
			adminMenu(sc);
			break;
		}
		}
	}
	
	
	
	public static void scanNotice(Scanner sc, String db, int i){
		try
		 {
		 
		 String file = "";
		 if(i==1) {
			 System.out.println("Enter filename:");
			 file = sc.nextLine();
		 }
		 else {
			 file = db;}

		 BufferedReader br = new BufferedReader( new FileReader(file));
		 String strLine = "";
		 StringTokenizer st = null;
		 int lineNumber = 0, tokenNumber = 0;
		 
		 while( (strLine = br.readLine()) != null){
			 lineNumber++;
			 st = new StringTokenizer(strLine, ",");
			 int docID = Integer.parseInt(st.nextToken());
			 String name = st.nextToken();
			 String consultationType = st.nextToken();
			 String days = st.nextToken();
			 	StringTokenizer st0 = new StringTokenizer(days,";");
			 	DayOfWeek[] daysAvailable = new DayOfWeek[st0.countTokens()];
			 	int count = -1;
			 	while(st0.hasMoreTokens()) {
			 		count = count+1;
			 		daysAvailable[count] = parseDay(st0.nextToken());
			 	}
			 String time = st.nextToken();
			 StringTokenizer st1 = new StringTokenizer(time,";");
			 	String time1 = st1.nextToken();
			 		StringTokenizer st11 = new StringTokenizer(time1,":");
			 		LocalTime startTime = LocalTime.of(Integer.parseInt(st11.nextToken()), Integer.parseInt(st11.nextToken()));
			 	String time2 = st1.nextToken();
			 		StringTokenizer st22 = new StringTokenizer(time2,":");
			 		LocalTime endTime = LocalTime.of(Integer.parseInt(st22.nextToken()), Integer.parseInt(st22.nextToken()));	
			 Notice notice = new Notice(new Doctor(docID,name,consultationType,daysAvailable,startTime,endTime));
			 

			 if(i==1) notice.writeToFile(strLine);
			 
		 
		}
		 
		 br.close();
		 if(i==1) System.out.println("Added notices");
		 if(i==0)System.out.println("Loading notices");
		}
		catch(Exception e){
			if(i==1)System.out.println("Exception while reading csv file: " + e); 
		}
		
		
		
	}
	
	public static DayOfWeek parseDay(String day) {
		DayOfWeek d=null;
		switch(day){
		case "M":
			d=DayOfWeek.MONDAY;
			break;
		case "T":
			d=DayOfWeek.TUESDAY;
			break;
		case "W":
			d=DayOfWeek.WEDNESDAY;
			break;
		case "Th":
			d=DayOfWeek.THURSDAY;
			break;
		case "F":
			d=DayOfWeek.FRIDAY;
			break;
		case "Sa":
			d=DayOfWeek.SATURDAY;
			break;
		case "Su":
			d=DayOfWeek.SUNDAY;
			break;
		}
		return d;
	}
	
	public static void scanMedicine(Scanner sc,String db,int i) {
		try{
			
			String file = "";
			if(i==1) {
				System.out.println("Enter file:"); 
				file = sc.nextLine();
			}
			else {file = db;}
	
			 BufferedReader br = new BufferedReader( new FileReader(file));
			  String strLine = "";
			 StringTokenizer st = null;
			 int lineNumber = 0, tokenNumber = 0;
			 
			 while( (strLine = br.readLine()) != null)
			 {
				 lineNumber++;
				 
				 st = new StringTokenizer(strLine, ",");
				 
				
		
				 tokenNumber++;
		
				 int medID = Integer.parseInt(st.nextToken());

				 String name = st.nextToken();

				 int price = Integer.parseInt(st.nextToken());
				 int quantity = Integer.parseInt(st.nextToken());
				 
//				 tokenNumber = 0;
				 Medicine med = new Medicine(name,medID,price,quantity);
				 if(i==1) med.writeToFile(strLine);
			 }
			 br.close();
			 if(i==1) System.out.println("Added medicines");
			 else System.out.println("Loading medicines");
		 }
		 catch(Exception e){
			 if(i==1)System.out.println("Exception while reading csv file: " + e); 
		 }
		
	}
	public static void studentMenu(Scanner sc,Student stud) {
		System.out.println("Menu:");
		System.out.println(" 1 View Notice Board\n 2 Book appointment\n 3 Purchase Medicine\n 4 Check Dues\n 0 Quit");
		int in = sc.nextInt();
		sc.nextLine();
		switch(in) {
		case 1:{
			for(int i=0; i<AppData.notices.size(); i++) {
				
				Notice notice = AppData.notices.get(i);
				
				System.out.println(notice.show());
				
			}
			System.out.println();
			studentMenu(sc,stud);
			break;
		}
		case 2:{
			scanAppointment(sc,null,1);
			studentMenu(sc,stud);
			
			break;
			
		}
		case 3:{
			scanPurchase(sc);
			studentMenu(sc,stud);
			break;
		}
		case 4:{
			stud.checkDues();
			studentMenu(sc,stud);
			break;
		}
		case 0:
			System.exit(0);
		}
	}
	
	public static void studentReg(Scanner sc) {
		System.out.println("L Login\nS Sign Up\nQ Quit");
		String in = sc.nextLine();
		switch(in) {
		case "L":{
			studentLogin(sc);
			break;
		}
		case "S":{
			scanStudent(sc,null,1);
			studentReg(sc);
			break;
		}
		case "Q": System.exit(0);
		}
	}
	public static void scanStudent(Scanner sc,String db,int i){
		
		 try
		 {
		 
		 String file = "";
		 if(i==1) {
			 System.out.println("Enter file:");
			 file = sc.nextLine();
		 }
		 else {
			 file = db;
		 }
		 
		 BufferedReader br = new BufferedReader( new FileReader(file));
		  String strLine = "";
		 StringTokenizer st = null;
		 
		 int lineNumber = 0, tokenNumber = 0;
		 
		 while( (strLine = br.readLine()) != null)
		 {
			 lineNumber++;
			 st = new StringTokenizer(strLine, ",");
			 
			 
			 tokenNumber++;
			 		
			 String name = st.nextToken();
			 int studID = Integer.parseInt(st.nextToken());
			 String email = st.nextToken();
			 String mobile = st.nextToken();
			
			 try {
				 if(!valid_email(email)) throw new Exception("Invalid email");
				 Student student = new Student(name,studID,email,mobile);
				 if(i==1) student.writeToFile(strLine);
			 }catch(Exception e){
				 System.out.println("Invalid email for "+studID+" "+name);
			 }
		 
			 

		 
		 }
		 br.close();
		 if(i==1) System.out.println("Added successfully");
		 else System.out.println("Loading students");
		 
		 
		 }
		 catch(Exception e)
		 {
		 if(i==1)System.out.println("Exception while reading csv file: " + e); 
		 }
	}
	
	public static Boolean valid_email(String BITS_email)
    {
        ///
     Pattern p = Pattern.compile("^[p|f](2018|2019|2020|2021|2022)[0-9]{4}(@pilani.bits-pilani.ac.in)");
     Matcher m = p.matcher(BITS_email);
    return (m.find() && m.group().equals(BITS_email));
    }
	
	public static void studentLogin(Scanner sc) {
		System.out.println("Enter id:");
		int in = sc.nextInt();
		sc.nextLine();
		try {
			Student student = AppData.studs.get(in);
			student.start();
		}catch(NullPointerException e) {
			System.out.println("Student not in database\nSign up");
			studentReg(sc);
		}
	}
	
	public static void scanAppointment(Scanner sc,String db, int i) {
		 try
		 {
		 
		 String file = "";
		 if(i==1) {
			 System.out.println("Enter File:");
			 file = sc.nextLine();
		 }
		 else {
			 file = db;
		 }

		 BufferedReader br = new BufferedReader( new FileReader(file));
		  String strLine = "";
		 StringTokenizer st = null;
		 int lineNumber = 0, tokenNumber = 0;
		 
		 while( (strLine = br.readLine()) != null){
			 lineNumber++;
			 
			 st = new StringTokenizer(strLine, ",");
			 
			 long timestamp = Long.parseLong(st.nextToken());
			 int studID = Integer.parseInt(st.nextToken());
			 int docID = Integer.parseInt(st.nextToken());
			 
			 
			 String day = st.nextToken();
			 DayOfWeek d = parseDay(day);	
			 
			 
		 	 String time1 = st.nextToken();
	 		 StringTokenizer st11 = new StringTokenizer(time1,":");
	 		 LocalTime startTime = LocalTime.of(Integer.parseInt(st11.nextToken()), Integer.parseInt(st11.nextToken()));
		 	 
	 		 Student student = AppData.studs.get(studID);
	 		 
	 		 try{
	 			 if(i==1) student.takeAppointment(timestamp, docID, d, startTime,strLine);
	 			 else student.takeAppointment(timestamp, docID, d, startTime,null);
	 			 
	 		 } catch(Exception e) {
	 			 System.out.println(e.getMessage());
	 		 }
		 }
		 br.close();
		 if(i==1) System.out.println("Booked Appointment");
		 else System.out.println("Loading appointments");
		 
		}
		 catch(Exception e)
		 {
			 if(i==1)System.out.println("Exception while reading csv file: " + e); 
		 }
	}
	
	public static void scanPurchase(Scanner sc) {
		 try
		 {
		 System.out.println("Enter file:");
		 String file = sc.nextLine();

		 BufferedReader br = new BufferedReader( new FileReader(file));
		  String strLine = "";
		 StringTokenizer st = null;
		 int lineNumber = 0, tokenNumber = 0;
		 
		 while( (strLine = br.readLine()) != null)
		 {
		 lineNumber++;
		 
		 st = new StringTokenizer(strLine, ",");
		 
		 

		 tokenNumber++;

		 	 int studID = Integer.parseInt(st.nextToken());
			 int medID = Integer.parseInt(st.nextToken());
			 int quantity = Integer.parseInt(st.nextToken());
			 String p_mode = st.nextToken();
			 try {
				 
				 AppData.studs.get(studID).purchaseMeds(medID, quantity, p_mode);
				 
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 
		 }
		 br.close();
		 
		 
		 }
		 catch(Exception e)
		 {
		 System.out.println("Exception while reading csv file: " + e); 
		 }
	}
}

