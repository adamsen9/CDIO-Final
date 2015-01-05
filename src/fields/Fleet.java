package fields;

public class Fleet extends Ownable{

	private int[] rent = {500,1000,2000,4000};
	
	public Fleet(int price, String name, int id, int possition){
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
		super.setType("Fleet");
		super.setFieldPossition(possition);
		
	}
	
	public void setRent(int rent, int indexOfRent){
		this.rent[indexOfRent] = rent;
	}
	
	@Override
	public int getRent() {
		if (super.getOwner() != null){
			return this.rent[super.getOwner().getNumberOfFleetsOwned()-1];
		}
			return 0; // returner 0 hvis grunden ikke ejes af nogle.
	}
	
	public String toString() {
		String s = "Rent: " + rent[0] + ", " + rent[1] + ", " + rent [2] + ", " + rent[3] + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		
		return s;
	}
}
