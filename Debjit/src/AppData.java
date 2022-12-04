import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class AppData {
	//ArrayList <Medicine> inventory = new ArrayList<>();
	static Map <Integer,Doctor> docs = new HashMap<Integer,Doctor>();
	//HashMap <Student, Integer> dues = new HashMap<>();
	static Map<Integer,Student> studs = new HashMap<Integer,Student>();
	static Map <Integer,Medicine> inventory = new HashMap<Integer,Medicine>();
	static ArrayList <Notice> notices = new ArrayList<>();
	static HashMap <Student, Integer> dues = new HashMap<Student,Integer>();
	static int sale;
	
	public static void writeToFile(int sales) {
		try
        {
        FileWriter myWriter = new FileWriter("resources/SALES.txt",false);
        myWriter.write(sales+"\n");
        myWriter.close();
        //System.out.println("File created");
        }
        catch (IOException e)
        {
          System.out.println("An error occurred.");
        }
	}
	public static void scanSales(String file, int i) {
		try
		 {
		 BufferedReader br = new BufferedReader( new FileReader(file));
		 String strLine = br.readLine();
		 sale = Integer.parseInt(strLine);
		 
		  
		 br.close();
		
		 System.out.println("Loading sales");
		 
		}
		 catch(Exception e)
		 {
			 if(i==1)System.out.println("Exception while reading csv file: " + e); 
		 }
	}
}
