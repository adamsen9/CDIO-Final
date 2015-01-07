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
		
		Player winner = new Player();
		
		int bid = 0;
		
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
