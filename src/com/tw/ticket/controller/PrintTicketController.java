package com.tw.ticket.controller;

import java.util.Map;

import com.tw.ticket.services.IPrintTicketServices;
import com.tw.ticket.services.impl.PrintTicketServicesImpl;

/**
 * ��ӡƱ�ݵ�Controller
 * ����ʱ�����û�дSpring-MVC-Mybatis��ܣ������տ��Ԥ����������git�ϸ���
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
	 * ��ӡСƱ
	 * @author cyu
	 * @since v1.0
	 * @param inputList �������
	 * @return ��ӡ���
	 */
	public String printTicket(String inputList)
	{
		//����û�п�ܣ�����new�ӿ�
		IPrintTicketServices iPrintTicketServices = new PrintTicketServicesImpl();
		
		//��ȡ��װ����Ľ����
		Map<String,Integer> resultMap = iPrintTicketServices.getProductInfo(inputList);
		
		//��ӡ���
		String result = iPrintTicketServices.printTicketResult(resultMap);
		System.out.println(result);
		return result;
	}
}
