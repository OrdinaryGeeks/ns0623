package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SaveUserInputTest {

	PointOfSale pos;
	
	@Before
	public void setUp() throws Exception {
		pos.LoadTools();
		pos.InitializeUserInput();
		
		
		
				
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void invalidToolCode() {
		
		
		
		  
		  assertFalse( pos.SaveUserInput.SaveToolCode("JBOL"));
		
	}
	
	@Test
	public void validToolCode() {

	assertTrue(pos.SaveUserInput.SaveToolCode("JAKR"));

	}
	
	
}
