package controllers;
import java.awt.Color;

import entities.Die;
import entities.GameBoard;
import entities.Player;
import fields.*;

public class GameController {
	private OurField currentField;
	private TerritoryController territoryController = new TerritoryController();
	private LaborCampController laborCampController = new LaborCampController();
	private FleetController fleetController = new FleetController();
	private RefugeController refugeController = new RefugeController();
	private TaxController taxController = new TaxController();
	private Player activePlayer;
	private GameBoard board;
	private Die dice;
	private Player players[];
	private Color colors[];
	private GUIManager display;
	private String name;
	
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
		
		
		//Vælg antal spillere
		numberOfPlayers = display.getNumberOfPlayers();
		
		for(int i = 0; i < numberOfPlayers; i++){
			name = display.getPlayerName(i+1);
			players[i] = new Player(i, name);
			display.addPlayer(players[i].getName(), players[i].getBalance(), colors[i]);
		}
		
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
			display.roll(activePlayer.getName());
			dieOne = dice.roll();
			dieTwo = dice.roll();
			display.setDice(dieOne, dieTwo);
			activePlayer.move(dieOne+dieTwo);
			
			//Spiller bevæger sig
			display.movePlayer(activePlayer.getPrevField(), activePlayer.getField(), activePlayer.getName());
                        
			//Logik til at kontrollere hvilket felt der er landet på.
			
			currentField = board.getField(activePlayer.getField() - 1);
			switch(currentField.getType()) {
			case("Territory"):
				if(!territoryController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("LaborCamp"):
				if(!laborCampController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("Fleet"):
				if(!fleetController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
				
			case("Refuge"):
				refugeController.landOnField(activePlayer, display, currentField, dice);
				break;
				
			case("Tax"):
				if(!taxController.landOnField(activePlayer, display, currentField, dice)){
					bankruptcy(turn);
				}
				break;
			}

			//Opdatering af gameboard
			
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
