package controllers;
import   java.lang.Math.*;
import java.util.Random;

import entities.Die;
import entities.Player;
import fields.OurField;
import entities.OurChanceCard;


public class OurChanceController extends FieldController{
	OurChanceCard[] liste = new OurChanceCard[5];
public OurChanceController() {
	
		
	}
	
	@Override
	public boolean landOnField(Player player, GUIManager display,
			OurField field, Die die) {
		// TODO Auto-generated method stub
	
		
		return false;
	}
	
}
