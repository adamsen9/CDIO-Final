package entities;


public class OurChanceCard {
	private String text;
	private String effect;
	private int amount;
	
	public OurChanceCard(String text, String effect, int amount){
		this.setText(text);
		this.setEffect(effect);
		this.setAmount(amount);
		
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
