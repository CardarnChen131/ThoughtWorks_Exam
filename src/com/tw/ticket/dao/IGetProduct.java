package com.tw.ticket.dao;

import com.tw.ticket.model.Product;

/**
 * MyBatis��DAO��
 * @author cyu
 * @since v1.0
 *
 */
public interface IGetProduct {

	/**
	 * ���������ѯ��Ʒȫ����Ϣ
	 * @author cyu
	 * @since v1.0
	 * @param barcode ������
	 * @see Product
	 * @return ��Ʒ��Ϣ
	 */
	Product getProductByBarcode(String barcode);
	
}
