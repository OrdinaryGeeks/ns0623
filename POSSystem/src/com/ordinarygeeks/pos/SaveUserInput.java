package com.ordinarygeeks.pos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveUserInput {

	static Checkout UserCheckout;
	Scanner input;
	
	boolean ToolCodeValid;
	boolean RentalDaysValid;
	boolean DiscountPercentValid;
	boolean CheckoutDateValid;
	boolean PointOfSaleInstance;
	boolean SystemIOInput;
	public SaveUserInput(boolean isIoInput)
	{
		UserCheckout = new Checkout();
		input = new Scanner(System.in);
		
		SystemIOInput = isIoInput;
		RentalDaysValid = false;
		DiscountPercentValid = false;
		CheckoutDateValid = false;
		PointOfSaleInstance = true;
	}

	public void RunPointOfSale()
	{
		
		do {
		
		String ToolCode = QueryForToolCode();
		if(SaveToolCode(ToolCode))
		{
			int RentalDays = QueryForRentalDays();
			
			if(RentalDays != -1)
			{
			if(SaveRentalDays(RentalDays))
			{
				int DiscountPercent = QueryForDiscountPercent();
				
				if(DiscountPercent != -1)
				{
				if(SaveDiscountPercent(DiscountPercent))
				{
					String CheckoutDate = QueryForDate();
					
					
					if(SaveDate(CheckoutDate))
					{
						
						UserCheckout.ProcessRentalAgreement();
						UserCheckout.RentalAgreement.Print();
					}
				}
			}
				
			}
			}
		}
		
		}while(QueryForRunPointOfSale());
		
		System.out.println("Exiting Point Of Sale System");
		System.exit(0);
		}
	

public boolean QueryForRunPointOfSale()
{
	
	String runPosAgain;
	
	do {
		System.out.println("Do you want to run the pos system again? Y/N");

		runPosAgain = input.nextLine();
 

		if(!runPosAgain.equals("Y") &! runPosAgain.equals("N"))
			System.out.println("Invalid answer");
 
		}
	while(!runPosAgain.equals("Y") &! runPosAgain.equals("N"));
	
	if(runPosAgain.equals("Y"))
	{
		UserCheckout = new Checkout();
		return true;
	}
	else
		return false;
	
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
		if(input.hasNextLine())
		{
			input.nextLine();
		}
		return RentalDays;
		}
		catch(InputMismatchException mismatch)
		{
			if(input.hasNextLine())
			{
				input.nextLine();
			}
		System.out.println("Please enter a whole number greater than 0 for number of rental days");
	
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
			if(SystemIOInput)
			{
				if(input.hasNextLine())
					{
						input.nextLine();
					}
			}
		
			System.out.println(rentalDaysException.getMessage());
			return false;
		}	
	}


public  int QueryForDiscountPercent() {
	
	try {
		
		System.out.println("Enter whole number 0-100 for discount percent");
		
		int DiscountPercent = input.nextInt();
		if(input.hasNextLine())
		{
			input.nextLine();
		}
		return DiscountPercent;
		}
	catch(InputMismatchException mismatch)
		{
			System.out.println("Please enter a whole number 0 - 100 for Discount Percent");
			if(input.hasNextLine())
			{
				input.nextLine();
			}
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
		if(SystemIOInput)
		{
			if(input.hasNextLine())
			{
				input.nextLine();
			}
		}
		System.out.println(DiscountPercentException.getMessage());
		return false;
	}
}

public String QueryForDate()
{
	System.out.println("Enter date for checkout in format mm/dd/yy");
	

	String dateString =input.nextLine();
	
	
	
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

}
