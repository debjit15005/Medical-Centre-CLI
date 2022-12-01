package medc;

import java.util.ArrayList;
import java.util.HashMap;

public class AppData {
	private static AppData appData = null;
	ArrayList <Medicine> inventory = new ArrayList<>();
	ArrayList <Notice> notices = new ArrayList<>();
	HashMap <Student, Integer> dues = new HashMap<>();
	
	private AppData() {
	}

}