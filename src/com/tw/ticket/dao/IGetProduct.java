package com.tw.ticket.dao;

import com.tw.ticket.model.Product;

public interface IGetProduct {

	Product getProductByBarcode(String barcode);
	
}
