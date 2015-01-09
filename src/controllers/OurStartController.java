package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;


public class OurStartController extends FieldController{

	@Override
	public boolean[] landOnField(Player player, GUIManager display,
			OurField field, Die die) {
		// TODO Auto-generated method stub
		boolean[] returnValue = {true, false};
		return returnValue;
	}

}
