package fields;

import entities.Player;

abstract public class Ownable extends OurField{
	private int price;
	private Player owner;
	private int fieldPossition;
	abstract public int getRent();
	private boolean pawned = false;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public boolean isOwned(){
		if(owner == null){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean isOwner(Player player){
		if(player==this.owner){
			return true;
		}
		else{
			return false;
		}
	}

	public int getFieldPossition() {
		return fieldPossition;
	}

	public void setFieldPossition(int fieldPossition) {
		this.fieldPossition = fieldPossition;
	}
	
	public void setPawned(){
		pawned = true;
	}
	public void setNotPawned(){
		pawned = false;
	}
	public boolean getPawnedStatus(){
		return pawned;
	}
}
