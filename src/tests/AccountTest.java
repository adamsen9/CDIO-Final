package tests;
import entities.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	@Test
	public void testDeposit() {
		Account acc = new Account(1000, 1);
		int expected = 1000;
		assertEquals(expected, acc.getBalance());
		acc.deposit(-100);
		assertEquals(expected, acc.getBalance());
		acc.deposit(100);
		expected += 100;
		assertEquals(expected, acc.getBalance());
		acc.deposit(-500);
		assertEquals(expected, acc.getBalance());
	}
	
	@Test
	public void testWithdraw(){
		Account acc = new Account(1000, 1);
		int expected = 1000;
		assertEquals(expected, acc.getBalance());
		assertEquals(true, acc.withdraw(100)); // balance = 900
		expected -= 100; // expected now 900
		assertEquals(expected, acc.getBalance());
		assertEquals(false, acc.withdraw(-100));
		assertEquals(900, acc.getBalance());
		assertEquals(true, acc.withdraw(0));
		assertEquals(900, acc.getBalance());
		// test that it returns true when the account does have enough money
		assertEquals(true, acc.withdraw(900)); // balance = 800
		expected = 0; // expected now 800
		assertEquals(expected, acc.getBalance()); 		
		// test that it return false when the account doesn't have enough money
		acc.setBalance(100);
		assertEquals(false, acc.withdraw(1000)); // balance doesn't change
		expected = 0;
		assertEquals(expected, acc.getBalance());		
	}
	
	@Test
	public void testTransfer(){
		Account acc1 = new Account(1000, 1);
		Account acc2 = new Account(1000, 1);
		assertEquals(1000, acc1.getBalance());
		assertEquals(1000, acc2.getBalance());
		assertEquals(true, acc1.transfer(acc2, 500));
		assertEquals(500, acc1.getBalance());
		assertEquals(1500, acc2.getBalance());
		assertEquals(false, acc1.transfer(acc2, -500));
		assertEquals(500, acc1.getBalance());
		assertEquals(1500, acc2.getBalance());
		assertEquals(true, acc1.transfer(acc2, 0));
		assertEquals(500, acc1.getBalance());
		assertEquals(1500, acc2.getBalance());
		assertEquals(true, acc1.transfer(acc2, 500));
		assertEquals(0, acc1.getBalance());
		assertEquals(2000, acc2.getBalance());
		acc1.setBalance(500);
		assertEquals(false, acc1.transfer(acc2, 600));
		assertEquals(0, acc1.getBalance());
		assertEquals(2500, acc2.getBalance());
	}

}
