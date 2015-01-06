package fields;

import java.awt.Color;

public class OurStreet extends Ownable{
	private int[] rent = new int[6];
	private int housePrice;
	private Color color;
	private int numberOfHouses;
	
	public OurStreet(int[] rent, int price, int housePrice, String name, int id, int fieldPossition){
		this.rent = rent;
		this.setHousePrice(housePrice);
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
		super.setType("Street");
		super.setFieldPossition(fieldPossition);
	}
	
	public void setRent(int rent, int index){
		if (index <= 5) {
			this.rent[index] = rent;
		} else {
			System.out.println("forkert index.");
			return;
		}
		
	}
	
	@Override
	public int getRent() {
		return rent[numberOfHouses];
	}
	
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getOwner().getName() + " fieldId: " + super.getFieldId();
		return s;
	}

	public int getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
