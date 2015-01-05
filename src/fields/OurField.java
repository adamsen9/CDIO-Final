package fields;

abstract public class OurField {
	private String name;
	private int fieldId;
	private String type;
	
	public String getName(){
		return this.name;
	}
	
	public int getFieldId(){
		return this.fieldId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
