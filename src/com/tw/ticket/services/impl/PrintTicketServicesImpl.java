package com.tw.ticket.services.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.tw.ticket.dao.IGetProduct;
import com.tw.ticket.mapper.getProductMapper;
import com.tw.ticket.model.Product;
import com.tw.ticket.services.IPrintTicketServices;

public class PrintTicketServicesImpl implements IPrintTicketServices
{

	@Override
	public Map<String, Integer> getProductInfo(String list) 
	{
		String[] arr = list.split(",");
		Map<String,Integer> itemMap = new HashMap<String, Integer>();
		String itemName = "";
		int itemCount = 1;
		int tempCount = 0;
		int arrLength = arr.length;
		for(int i = 0;i < arrLength; i++)
		{
			if(arr[i].contains("-"))
			{
				itemName = arr[i].split("-")[0];
				itemCount = Integer.valueOf(arr[i].split("-")[1]);
			}
			else
			{
				itemName = arr[i];
				itemCount = 1;
			}
			
			tempCount = itemMap.get(itemName)==null ? 0 : itemMap.get(itemName);
			itemMap.put(itemName, tempCount+itemCount);
		}
		return itemMap;
	}
	
	private Product getProduct(Object object)
	{
		IGetProduct getProductMap = new getProductMapper();
		return getProductMap.getProductByBarcode((String) object);
	}
	
	

	@Override
	public String printTicketResult(Map<String, Integer> resultMap) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("***<ûǮ׬�̵�>�����嵥***\r\n");
		sb.append(printItem(resultMap));
		sb.append("*************************\r\n");
		return sb.toString();
	}

	private String printItem(Map<String, Integer> resultMap)
	{
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = resultMap.keySet();
		Object[] arrItem = keySet.toArray();
		Product product = null;
		String name = null;
		String unit = null;
		String prices = null;
		String rules = null;
		String tempStr = "";
		int currentNum = 0;
		Float totalPrices = 0f;
		Float totalDiscount = 0f;
		Float currentPrices = 0f;
		Float currentDiscount = 0f;
		for(int i = 0; i < arrItem.length ; i++)
		{
			product = getProduct(arrItem[i]);
			name = product.getName();
			unit = product.getUnit();
			prices = product.getPrices();
			rules = product.getRules();
			currentNum = resultMap.get(arrItem[i]);
			currentPrices = Float.valueOf(prices)*currentNum;
			sb.append("���ƣ�"+name+"�� ������"+currentNum+unit+"�� ���ۣ�"+prices+"(Ԫ)��С�ƣ�"+printFormat(currentPrices)+"(Ԫ)");
			totalPrices += currentPrices;
			//��2��1
			if(rules.contains("1")&&currentNum>=3)
			{
				currentDiscount = (currentNum - currentNum/3*2 - currentNum%3) * Float.valueOf(prices);
				totalDiscount += currentDiscount;
				sb.append("����ʡ�� "+totalDiscount +"(Ԫ)");
				tempStr += "���ƣ�"+name+"��������" + currentNum/3 + unit+"\r\n";
			}
			//95��
			else if(rules.contains("2"))
			{
				currentDiscount = (float)(currentPrices * 0.05);
				totalDiscount += currentDiscount;
				sb.append("����ʡ�� "+totalDiscount +"(Ԫ)");
			}
			sb.append("\r\n");
			
		}
		if(tempStr!="")
		{
			tempStr ="-----------------------\r\n"+"�����һ��Ʒ��\r\n" + tempStr;
			sb.append(tempStr);
		}
		sb.append("-----------------------\r\n"+"�ܼƣ�"+totalPrices+"(Ԫ)\r\n");
		if(totalDiscount>0)
		{
			sb.append("��ʡ��" + totalDiscount + "(Ԫ)\r\n");
		}
		
		
		
		return sb.toString();
	}
	
	private String printFormat(Float inputNum)
	{
		String output = new DecimalFormat("#.00").format(inputNum).toString();
		return output.startsWith(".") ? 0 + output : output;
	}
	
}
