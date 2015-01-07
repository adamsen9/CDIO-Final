package controllers;

import java.awt.Color;

import entities.GameBoard;
import fields.*;
import boundaryToMatador.*;
import boundaryToMatador.Chance.Builder;

public class GUIManager {
	String state;
	String paymentChoice;
	String handel;
	
	public GUIManager(){
		
	}
	
	public GUIManager(String state, String paymentChoice, String handel){
		this.state = state;
		this.paymentChoice = paymentChoice;
		this.handel = handel;
		
	}
	
	public void create(GameBoard board) {
		
		Field[] fields = new Field[board.getNumberOfFields()];
		
		for(int i = 0; i < board.getNumberOfFields(); i++){
			OurField field = board.getField(i);
			String fieldType = field.getType();
			switch (fieldType){
			case "Street":
				OurStreet territory = (OurStreet) field;
				fields[i] = new Street.Builder()
					.setTitle(territory.getName())
					.setDescription("Rent: " + territory.getRent())
					.setSubText("Price: " + territory.getPrice())
					.setBgColor(territory.getColor())
					.build();
				break;
			case "Parking":
				OurParking refuge = (OurParking) field;
				fields[i] = new Refuge.Builder()
					.setTitle(refuge.getName())
					.setDescription("Receive: " + refuge.getBonus())
					.setSubText("Recieve: " + refuge.getBonus())
					.build();
				break;
			case "Brewery":
				OurBrewery brewery = (OurBrewery) field;
				fields[i] = new Brewery.Builder()
					.setTitle(brewery.getName())
					.setDescription("Rent: 100 * rolled value")
					.setSubText("Price: " + brewery.getPrice())
					.build();
				break;
			case "Tax":
				OurTax tax = (OurTax) field;
				if(tax.getTaxRate() == 0){
					fields[i] = new Tax.Builder()
					.setTitle(tax.getName())
					.setDescription("Pay: " + tax.getTaxAmount())
					.setSubText("Pay: " + tax.getTaxAmount())
					.build();
				}else {
					fields[i] = new Tax.Builder()
					.setTitle(tax.getName())
					.setDescription("Pay: " + tax.getTaxAmount() + " or Pay: " + tax.getTaxPercentage() + "% of your total Assets")
					.setSubText("Pay: " + tax.getTaxAmount() + " or Pay: " + tax.getTaxPercentage() + "% of your total Assets")
					.build();
				}
				break;		
			case "Shipping":
				OurShipping shipping = (OurShipping) field;
				fields[i] = new Shipping.Builder()
					.setTitle(shipping.getName())
					.setSubText("Price: " + shipping.getPrice())
					.setDescription("Rent 1: 500 | Rent 2: 1000 | Rent 3: 2000 | Rent 4: 4000")
					.build();
				break;
			case "Jail":
				OurJail jail = (OurJail) field;
				fields[i] = new Jail.Builder()
					.setTitle(jail.getName())
					.setSubText(jail.getName())
					.build();
				break;
			case "Start":
				fields[i] = new Start.Builder().build();
				break;
			case "Chance":
				fields[i] = new Chance.Builder()
				.setFgColor(Color.WHITE)
				.setBgColor(Color.BLACK)
				.build();
				break;
			}
		}
		
		GUI.create(fields);
	}
	public void addPlayer(String name, int balance) {
		GUI.addPlayer(name, balance);
	}
	public void addPlayer(String name, int balance, Color color) {
		GUI.addPlayer(name, balance, color);
		GUI.setCar(1, name);
	}

	public void setDice(int dieOne, int dieTwo) {
		GUI.setDice(dieOne, 0, 4, 7, dieTwo, 0, 5, 7);
	}
	
	public int getNumberOfPlayers(){
		if(state == "test"){
			return 5;
		}
		int numberOfPlayers = Integer.parseInt(GUI.getUserButtonPressed("Vælg hvor mange spillere der ønskes:", "2", "3", "4", "5", "6"));
		return numberOfPlayers;
	}
	
	public String roll(String name){
		if(state == "test"){
			return "";
		}
		return GUI.getUserButtonPressed("Det er " + name + "'s tur. Tryk på knappen for at kaste terningerne.", "Kast", "Køb hus/hotel");
	}
	
	public void rollJail(){
		GUI.getUserButtonPressed("Slå to ens for at blive løsladt", "Kast");
	}
	
	public void rollPayJail(){
		GUI.getUserButtonPressed("Du er blevet løsladt ved at betale 1000. Kast for at rykke", "Kast");
	}
	
	public int movePlayer(int prevField, int field, String name){
		if (prevField!=0) GUI.removeCar(prevField, name);
		GUI.setCar(field, name);
		return field;
	}
	public void updateBalance(String name, int balance) {
		GUI.setBalance(name, balance);
	}
	
	public String choosePayment(String name) {
		if(state == "test"){
			return this.paymentChoice;
		}
		return GUI.getUserButtonPressed("\n" + name + " er landet på karavane feltet og skal betale skat\nVil du betale 10% eller 4000?", "10%", "4000");
	}
	
	public void sendMessage(String message){
		if(state=="test"){
		return;
		}
		GUI.getUserButtonPressed("\n\n" + message, "Ok");
	}
	
	public String chooseToBuyFleet(String name, int price, String playerName){
		if(state == "test"){
			return handel;
		}
		return GUI.getUserButtonPressed("\n" + playerName + " er landet på flåden " + name + ". Den er ikke ejet.\nVil du købe " + name + "? Det koster " + price + " kroner.", "Køb", "Afslå");
	}
	
	public String chooseToBuyTerritory(String name, int price, String playerName, int rent){
		if(state == "test"){
			return handel;
		}
		return GUI.getUserButtonPressed("\n" + playerName  + " er landet på grunden " + name + ". Den er ikke ejet.\nVil du købe " + name + "? Det koster " + price + " kroner, lejen er på " + rent+".", "Køb", "Afslå");
	}
	
	public String chooseToBuyLaborCamp(String name, int price, String playerName){
		if(state == "test"){
			return handel;
		}
		return GUI.getUserButtonPressed("\n" + playerName + " er landet på grunden " + name + ". Den er ikke ejet.\nVil du købe " + name + "? Det koster " + price + " kroner, lejen varierer alt efter hvad der rulles.", "Køb", "Afslå");
	}
	
	public void setOwner(int fieldNumber, String name){
		if(state == "test"){
			return;
		}
		GUI.setOwner(fieldNumber, name);
	}
	
	public void removePlayer(String name){
		GUI.removeAllCars(name);
		GUI.setBalance(name, -9999);
	}
	
	public void removeOwner(int fieldNumber){
		GUI.removeOwner(fieldNumber);
	}
	
	public void winning(String name){
		if(state == "test"){
			System.out.println(name + " har vundet, tillykke!");
		} else{
			GUI.getUserButtonPressed("\n\n" + name + " har vundet spillet! Tillykke! ", "Afslut spil");
			GUI.close();
		}
	}
	
	public String getPlayerName(int playerNumber){
		String name = GUI.getUserString("Indtast venligst navnet for spiller "+playerNumber+":");
		return name;
	}
	
	public String rollOrPay() {
		return GUI.getUserButtonPressed("Vil du betale 1000 dkk eller rulle en gang med terningerne", "Betal","Rul");
	}
	
	
	
	
}


