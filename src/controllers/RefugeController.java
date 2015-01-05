package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;
import fields.OurRefuge;

public class RefugeController extends FieldController{
	private OurRefuge refuge;
	public RefugeController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		refuge = (OurRefuge) field;
		display.sendMessage(player.getName() + " landede på " + refuge.getName() + " og modtager " + refuge.getBonus());
		player.deposit(refuge.getBonus());
		return true;
	}
}
