import java.time.*;
import java.util.*;
import java.io.*;


public class Main extends Thread{
	public static void main(String[] args) {
		
		
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
			case "B":
				//state = State.STUDENT_MENU;
				studentMenu(sc);
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
//		Admin admin = new Admin();
//		admin.start();
		System.out.println("Welcome Admin");
		System.out.println("Menu: \n 1 View Student records\n 2 Today's appointments\n 3 Update Notice Board \n 4 Medicine Store\n 0 Quit");
		in = sc.nextInt();
		sc.nextLine();
		switch(in){
		case 1:
			for(Map.Entry<Integer,Student> stud : AppData.studs.entrySet()) {
				System.out.println(stud.getValue());
			}
			adminMenu(sc);
			break;
		case 2:
			System.out.println("Today's appointments");
			String day = sc.nextLine();
			DayOfWeek d = parseDay(day);
			
		
			for(Map.Entry<Integer,Doctor> doc : AppData.docs.entrySet()) {
				Iterator<LocalTime> i = doc.getValue().getAppts(d).iterator();
				while(i.hasNext()) {
					i.next().toString();
				}
//				System.out.println(doc.getValue().getAppts(d).);
			}
			adminMenu(sc);
			break;
		case 3:
			System.out.println("Notice Board Options");
			System.out.println(" 1 View Notice Board\n 2 Add Notice");
			noticeBoardMenu(sc);
			
			break;
		case 4:
			System.out.println("Medicine Store");
			System.out.println(" 1 View Medicines\n 2 Add Medicines");
			//System.out.println(admin.adminThread.isAlive());
			medicineStoreMenu(sc);
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
			
		}
		
	}
	
	public static void noticeBoardMenu(Scanner sc) {
		int in = 0;
		in = sc.nextInt();
		sc.nextLine();
		switch(in) {
		case 1:{
			for(int i=0; i<AppData.notices.size(); i++) {
				Notice notice = AppData.notices.get(i);
				notice.show();
			}
			noticeBoardMenu(sc);
			break;
		}
		case 2:{
			scanNotice(sc);
			noticeBoardMenu(sc);
			break;
		}
		case 0:
			adminMenu(sc);
			break;
		}	
	}
	
	public static void medicineStoreMenu(Scanner sc) {
		
	}
	
	public static void studentMenu(Scanner sc) {
		System.out.println("in studentMenu");
	}
	
	public static void scanNotice(Scanner sc){
		try
		 {
		 System.out.println("Enter filename:");
		 String file = sc.nextLine();

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
			 //DayOfWeek[] daysAvailable = st.nextToken();
			 
			 String days = st.nextToken();
			 	StringTokenizer st0 = new StringTokenizer(days,";");
			 	DayOfWeek[] daysAvailable = new DayOfWeek[st0.countTokens()];
			 	int count = -1;
			 	while(st0.hasMoreTokens()) {
			 		count = count+1;
			 		daysAvailable[count] = parseDay(st0.nextToken());
			 	}
			 String time1 = st.nextToken();
			 	StringTokenizer st1 = new StringTokenizer(time1,":");
			 	LocalTime startTime = LocalTime.of(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
			 String time2 = st.nextToken();
			 	StringTokenizer st2 = new StringTokenizer(time2,":");
			 	LocalTime endTime = LocalTime.of(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
		//tokenNumber = 0;
			 new Notice(new Doctor(docID,name,consultationType,daysAvailable,startTime,endTime));
			 return;
		 
		}
		 
		 
		}
		catch(Exception e){
			System.out.println("Exception while reading csv file: " + e); 
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
}
