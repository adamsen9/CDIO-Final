package controllers;
import java.util.ArrayList;

import entities.*;
import fields.*;


public class AuctionController {

	public AuctionController(){
		
	}
	
	public void auction(GUIManager GUI, Player[] possibleAuctioneers, Player activePlayer,  Ownable field, boolean bankruptcy){
		
		Player[] auctioneers = new Player[possibleAuctioneers.length];
		for(int i = 0; i < possibleAuctioneers.length; i++) {
			if(!(possibleAuctioneers[i] == null)) {
				if(possibleAuctioneers[i].getId() != activePlayer.getId()){
					auctioneers[i] = possibleAuctioneers[i];
				}
			}
		}
		
		Player auctionWinner = null;
		int winningBid = 0;
		int bidders = auctioneers.length;
		
		for(int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] == null)  {
				bidders--;
			}
		}
		
		if(bankruptcy == true) {
			GUI.sendMessage("Der bliver afholdt tvangsauktion af feltet " + field.getName() + ".");
		} else {
			GUI.sendMessage("Der bliver afholdt auktion af feltet " + field.getName() + ".");	
		}
		
		int minBid = field.getPrice();
		int newBid = 0;
		if(field.getPawnedStatus()) {
			minBid = (int) (field.getPrice()/2);
			GUI.sendMessage(field.getName() + " er pantsat. Startbuddet ligger på " + minBid+".");
		} else {
			GUI.sendMessage(field.getName() + " er ikke pantsat. Startbuddet ligger på " + minBid+".");
		}
		for (int i = 0; i < auctioneers.length; i++) {
			if(auctioneers[i] != null)
				if(auctioneers[i].getBalance() < minBid) {
					GUI.sendMessage(auctioneers[i].getName() + " har ikke råd til at byde og udgår automatisk.");
					auctioneers[i] = null;
				}
		}
		
		String choice = "";
		
		boolean allBid = false;
		outerloop:
		while(true) {
			for(int i = 0; i < auctioneers.length; i++) {
				
				if((auctioneers[i] != null)) {
					if(auctioneers[i].getBalance() <= minBid) {
						GUI.sendMessage(auctioneers[i].getName() + " har ikke længere råd til at byde på auktionen og udgår automatisk.");
						auctioneers[i] = null;
						bidders--;
					} else {
						choice = GUI.chooseToBid(auctioneers[i].getName(), minBid);
						
						if(choice.equals("Ja") && auctioneers[i].getBalance() >= minBid) {
							//Spørg om hvor meget
							
							while(true) {
								newBid = GUI.enterBid(minBid, auctioneers[i].getBalance());
								if(newBid <= minBid && !(allBid)) {
									GUI.sendMessage("Du skal minimum byde over det tidligere bud, prøv igen.");
								} else if(auctioneers[i].getBalance() < newBid) {
									GUI.sendMessage("Du kan ikke byde et større beløb end du har, prøv igen.");
								} else if((newBid == minBid && allBid && newBid <= auctioneers[i].getBalance()) || (newBid > minBid && newBid <= auctioneers[i].getBalance())) {
									winningBid = minBid;
									auctionWinner = auctioneers[i];
									break;
								}
							}
							
							
						} else if(choice.equals("Nej")) {
							auctioneers[i] = null;
							bidders--;
						}
					}
				}
				if(allBid && bidders <= 1) {
					break outerloop;
				}
			}
			allBid = true;
		}
		
		if(auctionWinner == null) {
			System.out.println("No bids");
			GUI.sendMessage("Ingen har budt på grunden " + field.getName() + ", og den overgår derfor til banken.");
			field.setOwner(null);
		} else {
			for(int i = 0; i < auctioneers.length; i++) {
				if(auctioneers[i] != null) {
					if(auctionWinner.getId() == auctioneers[i].getId()){
						//Overdrag grunden til den vindene spiller
						field.setOwner(auctioneers[i]);
						OurStreet street = (OurStreet) field;
						auctionWinner.addToInventory(field.getFieldId(), field.getPrice(), street.getCategory());
						GUI.setOwner(field.getFieldId(), auctioneers[i].getName());
						GUI.sendMessage(auctionWinner.getName()+" vandt auktionen af "+field.getName()+" for "+winningBid+".");
						
						//Fratræk den vindene spiller sit bud og opdater GUIen
						auctioneers[i].withdraw(winningBid);
						GUI.updateBalance(auctioneers[i].getName(), auctioneers[i].getBalance());
						break;
					}
				}
			}
		}
	}
}
