package controllers;

import entities.Die;
import entities.Player;
import fields.OurBrewery;
import fields.OurField;

public class OurBreweryController extends OwnableController{
	private OurBrewery brewery;
	public OurBreweryController() {
		
	}

	@Override
	public boolean[] landOnField(Player player, GUIManager display, OurField field, Die die) {
		brewery = (OurBrewery) field;
		boolean[] returnValue = {true, false};
	    if(brewery.isOwned()){
	    	if(!isOwner(player, brewery)){
	    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvilket felt han har landt på og hvad der skal ske
	    			display.sendMessage(player.getName() + "er landet på " + brewery.getName() + "og skal slå med tegningerne. Der betales 100*øjne*ejet Labor Camps.");
	    			//Jeg slår med 2 terninger, og viser dette i grafikken
	    			int dieOne = die.roll();
	    			int dieTwo = die.roll();
	    			display.setDice(dieOne, dieTwo);
	    			//Jeg udregner hvad spilleren skal betale til ejeren. Dette er øjne*ejet*100
	    			int rent = brewery.getOwner().getNumberOfBreweryOwned()*100*(dieOne+dieTwo);
	    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvad han slog og hvad han skal betale
	    			display.sendMessage("Du har slået " + (dieOne + dieTwo) + ", og skal betale " + rent + ".");
	    			//Jeg sender penge fra den aktive spiller til ejeren af feltet. Jeg ved han har penge nok da dette var condition til at komme herned
	    			returnValue[0] = payRent(player, brewery, (dieOne+dieTwo));
	    			return returnValue;
	    	}
	    }else{
	    	if(display.chooseToBuyBrewery(brewery.getName(), brewery.getPrice(), player.getName()) == "Køb"){
	    		if(buyField(player, display, brewery)){
	    			player.addNumberOfBrewery();
	    		}
	    	}
	    }
		return returnValue;
	}
}
