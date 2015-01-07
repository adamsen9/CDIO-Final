package controllers;
import java.util.ArrayList;

import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	public boolean Auction(GUIManager GUI, Player[] auctioneers, Ownable field, boolean bankruptcy){
		if(bankruptcy == true) {
			GUI.sendMessage("Der bliver afholdt tvangsauktion af feltet" + field.getName() + ".");
		} else {
			GUI.sendMessage("Der bliver afholdt auktion af feltet " + field.getName() + ".");	
		}
		GUI.sendMessage("Der bliver afholdt auktion af feltet " + field.getName() + ".");	
		
		int bid = field.getPrice();
		
		if(field.getPawnedStatus()) {
			bid = (int) (field.getPrice()/2);
		} else {
			GUI.sendMessage(field.getName() + " er ikke pantsat. Startbuddet ligger på " + bid);
		}
		
		
		
		Player winner = new Player();

		
		//GUI.removeOwner(playerInventory[i]);
		
		if(bid == 0) {
			field.setOwner(null);
			return false;
		} else {
			field.setOwner(winner);
			return true;
		}

	}
	
}
