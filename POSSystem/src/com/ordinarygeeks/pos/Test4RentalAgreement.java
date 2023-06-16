package com.ordinarygeeks.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test4RentalAgreement {
	
	PointOfSale pos;
	@Before
	public void setUp() throws Exception {
		
		pos.LoadTools();
		pos.InitializeUserInput(false);
		
		
		pos.SaveUserInput.SaveToolCode("JAKD");
		pos.SaveUserInput.SaveDate("9/3/15");
		pos.SaveUserInput.SaveRentalDays(6);
		pos.SaveUserInput.SaveDiscountPercent(0);
		
		pos.SaveUserInput.GetUserCheckout().ProcessRentalAgreement();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDueDate() {
		
		assertEquals("09/09/15",SaveUserInput.GetUserCheckout().RentalAgreement.GetDueDate());
	}

	@Test
	public void testGetToolCode() {
		assertEquals("JAKD",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolCode());
	}

	@Test
	public void testGetToolBrand() {
		assertEquals("DeWalt",SaveUserInput.GetUserCheckout().RentalAgreement.GetToolBrand());
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
		assertEquals(3, SaveUserInput.GetUserCheckout().RentalAgreement.GetChargeableDays());
	}

	@Test
	public void testGetPreDiscountCharge() {

		assertEquals("$8.97", SaveUserInput.GetUserCheckout().RentalAgreement.GetPreDiscountCharge());
	}

	@Test
	public void testGetDiscountAmount() {
		assertEquals("$0.00", SaveUserInput.GetUserCheckout().RentalAgreement.GetDiscountAmount());
	}

	@Test
	public void testGetFinalCharge() {
		assertEquals("$8.97", SaveUserInput.GetUserCheckout().RentalAgreement.GetFinalCharge());
	}

	@Test
	public void testGetDailyRentalCharge() {
		assertEquals("$2.99", SaveUserInput.GetUserCheckout().RentalAgreement.GetDailyRentalCharge());
	}

}
