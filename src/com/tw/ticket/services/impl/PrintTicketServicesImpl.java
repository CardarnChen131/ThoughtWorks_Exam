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
		sb.append("***<没钱赚商店>购物清单***\r\n");
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
			sb.append("名称："+name+"， 数量："+currentNum+unit+"， 单价："+prices+"(元)，小计："+printFormat(currentPrices)+"(元)");
			totalPrices += currentPrices;
			//买2赠1
			if(rules.contains("1")&&currentNum>=3)
			{
				currentDiscount = (currentNum - currentNum/3*2 - currentNum%3) * Float.valueOf(prices);
				totalDiscount += currentDiscount;
				sb.append("，节省： "+totalDiscount +"(元)");
				tempStr += "名称："+name+"，数量：" + currentNum/3 + unit+"\r\n";
			}
			//95折
			else if(rules.contains("2"))
			{
				currentDiscount = (float)(currentPrices * 0.05);
				totalDiscount += currentDiscount;
				sb.append("，节省： "+totalDiscount +"(元)");
			}
			sb.append("\r\n");
			
		}
		if(tempStr!="")
		{
			tempStr ="-----------------------\r\n"+"买二赠一商品：\r\n" + tempStr;
			sb.append(tempStr);
		}
		sb.append("-----------------------\r\n"+"总计："+totalPrices+"(元)\r\n");
		if(totalDiscount>0)
		{
			sb.append("节省：" + totalDiscount + "(元)\r\n");
		}
		
		
		
		return sb.toString();
	}
	
	private String printFormat(Float inputNum)
	{
		String output = new DecimalFormat("#.00").format(inputNum).toString();
		return output.startsWith(".") ? 0 + output : output;
	}
	
}
