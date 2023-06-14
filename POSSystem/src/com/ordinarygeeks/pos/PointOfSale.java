package com.ordinarygeeks.pos;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PointOfSale {

	
	public static Inventory Inventory;
	public static SaveUserInput SaveUserInput;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoadTools();
		
		InitializeUserInput(true);
		
		SaveUserInput.RunPointOfSale();

}


	public static void InitializeUserInput(boolean IOInput)
	{
		SaveUserInput = new SaveUserInput(IOInput);
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
