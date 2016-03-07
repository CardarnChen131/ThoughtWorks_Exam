package com.tw.ticket.services;

import java.util.Map;

public interface IPrintTicketServices 
{

	/**
	 * ��ȡ��Ʒ��Ϣ������װ�ɹ̶����
	 * @author cyu
	 * @since v1.0
	 * @param list �����
	 * @return �����Map
	 */
	Map<String,Integer> getProductInfo(String list);
	
	/**
	 * ��ȡ��Ʒ��Ϣ������װ�ɹ̶����
	 * @author cyu
	 * @since v1.0
	 * @param resultMap ��η�װ�ṹ�Ľ����
	 * @return ��ӡ���
	 */
	String printTicketResult(Map<String,Integer> resultMap);
	
}
