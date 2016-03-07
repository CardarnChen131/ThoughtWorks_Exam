package com.tw.ticket.controller;

import java.util.Map;

import com.tw.ticket.services.IPrintTicketServices;
import com.tw.ticket.services.impl.PrintTicketServicesImpl;

/**
 * 打印票据的Controller
 * 由于时间紧，没有搭建Spring-MVC-Mybatis框架，但按照框架预留，后期再git上更新
 * @author cyu
 * @since v1.0
 *
 */
//@Controller
public class PrintTicketController
{
//	@Autowired
//	private IPrintTicketServices iPrintTicketServices;
	
	/**
	 * 打印小票
	 * @author cyu
	 * @since v1.0
	 * @param inputList 输入参数
	 * @return 打印结果
	 */
	public String printTicket(String inputList)
	{
		//由于没有框架，所以new接口
		IPrintTicketServices iPrintTicketServices = new PrintTicketServicesImpl();
		
		//获取封装结果的结果集
		Map<String,Integer> resultMap = iPrintTicketServices.getProductInfo(inputList);
		
		//打印结果
		String result = iPrintTicketServices.printTicketResult(resultMap);
		System.out.println(result);
		return result;
	}
}
