package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test3RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("CHNS");
		pos.SaveUserInput.SaveDate("7/2/15");
		pos.SaveUserInput.SaveRentalDays(5);
		pos.SaveUserInput.SaveDiscountPercent(25);
		
		pos.SaveUserInput.UserCheckout.ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("07/07/15",SaveUserInput.UserCheckout.RentalAgreement.GetDueDate());
	}

	@Test
	public void testGetToolCode() {
		assertEquals("CHNS",SaveUserInput.UserCheckout.RentalAgreement.GetToolCode());
	}

	@Test
	public void testGetToolBrand() {
		assertEquals("Stihl",SaveUserInput.UserCheckout.RentalAgreement.GetToolBrand());
	}

	@Test
	public void testGetToolType() {
		assertEquals("Chainsaw",SaveUserInput.UserCheckout.RentalAgreement.GetToolType());
	}

	@Test
	public void testGetCheckoutDate() {
		assertEquals("07/02/15",SaveUserInput.UserCheckout.RentalAgreement.GetCheckoutDate());
	}

	@Test
	public void testGetChargeableDays() {
		assertEquals(3, SaveUserInput.UserCheckout.RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$4.47", SaveUserInput.UserCheckout.RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$1.12", SaveUserInput.UserCheckout.RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$3.35", SaveUserInput.UserCheckout.RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$1.49", SaveUserInput.UserCheckout.RentalAgreement.GetDailyRentalCharge());
	}

}
