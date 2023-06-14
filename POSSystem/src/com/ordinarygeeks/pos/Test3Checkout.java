package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test3Checkout {

	PointOfSale pos;
	
	@Before
	public void setUp() throws Exception {
		pos.LoadTools();
		pos.InitializeUserInput(false);

		
		
				
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void validToolCode() {

	assertTrue(pos.SaveUserInput.SaveToolCode("CHNS"));

	}
	
	@Test 
	public void validDate() {
		assertTrue(pos.SaveUserInput.SaveDate("7/2/15"));
	}
	
	@Test
	public void validRentalDays() {
		assertTrue(pos.SaveUserInput.SaveRentalDays(5));
	}
	
	@Test
	public void validDiscount() {
		assertFalse(pos.SaveUserInput.SaveDiscountPercent(25));
	}
	
	
}
