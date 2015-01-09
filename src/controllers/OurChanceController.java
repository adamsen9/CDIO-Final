package controllers;
import   java.lang.Math.*;
import java.util.Random;

import entities.Die;
import entities.Player;
import fields.OurField;
import entities.OurChanceCard;
import controllers.OurJailController;


	public class OurChanceController extends FieldController{
		OurChanceCard[] liste = new OurChanceCard[22];
		private Random rnd = new Random();
		int FaceValue;
		OurJailController JailTime = new OurJailController();
		
	public OurChanceController() {
		liste[0] = new OurChanceCard("Ryk 2 felter tilbage.", "ryk", -2);
		liste[1] = new OurChanceCard("Ryk 2 felter frem.","ryk", 2);
		liste[2] = new OurChanceCard("Der er kommet voldsomme renter på din vaffelis, betal 1500.", "betal", 1500);
		liste[3] = new OurChanceCard("DU HAR VUNDET I LOTTO, TILLYKE, modtag 1.000.", "modtag", 1000);
		liste[4] = new OurChanceCard("Du har stjålet en cykel og skal i fængsel.", "fængsel", 0);
		liste[5] = new OurChanceCard("Du har fundet en sjælden fugl og sælger den, modtag 500.", "modtag", 500);
		liste[6] = new OurChanceCard("Du var ude og handle og fandt en forladt pung, modtag 600.", "modtag", 600);
		liste[7] = new OurChanceCard("Du skulle alligevel ikke adoptere en lille asiater, modtag 1.000.", "modtag", 1000);
		liste[8] = new OurChanceCard("Du sælger din ene nyre til en velhavende kineser, modtag 2.000.", "modtag", 2000);
		liste[9] = new OurChanceCard("Du er eneste arving til din griske, gamle gnier af en tante, modtag 2.000.", "modtag", 2000);
		liste[10] = new OurChanceCard("Ryk frem til Strandvejen.","Strandvejen", 0);
		liste[11] = new OurChanceCard("Ryk frem til Rådhuspladsen.","Rådhuspladsen", 0);
		liste[12] = new OurChanceCard("Ryk frem til Østerbrogade.","Østerbrogade", 0);
		liste[13] = new OurChanceCard("Ryk frem til Start.","Start", 0);
		liste[14] = new OurChanceCard("Ryk frem til Gl. Kongevej.","Gl. Kongevej", 0);
		liste[15] = new OurChanceCard("Ryk frem til Vimmelskaftet.","Vimmelskaftet", 0);
		liste[16] = new OurChanceCard("Ryk frem til Kongens Nytorv.","Kongens Nytorv", 0);
		liste[17] = new OurChanceCard("Du skal til fødselsdag, betal 500.", "betal", 500);
		liste[18] = new OurChanceCard("Du køber en hundehvalp, betal 500.", "betal", 500);
		liste[19] = new OurChanceCard("Du skal have fjernet et voldsomt modermærke, betal 1.000.", "betal", 1000);
		liste[20] = new OurChanceCard("Der er kommet en ny Iphone, du bare må have, betal 2.000.", "betal", 2000);
		liste[21] = new OurChanceCard("Din søde tand driver dig til vanvid, slik og søde sager er den eneste udvej, betal 300.", "betal", 300);
		
	}
	
	public void putCardOnStack(GUIManager display){
		FaceValue = (int) rnd.nextInt(22);
		display.setcard(liste[FaceValue].getText());
		
	}
	
	
	@Override
	public boolean[] landOnField(Player player, GUIManager display,
			OurField field, Die die) {
		boolean[] returnValue = {true, false};
		switch (liste[FaceValue].getEffect()) {
			
		case ("betal"):
			display.sendMessage(liste[FaceValue].getText());
			//vi tager dine penge, aktive spiller
			returnValue[0] = player.withdraw(liste[FaceValue].getAmount());
			// vi har returnValue med her, da han teknisk set godt kan tabe ved at betale.
			break;
			
		case ("ryk"):
			display.sendMessage(liste[FaceValue].getText());
			display.movePlayer(field.getFieldId(), field.getFieldId()+liste[FaceValue].getAmount(), player.getName());
			player.move(liste[FaceValue].getAmount());
			returnValue[1] = true;
			break;
			
		case ("modtag"):
			display.sendMessage(liste[FaceValue].getText());
			//vi giver dig penge, aktive spiller
			player.deposit(liste[FaceValue].getAmount());
			break;
			
			
		case ("fængsel"):
			display.sendMessage(liste[FaceValue].getText());
			JailTime.PutInJail(player, display);
			break;
			
		case ("Strandvejen"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(20, player.getField());
			display.movePlayer(field.getFieldId(),20, player.getName());			
			returnValue[1] = true;
			break;
			
		case ("Rådhuspladsen"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(40, player.getField());
			display.movePlayer(field.getFieldId(),40, player.getName());			
			returnValue[1] = true;
			break;
			
		case ("Østerbrogade"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(40, player.getField());
			display.movePlayer(field.getFieldId(),1, player.getName());
			returnValue[1] = true;
			break;
			
		case ("Start"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(1, player.getField());
			display.movePlayer(field.getFieldId(),1, player.getName());
			returnValue[1] = true;
			break;
			
		case ("Gl. Kongevej"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(15, player.getField());
			display.movePlayer(field.getFieldId(),15, player.getName());
			returnValue[1] = true;
			break;
			
		case ("Vimmelskaftet"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(33, player.getField());
			display.movePlayer(field.getFieldId(),33, player.getName());
			returnValue[1] = true;
			break;
			
		case ("Kongens Nytorv"):
			display.sendMessage(liste[FaceValue].getText());
			player.setField(28, player.getField());
			display.movePlayer(field.getFieldId(),28, player.getName());			
			returnValue[1] = true;
			break;

		}
		putCardOnStack(display);
		return returnValue;
	}
	
}
