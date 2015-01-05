package fields;

public class Territory extends Ownable{
	private int rent;
	
	public Territory(int rent, int price, String name, int id, int fieldPossition){
		this.rent = rent;
		super.setPrice(price);;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Territory");
		super.setFieldPossition(fieldPossition);
	}
	
	public void setRent(int rent){
		this.rent = rent;
	}
	
	@Override
	public int getRent() {
		return rent;
	}
	
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getOwner().getName() + " fieldId: " + super.getFieldId();
		return s;
	}
}
