package com.tw.ticket.controller;

import java.util.Map;

import com.tw.ticket.services.IPrintTicketServices;
import com.tw.ticket.services.impl.PrintTicketServicesImpl;

public class PrintTicketController
{
	
	public String printTicket(String inputList)
	{
		IPrintTicketServices iPrintTicketServices = new PrintTicketServicesImpl();
		Map<String,Integer> resultMap = iPrintTicketServices.getProductInfo(inputList);
		String result = iPrintTicketServices.printTicketResult(resultMap);
		System.out.println(result);
		return result;
	}
}
