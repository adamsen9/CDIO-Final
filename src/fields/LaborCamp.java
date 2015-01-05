package fields;

public class LaborCamp extends Ownable{
	private int baseRent = 100;
	
	public LaborCamp(int price, String name, int id, int fieldPossition){
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
		super.setType("LaborCamp");
		super.setFieldPossition(fieldPossition);
	}
	
	@Override
	public int getRent() {
		// Ikke helt sikker på denne her metode i den her class.
		if (super.getOwner() != null){
			return super.getOwner().getNumberOfLaborCampsOwned()-1;
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
