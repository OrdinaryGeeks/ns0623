package com.ordinarygeeks.pos;

import java.util.Dictionary;
import java.util.Hashtable;

public class Inventory {

	
	Dictionary<String, Tool> Stock;
	
	public Inventory()
	{
		Stock = new Hashtable<String, Tool>();
		
	}
	
	public Tool GetTool(String ToolCode)
	{
		
	return Stock.get(ToolCode);
		
	}
	public void AddTool(Tool Tool)
	{
		Tool stockCheck = Stock.get(Tool.getCode());
		if(stockCheck== null)
		{
			Stock.put(Tool.getCode(), Tool);
		}
	}
}
