package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test6RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("JAKR");
		pos.SaveUserInput.SaveDate("7/2/20");
		pos.SaveUserInput.SaveRentalDays(4);
		pos.SaveUserInput.SaveDiscountPercent(50);
		
		pos.SaveUserInput.GetUserCheckout().ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("07/06/20",SaveUserInput.GetUserCheckout().RentalAgreement.GetDueDate());
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
		assertEquals("07/02/20",SaveUserInput.GetUserCheckout().RentalAgreement.GetCheckoutDate());
	}

	@Test
	public void testGetChargeableDays() {
		assertEquals(1, SaveUserInput.GetUserCheckout().RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$2.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$1.50", SaveUserInput.GetUserCheckout().RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$1.49", SaveUserInput.GetUserCheckout().RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$2.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetDailyRentalCharge());
	}

}
