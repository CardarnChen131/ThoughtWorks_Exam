package com.tw.ticket.services;

import java.util.Map;

public interface IPrintTicketServices 
{

	/**
	 * 获取产品信息，并封装成固定结果
	 * @author cyu
	 * @since v1.0
	 * @param list 入参数
	 * @return 结果集Map
	 */
	Map<String,Integer> getProductInfo(String list);
	
	/**
	 * 获取产品信息，并封装成固定结果
	 * @author cyu
	 * @since v1.0
	 * @param resultMap 入参封装结构的结果集
	 * @return 打印结果
	 */
	String printTicketResult(Map<String,Integer> resultMap);
	
}
