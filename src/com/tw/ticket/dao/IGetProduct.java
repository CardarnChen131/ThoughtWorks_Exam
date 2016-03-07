package com.tw.ticket.dao;

import com.tw.ticket.model.Product;

/**
 * MyBatis的DAO层
 * @author cyu
 * @since v1.0
 *
 */
public interface IGetProduct {

	/**
	 * 根据条码查询产品全部信息
	 * @author cyu
	 * @since v1.0
	 * @param barcode 条形码
	 * @see Product
	 * @return 产品信息
	 */
	Product getProductByBarcode(String barcode);
	
}
