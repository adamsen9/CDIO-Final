package entities;

public class Account {
	private int balance;
	final private int ID;
	
	public Account(int balance, int id){
		this.balance = balance;
		this.ID = id;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getBalance(){
		return this.balance;
	}
	
	public int getId(){
		return this.ID;
	}
	
	public void deposit(int amount){
		if(amount >= 0){
			this.balance += amount;
		} else if(amount < 0){
			System.out.println("Du må ikke indsætte negative beløb.");
		}
	}
	
	public boolean withdraw(int amount){
		if(amount >= 0){
			if(amount <= this.balance){
				this.balance -= amount; 
				return true;	
			} else {
				this.balance = 0;
				return false;
			}
		} 
		return false;		
	}
	
	public boolean transfer(Account reciever, int amount){
		if(amount >= 0){
			if(amount > this.balance){
				reciever.deposit(balance);
				this.withdraw(balance);
				return false;
			} else{
				this.withdraw(amount);
				reciever.deposit(amount);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		s += getId() + " " + getBalance();
		
		return s;
	}
	
}
