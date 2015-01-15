package entities;

public class Player{
	private String name;
	private int previous_field = 1;
	private int field = 1;
	final private int ID;
	final private int STARTMONEY = 30000;
	private String payMethod = "10%"; // Kan være "10%" eller "4000"
	private Account acc;
	private int numberOfShippingOwned = 0;
	private int numberOfBreweryOwned = 0;
	private boolean hasLost = false;
	private int[] inventory = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] ownedInCategory = {0,0,0,0,0,0,0,0};
	private int numberOfFieldsOwned = 0;
	private int wealthOfFieldsOwned = 0;
	private int timeInJail = 0;
	private boolean imPrisoned;
	private int houseValue = 0;
	
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
	
	public void setField(int field, int previousField){
		this.field = field;
		if(field < previousField){
			acc.deposit(4000);
		}
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
		field = previous_field + roll;
		if(field > 40){
			field -= 40;
			acc.deposit(4000);
		}
	}
	
	public void setPayMethod(String method) {
		this.payMethod = method;
	}
	
	public String getPayMethod() {
		return this.payMethod;
	}

	public int getNumberOfShippingOwned() {
		return numberOfShippingOwned;
	}

	public void setNumberOfShippingOwned(int numberOfFleetsOwned) {
		this.numberOfShippingOwned = numberOfFleetsOwned;
	}
	
	public void addNumberOfShippingOwned(){
		this.numberOfShippingOwned++;
	}
	
	public int getNumberOfBreweryOwned(){
		return numberOfBreweryOwned;
	}
	
	public void addNumberOfBrewery(){
		this.numberOfBreweryOwned++;
	}
	
	public void setNumberOfBrewery(int numberOfLabourCampsOwned){
		this.numberOfBreweryOwned = numberOfLabourCampsOwned;
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
	
	public void addToInventory(int fieldNumber, int fieldPrice, int category){
		this.inventory[this.numberOfFieldsOwned] = fieldNumber;
		this.numberOfFieldsOwned++;
		this.wealthOfFieldsOwned += fieldPrice;
		this.ownedInCategory[category-1]++;
	}
	
	public void addToInventory(int fieldNumber, int fieldPrice){
		this.inventory[this.numberOfFieldsOwned] = fieldNumber;
		this.numberOfFieldsOwned++;
		this.wealthOfFieldsOwned += fieldPrice;
	}
	public int getTotalAssets() {
		int totalAssets = acc.getBalance() + wealthOfFieldsOwned + houseValue;
		return totalAssets;
	}
	
	public void resetInventory(){
		for(int i = 0; i < this.inventory.length; i++){
			this.inventory[i] = 0;
		}
		this.numberOfFieldsOwned = 0;
		this.wealthOfFieldsOwned = 0;
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

	public int getTimeInJail() {
		return timeInJail;
	}

	public void setTimeInJail(int timeInJail) {
		this.timeInJail = timeInJail;
	}

	public boolean isImPrisoned() {
		return imPrisoned;
	}

	public void setImPrisoned(boolean imPrisoned) {
		this.imPrisoned = imPrisoned;
	}
	
	public int[] getOwnedInCategory(){
		return ownedInCategory;
	}
	
	public void setOwnedInCategory(int value, int index){
		ownedInCategory[index] = value;
	}

	public int getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(int houseValue) {
		this.houseValue = houseValue;
	}

	public void increaseHouseValue(int value){
		this.houseValue += value;
	}
	
	public void decreaseHouseValue(int value){
		this.houseValue -= value;
	}
}
