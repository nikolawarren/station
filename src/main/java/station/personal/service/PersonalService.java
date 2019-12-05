package station.personal.service;

import java.util.List;

import station.personal.pojo.ChangeApply;
import station.personal.pojo.RefundApply;
import station.personal.pojo.Ticket;


public interface PersonalService {
	 Ticket save(String ticket_date,String ticket_time,String ticket_departure,String ticket_destination,Integer user_id);
	
	 List<Ticket> showeffect(Integer user_id);
	
	 void saveRefundApply(String information,Integer userid,Integer ticket_id);
	 
	 void saveChangeApply(String information,String ticket_time,String ticket_departure,String ticket_destination,Integer userid,Integer ticket_id);
	 
	 List<RefundApply> refundlist();
	 
	 List<ChangeApply> changelist();
	 
	 List<Ticket> conditionalquery(String ticket_departure,String ticket_destination);
}
