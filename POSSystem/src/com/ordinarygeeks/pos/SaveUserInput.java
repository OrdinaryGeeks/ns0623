package com.ordinarygeeks.pos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveUserInput {

	Checkout UserCheckout;
	Scanner input;
	public SaveUserInput()
	{
		UserCheckout = new Checkout();
		input = new Scanner(System.in);
	}

	public void RunPointOfSale()
	{
		String ToolCode = QueryForToolCode();
		if(SaveToolCode(ToolCode))
		{
			int RentalDays = QueryForRentalDays();
			
			if(SaveRentalDays(RentalDays))
			{
				int DiscountPercent = QueryForDiscountPercent();
				
				if(SaveDiscountPercent(DiscountPercent))
				{
					String CheckoutDate = QueryForDate();
					
					
					if(SaveDate(CheckoutDate))
					{
						
						UserCheckout.ProcessRentalAgreement();
					}
				}
				
			}
			
		}
	}
	
	public String QueryForDate()
	{
		System.out.println("Enter date for checkout in format mm/dd/yy");
		
	
		String dateString =input.next();
		
		
		
		return dateString;
		
		
	}
	

	public int ParseString(String parseable)
	{
		
		return Integer.parseInt(parseable);
	}
public  boolean SaveDate(String DateString)
{
	
	String[] DateSplitArray = DateString.split("/");

	try {
		UserCheckout.setCheckoutDate(2000+ParseString(DateSplitArray[2]), ParseString(DateSplitArray[0]), ParseString(DateSplitArray[1]));
		return true;
	}
	catch(DateTimeException CheckoutDateException)
	{
	    System.out.println("There is an error with the checkout date");
	    return false;
	}
	
}

public  int QueryForYear() {
	
	System.out.println("Enter year for checkout reservation ");
	
	
	int Year = input.nextInt();
	return Year;
	

		
}
public  int QueryForMonth() {
	
	System.out.println("Enter month for checkout reservation ");
	
	
	int Month = input.nextInt();
	return Month;


		
}

public  int QueryForDay() {
	
	System.out.println("Enter day for checkout reservation ");
	
	
	int Day = input.nextInt();
	return Day;
	

		
}
public  int QueryForDiscountPercent() {
	
	try {
		
		System.out.println("Enter whole number 0-100 for discount percent");
		
		int DiscountPercent = input.nextInt();	
		return DiscountPercent;
		}
	catch(InputMismatchException mismatch)
		{
			System.out.println("Please enter a whole number for Discount Percent");
			input.nextLine();
			
			return -1;
			}
	
}
public  boolean SaveDiscountPercent(int DiscountPercent) {
	
	try {

	UserCheckout.setDiscountPercent(DiscountPercent);
	return true;
	}

	catch(Exception DiscountPercentException)
	{
		System.out.println(DiscountPercentException.getMessage());
		return false;
	}
}

public  String QueryForToolCode() {
		
		String UserToolCode;
		System.out.println("Enter the code for the tool you want");
		UserToolCode=input.nextLine();
		
		return UserToolCode;
	}
public  boolean SaveToolCode(String UserToolCode) {
					
		try {
		UserCheckout.setToolCode(UserToolCode);
		return true;
		}
		catch(Exception setToolCodeException)
		{
			System.out.println(setToolCodeException.getMessage());
			return false;
		}
	}
	
public int QueryForRentalDays() {
		
		int RentalDays;
		System.out.println("Enter the number of days you wish to rent the tool");
		
		try {
		RentalDays = input.nextInt();
		return RentalDays;
		}
		catch(InputMismatchException mismatch)
		{
		System.out.println("Please enter a whole number for number of rental days");
		input.nextLine();
		return -1;
		}
		
		
	}
	
public  boolean SaveRentalDays(int RentalDays)
	{
		
		try {
		
		UserCheckout.setRentalDayCount(RentalDays);
		return true;
		}
	
		catch(Exception rentalDaysException)
		{
			System.out.println(rentalDaysException.getMessage());
			return false;
		}	
	}
}
