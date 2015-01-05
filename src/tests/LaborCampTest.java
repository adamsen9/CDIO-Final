package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import controllers.*;
import entities.*;
import fields.*;

public class LaborCampTest {
	Die die;
	LaborCampController labcController;
	LaborCamp laborCamp;
	LaborCamp laborCamp2;
	Player player1;
	Player player2;
	GUIManager display;
	boolean HasLost1;
	boolean HasLost2;

	
	@Before
	public void setUp(){
		die = new Die(6, "test");
		labcController = new LaborCampController();
		laborCamp = new LaborCamp(2000, "Andeby", 15, 10);
		display = new GUIManager("test", "10%", "Køb");
		
	}	
	
	@Test
	public void NotOwnedBuyHasMoney(){
		
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		
		labcController.landOnField(player, display, laborCamp, die);
		
		assertEquals(10,player.getInventory()[0]);
		assertEquals(1,player.getNumberOfFieldsOwned());
		assertEquals(28000,player.getAcc().getBalance());
		assertEquals(true,labcController.landOnField(player, display, laborCamp, die));
	}
	@Test
	public void NotOwnedBuyExactMoney(){
		
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		player.getAcc().setBalance(2000);
		
		labcController.landOnField(player, display, laborCamp, die);
		
		assertEquals(10,player.getInventory()[0]);
		assertEquals(1,player.getNumberOfFieldsOwned());
		assertEquals(0,player.getAcc().getBalance(),0);
		assertEquals(true,labcController.landOnField(player, display, laborCamp, die));
	}
	@Test
	public void NotOwnedBuyNoMoney(){
		
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		player.getAcc().setBalance(1500);
		
		labcController.landOnField(player, display, laborCamp, die);
		
		assertEquals(0,player.getInventory()[0]);
		assertEquals(0,player.getNumberOfFieldsOwned());
		assertEquals(1500,player.getAcc().getBalance());
		assertEquals(true,labcController.landOnField(player, display, laborCamp, die));
	}
	
	
	@Test
	//Der testes om en spiller med rigeligt penge kan afvise et felt.  
	public void NotOwnedRejectHasMoney(){
		
		Player player = new Player(1,"Joachim von And");
		display = new GUIManager("test","10%","Afslå"); 
		
		labcController.landOnField(player, display, laborCamp, die);
		
		assertEquals(30000,player.getAcc().getBalance());
		assertEquals(0,(int)player.getInventory()[0]);
		assertEquals(0,player.getNumberOfFieldsOwned());	
	}
	
	@Test
	//Der testes om en spiller der lander på sit eget felt skal betale noget.
	public void OwnedSelf() {
		
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		
		labcController.landOnField(player, display, laborCamp, die);
		labcController.landOnField(player, display, laborCamp, die);
		
		assertEquals(10,player.getInventory()[0]);
		assertEquals(1,player.getNumberOfFieldsOwned());
		assertEquals(28000,player.getAcc().getBalance());
		assertEquals(true,labcController.landOnField(player, display, laborCamp, die));
	}

	@Test
	//Der testes om en spiller med rigeligt penge, der lander på et allerede eget felt af en anden betaler og mister det rigtige antal penge,
	//og ikke taber spillet selvom man har penge nok 
	public void OwnedOtherOnePayRentAbove(){
		
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(10,player1.getInventory()[0]);
		assertEquals(1,player1.getNumberOfFieldsOwned());
		assertEquals(29200,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		assertEquals(28800,player2.getAcc().getBalance());
		
		assertEquals(true,HasLost1);
		assertEquals(true,HasLost2);

	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	//og ikke ryger ud af spillet
	public void OwnedOtherOnePayRentExact(){
		
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		player2.getAcc().setBalance(1200);
		
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(10,player1.getInventory()[0]);
		assertEquals(1,player1.getNumberOfFieldsOwned());
		assertEquals(29200,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		assertEquals(0,player2.getAcc().getBalance());
		
		assertEquals(true,HasLost1);
		assertEquals(true,HasLost2);
	}
	
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	//og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	@Test 
	public void OwnedOtherOnePayRentBelow(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		player2.getAcc().setBalance(600);
		
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(10,player1.getInventory()[0]);
		assertEquals(1,player1.getNumberOfFieldsOwned());
		assertEquals(28600,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		
		assertEquals(true,HasLost1);
		assertEquals(false,HasLost2);
	}

		
		
	
	//Denne gang ejer han det andet felt 

	public void OwnedOtherTwoPayRentAbove(){
		
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(10,player1.getInventory()[0]);
		assertEquals(1,player1.getNumberOfFieldsOwned());
		assertEquals(30400,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		assertEquals(27600,player2.getAcc().getBalance());
		
		assertEquals(true,HasLost1);
		assertEquals(true,HasLost2);
		
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	//og ikke ryger ud af spillet
	public void OwnedOtherTwoPayRentExact(){
		
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		player2.getAcc().setBalance(2400);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(11,player1.getInventory()[0]);
		assertEquals(2,player1.getNumberOfFieldsOwned());
		assertEquals(28400,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		assertEquals(0,player2.getAcc().getBalance());
		
		assertEquals(true,HasLost1);
		assertEquals(true,HasLost2);
	}
	
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	//og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	@Test 
	public void OwnedOtherTwoPayRentBelow(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		player2.getAcc().setBalance(1200);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		
		assertEquals(11,player1.getInventory()[0]);
		assertEquals(2,player1.getNumberOfFieldsOwned());
		assertEquals(27200,player1.getAcc().getBalance());
		
		assertEquals(0,player2.getInventory()[0]);
		assertEquals(0,player2.getNumberOfFieldsOwned());
		
		assertEquals(true,HasLost1);
		assertEquals(false,HasLost2);
	}
	
	
}
