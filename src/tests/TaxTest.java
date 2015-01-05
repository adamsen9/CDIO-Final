package tests;
import entities.*;
import fields.*;
import controllers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class TaxTest{
	Die die;
	OurTax tax;
	TaxController taxController;
	GUIManager display;
	Player player;
	
	@Before
	public void setup() {
		die = new Die();
		tax = new OurTax(4000, 10, "skat", 1, 1);
		taxController = new TaxController();
		display = new GUIManager("test", "10%", "køb");
		player = new Player(1, "Mathias");
	}

	@Test
	public void testTaxFlatAmountWithMoney() {
		display = new GUIManager("test", "4000", "køb");
		int expectedBalance = 30000;
		assertEquals(expectedBalance, player.getBalance());
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= 4000;
		assertEquals(expectedBalance, player.getBalance());
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= 4000;
		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testFlatAmountWithoutMoney(){
		display = new GUIManager("test", "4000", "køb");
		player.getAcc().setBalance(3000);
		assertEquals(3000, player.getBalance());
		assertEquals(false, taxController.landOnField(player, display, tax, die));
	}
	
	@Test
	public void testFlatAmountWithExactMoney(){
		display = new GUIManager("test", "4000", "køb");
		player.getAcc().setBalance(4000);
		assertEquals(4000, player.getBalance());
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		assertEquals(0, player.getBalance());
	}
	
	@Test
	public void testPercentageWithoutFields(){
		int expectedBalance = 30000;
		assertEquals(expectedBalance, player.getBalance());
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= expectedBalance/10;
		assertEquals(expectedBalance, player.getBalance());
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= expectedBalance/10;
		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testPercentageWithFields(){
		int expectedBalance = 30000;
		assertEquals(expectedBalance, player.getBalance());
		player.addToInventory(2, 5000);
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= (expectedBalance+5000)/10;
		assertEquals(expectedBalance, player.getBalance());
		player.addToInventory(3, 10000);
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		expectedBalance -= (expectedBalance+5000+10000)/10;
		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testPercentageWithFieldsWithoutMoney(){
		player.getAcc().setBalance(1000);
		assertEquals(1000, player.getBalance());
		player.addToInventory(1, 20000);
		assertEquals(false, taxController.landOnField(player, display, tax, die));
	}
	
	@Test
	public void testPercentageWithFieldsWithExactMoney(){
		player.getAcc().setBalance(1000);
		assertEquals(1000, player.getBalance());
		player.addToInventory(1, 9000);
		assertEquals(true, taxController.landOnField(player, display, tax, die));
		assertEquals(0, player.getBalance());
	}
}
