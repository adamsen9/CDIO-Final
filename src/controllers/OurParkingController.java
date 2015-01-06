package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;
import fields.OurParking;

public class OurParkingController extends FieldController{
	private OurParking refuge;
	public OurParkingController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		refuge = (OurParking) field;
		display.sendMessage(player.getName() + " landede på " + refuge.getName() + " og modtager " + refuge.getBonus());
		player.deposit(refuge.getBonus());
		return true;
	}
}
