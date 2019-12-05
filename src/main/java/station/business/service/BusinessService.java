package station.business.service;

import java.util.List;

import station.group.pojo.GroupApply;
import station.property.pojo.Repair;

public interface BusinessService {
	
	List<GroupApply> groupAllshowoncheck();
	List<GroupApply> groupAllshowonapproval();
	void processingrefundonGroup(Integer refund_id,String result,Integer userid);
	void processingrefundonPersonal(Integer refund_id,String result,Integer userid);
	void processingchange(Integer change_id,String result,Integer userid);
	
	void check(Integer group_id,String result,Integer userid);
	void approval(Integer group_id,String result,Integer userid);
	
	List<Repair> showrepair();
	
	void repair(Integer repair_id,String result,Integer userid);
}
