package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import entities.*;
import fields.*;
import controllers.*;
public class TerritoryTest {
	Die die;
	Territory territory;
	TerritoryController territoryController;
	GUIManager display;
	Territory territory2; 
	
//Der er lavet test for LandOnField() af Territory. LandOnField tjekker følgende:
		//Lander man på et felt der ikke ejes får man muligheden for:
		//		- At købe feltet
		//      - Ikke at købe feltet 
		//
		//Lander man på et felt der ejes i forvejen:
		//		- Er det ejet af én selv sker der ikke noget
		//		- Er det ikke eget af en selv skal man betale penge til ejeren. Følgende konsekvenser for dette er:
		//				- Har man penge nok betaler man til ejeren, og beløbet man betaler bliver lagt til ejerens konto.
		//				- Har man ikke penge nok taber man spillet
		//
//Formålet med denne test er at se om LandOnField virker som den skal. Dette gøres ved følgende tests:
	//	Lander man på et felt der ikke ejes af nogen testes der for følgende scenarier: 
		//		- Man vælger at købe feltet, og:
			//		- Man har penge nok til at betale for feltet
			//		- Man har lige præcis nok penge til at betale for feltet
			//		- Man har ikke penge nok til at betale for feltet
		//		- Man vælger ikke at købe feltet	
			//		- Dette testes kun hvor man har penge nok, da man ikke betaler noget
	//	Lander man på et felt der ejes af nogen i forvejen testes der for følgende scenarier:
			//		- Er man selv ejer skal der ikke ske noget
			//		- Er man ikke selv ejer betaler man til en anden spiller. For dette testes der:
				//		- Når den betalende spiller har penge nok penge
				//		- Når den betalende spiller har lige præcis nok penge
				//		- Når den betalende spiller ikke har nok penge
	
	//Disse objekter ændrer sig aldrig, udover display til en enkelt test i NotOwnedRejectHasMoney().
	@Before
	public void setUp(){
		die = new Die();
		territory = new Territory(1000, 2000, "Andeby", 2, 10);
		territoryController = new TerritoryController();
		display = new GUIManager("test", "10%", "Køb");
	}
	
	@Test
	//Der testes om en spiller med rigeligt penge kan købe et felt og ende med de rigtige antal penge til sidst, 
	// og om hans data er opdateret til sidst
	public void NotOwnedBuyHasMoney() {
		
		//Jeg starter med at oprette en spiller der står på felt 10.
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		
		//Denne spiller køber feltet
		territoryController.landOnField(player, display, territory, die);
		
		
		//Der testes for om det rigtige beløb er blevet trukket, om listen med spillerens egede felter og hans antal af ejede felter opdateres. 
		//- til sidst undersøges der om han har tabt.
		assertEquals(28000,player.getAcc().getBalance());
		assertEquals(10,player.getField());
		assertEquals(10,player.getInventory()[0]);
		assertEquals(1,player.getNumberOfFieldsOwned());
		assertEquals(true,territoryController.landOnField(player, display, territory, die));
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge kan købe et felt. De samme ting undersøges bagefter.
	public void NotOwnedBuyEqualMoney() {
		
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(2000);
		
		territoryController.landOnField(player, display, territory, die);
		
		assertEquals(0,player.getAcc().getBalance());
		assertEquals(10,player.getInventory()[0]);
		assertEquals(1,player.getNumberOfFieldsOwned());
		assertEquals(true,territoryController.landOnField(player, display, territory, die));
		
	}
	
	@Test
	//Der testes om en spiller uden penge nok får lov til at købe et felt 
	public void NotOwnedBuyNoMoney() {
		
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(1500);
		player.setField(10);
		
		territoryController.landOnField(player, display, territory, die);
		
		assertEquals(1500,player.getAcc().getBalance());
		assertEquals(10,player.getField());
		assertEquals(0,player.getInventory()[0]);
		assertEquals(0,player.getNumberOfFieldsOwned());
		assertEquals(true,territoryController.landOnField(player, display, territory, die));
	}
	
	@Test
	//Der testes om en spiller med rigeligt penge kan afvise et felt.  
	public void NotOwnedRejectHasMoney() {
		
		Player player = new Player(1,"Anders And");
		GUIManager display = new GUIManager("test","10%","Afslå"); 
		player.setField(10);
		
		territoryController.landOnField(player, display, territory, die);
		
		assertEquals(30000,player.getAcc().getBalance());
		assertEquals(10,player.getField());
		assertEquals(0,player.getInventory()[0]);
		assertEquals(true,territoryController.landOnField(player, display, territory, die));
	}
	@Test
	//Der testes om en spiller der lander på sit eget felt skal betale noget.
	public void OwnedSelf() {
		
		Player player = new Player(1,"Onkel Joachim");
		player.setField(10);
		
		territoryController.landOnField(player, display, territory, die);
		territoryController.landOnField(player, display, territory, die);
		
		assertEquals(28000,player.getAcc().getBalance());
		assertEquals(10,player.getField());
		assertEquals(0,player.getInventory()[1]);
		assertEquals(true,territoryController.landOnField(player, display, territory, die));
	}

	@Test
	//Der testes om en spiller med rigeligt penge, der lander på et allerede eget felt af en anden betaler og mister det rigtige antal penge,
	// og ikke taber spillet selvom man har penge nok 
	public void OwnedOtherPayRentAbove() {
		
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player2.setField(10);
		player1.setField(10);
		
		territoryController.landOnField(player2, display, territory, die);
		territoryController.landOnField(player1, display, territory, die);
		
		assertEquals(29000,player1.getAcc().getBalance());
		assertEquals(29000,player2.getAcc().getBalance());
		assertEquals(10,player2.getInventory()[0]);
		assertEquals(0,player1.getInventory()[0]);
		assertEquals(true,territoryController.landOnField(player1, display, territory, die));
		assertEquals(true,territoryController.landOnField(player2, display, territory, die));
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	// og ikke ryger ud af spillet
	public void OwnedOtherPayRentExact() {
		
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player1.getAcc().setBalance(1000);
		player2.setField(10);
		player1.setField(10);
		
		boolean HasLost2 = territoryController.landOnField(player2, display, territory, die);
		boolean HasLost1 = territoryController.landOnField(player1, display, territory, die);
		
		assertEquals(0,player1.getAcc().getBalance());
		assertEquals(29000,player2.getAcc().getBalance());
		assertEquals(10,player2.getInventory()[0]);
		assertEquals(0,player1.getInventory()[0]);
		assertEquals(true,HasLost1);
		assertEquals(true,HasLost2);
		
	}
	
	@Test
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	// og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	public void OwnedOtherPayRentBelow() {
		
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player1.getAcc().setBalance(500);
		player2.setField(10);
		player1.setField(10);
		
		boolean HasLost2 = territoryController.landOnField(player2, display, territory, die);
		boolean HasLost1 = territoryController.landOnField(player1, display, territory, die);
		
		assertEquals(28500, player2.getAcc().getBalance());
		assertEquals(10,player2.getInventory()[0]);
		assertEquals(false,HasLost1);
		assertEquals(true,HasLost2);

	}
}
