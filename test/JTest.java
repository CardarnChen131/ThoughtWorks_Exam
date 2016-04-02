import org.junit.Test;

import com.tw.ticket.controller.PrintTicketController;


public class JTest {

	private String inputList="['ITEM000001','ITEM000001','ITEM000001','ITEM000002-3','ITEM000002','ITEM000003','ITEM000003','ITEM000004-6','ITEM000005']";
	
	/**
	 * ≤‚ ‘¿‡¥Ú”°≤‚ ‘
	 */
	@Test
	public void test1(){
		PrintTicketController ptc = new PrintTicketController();
		ptc.printTicket(inputList);
	}
	
}
