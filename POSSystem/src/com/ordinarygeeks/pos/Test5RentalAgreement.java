package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test5RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("JAKR");
		pos.SaveUserInput.SaveDate("7/2/15");
		pos.SaveUserInput.SaveRentalDays(9);
		pos.SaveUserInput.SaveDiscountPercent(0);
		
		pos.SaveUserInput.GetUserCheckout().ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("07/11/15",SaveUserInput.GetUserCheckout().RentalAgreement.GetDueDate());
	}

	@Test
	public void testGetToolCode() {
		assertEquals("JAKR",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolCode());
	}

	@Test
	public void testGetToolBrand() {
		assertEquals("Ridgid",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolBrand());
	}

	@Test
	public void testGetToolType() {
		assertEquals("Jackhammer",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolType());
	}

	@Test
	public void testGetCheckoutDate() {
		assertEquals("07/02/15",SaveUserInput.GetUserCheckout().RentalAgreement.GetCheckoutDate());
	}

	@Test
	public void testGetChargeableDays() {
		assertEquals(6, SaveUserInput.GetUserCheckout().RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$17.94", SaveUserInput.GetUserCheckout().RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$0.00", SaveUserInput.GetUserCheckout().RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$17.94", SaveUserInput.GetUserCheckout().RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$2.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetDailyRentalCharge());
	}

}
