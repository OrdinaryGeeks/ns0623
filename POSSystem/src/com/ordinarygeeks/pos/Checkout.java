package com.ordinarygeeks.pos;

import java.time.LocalDate;

public class Checkout {

	private String ToolCode;
	private int RentalDayCount;
	private int DiscountPercent;
	private LocalDate CheckoutDate;
	public RentalAgreement RentalAgreement; 
	
	public Checkout()
	{
		RentalAgreement = new RentalAgreement();
		
	}
	
	public Checkout(String ToolCode, int RentalDayCount, int DiscountPercent, LocalDate CheckoutDate)
	{
		this.ToolCode = ToolCode;
		this.RentalDayCount = RentalDayCount;
		this.DiscountPercent = DiscountPercent;
		this.CheckoutDate = CheckoutDate;
		this.RentalAgreement = new RentalAgreement();
	}
	
	public void ProcessRentalAgreement()
	{
		RentalAgreement.SetCheckOutDate(CheckoutDate);
		RentalAgreement.SetRentalDays(RentalDayCount);
		RentalAgreement.SetToolCode(ToolCode);
		RentalAgreement.SetTool();
		RentalAgreement.SetToolBrand();
		RentalAgreement.SetToolType();
		RentalAgreement.SetDiscountPercent(DiscountPercent);
		RentalAgreement.AssignDailyRentalCharge();
		RentalAgreement.CalculateDueDate();
		RentalAgreement.CalculateChargeableDays();
		RentalAgreement.CalculatePreDiscountCharge();
		RentalAgreement.CalculateDiscountAmount();
		RentalAgreement.CalculateFinalCharge();
		
		
		
	}
	
	public void PrintRentalAgreement()
	{
		RentalAgreement.Print();
	}
	
	public LocalDate getCheckoutDate() {
		return CheckoutDate;
	}
	
	public void setCheckoutDate(int year, int month, int day) 
	{
		this.CheckoutDate = LocalDate.of(year,  month,  day);
		
	}
	
	public void setCheckoutDate(String DateString)
	{
		this.CheckoutDate.parse(DateString);
	}

	
	public String getToolCode(){
		return ToolCode;
	}
	public void setToolCode(String ToolCode) throws Exception {
	
		if(PointOfSale.Inventory.GetTool(ToolCode)==null)
			throw new Exception("Tool Code is not in the Inventory");
		this.ToolCode = ToolCode;
	}
	
	public int getRentalDayCount() {
		return RentalDayCount;
	}
	
	public void setRentalDayCount(int RentalDayCount) throws Exception
	{
		if(RentalDayCount < 1)
			throw new Exception("Rental Day Count should be 1 or greater");
		else
			this.RentalDayCount = RentalDayCount;
	}
	
	public int getDiscountPercent()
	{
		return DiscountPercent;
	}
	
	public void setDiscountPercent(int DiscountPercent) throws Exception{
		
		if(DiscountPercent < 0 || DiscountPercent > 100)
			throw new Exception("Discount Percent should be between 0 and 100.");
		else
		{
			
			this.DiscountPercent = DiscountPercent;
		}
		
	}

}