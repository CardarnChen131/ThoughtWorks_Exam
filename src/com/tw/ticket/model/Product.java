package com.tw.ticket.model;

/**
 * 产品的实体类
 * @author cyu
 * @since v1.0
 */
public class Product 
{
	private String name;//产品名
	private String unit;//单位
	private String prices;//单价
	private String barcode;//条形码
	private String rules;//打折规则，0是无，1是买二增一，2是95折
	
	/**
	 * 用于测试的构造方法
	 * @param name 名称
	 * @param unit 单位
	 * @param prices 单价
	 * @param barcode 条码
	 * @param rules 规则
	 */
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