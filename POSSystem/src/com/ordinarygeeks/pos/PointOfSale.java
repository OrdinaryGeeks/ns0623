package com.ordinarygeeks.pos;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PointOfSale {

	
	public static Inventory Inventory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Checkout UserCheckout = new Checkout();
		String UserToolCode;
		int RentalDays=0;
		LocalDate RentalDate = null;
		int Year =0;
		int Month =0;
		int Day =0;
		boolean RentalDaysValid=false;
		boolean DiscountPercentValid = false;
		int DiscountPercent = 0;
		boolean CheckoutDateValid = false;
		LoadTools();
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Point of Sale 2.0");
		System.out.println("Enter the code for the tool you want");
		UserToolCode=in.nextLine();
		if(Inventory.GetTool(UserToolCode)==null)
			do {
			System.out.println("Please enter a valid code.");
			System.out.println("Enter the code for the tool you want");
			UserToolCode = in.nextLine();
			}
			while(Inventory.GetTool(UserToolCode)==null);
		
		try {
		UserCheckout.setToolCode(UserToolCode);
		}
		catch(Exception setToolCodeException)
		{
			System.out.println(setToolCodeException.getMessage());
		}
		
		do {
		System.out.println("Enter the number of days you wish to rent the tool");
		try {
		RentalDays = in.nextInt();
		
		UserCheckout.setRentalDayCount(RentalDays);
		
		RentalDaysValid = true;
		}
		catch(InputMismatchException mismatch)
		{
		System.out.println("Please enter a whole number for number of rental days");
		in.nextLine();
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		}while(RentalDaysValid == false);
		
		
		do {
		System.out.println("Enter a whole number for the Discount Percent to be applied");
		try {
		DiscountPercent = in.nextInt();
		
		UserCheckout.setDiscountPercent(DiscountPercent);
		
		DiscountPercentValid = true;
		}
		catch(InputMismatchException mismatch)
		{
		System.out.println("Please enter a whole number for number for Discount Percent");
		in.nextLine();
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		}while(DiscountPercentValid == false);
		
		
		do {
			try {
		System.out.println("Enter year for checkout reservation ");
		Year = in.nextInt();
		if(Year >= 0)
		{
		System.out.println("Enter month for checkout reservation");
		Month = in.nextInt();
		
		if(Month >= 1 && Month<=12)
		{
			
			RentalDate = LocalDate.of(Year,  Month, 1);
		System.out.println("Enter day for checkout reservation");
		Day = in.nextInt();
		if(Day >= 1 && Day <=RentalDate.lengthOfMonth())
		{
		RentalDate = RentalDate.withDayOfMonth(Day);
		}
		else			
		{
		throw new DateTimeException("Invalid Day entered for the month");
		}
		}
		else
			throw new DateTimeException("Invalid Month entered.  Must be between 1 and 12.");
		}
		else
			throw new DateTimeException("Invalid Year entered.  Year must be greater than zero.");
		UserCheckout.setCheckoutDate(RentalDate);
		
		CheckoutDateValid = true;
		}
		catch(InputMismatchException mismatch)
		{
		System.out.println("Please enter a whole number for number for the Month, Day, and Year");
		in.nextLine();
		}
		catch(DateTimeException incorrectDate)
		{
			System.out.println(incorrectDate.getMessage());
		
		System.out.println("Please enter a valid Date");		
				
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		}while(CheckoutDateValid == false);
		
		UserCheckout.ProcessRentalAgreement();
	}

	public static void LoadTools()
	{
		Inventory = new Inventory();
	
		
		Inventory.AddTool(new Tool("CHNS", "Chainsaw", "Stihl"));
		Inventory.AddTool(new Tool("LADW", "Ladder", "Werner"));
		Inventory.AddTool(new Tool("JAKD", "Jackhammer", "DeWalt"));
		Inventory.AddTool(new Tool("JAKR", "Jackhammer", "Ridgid"));
		
	}
	

	
}
