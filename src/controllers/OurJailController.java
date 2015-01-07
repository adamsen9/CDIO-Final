package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;
import fields.OurJail;

public class OurJailController extends FieldController{
	public OurJail jail;
	
	
	public void PutInJail(Player player, GUIManager display){
		
		//tjek om det virker ved blackbox test senere
		display.sendMessage(player.getName() + " er taget til fange og sendt i fængsel");
		player.setField(11);
		display.movePlayer(31, 11, player.getName());
		player.setTimeInJail(0);
		player.setImPrisoned(true);
		
	}
	
	@Override
	public boolean landOnField(Player player, GUIManager display,
			OurField field, Die die) {
		jail = (OurJail) field;
		if (jail.getVisit()) {
			//Du er på besøg i fængsel 
			//kald på GUIManager
			return true;
		}
		else {
			PutInJail(player,display);
			return true;
		}
	}

}
