package entities;

public class Player{
	private String name;
	private int previous_field = 0;
	private int field = 0;
	final private int ID;
	final private int STARTMONEY = 30000;
	private String payMethod = "10%"; // Kan være "10%" eller "4000"
	private Account acc;
	private int numberOfFleetsOwned = 0;
	private int numberOfLaborCampsOwned = 0;
	private boolean hasLost = false;
	private int[] inventory = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int numberOfFieldsOwned = 0;
	private int wealthOfFieldsOwned = 0;
	
	public Player() {
		this.name = "DefaultPlayer";
		this.ID = 1;
		this.acc = new Account(this.STARTMONEY, 1);
	}
	
	public Player(int id, String name){
		this.name = name;
		this.ID = id;
		this.acc = new Account(this.STARTMONEY, id);
	} 
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return this.ID;
	}
	
	public void setField(int field){
		this.field = field;
	}
	
	public int getField(){
		return this.field;
	}
	
	public int getPrevField() {
		return this.previous_field;
	}

	public Account getAcc() {
		return acc;
	}
	
	public int getStartMoney(){
		return STARTMONEY;
	}

	public void move(int roll) {
		previous_field = field;
		if (field+roll == 21) field=21;
		else field = (field+roll)%21;
	}
	
	public void setPayMethod(String method) {
		this.payMethod = method;
	}
	
	public String getPayMethod() {
		return this.payMethod;
	}

	public int getNumberOfFleetsOwned() {
		return numberOfFleetsOwned;
	}

	public void setNumberOfFleetsOwned(int numberOfFleetsOwned) {
		this.numberOfFleetsOwned = numberOfFleetsOwned;
	}
	
	public void addNumberOfFleetsOwned(){
		this.numberOfFleetsOwned++;
	}
	
	public int getNumberOfLaborCampsOwned(){
		return numberOfLaborCampsOwned;
	}
	
	public void addNumberOfLaborCamps(){
		this.numberOfLaborCampsOwned++;
	}
	
	public void setNumberOfLaborCamps(int numberOfLabourCampsOwned){
		this.numberOfLaborCampsOwned = numberOfLabourCampsOwned;
	}
	
	//Denne metode skal kaldes idet GameController vurderer at en spiller er gået bankerot
	public void bankruptcy(){
		System.out.print("Spiller " + name + " er gået konkurs og er ude af spillet.");
		this.hasLost = true;
		//Frigiv alle ejede grunde
	}

	public boolean isHasLost() {
		return hasLost;
	}

	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
	}

	public int[] getInventory() {
		return inventory;
	}
	
	public int getNumberOfFieldsOwned(){
		return numberOfFieldsOwned;
	}
	
	public void setInventory(int[] inventory) {
		this.inventory = inventory;
	}
	
	public void addToInventory(int fieldNumber, int fieldPrice){
		this.inventory[this.numberOfFieldsOwned] = fieldNumber;
		this.numberOfFieldsOwned++;
		this.wealthOfFieldsOwned += fieldPrice;
	}
	
	public int getTotalAssets() {
		int totalAssets = acc.getBalance() + wealthOfFieldsOwned;
		return totalAssets;
	}
	
	public void resetInventory(){
		for(int i = 0; i < this.inventory.length; i++){
			this.inventory[i] = 0;
		}
		this.numberOfFieldsOwned = 0;
	}
	
	// methods for other classes to use when using account.
	
	public int getBalance(){
		return acc.getBalance();
	}
	
	public boolean transfer(Player reciever, int amount){
		return acc.transfer(reciever.getAcc(), amount);
	}
	
	public boolean withdraw(int amount){
		return acc.withdraw(amount);
	}
	
	public void deposit(int amount){
		acc.deposit(amount);
	}
}