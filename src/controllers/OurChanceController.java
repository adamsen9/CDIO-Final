package controllers;
import   java.lang.Math.*;
import java.util.Random;

import entities.Die;
import entities.Player;
import fields.OurField;
import entities.OurChanceCard;


	public class OurChanceController extends FieldController{
		OurChanceCard[] liste = new OurChanceCard[5];
		private Random rnd = new Random();
		int FaceValue;
		
	public OurChanceController() {
		liste[0] = new OurChanceCard("Ryk 2 felter tilbage", "ryk", -2);
		liste[1] = new OurChanceCard("Ryk 2 felter frem","ryk", 2);
		//liste[2] = new OurChanceCard("jeg er kort nr 3", "betal", 3000);
		//liste[3] = new OurChanceCard("jeg er kort nr 4", "ryk", 4);
		//liste[4] = new OurChanceCard("jeg er kort nr 5", "betal", 5000);	
	}
	
	public void putCardOnStack(GUIManager display){
		FaceValue = (int) rnd.nextInt(1);
		display.setcard(liste[FaceValue].getText() + ". " +  liste[FaceValue].getEffect() +  " " + liste[FaceValue].getAmount());
		
	}
	
	
	@Override
	public boolean[] landOnField(Player player, GUIManager display,
			OurField field, Die die) {
		boolean[] returnValue = {true, false};
		switch (liste[FaceValue].getText()) {
		case ("Ryk 2 felter tilbage"):
			display.sendMessage(liste[FaceValue].getText());
			display.movePlayer(field.getFieldId(), field.getFieldId()+liste[FaceValue].getAmount(), player.getName());
			player.move(liste[FaceValue].getAmount());
			break;
			
		case ("Ryk 2 felter frem"):
			display.sendMessage(liste[FaceValue].getText());
			display.movePlayer(field.getFieldId(), field.getFieldId()+liste[FaceValue].getAmount() , player.getName());
			player.move(liste[FaceValue].getAmount());
			break;
			
			


		}
		
		return returnValue;
	}
	
}
