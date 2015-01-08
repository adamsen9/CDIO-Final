package controllers;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Die;
import entities.GameBoard;
import entities.Player;
import fields.*;

public class GameController {
	private OurField currentField;
	private OurStreetController streetController = new OurStreetController();
	private OurBreweryController breweryController = new OurBreweryController();
	private OurShippingController shippingController = new OurShippingController();
	private OurParkingController refugeController = new OurParkingController();
	private OurTaxController taxController = new OurTaxController();
	private OurJailController jailController = new OurJailController();
	private OurChanceController chanceController = new OurChanceController();
	private Player activePlayer;
	private GameBoard board;
	private Die dice;
	private Player players[];
	private Color colors[];
	private GUIManager display;
	private String name;
	private boolean DTurn = false;
	
	public GameController(){
		
	}
	
	public static void main(String[] args) {
		GameController game = new GameController();
		game.run();
	}
	
	public void run() {
		board = new GameBoard();
		dice = new Die();
		players = new Player[6];
		colors = new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.ORANGE, Color.YELLOW};
		int numberOfPlayers, dieOne, dieTwo;
		int turn = 0;
		//Kør spillet
		display = new GUIManager();
		display.create(board);
		display.setcard("Robert er nummer et på din playlist");
		display.setcard("9gag er tidsfordriv");
		
		//Vælg antal spillere
		//numberOfPlayers = display.getNumberOfPlayers();
//		for(int i = 0; i < numberOfPlayers; i++){
//			name = display.getPlayerName(i+1);
//			players[i] = new Player(i, name);
//			display.addPlayer(players[i].getName(), players[i].getBalance(), colors[i]);
//		}
		
		numberOfPlayers = 2;
		players[0] = new Player(0, "Jens");
		display.addPlayer("Jens", 30000, colors[0]);
		
		players[1] = new Player(1, "Mogens");
		display.addPlayer("Mogens", 30000, colors[1]);
		
		//loop er får vores spil til at køre.
		while(true){
			activePlayer = players[turn];
			if (activePlayer == null) {// tjekker om spilleren har tabt eller stadig er med.
				turn = ++turn % numberOfPlayers;
				continue; 
			}
			int countOfNotNull = 0;
			Player winningPlayer = null;
			for (int count = 0; count < players.length; count++){
				if (players[count] != null){
					countOfNotNull++;
					winningPlayer = players[count];
				}
			}
			if (countOfNotNull == 1){
				display.winning(winningPlayer.getName());
				break;
			}
			while (true){
				String result = display.roll(activePlayer.getName());
				if(result == "Køb hus/hotel"){
					int[] ownedInCategory = activePlayer.getOwnedInCategory();
					System.out.println(activePlayer.getName() + Arrays.toString(ownedInCategory));
					ArrayList<String> owned = new ArrayList<String>();
					for(int i = 0; i < ownedInCategory.length; i++){
						if((ownedInCategory[i] == 2 && (i == 0 || i == 7)) || ownedInCategory[i] == 3){
							System.out.println("hej");
							ArrayList<OurField> foo = board.getAllInCategory(i);
							System.out.println(foo.size());
							for(int j = 0; j < foo.size(); j++){
								owned.add(foo.get(j).getName());
								System.out.println("KØRER RUNDT");
							}
						}
					}
					System.out.println("owned size "+owned.size());
					if(owned.size() > 1){
						System.out.println("OWNED OVER 1");
						String nameOfPlacement = display.chooseToPlaceHouse(owned);
						OurStreet placement = (OurStreet) board.getFieldWhereName(nameOfPlacement);
						if(activePlayer.getBalance() < placement.getHousePrice()){
							display.sendMessage("Du har ikke nok penge til at købe dette hus");
							continue;
						} else{
							activePlayer.withdraw(placement.getHousePrice());
							placement.addHouse();
							display.updateHouses(placement.getFieldId(), placement.getNumberOfHouses());
						}
						
					}
				} else{
					break;
				}
			}
			
			dieOne = dice.roll();
			dieTwo = dice.roll();
			
			if(activePlayer.isImPrisoned()){
				if(display.rollOrPay().equals("Betal")){
					display.rollPayJail();
					activePlayer.withdraw(1000);
					activePlayer.setImPrisoned(false);
					activePlayer.setTimeInJail(0);
					
				}
				else{
					display.rollJail();
					display.setDice(dieOne, dieTwo);
					if(dieOne==dieTwo){
						display.sendMessage("Du slog 2 ens og er blevet løsladt. Du får en ekstra tur.");
						activePlayer.setImPrisoned(false);
						activePlayer.setTimeInJail(0);
						DTurn = true;
					}
					else{
						activePlayer.setTimeInJail(activePlayer.getTimeInJail()+1);
						if(activePlayer.getTimeInJail()>3){
							display.sendMessage("Du siddet din tid og er nu blevet løsladt. Dette koster 1000");
							activePlayer.withdraw(1000);
							activePlayer.setImPrisoned(false);
							activePlayer.setTimeInJail(0);
						}
						else{
							display.sendMessage("Du har ikke slået to ens og forbliver i fængslet");
							turn++;
							continue;
						}
					}
				}
			}
//			display.setDice(dieOne, dieTwo);
//			activePlayer.move(dieOne+dieTwo);
			activePlayer.move(display.getUserInteger(activePlayer.getName() +", indtast hvad du vil slå:"));
			display.updateBalance(activePlayer.getName(), activePlayer.getBalance());
			
			//Spiller bevæger sig
			display.movePlayer(activePlayer.getPrevField(), activePlayer.getField(), activePlayer.getName());
                        
			//Logik til at kontrollere hvilket felt der er landet på.
			
			currentField = board.getField(activePlayer.getField() - 1);
			switch(currentField.getType()) {
			case("Street"):
				if(!streetController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("Brewery"):
				if(!breweryController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("Shipping"):
				if(!shippingController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("Parking"):
				refugeController.landOnField(activePlayer, display, currentField, dice);
				break;
				
			case("Tax"):
				if(!taxController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
			case("Start"):
				break;
			case("Jail"):
				jailController.landOnField(activePlayer, display, currentField, dice);
				break;
			case("Chance"):
				chanceController.landOnField(activePlayer, display, currentField, dice);
				break;
			}
			//Opdatering af gameboard
			if (DTurn == true){
				DTurn = false;
				turn--;
			}
				turn = ++turn % numberOfPlayers;
			for(int i = 0; i < numberOfPlayers; i++) {
				if (players[i] == null) continue;
				display.updateBalance(players[i].getName(), players[i].getBalance());
			}
		}	
	}	
	private void bankruptcy(int turn){
		int[] playerInventory = activePlayer.getInventory();
		for (int i = 0; i < playerInventory.length; i++){
			if (playerInventory[i] != 0){
				Ownable currentOwnable = (Ownable) board.getField(playerInventory[i]-1);
				currentOwnable.setOwner(null);
				display.removeOwner(playerInventory[i]);
			}
		}
		activePlayer.resetInventory();
		display.removePlayer(activePlayer.getName());
		players[turn] = null;
	}
}
