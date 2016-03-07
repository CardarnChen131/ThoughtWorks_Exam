package com.tw.ticket.model;

public class Product 
{
	private String name;
	private String unit;
	private String prices;
	private String barcode;
	private String rules;
	
	public Product(String name, String unit, String prices, String barcode,
			String rules) 
	{
		super();
		this.name = name;
		this.unit = unit;
		this.prices = prices;
		this.barcode = barcode;
		this.rules = rules;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getUnit() 
	{
		return unit;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
	public String getPrices() 
	{
		return prices;
	}
	public void setPrices(String prices) 
	{
		this.prices = prices;
	}
	public String getBarcode() 
	{
		return barcode;
	}
	public void setBarcode(String barcode) 
	{
		this.barcode = barcode;
	}
	public String getRules() 
	{
		return rules;
	}
	public void setRules(String rules) 
	{
		this.rules = rules;
	}
	
	
}