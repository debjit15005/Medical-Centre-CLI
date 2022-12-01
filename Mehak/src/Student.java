package medc;

import java.util.Scanner;

public class Student {
	private String ID;
	private String name;
	private String email;
	private String mobile;
	private AppData appData;
	
	Scanner in = new Scanner(System.in);
	
	
	Student (String name, String ID, String email, String mobile ){
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.ID = ID;
	}
	
	public void checkNoticeBoard() {
		for (Notice notice: appData.notices) {
			notice.showNotice();
		}
	}
	
	public void purchaseMeds(String name, int quant) {
		Medicine m = null;
		for(Medicine med: appData.inventory) {
			if (med.getName() == name) {
				m = med;
				break;
			}
		}
		if (m == null) System.out.println("Medicine unavailable");
		else {
			if (quant<= m.getQuantity()) {
				int bill = quant* m.getPrice();
				m.setQuantity(m.getQuantity() - quant);
				System.out.println("bill:" + bill + '\n' + "How would you like to pay?");
				String s = in.nextLine();
				if(s.compareToIgnoreCase("Pay Now") == 0)  
					System.out.println("Transaction successful!");
				if(s.compareToIgnoreCase("Pay Later") == 0) {
					appData.dues.computeIfPresent(this,(k,v) -> v + bill);
					if (appData.dues.get(this)!= null) appData.dues.put(this,bill);
					System.out.println("Added to your dues");
				}
			}
		}
		
	}
}
