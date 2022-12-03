import java.util.*;
public class Admin implements Runnable{
	Thread adminThread;
	private  boolean stopThread = false;
	
	
	public void start() {
		if(adminThread==null) {
			this.adminThread = new Thread(this,"admin");
			this.adminThread.start();
		}
	}
	public void run() {
		while(true) {
			if(stopThread) {System.out.println("Ending admin"); break;}
			//System.out.println("Admin running");
			Scanner adminScanner = new Scanner(System.in);
			int i = 0;
			while(i<1) {
				Main.adminMenu(adminScanner);
			}
			
		}
	}
	public void setStopThread(boolean stopThread) {
		this.stopThread = stopThread;
	}
	public void createNotice(Doctor D) {
		synchronized (AppData.notices) {
		AppData.notices.add(new Notice(D));
		}
	}
	
	public void deleteNotice(int noticeNum) {
		synchronized (AppData.notices) {
		AppData.notices.remove(noticeNum - 1);
		}
	}
	
	public void createDoctor() {
		
	}
	
	public void deleteDoctor() {
		
	}
	
	
	
	public void removeStudent() {
		
	}
	
	public void viewStudents() {
		
	}
	
	void checkAllDues() {
		System.out.println("All dues");
		for (Map.Entry<Student,Integer> entry : AppData.dues.entrySet()) 
            System.out.println( entry.getKey().getName() + ":  = " + entry.getValue());
		
		int netDues = 0;
		for (Map.Entry<Student,Integer> entry : AppData.dues.entrySet()) 
            netDues = netDues + entry.getValue();
		
		System.out.println("Net dues = " + netDues);
	}
	
	void netSales() {
		System.out.println("Net Sales: " + AppData.sale);
	}
		
}