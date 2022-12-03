public class Medicine {
private String name;
private int medID;
private int price;
private int quantity;

Medicine(String name, int ID, int price, int quantity){
	this.name = name;
	this.medID = ID;
	this.price = price;
	this.quantity = quantity;
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
}