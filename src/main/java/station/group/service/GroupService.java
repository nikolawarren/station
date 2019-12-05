package station.group.service;

import java.util.List;

import station.group.pojo.GroupApply;
import station.group.pojo.GroupRefund;

public interface GroupService {
	void savegroup(Integer person_number,String group_date,String group_time,String group_departure,String group_destination,Integer user_id);
	
	List<GroupApply> refundshow(Integer user_id);
	
	void saverefund(Integer group_id,String information,Integer user_id);
	
	List<GroupRefund> refundlist();
	

}
