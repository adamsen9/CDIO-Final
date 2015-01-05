package entities;
import java.util.Random;

public class Die {
	private int faceValue;
	private Random rnd = new Random();
	String state;
	int tal;
	
	public Die(int tal, String test){
		this.tal = tal;
		this.state = test;
	}
	
	public Die(){
		faceValue = 0;
	}
	
	public int roll(){
		if (state == "test"){
				return tal;
		}
		faceValue = rnd.nextInt(6)+1;
		return faceValue;
	}
	
	public void setDie(int value){
		faceValue = value;
	}
	
	public int getDie(){
		return faceValue;
	}
}
