package fields;

public class OurBrewery extends Ownable{
	private int baseRent = 100;
	
	public OurBrewery(int price, String name, int id, int fieldPossition){
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
		super.setType("Brewery");
		super.setFieldPossition(fieldPossition);
	}
	
	@Override
	public int getRent() {
		// Ikke helt sikker på denne her metode i den her class.
		if (super.getOwner() != null){
			return super.getOwner().getNumberOfBreweryOwned()-1;
		}
			return 0; // returner 0 hvis grunden ikke ejes af nogle.
	}

	public int getBaseRent() {
		return baseRent;
	}

	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}
	
	public String toString() {
		String s = "Price: " + super.getPrice() + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		return s;
	}
}
