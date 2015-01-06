package fields;

public class OurJail extends OurField{
	private boolean visit;
	public OurJail(String name, int fieldId, boolean visit){
		this.visit = visit;
		super.setName(name);
		super.setFieldId(fieldId);
		super.setType("Jail");
	}
}
