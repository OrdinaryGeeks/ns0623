package com.ordinarygeeks.pos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class RentalAgreement {

	private String ToolCode;
	private 	String ToolType;
	private String ToolBrand;
	private int RentalDays;
	private LocalDate CheckOutDate;
	private LocalDate DueDate;
	private BigDecimal DailyRentalCharge;
	private int ChargeDays;
	private BigDecimal PreDiscountCharge;
	private int DiscountPercent;
	private BigDecimal DiscountAmount;
	private BigDecimal FinalCharge;
	private int PartialWeekDays;
	private int NumberOfFullWeeks;
	private int WeekdayCount;
	private int WeekendDays;
	
	Tool CustomerTool;
	
	int HolidayDays;
	LocalDate[] July4ths;
	LocalDate[] LaborDays;
	public RentalAgreement() {
		
		HolidayDays = 0;
		WeekendDays =0;
	}
	
	public void CalculateDueDate()
	{

		DueDate = CheckOutDate.plusDays(RentalDays);
		
	}
	
	public String GetDueDate()
	{
		
		return DateFormatter(DueDate);
	}
	
	public boolean DateALessThanDateB(LocalDate dateA, LocalDate dateB)
	{
		if(dateA.getYear() < dateB.getYear())
			{
				return true;
			}
		else if(dateA.getYear() == dateB.getYear())
		{
		 if(dateA.getMonthValue()<dateB.getMonthValue())
			 {
			 return true;
			 }
		 else if(dateA.getMonthValue() == dateB.getMonthValue())
		 {
			 if(dateA.getDayOfMonth() < dateB.getDayOfMonth())
			 {	 return true;
			 
			 }
		 }
		}
			return false;
	}
	public boolean DateAEqualsDateB(LocalDate dateA, LocalDate dateB)
	{
		
		if(dateA.getYear() == dateB.getYear())
		{
			if(dateA.getMonthValue() == dateB.getMonthValue())
			{
				if(dateA.getDayOfMonth() == dateB.getDayOfMonth())
				{
					return true;
				}
			}
		}
		return false;
	}

	public void processHolidaysForYear(int year)
	{
		
		ProcessIndependenceDays(year);
		ProcessLaborDays(year);
		

	}
	
	public void ProcessIndependenceDays(int year)
	{

		//Find July 4th
		
		LocalDate currentJuly4th = LocalDate.of(year, 7, 4);
		if(currentJuly4th.getDayOfWeek() == DayOfWeek.SATURDAY)
		{
			currentJuly4th.withDayOfMonth(3);
		}
		else if(currentJuly4th.getDayOfWeek() == DayOfWeek.SUNDAY)
		{
			currentJuly4th.withDayOfMonth(5);
		}
		
		
		//if July4th charge day falls between checkout Date and Due date 
		if(DateALessThanDateB(CheckOutDate, currentJuly4th))
		{
			if(DateALessThanDateB(currentJuly4th, DueDate))
				{
				HolidayDays++;
				}
		}		
		//If July4th charge day falls on checkout Date or Due Date
		if(DateAEqualsDateB(CheckOutDate, currentJuly4th))
		{
			HolidayDays++;
		}
		if(DateAEqualsDateB(DueDate, currentJuly4th))
		{
			HolidayDays++;
		}
	}
	
	public void ProcessLaborDays(int year) {
		
		
		LocalDate Sept1st = LocalDate.of(year,  9, 1);
		LocalDate currentLaborDay = null;
		switch(Sept1st.getDayOfWeek())
		{
		case MONDAY:
			currentLaborDay = Sept1st;
			break;
		case TUESDAY:
			currentLaborDay = LocalDate.of(year,  9,  7);
			break;
		case WEDNESDAY:
			currentLaborDay = LocalDate.of(year,  9,  6);
			break;
		case THURSDAY:
			currentLaborDay = LocalDate.of(year,  9,  5);
			break;
		case FRIDAY:
			currentLaborDay = LocalDate.of(year,  9,  4);
			break;
		case SATURDAY:
			currentLaborDay = LocalDate.of(year,  9,  3);
			break;
		case SUNDAY:
			currentLaborDay = LocalDate.of(year, 9,  2);
			break;
		}
		
		//if currentLabor Day falls between checkout Date and due date or on one or the other
		if(DateALessThanDateB(CheckOutDate, currentLaborDay))
		{
			if(DateALessThanDateB(currentLaborDay, DueDate))
			{
				HolidayDays++;
			}
		}
		
		if(DateAEqualsDateB(CheckOutDate, currentLaborDay))
		{
			HolidayDays++;
		}
		if(DateAEqualsDateB(DueDate, currentLaborDay))
		{
			HolidayDays++;
		}
	}

	
	public void SetTool()
	{
		CustomerTool = PointOfSale.Inventory.GetTool(ToolCode);
		
	}
	public void SetToolType()
	{
		ToolType = CustomerTool.getType();
	}
	
	public void SetToolBrand()
	{
		ToolBrand = CustomerTool.getBrand();
	}
	
	public String GetToolCode() {
		return ToolCode;
	}
	
	public String GetToolBrand() {
		return ToolBrand;
	}
	public String GetToolType() {
		
		return ToolType;
	}
	
	public void Print() {
		
		System.out.println("Tool code " + GetToolCode());
		System.out.println("Tool type " + GetToolType());
		System.out.println("Tool brand " + GetToolBrand());
		System.out.println("Rental days " + GetRentalDays());
		System.out.println("Check out date " +GetCheckoutDate()); 
		System.out.println("Due date " + GetDueDate());
		System.out.println("Daily rental charge " + GetDailyRentalCharge());
		System.out.println("Charge days " + GetChargeableDays());
		System.out.println("Pre-discount charge " + GetPreDiscountCharge());
		System.out.println("Discount Percent " + GetDiscountPercent());
		System.out.println("Discount amount " + GetDiscountAmount());
		System.out.println("Final Charge " + GetFinalCharge());
	
	}
	
	public String GetCheckoutDate()
	{
		
		return DateFormatter(CheckOutDate);
	}
	public String DateFormatter(LocalDate LocalDate)
	{
		
		
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	
	
	return LocalDate.format(formatter);
		
		
	}
	
	
	public void CalculateChargeableDays() {
	//get start year 
		
		int StartYear=CheckOutDate.getYear();
		int EndYear =DueDate.getYear();
		
		int YearsDifference = EndYear - StartYear;
		if(ToolType == "Ladder" || ToolType == "Jackhammer")
		{
			
			processHolidaysForYear(StartYear);
			for(int i = 0; i<YearsDifference; i++)
			{
				processHolidaysForYear(StartYear+i);
			}
		}
		if(ToolType == "Chainsaw" || ToolType == "Jackhammer")
		{
			CountWeekendDays();
		}
		
		ChargeDays = RentalDays - WeekendDays - HolidayDays;
		
	}
	
	public int GetChargeableDays()
	{
			return ChargeDays;
	}
	
	public void CalculatePreDiscountCharge() {
		
		if(ToolType == "Ladder" || ToolType == "Chainsaw" || ToolType=="Jackhammer")
		{
			PreDiscountCharge =  DailyRentalCharge.multiply(new BigDecimal(ChargeDays));
		}
			
	}
	
	public String GetPreDiscountCharge() {
		
	return CurrencyFormatter(PreDiscountCharge);

	}
	
	public void SetDiscountPercent(int DiscountPercent)
	{
		this.DiscountPercent = DiscountPercent;
	}
	
	public String GetDiscountPercent() {
		
		return DiscountPercent + "%";
	}

	
	public void CalculateDiscountAmount() {
		
		DiscountAmount = new BigDecimal(DiscountPercent/100.0).multiply(PreDiscountCharge);
		
		DiscountAmount = DiscountAmount.setScale(2, RoundingMode.HALF_UP);	
	}
	public String GetDiscountAmount() {
		
		return CurrencyFormatter(DiscountAmount);
	}

	public String CurrencyFormatter(BigDecimal Currency)
	{
		
		Locale locale = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format.format(Currency);
	}
	
	public String CurrencyFormatter(long Currency)
	{
		
		Locale locale = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format.format(Currency);
	}
	
	public void CalculateFinalCharge() {
		

		FinalCharge = PreDiscountCharge.subtract(DiscountAmount);
	}
	public String GetFinalCharge() {
	
		return CurrencyFormatter(FinalCharge);
	}
	public void CountWeekendDays()
	{
		
		NumberOfFullWeeks = RentalDays / 7;
		PartialWeekDays = RentalDays % 7;
		WeekendDays =0;
		switch(CheckOutDate.getDayOfWeek()) {
		
		case MONDAY:
			WeekendDays += NumberOfFullWeeks*2;
			if(PartialWeekDays == 6)//Saturday
			{
				WeekendDays += 1;
			}
		break;
		case TUESDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=5)//Saturday
			{
				WeekendDays += 1;
			}
			if(PartialWeekDays == 6)//Sunday
			{
				WeekendDays += 1;
			}
		break;
		case WEDNESDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=4)//Saturday
			{
				WeekendDays += 1;
			}
			if(PartialWeekDays >= 5)//Sunday
			{
				WeekendDays += 1;
			}
			break;
		case THURSDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=3)//Saturday
			{
				WeekendDays += 1;
			}
			if(PartialWeekDays >= 4)//Sunday
			{
				WeekendDays += 1;
			}
			break;
		case FRIDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=2)//Saturday
			{
				WeekendDays += 1;
			}
			if(PartialWeekDays >=3)//Sunday
			{
				WeekendDays += 1;
			}
			break;
		case SATURDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=1)//Saturday
			{
				WeekendDays += 1;
			}
			if(PartialWeekDays >=2)//Sunday
			{
				WeekendDays += 1;
			}
			break;
		case SUNDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays >=1)//Sunday
			{
				WeekendDays += 1;
			}
			break;
		}
		
	}
	
	public void SetToolCode(String ToolCode) {
		
		this.ToolCode = ToolCode;
	}
	
	public void AssignDailyRentalCharge() {
		
		if(this.ToolType == "Ladder")
		{
			DailyRentalCharge = new BigDecimal(1.99);
		}
		if(this.ToolType == "Chainsaw")
		{
			DailyRentalCharge = new BigDecimal(1.49);
		}
		if(this.ToolType == "Jackhammer")
		{
			DailyRentalCharge= new BigDecimal(2.99);
		}
	}
	public String GetDailyRentalCharge() {
		
		return CurrencyFormatter(DailyRentalCharge);
	}

	public void SetToolBrand(String ToolBrand) {
		this.ToolBrand = ToolBrand;
	}
	public int GetRentalDays()
	{
		return this.RentalDays;
		
	}
	public void SetRentalDays(int RentalDays) {
		this.RentalDays = RentalDays;
	}
	public void SetCheckOutDate(LocalDate CheckOutDate) {
		this.CheckOutDate = CheckOutDate;
	}
}
