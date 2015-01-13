package controllers;
import entities.*;
import fields.*;

public class OurStreetController extends OwnableController {
	private OurStreet street;
	
	public OurStreetController() {
		
	}

	@Override
	public boolean[] landOnField(Player player, GUIManager display, OurField field, Die die) {
		street = (OurStreet) field;
		boolean[] returnValue = {true, false};
		if(street.getPawnedStatus()){
			display.sendMessage("Denne grund er pantsat");
			return returnValue;
		}
		if(street.isOwned()) {
			if(isOwner(player, street)) {
				display.sendMessage("Du er ejer af denne grund");
			} else {
				display.sendMessage(player.getName() + " er landet på " + street.getName() + ". Grunden er ejet, du skal betale " + street.getRent() + " i leje.");
				returnValue[0] =  payRent(player, street);
				return returnValue;
			}
			
		} else {
			String choice = display.chooseToBuyStreet(street.getName(), street.getPrice(), player.getName(), street.getRent());
			if(choice == "Køb"){
				buyField(player, display, street);
			} else if(choice == "Afslå") {
				//Gennemfør auktion
			}
		}
		return returnValue;
	}
}
