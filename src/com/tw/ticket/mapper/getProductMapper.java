package com.tw.ticket.mapper;

import java.util.HashMap;
import java.util.Map;

import com.tw.ticket.dao.IGetProduct;
import com.tw.ticket.model.Product;

public class getProductMapper implements IGetProduct{

	private static Map<String,Product> initMap = new HashMap<String, Product>();
	
	private void init()
	{
		initMap.put("ITEM000001", new Product("¿É¿Ú¿ÉÀÖ", "Æ¿", "3.00", "ITEM000001", "0"));
		initMap.put("ITEM000002", new Product("ÓðÃ«Çò", "¸ö", "1.00", "ITEM000002", "1"));
		initMap.put("ITEM000003", new Product("Æ»¹û", "½ï", "5.50", "ITEM000003", "2"));
		initMap.put("ITEM000004", new Product("Çàµº9¶È", "Ìý", "4.00", "ITEM000004", "2"));
		initMap.put("ITEM000005", new Product("µ°»ÆÅÉ", "´ü", "9.00", "ITEM000005", "1,2"));
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
