package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;
public class PlayerTest {

	@Test
	public void TestInitialConditions() {
		//Test af start egenskab:
		Player player = new Player(101, "THOMAS");
		assertEquals("THOMAS", player.getName());
		assertEquals(101, player.getId());
		assertEquals(30000, player.getBalance());
		assertEquals(0, player.getNumberOfFleetsOwned());
		assertEquals(0, player.getNumberOfLaborCampsOwned());
		assertEquals(0, player.getNumberOfFieldsOwned());
	}
	
	@Test
	//Vi tester add og set metoderne, for felterne.
	public void testAddSet()  {
		Player player = new Player(101, "THOMAS");
		player.addNumberOfFleetsOwned();
		player.addNumberOfLaborCamps();
		player.addToInventory(10,1000);
		assertEquals(1, player.getNumberOfFleetsOwned());
		assertEquals(1,player.getNumberOfLaborCampsOwned());
		assertEquals(1, player.getNumberOfFieldsOwned());
		assertEquals(10, player.getInventory()[0]);
	}
	
	@Test
	//Vi tester om metoden Haslost, setHasLost og isHasLost virker.
	public void testLost(){
		Player player = new Player(101, "THOMAS");
		assertEquals(false, player.isHasLost());
		player.setHasLost(true);		
		assertEquals(true, player.isHasLost());
	}

	@Test
	//VI tester Field og move metoderne virker.
	public void testFieldmove() {
		Player player = new Player(101, "THOMAS");
		assertEquals(0, player.getField());
		player.move(6);
		assertEquals(6, player.getField());
		player.move(15);
		assertEquals(21, player.getField());
		player.move(12);
		assertEquals(12, player.getField());
		assertEquals(21, player.getPrevField());		
	}
	


}
