package com.tw.ticket.mapper;

import java.util.HashMap;
import java.util.Map;

import com.tw.ticket.dao.IGetProduct;
import com.tw.ticket.model.Product;

/**
 * 模拟数据库查询
 * @author cyu
 * @since v1.0
 *
 */
public class getProductMapper implements IGetProduct{

	private static Map<String,Product> initMap = new HashMap<String, Product>();
	
	/**
	 * 数据初始化
	 */
	private void init()
	{
		initMap.put("ITEM000001", new Product("可口可乐", "瓶", "3.00", "ITEM000001", "0"));
		initMap.put("ITEM000002", new Product("羽毛球", "个", "1.00", "ITEM000002", "1"));
		initMap.put("ITEM000003", new Product("苹果", "斤", "5.50", "ITEM000003", "2"));
		initMap.put("ITEM000004", new Product("青岛9度", "听", "4.00", "ITEM000004", "2"));
		initMap.put("ITEM000005", new Product("蛋黄派", "袋", "9.00", "ITEM000005", "1,2"));
	}
	
	/**
	 * 默认构造方法，一进入即获得结果
	 */
	public getProductMapper()
	{
		init();
	}
	
	/**
	 * 根据条码查询产品全部信息
	 * @author cyu
	 * @since v1.0
	 * @param barcode 条形码
	 * @see Product
	 * @return 产品信息
	 */
	@Override
	public Product getProductByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return initMap.get(barcode);
	}

}
