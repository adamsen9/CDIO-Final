package fields;

public class OurStreet extends Ownable{
	private int[] rent = new int[6];
	
	public OurStreet(int[] rent, int price, String name, int id, int fieldPossition){
		this.rent = rent;
		super.setPrice(price);;
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
		return rent;
	}
	
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getOwner().getName() + " fieldId: " + super.getFieldId();
		return s;
	}
}
