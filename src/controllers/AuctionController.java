package controllers;
import java.util.ArrayList;

import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	public Player Auction(GUIManager GUI, Player[] auctioneers, Ownable field, boolean bankruptcy){
		int bidders = auctioneers.length;
		
		for(int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] == null)  {
				bidders--;
			}
		}
		
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
		
		int c = 0;
		outerloop:
		while(true) {
			for(int i = 0; i < auctioneers.length; i++) {
				
				if((auctioneers[i] != null)) {
					if(auctioneers[i].getBalance() <= bid) {
						GUI.sendMessage(auctioneers[i].getName() + " har ikke længere råd til at byde på auktionen og er automatisk ude");
						auctioneers[i] = null;
						bidders--;
					} else {
						choice = GUI.chooseToBid(auctioneers[i].getName(), bid);
						
						if(choice.equals("Ja") && auctioneers[i].getBalance() >= bid) {
							//Spørg om hvor meget
							bid = GUI.enterBid(bid, auctioneers[i].getBalance());
							
							highestBidder = auctioneers[i];
							
						} else if(choice.equals("Nej")) {
							auctioneers[i] = null;
							bidders--;
						}
					}
				}
				if(c >= 1 && bidders <= 1) {
					break outerloop;
				}
			}
			c++;
		}
		
		if(highestBidder == null) {
			return null;
		} else {
			return highestBidder;
		}
	}
	
}
