package com.ordinarygeeks.pos;

public class Tool {

	private String Code;
	private String Type;
	private String Brand;
	
	public Tool(String Code, String Type, String Brand)
	{
		this.Code = Code;
		this.Type = Type;
		this.Brand = Brand;
	}
	
	public String getCode(){
		return Code;
	}
	
	public String getType() {
		return Type;
	}
	public String getBrand() {
		return Brand;
	}
	public boolean isWeekendCharge()
	{
		
		if(Type=="Ladder")
			return true;
		else
			return false;
	}
	
	public boolean isHolidayCharge()
	{
		if(Type == "Chainsaw")
			return true;
		else
			return false;
		
	}
}
