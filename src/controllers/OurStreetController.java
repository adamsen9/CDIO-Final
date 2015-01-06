package controllers;
import entities.*;
import fields.*;

public class OurStreetController extends OwnableController {
	private OurStreet territory;
	
	public OurStreetController() {
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		territory = (OurStreet) field;
		if(territory.isOwned()) {
			if(isOwner(player, territory)) {
				display.sendMessage("Du er ejer af denne grund");
			} else {
				display.sendMessage(player.getName() + " er landet på " + territory.getName() + ". Grunden er ejet, du skal betale " + territory.getRent() + " i leje.");
				return payRent(player, territory);
			}
			
		} else {
			if(display.chooseToBuyTerritory(territory.getName(), territory.getPrice(), player.getName(), territory.getRent()) == "Køb"){
				buyField(player, display, territory);
			}
		}
		return true;
	}
}
