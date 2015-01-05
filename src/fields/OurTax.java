package fields;

public class OurTax extends OurField{
	private int taxAmount;
	private int taxPercentage;
	private double taxRate = 0.1; 
	private int fieldPossition;
	
	public OurTax(int taxAmount, int taxRate, String name, int id, int fieldPossition){
		this.taxAmount = taxAmount;
		this.taxPercentage = taxRate;
		this.taxRate = (double) taxRate/100;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Tax");
		this.setFieldPossition(fieldPossition);
	}
	
	public void setTaxAmount(int taxAmount){
		this.taxAmount = taxAmount;
	}
	
	public void setTaxRate(double taxRate){
		this.taxRate = taxRate;
	}
	
	public void setTaxPercentage(int taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	public int getTaxAmount(){
		return this.taxAmount;
	}
	
	public double getTaxRate(){
		return this.taxRate;
	}
	
	public int getTaxPercentage() {
		return taxPercentage;
	}

	public int getFieldPossition() {
		return fieldPossition;
	}

	public void setFieldPossition(int fieldPossition) {
		this.fieldPossition = fieldPossition;
	}
	
	public String toString() {
		String s = "Tax Amount: " + taxAmount + " Tax Rate: " + taxRate + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		
		return s;
	}
	
}
