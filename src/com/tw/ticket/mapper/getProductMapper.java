package com.tw.ticket.mapper;

import java.util.HashMap;
import java.util.Map;

import com.tw.ticket.dao.IGetProduct;
import com.tw.ticket.model.Product;

public class getProductMapper implements IGetProduct{

	private static Map<String,Product> initMap = new HashMap<String, Product>();
	
	private void init()
	{
		initMap.put("ITEM000001", new Product("�ɿڿ���", "ƿ", "3.00", "ITEM000001", "0"));
		initMap.put("ITEM000002", new Product("��ë��", "��", "1.00", "ITEM000002", "1"));
		initMap.put("ITEM000003", new Product("ƻ��", "��", "5.50", "ITEM000003", "2"));
		initMap.put("ITEM000004", new Product("�ൺ9��", "��", "4.00", "ITEM000004", "2"));
		initMap.put("ITEM000005", new Product("������", "��", "9.00", "ITEM000005", "1,2"));
	}
	
	public getProductMapper()
	{
		init();
	}
	
	@Override
	public Product getProductByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return initMap.get(barcode);
	}

}
