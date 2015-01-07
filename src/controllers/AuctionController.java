package controllers;
import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	
	public boolean bankruptcyAuction(){
		
		
		return true;
	}
	
	public boolean Auction(GUIManager GUI, Player players[], Ownable field){
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
