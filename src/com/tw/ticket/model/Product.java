package com.tw.ticket.model;

/**
 * ��Ʒ��ʵ����
 * @author cyu
 * @since v1.0
 */
public class Product 
{
	private String name;//��Ʒ��
	private String unit;//��λ
	private String prices;//����
	private String barcode;//������
	private String rules;//���۹���0���ޣ�1�������һ��2��95��
	
	/**
	 * ���ڲ��ԵĹ��췽��
	 * @param name ����
	 * @param unit ��λ
	 * @param prices ����
	 * @param barcode ����
	 * @param rules ����
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