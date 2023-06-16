package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test1RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("JAKR");
		pos.SaveUserInput.SaveDate("9/3/15");
		pos.SaveUserInput.SaveRentalDays(5);
		pos.SaveUserInput.SaveDiscountPercent(101);
		
		pos.SaveUserInput.GetUserCheckout().ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("09/08/15",SaveUserInput.GetUserCheckout().RentalAgreement.GetDueDate());
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
		assertEquals("09/03/15",SaveUserInput.GetUserCheckout().RentalAgreement.GetCheckoutDate());
	}

	@Test
	public void testGetChargeableDays() {
		assertEquals(2, SaveUserInput.GetUserCheckout().RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$5.98", SaveUserInput.GetUserCheckout().RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$0.00", SaveUserInput.GetUserCheckout().RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$5.98", SaveUserInput.GetUserCheckout().RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$2.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetDailyRentalCharge());
	}

}
