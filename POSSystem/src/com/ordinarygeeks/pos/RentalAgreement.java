package com.ordinarygeeks.pos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class RentalAgreement {

	String ToolCode;
	String ToolType;
	String ToolBrand;
	int RentalDays;
	LocalDate CheckOutDate;
	LocalDate DueDate;
	double DailyRentalCharge;
	int ChargeDays;
	double PreDiscountCharge;
	int DiscountPercent;
	Double DiscountAmount;
	double FinalCharge;
	int PartialWeekDays;
	int NumberOfFullWeeks;
	int WeekdayCount;
	int WeekendDays;
	
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
			if(dateA.getMonth() == dateB.getMonth())
				if(dateA.getDayOfMonth() == dateB.getDayOfMonth())
					return true;
		
		return false;
	}
	public void CheckIndependenceDayCurrentYear()
	{
		//Under 365 Days
		LocalDate currentJuly4th = LocalDate.of(CheckOutDate.getYear(), 7, 4);
		if(currentJuly4th.getDayOfWeek() == DayOfWeek.SATURDAY)
			currentJuly4th.withDayOfMonth(3);
		if(currentJuly4th.getDayOfWeek() == DayOfWeek.SUNDAY)
			currentJuly4th.withDayOfMonth(5);
		

	}
	
	public void processHolidaysForYear(int year)
	{
		//Find July 4th
		
		LocalDate currentJuly4th = LocalDate.of(year, 7, 4);
		if(currentJuly4th.getDayOfWeek() == DayOfWeek.SATURDAY)
			currentJuly4th.withDayOfMonth(3);
		else if(currentJuly4th.getDayOfWeek() == DayOfWeek.SUNDAY)
			currentJuly4th.withDayOfMonth(5);
		
		
		//if July4th charge day falls between checkout Date and Due date 
		if(DateALessThanDateB(CheckOutDate, currentJuly4th))
			if(DateALessThanDateB(currentJuly4th, DueDate))
				{
				HolidayDays++;
			}
				
		//If July4th charge day falls on checkout Date or Due Date
		if(DateAEqualsDateB(CheckOutDate, currentJuly4th))
			HolidayDays++;
		if(DateAEqualsDateB(DueDate, currentJuly4th))
			HolidayDays++;
		
		
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
			if(DateALessThanDateB(currentLaborDay, DueDate))
				HolidayDays++;
		
		
		if(DateAEqualsDateB(CheckOutDate, currentLaborDay))
			HolidayDays++;
		if(DateAEqualsDateB(DueDate, currentLaborDay))
			HolidayDays++;
	}
	
	public void Process()
	{
		Tool CustomerTool = PointOfSale.Inventory.GetTool(ToolCode);
		
		ToolBrand= CustomerTool.getBrand();
		ToolType = CustomerTool.getType();
		
		CalculateDueDate();
		GetDailyRentalCharge();
		GetChargeableDays();
		GetPreDiscountCharge();
		GetDiscountPercent();
		GetFinalCharge();
		
		
		Print();
		
		
	}
	
	public void Print() {
		
		System.out.println("Tool code " + ToolCode);
		System.out.println("Tool type " + ToolType);
		System.out.println("Tool brand " + ToolBrand);
		System.out.println("Rental days " + RentalDays);
		System.out.println("Check out date " + DateFormatter(CheckOutDate));
		System.out.println("Due date " + DateFormatter(DueDate));
		System.out.println("Daily rental charge " + CurrencyFormatter(DailyRentalCharge));
		System.out.println("Charge days " + ChargeDays);
		System.out.println("Pre-discount charge " + CurrencyFormatter(PreDiscountCharge));
		System.out.println("Discount Percent " + DiscountPercent + "%");
		System.out.println("Discount amount " + CurrencyFormatter(DiscountAmount));
		System.out.println("Final Charge " + CurrencyFormatter(FinalCharge));
		
		
		
		
		
		
		
	}
	
	public String DateFormatter(LocalDate LocalDate)
	{
		
		
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	
	
	return LocalDate.format(formatter);
		
		
	}
	
	
	
	public void GetChargeableDays()
	{
		
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
	
	public void GetPreDiscountCharge() {
		
		if(ToolType == "Ladder")
		{
			PreDiscountCharge = ChargeDays * DailyRentalCharge;
		}
		if(ToolType == "Chainsaw")
		{
			PreDiscountCharge = ChargeDays * DailyRentalCharge;
		}
		if(ToolType == "Jackhammer")
		{
			PreDiscountCharge = ChargeDays * DailyRentalCharge;
		}
	}
	
	public void SetDiscountPercent(int DiscountPercent)
	{
		this.DiscountPercent = DiscountPercent;
	}

	public void GetDiscountPercent() {
		
		DiscountAmount = PreDiscountCharge * (float)DiscountPercent/100.0f;
		
	}
	public void DiscountAmountRounding()
	{
	
		BigDecimal DiscountAmountBigDecimal = new BigDecimal(Double.toString(DiscountAmount));
		
		DiscountAmountBigDecimal.setScale(2, RoundingMode.HALF_UP);
		
		DiscountAmount = DiscountAmountBigDecimal.doubleValue();
		
	}
	
	public String CurrencyFormatter(double Currency)
	{
		
		Locale locale = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format.format(Currency);
	}
	public void GetFinalCharge() {
		FinalCharge = PreDiscountCharge - DiscountAmount;
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
				WeekendDays += 1;
		break;
		case TUESDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=5)//Saturday
				WeekendDays += 1;
			if(PartialWeekDays == 6)//Sunday
				WeekendDays += 1;
		break;
		case WEDNESDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=4)//Saturday
				WeekendDays += 1;
			if(PartialWeekDays >= 5)//Sunday
				WeekendDays += 1;
			break;
		case THURSDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=3)//Saturday
				WeekendDays += 1;
			if(PartialWeekDays >= 4)//Sunday
				WeekendDays += 1;
			break;
		case FRIDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=2)//Saturday
				WeekendDays += 1;
			if(PartialWeekDays >=3)//Sunday
				WeekendDays += 1;
			break;
		case SATURDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays>=1)//Saturday
				WeekendDays += 1;
			if(PartialWeekDays >=2)//Sunday
				WeekendDays += 1;
			break;
		case SUNDAY:
			WeekendDays += NumberOfFullWeeks * 2;
			if(PartialWeekDays >=1)//Sunday
				WeekendDays += 1;
			break;
		}
		
	}
	
	public void SetToolCode(String ToolCode) {
		
		this.ToolCode = ToolCode;
	}
	public void GetDailyRentalCharge() {
		
		
		if(this.ToolType == "Ladder")
			DailyRentalCharge = 1.99;
		if(this.ToolType == "Chainsaw")
			DailyRentalCharge = 1.49;
		if(this.ToolType == "Jackhammer")
			DailyRentalCharge= 2.99;
	}

	public void SetToolBrand(String ToolBrand) {
		this.ToolBrand = ToolBrand;
	}
	public void SetRentalDays(int RentalDays) {
		this.RentalDays = RentalDays;
	}
	public void SetCheckOutDate(LocalDate CheckOutDate) {
		this.CheckOutDate = CheckOutDate;
	}
}
