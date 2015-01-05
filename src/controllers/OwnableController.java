package controllers;

import entities.*;
import fields.*;

public abstract class OwnableController extends FieldController {
	
	public boolean buyField(Player player, GUIManager display, Ownable field){
		if(player.getBalance() >= field.getPrice()){
			player.withdraw(field.getPrice());
			player.addToInventory(field.getFieldPossition(), field.getPrice());
    		field.setOwner(player);
    		display.setOwner(player.getField(), player.getName());
    		return true;
    	}
    	else{
    		display.sendMessage("Du har ikke nok penge til at købe denne grund.");
    		return false;
    	}
	}
	
	public boolean isOwner(Player player, Ownable ownable) {
		if(player.getId() == ownable.getOwner().getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean payRent(Player player, Ownable ownable) {
		return player.transfer(ownable.getOwner(),ownable.getRent());
	}
	
	public boolean payRent(Player player, LaborCamp laborCamp, int sum){
		return player.transfer(laborCamp.getOwner(), laborCamp.getBaseRent()*laborCamp.getOwner().getNumberOfLaborCampsOwned()*sum);
	}
}
