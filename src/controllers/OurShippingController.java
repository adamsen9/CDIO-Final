package controllers;

import entities.Die;
import entities.Player;
import fields.OurShipping;
import fields.OurField;

public class OurShippingController extends OwnableController {
	private OurShipping fleet;
	public OurShippingController(){
		
	}

	@Override
	public boolean[] landOnField(Player player, GUIManager display, OurField field, Die die) {
		fleet = (OurShipping) field;
		boolean[] returnValue = {true, false};
		if(fleet.isOwned()){
			if(!isOwner(player, fleet)){
					display.sendMessage(player.getName() + " er landet på " + fleet.getName() + " og skal betale " + fleet.getRent() + " kroner.");
					//Her overføres penge fra spilleren der landte på 
					returnValue[0] = payRent(player, fleet);
					return returnValue;
			}
			
		} else{
			if(display.chooseToBuyFleet(fleet.getName(), fleet.getPrice(), player.getName()) == "Køb"){
				if(buyField(player, display, fleet)){
					player.addNumberOfFleetsOwned();
				}
			}
		}
		return returnValue;
	}
}
