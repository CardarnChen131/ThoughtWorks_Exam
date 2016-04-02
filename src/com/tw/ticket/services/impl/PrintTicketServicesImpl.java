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
	 * ��ȡ��Ʒ��Ϣ������װ�ɹ̶����
	 * @author cyu
	 * @since v1.0
	 * @param list �����
	 * @return �����Map
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
		int itemCount = 1;//��Ʒ����ͳ��
		int totalCount = 0;//����
		int arrLength = arr.length;
		for(int i = 0;i < arrLength; i++)
		{
			//�������-����������ģʽ
			if(arr[i].contains("-"))
			{
				itemName = arr[i].split("-")[0];
				itemCount = Integer.valueOf(arr[i].split("-")[1]);
			}
			else
			{
				itemName = arr[i];
				itemCount = 1; //��������Ĭ��1��
			}
			
			totalCount = itemMap.get(itemName)==null ? 0 : itemMap.get(itemName);
			itemMap.put(itemName, totalCount+itemCount);
		}
		return itemMap;
	}
	
	/**
	 * ģ������ݿ��в�ѯ����	 
	 * @author cyu
	 * @since v1.0
	 * @param object ��������
	 * @return Product ��ʵ��
	 */
	private Product getProduct(Object object)
	{
		//û��ע�룬����new�ӿ�
		IGetProduct getProductMap = new getProductMapper();
		return getProductMap.getProductByBarcode((String) object);
	}
	
	
	/**
	 * ��ȡ��Ʒ��Ϣ������װ�ɹ̶����
	 * @author cyu
	 * @since v1.0
	 * @param resultMap ��η�װ�ṹ�Ľ����
	 * @return ��ӡ���
	 */
	@Override
	public String printTicketResult(Map<String, Integer> resultMap) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("***<ûǮ׬�̵�>�����嵥***\r\n");
		sb.append(printItem(resultMap));
		sb.append("*************************\r\n");
		return sb.toString();
	}

	/**
	 * ��ӡ��ϸ��Ϣ
	 * @author cyu
	 * @since v1.0
	 * @param resultMap ��η�װ�ṹ�Ľ����
	 * @return
	 */
	private String printItem(Map<String, Integer> resultMap)
	{
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = resultMap.keySet();
		Object[] arrItem = keySet.toArray();
		Product product = null;//��Ʒ
		String name = null;//����
		String unit = null;//��λ
		String prices = null;//����
		String rules = null;//�ۿ۹���
		String tempStr = "";//�����2��1�ĵ�����ӡ����
		int currentNum = 0;//��Ʒ��
		Float totalPrices = 0f;//�ܼ�
		Float totalDiscount = 0f;//���ۿ�
		Float currentPrices = 0f;//��ǰ��
		Float currentDiscount = 0f;//��ǰ�ۿ�
		for(int i = 0; i < arrItem.length ; i++)
		{
			//��ȡʵ��
			product = getProduct(arrItem[i]);
			if(product!=null)
			{
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
				tempStr += "���ƣ�"+name+"��������" + currentNum/3 + unit+"\r\n";//�����һ�����ӡ
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
		}
		//�����һ��Ʒ�����ӡ�ط�
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
	
	/**
	 * ���۸���2λС����������ӡ
	 * @author cyu
	 * @since v1.0
	 * @param inputNum �������
	 * @return ����2λС����ʽ
	 */
	private String printFormat(Float inputNum)
	{
		String output = new DecimalFormat("#.00").format(inputNum).toString();
		return output.startsWith(".") ? 0 + output : output;
	}
	
}
