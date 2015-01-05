package tests;

import entities.GameBoard;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GameBoardTest {
	GameBoard board;
	
	@Before
	public void setUp(){
		board = new GameBoard();
	}
	
	@Test
	public void testNumberOfFields() {
		assertEquals(board.getNumberOfFields(),21);
	}
}