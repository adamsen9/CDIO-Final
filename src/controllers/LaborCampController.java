package controllers;

import entities.Die;
import entities.Player;
import fields.LaborCamp;
import fields.OurField;

public class LaborCampController extends OwnableController{
	private LaborCamp laborCamp;
	public LaborCampController() {
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		laborCamp = (LaborCamp) field;
	    if(laborCamp.isOwned()){
	    	if(!isOwner(player, laborCamp)){
	    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvilket felt han har landt på og hvad der skal ske
	    			display.sendMessage(player.getName() + "er landet på " + laborCamp.getName() + "og skal slå med tegningerne. Der betales 100*øjne*ejet Labor Camps.");
	    			//Jeg slår med 2 terninger, og viser dette i grafikken
	    			int dieOne = die.roll();
	    			int dieTwo = die.roll();
	    			display.setDice(dieOne, dieTwo);
	    			//Jeg udregner hvad spilleren skal betale til ejeren. Dette er øjne*ejet*100
	    			int rent = laborCamp.getOwner().getNumberOfLaborCampsOwned()*100*(dieOne+dieTwo);
	    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvad han slog og hvad han skal betale
	    			display.sendMessage("Du har slået " + (dieOne + dieTwo) + ", og skal betale " + rent + ".");
	    			//Jeg sender penge fra den aktive spiller til ejeren af feltet. Jeg ved han har penge nok da dette var condition til at komme herned
	    			return payRent(player, laborCamp, (dieOne+dieTwo));
	    	}
	    }else{
	    	if(display.chooseToBuyLaborCamp(laborCamp.getName(), laborCamp.getPrice(), player.getName()) == "Køb"){
	    		if(buyField(player, display, laborCamp)){
	    			player.addNumberOfLaborCamps();
	    		}
	    	}
	    }
		return true;
	}
}
