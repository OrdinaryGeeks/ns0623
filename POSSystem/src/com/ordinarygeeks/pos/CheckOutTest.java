package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckOutTest {

	
	Checkout CheckOut = new Checkout();
	@Before
	public void setUp() throws Exception {
		CheckOut.setToolCode("JAKR");
		CheckOut.setCheckoutDate(LocalDate.of(2015, 9, 3));
		CheckOut.setRentalDayCount(5);
		CheckOut.setDiscountPercent(101);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
