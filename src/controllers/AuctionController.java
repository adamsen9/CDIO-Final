package controllers;
import java.util.ArrayList;

import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	public boolean Auction(GUIManager GUI, Player[] auctioneers, Ownable field, boolean bankruptcy){
		int bidders = auctioneers.length;
		
		for(int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] == null)  {
				bidders--;
			}
		}
		
		System.out.println(bidders + " bidders");
		
		if(bankruptcy == true) {
			GUI.sendMessage("Der bliver afholdt tvangsauktion af feltet" + field.getName() + ".");
		} else {
			GUI.sendMessage("Der bliver afholdt auktion af feltet " + field.getName() + ".");	
		}
		
		int bid = field.getPrice();
		
		if(field.getPawnedStatus()) {
			bid = (int) (field.getPrice()/2);
		} else {
			GUI.sendMessage(field.getName() + " er ikke pantsat. Startbuddet ligger på " + bid);
		}
		for (int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] != null)
				if(auctioneers[i].getBalance() < bid) {
					GUI.sendMessage(auctioneers[i].getName() + " har ikke råd til at byde og udgår automatisk");
					auctioneers[i] = null;
				}
		}
		
		Player highestBidder = null;
		String choice = "";
		
		for(int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] == null) {
				System.out.println(i + " = null");
			} else {
				System.out.println(i + " != null");
			}
		}
		
		System.out.println(auctioneers[1].getName());
		System.out.println(auctioneers[2].getName());
		
		while(bidders != 1) {
			for(int i = 0; i < auctioneers.length; i++) {

				
				if((auctioneers[i] != null)) {
					choice = GUI.chooseToBid(auctioneers[i].getName(), bid);
					
					if(choice.equals("Ja") && auctioneers[i].getBalance() >= bid) {
						//Spørg om hvor meget
						GUI.enterBid(bid);
						
						
					} else if(choice.equals("Nej")) {
						auctioneers[i] = null;
						bidders--;
						System.out.println(bidders);
					} else if(choice.equals("Ja") && auctioneers[i].getBalance() < bid ) {
						
					}
				}
			}
		}
		
		
		
		if(highestBidder == null) {
			return false;
		} else {
			return true;
		}
		
		//GUI.removeOwner(playerInventory[i]);
	}
	
}
