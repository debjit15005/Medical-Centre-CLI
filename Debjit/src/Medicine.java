import java.io.FileWriter;
import java.io.IOException;

public class Medicine {
private String name;
private int medID;
private int price;
private int quantity;

Medicine(String name, int medID, int price, int quantity){
	this.name = name;
	this.medID = medID;
	this.price = price;
	this.quantity = quantity;
	AppData.inventory.put(this.medID, this);
	}

public String getName() {
	return name;
	}

public int getID() {
	return medID;
	}

public int getPrice() {
	return price;
	}

public int getQuantity() {
	return quantity;
	}


public void setQuantity(int quantity) {
	this.quantity = quantity;
	}

public String toString() {
	return String.format(this.medID + " %-10s " + "%-4d " + "%-4d" , this.name, this.price, this.quantity);
	}
public void writeToFile(String string) {
	try
    {
    FileWriter myWriter = new FileWriter("resources/medicineDB.txt",true);
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