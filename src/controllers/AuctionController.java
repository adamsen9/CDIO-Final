package controllers;
import java.util.ArrayList;

import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	public boolean Auction(GUIManager GUI, Player[] auctioneers, Ownable field, boolean bankruptcy){
		if(bankruptcy == true) {
			
		} else {
			
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
