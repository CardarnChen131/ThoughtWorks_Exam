package com.tw.ticket.services.impl;

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
	/**
	 * 获取产品信息，并封装成固定结果
	 * @author cyu
	 * @since v1.0
	 * @param list 入参数
	 * @return 结果集Map
	 */
	@Override
	public Map<String, Integer> getProductInfo(String list) 
	{
		list = list.replaceAll("\\[", "");
		list = list.replaceAll("\\]", "");
		list = list.replaceAll("'", "");
		String[] arr = list.split(",");
		Map<String,Integer> itemMap = new HashMap<String, Integer>();
		String itemName = "";
		int itemCount = 1;//产品数量统计
		int totalCount = 0;//总数
		int arrLength = arr.length;
		for(int i = 0;i < arrLength; i++)
		{
			//针对条码-数量隔开的模式
			if(arr[i].contains("-"))
			{
				itemName = arr[i].split("-")[0];
				itemCount = Integer.valueOf(arr[i].split("-")[1]);
			}
			else
			{
				itemName = arr[i];
				itemCount = 1; //单独条码默认1个
			}
			
			totalCount = itemMap.get(itemName)==null ? 0 : itemMap.get(itemName);
			itemMap.put(itemName, totalCount+itemCount);
		}
		return itemMap;
	}
	
	/**
	 * 模拟从数据库中查询数据	 
	 * @author cyu
	 * @since v1.0
	 * @param object 输入条码
	 * @return Product 的实体
	 */
	private Product getProduct(Object object)
	{
		//没有注入，采用new接口
		IGetProduct getProductMap = new getProductMapper();
		return getProductMap.getProductByBarcode((String) object);
	}
	
	
	/**
	 * 获取产品信息，并封装成固定结果
	 * @author cyu
	 * @since v1.0
	 * @param resultMap 入参封装结构的结果集
	 * @return 打印结果
	 */
	@Override
	public String printTicketResult(Map<String, Integer> resultMap) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("***<没钱赚商店>购物清单***\r\n");
		sb.append(printItem(resultMap));
		sb.append("*************************\r\n");
		return sb.toString();
	}

	/**
	 * 打印详细信息
	 * @author cyu
	 * @since v1.0
	 * @param resultMap 入参封装结构的结果集
	 * @return
	 */
	private String printItem(Map<String, Integer> resultMap)
	{
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = resultMap.keySet();
		Object[] arrItem = keySet.toArray();
		Product product = null;//产品
		String name = null;//名称
		String unit = null;//单位
		String prices = null;//单价
		String rules = null;//折扣规则
		String tempStr = "";//针对买2增1的单独打印内容
		int currentNum = 0;//产品数
		Float totalPrices = 0f;//总价
		Float totalDiscount = 0f;//总折扣
		Float currentPrices = 0f;//当前价
		Float currentDiscount = 0f;//当前折扣
		for(int i = 0; i < arrItem.length ; i++)
		{
			//获取实体
			product = getProduct(arrItem[i]);
			if(product!=null)
			{
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
				tempStr += "名称："+name+"，数量：" + currentNum/3 + unit+"\r\n";//买二赠一特殊打印
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
		}
		//买二赠一产品特殊打印地方
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
	
	/**
	 * 将价格保留2位小数，精美打印
	 * @author cyu
	 * @since v1.0
	 * @param inputNum 入参数字
	 * @return 保留2位小数格式
	 */
	private String printFormat(Float inputNum)
	{
		String output = new DecimalFormat("#.00").format(inputNum).toString();
		return output.startsWith(".") ? 0 + output : output;
	}
	
}
