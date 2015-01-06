package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;
import fields.OurJail;

public class OurJailController extends FieldController{
	public OurJail jail;
	
	
	public void PutInJail(Player player){
		
		//tjek om det virker ved blackbox test senerer
		player.move(19);
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
			PutInJail(player);
			return true;
		}
	}

}
