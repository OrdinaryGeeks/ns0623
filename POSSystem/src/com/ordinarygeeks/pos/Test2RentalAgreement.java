package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test2RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("LADW");
		pos.SaveUserInput.SaveDate("7/2/20");
		pos.SaveUserInput.SaveRentalDays(3);
		pos.SaveUserInput.SaveDiscountPercent(10);
		
		pos.SaveUserInput.GetUserCheckout().ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("07/05/20",SaveUserInput.GetUserCheckout().RentalAgreement.GetDueDate());
	}

	@Test
	public void testGetToolCode() {
		assertEquals("LADW",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolCode());
	}

	@Test
	public void testGetToolBrand() {
		assertEquals("Werner",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolBrand());
	}

	@Test
	public void testGetToolType() {
		assertEquals("Ladder",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolType());
	}

	@Test
	public void testGetCheckoutDate() {
		assertEquals("07/02/20",SaveUserInput.GetUserCheckout().RentalAgreement.GetCheckoutDate());
	}

	@Test
	public void testGetChargeableDays() {
		assertEquals(2, SaveUserInput.GetUserCheckout().RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$3.98", SaveUserInput.GetUserCheckout().RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$0.40", SaveUserInput.GetUserCheckout().RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$3.58", SaveUserInput.GetUserCheckout().RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$1.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetDailyRentalCharge());
	}

}
