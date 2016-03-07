package com.tw.ticket.services;

import java.util.Map;

public interface IPrintTicketServices 
{

	Map<String,Integer> getProductInfo(String list);
	
	String printTicketResult(Map<String,Integer> resultMap);
	
}
