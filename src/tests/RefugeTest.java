package tests;


import controllers.*;
import entities.*;
import fields.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.TerritoryController;



//Der er lavet test for:
	//landOnfield()
		//Overfører et beløb til spilleren der lander på feltet
			//Formålet med testen er at kontrollere hvorvidt land on field virker korrekt. Dette gøres ved følgende tests:
				//At spillerens balance stiger med det forventede beløb
		//Denne test forudsætter at metoden deposit() i Account deposit fungerer korrekt.
	//setBonus() og getBonus()
		//Ændrer den bonus man får for at lande på et felt og henter selvsamme bonus
			//Formålet med testen er at kontrollere hvorvidt at setBonus() og getBonus() virker korrekt. Dette gøres ved følgende test:
				//At den værdi bonusen i Refuge bliver sat til også bliver returneret. Samtidig bliver det kontrolleret hvorvidt at den mængde en spiller modtager ændrer sig sideløbende.
	//setFieldPossition() og getFieldPossition()
		//Ændrer position på brættet hvor feltet er og returnerer positionen
			//Formålet med testen er at kontrollere hvorvidt at metoderne virker korrekt. Dette gøres ved at:
				//Kontrollere om positionerne stemmer overens med det forventede
				//Kontrollere om der efter ændring af positionen stadig returneres den forventede position af feltet

public class RefugeTest {
	OurRefuge refuge;
	OurRefuge refuge2;
	RefugeController refugeController;
	Die die;
	Player player;
	GUIManager display;
	
	
	@Before
	public void setUp(){
		die = new Die();
		refuge = new OurRefuge(500, "Monastery", 13, 12);
		refugeController = new RefugeController();
		display = new GUIManager("test", "", "");
		player = new Player();
	}
	
	
	@Test
	public void testLandingOnField() {
		assertEquals(500, refuge.getBonus());
		assertEquals(true, refugeController.landOnField(player, display, refuge, die));
		assertEquals(player.getAcc().getBalance(),30500);
	}
	
	@Test
	public void testSetGet(){
		refuge.setBonus(5000);
		assertEquals(refuge.getBonus(), 5000);
		assertEquals(true, refugeController.landOnField(player, display, refuge, die));
		assertEquals(player.getAcc().getBalance(),35000);
	}
	
	@Test
	public void testFieldPosition(){
		//To refuge testes
		refuge2 = new OurRefuge(800, "København", 18, 6);
		assertEquals(refuge.getFieldPossition(), 12);
		assertEquals(refuge2.getFieldPossition(), 6);
		
		//Positionerne ændres
		refuge.setFieldPossition(10);
		refuge2.setFieldPossition(48);
		
		//Det testes om værdierne stemmer overens
		assertEquals(refuge.getFieldPossition(), 10);
		assertEquals(refuge2.getFieldPossition(), 48);
	}
}
